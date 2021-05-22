package roguelike.model.game.arena;

import roguelike.model.Position;
import roguelike.model.game.elements.*;
import roguelike.model.game.structures.Path;
import roguelike.model.game.structures.Room;

import java.util.*;


public class Arena {

    private int width;
    private int height;
    private Hero hero;
    private int time = 90;
    private List<Room> rooms = new ArrayList<>();
    private List<Path> paths = new ArrayList<>();

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Hero getHero() { return hero; }
    public int getTime() { return time; }
    public List<Room> getRooms() { return rooms; }
    public List<Path> getPaths() { return paths; }

    public void setHero(Hero hero) { this.hero = hero; }
    public void setTime(int time) { this.time = time; }
    public void setRooms(List<Room> rooms) { this.rooms = rooms; }
    public void addRoom(Room room) { rooms.add(room); }
    public void setPaths(List<Path> paths) { this.paths = paths; }

    public String writeTime() { return String.format("%d:%02d", time / 60, time % 60); }
    public void decreaseTime() { time--; }

    public boolean inRoom(Position position) {
        for (Room room : rooms) {
            if (room.inRoom(position))
                return true;
        }
        return false;
    }

    public boolean inInnerRoom(Position position) {
        for (Room room : rooms) {
            if (room.inInnerRoom(position))
                return true;
        }
        return false;
    }

    public boolean inPath(Position position) {
        for (Path path : paths) {
            for (Chunk chunk : path.getChunks()) {
                if (position.equals(chunk.getPosition()))
                    return true;
            }
        }
        return false;
    }

    public Monster getMonster(Position position) {
        for (Room room : rooms) {
            if (room.inRoom(position)) {
                return room.getMonster(position);
            }
        }
        return new Monster(new Position(0, 0), 0, 0);
    }

    public Coin retrieveCoin(Position position) {
        for (Room room : rooms) {
            if (room.inRoom(position)) {
                return room.retrieveCoin(position);
            }
        }
        return new Coin(new Position(0, 0), 0);
    }

    public StrengthPotion retrieveStrengthPotion(Position position) {
        for (Room room : rooms) {
            if (room.inRoom(position)) {
                return room.retrieveStrengthPotion(position);
            }
        }
        return new StrengthPotion(new Position(0, 0), 0, 0);
    }

    public boolean isGoal(Position position) {
        for (Room room : rooms) {
            if (room.inRoom(position)) {
                return room.isGoal(position);
            }
        }
        return false;
    }

    public void attackMonster(Position monsterPosition, int heroStrength, long time) {
        for (Room room : rooms) {
            if (room.inRoom(monsterPosition)) {
                room.attackMonster(monsterPosition, heroStrength, time);
            }
        }
    }
}