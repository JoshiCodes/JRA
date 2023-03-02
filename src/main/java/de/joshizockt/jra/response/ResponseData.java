package de.joshizockt.jra.response;

import com.google.gson.JsonObject;
import de.joshizockt.jra.object.RedditObject;
import de.joshizockt.jra.util.JsonUtil;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ResponseData {

    private final String modhash;
    private final String dist;
    private final String after;

    private final JsonObject[] children;

    ResponseData(JsonObject object) {
        this.modhash = JsonUtil.getString(object, "modhash", null);
        this.dist = JsonUtil.getString(object, "dist", null);
        this.after = JsonUtil.getString(object, "after", null);
        this.children = JsonUtil.getArray(object, "children", null)
                .asList().stream().filter(e -> e instanceof JsonObject)
                .map(e -> (JsonObject) e).toArray(JsonObject[]::new);
    }

    public String getModhash() {
        return modhash;
    }

    public String getDist() {
        return dist;
    }

    public String getAfter() {
        return after;
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
