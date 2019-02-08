package ideaman924.percentagecalculator.calculation;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Calculate {

    /**
     * Returns a percentage in decimal form.
     * @param bd1 Nominator of the fraction
     * @param bd2 Denominator of the fraction
     * @param precision Set precision of calculation. See MathContext documentation.
     * @return A decimal form percentage (12.5% is returned as 0.125)
     * @throws Exception If the calculation fails this method throws an exception.
     */
    public BigDecimal calculatePercentage(BigDecimal bd1, BigDecimal bd2, int precision) throws Exception {
        try {
            BigDecimal calculatedNumber = bd1
                    .divide(bd2, new MathContext(precision, RoundingMode.HALF_UP))
                    .setScale(precision, RoundingMode.CEILING);
            return calculatedNumber;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Returns number calculated by percent.
     * @param bd1 Entire workload
     * @param bd2 Percent (in percentage)
     * @param precision Set precision of calculation. See MathContext documentation.
     * @return A BigDecimal of the result.
     * @throws Exception If the calculation fails the method throws an exception.
     */
    public BigDecimal calculateNumber(BigDecimal bd1, BigDecimal bd2, int precision) throws Exception {
        try {
            BigDecimal calculatedNumber = bd1
                    .multiply(bd2)
                    .divide(new BigDecimal(100), new MathContext(precision, RoundingMode.HALF_UP))
                    .setScale(precision, RoundingMode.CEILING);
            return calculatedNumber;
        } catch (Exception e) {
            throw e;
        }
    }
}
