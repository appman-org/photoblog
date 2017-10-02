<%@tag body-content="empty" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="breadcrumbViewDto" required="false" type="org.appman.photoblog.page.common.dto.BreadcrumbViewDto" %>
<%@tag import="org.appman.photoblog.page.common.pageattributes.PageAttributes" %>
<ol class="breadcrumb ${PageAttributes.BLOCK_BREADCRUMB}">
    <c:forEach end="${breadcrumbViewDto.breadcrumbItemViewDtos.size()-2}" items="${breadcrumbViewDto.breadcrumbItemViewDtos}" var ="breadcrumbItemViewDto">
        <li><a href="${breadcrumbItemViewDto.url}">${breadcrumbItemViewDto.title}</a></li>
    </c:forEach>
    <c:forEach begin="${breadcrumbViewDto.breadcrumbItemViewDtos.size()-1}" items="${breadcrumbViewDto.breadcrumbItemViewDtos}" var ="breadcrumbItemViewDto">
        <li class="active">${breadcrumbItemViewDto.title}</li>
    </c:forEach>

</ol>