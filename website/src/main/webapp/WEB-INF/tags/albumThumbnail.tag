<%@tag body-content="empty" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="photoViewDto" required="false" type="org.appman.photoblog.page.generic.dto.PhotoViewDto" %>
<%@tag import="org.appman.photoblog.page.common.pageattributes.PageAttributes" %>
<div class="col-md-3 col-sm-4 col-xs-6">
    <a class="albumthumbnail" href="${photoViewDto.photoPageUrl}">
        <img class="img-responsive ${PageAttributes.ALBUM_THUMBNAIL}" src="${photoViewDto.imageUrl}" alt="${photoViewDto.altText}"/>
    </a>
    <p></p>
</div>