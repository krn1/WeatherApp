package weather.co;

import javax.inject.Singleton;

import dagger.Component;
import weather.repository.network.NetworkModule;
import weather.repository.network.RestApi;

@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent {

    void inject(WeatherApp application);

    WeatherApp application();

    RestApi restApi();

}
