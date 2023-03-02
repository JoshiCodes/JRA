package de.joshizockt.jra.requests.json.subreddit;

import de.joshizockt.jra.object.Link;
import de.joshizockt.jra.object.RedditObject;
import de.joshizockt.jra.requests.json.JsonRequest;
import de.joshizockt.jra.response.RedditResponse;

import java.util.List;

public class SubredditRandomPostRequest extends JsonRequest<Link> {

    public SubredditRandomPostRequest(String subreddit) {
        super(subreddit + "/random/.json?count=1");
    }

    @Override
    public Link parse(RedditResponse... responses) {
        RedditResponse response = responses[0]; // Get the first response, because we only need one
        List<RedditObject> objects = response.getData().getChildrenAsObjects();
        List<Link> links = objects.stream().filter(o -> o instanceof Link).map(o -> (Link) o).toList();
        if(links.size() == 0) return null;
        return links.get(0);
    }

}
