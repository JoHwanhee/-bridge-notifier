package com.jayden.bridgenotifier.rss.service;


public record NewFeedPublishedEvent() {
    public static NewFeedPublishedEvent empty() {
        return new NewFeedPublishedEvent();
    }
}
