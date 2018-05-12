package weather.co.app;

import android.app.Application;

import timber.log.Timber;
import timber.log.Timber.DebugTree;

public class WeatherApp extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        // dependency injection graph
        component = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
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
