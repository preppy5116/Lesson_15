import java.util.HashMap;

public class Task1 {
    public static void main(String[] args) {
        int SIZE = 100000;
        HashMap<Integer, String> map = new HashMap<>();
        for (int i = 0; i < SIZE; i++) {
            map.put(i, putValue(i));
        }
    }
    private static String putValue(int i){
        return "Value" + i;
    }
}