package ideaman924.percentagecalculator.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

import androidx.appcompat.app.AppCompatActivity;
import ideaman924.percentagecalculator.R;
import ideaman924.percentagecalculator.calculation.Calculate;
import ideaman924.percentagecalculator.calculation.Sanitation;

public class PercentActivity extends AppCompatActivity {

    EditText firstNum, secondNum;
    TextView result;
    Sanitation sanitation;
    Calculate calculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_percent);

        firstNum = findViewById(R.id.firstNumberPercentMode);
        secondNum = findViewById(R.id.secondNumberPercentMode);
        result = findViewById(R.id.resultPercentMode);

        firstNum.addTextChangedListener(numberWatcher);
        secondNum.addTextChangedListener(numberWatcher);

        sanitation = new Sanitation();
        calculate = new Calculate();
    }

    private TextWatcher numberWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            calculateNumberPercent(findViewById(android.R.id.content));
        }
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        @Override
        public void afterTextChanged(Editable s) {}
    };

    public void calculateNumberPercent(View view) {
        boolean isValidNumber = false;
        try {
            isValidNumber = sanitation.rawVerify(firstNum, secondNum);
        } catch (Exception e) {
            Toast.makeText(this, R.string.error_invalid_number, Toast.LENGTH_SHORT).show();
        }

        if (isValidNumber) {
            BigDecimal num1 = new BigDecimal(firstNum.getText().toString());
            BigDecimal num2 = new BigDecimal(secondNum.getText().toString());
            try {
                BigDecimal calculatedNumber = calculate.calculateNumber(num1, num2, 10);
                NumberFormat integerFormat = NumberFormat.getNumberInstance();
                integerFormat.setMaximumFractionDigits(0);
                integerFormat.setRoundingMode(RoundingMode.HALF_UP);
                result.setText(integerFormat.format(calculatedNumber));
            } catch (Exception e) {
                Toast.makeText(this, R.string.error_calculation_fail, Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }

    public void detailCalculateNumberPercent(View v) {
        int precision = 3;
        boolean isValidNumber = false;
        try {
            isValidNumber = sanitation.rawVerify(firstNum, secondNum);
        } catch (Exception e) {
            Toast.makeText(this, R.string.error_invalid_number, Toast.LENGTH_SHORT).show();
        }

        if (isValidNumber) {
            BigDecimal num1 = new BigDecimal(firstNum.getText().toString());
            BigDecimal num2 = new BigDecimal(secondNum.getText().toString());
            try {
                BigDecimal calculatedNumber = calculate.calculateNumber(num1, num2, precision + 7);
                NumberFormat decimalFormat = NumberFormat.getNumberInstance();
                decimalFormat.setMinimumFractionDigits(precision);
                result.setText(decimalFormat.format(calculatedNumber));
            } catch (Exception e) {
                Toast.makeText(this, R.string.error_calculation_fail, Toast.LENGTH_SHORT).show();
            }
        }
    }

}
