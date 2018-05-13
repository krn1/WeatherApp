package weather.co.detail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.airbnb.epoxy.EpoxyRecyclerView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;
import weather.co.R;
import weather.co.app.WeatherApp;
import weather.co.detail.epoxy.WeatherDetailsController;
import weather.co.detail.epoxy.WeatherInfo;

public class WeatherDetailsActivity extends AppCompatActivity implements WeatherDetailContract.View {

    private static final String KEY_CITY_ID = "key_city_id";
    private static final String KEY_UNIT_ID = "key_unit_id";

    private String city = "Los Angeles"; //Default city
    private StringBuffer units;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.list)
    EpoxyRecyclerView list;

    @BindView(R.id.empty_view)
    TextView emptyView;

    @Inject
    WeatherDetailsPresenter presenter;
    private WeatherDetailsController listController;

    public static void start(Activity context, String city, String unit) {
        Intent intent = new Intent(context, WeatherDetailsActivity.class);
        intent.putExtra(KEY_CITY_ID, city);
        intent.putExtra(KEY_UNIT_ID, unit);
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
        if (intent.hasExtra(KEY_UNIT_ID)) {
            units = new StringBuffer(intent.getStringExtra(KEY_UNIT_ID));
        }
        setupToolbar();
        setupEpoxy();
        Timber.e("Unit selected " + units);
        getComponent().inject(this);
        presenter.start();
    }

    @Override
    public void showError(String message) {
        emptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showHeader(WeatherInfo weatherInfo) {
        emptyView.setVisibility(View.GONE);
        listController.setHeader(weatherInfo);
    }

    @Override
    public void showWeatherForecast(List<WeatherInfo> weatherInfoList) {
        listController.setContents(weatherInfoList);
    }

    // region private
    private void setupToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }
        toolbar.setNavigationIcon(getDrawable(R.drawable.abc_ic_ab_back_material));
        toolbar.setNavigationOnClickListener(toolbar -> onBackPressed());
        toolbar.setTitle(city);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
    }

    private WeatherDetailsComponent getComponent() {
        WeatherApp application = (WeatherApp) getApplicationContext();
        return DaggerWeatherDetailsComponent.builder()
                .applicationComponent(application.getComponent())
                .weatherDetailsModule(new WeatherDetailsModule(this, city, units))
                .build();
    }

    private void setupEpoxy() {
        listController = new WeatherDetailsController();
        list.setController(listController);
    }
    //endregion
}
