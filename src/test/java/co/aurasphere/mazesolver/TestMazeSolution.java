package co.aurasphere.mazesolver;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;

import org.junit.Ignore;
import org.junit.Test;

public class TestMazeSolution {
	
	private final static File OUTPUT_DIRECTORY = 
			new File("D:\\Repositories\\maze-solver\\output");
	
	// NO solution?
	@Test
	@Ignore
	public void testOne() throws Exception {
		String inputFile = "1.bmp";
		Point start = new Point(209, 237);
		Point end = new Point(620, 620);
		doActualTest(inputFile, start, end);
	}
	
	// Type 13 Unsupported
	@Test
	@Ignore
	public void testTwo() throws Exception {
		String inputFile = "2.png";
		Point start = new Point(150, 100);
		Point end = new Point(500, 200);
		doActualTest(inputFile, start, end);
	}
	
	// OK ma ho dovuto convertirla a bitmap perchè png con rgba non è supportato
	@Test
	public void testThree() throws Exception {
		String inputFile = "3.bmp";
		Point start = new Point(21, 32);
		Point end = new Point(380, 280);
		doActualTest(inputFile, start, end);
	}
	
	// Unsupported type 6
	@Test
	@Ignore
	public void testFour() throws Exception {
		String inputFile = "4.png";
		Point start = new Point(150, 100);
		Point end = new Point(500, 200);
		doActualTest(inputFile, start, end);
	}
	
	// Unsupported type 6
	@Test
	@Ignore
	public void testFive() throws Exception {
		String inputFile = "5.png";
		Point start = new Point(150, 100);
		Point end = new Point(500, 200);
		doActualTest(inputFile, start, end);
	}
	
	@Test
	@Ignore
	public void testSix() throws Exception {
		String inputFile = "6.jpg";
		Point start = new Point(150, 100);
		Point end = new Point(500, 200);
		doActualTest(inputFile, start, end);
	}
	
	// Unsupported type 13
	@Test
	@Ignore
	public void testSeven() throws Exception {
		String inputFile = "7.png";
		Point start = new Point(150, 100);
		Point end = new Point(500, 200);
		doActualTest(inputFile, start, end);
	}

	// OK
	@Test
	@Ignore
	public void testEight() throws Exception {
		String inputFile = "8.bmp";
		Point start = new Point(150, 100);
		Point end = new Point(500, 200);
		doActualTest(inputFile, start, end);
	}
	
	// OK
	@Test
	@Ignore
	public void testNine() throws Exception {
		String inputFile = "9.bmp";
		Point start = new Point(41, 20);
		Point end = new Point(345, 273);
		doActualTest(inputFile, start, end);
	}
	
	private void doActualTest(String inputFileName, Point start, Point end) throws Exception {
		File inputFile = new File(getClass().getClassLoader().getResource(inputFileName).toURI());
		
		// load the file using Java's imageIO library
		BufferedImage image = javax.imageio.ImageIO.read(inputFile);
		
		Point cursor = new Point(1, 1);
		BufferedImage solution = MazeSolver.solveMaze(image, start, end, cursor);

		File outputFile = new File(OUTPUT_DIRECTORY, inputFileName);
		if (outputFile.exists()) {
			outputFile.delete();
		}
		
		javax.imageio.ImageIO.write(solution, "jpg", outputFile);
	}
}
