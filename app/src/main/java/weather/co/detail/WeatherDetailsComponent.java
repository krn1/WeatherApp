package weather.co.detail;

import dagger.Component;
import weather.co.app.ApplicationComponent;
import weather.co.dagger.PerActivity;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = WeatherDetailsModule.class)
interface WeatherDetailsComponent {

    void inject(WeatherDetailsActivity weatherDetailsActivity);
}
