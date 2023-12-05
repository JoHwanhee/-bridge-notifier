package com.jayden.bridgenotifier;

import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.BinaryBody;
import org.mockserver.model.Header;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

public class Fixture {

    public static ClientAndServer mockRssServerStart() {
        ClientAndServer mockServer = ClientAndServer.startClientAndServer(8888);
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
        return mockServer;
    }
}
