![Github Release](https://img.shields.io/github/v/release/JoshiCodes/JRA?include_prereleases)

# JRA (Java Reddit API)

This Project is a try to implement ***some*** features of the [Reddit API](https://www.reddit.com/dev/api/).
I do not plan to have it work as well as some others, rather as a learning experience for me.

## This API is not finished and may not be finished in the future.

## Download
You can download the latest Version as a Jar [here](https://github.com/JoshiCodes/JRA/releases).
You can also import it as a Maven Dependency:
```xml
<dependency>
    <groupId>de.joshizockt</groupId>
    <artifactId>jta</artifactId>
    <version>1.0-alpha.1</version>
</dependency>
```

## Usage
To use this API, you need a new RedditAPI Instance. You can do this by using the following Code:
```java
RedditAPI api = RedditAPI.create();
```

**Note:** *If Authentication is built in, here is where this would be explained.*

### Requests

**Note:** *As of now, there are only __two__ Requests, both of which are simple JSON Requests. Therefor, you do not need to authenticate. This also means, that you can not use the API to post, comment or vote.*

You can use the API to call a simple Request to the Reddit API. This is done by using the following Code:
```java
api.makeRequest(Request<T>);
```
For now, there are only two Requests, which are:
- [SubredditHotRequest]()
- [SubredditRandomPostRequest]()
The first one returns a List of Links, the second one returns a single Link. Both of them are used like this:
```java
SubredditHotRequest request = new SubredditHotRequest("subredditname"); // Replace "subredditname" with the name of the Subreddit
api.makeRequest(request).queue(links -> {
    // Do something with the Links
});
```

As you may have noticed, the Request is not completed directly, but rather by using the `queue()` Method. This is because the Request is done asynchronously. If you want to wait for the Request to finish, you can use `RestAction#complete`. This will return the Result of the Request.

It is recommended to use `queue` instead of `complete`, as it will not block the Thread, but rather execute the Code in the Consumer when the Request is finished.