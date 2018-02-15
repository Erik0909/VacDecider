package erik.vacationdecider;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Arrays;

import cz.msebera.android.httpclient.Header;



public class MainActivity extends AppCompatActivity {
    //Variabler
    private String[] byer;
    private ArrayList<Cities> land = new ArrayList<Cities>();
    private Cities valgtLand;
    final String URL = "https://api.fixer.io/latest?base=NOK";
    double usd, sek, eur, TRY = 0;
    //Inserting objects and adding drawables
    private void populateObjects(CurrencyValueModel currValues) {
        //Getting currency values from CurrencyValueModels JSON
        usd = currValues.getUSD();
        sek = currValues.getSEK();
        eur = currValues.getEUR();
        TRY = currValues.getTRY();

        //Setting drawables
        Drawable Spania = getResources().getDrawable(R.drawable.spn);
        Drawable Sverige = getResources().getDrawable(R.drawable.svg);
        Drawable Tyrkia = getResources().getDrawable((R.drawable.trk));
        Drawable Tyskland = getResources().getDrawable((R.drawable.tsk));
        Drawable USA = getResources().getDrawable((R.drawable.usd));

        //Making city objects
        Cities sverigehotell = new Cities("Stockholm", "SvenskeHotel", Sverige, "SEK", sek, 400, 700);
        Cities spaniahotell = new Cities("Madrid", "SpaniaHotel", Spania, "Euro", eur, 300, 800);
        Cities tyrkiahotell = new Cities("Ankara", "TyrkiaHotel", Tyrkia, "Tyrkisk lira", TRY, 200, 900);
        Cities tysklandhotell = new Cities("Berlin", "TysklandHotel", Tyskland, "Euro", eur, 500, 1000);
        Cities USAhotell = new Cities("Washington D.C", "USAHotel", USA, "USD", usd, 300, 1200);

        //Adding to arraylist
        land.add(sverigehotell);
        land.add(tysklandhotell);
        land.add(USAhotell);

        land.add(spaniahotell);
        land.add(tyrkiahotell);

        //Getting views
        final TextView citiesTxt = (TextView)findViewById(R.id.cityTxt);
        final TextView hotelTxt = (TextView)findViewById(R.id.hotelTxt);
        final ImageView flagImg = (ImageView)findViewById(R.id.flagImg);
        Spinner s1 = (Spinner) findViewById(R.id.spinner);

        //Populating Spinner
        byer = getResources().getStringArray(R.array.items); //Adding string-array 'items' to byer
        //Adding byer to spinner
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
                //Index is value of selected item.
                //Land is arraylist with city objects
                valgtLand = land.get(index);
                citiesTxt.setText(valgtLand.getCity());
                hotelTxt.setText(valgtLand.getHotel());
                flagImg.setImageDrawable(valgtLand.getFlag());
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // https://api.fixer.io/latest?base=NOK

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    protected void onResume() {
        super.onResume();
        letsDoSomeNetworking();
    }



    //Trying to fetch JSON Currency values
    private void letsDoSomeNetworking() {
        // AsyncHttpClient belongs to the loopj dependency.
        AsyncHttpClient client = new AsyncHttpClient();
        // Making an HTTP GET request by providing a URL and the parameters.
        client.get(URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("JSON", "Success! JSON: " + response.toString());
               CurrencyValueModel currValues = CurrencyValueModel.fromJson(response);
               if(land.isEmpty()) {
                   Log.d("Currency", "If");

                   populateObjects(currValues);
               } else {
                   Log.d("Currency", "Else");
                   updateCurrencyValues(currValues);
               }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject response) {

                Log.e("JSON", "Fail " + e.toString());
                Toast.makeText(MainActivity.this, "Request Failed", Toast.LENGTH_SHORT).show();

                Log.d("JSON", "Status code " + statusCode);
                Log.d("JSON", "Here's what we got instead " + response.toString());

            }

        });
    }

    public void updateCurrencyValues(CurrencyValueModel currValues) {
        usd = currValues.getUSD();
        TRY = currValues.getTRY();
        eur = currValues.getEUR();
        sek = currValues.getSEK();

//        land.add(sverigehotell);
//        land.add(tysklandhotell);
//        land.add(USAhotell);
//
//        land.add(spaniahotell);
//        land.add(tyrkiahotell);

        land.get(0).setCurrencyValue(sek);
        Log.d("Currency", "Sverige: " + land.get(0).getCurrencyValue() + " " + land.get(0).getCity());
        land.get(1).setCurrencyValue(eur);
        Log.d("Currency", "Tysk: " + land.get(1).getCurrencyValue()+ " " + land.get(1).getCity());

        land.get(2).setCurrencyValue(usd);
        Log.d("Currency", "USA: " + land.get(2).getCurrencyValue() + " " + land.get(2).getCity());

        land.get(3).setCurrencyValue(eur);
        Log.d("Currency", "Spania: " + land.get(3).getCurrencyValue() + " " + land.get(3).getCity());

        land.get(4).setCurrencyValue(TRY);
        Log.d("Currency", "Tyrkia: " + land.get(4).getCurrencyValue() + " " + land.get(4).getCity());


    }


}
