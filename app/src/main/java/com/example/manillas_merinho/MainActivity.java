package com.example.manillas_merinho;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText cantidad;
    private TextView valor;
    private double trm = 3200.00;
    private Spinner comboMaterial, comboTipo, comboDije, comboMoneda;
    private ArrayAdapter<String> adapterMaterial, adapterTipo,adapterDije, adapterMoneda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cantidad = findViewById(R.id.txtCantidad);
        valor = findViewById(R.id.lblValor);
        comboMaterial = findViewById(R.id.cmbMaterial);
        comboTipo = findViewById(R.id.cmbTipo);
        comboDije = findViewById(R.id.cmbDije);
        comboMoneda = findViewById(R.id.cmbMoneda);

        String[] material = getResources().getStringArray(R.array.material);
        String[] tipo = getResources().getStringArray(R.array.tipo);
        String[] dije = getResources().getStringArray(R.array.dije);
        String[] moneda = getResources().getStringArray(R.array.moneda);

        adapterMaterial = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, material);
        comboMaterial.setAdapter(adapterMaterial);

        adapterTipo = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tipo);
        comboTipo.setAdapter(adapterTipo);

        adapterDije = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dije);
        comboDije.setAdapter(adapterDije);

        adapterMoneda = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, moneda);
        comboMoneda.setAdapter(adapterMoneda);
    }

    public void valorApagar(View v) {
        double cant=0, valorUSD = 0.00, total = 0.00;
        int intComboMaterial, intComboDije, intComboTipo, intComboMoneda=20;

        if (validar()) {
            intComboMaterial = comboMaterial.getSelectedItemPosition();
            intComboDije = comboDije.getSelectedItemPosition();
            intComboTipo = comboTipo.getSelectedItemPosition();
            intComboMoneda = comboMoneda.getSelectedItemPosition();

            cant = Double.parseDouble(cantidad.getText().toString());

            //Cuero
            if(intComboMaterial == 0) {
                //Cuero - Ancla
                if(intComboDije == 0) {
                    //Cuero - Ancla - Oro/Oro rosado
                    if (intComboTipo == 0 || intComboTipo == 1) {
                        valorUSD = 120;
                    }
                    //Cuero - Ancla - Plata
                    if (intComboTipo == 2) {
                        valorUSD = 100;
                    }
                    //Cuero - Ancla - Niquel
                    if (intComboTipo == 3) {
                        valorUSD = 90;
                    }
                }else{ //Cuero - Martillo
                    //Cuero - Martillo - Oro/Oro rosado
                    if (intComboTipo == 0 || intComboTipo == 1) {
                        valorUSD = 100;
                    }
                    //Cuero - Martillo - Plata
                    if (intComboTipo == 2) {
                        valorUSD = 80;
                    }
                    //Cuero - Martillo - Niquel
                    if (intComboTipo == 3) {
                        valorUSD = 70;
                    }
                }
            } else { //Cuerda
                //Cuerda - Ancla
                if (intComboDije == 0) {
                    //Cuerda - Ancla - Oro/Oro rosado
                    if (intComboTipo == 0 || intComboTipo == 1) {
                        valorUSD = 110;
                    }
                    //Cuerda - Ancla - Plata
                    if (intComboTipo == 2) {
                        valorUSD = 90;
                    }
                    //Cuerda - Ancla - Niquel
                    if (intComboTipo == 3) {
                        valorUSD = 80;
                    }
                } else { //Cuerda - Martillo
                    //Cuerda - Martillo - Oro/Oro rosado
                    if (intComboTipo == 0 || intComboTipo == 1) {
                        valorUSD = 90;
                    }
                    //Cuerda - Martillo - Plata
                    if (intComboTipo == 2) {
                        valorUSD = 70;
                    }
                    //Cuerda - Martillo - Niquel
                    if (intComboTipo == 3) {
                        valorUSD = 50;
                    }
                }
            }
        }
        //COP
        if(intComboMoneda == 0){
            total = (cant*valorUSD)*trm;
        }else{
            total = cant*valorUSD;
        }
        valor.setText("$"+String.format("%.2f", total)+" "+comboMoneda.getSelectedItem().toString());
    }

    public boolean validar() {
        if (cantidad.getText().toString().isEmpty()) {
            cantidad.setError(getString(R.string.cantidadVacio));
            cantidad.requestFocus();
            return false;
        }

        double cant = Double.parseDouble(cantidad.getText().toString());
        if (cant == 0) {
            cantidad.setError(getString(R.string.cantidadMayorCero));
            cantidad.requestFocus();
            return false;
        }

        return true;
    }

    public void borrar(View v){
        cantidad.setText("");
        valor.setText("$0.00");
        comboMaterial.setSelection(0);
        comboDije.setSelection(0);
        comboTipo.setSelection(0);
        comboMoneda.setSelection(0);
    }

}