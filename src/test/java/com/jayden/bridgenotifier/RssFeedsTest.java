package com.jayden.bridgenotifier;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.sql.rowset.spi.XmlReader;

import static org.assertj.core.api.Assertions.assertThat;

class RssFeedsTest {


    @Test
    @DisplayName("download rss feed")
    void download_rss_feed() {
        RssFeeds rssFeeds = RssFeeds.from("https://www.bridgewinners.com/rss/articleFeed.xml");

        XmlReader xmlReader = rssFeeds.download();

        assertThat(xmlReader).isNotNull();
    }
}