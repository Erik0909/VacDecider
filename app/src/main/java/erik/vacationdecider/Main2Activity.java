package erik.vacationdecider;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity  {
    //Variables
    NumberPicker noPicker = null;
    DecimalFormat format = new DecimalFormat("##.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final TextView txtCity = (TextView)findViewById(R.id.txtCity);
        final TextView txtHotel = (TextView)findViewById(R.id.txtHotel);
        final TextView txtCurrency = (TextView)findViewById(R.id.txtCurrency);
        final TextView txtCurrencyValue = (TextView)findViewById(R.id.txtCurrencyValue);
        final TextView txtPris = (TextView)findViewById(R.id.txtPris);

        //Intent
        Intent i = getIntent();
        final Cities land = (Cities) getIntent().getSerializableExtra(("List"));

        //Number picker 1
        noPicker = (NumberPicker)findViewById(R.id.numberPicker);
        noPicker.setMaxValue(20);
        noPicker.setMinValue(1);
        noPicker.setWrapSelectorWheel(false);
        String enDag = Double.toString(land.getAirplaneCost() + land.getAvarageExpenditure());
        txtPris.setText(enDag);
        noPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i2) {
                Log.d("Tes2t: ", "Test2: " + i2);
                double finalValue = (i2 * land.getAvarageExpenditure()) + land.getAirplaneCost();
                String strDouble = Double.toString(finalValue);
                txtPris.setText(strDouble);

            }
        });





        //Updates strings
        txtCity.setText(land.getCity());
        txtHotel.setText(land.getHotel());
        txtCurrency.setText(land.getCurrency());
        double currValue = land.getCurrencyValue();
        double currConv = (100 * currValue);
        txtCurrencyValue.setText("100 NOK to " + land.getCurrency() + " = " + format.format(currConv));



    }

}
