package weather.co.detail;

interface WeatherDetailContract {
    interface View {

        void showError(String message);

    }

    interface Presenter {

        void start();

        void stop();

    }
}
