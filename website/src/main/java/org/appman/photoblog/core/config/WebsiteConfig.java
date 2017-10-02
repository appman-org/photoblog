package org.appman.photoblog.core.config;

import org.appman.photoblog.core.EnvironmentType;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Pieter on 5/28/2016.
 */
@Component
@Data
@ConfigurationProperties(prefix = "website")
public class WebsiteConfig {

    private boolean runLocal;
    private String baseUrl;
    private String baseTitle;
    private String description;
    private EnvironmentType environmentType;
    private String analyticsTrackingId;

    private int rssItems;

    private String disqusAcount;

    private String imageImportFolder;


    private ContentSource content;

    private String googleMapsApiKey;

    @Data
    public static class ContentSource{
        private Locations remote;
        private Locations local;
    }

    @Data
    public static class Locations{
        private String albumList;
        private String photoList;
        private String imageUrlPrefix;
        private String blogList;
        private String aboutPage;
    }


}
