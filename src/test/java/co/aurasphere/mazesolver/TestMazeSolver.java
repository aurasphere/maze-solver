package co.aurasphere.mazesolver;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

public class TestMazeSolver {

	private File outputFolder = new File("output");
	
	// Creates the output directory if it doesn't exist
	@Before
	public void setup() {
		if (!outputFolder.exists()) {
			outputFolder.mkdir();
		}
	}

	@Test
	public void testOne() throws Exception {
		String inputFile = "1.png";
		Point start = new Point(0, 0);
		Point end = new Point(40, 39);
		doActualTest(inputFile, start, end);
	}

	@Test
	public void testTwo() throws Exception {
		String inputFile = "2.png";
		Point start = new Point(0, 0);
		Point end = new Point(200, 199);
		doActualTest(inputFile, start, end);
	}

	@Test
	public void testThree() throws Exception {
		String inputFile = "3.png";
		Point start = new Point(0, 0);
		Point end = new Point(100, 99);
		doActualTest(inputFile, start, end);
	}

	@Test
	public void testFour() throws Exception {
		String inputFile = "4.png";
		Point start = new Point(0, 0);
		Point end = new Point(200, 199);
		doActualTest(inputFile, start, end);
	}

	@Test
	public void testFive() throws Exception {
		String inputFile = "5.png";
		Point start = new Point(0, 0);
		Point end = new Point(20, 19);
		doActualTest(inputFile, start, end);
	}

	private void doActualTest(String inputFileName, Point start, Point end) throws Exception {
		File inputFile = new File(getClass().getClassLoader().getResource(inputFileName).toURI());
		
		// load the file using Java's imageIO library
		BufferedImage image = javax.imageio.ImageIO.read(inputFile);

		// Solves the maze
		BufferedImage solution = MazeSolver.solveMaze(image, start, end);

		// Saves the solution
		File outputFile = new File(outputFolder, inputFileName);
		if (outputFile.exists()) {
			outputFile.delete();
		}
		javax.imageio.ImageIO.write(solution, getFileExtension(inputFileName), outputFile);
		System.out.println("Succesfully saved solution into: " + outputFile.getAbsolutePath());
	}
	
	// Returns the extension of a file
	public String getFileExtension(String filename) {
	    return Optional.ofNullable(filename)
	      .filter(f -> f.contains("."))
	      .map(f -> f.substring(filename.lastIndexOf(".") + 1)).get();
	}
}