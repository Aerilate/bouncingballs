import java.awt.*;

/**
 * BouncingBall
 * Represents a ball subject to elastic collisions
 */
public class BouncingBall {
    private int xVelocity;
    private int yVelocity;
    private int radius;
    private int xCoordinate;
    private int yCoordinate;
    private final int RADIUSDIVISOR = 250;

    // required for collision checks
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int screenWidth = (int) screenSize.getWidth();
    private int screenHeight = (int) screenSize.getHeight();

    BouncingBall() {
        radius = (screenWidth / RADIUSDIVISOR);
        xCoordinate = (int) (Math.random() * (screenWidth - 2 * radius));
        yCoordinate = (int) (Math.random() * (screenHeight - 2 * radius));
        xVelocity = (int) ((Math.random() * 5) - 5);
        yVelocity = (int) ((Math.random() * 5) - 5);
    }

    /**
     * move
     * Defines change in ball position over time
     *
     * @return void
     */
    public void move() {
        setXCoordinate(getXCoordinate() + getXVelocity());
        setYCoordinate(getYCoordinate() + getYVelocity());

        if ((getXCoordinate() <= radius) || (screenWidth <= getXCoordinate() + radius)) {
            setXVelocity(-getXVelocity());
        }

        if ((getYCoordinate() <= radius) || (screenWidth <= getYCoordinate() + radius)) {
            setYVelocity(-getYVelocity());
        }
    }

    /**
     * collide
     * Defines the behaviour when two balls collide
     *
     * @param BouncingBall otherBall
     * @return void
     */
    public void collide(BouncingBall otherBall) {
        double distance = Math.pow(otherBall.getXCoordinate() - getXCoordinate(), 2)
                + Math.pow(otherBall.getYCoordinate() - getYCoordinate(), 2);
        if (distance <= Math.pow((getRadius()), 2)) {
            int otherVelocityX, otherVelocityY;
            otherVelocityX = otherBall.getXVelocity();
            otherVelocityY = otherBall.getYVelocity();

            otherBall.setXVelocity(getXVelocity());
            otherBall.setYVelocity(getYVelocity());
            setXVelocity(otherVelocityX);
            setYVelocity(otherVelocityY);
        }
    }

    public int getXVelocity() {
        return xVelocity;
    }

    public int getYVelocity() {
        return yVelocity;
    }

    public void setXCoordinate(int xPos) {
        xCoordinate = xPos;
    }

    public void setYCoordinate(int yPos) {
        yCoordinate = yPos;
    }

    public void setXVelocity(int xVel) {
        xVelocity = xVel;
    }

    public void setYVelocity(int yVel) {
        yVelocity = yVel;
    }

    public int getRadius() {
        return radius;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }
}
