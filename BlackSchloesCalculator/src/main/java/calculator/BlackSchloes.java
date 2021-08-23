package calculator;

import java.util.InputMismatchException;
import java.util.Scanner;

import static calculator.BlackSchloesUtil.calculateCall;
import static calculator.BlackSchloesUtil.calculatePut;
import static java.lang.System.in;
import static java.lang.System.out;


/**
 * Simple Black-Scholes Model ( European Options )
 * Based on:
 * http://www.maxi-pedia.com/black+scholes+model
 */
public class BlackSchloes {

    /**
     * Calculate call/put option price given an input
     *
     * @param args
     */
    public static void main(String[] args) {
        char optionType;

        double spotPrice;
        double strikePrice;
        double timeInYears;
        double sigma;
        double rate;

        Scanner input = new Scanner(in);

        try {
            out.println("Please enter type of the option ( Call: 'c', Put: 'p') ");
            optionType = input.next().charAt(0);

            out.println("Enter the values one by one: ");

            out.println("Spot Price:");
            spotPrice = input.nextDouble();
            out.println("Strike Price:");
            strikePrice = input.nextDouble();
            out.println("Time to Expiration [Years]:");
            timeInYears = input.nextDouble();
            out.println("Standard Deviation [% / Year]:");
            sigma = input.nextDouble();
            out.println("Risk-Free Interest Rate [% / Year]:");
            rate = input.nextDouble();

        } catch (InputMismatchException e) {
            throw new InputMismatchException("Invalid input. Please try one more time.");
        }

        input.close();


        switch (optionType) {
        case 'c' :
            out.printf("Call Price: %.2f", calculateCall(spotPrice, strikePrice, timeInYears, sigma, rate));
            break;

        case 'p' :
            out.printf("Put Price: %.2f", calculatePut(spotPrice, strikePrice, timeInYears, sigma, rate));
            break;

        default:
            out.printf("%c is an invalid option type, please try again.", optionType);
        }

    }

}
