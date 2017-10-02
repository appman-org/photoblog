<%@tag body-content="empty" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="albumOverviewItemDto" required="false" type="org.appman.photoblog.page.album.dto.AlbumOverviewItemDto" %>
<%@tag import="org.appman.photoblog.page.common.pageattributes.PageAttributes" %>
<div class="row">
    <div class="col-sm-4">
        <a href="${albumOverviewItemDto.albumUrl}">
                <img class="img-responsive ${PageAttributes.ALBUM_OVERVIEW_THUMBNAIL}" src="${albumOverviewItemDto.thumbnail.imageUrl}" alt="${albumOverviewItemDto.thumbnail.altText}"/>
            </a>
            <p></p>
    </div>
    <div class="col-sm-8">
        <p><a href="${albumOverviewItemDto.albumUrl}">${albumOverviewItemDto.albumName}</a></p>
    </div>
</div>

