import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/*

*/
public class Day19J {
    public static int part1(int count) {
        return 0;
    }


    public static int part2(int count) {
/*
        Deque<Integer> left2 = IntStream.rangeClosed(1, count / 2).boxed().collect(Collectors.toCollection(LinkedList::new));
        Deque<Integer> right2 = IntStream.rangeClosed(count / 2 + 1, count).boxed().collect(Collectors.toCollection(LinkedList::new));
        static IntStream revRange(int from, int to) {
            return IntStream.range(from, to)
                    .map(i -> to - i + from - 1);
        }
*/


        Deque<Integer> left = new LinkedList<>();
        Deque<Integer> right = new LinkedList<>();

        int half = (count / 2) + 1;
        for (int i = 1; i <= count; i++) {
            if (i < half) {
                left.addLast(i);
            } else {
                right.addFirst(i);
            }
        }

        while (!left.isEmpty() && !right.isEmpty()) {
            if (left.size() > right.size()) {
                left.removeLast();
            } else {
                right.removeLast();
            }
            right.addFirst(left.removeFirst());
            left.addLast(right.removeLast());
        }
        if (left.isEmpty()) return right.getFirst();
        else return left.getFirst();
    }

    public static void main(String[] args) {
        System.out.println(part2(5));
        System.out.println(part2(3018458));
    }
}

/*
int main(){
    int N = 3001330;
    int prevAns = 2;

    for(int i = 4; i <= N; i++) {
        prevAns = 1 + prevAns;
        prevAns %= (i - 1);
        if(prevAns >= i / 2) prevAns++;
    }
    cout << prevAns << endl;

    return 0;
}


public static int GetFormulaCrossPosition(int n)
{
    int pow = (int)Math.Floor(Math.Log(n) / Math.Log(3));
    int b = (int)Math.Pow(3, pow);
    if (n == b)
        return n;
    if (n - b <= b)
        return n - b;
    return 2 * n - 3 * b;
}


 */