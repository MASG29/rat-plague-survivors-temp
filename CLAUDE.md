# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## What this is

RatPlagueSurvivors — a Java 17 Vampire-Survivors-style 2D game (a cat fighting waves of rats/mice/a boss "Gigarat") built on the `simplegraphics` library (`lib/simplegraphics.jar`, package `com.codeforall.online.simplegraphics.*`). There is no dependency manager (no Maven/Gradle); the only third-party jar lives in `lib/`.

## Build & run

Build system is Ant (`build.xml`), java `release="17"`.

```bash
ant clean          # remove build/
ant                # compile + package build/RatPlagueSurvivors.jar (default target: jarfile)
java -jar "build/RatPlagueSurvivors.jar"
```

`run.sh` does exactly the above three steps.

The jar bundles `lib/*.jar` (via `zipgroupfileset`) and the whole `resources/` directory (sprites/sounds), and sets `Main-Class: Main`. Entry point is `src/Main.java`, which just constructs `ratplaguesurvivors.init.GameLoop`, calls `init()`, then `start()`.

There is no JUnit/test runner configured. `src/tests/*.java` (package `tests`) are manual, visual smoke tests — each has a `main()` that boots a `GameLoop` or a specific menu/screen so a human can eyeball behavior; they are not wired into the Ant build and aren't run automatically. Compile/run them individually with `javac`/`java` against the same classpath (`lib/*.jar` + compiled classes) when you need to sanity-check a change visually.

## Architecture

Everything lives under `src/ratplaguesurvivors/`, split by concern:

- `init/` — `GameLoop` is now a thin driver: it owns the single `while(true)` loop (`start()`, `Thread.sleep(10)`, ~100 ticks/sec) and an `EnumMap<GameState, State>`, delegating each tick to `currentState.updateState()` and switching `currentState` when a `State` calls back through the `StateSwitcher` interface it implements. `GameContext` (also in `init/`) is the composition root and holds everything the states share — player, map, HUD, spawner, menus, keyboard/sound handlers, the per-map `CombatSystem` — plus the methods that operate on them (`gameInit`, `mapUpdate`, `clearMap`, `restartGame`, `render`, `update`). One `State` implementation per `GameState` lives in `init/states/` (`MenuState`, `NameInputState`, `LoadingState`, `PlayingState`, `GameOverState`), each holding just a `GameContext` + `StateSwitcher` reference; `renderState()` is intentionally a no-op on all of them because `render()` isn't a per-tick concern (see below) — it's called directly by `GameContext`/states only at state-entry points (LOADING→GAME, restart). `GameContext` implements `CombatEventListener` (`onPlayerDied`/`onBossDefeated`) since reacting to those touches its own state (`lvl`, `render`, `mapUpdate`); it only asks for a `GameState` transition through `StateSwitcher`. See `expl` at the repo root for the full reasoning/history of this refactor (it replaced an all-in-`GameLoop` `switch(gameState)`).
- `systems/` — `CombatSystem` resolves one tick of player movement, enemy chase/attack behaviour and collision damage, given the shared `collidables` list; when a non-boss enemy dies it delegates XP/kill-count/cleanup bookkeeping to `EnemyRewardSystem` (kept separate from collision detection). `CombatSystem` doesn't know about `GameState` or `MapLevel`; it reports player death / boss defeat back to its owner via `CombatEventListener` (mirrors the `MouseInputListener` decoupling in `hud`/`input`).
- `entity/` — `Entity` (abstract base: name, HP, `Position`, hitbox) implements `Collidable`. `entity/pc/PlayableCharacter` (the cat) and `entity/npc/Enemy` (mouse/rat/Gigarat boss, parameterized by `EnemyType`) both extend it. `BaseAnimationController` is the shared frame-ticking abstraction (`TICKS_PER_FRAME`); concrete controllers (`AnimationController`, `RatAnimationController`, `MouseAnimationController`, `BossAnimationController`) drive `Picture` sprites per entity type. `EnemySpawner` handles timed spawning, per-type population caps (mouse/rat/boss), and overlap checks against obstacles/other enemies.
- `attacks/` + `weapons/` — `BaseAttack` (abstract, implements `Attacks`+`Collidable`) is the per-weapon attack/hitbox lifecycle (damage, cooldown, hitbox); `Sword`/`SwordAnimationController` etc. implement a specific weapon's visuals and hitbox shape.
- `interfaces/` — small role interfaces composed onto entities rather than a deep class hierarchy: `Collidable` (hitbox-based collision contract used everywhere), `Moves`, `Attacks`, `State`, `EnemyAnimationController`, `MouseInputListener` (see below).
- `hud/` — `HUD` composes `HUDComponent`s (background/health bar, XP display, kill scoreboard), each built from `HUDNumberText`/`HUDImageText`/`HUDLetters`/`HUDDigits` sprite-based "bitmap font" renderers (no native text rendering — digits/letters are drawn from sprite sheets). `GameMenu`, `GameOverMenu`, `LoadingScreen` are the other screens driven by `GameState`.
- `input/` — `KeyboardHandlers` implements `simplegraphics`'s `KeyboardHandler`; it registers a pressed+released `KeyboardEvent` listener pair for essentially every key individually (there's no generic "any key" hook in the library), then folds them into a single `Directions` enum for movement. `MouseHandler` dispatches to a list of `MouseInputListener`s (see `GameMenu`, which implements that interface) rather than holding concrete references to menu classes — this decoupling was a deliberate recent refactor (see git log), so keep new mouse-driven UI wired the same way (implement `MouseInputListener`, register with `MouseHandler`) instead of adding new coupling back in.
- `map/` — `Map` owns the background `Picture`, `MapLevel` (per-level difficulty: obstacle count, max enemy counts, map asset path) and `Obstacle`s; movement is implemented by moving the *map and obstacles* under a fixed player rather than moving the player sprite (a scrolling-world illusion) — see `CombatSystem.playerMoves()`.
- `utils/` — `Position` (x/y/width/height + translate, the universal coordinate/hitbox type), `Hitbox`, `CollisionDetector` (rectangle-overlap logic used by every `Collidable.hasCollided`), `Util` (misc/random helpers).
- `output/` — `ScoreWriter` persists high scores to `scoreboard/highscore.txt`; `input/ScoreLoader` reads them back.

### Collision model

Nearly every game object (player, enemies, map, obstacles, HUD components, attacks) implements `Collidable` (`getPos`, `getHitbox`, `hasCollided`, `collided`). `CombatSystem` (see `systems/`) is the sole place that iterates all `Collidable`s each tick and calls `collided()` on both sides — individual classes don't discover collisions on their own, they only react to being told about one. When adding a new collidable type, follow this pattern rather than having objects poll each other directly.

### Sprite/type enums

Character/enemy/weapon variants are driven by enums that centralize per-type stats and asset paths (e.g. `EnemyType`, `PCType`, `CatSpriteType`, `RatSpriteType`, `MouseSpriteType`, `BossSpriteType`, `SwordSpriteType`, `ObstacleType`, `MapLevel`). When adding a new enemy/character/level variant, prefer extending the relevant enum over introducing a new class hierarchy.

## Assets

`resources/` (sprites, sounds, HUD/menu art) is copied verbatim into the built jar and also referenced directly by relative path at runtime (e.g. `"resources/sound/v3-8bits.wav"`, `"resources/Menu/HUD/KillScoreboard.png"`) — run the game (`java -jar ...` or the `tests` mains) from the repo root so these relative paths resolve.
