package com.jayden.bridgenotifier.rss.model;

import java.net.MalformedURLException;
import java.net.URL;

public record FeedUrl(
    URL url
) {
    public static FeedUrl by(String url) {
        URL uri;
        try {
            uri = new URL(url); // Check if the URL is valid
        } catch (MalformedURLException | IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid URL: " + url);
        }

        return new FeedUrl(uri);
    }
}
