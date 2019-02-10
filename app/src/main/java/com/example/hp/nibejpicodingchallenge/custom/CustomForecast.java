package com.example.hp.nibejpicodingchallenge.custom;

public class CustomForecast {
    String dateTime;
    String imageId;
    String temp;
    String description;
    String windSpeed;

    public CustomForecast(String dateTime, String imageId, String temp, String description, String windSpeed) {
        this.dateTime = dateTime;
        this.imageId = imageId;
        this.temp = temp;
        this.description = description;
        this.windSpeed = windSpeed;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }


    @Override
    public String toString() {
        return "CustomForecast{" +
                "dateTime='" + dateTime + '\'' +
                ", imageId='" + imageId + '\'' +
                ", temp='" + temp + '\'' +
                ", description='" + description + '\'' +
                ", windSpeed='" + windSpeed + '\'' +
                '}';
    }
}
