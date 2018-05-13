package weather.co.detail;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import timber.log.Timber;
import weather.co.dagger.PerActivity;
import weather.co.detail.model.WeatherData;
import weather.co.detail.model.forecast.ForecastWeatherData;
import weather.repository.network.RestApi;

import static weather.repository.network.NetworkConstants.API_KEY;

@PerActivity
class WeatherDetailsPresenter implements WeatherDetailContract.Presenter {

    private WeatherDetailContract.View view;
    private CompositeDisposable disposable;
    private RestApi apiService;
    private String city;

    @Inject
    WeatherDetailsPresenter(WeatherDetailContract.View view,
                            String city,
                            RestApi apiService,
                            CompositeDisposable disposable) {
        this.view = view;
        this.city = city;
        this.apiService = apiService;
        this.disposable = disposable;
    }

    @Override
    public void start() {
        Timber.e("Presenter start");
        getCurrentWeather();
       // getForecastWeather();
    }

    @Override
    public void stop() {
        disposable.clear();
    }

    // region private
    private void getCurrentWeather() {
        disposable.add(apiService.getCurrentWeather("London,uk", "metric", API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<WeatherData>() {
                    @Override
                    public void onNext(WeatherData weatherData) {

                        Timber.e("Current Temperature :\n" + weatherData.getMain().getTemp());
                        Timber.e(" weather data :\n" + weatherData.getWeather());
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

    private void getForecastWeather() {
        disposable.add(apiService.getForecastWeather("London,uk", "metric", "6", API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<ForecastWeatherData>() {
                    @Override
                    public void onNext(ForecastWeatherData weatherData) {

                        Timber.e(" forecast data :\n" + weatherData.toString());
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
    // endregion
}
