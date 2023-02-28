package de.joshizockt.jra.requests.subreddit;

import com.google.gson.JsonObject;
import de.joshizockt.jra.object.Link;
import de.joshizockt.jra.object.RedditObject;
import de.joshizockt.jra.requests.Request;
import de.joshizockt.jra.response.RedditResponse;

import java.util.Arrays;
import java.util.List;

public class SubredditHotRequest extends Request<List<Link>> {

    public SubredditHotRequest(String subreddit) {
        super(subreddit + "/hot/.json");
    }

    public SubredditHotRequest(String subreddit, int count) {
        super(subreddit + "/hot/.json?count=" + count);
    }

    @Override
    public List<Link> parse(RedditResponse response) {
        List<RedditObject> objects = response.getData().getChildrenAsObjects();
        return objects.stream().filter(o -> o instanceof Link).map(o -> (Link) o).toList();
    }

}
