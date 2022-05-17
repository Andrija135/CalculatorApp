package android.tvz.hr.kalkulatorpoljak;

import android.os.Bundle;
import android.tvz.hr.kalkulatorpoljak.databinding.ActivityMainBinding;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity {

    TextView calculationTV;
    TextView resultTV;

    String calculations = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        android.tvz.hr.kalkulatorpoljak.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        calculationTV = binding.calculationTV;
        resultTV = binding.resultTV;


        //Operations
        binding.addBtn.setOnClickListener(v -> setCalculations("+"));
        binding.subtractBtn.setOnClickListener(v -> setCalculations("-"));
        binding.multiplyBtn.setOnClickListener(v -> setCalculations("*"));
        binding.divideBtn.setOnClickListener(v -> setCalculations("/"));

        //Numbers
        binding.zeroBtn.setOnClickListener(v -> setCalculations("0"));
        binding.oneBtn.setOnClickListener(v -> setCalculations("1"));
        binding.twoBtn.setOnClickListener(v -> setCalculations("2"));
        binding.threeBtn.setOnClickListener(v -> setCalculations("3"));
        binding.fourBtn.setOnClickListener(v -> setCalculations("4"));
        binding.fiveBtn.setOnClickListener(v -> setCalculations("5"));
        binding.sixBtn.setOnClickListener(v -> setCalculations("6"));
        binding.sevenBtn.setOnClickListener(v -> setCalculations("7"));
        binding.eightBtn.setOnClickListener(v -> setCalculations("8"));
        binding.nineBtn.setOnClickListener(v -> setCalculations("9"));


        //Equals
        binding.equalsBtn.setOnClickListener(v -> {


            Double result = null;

            ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
            boolean calculationIsEmpty = calculations.isEmpty();

            if (!calculationIsEmpty) {
                try {
                    result = (double) engine.eval(calculations);
                } catch (ScriptException e) {
                    Toast.makeText(MainActivity.this, "Invalid Input", Toast.LENGTH_SHORT).show();
                }

                if (result != null) {
                    resultTV.setText(String.valueOf(result.doubleValue()));
                }
            }


        });

        //Clear
        binding.clearBtn.setOnClickListener(v -> {
            calculationTV.setText("");
            calculations = "";
            resultTV.setText("");
        });


    }

    private void setCalculations(String givenValue) {
        calculations = calculations + givenValue;
        calculationTV.setText(calculations);
    }


}