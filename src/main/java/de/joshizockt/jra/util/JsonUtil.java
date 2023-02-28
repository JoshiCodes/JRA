package de.joshizockt.jra.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class JsonUtil {

    public static String getString(JsonObject object, String key, String def) {
        JsonElement e = object.get(key);
        if(e == null) return def;
        if(!e.isJsonPrimitive()) return def;
        if(!e.getAsJsonPrimitive().isString()) return def;
        return e.getAsString();
    }

    public static boolean getBoolean(JsonObject object, String key, boolean def) {
        JsonElement e = object.get(key);
        if(e == null) return def;
        if(!e.isJsonPrimitive()) return def;
        if(!e.getAsJsonPrimitive().isBoolean()) return def;
        return e.getAsBoolean();
    }

    public static double getDouble(JsonObject object, String key, int def) {
        JsonElement e = object.get(key);
        if(e == null) return def;
        if(!e.isJsonPrimitive()) return def;
        if(!e.getAsJsonPrimitive().isNumber()) return def;
        return e.getAsDouble();
    }

    public static int getInt(JsonObject object, String key, int def) {
        JsonElement e = object.get(key);
        if(e == null) return def;
        if(!e.isJsonPrimitive()) return def;
        if(!e.getAsJsonPrimitive().isNumber()) return def;
        return e.getAsInt();
    }

    public static JsonObject getObject(JsonObject object, String key, JsonObject def) {
        JsonElement e = object.get(key);
        if(e == null) return def;
        if(!e.isJsonObject()) return def;
        return e.getAsJsonObject();
    }

    public static JsonArray getArray(JsonObject object, String key, JsonArray def) {
        JsonElement e = object.get(key);
        if(e == null) return def;
        if(!e.isJsonArray()) return def;
        return e.getAsJsonArray();
    }

}
