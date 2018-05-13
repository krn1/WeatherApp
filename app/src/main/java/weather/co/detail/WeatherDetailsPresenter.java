package weather.co.detail;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import timber.log.Timber;
import weather.co.dagger.PerActivity;
import weather.co.detail.epoxy.WeatherInfo;
import weather.co.detail.model.WeatherData;
import weather.co.detail.model.forecast.ForecastData;
import weather.co.detail.model.forecast.List;
import weather.co.utils.DateUtil;
import weather.repository.network.NetworkUtils;
import weather.repository.network.RestApi;

import static weather.repository.network.NetworkUtils.API_KEY;

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
        getCurrentWeather();
    }

    @Override
    public void stop() {
        disposable.clear();
    }

    // region private
    private void getCurrentWeather() {
        disposable.add(apiService.getCurrentWeather(city, "metric", API_KEY)
                .flatMap(weatherData -> Flowable.fromCallable(() -> {
                    getForecastWeather();
                    return weatherData;
                }))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<WeatherData>() {
                    @Override
                    public void onNext(WeatherData weatherData) {
                        String imgUrl = NetworkUtils.getImageUrl(weatherData.getWeather().get(0).getIcon());
                        String temp = String.valueOf(weatherData.getMain().getTemp());
                        WeatherInfo weatherInfo = new WeatherInfo();
                        weatherInfo.setImageUrl(imgUrl);
                        weatherInfo.setTemp(temp);
                        view.showHeader(weatherInfo);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Timber.d(throwable);
                        view.showError(throwable.getMessage());
                    }

                    @Override
                    public void onComplete() {
                    }
                }));
    }

    private void getForecastWeather() {
        disposable.add(apiService.getForecastWeather(city, "metric", "7", API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<ForecastData>() {
                    @Override
                    public void onNext(ForecastData weatherData) {
                        showListContent(weatherData);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Timber.e(throwable);
                        view.showError(throwable.getMessage());
                    }

                    @Override
                    public void onComplete() {
                    }
                }));
    }

    private void showListContent(ForecastData weatherData) {
        java.util.List<WeatherInfo> weatherInfoList = new ArrayList<>();
        for (List listItem : weatherData.getList()) {
            String day = DateUtil.getDateFrom(listItem.getDt());
            String temp = String.valueOf(listItem.getTemp().getDay());
            String imgUrl = NetworkUtils.getImageUrl(listItem.getWeather().get(0).getIcon());
            WeatherInfo weatherInfo = new WeatherInfo(day, imgUrl, temp, "kelvin");
            weatherInfoList.add(weatherInfo);

            // Timber.e("day:%s temp:%s imgurl:%s ", day, temp, imgUrl);
        }
        view.showWeatherForecast(weatherInfoList);
    }

    // endregion
}
