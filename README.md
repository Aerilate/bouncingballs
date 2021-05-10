# :basketball: Bouncing Balls :basketball:
Bouncing Balls is a Java program aimed to optimize the collision of balls using a region quadtree algorithm. The simulation can handle over 20,000 bouncing balls.
Here is what the simulation looks like with 1000 bouncing balls:

![GIF of Bouncing Balls Program](bouncingballs.gif)\
&nbsp;

## The Algorithm
The screen is split into quadrants. When there is a certain quantity of balls in the same quadrant, that quadrant then gets split into four quadrants as well (and so on...). Only balls in the same quadrant are checked against each other for collisions. When there are not enough balls in four split quadrants, the quadrants recombine as one. \
&nbsp;

## Implementation
This program was implemented with Java and Swing. This program leverages OO design.\
&nbsp;
