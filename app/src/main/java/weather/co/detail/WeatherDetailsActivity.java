package weather.co.detail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import timber.log.Timber;
import weather.co.R;

public class WeatherDetailsActivity extends AppCompatActivity {

    private static final String KEY_CITY_ID = "key_account_id";
    private String city = "Los Angeles";

    public static void start(Activity context, String city) {
        Intent intent = new Intent(context, WeatherDetailsActivity.class);
        intent.putExtra(KEY_CITY_ID, city);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_details);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent.hasExtra(KEY_CITY_ID)) {
            city = intent.getStringExtra(KEY_CITY_ID);
        }

        getSupportActionBar().setTitle(city);
        Timber.e("City selected " + city);
        //getCurrentWeather();
        //  getForecastWeather();
    }
}
