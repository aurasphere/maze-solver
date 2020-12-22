[![Donate](https://img.shields.io/badge/Donate-PayPal-orange.svg)](https://www.paypal.com/donate/?cmd=_donations&business=8UK2BZP2K8NSS)

# Maze Solver
Application for solving maze pictures using the A* algorithm discussed [in this other project of mine](https://github.com/aurasphere/reply-challenge-2018).

It doesn't work for large pictures since it reads images pixel-by-pixel, without clustering.

For sample usage, check the jUnit. You can use [this website](https://keesiemeijer.github.io/maze-generator/#generate) to generate mazes. Remember to set the wall thickness to 1 and to match the maze color with `MazeSolver.wallColor`.

| Before  | After |
| ------------- | ------------- |
| <img src="src/test/resources/1.png">  | <img src="output/1.png">  |
| <img src="src/test/resources/2.png">  | <img src="output/2.png">  |
| <img src="src/test/resources/3.png">  | <img src="output/3.png">  |
| <img src="src/test/resources/4.png">  | <img src="output/4.png">  |
| <img src="src/test/resources/5.png">  | <img src="output/5.png">  |
 

<sub>Copyright (c) 2018 Donato Rimenti</sub>
