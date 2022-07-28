import java.util.Comparator;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        NodeManager manager = new NodeManager(new Comparator<Node>() {
            public int compare(Node o1, Node o2) {
                return o2.getValue() - o1.getValue();
            }
        });
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            Node node = manager.add(random.nextInt(100));
            System.out.println(node);
        }
    }
}
