package de.joshizockt.jra.response;

import com.google.gson.JsonObject;
import de.joshizockt.jra.object.RedditObject;

import java.util.ArrayList;
import java.util.List;

public class ResponseData {

    private String modhash;
    private String dist;
    private String after;
    private String before;

    private JsonObject[] children;

    public String getModhash() {
        return modhash;
    }

    public String getDist() {
        return dist;
    }

    public String getAfter() {
        return after;
    }

    public String getBefore() {
        return before;
    }

    public JsonObject[] getChildren() {
        return children;
    }

    public List<RedditObject> getChildrenAsObjects() {
        List<RedditObject> list = new ArrayList<>();
        for(JsonObject object : children) {
            RedditObject o = RedditObject.from(object);
            if(o == null) continue;
            list.add(o);
        }
        return list;
    }

}
