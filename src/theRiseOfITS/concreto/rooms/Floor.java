package theRiseOfITS.concreto.rooms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import theRiseOfITS.astratto.Room;

public class Floor {
    private String name;
    private Map<Point, Room> rooms = new HashMap<>();
    private Room initialRoom;
    private BossRoom bossRoom; 

    public Floor(String name) {
        this.name = name;
    }

    public Floor() {}

    public Map<Point, Room> getRooms() {
        return rooms;
    }

    public void setRooms(Map<Point, Room> rooms) {
        this.rooms = rooms;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInitialRoom(Room initialRoom) {
        this.initialRoom = initialRoom;
    }

    public String getName() {
        return name;
    }

    public Room getInitialRoom() {
        return initialRoom;
    }

    public BossRoom getBossRoom() {
        return bossRoom;
    }

    public boolean isBossDefeated() {
        return bossRoom != null && bossRoom.isBossDefeated();
    }

    public boolean puòPassareAlProssimoPiano() {
        return isBossDefeated();
    }

    public void join(Room a, Direction dir, Room b) {
        if (a.addDoor(dir, b)) {
            b.addDoor(dir.opposite(), a);
        }
    }

    public void generateMap() {
        Random rand = new Random();
        int totalRooms = rand.nextInt(6, 15);
        List<Room> list = new ArrayList<>();
        initialRoom = new InitialRoom("Stanza Iniziale");

        initialRoom.setPosition(0, 0);
        Point initialPoint = new Point(0, 0);
        rooms.put(initialPoint, initialRoom);
        list.add(initialRoom);

        int i = 1;
        while (rooms.size() < totalRooms) {
            Room base = list.get(rand.nextInt(list.size()));
            List<Direction> directions = new ArrayList<>(List.of(Direction.values()));
            Collections.shuffle(directions);

            boolean joined = false;
            for (Direction direction : directions) {
                Point offset = direction.increment();
                int nx = base.getX() + offset.x;
                int ny = base.getY() + offset.y;
                Point newPoint = new Point(nx, ny);

                if (!rooms.containsKey(newPoint)) {
                    Room newRoom = new RandomRoom("Stanza_" + i);
                    newRoom.setPosition(nx, ny);
                    join(base, direction, newRoom);
                    rooms.put(newPoint, newRoom);
                    list.add(newRoom);
                    joined = true;
                    break;
                }
            }

            if (!joined && list.size() > 0) {
                list.remove(base);
            }
            i++;
        }

        // Stanze speciali
        bossRoom = new BossRoom("Stanza del Boss", name);
        Room treasureRoom = new TreasureRoom("Stanza del tesoro");
        Room merchantRoom = new MerchantRoom("Stanza del mercante");

        List<Room> specialRooms = new ArrayList<>(List.of(bossRoom, treasureRoom, merchantRoom));
        int numberofSpecialRoom = specialRooms.size();
        int count = 0;

        while (count < numberofSpecialRoom) {
            Room base = list.get(rand.nextInt(list.size()));
            List<Direction> directions = new ArrayList<>(List.of(Direction.values()));
            Collections.shuffle(directions);

            boolean joined = false;
            for (Direction direction : directions) {
                Point offset = direction.increment();
                int nx = base.getX() + offset.x;
                int ny = base.getY() + offset.y;
                Point newPoint = new Point(nx, ny);

                if (!rooms.containsKey(newPoint)) {
                    int index = rand.nextInt(specialRooms.size());
                    Room newRoom = specialRooms.get(index);
                    newRoom.setPosition(nx, ny);
                    join(base, direction, newRoom);
                    rooms.put(newPoint, newRoom);
                    specialRooms.remove(index);
                    joined = true;
                    count++;
                    break;
                }
            }

            if (!joined && list.size() > 0) {
                list.remove(base);
            }
        }
    }

    @Override
    public String toString() {
        return "Floor [name=" + name + "]";
    }

    public void printMap() {
        if (rooms.isEmpty()) {
            System.out.println("La mappa è vuota.");
            return;
        }

        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;

        for (Point p : rooms.keySet()) {
            if (p.x < minX) minX = p.x;
            if (p.x > maxX) maxX = p.x;
            if (p.y < minY) minY = p.y;
            if (p.y > maxY) maxY = p.y;
        }

        for (int y = maxY; y >= minY; y--) {
            StringBuilder line1 = new StringBuilder();
            StringBuilder line2 = new StringBuilder();
            StringBuilder line3 = new StringBuilder();

            for (int x = minX; x <= maxX; x++) {
                Point p = new Point(x, y);
                Room room = rooms.get(p);

                if (room != null) {
                    char c;
                    if (room instanceof InitialRoom) c = 'I';
                    else if (room instanceof BossRoom) c = 'B';
                    else if (room instanceof TreasureRoom) c = 'T';
                    else if (room instanceof MerchantRoom) c = 'M';
                    else c = 'R';

                    line1.append(room.getConnectedRoom(Direction.NORD) != null ? " ^ " : "   ");
                    line2.append(room.getConnectedRoom(Direction.OVEST) != null ? "<" : " ");
                    line2.append(c);
                    line2.append(room.getConnectedRoom(Direction.EST) != null ? ">" : " ");
                    line3.append(room.getConnectedRoom(Direction.SUD) != null ? " v " : "   ");
                } else {
                    line1.append("   ");
                    line2.append("   ");
                    line3.append("   ");
                }
            }

            System.out.println(line1.toString());
            System.out.println(line2.toString());
            System.out.println(line3.toString());
        }
    }
}
