package erik.vacationdecider;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

import static android.provider.AlarmClock.EXTRA_MESSAGE;


public class MainActivity extends AppCompatActivity {
    //Variabler
    private String[] byer;
    private ArrayList<Cities> land = new ArrayList<Cities>();
    private Cities valgtLand;

    //Inserting objects and adding drawables
    public void populateObjects() {
        Drawable Spania = getResources().getDrawable(R.drawable.spn);
        Drawable Sverige = getResources().getDrawable(R.drawable.svg);
        Drawable Tyrkia = getResources().getDrawable((R.drawable.trk));
        Drawable Tyskland = getResources().getDrawable((R.drawable.tsk));
        Drawable USA = getResources().getDrawable((R.drawable.usd));
        Cities sverigehotell = new Cities("Stockholm", "SvenskeHotel", Sverige, "SEK", 1.02, 400, 700);
        Cities spaniahotell = new Cities("Madrid", "SpaniaHotel", Spania, "Euro", 0.1, 300, 800);
        Cities tyrkiahotell = new Cities("Ankara", "TyrkiaHotel", Tyrkia, "Tyrkisk lira", 0.48, 200, 900);
        Cities tysklandhotell = new Cities("Berlin", "TysklandHotel", Tyskland, "Euro", 0.1, 500, 1000);
        Cities USAhotell = new Cities("Washington D.C", "USAHotel", USA, "USD", 0.13, 300, 1200);

        land.add(sverigehotell);
        land.add(tysklandhotell);
        land.add(USAhotell);

        land.add(spaniahotell);
        land.add(tyrkiahotell);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        populateObjects();

        //Getting views
        final TextView citiesTxt = (TextView)findViewById(R.id.cityTxt);
        final TextView hotelTxt = (TextView)findViewById(R.id.hotelTxt);
        final ImageView flagImg = (ImageView)findViewById(R.id.flagImg);


        //Populating Spinner
        byer = getResources().getStringArray(R.array.items);
        Spinner s1 = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, byer);

        //Spinner select
        s1.setAdapter(adapter);
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                int index = arg0.getSelectedItemPosition();
                Toast.makeText(getBaseContext(),
                        "You have selected item : " + byer[index],
                        Toast.LENGTH_SHORT).show();

                //Getting countries and changing textviews & imageview based on spinner
                valgtLand = land.get(index);
                citiesTxt.setText(valgtLand.getCity());
                hotelTxt.setText(valgtLand.getHotel());
                flagImg.setImageDrawable(valgtLand.getFlag());
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        //Button onClick method (Redirects to new Activity with the selected country object)
        Button yourButton = (Button) findViewById(R.id.btnCalc);
        yourButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(MainActivity.this, Main2Activity.class );
                i.putExtra("List", valgtLand);
                startActivity(i);

            }
        });
    }

    }
