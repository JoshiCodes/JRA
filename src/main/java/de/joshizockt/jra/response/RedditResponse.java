package de.joshizockt.jra.response;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;

public class RedditResponse {

    @Expose
    private String kind;

    @Expose
    private JsonObject data;

    private ResponseData responseData;

    public String getKind() {
        return kind;
    }

    public ResponseData getData() {
        if(responseData != null) return responseData;
        return responseData = new ResponseData(data);
    }

}
