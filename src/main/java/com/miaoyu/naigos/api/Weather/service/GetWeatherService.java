package com.miaoyu.naigos.api.Weather.service;

import com.miaoyu.naigos.constantsMap.ErrorMap;
import com.miaoyu.naigos.constantsMap.SuccessMap;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class GetWeatherService {
    public Map<String, Object> getWeatherService(String location){
        String key = "cd612e4d815846debdd77804462f2468";
        String urlStr = "https://geoapi.qweather.com/v2/city/lookup?location=" + location;
        String cityId;
        String getNowWeatherUrl;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()){
            HttpGet request = new HttpGet(urlStr);
            request.addHeader("X-QW-Api-Key", key);
            request.addHeader("Accept-Encoding", "gzip, deflate");
            try (CloseableHttpResponse response = httpClient.execute(request)){
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode != 200){
                    return new ErrorMap().errorMap("城市无法查询！");
                }
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String responseBody = EntityUtils.toString(entity, "UTF-8");
                    JSONObject jsonObject = new JSONObject(responseBody);
                    if (!Objects.equals(jsonObject.getString("code"), "200")){
                        return new ErrorMap().errorMap("城市无法获取！");
                    }
                    JSONArray locationArray = jsonObject.getJSONArray("location");
                    if (locationArray.isEmpty()){
                        return new ErrorMap().errorMap("城市未找到！");
                    }
                    JSONObject locationObject = locationArray.getJSONObject(0);
                    cityId = locationObject.getString("id");
                } else {
                    return new ErrorMap().errorMap("城市未找到！");
                }
            } catch (IOException e){
                return new ErrorMap().apiFetchError();
            }
        } catch (IOException e){
            return new ErrorMap().apiFetchError();
        }
        getNowWeatherUrl = "https://devapi.qweather.com/v7/weather/now?location=" + cityId;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()){
            HttpGet requestNowWeather = new HttpGet(getNowWeatherUrl);
            requestNowWeather.addHeader("X-QW-Api-Key", key);
            requestNowWeather.addHeader("Accept-Encoding", "gzip, deflate");
            try (CloseableHttpResponse responseNowWeather = httpClient.execute(requestNowWeather)){
                int statusCode = responseNowWeather.getStatusLine().getStatusCode();
                if (statusCode != 200){
                    return new ErrorMap().errorMap("天气无法查询！");
                }
                HttpEntity entity = responseNowWeather.getEntity();
                if (entity == null) {
                    return new ErrorMap().errorMap("天气无法获取！");
                }
                String responseNowWeatherBody = EntityUtils.toString(entity, "UTF-8");
                JSONObject jsonObject = new JSONObject(responseNowWeatherBody);
                if (!Objects.equals(jsonObject.getString("code"), "200")){
                    return new ErrorMap().errorMap("天气无法获取！");
                }
                JSONObject nowWeatherBody = jsonObject.getJSONObject("now");
                Map<String, Object> data = new HashMap<>();
                data.put("city", location);
                data.put("city_id", cityId);
                data.put("temp", nowWeatherBody.getString("temp"));
                data.put("text", nowWeatherBody.getString("text"));
                return new SuccessMap().standardSuccessMap(data);
            } catch (IOException e){
                return new ErrorMap().apiFetchError();
            }
        } catch (IOException e){
            return new ErrorMap().apiFetchError();
        }
    }
}
