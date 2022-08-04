import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class NodeManager {

    private Node root;

    private Comparator<Node> comparator;

    private Integer lookingValue;
    private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    ;

    public NodeManager(Comparator<Node> comparator) {
        this.comparator = comparator;
    }

    public Integer read() {
        try {
            return Integer.parseInt(bufferedReader.readLine());
        } catch (IOException e) {
            System.out.println("Произошла Ошибка. Повторите ввод");
            return read();
        }
    }

    public Node add(Integer value) {
        // Проверяем корень дерева на null
        if (this.root == null) {
            // Если корень null, то создаем его, инициализируя переданным занчением
            this.root = new Node(value);
            // Возвращаем созданную ноду
            return this.root;
        }
        // Есали корень не null, то ищем для него место в дереве
        return getNext(this.root, value);
    }

    /**
     * @param root  - Текущая проверяемая нода, т.е. нода для которой
     *              пытаемся добавить элемент в качестве наследника
     * @param value - значение добавляемого элемента
     * @return - вновь созданную ноду
     */
    private Node getNext(Node root, Integer value) {
        // Определяем положение новой ноды в дереве (Слева или справа)
        Direction direction = getDirection(value, root);
        switch (direction) {
            // Если новое положение слева
            case LEFT: {
                // Проверяем у текущей ноды в дереве, свободно ли
                // у нее место слева
                if (root.getLeft() != null) {
                    // Если место не свободно, то рекурсивно вызываем
                    // этот же метод, нов качестве корня передаем следующую ноду
                    // расположенную СЛЕВА
                    return getNext(root.getLeft(), value);
                } else {
                    // Если место свободно, то создаем на этом месте новую ноду
                    Node node = new Node(value, root, Direction.LEFT);
                    // Родительской ноде назначаем наследника слева
                    root.setLeft(node);
                    // Возвращаяю вновь созданную ноду
                    return node;
                }
            }
            case RIGHT: {
                if (root.getRight() != null) {
                    return getNext(root.getRight(), value);
                } else {
                    Node node = new Node(value, root, Direction.RIGHT);
                    root.setRight(node);
                    return node;
                }
            }
            default:
                return root;
        }
    }

    private Direction getDirection(Integer value, Node comparingNode) {
        int compareResult = comparator.compare(comparingNode, new Node(value));
        return compareResult > 0 ? Direction.RIGHT :
                compareResult < 0 ? Direction.LEFT :
                        Direction.EQUAL;
    }

    public Integer get() {
        lookingValue = read();
        if (this.root == null) {
            throw new NoSuchElementException();
        }
        return getLookingValue(this.root, lookingValue);
    }

    public Integer getLookingValue(Node root, Integer lookingValue) {
        Direction direction = getDirection(lookingValue, root);
        switch (direction) {
            case LEFT: {
                if (root.getLeft() != null) {
                    return getLookingValue(root.getLeft(), lookingValue);
                } else {
                    throw new NoSuchElementException();
                }
            }
            case RIGHT: {
                if (root.getRight() != null) {
                    return getLookingValue(root.getRight(), lookingValue);
                } else {
                    throw new NoSuchElementException();
                }
            }
            case EQUAL:
                return lookingValue;
            default:
                throw new NoSuchElementException();
        }
    }

    public Integer maxNubmer() {
        Integer maximum = root.getValue();
        if (this.root == null) {
            throw new NoSuchElementException();
        }
        return getMaxValue(this.root, maximum);
    }

    public Integer getMaxValue(Node root, Integer maximum) {
        Direction direction = getDirection(maximum, root);
        switch (direction) {
            case LEFT: {
                return maximum = root.getValue();
            }
            case RIGHT: {
                if (root.getRight() != null) {
                    maximum = root.getValue();
                    return getLookingValue(root.getRight(), maximum);
                } else {
                    return maximum = root.getValue();
                }
            }
            default:
                return maximum = root.getValue();
        }
    }

    public Integer minNubmer() {
        if (this.root == null) {
            throw new NoSuchElementException();
        }
        Integer minimum = root.getValue();
        return getMinValue(this.root, minimum);
    }

    public Integer getMinValue(Node root, Integer minimum) {
        Direction direction = getDirection(minimum, root);
        minimum = root.getValue();
        switch (direction) {
            case LEFT: {
                if (root.getRight() != null) {
                    return getLookingValue(root.getLeft(), minimum);
                } else {
                    return minimum;
                }

            }
            case RIGHT: {
                return minimum;
            }

        }
        return minimum;
    }

}
