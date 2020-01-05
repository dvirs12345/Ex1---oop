package Ex1Testing;

import java.io.IOException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Functions_GUITest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testInitFromFile()  throws IOException {
		Functions_GUI fg0 = new Functions_GUI();
		fg0.initFromFile("function_file.txt");
		fg0.drawFunctions(1200, 600, new Range(-9, 11), new Range(-3, 7), 800);
	}

	@Test
	void testSaveToFile() throws IOException {
		Functions_GUI fg = new Functions_GUI();
		fg.add( new ComplexFunction(new Sinus(new Monom(1, 1))));
		fg.add( new ComplexFunction("div(plus(-x^3+2.4x^2+5,x^5 -1.2999999999999998x),50)"));
		fg.add( new Polynom("X^3"));
		fg.add( new Sinus(new Polynom("5X+1")));
		fg.drawFunctions("GUI_params.txt");
		String file = "out.txt";
		try {
			fg.saveToFile(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testDrawFunctionsIntIntRangeRangeInt()  throws IOException {
		Functions_GUI fg = new Functions_GUI();
		fg.add( new Polynom("X^3"));
		fg.add( new Sinus(new Polynom("5X+1")));
		fg.drawFunctions(1200, 600, new Range(-9, 11), new Range(-3, 7), 800);
	}

	@Test
	void testDrawFunctionsString()  throws IOException {
		Functions_GUI fg0 = new Functions_GUI();
		fg0.initFromFile("function_file.txt");
		Functions_GUI fg = new Functions_GUI();
		fg.add( new Polynom("X^3"));
		fg.add( new Sinus(new Polynom("5X+1")));
		fg.drawFunctions("GUI_params.txt");
	}

}
