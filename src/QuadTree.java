import java.awt.*;
import java.util.ArrayList;

/**
 * QuadTree<E>
 * A QuadTree DS that holds nodes
 */
public class QuadTree<E> {
    public final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public int screenWidth = (int) screenSize.getWidth();
    public int screenHeight = (int) screenSize.getHeight();

    private Node<ArrayList> root = new Node<>(
            null,
            0,
            0,
            screenWidth,
            screenHeight);
    public final int THRESHOLD = 5;

    private ArrayList<BouncingBall> lTList = new ArrayList<>();
    private ArrayList<BouncingBall> lBList = new ArrayList<>();
    private ArrayList<BouncingBall> rBlist = new ArrayList<>();
    private ArrayList<BouncingBall> rTlist = new ArrayList<>();

    public QuadTree(ArrayList<BouncingBall> list) {
        root.setItem(list);
        checkSplit(root, root.getItem());
    }

    /**
     * checkSplit
     * Checks to split into quadrants and adds lists
     *
     * @param node, list2
     * @return void
     */
    public void checkSplit(Node<ArrayList> node, ArrayList<BouncingBall> list2) {
        ArrayList<BouncingBall> lTList2;
        ArrayList<BouncingBall> lBList2;
        ArrayList<BouncingBall> rBList2;
        ArrayList<BouncingBall> rTList2;

        ArrayList<BouncingBall> list = new ArrayList<BouncingBall>();
        for (int i = 0; i < list2.size(); i++) {
            list.add(list2.get(i));
            node.setItem(list);
        }

        //Splits when number of balls are over threshold
        if (list.size() >= THRESHOLD) {
            node.splitTree();

            lTList.clear();
            lBList.clear();
            rTlist.clear();
            rBlist.clear();

            //Counts balls in child quadrants
            for (int i = 0; i < list.size(); i++) {
                if (((list.get(i).getXCoordinate() + list.get(i).getRadius()) >= (node.getLeftTop().getNorthWXCoordinate()))
                        && ((list.get(i).getXCoordinate() + list.get(i).getRadius()) <= (node.getLeftTop().getSouthEXCoordinate()))
                        && (((list.get(i).getYCoordinate()) + list.get(i).getRadius()) >= (node.getLeftTop().getNorthWYCoordinate()))
                        && (list.get(i).getYCoordinate() + list.get(i).getRadius() <= (node.getLeftTop().getSouthEYCoordinate()))) {
                    lTList.add(list.get(i));
                }

                if (((list.get(i).getXCoordinate() + list.get(i).getRadius()) >= (node.getLeftBottom().getNorthWXCoordinate()))
                        && ((list.get(i).getXCoordinate() + list.get(i).getRadius()) <= (node.getLeftBottom().getSouthEXCoordinate()))
                        && (((list.get(i).getYCoordinate()) + list.get(i).getRadius()) >= (node.getLeftBottom().getNorthWYCoordinate()))
                        && (list.get(i).getYCoordinate() + list.get(i).getRadius() <= (node.getLeftBottom().getSouthEYCoordinate()))) {
                    lBList.add(list.get(i));
                }

                if (((list.get(i).getXCoordinate() + list.get(i).getRadius()) >= (node.getRightBottom().getNorthWXCoordinate()))
                        && ((list.get(i).getXCoordinate() + list.get(i).getRadius()) <= (node.getRightBottom().getSouthEXCoordinate()))
                        && (((list.get(i).getYCoordinate()) + list.get(i).getRadius()) >= (node.getRightBottom().getNorthWYCoordinate()))
                        && (list.get(i).getYCoordinate() + list.get(i).getRadius() <= (node.getRightBottom().getSouthEYCoordinate()))) {
                    rBlist.add(list.get(i));
                }

                if (((list.get(i).getXCoordinate() + list.get(i).getRadius()) >= (node.getRightTop().getNorthWXCoordinate()))
                        && ((list.get(i).getXCoordinate() + list.get(i).getRadius()) <= (node.getRightTop().getSouthEXCoordinate()))
                        && (((list.get(i).getYCoordinate()) + list.get(i).getRadius()) >= (node.getRightTop().getNorthWYCoordinate()))
                        && (list.get(i).getYCoordinate() + list.get(i).getRadius() <= (node.getRightTop().getSouthEYCoordinate()))) {
                    rTlist.add(list.get(i));
                }
            }

            lTList2 = new ArrayList<>();
            lBList2 = new ArrayList<>();
            rBList2 = new ArrayList<>();
            rTList2 = new ArrayList<>();

            //Copies the lists
            for (int i = 0; i < lTList.size(); i++) {
                lTList2.add(lTList.get(i));
            }
            for (int i = 0; i < lBList.size(); i++) {
                lBList2.add(lBList.get(i));
            }
            for (int i = 0; i < rBlist.size(); i++) {
                rBList2.add(rBlist.get(i));
            }
            for (int i = 0; i < rTlist.size(); i++) {
                rTList2.add(rTlist.get(i));
            }

            node.getLeftTop().setItem(lTList2);
            node.getLeftBottom().setItem(lBList2);
            node.getRightBottom().setItem(rBList2);
            node.getRightTop().setItem(rTList2);

            //Recursively counts balls in child quadrants
            try {
                if (node.getLeftTop() != null) {
                    checkSplit(node.getLeftTop(), node.getLeftTop().getItem());
                }
                if (node.getLeftBottom() != null) {
                    checkSplit(node.getLeftBottom(), node.getLeftBottom().getItem());
                }
                if (node.getRightBottom() != null) {
                    checkSplit(node.getRightBottom(), node.getRightBottom().getItem());
                }
                if (node.getRightTop() != null) {
                    checkSplit(node.getRightTop(), node.getRightTop().getItem());
                }
            } catch (NullPointerException e) {
                System.out.println("Node deleted");
            }
        } else if (list.size() < THRESHOLD) {
            node.collapseTree();
        }
    }

    /**
     * traverseNode
     * Collects the leaf nodes
     *
     * @param node, tempList
     * @return void
     */
    public void traverseNode(Node<ArrayList> node, ArrayList<Node> tempList) {
        if (node.getLeftTop() != null) {
            tempList.add(node.getLeftTop());
            tempList.add(node.getLeftBottom());
            tempList.add(node.getRightBottom());
            tempList.add(node.getRightTop());

            traverseNode(node.getLeftTop(), tempList);
            traverseNode(node.getLeftBottom(), tempList);
            traverseNode(node.getRightBottom(), tempList);
            traverseNode(node.getRightTop(), tempList);
        }
    }

    public Node<ArrayList> getRootNode() {
        return this.root;
    }
}