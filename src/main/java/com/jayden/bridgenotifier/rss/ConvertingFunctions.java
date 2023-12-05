package com.jayden.bridgenotifier.rss;

import com.jayden.bridgenotifier.rss.model.FeedUrl;

import java.util.function.Function;

public class ConvertingFunctions {
    private static Function<String, FeedUrl> feedApply = FeedUrl::by;

    public static FeedUrl apply(String url) {
        return feedApply.apply(url);
    }
}
