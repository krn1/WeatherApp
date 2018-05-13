package weather.co.detail.epoxy;

import com.airbnb.epoxy.EpoxyController;

import java.util.LinkedHashSet;
import java.util.Set;

import weather.co.detail.model.forecast.Weather;

public class WeatherDetailsController extends EpoxyController {

    private Set<Weather> weatherSet;
    private WeatherInfo headerData;

    public WeatherDetailsController() {
        weatherSet = new LinkedHashSet<>();
        headerData = null;
    }

    @Override
    protected void buildModels() {
        new HeaderViewModel_()
                .id("UserProfileCard")
                .header(headerData)
                .addIf(headerData != null, this);

        new EmptyListViewModel_()
                .id("EmptyList")
                .addIf(weatherSet.isEmpty() && headerData == null, this);
    }


    public void setHeader(WeatherInfo headerData) {
        this.headerData = headerData;
        requestModelBuild();
    }
}
