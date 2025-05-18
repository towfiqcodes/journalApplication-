package net.gaelixinfo.Journal.App.service;

import net.gaelixinfo.Journal.App.api.response.WeatherResponse;
import net.gaelixinfo.Journal.App.cache.AppCache;
import net.gaelixinfo.Journal.App.constants.Placeholders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {


    @Value("${weather.api.key}")
    private   String apiKey;


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    public  WeatherResponse getWeather(String city) {
        String finalApi=appCache.getAppCache().get(AppCache.keys.WEATHER_API.toString()).replace(Placeholders.city, city).replace(Placeholders.apiKey, apiKey);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherResponse.class);
        WeatherResponse body = response.getBody();
        return body;

    }
}
