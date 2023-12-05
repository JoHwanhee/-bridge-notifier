package com.jayden.bridgenotifier.rss.service;

import com.jayden.bridgenotifier.rss.model.FeedUrl;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class RssWatcherService {

    private final RssFeedService rssFeedService;
    private final ApplicationEventPublisher applicationEventPublisher;

    public RssWatcherService(RssFeedService rssFeedService, ApplicationEventPublisher applicationEventPublisher) {
        this.rssFeedService = rssFeedService;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void checkForNewFeeds(FeedUrl feedUrl) throws IOException, FeedException {
        SyndFeed feed = rssFeedService.readFeed(feedUrl);
        List<SyndEntry> entries = feed.getEntries();

        for (SyndEntry entry : entries) {
            fireEvent(entry);
        }
    }

    private void fireEvent(SyndEntry entry) {
        entry.getLink();
        applicationEventPublisher.publishEvent(NewFeedPublishedEvent.empty());
    }
}