import java.util.LinkedList;
import java.util.Arrays;
import java.util.HashMap;

public class Day11J {
    private static class FuncCall { //Since i'm using a bfs I want to save the function calls.
        int[] n;
        int k;
        int e;

        public FuncCall(int n[], int k, int e) {
            this.n = new int[n.length];
            for (int i = 0; i < n.length; i++) this.n[i] = n[i];
            this.k = k;
            this.e = e;
        }
    }

    public static void main(String args[]) {
        // Input description: 
        // for every i < n.length/2;
        // n[2*i] = placement of microship_i,
        // n[2*i+1] = placement of generator_i.
        int[] n;
//        if (args.length > 0)
//            n = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 1}; //input part2
//        else
            n = new int[]{0, 0, 1, 0, 1, 0, 2, 2, 2, 2}; //input part1

        HashMap<String, Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= n.length; i++) {
            sb.append(3);
        }
        String finalstate = sb.toString();
        LinkedList<FuncCall> bfs = new LinkedList<>();
        bfs.add(new FuncCall(n, 0, 0));
        while (!bfs.isEmpty()) {
            FuncCall f = bfs.removeFirst();
            if (f.e < 0 || f.e > 3) continue; //elevator outside building
            sortx(f.n);
            if (!ok(f.n)) continue; //microship gets fried
            String s = getStr(f.n, f.e);
            if (map.containsKey(s)) continue; //state we've already been to.
            map.put(s, f.k);
            System.out.println("processing state " + s);
            if (s.equals(finalstate)) break; //found final state!

            for (int i = 0; i < f.n.length; i++) {
                if (f.n[i] != f.e) continue; //element isn't on elevator level.
                f.n[i]--;
                bfs.addLast(new FuncCall(f.n, f.k + 1, f.e - 1)); //put call to move n[i] on que
                f.n[i] += 2;
                bfs.addLast(new FuncCall(f.n, f.k + 1, f.e + 1)); //put call to move n[i] on que
                f.n[i]--;

                for (int j = i + 1; j < f.n.length; j++) {
                    if (f.n[j] != f.e) continue;
                    f.n[i]--;
                    f.n[j]--; //mv down
                    bfs.addLast(new FuncCall(f.n, f.k + 1, f.e - 1)); //put call to move n[i]&n[j] on que
                    f.n[i] += 2;
                    f.n[j] += 2; //mv up
                    bfs.addLast(new FuncCall(f.n, f.k + 1, f.e + 1)); //put call to move n[i]&n[j] on que
                    f.n[i]--;
                    f.n[j]--; //reset
                }
            }
        }
        System.out.print(map.get(finalstate));
    }

    private static class Pair implements Comparable<Pair> { //helper class to the state vector.
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int compareTo(Pair p) {
            if (x != p.x) return x - p.x;
            return y - p.y;
        }
    }

    public static void sortx(int[] n) {
        Pair[] pairs = new Pair[n.length / 2];
        for (int i = 0; i < pairs.length; i++) {
            pairs[i] = new Pair(n[2 * i], n[2 * i + 1]);
        }
        Arrays.sort(pairs);
        for (int i = 0; i < pairs.length; i++) {
            n[2 * i] = pairs[i].x;
            n[2 * i + 1] = pairs[i].y;
        }
    }

    public static boolean ok(int[] n) {
        for (int i = 0; i < n.length; i += 2) //microships
            if (n[i] != n[i + 1])
                for (int j = 1; j < n.length; j += 2) //generator
                    if (n[j] == n[i])  //microship getting fried.
                        return false;

        return true;
    }

    public static String getStr(int n[], int e) {
        StringBuilder sb = new StringBuilder();
        sb.append(e);
        for (int i = 0; i < n.length; i++) {
            sb.append(n[i]);
        }
        return sb.toString();
    }
}