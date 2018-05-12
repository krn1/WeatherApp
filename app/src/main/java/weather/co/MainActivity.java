package weather.co;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import timber.log.Timber;
import weather.co.app.ApplicationComponent;
import weather.co.app.WeatherApp;
import weather.repository.model.User;
import weather.repository.model.WeatherData;
import weather.repository.network.RestApi;

import static weather.repository.network.NetworkConstants.API_KEY;

public class MainActivity extends AppCompatActivity {

    private ApplicationComponent component;

    @Inject
    WeatherApp weatherApp;

    @Inject
    RestApi apiService;

    CompositeDisposable disposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getComponent().inject(this);
        Timber.e("Start Action ");
        weatherApp.print();
        getCurrentWeather();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }

    private void restRx() {
        disposable.add(apiService.getUsers("reddit")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<User>() {
                    @Override
                    public void onNext(User user) {
                        Gson gson = new GsonBuilder().setPrettyPrinting().create();
                        String jsonOutput = gson.toJson(user);
                        Timber.e("Print pretty Rx :\n" + jsonOutput);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Timber.d(throwable);
                        handleError(throwable);
                    }

                    @Override
                    public void onComplete() {
                    }
                }));
    }

    private void handleError(Throwable throwable) {
        Timber.e("Error" + throwable.getLocalizedMessage());
    }


    private void getCurrentWeather() {
        disposable.add(apiService.getCurrentWeather("London,uk", "metric", API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<WeatherData>() {
                    @Override
                    public void onNext(WeatherData weatherData) {

                        Timber.e("Print pretty weather data :\n" + weatherData.toString());
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Timber.d(throwable);
                        handleError(throwable);
                    }

                    @Override
                    public void onComplete() {
                    }
                }));
    }

    private MainActivityComponent getComponent() {
        return DaggerMainActivityComponent.builder().applicationComponent(((WeatherApp) getApplication()).getComponent()).build();
    }
}
