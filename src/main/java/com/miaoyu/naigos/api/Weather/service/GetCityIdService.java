package com.miaoyu.naigos.api.Weather.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Service
public class GetCityIdService {
    public String getCityIdService(String cityName) {
        String apiUrl = "https://geoapi.qweather.com/v2/city/lookup?location=beijin&key=cd612e4d815846debdd77804462f2468";
        System.out.println(apiUrl);
        try {
            // 创建URL对象
            URL url = new URL(apiUrl);
            // 打开连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // 设置请求方法
            connection.setRequestMethod("GET");

            // 获取响应码
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
                String inputLine;
                StringBuffer response = new StringBuffer();
                System.out.println(response);
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                // 解析JSON数据
                JSONObject jsonObject = new JSONObject(response.toString());
                String code = jsonObject.getString("code");
                System.out.println(code);
                if ("200".equals(code)) {
                    JSONArray locationArray = jsonObject.getJSONArray("location");
                    for (int i = 0; i < locationArray.length(); i++) {
                        JSONObject locationObject = locationArray.getJSONObject(i);
                        String name = locationObject.getString("name");
                        String id = locationObject.getString("id");
                        // 其他字段也可以类似地获取
                        System.out.println("Name: " + name + ", ID: " + id);

                        // 如果需要获取更多详细天气信息，可以根据返回的fxLink进一步访问
                        // String fxLink = locationObject.getString("fxLink");
                        // 你可以使用相同的HttpURLConnection方法来访问fxLink并获取详细天气数据
                    }
                    return locationArray.getJSONObject(0).getString("id");
                } else {
                    return "未知地区！";
                }
            } else {
                return "获取失败！";
            }
        } catch (Exception e) {
            return "获取失败！catch";
        }
    }
}
