package weather.co.detail;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import timber.log.Timber;
import weather.co.dagger.PerActivity;

@PerActivity
class WeatherDetailsPresenter implements WeatherDetailContract.Presenter {

    private WeatherDetailContract.View view;
    private CompositeDisposable disposable;
    private String city;

    @Inject
    WeatherDetailsPresenter(WeatherDetailContract.View view,
                            String city,
                            CompositeDisposable disposable) {
        this.view = view;
        this.city = city;
        this.disposable = disposable;
    }

    @Override
    public void start() {
        Timber.e("Presenter start");
    }

    @Override
    public void stop() {
        disposable.clear();
    }
}
