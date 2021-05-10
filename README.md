# Bouncing Balls
Bouncing Balls is a Java program aimed to optimize the collision of balls using a region quadtree algorithm.
Here is what the simulation looks like with over 1000 bouncing balls:

![GIF of Bouncing Balls Program](bouncingballs.gif)

## The Algorithm
The screen is split into quadrants. When there is a certain quantity of balls in the same quadrant, that quadrant then gets split into four quadrants as well. Only balls in the same quadrant are checked against each other for collisions.

## Implementation
This program was implemented with Java and its Swing elements. This program leverages OO design.
