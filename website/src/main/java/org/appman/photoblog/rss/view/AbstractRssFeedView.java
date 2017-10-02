package org.appman.photoblog.rss.view;

import com.rometools.rome.feed.rss.Channel;
import org.appman.photoblog.core.config.WebsiteConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.feed.AbstractFeedView;

/**
 * Created by Pieter on 6/23/2016.
 */
public abstract class AbstractRssFeedView extends AbstractFeedView<Channel> {

    @Autowired
    protected WebsiteConfig websiteConfig;

    public AbstractRssFeedView() {
        setContentType("application/rss+xml");
    }

    protected Channel newFeed() {
        return new Channel("rss_2.0");
    }



}
