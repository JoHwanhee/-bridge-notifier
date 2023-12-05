package com.jayden.bridgenotifier.rss.model;

import org.junit.jupiter.api.Test;

import static com.jayden.bridgenotifier.rss.ConvertingFunctions.apply;
import static org.junit.jupiter.api.Assertions.*;

class FeedUrlTest {


    @Test
    void validUrlShouldReturnFeedUri() {
        String validUrl = "https://www.example.com/feed";

        FeedUrl feedUrl = apply(validUrl);

        assertEquals(validUrl, feedUrl.url().toString());
    }

    @Test
    void invalidUrlShouldThrowException() {
        String invalidUrl = "invalidurl";

        assertThrows(IllegalArgumentException.class, () -> {
            apply(invalidUrl);
        });
    }
}