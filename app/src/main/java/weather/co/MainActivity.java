package weather.co;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import weather.co.app.WeatherApp;
import weather.co.detail.WeatherDetailsActivity;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.header)
    TextView headerView;

    @BindView(R.id.spinner)
    Spinner spinner;

    @BindView(R.id.units)
    TextView unitsView;

    @BindView(R.id.units_spinner)
    Spinner unitSpinner;

    @BindView(R.id.button)
    Button button;

    private String selectedCity = "Los Angeles";
    private String selectedUnit = "Kelvin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setUpCitySpinner();
        setUpUnitSpinner();

        getComponent().inject(this);
        button.setOnClickListener(view -> showForecastDetails());
    }

    //region private
    private void setUpCitySpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.city_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    selectedCity = (String) parent.getItemAtPosition(position);
                    button.setEnabled(true);
                } else {
                    button.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setUpUnitSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.units_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitSpinner.setAdapter(adapter);
        unitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedUnit = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private MainActivityComponent getComponent() {
        return DaggerMainActivityComponent.builder().applicationComponent(((WeatherApp) getApplication()).getComponent()).build();
    }

    private void showForecastDetails() {
        WeatherDetailsActivity.start(this, selectedCity, getWeatherUnits());
    }

    private String getWeatherUnits() {
        switch (selectedUnit) {
            case "Celsius":
                return "metric";
            case "Fahrenheit":
                return "imperial";
            default:
                return "kelvin";
        }
    }
    // endregion
}
