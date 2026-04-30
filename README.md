# Mini App

## Description
Replicate of Flappy Bird game.

## How to Run
press play button 
## How to test 
./gradlew test  

## Team Overview
This project was developed as a team effort, with each member responsible for a core part of the game. The work is divided into focused components that integrate into a complete gameplay system.

## Task Distribution

### Task 1: Bird Mechanics
**Owner:** Maimun

**Responsibilities**
- Gravity (up and down movement)
- Flap behavior (tap to apply upward velocity)
- Position updates per frame
- Bird hitbox for collision handling

**Files**
- Bird.java
- BirdTest.java

### Task 2: Main Activity and Game Loop
**Owner:** Anisa

**Responsibilities**
- App start and restart logic
- Switching between states (START, RUNNING, GAME_OVER)
- Connecting UI with game logic
- Running the update loop (timer, invalidate, redraw)

**Files**
- MainActivity
- GameEngine

### Task 3: UI Layout and Custom View
**Owner:** Rahma

**Responsibilities**
- Layout structure including buttons and score text
- Drawing the bird and pipes on screen
- Handling touch input and passing events to the engine

**Files**
- activity_main.xml
- game_over.xml
- GameView.java

### Task 4: Obstacles and Scoring
**Owner:** Farhiya

**Responsibilities**
- Spawning pipes
- Moving pipes across the screen
- Removing off screen pipes
- Detecting collision with the bird
- Incrementing score when passing pipes

**Files**
- Pipe
- PipeManager
- ScoreManager.kt
