import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;

public class Task2 {
    private static final int SIZE = 2_000_000_000;
    static HashSet<String> set = new HashSet<>();
    public static void main(String[] args) {
        for (int i = 0; i < SIZE; i++) {
            createUnusedObjects();
        }
        System.out.println("Finished; total items: " + set.size());
    }
    private static void createUnusedObjects(){
        Object o = new Task2();
        if (ThreadLocalRandom.current().nextInt(500)==0)
            set.add(o.toString());
    }
}
