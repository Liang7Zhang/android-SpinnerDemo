package androidemo.liang.com.android_spinnerdemo;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;


public class SpinnerDemo extends Activity implements AdapterView.OnItemSelectedListener {

    private static String[] items = {"Washington", "Oregon", "California",
            "Nevada", "Idaho", "Montana", "Utah", "Arizona", "New Mexico", "Texas",
            "North Dakota", "South Dakota", "Nebraska", "kansas", "Oklahoma", "Minnesota",
            "Lowa", "Missouri", "Arkansas", "Louisiana", "Mississippi", "Tennessee",
            "Indiana", "Wisconsin", "Michigan", "Kentucky", "Alabama", "Georgia",
            "South Carolina", "Florida", "North Carolina", "Virginia", "Ohio",
            "West Virginia", "New York"};
    private TextView selection;
    private SharedPreferences mSavedCountryName;
//    private static String[] sortedCountryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_demo);
        selection = (TextView)findViewById(R.id.selection);

        // Sort country list
        Arrays.sort(items);

        Spinner spin = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, items);
        // Tell spinner how does drop down view look like
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(arrayAdapter);
        spin.setOnItemSelectedListener(this);

        // Keep your previously selection
        // HOWTO:
        // 1) Save selection of country
        // 2) Restore it once launch it again
        mSavedCountryName = getSharedPreferences("country_name", MODE_PRIVATE);
        // check if we have valid history
        String name = mSavedCountryName.getString("country_name", String.valueOf(""));
        if (!name.equals(String.valueOf(""))){
            // yes we have historical record
            // spin set default option
            // 'Florida' to '19'
            ArrayList<String> list = new ArrayList<>(Arrays.asList(items));
            spin.setSelection(list.lastIndexOf(name));

            // set spin with specified selection with animation
//            spin.setSelection(position, true);
        }


        // Keep the list of country level of USA to local storage
        // HOWTO:

        // Keep the list of country level of USA to remote storage
        // HOWTO:
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_spinner_demo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // 1) Save selection of country
//        sharedPreference set "savedCountryName" items[position]
        String country = items[position];
        SharedPreferences.Editor editor = mSavedCountryName.edit();
        editor.putString("country_name", country);
        editor.commit();
        selection.setText(country);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
