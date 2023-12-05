package com.jayden.bridgenotifier.rss.service;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import org.junit.jupiter.api.*;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.BinaryBody;
import org.mockserver.model.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static com.jayden.bridgenotifier.Fixture.mockRssServerStart;
import static com.jayden.bridgenotifier.rss.ConvertingFunctions.apply;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

@SpringBootTest
class RssFeedServiceTest {

    private static ClientAndServer mockServer;

    @BeforeAll
    static void setUp() {
        mockServer = mockRssServerStart();
    }

    @AfterAll
    static void tearDown() {
        mockServer.stop();
    }


    @Autowired
    private RssFeedService sut;

    @Test
    @DisplayName("download rss feed, and return xml reader")
    void download_rss_feed() throws IOException, FeedException {
        SyndFeed actual = sut.readFeed(apply("http://localhost:8888/rss"));

        actual.getEntries().forEach(System.out::println);

        assertThat(actual).isNotNull();
    }
}