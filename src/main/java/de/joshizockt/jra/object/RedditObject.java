package de.joshizockt.jra.object;

import com.google.gson.JsonObject;
import de.joshizockt.jra.util.JsonUtil;

import java.lang.reflect.Constructor;

public abstract class RedditObject {

    private JsonObject data;

    private final RedditType type;
    private final String id;

    public RedditObject(RedditType type, JsonObject data) {
        this(
                data,
                type,
                JsonUtil.getString(data, "id", null)
        );
    }

    RedditObject(JsonObject data, RedditType type, String id) {
        this(data, type.getPrefix() + id);
    }

    RedditObject(JsonObject data, String id) {
        if(!id.contains("_"))
            throw new IllegalArgumentException("Invalid ID: " + id);

        String[] args = id.split("_");
        if(args.length != 2)
            throw new IllegalArgumentException("Invalid ID: " + id);

        this.type = RedditType.of(args[0].toUpperCase());
        if(type == null)
            throw new IllegalArgumentException("Invalid Object Type: " + args[0].toUpperCase() + " does not match any known type.");
        this.id = args[1];
        this.data = data;
    }

    public static RedditObject from(JsonObject object) {
        String kind = JsonUtil.getString(object, "kind", null);
        JsonObject data = object.getAsJsonObject("data");
        if(kind == null || data == null) return null;
        String id = JsonUtil.getString(data, "id", null);
        if(id == null) return null;
        RedditType type = RedditType.of(kind);
        if(type == null) return null;
        if(type.getClazz() == null) {
            throw new NullPointerException("The Class for the Type " + type.name() + " is null! Cannot create new Object with this Type!");
        }
        Class<? extends RedditObject> clazz = type.getClazz();
        Constructor<?>[] constructors = clazz.getConstructors();
        for (Constructor<?> constructor : constructors) {
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            if(parameterTypes.length == 2 && parameterTypes[0].equals(RedditType.class) && parameterTypes[1].equals(JsonObject.class)) {
                try {
                    return (RedditObject) constructor.newInstance(type, data);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if(parameterTypes.length == 1 && parameterTypes[0].equals(JsonObject.class)) {
                try {
                    return (RedditObject) constructor.newInstance(data);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public RedditType getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public JsonObject getRawData() {
        return data;
    }

}
