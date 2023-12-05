package com.jayden.bridgenotifier.rss.service;

import com.rometools.rome.io.FeedException;
import org.junit.jupiter.api.*;
import org.mockserver.integration.ClientAndServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;

import java.io.IOException;

import static com.jayden.bridgenotifier.Fixture.mockRssServerStart;
import static com.jayden.bridgenotifier.rss.ConvertingFunctions.apply;
import static org.mockito.Mockito.*;

@SpringBootTest
class RssWatcherServiceTest {
    private static ClientAndServer mockServer;

    @BeforeAll
    static void setUpAll() {
        mockServer = mockRssServerStart();
    }

    @AfterAll
    static void tearDown() {
        mockServer.stop();
    }


    @Autowired
    private RssFeedService rssFeedService;
    private ApplicationEventPublisher applicationEventPublisherMock;
    private RssWatcherService sut;

    @BeforeEach
    void setUp() {
        applicationEventPublisherMock = mock(ApplicationEventPublisher.class);
        sut = new RssWatcherService(rssFeedService, applicationEventPublisherMock);
    }

    @Test
    @DisplayName("rss item이 새로 추가되면, 이벤트를 발생시킨다")
    void name() throws IOException, FeedException {
        sut.checkForNewFeeds(apply("http://localhost:8888/rss"));

        verify(applicationEventPublisherMock, times(2))
            .publishEvent(NewFeedPublishedEvent.empty());
    }
}