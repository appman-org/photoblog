<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="se" tagdir="/WEB-INF/tags" %>
<%@ page import="org.appman.photoblog.page.common.pageattributes.PageAttributes" %>
<c:set var="mapPageViewDto" value="${pageViewDto}"/>
<div class="${PageAttributes.MAP_PAGE_CONTENT}">
    <div class="map-on-map-page">
        <se:map/>
    </div>
    <div>
        <p class="text-center">Filter per year: <a href="/map">All</a>
        <c:forEach items="${mapPageViewDto.paginationViewDto.paginationItemViewDtos}" var ="paginationItemViewDto">
            - <a href="${paginationItemViewDto.url}">${paginationItemViewDto.title}</a>
        </c:forEach>
        </p>
    </div>
    <se:breadCrumb breadcrumbViewDto = "${pageViewDto.breadcrumbViewDto}"/>
</div>
