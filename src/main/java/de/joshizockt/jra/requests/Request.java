package de.joshizockt.jra.requests;

import com.google.gson.JsonObject;
import de.joshizockt.jra.response.RedditResponse;

import java.util.HashMap;

public abstract class Request<T> {

    protected String BASE_URL = "https://reddit.com/r/";

    private final String url;
    private final HashMap<String, Object> body;

    public Request(String parameter) {
        this.url = BASE_URL + (parameter.startsWith("/") ? parameter.replaceFirst("/", "") : parameter); // Remove first slash if exists, to prevent double slashes
        this.body = new HashMap<>();
    }

    public abstract T parse(RedditResponse... response);

    protected void addData(String key, Object value) {
        body.put(key, value);
    }

    public boolean hasData() {
        return !body.isEmpty();
    }

    public String getUrl() {
        return url;
    }

    public HashMap<String, Object> getBody() {
        return body;
    }

}
