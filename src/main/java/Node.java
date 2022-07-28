public class Node {

    private Integer value;

    private Node parent;

    private Node left;

    private Node right;

    private Direction direction;

    public Node(Integer value) {
        this.value = value;
    }

    public Node(Integer value, Node parent, Direction direction) {
        this.value = value;
        this.parent = parent;
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", parent=" + getParentValue() +
                ", direction=" + direction +
                '}';
    }

    private String getParentValue() {
        return parent == null ? "ROOT" : String.valueOf(parent.getValue());
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
