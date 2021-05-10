/* Display.java
 * Displays the simulation
 * October 30, 2018
 * Raymond Wang
 */

//Imports

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import java.util.ArrayList;

/**
 * Display
 * Displays the window
 *
 * @param nothing
 * @return nothing
 */
public class Display {
    private JFrame window;
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    private JPanel drawnPanel;
    private ArrayList<BouncingBall> list = new ArrayList<BouncingBall>();
    private final int STARTINGBALLS =1000;

    /**
     * Display
     * Constructor for the display
     *
     * @param nothing
     * @return nothing
     */
    Display() {
        for (int i = 0; i < STARTINGBALLS; i++) {
            list.add(new BouncingBall());
        }
        window = new JFrame("Ball Simulation");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(screenSize);
        window.setResizable(false);
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.setFocusable(true);

        drawnPanel = new DrawnPanel();
        window.add(drawnPanel);
        window.setVisible(true);

        window.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent f) {
            }

            @Override
            public void keyReleased(KeyEvent f) {
            }

            @Override
            public void keyPressed(KeyEvent f) {
                list.add(new BouncingBall());
            }
        });
    }

    /**
     * DrawnPanel
     * Draws the lines and the balls
     *
     * @param nothing
     * @return nothing
     */
    private class DrawnPanel extends JPanel {

        /**
         * paintComponent
         * Draws the circles
         *
         * @param g
         * @return void
         */
        public void paintComponent(Graphics g) {
            super.paintComponent(g); //required
            setDoubleBuffered(true);

            QuadTree<BouncingBall> quadTree;
            ArrayList<Node> quadList;
            ArrayList<Node> leafList;
            //Draws the ovals
            for (int i = 0; i < list.size(); i++) {
                g.setColor(Color.BLACK);
                g.fillOval(list.get(i).getXCoordinate(), list.get(i).getYCoordinate(), list.get(i).getRadius(), list.get(i).getRadius());

                list.get(i).move();
                repaint();
            }

         quadTree = new QuadTree<BouncingBall>(list);
           quadList = drawLines(quadTree, g);
           leafList = new ArrayList<Node>();

            //Copies the quadList to the leafList
            for (int i = 0; i < quadList.size(); i++) {
                if ((quadList.get(i).getLeftTop() == null) && (quadList.get(i).getLeftTop() == null) && (quadList.get(i).getRightBottom() == null) & (quadList.get(i).getRightTop() == null)) {
                    leafList.add(quadList.get(i));
                }
            }

            //Calls the collide method
            for (int i = 0; i < leafList.size(); i++) {
                for (int j = 0; j < leafList.get(i).getItem().size(); j++) {
                    for (int k = 1; k < leafList.get(i).getItem().size() - j; k++) {
                        ((BouncingBall) (leafList.get(i).getItem().get(j))).collide(((BouncingBall) (leafList.get(i).getItem().get(j + k))));
                    }
                }
            }
        }

        /**
         * drawLines
         * Draws the lines
         *
         * @param quadTree, g
         * @return ArrayList<Node>
         */
        public ArrayList<Node> drawLines(QuadTree<BouncingBall> quadTree, Graphics g) {
            //An arraylist with quadrants
            ArrayList<Node> quadList = new ArrayList<Node>();
            quadList.clear();
            quadList.add(quadTree.getRootNode());
            quadTree.traverseNode(quadTree.getRootNode(), quadList);

            //Draws the box lines
            for (int i = 0; i < quadList.size(); i++) {
                g.setColor(Color.blue);
                g.drawRect(quadList.get(i).getNorthWXCoordinate(), quadList.get(i).getNorthWYCoordinate(),
                        quadList.get(i).getSouthEXCoordinate() - quadList.get(i).getNorthWXCoordinate(),
                        quadList.get(i).getSouthEYCoordinate() - quadList.get(i).getNorthWYCoordinate());
            }
            return quadList;
        }
    }
}
