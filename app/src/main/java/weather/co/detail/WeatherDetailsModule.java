package weather.co.detail;


import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import weather.co.dagger.PerActivity;

@Module
class WeatherDetailsModule {
    private WeatherDetailContract.View view;
    private String city;

    WeatherDetailsModule(WeatherDetailContract.View view, String city) {
        this.view = view;
        this.city = city;
    }

    @PerActivity
    @Provides
    WeatherDetailContract.View providesView() {
        return view;
    }

    @PerActivity
    @Provides
    CompositeDisposable providesDisposable() {
        return new CompositeDisposable();
    }

    @PerActivity
    @Provides
    String provideCity() {
        return city;
    }
}
