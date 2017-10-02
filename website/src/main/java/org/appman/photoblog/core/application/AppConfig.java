package org.appman.photoblog.core.application;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Pieter on 10/18/2016.
 */
@Configuration
@ComponentScan("org.appman.photoblog")
public class AppConfig extends WebMvcConfigurerAdapter {

}
