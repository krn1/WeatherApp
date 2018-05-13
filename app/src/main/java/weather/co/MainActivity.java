package weather.co;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;
import timber.log.Timber;
import weather.co.app.ApplicationComponent;
import weather.co.app.WeatherApp;
import weather.co.detail.WeatherDetailsActivity;
import weather.repository.network.RestApi;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ApplicationComponent component;

    @BindView(R.id.header)
    TextView headerView;

    @BindView(R.id.spinner)
    Spinner spinner;

    @Inject
    WeatherApp weatherApp;

    @Inject
    RestApi apiService;

    CompositeDisposable disposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setUpSpinner();

        getComponent().inject(this);
        Timber.e("Start Action ");
        //getCurrentWeather();
      //  getForecastWeather();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if (position > 0) {
            String selectedCity = (String) parent.getItemAtPosition(position);
            WeatherDetailsActivity.start(this, selectedCity);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

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
    // endregion
}
