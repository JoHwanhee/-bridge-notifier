package com.jayden.bridgenotifier;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public record FeedUri(
    URI uri
) {
    public static FeedUri by(String url) {
        URI uri;
        try {
            uri = new URL(url).toURI(); // Check if the URL is valid
        } catch (MalformedURLException | IllegalArgumentException | URISyntaxException e) {
            throw new IllegalArgumentException("Invalid URL: " + url);
        }

        return new FeedUri(uri);
    }
}
