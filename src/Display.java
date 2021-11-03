import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Display {
    private final int STARTING_BALLS = 2500;
    private JFrame window;
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private JPanel drawnPanel;
    private ArrayList<BouncingBall> list = new ArrayList<>();

    Display() {
        for (int i = 0; i < STARTING_BALLS; i++) {
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
            for (BouncingBall bouncingBall : list) {
                g.setColor(Color.BLACK);
                g.fillOval(bouncingBall.getXCoordinate(), bouncingBall.getYCoordinate(), bouncingBall.getRadius(), bouncingBall.getRadius());

                bouncingBall.move();
                repaint();
            }

            quadTree = new QuadTree<>(list);
            quadList = drawLines(quadTree, g);
            leafList = new ArrayList<>();

            //Copies the quadList to the leafList
            for (Node value : quadList) {
                if ((value.getLeftTop() == null) && (value.getLeftTop() == null) && (value.getRightBottom() == null) & (value.getRightTop() == null)) {
                    leafList.add(value);
                }
            }

            //Calls the collide method
            for (Node node : leafList) {
                for (int j = 0; j < node.getItem().size(); j++) {
                    for (int k = 1; k < node.getItem().size() - j; k++) {
                        ((BouncingBall) (node.getItem().get(j))).collide(((BouncingBall) (node.getItem().get(j + k))));
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
            ArrayList<Node> quadList = new ArrayList<>();
            quadList.add(quadTree.getRootNode());
            quadTree.traverseNode(quadTree.getRootNode(), quadList);

            //Draws the box lines
            for (Node node : quadList) {
                g.setColor(Color.blue);
                g.drawRect(node.getNorthWXCoordinate(), node.getNorthWYCoordinate(),
                        node.getSouthEXCoordinate() - node.getNorthWXCoordinate(),
                        node.getSouthEYCoordinate() - node.getNorthWYCoordinate());
            }
            return quadList;
        }
    }
}
