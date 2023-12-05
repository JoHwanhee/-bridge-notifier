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

import static com.jayden.bridgenotifier.rss.ConvertingFunctions.apply;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

@SpringBootTest
class RssFeedServiceTest {

    private static ClientAndServer mockServer;

    @Autowired
    private RssFeedService sut;

    @BeforeAll
    static void setUp() {
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

    @Test
    @DisplayName("download rss feed, and return xml reader")
    void download_rss_feed() throws IOException, FeedException {
        SyndFeed actual = sut.readFeed(apply("http://localhost:8888/rss"));

        actual.getEntries().forEach(System.out::println);

        assertThat(actual).isNotNull();
    }
}