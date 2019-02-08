package ideaman924.percentagecalculator.calculation;

import android.widget.EditText;

/**
 * Created by ideam on 2018-01-19.
 */

public class Sanitation {

    /**
     * Verifies if the supplied EditTexts contain sane data for manipulation.
     * @param num1 First EditText designated for nominator.
     * @param num2 Second EditText designated for denominator.
     * @return Returns number sanity status.
     * @throws Exception If the numbers do not fit inside int or other parsing issues.
     */
    public boolean rawVerify(EditText num1, EditText num2) throws Exception {
        boolean isNum1Empty = num1.getText().toString().matches("");
        boolean isNum2Empty = num2.getText().toString().matches("");

        if(isNum1Empty || isNum2Empty)
            return false;
        else {
            try {
                return numberVerify(Integer.parseInt(num1.getText().toString()), Integer.parseInt(num2.getText().toString()));
            } catch (Exception e) {
                throw e;
            }
        }

    }

    private boolean numberVerify(int num1, int num2) {
        return ((num1 >= 0) && (num2 > 0));
    }

    public boolean isValidPercent(int num1, int num2) {
        return !(num1 <= 0) || !(num2 <= 0);
    }
}
