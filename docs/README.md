# LPOO_T06G65 - <project name>

This is a rogue inspired game, the core idea is a (almost) random generated map in which to progess 
you need to slain monster and obtain better gear as the game goes on, until you reach the end.

## Implemented Features

- Path Generation between rooms - A random generator of paths using as a guide the original rogue idea

- Visibilty - The paths and rooms are only revealed when the player approach it

## Planned Features

- A turn based combat system - The idea is for the combat to be in turns between the player and the monster

- A better UI - especialy important for the combat

- Different weapons - to give a sense of progression which new weapon should increase the damage

## Design

### Game loop

#### Problem

Simple synchronization problem with the monsters movementatino and game updates getting stuck waiting for the players input 

### Pattern 

It was used the game loop pattern to synchronize the monster movementation. The players input is now get by polling to avoid the halt of the game

## Known Code Smells and Refactoring Suggestions

## Testing

## Self-evaluation