package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

        String filePath = "src/main/java/org/example/prospects.txt";

        printProspects(filePath);
    }

    public static void printProspects(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int i = 0;
            br.readLine();
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty() && !line.equals(".")) {
                    String[] result = removeCommasInQuotes(line).split(",");
                    double monthly_payment = mortgageEquation(Double.parseDouble(result[1]), Double.parseDouble(result[2]), Integer.parseInt(result[3]));
                    String monthly_payment_string = String.valueOf(monthly_payment);
                    System.out.printf("Prospect %s: %s wants to borrow %s € for a period of %s years and pay %s € each month\n", i+=1, result[0], result[1], result[3], monthly_payment_string );
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String removeCommasInQuotes(String line) {
        Pattern pattern = Pattern.compile("\"([^\"]*)\"");
        Matcher matcher = pattern.matcher(line);

        while (matcher.find()) {
            String quotedText = matcher.group(1);
            String textWithoutCommas = quotedText.replace(",", " ");
            line = line.replace("\"" + quotedText + "\"", textWithoutCommas);
        }

        return line;
    }

    public static double mortgageEquation(double totalLoan, double yearly_interest, int years) {
        double b = yearly_interest / 100 / 12;
        double U = totalLoan;
        int p = years * 12;
        double exponent_base = 1 + b;
        double exponent_result = exponent_base;
        for (int i = 1; i < p; i++) {
            exponent_result *= exponent_base;
        }
        return U * ((b * exponent_result) / (exponent_result - 1));
    }
}