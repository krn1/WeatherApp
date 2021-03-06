package weather.co.detail.epoxy;

import com.airbnb.epoxy.EpoxyController;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class WeatherDetailsController extends EpoxyController {

    private Set<WeatherInfo> weatherList;
    private WeatherInfo headerData;

    public WeatherDetailsController() {
        weatherList = new LinkedHashSet<>();
        headerData = null;
    }

    @Override
    protected void buildModels() {
        new EmptyListViewModel_()
                .id("EmptyList")
                .addIf(weatherList.isEmpty() && headerData == null, this);

        new HeaderViewModel_()
                .id("UserProfileCard")
                .header(headerData)
                .addIf(headerData != null, this);

        for (WeatherInfo weatherInfo : weatherList) {
            new WeatherListItemModel_()
                    .id(weatherInfo.getDay())
                    .content(weatherInfo)
                    .addTo(this);
        }
    }


    public void setHeader(WeatherInfo headerData) {
        this.headerData = headerData;
        requestModelBuild();
    }

    public void setContents(List<WeatherInfo> weatherInfoList) {
        weatherList.addAll(weatherInfoList);
        requestModelBuild();
    }
}
