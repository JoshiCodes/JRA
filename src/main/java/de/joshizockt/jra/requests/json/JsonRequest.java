package de.joshizockt.jra.requests.json;

import de.joshizockt.jra.requests.Request;

/**
 * JsonRequest represents a simple Request that uses a normal Reddit Link with the /.json extension
 * This does not require any authentication and is used for simple requests
 * @param <T> The Element that should be returned
 */
public abstract class JsonRequest<T> extends Request<T> {

    public JsonRequest(String arg) {
        super(
                arg +
                        (arg.contains(".json") ?
                                ""
                                :
                                ((arg.endsWith("/") ? "" : "/") + ".json")
                        )
        );
    }

}
