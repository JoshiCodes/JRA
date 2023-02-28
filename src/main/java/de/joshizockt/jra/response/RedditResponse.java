package de.joshizockt.jra.response;

import com.google.gson.JsonObject;

public class RedditResponse {

    private String kind;
    private ResponseData data;

    public String getKind() {
        return kind;
    }

    public ResponseData getData() {
        return data;
    }

}
