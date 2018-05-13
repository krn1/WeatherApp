package weather.co.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

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
        initializeFresco();
    }

    public ApplicationComponent getComponent() {
        return component;
    }
    // region private
    private void initializeFresco() {
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setDownsampleEnabled(true)
                .build();
        Fresco.initialize(this, config);
    }
    //endregion
}
