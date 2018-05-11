package weather.co;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final WeatherApp application;

    public ApplicationModule(WeatherApp application) {
        this.application = application;
    }

    @Provides
    public WeatherApp provideApplication() {
        return application;
    }

}
