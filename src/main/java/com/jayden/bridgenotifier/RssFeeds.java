package com.jayden.bridgenotifier;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RssFeeds {

    private final FeedUri feedUri;

    private RssFeeds(FeedUri feedUri) {
        this.feedUri = feedUri;
    }

    public static RssFeeds from(String url) {
        return new RssFeeds(FeedUri.by(url));
    }

    public Document download() throws IOException, ParserConfigurationException, SAXException {
        URL url = new URL(feedUri.uri().toString());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        try (InputStream inputStream = connection.getInputStream()) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            return builder.parse(inputStream);
        } finally {
            connection.disconnect();
        }
    }
}
