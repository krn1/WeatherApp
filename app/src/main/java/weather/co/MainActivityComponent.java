package weather.co;

import dagger.Component;
import weather.co.dagger.PerActivity;

@PerActivity
@Component(dependencies = ApplicationComponent.class)
interface MainActivityComponent {
    void inject(MainActivity activity);
}