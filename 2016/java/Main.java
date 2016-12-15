import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Main {

//    public static String[] input = new String[]{"R8", "R4", "R4", "R8"};
    public static String[] input = new String[]{"R4", "R4", "L1", "R3", "L5", "R2", "R5", "R1", "L4", "R3", "L5", "R2", "L3", "L4", "L3", "R1", "R5", "R1", "L3", "L1", "R3", "L1", "R2", "R2", "L2", "R5", "L3", "L4", "R4", "R4", "R2", "L4", "L1", "R5", "L1", "L4", "R4", "L1", "R1", "L2", "R5", "L2", "L3", "R2", "R1", "L194", "R2", "L4", "R49", "R1", "R3", "L5", "L4", "L1", "R4", "R2", "R1", "L5", "R3", "L5", "L4", "R4", "R4", "L2", "L3", "R78", "L5", "R4", "R191", "R4", "R3", "R1", "L2", "R1", "R3", "L1", "R3", "R4", "R2", "L2", "R1", "R4", "L5", "R2", "L2", "L4", "L2", "R1", "R2", "L3", "R5", "R2", "L3", "L3", "R3", "L1", "L1", "R5", "L4", "L4", "L2", "R5", "R1", "R4", "L3", "L5", "L4", "R5", "L4", "R5", "R4", "L3", "L2", "L5", "R4", "R3", "L3", "R1", "L5", "R5", "R1", "L3", "R2", "L5", "R5", "L3", "R1", "R4", "L5", "R4", "R2", "R3", "L4", "L5", "R3", "R4", "L5", "L5", "R4", "L4", "L4", "R1", "R5", "R3", "L1", "L4", "L3", "L4", "R1", "L5", "L1", "R2", "R2", "R4", "R4", "L5", "R4", "R1", "L1", "L1", "L3", "L5", "L2", "R4", "L3", "L5", "L4", "L1", "R3"};

    public static Set<String> visited = new HashSet<>();

    public static void main(String[] args) {
        int x = 0;
        int y = 0;
        int xM = 0;
        int yM = 0;
        int face = 0;
        for (int i = 0; i < input.length; i++) {
            String s = input[i];
            String dir = "" + s.charAt(0);
            int len = Integer.parseInt(s.substring(1));
            if (face == 0) { // N
                if (Objects.equals(dir, "R")) {
                    face = 1; // E
                    addLocation(x, len, y, 0);
                    x = x + len;
                } else if (Objects.equals(dir, "L")) {
                    face = 3;
                    addLocation(x, -len, y, 0);
                    x = x - len;
                }
            } else if (face == 1) { // E
                if (Objects.equals(dir, "R")) {
                    face = 2; // E
                    addLocation(x, 0, y, -len);
                    y = y - len;
                } else if (Objects.equals(dir, "L")) {
                    face = 0;
                    addLocation(x, 0, y, len);
                    y = y + len;
                }
            } else if (face == 2) { // S
                if (Objects.equals(dir, "R")) {
                    face = 3; // W
                    addLocation(x, -len, y, 0);
                    x = x - len;
                } else if (Objects.equals(dir, "L")) {
                    face = 1;
                    addLocation(x, len, y, 0);
                    x = x + len;
                }
            } else if (face == 3) { // W
                if (Objects.equals(dir, "R")) {
                    face = 0; // E
                    addLocation(x, 0, y, len);
                    y = y + len;
                } else if (Objects.equals(dir, "L")) {
                    face = 2;
                    addLocation(x, 0, y, -len);
                    y = y - len;
                }
            }
        }
        System.out.println(Math.abs(x) + Math.abs(y));
    }

    public static void addLocation(int x, int dx, int y, int dy) {
        if (dx > 0) {
            for (int i = 1; i <= dx; i++) {
                String location = "" + (x+i) + "," + y;
                if (visited.contains(location)) {
                    System.out.println(location + ": " + (Math.abs(x+i) + Math.abs(y)));
                }
                visited.add(location);
            }
        } else if (dx < 0) {
            for (int i = 1; i <= -dx; i++) {
                String location = "" + (x-i) + "," + y;
                if (visited.contains(location)) {
                    System.out.println(location + ": " + (Math.abs(x-i) + Math.abs(y)));
                }
                visited.add(location);
            }
        } else if (dy > 0) {
            for (int i = 1; i <= dy; i++) {
                String location = "" + x + "," + (y+i);
                if (visited.contains(location)) {
                    System.out.println(location + ": " + (Math.abs(x) + Math.abs(y+i)));
                }
                visited.add(location);
            }
        } else if (dy < 0) {
            for (int i = 1; i <= -dy; i++) {
                String location = "" + x + "," + (y-i);
                if (visited.contains(location)) {
                    System.out.println(location + ": " + (Math.abs(x) + Math.abs(y-i)));
                }
                visited.add(location);
            }
        }
    }
}
