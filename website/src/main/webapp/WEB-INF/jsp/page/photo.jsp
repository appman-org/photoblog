<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="se" tagdir="/WEB-INF/tags" %>
<%@ page import="org.appman.photoblog.page.common.pageattributes.PageAttributes" %>
<c:set var="photoPageDto" value="${pageViewDto}"/>
<c:set var="currentPhoto" value="${photoPageDto.currentPhoto}"/>
<c:set var="previousPhoto" value="${photoPageDto.previousPhoto}"/>
<c:set var="nextPhoto" value="${photoPageDto.nextPhoto}"/>


    <div class="${PageAttributes.PHOTO_CONTENT}">
    <img class="img-responsive center-block ${PageAttributes.PHOTO_FULL_IMAGE}" src="${ currentPhoto.imageUrl }" alt="${ currentPhoto.altText }"/>

    <div class="row">
        <div class ="center-block">
            <c:if test="${photoPageDto.showPreviousLink}">

                <a class="left" href="${ previousPhoto.photoPageUrl }" >
                    <span class="glyphicon glyphicon-chevron-left btn-lg"></span>
                </a>
            </button>
            </c:if>

            <c:if test="${photoPageDto.showNextLink}">
                <a class="right" href="${ nextPhoto.photoPageUrl }">
                    <span class="glyphicon glyphicon-chevron-right btn-lg"></span>
                </a>
            </c:if>
            <c:if test="${currentPhoto.hasDescription}">
                <p>${ currentPhoto.description }</p>
            </c:if>
        </div>
    </div>
    <p>(use the arrow keys on your keyboard!)</p>
    <c:if test="${currentPhoto.latitude != null}">
        <div class="row">
            <a href="https://www.google.com/maps?z=18&q=${currentPhoto.latitude},${currentPhoto.longitude}">
                <img class="img-responsive center-block" src="http://maps.google.com/maps/api/staticmap?center=${currentPhoto.latitude},${currentPhoto.longitude}&zoom=8&size=200x120&sensor=false" />
            </a>
        </div>
    </c:if>

    <se:commentsTag commentsDto = "${photoPageDto.commentsDto}"/>

    <se:breadCrumb breadcrumbViewDto = "${photoPageDto.breadcrumbViewDto}"/>

<script>
$(document).keydown(function(e){
  if (e.keyCode == 37) {
    e.preventDefault();
    <c:if test="${photoPageDto.showPreviousLink}">
        window.location.href = "${ previousPhoto.photoPageUrl }";
    </c:if>
  } else if (e.keyCode == 39) {
    e.preventDefault();
    <c:if test="${photoPageDto.showNextLink}">
        window.location.href = "${ nextPhoto.photoPageUrl }";
    </c:if>
  }
});
</script>

