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

### OldGame loop

#### Problem

Simple synchronization problem with the monsters movementatino and game updates getting stuck waiting for the players input 

#### Pattern 

It was used the game loop pattern to synchronize the monster movementation. The players input is now get by polling to avoid the halt of the game

#### Implementation

[Here is the current implementation](https://github.com/FEUP-LPOO-2021/lpoo-2021-g65/blob/780842bade1c547aadfa3133323daada60157523/src/main/java/OldGame.java#L51-L74)

## Known Code Smells and Refactoring Suggestions

## Testing

## Self-evaluation
