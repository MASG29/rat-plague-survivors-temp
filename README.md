# RatPlagueSurvivors

A Vampire-Survivors-style 2D top-down game: a sword-wielding cat ("Gato das
Botas") fights off endless waves of mice and rats, leveling up until the
boss — Gigarat — shows up. Built in Java 17 on top of the `simplegraphics`
library (`lib/simplegraphics.jar`).

## Requirements

- Java 17 (JDK)
- Apache Ant

There's no Maven/Gradle — `simplegraphics.jar` is the only third-party
dependency, and it's already vendored in `lib/`.

## Build & run

```bash
ant clean          # remove build/
ant                # compile + package build/RatPlagueSurvivors.jar
java -jar "build/RatPlagueSurvivors.jar"
```

Run it from the repo root — sprites/sounds under `resources/` are loaded by
relative path at runtime.

## Controls

- `WASD` / arrow keys — move
- `ESC` — open/close the menu
- `I` — debug cheat: refill HP to full

Attacking is automatic — the sword swings on its own cooldown toward the
direction you're facing, no attack key needed.

## Gameplay

- 3 maps of increasing difficulty (more obstacles, more enemies).
- Enemy types: Mouse, Rat, and the boss, Gigarat, who shows up after a
  time delay on each map and must be defeated to advance to the next one.
- Kill enemies for XP and level up; a HUD tracks HP, XP and kill count.
- High score (name, score, kills, XP) is saved locally to
  `scoreboard/highscore.txt` and shown on the main menu.

## Project layout

```
src/ratplaguesurvivors/
├── init/       composition root + game state machine (init/states/)
├── systems/    per-tick combat resolution (movement, collisions, damage, XP)
├── entity/     player (entity/pc) and enemies (entity/npc), animation controllers
├── attacks/    attack lifecycle (hitbox, cooldown, damage)
├── weapons/    concrete weapons (sprites, hitbox shape) built on attacks/
├── interfaces/ small role interfaces (Collidable, Moves, Attacks, ...)
├── hud/        HUD components and full-screen menus (main menu, game over, loading)
├── input/      keyboard/mouse handlers, sound, score loading
├── map/        map, obstacles, per-level difficulty
├── utils/      position/hitbox/collision helpers
└── output/     high score persistence

src/tests/      manual visual smoke tests (not wired into the Ant build)
```

There is no automated test runner (no JUnit configured); `src/tests/*.java`
are manual smoke tests you compile/run individually to eyeball a change.
