<%@tag body-content="empty" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="photoViewDto" required="false" type="org.appman.photoblog.page.generic.dto.PhotoViewDto" %>
<div class="col-md-3 col-sm-4 col-xs-6">

<div class="centered-image cropped" style="background-image: url(${photoViewDto.imageUrl});">
  <img src="${photoViewDto.imageUrl}" width="200" height="200" alt="${photoViewDto.altText}">
</div>
<p></p>



  </div>