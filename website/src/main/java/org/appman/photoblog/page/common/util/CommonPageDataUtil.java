package org.appman.photoblog.page.common.util;

import org.appman.photoblog.page.common.dto.BreadcrumbViewDto;

/**
 * Created by Pieter on 10/11/2016.
 */
final public class CommonPageDataUtil {

    private CommonPageDataUtil(){}

    public static BreadcrumbViewDto getBaseBreadCrumbViewDto() {
        BreadcrumbViewDto result = new BreadcrumbViewDto();
        result.addBreadcrumbItem("Home", "/");
        return result;
    }


}
