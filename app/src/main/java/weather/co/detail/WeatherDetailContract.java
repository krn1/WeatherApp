package weather.co.detail;

import java.util.List;

import weather.co.detail.epoxy.WeatherInfo;

interface WeatherDetailContract {
    interface View {

        void showError(String message);

        void showHeader(WeatherInfo weatherInfo);

        void showWeatherForecast(List<WeatherInfo> weatherInfoList);
    }

    interface Presenter {

        void start();

        void stop();

    }
}
