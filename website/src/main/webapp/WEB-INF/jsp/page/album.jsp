<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="se" tagdir="/WEB-INF/tags" %>
<%@ page import="org.appman.photoblog.page.common.pageattributes.PageAttributes" %>

            <div class="${PageAttributes.ALBUM_CONTENT}">
                <h3>${pageViewDto.albumName}</h3>
                <div class="row">
                    <c:forEach items="${pageViewDto.photoViewDtoList}" var ="photoViewDto">
                            <se:albumThumbnail photoViewDto = "${photoViewDto}"/>
                        </c:forEach>

                </div>
                <div class="map-on-album-page">
                    <se:map/>
                </div>

                <se:commentsTag commentsDto = "${pageViewDto.commentsDto}"/>
                <se:breadCrumb breadcrumbViewDto = "${pageViewDto.breadcrumbViewDto}"/>
            </div>
