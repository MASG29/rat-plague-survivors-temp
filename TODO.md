      # TODO — RatPlagueSurvivors

Lista de organização/limpeza, cruzada com as práticas vistas no `class10`
(JUnit a partir da semana 15, OOP/generics da semana 5-7, builder pattern
da semana 8, etc.).

## 1. Repositório / build

- [x] `build/RatPlagueSurvivors.jar` (77MB!) estava commitado no git.
      `build/` não estava no `.gitignore` (tinha `out/`, `bin/`, `target/`
      mas não `build/`). **Corrigido nesta sessão**: removido do
      tracking (`git rm --cached`) e adicionado `build/` ao `.gitignore`.
      Falta: `git commit` para tornar isto definitivo (não commitei
      automaticamente).
- [ ] `README.md` ainda é o texto genérico de template do VS Code
      ("Welcome to the VS Code Java world..."). Devia descrever o jogo,
      como compilar (`ant`), como correr (`java -jar build/RatPlagueSurvivors.jar`)
      e os controlos (WASD/setas, ESC para menu, `I` = cheat de HP).

## 2. Testes

No `class10`, a partir da semana 15 (`calculator-test`) usámos JUnit 4
(`@Test`, `assertEquals`, nomes de métodos `shouldXxx...`, pasta
`src/test/java` a espelhar `src/main/java`). Nada disto existe aqui:

- [ ] `src/tests/*.java` (`PCTest`, `EnemySpawnTest`, `TestMapGen`) não
      são testes automatizados — são só `main()` que abrem o jogo/menu
      para inspeção visual manual. Não correm no build (`build.xml` não
      tem `<junit>`), nem seguem a convenção `*Test` do JUnit.
- [ ] Não há testes unitários para a lógica pura que dá para testar sem
      gráficos: `CollisionDetector`, `Position`, `Lvl` (XP/level up),
      `EnemyType`/`PCType` (stats por tipo), `ScoreLoader` (parsing do
      ficheiro `name-score-kills-xp`).
- [ ] Adicionar JUnit 4 ao `lib/` (ou migrar para Maven, como as semanas
      mais avançadas do class10) e um target `<junit>` no `build.xml`.

## 3. Código morto / inconsistências

- [~] `weapons/Weapon.java` — **não é dead code**: é o ponto de partida
      para o sistema de inventário planeado (várias armas, armaduras,
      adereços/runas que o jogador poderá equipar). Fica como está por
      agora. Quando o inventário avançar, `BaseAttack`/`Sword` deviam
      provavelmente passar a usar/estender `Weapon` (hoje `Sword extends
      BaseAttack` diretamente, sem qualquer relação com `Weapon`) — a
      ligação entre os dois ainda está por fazer.
- [x] `PlayableCharacter.java:46` — a linha comentada
      `//this.sprite.draw();` **não fazia sentido descomentar**: o
      `LoadingScreen.start()` corre antes do `gameInit()` (ver
      `GameLoop.start()`, caso `NAME_INPUT`), por isso desenhar o sprite
      logo no construtor faria o gato aparecer por cima do ecrã de
      loading. Removida a linha morta e deixado um comentário a explicar
      porque o `draw()` só acontece mais tarde, em `GameLoop.render()`.
- [x] `Enemy.isAlive()` — override que só fazia `return super.isAlive();`
      removido (sem alterar comportamento).
- [x] Comentários em português espalhados por `GameLoop.java`,
      `PlayableCharacter.java`, `Lvl.java`, os quatro
      `*AnimationController.java` e `GameMenu.java` — todos traduzidos
      para inglês, para consistência com o resto da base de código.

## 4. `GameLoop` como "God Class"

- [x] Física/colisões/combate (`checkCollisions`, `checkEnemyCollisions`,
      `pathFind`, `playerMoves`) extraídos para
      `ratplaguesurvivors.systems.CombatSystem`. `GameLoop` só fica a
      saber "chama o combate e reage quando ele diz que o jogador
      morreu ou o boss foi derrotado" (via `CombatEventListener`, novo
      em `interfaces/`). Ver `expl` para o raciocínio completo. `speed`
      também já saiu do `GameLoop` (passou a `PLAYER_SPEED` dentro do
      `CombatSystem`).
- [x] O `switch` de `GameState` em `start()` foi substituído pelo State
      Pattern: `interfaces/State.java` (já existia, sem uso) ganhou
      `throws InterruptedException` e passou a ser implementado por
      5 classes novas em `init/states/` (`MenuState`, `NameInputState`,
      `LoadingState`, `PlayingState`, `GameOverState`). `GameLoop`
      passou a driver fino (`while(true)` + `EnumMap<GameState, State>`
      + `StateSwitcher`); todo o resto (player/map/hud/spawner/menus e
      os métodos `gameInit`/`mapUpdate`/`clearMap`/`restartGame`/
      `render`/`update`) mudou-se para `init/GameContext.java`, o novo
      composition root partilhado pelos 5 estados. Ver `expl`, Passo 4,
      para o raciocínio (em particular: porque é que `render()`/
      `renderState()` não corre por tick).
- [x] Recompensa por matar inimigo normal (XP, kill count, cleanup de
      sprite, contagem do spawner) extraída de `CombatSystem` para
      `systems/EnemyRewardSystem.java` — `CombatSystem` fica só com a
      deteção de colisão e a decisão "avisar o boss morreu". Ver `expl`,
      Passo 3.
- [x] Números mágicos nomeados: `BOSS_SPAWN_DELAY_MS` (era `30000`, hoje
      em `GameContext.update()`), `ATTACK_COOLDOWN_TICKS` (era `60`, em
      `Enemy.cooldownReset()`), `HITBOX_OFFSET_X/Y`, `BOSS_SPRITE_SIZE`,
      `BOSS_HITBOX_OFFSET_X/Y` (offsets `40`/`10`/`192` em `Enemy`),
      `HITBOX_WIDTH/HEIGHT` (offsets `40`/`60` em `PlayableCharacter`).
      Ver `expl`, Passo 2.

## 5. `KeyboardHandlers.java`

- [ ] `eventKey()` regista à mão ~60 `KeyboardEvent` (pressed+released
      para cada letra do alfabeto, mesmo as que nunca são usadas). Dá
      para gerar isto num loop sobre uma lista de keys em vez de ter
      500+ linhas repetidas à mão.

## 6. Outras notas

- [ ] `scoreboard/highscore.txt` cai no `*.txt` do `.gitignore`, por isso
      nunca fica versionado — cada clone novo começa sem highscore.
      Confirmar que é mesmo o comportamento pretendido (parece que sim,
      é ficheiro gerado em runtime, não código).

---

**Aplicado nesta sessão:** item 1 (jar de build removido do tracking do
git + `build/` adicionado ao `.gitignore`).

**Aplicado numa sessão seguinte:** item 4 completo (`GameLoop` deixou
de ser God Class — `CombatSystem`, `EnemyRewardSystem`, números
mágicos nomeados e State Pattern via `GameContext`/`init/states/`; ver
`expl`). Resto por fazer.
