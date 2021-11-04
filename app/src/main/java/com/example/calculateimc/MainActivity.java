package com.example.calculateimc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.calculateimc.statics.StaticInfo;

public class MainActivity extends AppCompatActivity {

    private EditText peso;
    private EditText altura;
    private Button calcular;
    private Button limpar;
    private Button resultadoCor;
    private TextView resultadoImc;
    private TextView resultadoClassificacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
        handleEvents();
    }

    private void initComponents()
    {
        try {
            peso = findViewById(R.id.peso);
            altura = findViewById(R.id.altura);
            calcular = findViewById(R.id.calcBtn);
            limpar = findViewById(R.id.cleanBtn);
            resultadoCor = findViewById(R.id.btnResult);
            resultadoImc = findViewById(R.id.imc);
            resultadoClassificacao = findViewById(R.id.classificacao);
        }
        catch (Exception ex){
            System.out.println("initComponents(): " + ex.getMessage());
        }
    }

    private void handleEvents()
    {
        try {
            handleCalculateBtn();
            handleCleanBtn();
        }
        catch (Exception ex) {
            System.out.println("handleEvents(): " + ex.getMessage());
        }
    }

    private void handleCalculateBtn()
    {
        try {
            cleanFields();
            calcular.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!testEditTextIsValid(peso) || !testEditTextIsValid(altura))
                        Toast.makeText(MainActivity.this, "É necessário preencher todos os campos.", Toast.LENGTH_LONG).show();
                    else
                    {
                        closeInputKeyboard(view);
                        String weightInput = peso.getText().toString();
                        String heightInput = altura.getText().toString();
                        double weight = Double.parseDouble(weightInput);
                        double height = Double.parseDouble(heightInput);

                        double result = (weight / Math.pow(height, 2));
                        resultadoImc.setText(String.format("%.2f", result));
                        setLabelAndButtonColor(result);
                    }
                }
            });
        }
        catch (Exception ex){
            System.out.println("handleCalculateBtn(): " + ex.getMessage());
        }
    }

    private void closeInputKeyboard(View view)
    {
        try {
            InputMethodManager input = (InputMethodManager) getSystemService(MainActivity.INPUT_METHOD_SERVICE);
            input.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        catch(Exception ex){
            System.out.println("closeInputKeyboard(): " + ex.getMessage());
        }
    }

    private void handleCleanBtn()
    {
        try {
            limpar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cleanFields();
                    closeInputKeyboard(view);
                }
            });
        }
        catch (Exception ex){
            System.out.println("handleCleanBtn(): " + ex.getMessage());
        }
    }

    private void cleanFields()
    {
        try {
            peso.setText("");
            altura.setText("");
            resultadoCor.setBackgroundColor(getResources().getColor(R.color.white));
            resultadoImc.setText("");
            resultadoClassificacao.setText("");
        }
        catch (Exception ex){
            System.out.println("cleanFields(): " + ex.getMessage());
        }
    }

    private boolean testEditTextIsValid(EditText editText)
    {
        try {
            String text = editText.getText().toString();
            return !text.isEmpty();
        }
        catch (Exception ex){
            System.out.println("testEditTextDouble(): " + ex.getMessage());
            return false;
        }
    }

    private void setLabelAndButtonColor(double result)
    {
        try {
                if(result < 16.0)
                {
                    resultadoCor.setBackgroundColor(getResources().getColor(R.color.magrezaIII));
                    resultadoClassificacao.setText(StaticInfo.MagrezaIII);
                }
                else if(result >= 16.0 && result <= 16.9)
                {
                    resultadoCor.setBackgroundColor(getResources().getColor(R.color.magrezaII));
                    resultadoClassificacao.setText(StaticInfo.MagrezaII);
                }
                else if(result > 16.9 && result <= 18.4)
                {
                    resultadoCor.setBackgroundColor(getResources().getColor(R.color.magrezaI));
                    resultadoClassificacao.setText(StaticInfo.MagrezaI);
                }
                else if(result > 18.4 && result <= 24.9)
                {
                    resultadoCor.setBackgroundColor(getResources().getColor(R.color.eutrofia));
                    resultadoClassificacao.setText(StaticInfo.Eutrofia);
                }
                else if(result > 24.9 && result <= 29.9)
                {
                    resultadoCor.setBackgroundColor(getResources().getColor(R.color.preObesidade));
                    resultadoClassificacao.setText(StaticInfo.PreObesidade);
                }
                else if(result > 29.9 && result <= 34.9)
                {
                    resultadoCor.setBackgroundColor(getResources().getColor(R.color.moderada));
                    resultadoClassificacao.setText(StaticInfo.ObesidadeModerada);
                }
                else if(result > 34.9 && result <= 39.9)
                {
                    resultadoCor.setBackgroundColor(getResources().getColor(R.color.severa));
                    resultadoClassificacao.setText(StaticInfo.ObesidadeSevera);
                }
                else
                {
                    resultadoCor.setBackgroundColor(getResources().getColor(R.color.muitoSevera));
                    resultadoClassificacao.setText(StaticInfo.ObesidadeMuitoSevera);
                }
        }
        catch (Exception ex){
            System.out.println("setLabelAndButtonColor(): " + ex.getMessage());
        }
    }

}