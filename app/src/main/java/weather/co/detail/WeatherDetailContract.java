package weather.co.detail;

import weather.co.detail.epoxy.WeatherInfo;

interface WeatherDetailContract {
    interface View {

        void showError(String message);

        void showHeader(WeatherInfo weatherInfo);
    }

    interface Presenter {

        void start();

        void stop();

    }
}
