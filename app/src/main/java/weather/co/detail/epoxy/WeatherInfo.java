package weather.co.detail.epoxy;

import java.util.Objects;

public class WeatherInfo {
    String day;
    String imageUrl;
    String temp;
    String units;

    public WeatherInfo(){
        units = "kelvin";
    }

    public WeatherInfo(String day, String imageUrl, String temp, String units) {
        this.day = day;
        this.imageUrl = imageUrl;
        this.temp = temp;
        this.units = units;
    }
    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherInfo that = (WeatherInfo) o;
        return Objects.equals(day, that.day) &&
                Objects.equals(imageUrl, that.imageUrl) &&
                Objects.equals(temp, that.temp) &&
                Objects.equals(units, that.units);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageUrl, temp);
    }
}
