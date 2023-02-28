package de.joshizockt.jra;

import de.joshizockt.jra.requests.Request;
import de.joshizockt.jra.rest.RestAction;

public abstract class RedditAPI {

    public static RedditAPIImpl create() {
        return new RedditAPIImpl();
    }

    abstract public <T> RestAction<T> makeRequest(Request<T> request);

}
