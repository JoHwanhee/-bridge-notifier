package com.jayden.bridgenotifier;

import javax.sql.RowSetInternal;
import javax.sql.rowset.WebRowSet;
import javax.sql.rowset.spi.XmlReader;
import java.io.Reader;
import java.sql.SQLException;

public class RssFeeds {

    private final FeedUri feedUri;

    private RssFeeds(FeedUri feedUri) {
        this.feedUri = feedUri;
    }

    public static RssFeeds from(String url) {
        return new RssFeeds(FeedUri.by(url));
    }

    public XmlReader download() {
        return new XmlReader() {
            @Override
            public void readXML(WebRowSet caller, Reader reader) throws SQLException {

            }

            @Override
            public void readData(RowSetInternal caller) throws SQLException {

            }
        };
    }
}
