package weather.co.detail.epoxy;

import com.airbnb.epoxy.EpoxyController;

import java.util.LinkedHashSet;
import java.util.Set;

import weather.co.detail.model.forecast.Weather;

public class WeatherDetailsController extends EpoxyController {

    private Set<Weather> weatherSet;

    public WeatherDetailsController() {
        weatherSet = new LinkedHashSet<>();
    }

    @Override
    protected void buildModels() {
        new EmptyListViewModel_()
                .id("EmptyList")
                .addIf(weatherSet.isEmpty(), this);
    }

    public void setHeader() {
        requestModelBuild();
    }
}
