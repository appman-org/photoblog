<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="se" tagdir="/WEB-INF/tags" %>
<%@ page import="org.appman.photoblog.page.common.pageattributes.PageAttributes" %>

    <div class="${PageAttributes.TEXT_POST_CONTENT}">
        <h1>${pageViewDto.title}</h1>
        <div class="row">
            <div class="col-md-2 col-sm-1 col-xs-0"></div>
            <div class="col-md-8 col-sm-10 col-xs-12">
                <img class="img-responsive center-block" src="${ pageViewDto.singlePhoto.imageUrl }" alt="${ pageViewDto.singlePhoto.altText }"/>
            </div>
            <div class="col-md-2 col-sm-1 col-xs-0"></div>
        </div>
        <div class="${PageAttributes.TEXT_POST_FULL_TEXT}">
            <p>${pageViewDto.bodyText}</p>
        </div>
        <se:commentsTag commentsDto = "${pageViewDto.commentsDto}"/>
    </div>
