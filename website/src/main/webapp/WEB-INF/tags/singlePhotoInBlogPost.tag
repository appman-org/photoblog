<%@tag body-content="empty" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="photoViewDto" required="false" type="org.appman.photoblog.page.generic.dto.PhotoViewDto" %>


<div>
  <img class="img-responsive" src="${photoViewDto.imageUrl}" alt="${photoViewDto.altText}">
</div>



