package com.jayden.bridgenotifier.rss.service;

import com.jayden.bridgenotifier.rss.model.FeedUrl;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;

@Service
public class RssFeedService {

    public SyndFeed readFeed(FeedUrl feedUrl) throws IOException, FeedException {
        try (XmlReader reader = new XmlReader(new URL(feedUrl.url().toString()))) {
            return new SyndFeedInput().build(reader);
        }
    }
}