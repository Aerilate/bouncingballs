/* BouncingBall.java
 * The balls that bounce around the screen
 * October 30, 2018
 * Raymond Wang
 */

import java.awt.*;

/**
 * BouncingBall
 * The bouncing ball object
 *
 * @param nothing
 * @return nothing
 */
public class BouncingBall {
    private int xVelocity;
    private int yVelocity;
    private int radius;
    private int xCoordinate;
    private int yCoordinate;
    private final int RADIUSDIVISOR=250;

    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
   private int screenWidth = (int) screenSize.getWidth();
   private int screenHeight = (int) screenSize.getHeight();

    /**
     * BouncingBall
     * Constructor for a bouncing ball
     *
     * @param nothing
     * @return void
     */
    BouncingBall() {
        radius = (screenWidth /RADIUSDIVISOR );
        xCoordinate = (int) (Math.random() * (screenWidth - 2 * radius));
        yCoordinate = (int) (Math.random() * (screenHeight - 2 * radius));
        xVelocity = (int) ((Math.random() * 5) - 5);
        yVelocity = (int) ((Math.random() * 5) - 5);
    }

    /**
     * move
     * The move method
     *
     * @param nothing
     * @return void
     */
    public void move() {
        this.setXCoordinate(this.getXCoordinate() + this.getXVelocity());
        this.setYCoordinate(this.getYCoordinate() + this.getYVelocity());

        if ((this.getXCoordinate() <= 0) || (this.getXCoordinate() >= (screenWidth - radius))) {
            this.setXVelocity(-this.getXVelocity());
        }

        if ((this.getYCoordinate() <= 0) || (this.getYCoordinate() >= (screenHeight - radius))) {
            this.setYVelocity(-this.getYVelocity());
        }
    }

    /**
     * collide
     * The collide method
     *
     * @param BouncingBall otherBall
     * @return void
     */
    public void collide(BouncingBall otherBall) {
        //int collisionX, collisionY;
        int otherVelocityX, otherVelocityY;

        //System.out.println(this.getXCoordinate());
        double dotProduct = ((otherBall.getXCoordinate() - this.getXCoordinate()) * (otherBall.getXVelocity() - this.getXVelocity())
                + (otherBall.getYCoordinate() - this.getYCoordinate()) * (otherBall.getYVelocity() - this.getYVelocity()));

        if (((Math.pow(otherBall.getXCoordinate() - this.getXCoordinate(), 2) +
                Math.pow(otherBall.getYCoordinate() - this.getYCoordinate(), 2))) <= Math.pow((this.getRadius()), 2)) {

            /*  if (dotProduct<0) {
            if ((Math.abs(otherBall.getXCoordinate() - this.getXCoordinate())<=(2*this.getRadius()))
            && (Math.abs-(otherBall.getXCoordinate() - this.getXCoordinate()*yVelocity) <=(2*this.getRadius()))){

            this.setXCoordinate(this.getXCoordinate()+otherBall.getXVelocity());
            this.setYCoordinate(this.getYCoordinate()+otherBall.getYVelocity());

            otherBall.setXCoordinate(otherBall.getXCoordinate()+this.getXVelocity());
            otherBall.setYCoordinate(otherBall.getYCoordinate()+this.getYVelocity());
            if ((((otherBall.getXCoordinate() - this.getXCoordinate())*(otherBall.getXVelocity() - this.getXVelocity()) <0)
                 && ((otherBall.getYCoordinate() - this.getYCoordinate())*(otherBall.getYVelocity() - this.getYVelocity())<0))){}*/

            otherVelocityX = otherBall.getXVelocity();
            otherVelocityY = otherBall.getYVelocity();

            otherBall.setXVelocity(this.getXVelocity());
            otherBall.setYVelocity(this.getYVelocity());
            this.setXVelocity(otherVelocityX);
            this.setYVelocity(otherVelocityY);


        }
    }

    /**
     * getXVelocity
     * Returns the x velocity
     *
     * @param nothing
     * @return xVelocity
     */
    public int getXVelocity() {
        return this.xVelocity;
    }

    /**
     * getYVelocity
     * Returns the y velocity
     *
     * @param nothing
     * @return yVelocity
     */
    public int getYVelocity() {
        return this.yVelocity;
    }

    /**
     * setXCoordinate
     * Sets the x pos
     * 2param nothing
     *
     * @return void
     */
    public void setXCoordinate(int xPos) {
        this.xCoordinate = xPos;
    }

    /**
     * setYCoordinate
     * Sets the y velocity
     *
     * @param the y position
     * @return void
     */
    public void setYCoordinate(int yPos) {
        this.yCoordinate = yPos;
    }

    /**
     * setXVelocity
     * Sets the x velocity
     *
     * @param the x position
     * @return void
     */
    public void setXVelocity(int xVel) {
        this.xVelocity = xVel;
    }

    /**
     * setyVelocity
     * Sets the y velocity
     *
     * @param the y velocity
     * @return void
     */
    public void setYVelocity(int yVel) {
        this.yVelocity = yVel;
    }

    /**
     * getRadius
     * Gets the radius
     *
     * @param nothing
     * @return void
     */
    public int getRadius() {
        return this.radius;
    }

    /**
     * getXCoordinate
     * Gets the x coordinate
     *
     * @param nothing
     * @return int xCoordinate
     */
    public int getXCoordinate() {
        return this.xCoordinate;
    }

    /**
     * getYCoordinate
     * Gets the y coordinate
     *
     * @param nothing
     * @return int yCoordinate
     */
    public int getYCoordinate() {
        return this.yCoordinate;
    }
}
