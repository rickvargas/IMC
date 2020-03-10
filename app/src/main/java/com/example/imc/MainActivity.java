package com.example.imc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText etPeso, etAltura;
    TextView tvIMC;
    ImageView ivIMC;
    SeekBar sbIMC;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    
        etPeso = findViewById(R.id.editTextPeso);
        etAltura = findViewById(R.id.editTextAltura);
        tvIMC = findViewById(R.id.textViewIMC);
        ivIMC = findViewById(R.id.imageViewIMC);
        sbIMC = findViewById(R.id.seekBarIMC);
    }
    
    public void imc(View view) {
        try {
            float peso = Float.parseFloat(etPeso.getText().toString());
            float altura = Float.parseFloat(etAltura.getText().toString());
            float imc = CalcularIMC(peso, altura);

            String textIMC;
            if (imc == -1) {
                textIMC = "Falha ao gerar IMC. Insira apenas numeros superiores a 0.";
                sbIMC.setProgress(0);
            } else {
                textIMC = String.format("%.2f", imc) + "";
                sbIMC.setProgress((int) imc);
            }
            tvIMC.setText(textIMC);
            ivIMC.setImageResource(ShowImageIMC(imc));
  
        } catch (Exception e){
            tvIMC.setText("Falha ao gerar IMC. Insira apenas numeros.");
            ivIMC.setImageResource(ShowImageIMC(-1));
        }
    }
    
    public float CalcularIMC(float peso, float altura){
        float imc = peso / (altura * altura);
        if (peso <= 0 || altura <= 0){
            imc = -1;
        }
        return imc;
    }
    
    public int ShowImageIMC(float imc){
        int idImage;
        if (imc == -1){
            idImage = R.drawable.invalid;
        } else if (imc < 10.5) {
            idImage = R.drawable.abaixopeso;
        } else if (imc < 25){
            idImage = R.drawable.normal;
        } else if (imc < 30){
            idImage = R.drawable.sobrepeso;
        } else if (imc < 35){
            idImage = R.drawable.obesidade1;
        } else if (imc < 40){
            idImage = R.drawable.obesidade2;
        } else {
            idImage = R.drawable.obesidade3;
        }
        return idImage;
    }
}
