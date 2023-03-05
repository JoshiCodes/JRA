package de.joshizockt.jra.object;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import de.joshizockt.jra.util.JsonUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Link extends RedditObject {

    private final String subreddit;
    private final String subredditPrefixed;

    private final String selfText;

    private final String title;

    private final boolean saved;

    private final boolean hidden;

    private final String name;

    private final double upvoteRatio;
    private final int ups;
    private final int downs;
    private final int score;
    private final int numComments;

    private final int thumbnailWidth;
    private final int thumbnailHeight;

    private final String thumbnail;
    private final String domain;
    private final String url;
    private final String permalink;

    private final String urlOverriddenByDest;

    private final double created;
    private final double createdUtc;

    private final boolean isVideo;
    private final boolean mediaOnly;
    private final boolean spoiler;
    private final boolean nsfw;

    private final String author;

    private Link.Preview preview;

    public Link(JsonObject data) {
        super(RedditType.LINK, data);

        this.subreddit = JsonUtil.getString(data, "subreddit", null);
        this.subredditPrefixed = JsonUtil.getString(data, "subreddit_name_prefixed", null);

        this.selfText = JsonUtil.getString(data, "selftext", null);

        this.title = JsonUtil.getString(data, "title", null);

        this.saved = JsonUtil.getBoolean(data, "saved", false);
        this.hidden = JsonUtil.getBoolean(data, "hidden", false);

        this.name = JsonUtil.getString(data, "name", null);

        this.upvoteRatio = JsonUtil.getDouble(data, "upvote_ratio", 0);
        this.ups = JsonUtil.getInt(data, "ups", 0);
        this.downs = JsonUtil.getInt(data, "downs", 0);
        this.score = JsonUtil.getInt(data, "score", 0);
        this.numComments = JsonUtil.getInt(data, "num_comments", 0);

        this.thumbnailWidth = JsonUtil.getInt(data, "thumbnail_width", 0);
        this.thumbnailHeight = JsonUtil.getInt(data, "thumbnail_height", 0);

        this.thumbnail = JsonUtil.getString(data, "thumbnail", null);
        this.domain = JsonUtil.getString(data, "domain", null);
        this.url = JsonUtil.getString(data, "url", null);
        this.permalink = JsonUtil.getString(data, "permalink", null);

        this.urlOverriddenByDest = JsonUtil.getString(data, "url_overridden_by_dest", null);

        this.created = JsonUtil.getDouble(data, "created", 0);
        this.createdUtc = JsonUtil.getDouble(data, "created_utc", 0);

        this.isVideo = JsonUtil.getBoolean(data, "is_video", false);
        this.mediaOnly = JsonUtil.getBoolean(data, "media_only", false);
        this.spoiler = JsonUtil.getBoolean(data, "spoiler", false);
        this.nsfw = JsonUtil.getBoolean(data, "over_18", false);

        this.author = JsonUtil.getString(data, "author", null);

        JsonObject preview = JsonUtil.getObject(data, "preview", null);
        if(preview != null) {
            this.preview = new Link.Preview() {

                @Override
                public List<Image> getImages() {
                    List<Image> list = new ArrayList<>();
                    JsonArray images = JsonUtil.getArray(preview, "images", null);
                    if(images != null) {
                        for (int i = 0; i < images.size(); i++) {
                            JsonElement e = images.get(i);
                            if(e == null || !e.isJsonObject()) continue;
                            JsonObject o = e.getAsJsonObject();
                            Image image = new Image() {
                                @Override
                                public String getId() {
                                    return JsonUtil.getString(o, "id", null);
                                }

                                @Override
                                public Source getSource() {
                                    JsonElement source = JsonUtil.getObject(o, "source", null);
                                    if(source == null || !source.isJsonObject()) return null;
                                    return new Gson().fromJson(source, Source.class);
                                }

                                @Override
                                public List<Source> getResolutions() {
                                    JsonArray resolutions = JsonUtil.getArray(o, "resolutions", null);
                                    if(resolutions == null) return null;
                                    List<Source> list = new ArrayList<>();
                                    for (int i = 0; i < resolutions.size(); i++) {
                                        JsonElement e = resolutions.get(i);
                                        if(e == null || !e.isJsonObject()) continue;
                                        JsonObject o = e.getAsJsonObject();
                                        list.add(new Gson().fromJson(o, Source.class));
                                    }
                                    return list;
                                }

                                @Override
                                public Variants getVariants() {
                                    JsonObject variants = JsonUtil.getObject(o, "variants", null);
                                    if(variants == null) return null;
                                    return key -> JsonUtil.getObject(variants, key, null);
                                }
                            };
                            list.add(image);
                        }
                    }
                    return list;
                }

                @Override
                public boolean isEnabled() {
                    return JsonUtil.getBoolean(preview, "enabled", false);
                }

            };
        }

    }

    public String getSubreddit() {
        return subreddit;
    }

    public String getSubredditPrefixed() {
        return subredditPrefixed;
    }

    public String getSelfText() {
        return selfText;
    }

    public String getTitle() {
        return title;
    }

    public boolean isSaved() {
        return saved;
    }

    public boolean isHidden() {
        return hidden;
    }

    public String getName() {
        return name;
    }

    public double getUpvoteRatio() {
        return upvoteRatio;
    }

    public int getUpvotes() {
        return ups;
    }

    public int getDownvotes() {
        return downs;
    }

    public int getScore() {
        return score;
    }

    public int getNumComments() {
        return numComments;
    }

    public int getThumbnailWidth() {
        return thumbnailWidth;
    }

    public int getThumbnailHeight() {
        return thumbnailHeight;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getDomain() {
        return domain;
    }

    public String getUrl() {
        return url;
    }

    public String getPermalink() {
        return permalink;
    }

    public String getUrlOverriddenByDest() {
        return urlOverriddenByDest;
    }

    public double getCreated() {
        return created;
    }

    public Date getCreatedDate() {
        return new Date((long) (created * 1000));
    }

    public double getCreatedUtc() {
        return createdUtc;
    }

    public Date getCreatedUtcDate() {
        return new Date((long) (createdUtc * 1000));
    }

    public boolean isVideo() {
        return isVideo;
    }

    public boolean isMediaOnly() {
        return mediaOnly;
    }

    public boolean isSpoiler() {
        return spoiler;
    }

    public boolean isNsfw() {
        return nsfw;
    }

    public String getAuthor() {
        return author;
    }

    public Preview getPreview() {
        return preview;
    }

    public static interface Preview {

        List<Image> getImages();
        boolean isEnabled();

    }

    public interface Image {

        String getId();

        Image.Source getSource();
        List<Image.Source> getResolutions();
        Image.Variants getVariants();

        public class Source {
            private String url;
            private int width;
            private int height;

            public String getUrl() {
                return url;
            }

            public int getHeight() {
                return height;
            }

            public int getWidth() {
                return width;
            }

        }

        interface Variants {

            JsonObject getObject(String key);
            default Image.Source get(String key) {
                JsonObject o = getObject(key);
                if(o == null) return null;
                JsonElement source = JsonUtil.getObject(o, "source", null);
                if(source == null || !source.isJsonObject()) return null;
                return new Gson().fromJson(source, Image.Source.class);
            }
            default List<Image.Source> getResolutions(String key) {
                JsonObject o = getObject(key);
                if(o == null) return null;
                JsonArray resolutions = JsonUtil.getArray(o, "resolutions", null);
                if(resolutions == null) return null;
                List<Image.Source> list = new ArrayList<>();
                for (int i = 0; i < resolutions.size(); i++) {
                    JsonElement e = resolutions.get(i);
                    if(e == null || !e.isJsonObject()) continue;
                    list.add(new Gson().fromJson(e, Image.Source.class));
                }
                return list;
            }

        }

    }

}
