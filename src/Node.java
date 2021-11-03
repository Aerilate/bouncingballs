import java.util.ArrayList;

/**
 * Node<T>
 * The node object that occurs in the tree
 */
class Node<T> {
    private ArrayList<BouncingBall> item;
    private Node<T> leftTop,
            leftBottom,
            rightBottom,
            rightTop;
    private int northWestXCoordinate,
            northWestYCoordinate,
            southEastXCoordinate,
            southEastYCoordinate;

    public Node(ArrayList<BouncingBall> item,
                int northWestXCoordinate,
                int northWestYCoordinate,
                int southEastXCoordinate,
                int southEastYCoordinate) {
        this.item = item;
        this.leftTop = null;
        this.leftBottom = null;
        this.rightBottom = null;
        this.rightTop = null;
        this.northWestXCoordinate = northWestXCoordinate;
        this.northWestYCoordinate = northWestYCoordinate;
        this.southEastXCoordinate = southEastXCoordinate;
        this.southEastYCoordinate = southEastYCoordinate;
    }

    /**
     * splitTree
     * Method to add four leaves to a node, one for each quadrant
     *
     * @return void
     */
    public void splitTree() {
        int halfWidth = (this.southEastXCoordinate - this.northWestXCoordinate) / 2;
        int halfHeight = (this.southEastYCoordinate - this.northWestYCoordinate) / 2;

        this.setLeftTop(
                new Node<>(
                        null,
                        this.northWestXCoordinate,
                        this.northWestYCoordinate,
                        halfWidth + this.northWestXCoordinate,
                        halfHeight + this.northWestYCoordinate));
        this.setLeftBottom(
                new Node<>(
                        null,
                        this.northWestXCoordinate,
                        this.northWestYCoordinate + halfHeight,
                        halfWidth + this.northWestXCoordinate,
                        this.southEastYCoordinate));
        this.setRightBottom(
                new Node<>(
                        null,
                        halfWidth + this.northWestXCoordinate,
                        halfHeight + this.northWestYCoordinate,
                        this.southEastXCoordinate,
                        this.southEastYCoordinate));
        this.setRightTop(
                new Node<>(
                        null,
                        halfWidth + this.getNorthWXCoordinate(),
                        northWestYCoordinate,
                        this.southEastXCoordinate,
                        halfHeight + this.northWestYCoordinate));
    }

    /**
     * collapseTree
     * Removes four leaves of a node
     *
     * @return void
     */
    public void collapseTree() {
        this.leftTop = null;
        this.leftBottom = null;
        this.rightBottom = null;
        this.rightTop = null;
    }

    public int getNorthWXCoordinate() {
        return this.northWestXCoordinate;
    }

    public int getNorthWYCoordinate() {
        return this.northWestYCoordinate;
    }

    public int getSouthEXCoordinate() {
        return this.southEastXCoordinate;
    }

    public int getSouthEYCoordinate() {
        return this.southEastYCoordinate;
    }

    public void setLeftTop(Node<T> newNode) {
        this.leftTop = newNode;
    }

    public void setLeftBottom(Node<T> newNode) {
        this.leftBottom = newNode;
    }

    public void setRightBottom(Node<T> newNode) {
        this.rightBottom = newNode;
    }

    public void setRightTop(Node<T> newNode) {
        this.rightTop = newNode;
    }

    public Node<T> getLeftTop() {
        return this.leftTop;
    }

    public Node<T> getLeftBottom() {
        return this.leftBottom;
    }

    public Node<T> getRightBottom() {
        return this.rightBottom;
    }

    public Node<T> getRightTop() {
        return this.rightTop;
    }

    public ArrayList<BouncingBall> getItem() {
        return this.item;
    }

    public void setItem(ArrayList<BouncingBall> item) {
        this.item = item;
    }
}