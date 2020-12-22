package co.aurasphere.mazesolver;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;

/**
 * Class for solving a maze provided in form of an image.
 * 
 * @author Donato Rimenti
 *
 */
public class MazeSolver {

	public static int mazeHeight;

	public static int mazeWidth;

	public static Set<Node> obstaclePoints;

	public static Color wallColor = Color.BLACK;

	public static Color solutionColor = Color.RED;

	public static BufferedImage solveMaze(BufferedImage maze, Point start, Point end) {
		
		// Sets global variables.
		mazeHeight = maze.getHeight();
		mazeWidth = maze.getWidth();

		// Maps the walls.
		Set<Node> obstacles = new HashSet<>();
		for (int x = 0; x < mazeWidth; x++) {
			for (int y = 0; y < mazeHeight; y++) {
				Color color = new Color(maze.getRGB(x, y));
				if (color.equals(wallColor)) {
					obstacles.add(new Node(x, y));
				}
			}
		}
		obstaclePoints = obstacles;

		// Solves the maze.
		Node result = new AStarAlgorithm().calculateShortestPath(new Node(start), new Node(end));

		// Prints the solution.
		if (result != null) {
			while (result.getParent() != null) {
				maze.setRGB(result.x, result.y, solutionColor.getRGB());
				result = result.getParent();
			}
		}
		return maze;
	}
}