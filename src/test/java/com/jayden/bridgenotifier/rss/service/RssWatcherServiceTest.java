package com.jayden.bridgenotifier.rss.service;

import com.rometools.rome.io.FeedException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.BinaryBody;
import org.mockserver.model.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;

import java.io.IOException;

import static com.jayden.bridgenotifier.rss.ConvertingFunctions.apply;
import static org.mockito.Mockito.*;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

@SpringBootTest
class RssWatcherServiceTest {
    private static ClientAndServer mockServer;

    @BeforeAll
    static void beforeAll() {
        mockServer = ClientAndServer.startClientAndServer(8888);
        mockServer.when(
                        request()
                                .withMethod("GET")
                                .withPath("/rss")
                )
                .respond(
                        response()
                                .withHeader(new Header("Content-Type", "text/xml;charset=utf-8"))
                                .withBody(new BinaryBody(("" +
                                        "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                                        "<rss version=\"2.0\">\n" +
                                        "\n" +
                                        "<channel>\n" +
                                        "  <title>W3Schools Home Page</title>\n" +
                                        "  <link>https://www.w3schools.com</link>\n" +
                                        "  <description>Free web building tutorials</description>\n" +
                                        "  <item>\n" +
                                        "    <title>RSS Tutorial</title>\n" +
                                        "    <link>https://www.w3schools.com/xml/xml_rss.asp</link>\n" +
                                        "    <description>New RSS tutorial on W3Schools</description>\n" +
                                        "  </item>\n" +
                                        "  <item>\n" +
                                        "    <title>XML Tutorial</title>\n" +
                                        "    <link>https://www.w3schools.com/xml</link>\n" +
                                        "    <description>New XML tutorial on W3Schools</description>\n" +
                                        "  </item>\n" +
                                        "</channel>\n" +
                                        "\n" +
                                        "</rss>" +
                                        "").strip()
                                        .getBytes()))
                );
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
    void name() throws IOException, FeedException {
        sut.checkForNewFeeds(apply("http://localhost:8888/rss"));

        verify(applicationEventPublisherMock, times(2))
            .publishEvent(NewFeedPublishedEvent.empty());
    }
}