/* Node.java
 * Nodes that hold the array list
 * October 30, 2018
 * Raymond Wang
 */

import java.util.ArrayList;

/** Node<T>
 * The node object that occurs in the tree
 * @param nothing
 * @return nothing
 */
class Node<T> {
    private ArrayList<BouncingBall> item;
    private Node<T> leftTop, leftBottom, rightBottom, rightTop;
    private int northWestXCoordinate, northWestYCoordinate, southEastXCoordinate, southEastYCoordinate;

    /** Node
     * Constructor for a node object
     * @param ArrayList<BouncingBall> item
     * int northWestXCoordinate, int northWestYCoordinate, int southEastXCoordinate, int southEastYCoordinate
     * @return nothing
     */
    public Node(ArrayList<BouncingBall> item, int northWestXCoordinate, int northWestYCoordinate, int southEastXCoordinate, int southEastYCoordinate) {
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

    /** splitTree
     * Method to add four leaves to a node, one for each quadrant
     * @param nothing
     * @return void
     */
    public void splitTree() {
        int halfWidth = (this.southEastXCoordinate - this.northWestXCoordinate) / 2;
         int halfHeight = (this.southEastYCoordinate - this.northWestYCoordinate) / 2;

        this.setLeftTop(new Node<T>(null, this.northWestXCoordinate, this.northWestYCoordinate,halfWidth+ this.northWestXCoordinate, halfHeight+ this.northWestYCoordinate));
        this.setLeftBottom(new Node<T>(null, this.northWestXCoordinate, this.northWestYCoordinate+halfHeight, halfWidth+ this.northWestXCoordinate, this.southEastYCoordinate));
        this.setRightBottom(new Node<T>(null, halfWidth + this.northWestXCoordinate, halfHeight+this.northWestYCoordinate, this.southEastXCoordinate, this.southEastYCoordinate));
        this.setRightTop(new Node<T>(null, halfWidth+this.getNorthWXCoordinate(), northWestYCoordinate, this.southEastXCoordinate, halfHeight+ this.northWestYCoordinate));

    }

    /** collapseTree
     * Removes four leaves of a node
     * @param nothing
     * @return void
     */
    public void collapseTree(){
        this.leftTop = null;
        this.leftBottom = null;
        this.rightBottom = null;
        this.rightTop = null;
    }

    /** getNorthWXCoordinate
     * Returns the north west x coordinate
     * @param nothing
     * @return northWestXCoordinate
     */
    public int getNorthWXCoordinate(){
        return this.northWestXCoordinate;
    }

    /** getNorthWYCoordinate
     * Returns the north west y coordinate
     * @param nothing
     * @return northWestYCoordinate
     */
    public int getNorthWYCoordinate(){
        return this.northWestYCoordinate;
    }

    /** getSouthEXCoordinate
     * Returns the south east x coordinate
     * @param nothing
     * @return southEastXCoordinate
     */
    public int getSouthEXCoordinate(){
        return this.southEastXCoordinate;
    }

    /** getSouthEYCoordinate
     * Returns the south east Y coordinate
     * @param nothing
     * @return southEastYCoordinate
     */
    public int getSouthEYCoordinate(){
        return this.southEastYCoordinate;
    }

    /** setLeftTop
     * Sets the top left node
     * @param Node<T> newNode
     * @return void
     */
    public void setLeftTop(Node<T> newNode) {
        this.leftTop = newNode;
    }

    /** setLeftBottom
     * Sets the bottom left node
     * @param Node<T> newNode
     * @return void
     */
    public void setLeftBottom(Node<T> newNode) {
        this.leftBottom = newNode;
    }

    /** setRightBottom
     * Sets the top right node
     * @param Node<T> newNode
     * @return void
     */
    public void setRightBottom(Node<T> newNode) {
        this.rightBottom = newNode;
    }

    /** setRightTop
     * Sets the top right node
     * @param Node<T> newNode
     * @return void
     */
    public void setRightTop(Node<T> newNode) {
        this.rightTop = newNode;
    }

    /** getLeftTop
     * Gets the top left node
     * @param nothing
     * @return Node<T>
     */
    public Node<T> getLeftTop() {
        return this.leftTop;
    }

    /** getLeftBottom
     * Gets the bottom left node
     * @param nothing
     * @return Node<T>
     */
    public Node<T> getLeftBottom() {
        return this.leftBottom;
    }

    /** getRightBottom
     * Gets the bottom right node
     * @param nothing
     * @return Node<T>
     */
    public Node<T> getRightBottom() {
        return this.rightBottom;
    }

    /** getRightTop
     * Gets the top right node
     * @param nothing
     * @return Node<T>
     */
    public Node<T> getRightTop() {
        return this.rightTop;
    }

    /** getItem
     * Gets a node's item
     * @param nothing
     * @return ArrayList<BouncingBall>
     */
    public ArrayList<BouncingBall> getItem() {
        return this.item;
    }

    /** setItem
     * Sets a node's item
     * @param ArrayList<BouncingBall> item
     * @return void
     */
    public void setItem(ArrayList<BouncingBall> item) {
        this.item = item;
    }
}