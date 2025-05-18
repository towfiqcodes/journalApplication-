package net.gaelixinfo.Journal.App.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WeatherResponse{
    private Current current;


    @Getter
    @Setter
    public  class Current{
        @JsonProperty("temperature")
        private int temperature;
        @JsonProperty("weather_descriptions")
        private List<String> weatherDescriptions;
        @JsonProperty("feelslike")
        private int feelsLike;

    }

}




