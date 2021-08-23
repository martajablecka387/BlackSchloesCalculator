package calculator;

import org.apache.commons.math3.distribution.NormalDistribution;

import java.util.InputMismatchException;

public class BlackSchloesUtil {


    private BlackSchloesUtil() {
        throw new UnsupportedOperationException();
    }

    /**
     * Convert value in percentage format into expected format
     *
     * @param value
     * @return converted value
     */
    public static double calculateValueInPercentage(double value) {
        return value * 0.01;
    }

    /**
     * Calculate price of put option
     *
     * @param spotPrice
     * @param strikePrice
     * @param time
     * @param sigma
     * @param rate
     * @return price of put option
     */
    public static double calculatePut(double spotPrice, double strikePrice, double time, double sigma, double rate) {

        rate = calculateValueInPercentage(rate);
        sigma = calculateValueInPercentage(sigma);
        double[] d = d1d2(spotPrice, strikePrice, time, sigma, rate);

        return strikePrice * Math.exp(-rate * time) * cumulativeDistributionFunction(-d[1]) - spotPrice * cumulativeDistributionFunction(-d[0]);
    }

    /**
     * Calculate price of call option
     *
     * @param spotPrice
     * @param strikePrice
     * @param time
     * @param sigma
     * @param rate
     * @return price of call option
     */
    public static double calculateCall(double spotPrice, double strikePrice, double time, double sigma, double rate) {

        rate = calculateValueInPercentage(rate);
        sigma = calculateValueInPercentage(sigma);
        double[] d = d1d2(spotPrice, strikePrice, time, sigma, rate);

        return (spotPrice * cumulativeDistributionFunction(d[0])) - (strikePrice * Math.exp(-rate * time) * cumulativeDistributionFunction(d[1]));
    }

    /**
     * Calculate input for Cumulative Distribution Function
     *
     * @param spotPrice
     * @param strikePrice
     * @param time
     * @param sigma
     * @param rate
     * @return input for Cumulative Distribution Function
     */
    public static double[] d1d2(double spotPrice, double strikePrice, double time, double sigma, double rate) {

        double spst = strikePrice != 0 ? spotPrice / strikePrice : 0;
        double sigt = sigma * Math.sqrt(time);

        if (spst == 0 || sigt == 0) {
            throw new InputMismatchException("Spot Price, Strike Price, Standard Deviation and Time to Expiration cannot be 0.");
        }

        double d1 = (Math.log(spst) + ((rate + Math.pow(sigma, 2) / 2) * time)) / sigt;
        double d2 = d1 - sigma * Math.sqrt(time);

        return new double[] {d1, d2};
    }

    /**
     * Return value from Cumulative Distribution Function
     *
     * @param d
     * @return value from Cumulative Distribution Function
     */
    public static double cumulativeDistributionFunction(double d) {
        NormalDistribution normal = new NormalDistribution();
        return normal.cumulativeProbability(d);
    }

}
