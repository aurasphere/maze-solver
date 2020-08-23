package co.aurasphere.mazesolver;

import java.awt.Color;
import java.awt.Graphics;
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

	public static BufferedImage solveMaze(BufferedImage maze, Point start, Point end, Point cursorSize) {
		CannyEdgeDetector detector = new CannyEdgeDetector();

		// adjust its parameters as desired
		detector.setLowThreshold(0.5f);
		detector.setHighThreshold(20f);

		// apply it to an image
		detector.setSourceImage(maze);
		detector.process();
		BufferedImage edgesImage = detector.getEdgesImage();

		Graphics graphics = maze.getGraphics();
		// Starting point.
		graphics.setColor(Color.GREEN);
		graphics.drawOval(start.x, start.y, 3, 3);

		// End point.
		graphics.drawOval(end.x, end.y, 3, 3);

		int nodeDistanceX = cursorSize.x;
		int nodeDistanceY = cursorSize.y;
		Set<Node> obstacles = new HashSet<>();
		for (int x = 0; x < edgesImage.getWidth(); x += cursorSize.x) {
			for (int y = 0; y < edgesImage.getHeight(); y += cursorSize.y) {
//				Color color = new Color(edgesImage.getRGB(x, y)); 
				Color color = getColorFromBlock(edgesImage, cursorSize, x, y);
				if (color.equals(Color.BLACK)) {
					obstacles.add(new Node(x - nodeDistanceX, y - nodeDistanceY));
					obstacles.add(new Node(x - nodeDistanceX, y - nodeDistanceY));
					obstacles.add(new Node(x - nodeDistanceX, y - nodeDistanceY));
					obstacles.add(new Node(x, y - nodeDistanceY));
					obstacles.add(new Node(x, y));
					obstacles.add(new Node(x, y + nodeDistanceY));
					obstacles.add(new Node(x + nodeDistanceX, -nodeDistanceY));
					obstacles.add(new Node(x + nodeDistanceX, y));
					obstacles.add(new Node(x + nodeDistanceX, y + nodeDistanceY));
				}
			}
		}

		Labyrinth.obstaclePoints.addAll(obstacles);
		AStarAlgorithm algo = new AStarAlgorithm();
		Node result = algo.calculateShortestPath(new Node(start), new Node(end));

		graphics.setColor(Color.RED);
		if (result != null) {
			while (result.getParent() != null) {
				graphics.drawLine(result.x, result.y, result.getParent().x, result.getParent().y);
				result = result.getParent();
			}
		}
		return maze;
	}

	// If the block	 contains a black pixel then the whole block is black
	private static Color getColorFromBlock(BufferedImage edgesImage, Point cursorSize, int x, int y) {
		for (int a = x; a < x + cursorSize.x; a++) {
			for (int b = y; b < y + cursorSize.y; b++) {
				if (new Color(edgesImage.getRGB(a, b)).equals(Color.WHITE)) {
					System.out.println("BLACK");
					return Color.BLACK;
				}
			}
		}
		System.out.println("WHITE");
		return Color.WHITE;
	}
}
