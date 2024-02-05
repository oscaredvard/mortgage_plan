import org.example.Main;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    @Test
    public void testPrintProspects() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String filePath = "prospects.txt";

        Main.printProspects(filePath);

        String expectedOutput = "Prospect 1: Juha wants to borrow 1000 € for a period of 2 years and pay 43.87138973406859 € each month\n" +
                "Prospect 2: Karvinen wants to borrow 4356 € for a period of 6 years and pay 62.86631476623255 € each month\n" +
                "Prospect 3: Claes Månsson wants to borrow 1300.55 € for a period of 2 years and pay 59.21856882868132 € each month\n" +
                "Prospect 4: Clarencé Andersson wants to borrow 2000 € for a period of 4 years and pay 46.970058095872126 € each month";
        assertEquals(expectedOutput, outContent.toString().trim());

        System.setOut(System.out);
    }

    @Test
    public void testRemoveCommasInQuotes() {
        String input = "\"Oscar,Eriksson\",30,5,200000";
        String expectedOutput = "Oscar Eriksson,30,5,200000";

        String actualOutput = Main.removeCommasInQuotes(input);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testMortgageEquation() {
        double totalLoan = 200000;
        double yearlyInterest = 4.5;
        int years = 30;

        double expectedMonthlyPayment = 1013.37;

        double actualMonthlyPayment = Main.mortgageEquation(totalLoan, yearlyInterest, years);

        assertEquals(expectedMonthlyPayment, actualMonthlyPayment, 0.01);
    }
}

