package de.joshizockt.jra.requests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import de.joshizockt.jra.response.RedditResponse;
import de.joshizockt.jra.rest.RestAction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RequestHandler {

    private final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36 OPR/77.0.4054.203";
    public static final String CONTENT_TYPE = "application/json";

    public <T> RestAction<T> execute(Request<T> request) {
        return new RestAction<>(unused -> {
            HttpURLConnection connection = null;
            try {
                URL url = new URL(request.getUrl());
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("User-Agent", USER_AGENT);
                connection.setRequestProperty("Content-Type", CONTENT_TYPE);
                connection.setDoOutput(true);
                connection.setDoInput(true);
                if (request.hasData()) {
                    // TODO
                }

                Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
                JsonElement element = gson.fromJson(
                        new BufferedReader(new InputStreamReader(connection.getInputStream())),
                        JsonElement.class
                );
                if(element.isJsonObject()) {
                    RedditResponse response = gson.fromJson(element, RedditResponse.class);
                    if(response == null) {
                        return null;
                    }
                    return request.parse(response);
                }
                if(element.isJsonArray()) {
                    List<RedditResponse> responses = new ArrayList<>();
                    for (JsonElement jsonElement : element.getAsJsonArray()) {
                        responses.add(gson.fromJson(jsonElement, RedditResponse.class));
                    }
                    if(responses.isEmpty()) {
                        return null;
                    }
                    return request.parse(responses.toArray(new RedditResponse[0]));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return null;
        });
    }

}
