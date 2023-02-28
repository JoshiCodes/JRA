package de.joshizockt.jra;

import de.joshizockt.jra.requests.Request;
import de.joshizockt.jra.requests.RequestHandler;
import de.joshizockt.jra.rest.RestAction;

public class RedditAPIImpl extends RedditAPI {

    private final RequestHandler requestHandler;

    public RedditAPIImpl() {
        this.requestHandler = new RequestHandler();
    }

    @Override
    public <T> RestAction<T> makeRequest(Request<T> request) {
        return requestHandler.execute(request);
    }

}
