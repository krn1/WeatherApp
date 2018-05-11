package weather.co;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import javax.inject.Inject;

import dagger.Component;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    private ApplicationComponent component;

    @Inject
    WeatherApp weatherApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getComponent().inject(this);
        Timber.e("Start Action ");
        weatherApp.print();

    }

    private MainActivityComponent getComponent() {
        return DaggerMainActivityComponent.builder()
                .applicationComponent(((WeatherApp) getApplication()).getComponent())
                .build();
    }
}
