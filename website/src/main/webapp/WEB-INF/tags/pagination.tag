<%@tag body-content="empty" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="paginationViewDto" required="true" type="org.appman.photoblog.page.generic.dto.PaginationViewDto" %>
<%@tag import="org.appman.photoblog.page.common.pageattributes.PageAttributes" %>
<div class="text-center ${PageAttributes.PAGINATION_BLOCK}">
    <ul class="pagination">
        <c:forEach items="${paginationViewDto.paginationItemViewDtos}" var ="paginationItemViewDto">

            <c:choose>
                <c:when test="${paginationItemViewDto.active}">
                    <li class="active ${PageAttributes.PAGINATION_ITEM}"><a>${paginationItemViewDto.title}</a></li>
                </c:when>
                <c:otherwise>
                    <li class="${PageAttributes.PAGINATION_ITEM}"><a href="${paginationItemViewDto.url}">${paginationItemViewDto.title}</a></li>
                </c:otherwise>
            </c:choose>


        </c:forEach>



    </ul>
</div>
