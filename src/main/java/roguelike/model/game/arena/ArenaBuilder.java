package roguelike.model.game.arena;

import roguelike.model.game.elements.Chunk;
import roguelike.model.game.structures.Path;
import roguelike.model.game.structures.Room;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public abstract class ArenaBuilder {

    protected void createPaths(Arena arena){
        ArrayList<Path> paths = new ArrayList<>();
        Random random = new Random();

        Queue<Room> queue = new LinkedList<>();
        for(Room room: arena.getRooms()) {
            queue.add(room);
        }

        while(queue.size() > 1) {

            Path path = new Path();

            boolean hDirection = false;
            boolean vDirection = false;

            Room room1 = queue.poll();
            Room room2 = queue.peek();
            Room mostLeft = room1;
            Room mostTop = room1;
            Room mostRight = room2;
            Room mostBottom = room2;


            if (room1.getX() + room1.getWidht() < room2.getX()) {
                hDirection = true;
                mostLeft = room1;
                mostRight = room2;
            }
            if (room2.getX() + room2.getWidht() < room1.getX()) {
                hDirection = true;
                mostLeft = room2;
                mostRight = room1;
            }
            if (room1.getY() + room1.getHeight() < room2.getY()) {
                vDirection = true;
                mostTop = room1;
                mostBottom = room2;
            }
            if (room2.getY() + room2.getHeight() < room1.getY()) {
                vDirection = true;
                mostTop = room2;
                mostBottom = room1;
            }
            if (hDirection && vDirection) {
                hDirection = random.nextBoolean();
            }
            if (!(hDirection || vDirection)){
                //two room are overlapping
                continue;
            }


            int division;
            if (hDirection) {
                division = random.nextInt(mostRight.getX() - (mostLeft.getX() + mostLeft.getWidht()) - 1) + mostLeft.getX() + mostLeft.getWidht() + 1;
                int leftPointX = mostLeft.getX() + mostLeft.getWidht();
                int leftPointY = random.nextInt(mostLeft.getHeight() - 2) + mostLeft.getY() + 1;

                int rightPointX = mostRight.getX();
                int rightPointY = random.nextInt(mostRight.getHeight() - 2) + mostRight.getY() + 1;


                int x = leftPointX;
                int y = leftPointY;
                while (x < division) {
                    path.add(new Chunk(x, y));
                    x++;
                }
                while (y != rightPointY + 1) {
                    path.add(new Chunk(x, y));
                    if (y > rightPointY) y--;
                    else y++;
                }
                while (x != rightPointX + 1) {
                    path.add(new Chunk(x, y));
                    x++;
                }

            } else {
                division = random.nextInt(mostBottom.getY() - (mostTop.getY() + mostTop.getHeight()) - 1) + mostTop.getY() + mostTop.getHeight() + 1;
                int topPointX = random.nextInt(mostTop.getWidht() - 2) + mostTop.getX() + 1;
                int topPointY = mostTop.getY() + mostTop.getHeight();

                int bottomPointx = random.nextInt(mostBottom.getWidht() - 2) + mostBottom.getX() + 1;
                int bottomPointY = mostBottom.getY();


                int x = topPointX;
                int y = topPointY;
                while (y < division) {
                    path.add(new Chunk(x, y));
                    y++;
                }
                while (x != bottomPointx + 1) {
                    path.add(new Chunk(x, y));
                    if (x > bottomPointx) x--;
                    else x++;
                }
                while (y != bottomPointY + 1) {
                    path.add(new Chunk(x, y));
                    y++;
                }
            }
            paths.add(path);
        }

        arena.setPaths(paths);
    }
}
