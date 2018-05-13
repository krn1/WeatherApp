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
import timber.log.Timber;
import weather.co.app.WeatherApp;
import weather.co.detail.WeatherDetailsActivity;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @BindView(R.id.header)
    TextView headerView;

    @BindView(R.id.spinner)
    Spinner spinner;

    @BindView(R.id.button)
    Button button;

    private String selectedCity = "Los Angeles";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setUpSpinner();

        getComponent().inject(this);
        button.setOnClickListener(view-> showForecastDetails());
    }

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
    public void onNothingSelected(AdapterView<?> parent) { }

    //region private
    private void setUpSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.city_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    private MainActivityComponent getComponent() {
        return DaggerMainActivityComponent.builder().applicationComponent(((WeatherApp) getApplication()).getComponent()).build();
    }

    private void showForecastDetails() {
        Timber.e("Clicked");
        WeatherDetailsActivity.start(this, selectedCity);
    }
    // endregion
}
