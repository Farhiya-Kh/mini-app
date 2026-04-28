# mini-app
Description:
Replicate of Flappy Bird Game: 

How to run:
./gradlew appRun


Distribution between team : 
Task 1 - Bird Mechanics:
Responsibilities 
  gravity(Up/Down)
  flap behavior (tap → upward velocity)
  position updates per frame
  bird hitbox (for collision later)
Files: 
  Bird.java
  BirdTest.java 

Task 2 - MainActivity + Game Loop 
Responsibilities 
  app start / restart
  switching between states (START, RUNNING, GAME_OVER)
  connecting UI ↔ game logic
  calling update loop (timer / invalidate / redraw)
Files: 
  MainActivity 
  GameEngine

Task 3 - UI Layout + Custom View
Responsibilities 
  Layout structure like buttons, score text
  Drawing the bird + pipes on screen
  Handling touch input -> pass event to engine 
Files: 
  activity_main.xml
  game_over.xml
  GameView.java (Draws the game on screen)

Task 4 - Obstacles + scoring
Responsibilities 
  spawning pipes
  moving pipes across screen
  removing off-screen pipes
  detecting collision with bird
  incrementing score when passing pipes
Files: 
  Pipe
  PipeManager
  ScoreManager.kt
