package com.jayden.bridgenotifier;

import org.junit.jupiter.api.Test;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;

class FeedUriTest {


    @Test
    void validUrlShouldReturnFeedUri() {
        String validUrl = "https://www.example.com/feed";
        FeedUri feedUri = FeedUri.by(validUrl);
        assertEquals(URI.create(validUrl), feedUri.uri());
    }

    @Test
    void invalidUrlShouldThrowException() {
        String invalidUrl = "invalidurl";
        assertThrows(IllegalArgumentException.class, () -> {
            FeedUri.by(invalidUrl);
        });
    }
}