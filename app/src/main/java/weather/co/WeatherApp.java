package weather.co;

import android.app.Application;

import dagger.android.DaggerApplication;
import timber.log.Timber;
import timber.log.Timber.DebugTree;

public class WeatherApp extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        // init dependency injection graph
        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        component.inject(this);

        Timber.plant(new DebugTree());
    }

    public void print() {
        Timber.e("Inside Application baby");
    }

    public ApplicationComponent getComponent() {
        return component;
    }
}
