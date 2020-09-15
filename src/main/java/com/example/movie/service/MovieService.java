package com.example.movie.service;

import com.example.movie.dto.MovieDTO;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {
    final static String NAVER = "wp1eFPIkPNkn71JUjuX1";
    final static String NAVERSECRET = "VQsaz1sI4e";

    public List<MovieDTO> movieSearch(String query){
        BufferedReader br = null;
        String urlStr = "https://openapi.naver.com/v1/search/movie.json?query=";
        String display = "100";
        List<MovieDTO> movieDTOList = new ArrayList<>();

        try {
            urlStr += URLEncoder.encode(query, "UTF-8");
            urlStr += "&display="+display;
            URL url = new URL(urlStr);

            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("X-Naver-Client-Id", NAVER);
            urlConnection.setRequestProperty("X-Naver-Client-Secret", NAVERSECRET);
            br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),"UTF-8"));
            String result = "";
            String line;
            while((line = br.readLine()) != null) {
                result = result + line;
            }

            JsonParser jsonParser = new JsonParser();
            JsonObject movieObject = (JsonObject) jsonParser.parse(result);
            JsonArray movieItems = (JsonArray) movieObject.get("items");

            System.out.println(movieItems);

            for (int i = 0; i < movieItems.size(); i++) {
                JsonObject items = (JsonObject) movieItems.get(i);

                String title = items.get("title").getAsString();
                String link = items.get("link").getAsString();
                String image = items.get("image").getAsString();
                String pubDate = items.get("pubDate").getAsString();
                String director = items.get("director").getAsString();
                String actor = items.get("actor").getAsString();
                double userRating = items.get("userRating").getAsDouble();

                MovieDTO movieDTO = new MovieDTO(title, link, image, pubDate, director, actor, userRating);
                movieDTOList.add(movieDTO);
            }


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return movieDTOList;
    }
}
