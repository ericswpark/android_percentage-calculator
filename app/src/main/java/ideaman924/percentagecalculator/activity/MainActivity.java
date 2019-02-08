package ideaman924.percentagecalculator.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.text.NumberFormat;

import androidx.appcompat.app.AppCompatActivity;
import ideaman924.percentagecalculator.R;
import ideaman924.percentagecalculator.calculation.Calculate;
import ideaman924.percentagecalculator.calculation.Sanitation;

public class MainActivity extends AppCompatActivity {

    EditText firstNum,secondNum;
    TextView result;
    Sanitation sanitation;
    Calculate calculate;

    private TextWatcher numberWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            calculatePercentage(findViewById(android.R.id.content));
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstNum = findViewById(R.id.firstNumber);
        secondNum = findViewById(R.id.secondNumber);
        result = findViewById(R.id.result);

        firstNum.addTextChangedListener(numberWatcher);
        secondNum.addTextChangedListener(numberWatcher);

        sanitation = new Sanitation();
        calculate = new Calculate();
    }


    public void calculatePercentage(View view) {
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
                BigDecimal decimalPercent = calculate.calculatePercentage(num1, num2,4);
                NumberFormat percentFormat = NumberFormat.getPercentInstance();
                result.setText(percentFormat.format(decimalPercent));
            } catch (Exception e) {
                Toast.makeText(this, R.string.error_calculation_fail, Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }

    public void detailCalculatePercentage(View view) {
        int precision = 3;
        boolean isValidNumber = false;
        try {
            isValidNumber = sanitation.rawVerify(firstNum, secondNum);
        } catch (Exception e) {
            Toast.makeText(this, R.string.error_invalid_number, Toast.LENGTH_SHORT).show();
        }

        if(isValidNumber) {
            BigDecimal num1 = new BigDecimal(firstNum.getText().toString());
            BigDecimal num2 = new BigDecimal(secondNum.getText().toString());
            try {
                BigDecimal calculatedNumber = calculate.calculatePercentage(num1, num2, precision + 3);
                NumberFormat percentFormat = NumberFormat.getPercentInstance();
                percentFormat.setMinimumFractionDigits(precision);
                result.setText(percentFormat.format(calculatedNumber));
            } catch (Exception e) {
                Toast.makeText(this, R.string.error_calculation_fail, Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }

    public void launchPercentCalculateMode(View view) {
        Intent intent = new Intent(getApplicationContext(), PercentActivity.class);
        startActivity(intent);
    }

}
