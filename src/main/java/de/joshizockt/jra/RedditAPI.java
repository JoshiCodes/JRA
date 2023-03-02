package de.joshizockt.jra;

import de.joshizockt.jra.requests.Request;
import de.joshizockt.jra.requests.RequestHandler;
import de.joshizockt.jra.rest.RestAction;

public abstract class RedditAPI {

    public static RedditAPI create() {
        final RequestHandler handler = new RequestHandler();;
        return new RedditAPI() {

            @Override
            public <T> RestAction<T> makeRequest(Request<T> request) {
                return handler.execute(request);
            }
        };
    }

    abstract public <T> RestAction<T> makeRequest(Request<T> request);

}
