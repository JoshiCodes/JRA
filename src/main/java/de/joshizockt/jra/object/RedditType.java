package de.joshizockt.jra.object;

public enum RedditType {

    /**
     * Only {@link LINK} is supported
     */

    COMMENT("t1", null),
    ACCOUNT("t2", null),
    LINK("t3", Link.class),
    MESSAGE("t4", null),
    SUBREDDIT("t5", null),
    AWARD("t6", null);

    private final String prefix;
    private final Class<? extends RedditObject> clazz;

    RedditType(String prefix, Class<? extends RedditObject> clazz) {
        this.prefix = prefix + (prefix.endsWith("_") ? "" : "_");
        this.clazz = clazz;
    }

    public String getPrefix() {
        return prefix;
    }

    public Class<? extends RedditObject> getClazz() {
        return clazz;
    }

    public static RedditType of(String id) {
        if(id == null)
            return null;
        id = id.toLowerCase();
        if(!id.endsWith("_") && !id.contains("_"))
            id += "_";
        for(RedditType type : values()) {
            if(id.startsWith(type.getPrefix()))
                return type;
        }
        return null;
    }

}
