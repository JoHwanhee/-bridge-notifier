package com.jayden.bridgenotifier;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RssFeedsTest {


    @Test
    @DisplayName("download rss feed")
    void download_rss_feed() {
        RssFeeds rssFeeds = RssFeeds.from("https://www.bridgewinners.com/rss/articleFeed.xml");

        rssFeeds.download();

        assertThat(rssFeeds).isNotNull();
    }
}