<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="se" tagdir="/WEB-INF/tags" %>
<%@ page import="org.appman.photoblog.page.common.pageattributes.PageAttributes" %>



        <div class="${PageAttributes.MEDIALISTING_PAGE_CONTENT} ${pageViewDto.pageId}">
            <c:forEach items="${pageViewDto.mediaPostViewDtos}" var ="mediaPostViewDto">
                <div class="panel panel-default">

                      <div class="panel-heading"><a href="${mediaPostViewDto.url}" class="${PageAttributes.BLOG_OVERVIEW_POST_TITLE}"><h4>${mediaPostViewDto.title}</h4></a></div>
                      <div class="panel-body">
                        <div class="row">
                           <c:forEach end="3" items="${mediaPostViewDto.albumPhotos}" var ="albumPhoto">
                                <a href="${mediaPostViewDto.url}">
                                    <se:photoInAlbumPost photoViewDto = "${albumPhoto}"/>
                                </a>
                            </c:forEach>

                        </div>
                        <div class="row">
                            <c:if test="${mediaPostViewDto.singlePhoto != null}">
                                <div class="col-md-5 col-sm-6 col-xs-12">
                                    <a href="${mediaPostViewDto.url}">
                                        <se:singlePhotoInBlogPost photoViewDto = "${mediaPostViewDto.singlePhoto}"/>
                                    </a>
                                </div>
                            </c:if>
                            <c:if test="${mediaPostViewDto.shortBodyText != null}">
                                <div class="col-md-7 col-sm-6 col-xs-12">
                                    <p>${mediaPostViewDto.shortBodyText}
                                        <c:if test="${mediaPostViewDto.hasShowMoreButton}">
                                            <a href="${mediaPostViewDto.url}">(continue)</a>
                                        </c:if>
                                    </p>
                                </div>
                            </c:if>
                        </div>
                        <p></p>
                        <p>${blogPostViewDto.publishDate}</p>
                      </div>

                </div>
                <!-- <se:albumThumbnail photoViewDto = "${photoViewDto}"/> -->
            </c:forEach>

            <se:pagination paginationViewDto = "${pageViewDto.paginationViewDto}"/>
        </div>