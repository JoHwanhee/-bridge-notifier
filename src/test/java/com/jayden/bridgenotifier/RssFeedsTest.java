package com.jayden.bridgenotifier;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.BinaryBody;
import org.mockserver.model.Header;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

class RssFeedsTest {

    private ClientAndServer mockServer;

    @BeforeEach
    void setUp() {
        mockServer = ClientAndServer.startClientAndServer(8888);
        new MockServerClient("localhost", 8888)
                .when(
                        request()
                                .withMethod("GET")
                                .withPath("/rss")
                )
                .respond(
                        response()
                                .withHeader(new Header("Content-Type", "text/xml;charset=utf-8"))
                                .withBody(new BinaryBody("<rss></rss>".getBytes()))
                );
    }


    @Test
    @DisplayName("download rss feed, and return xml reader")
    void download_rss_feed() throws IOException, ParserConfigurationException, SAXException {
        RssFeeds sut = RssFeeds.from("http://localhost:8888/rss");

        Document actual = sut.download();

        assertThat(actual).isNotNull();
    }
}