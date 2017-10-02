package org.appman.photoblog.page.medialisting.view;

import com.rometools.rome.feed.rss.Channel;
import org.appman.photoblog.page.generic.facade.MediaPostFacade;
import org.appman.photoblog.rss.view.AbstractRssFeedView;
import static org.appman.photoblog.page.generic.util.UrlUtil.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Date;
import java.util.Map;

/**
 * Created by Pieter on 6/23/2016.
 */
@Component("mainRssFeedView")
public class MainRssFeedView extends AbstractRssFeedView {

    @Resource(name = "defaultMediaPostFacade")
    private MediaPostFacade mediaPostFacade;


    public void setMediaPostFacade(MediaPostFacade mediaPostFacade) {
        this.mediaPostFacade = mediaPostFacade;
    }

    @Override
    protected Channel newFeed() {
        Channel channel = new Channel("rss_2.0");
        channel.setLink(websiteConfig.getBaseUrl() + rssFeedUrl());
        channel.setTitle(websiteConfig.getBaseTitle());
        channel.setDescription(websiteConfig.getDescription());
        channel.setPubDate(new Date());
        return channel;
    }

    @Override
    protected void buildFeedEntries(Map<String, Object> map, Channel channel, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        channel.setItems(mediaPostFacade.getRssFeedItems(websiteConfig.getRssItems()));

    }

}
