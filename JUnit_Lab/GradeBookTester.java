import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GradeBookTester {

	private GradeBook g1;
	private GradeBook g2;
	@BeforeEach
	void setUp() throws Exception
	{
		g1 = new GradeBook(5);
	    g2 = new GradeBook(5);
	    g1.addScore(50.0);
	    g1.addScore(75.0);
	    g2.addScore(30.0);
	    g2.addScore(10.0);
	    g2.addScore(40.0);
	    g2.addScore(20.0);
	}

	@AfterEach
	void tearDown() throws Exception
	{
		g1 = null;
		g2 = null;
	}

	@Test
	void testAddScore()
	{
		assertTrue((g1.toString()).equals("50.0 75.0"));
	    assertTrue((g2.toString()).equals("30.0 10.0 40.0 20.0"));
	    assertEquals(2, g1.getScoreSize(), 0.001);
	    assertEquals(4, g2.getScoreSize(), 0.001);
		
	}

	@Test
	void testSum()
	{
		// g1 = 50 + 75 = 125
		// g2 = 30 + 10 + 40 + 20 = 100
		// 0.0001 is the max difference in two values that we are comparing
	    assertEquals(125.0, g1.sum(), 0.0001);
	    assertEquals(100.0, g2.sum(), 0.0001);
	}

	@Test
	void testMinimum()
	{
		// g1 = 50
		// g2 = 10
	    assertEquals(50.0, g1.minimum(), 0.001);
	    assertEquals(10.0, g2.minimum(), 0.001);
	}

	@Test
	void testFinalScore()
	{
		// sum - minimum
		// g1 = 125 - 50 = 75
		// g2 = 100 - 10 = 90
	    assertEquals(75.0, g1.finalScore(), 0.001);
	    assertEquals(90.0, g2.finalScore(), 0.001);
	}

}
