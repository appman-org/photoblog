<!DOCTYPE html>
<%@ taglib prefix="se" tagdir="/WEB-INF/tags" %>
<%@ page import="org.appman.photoblog.page.common.pageattributes.PageAttributes" %>
<html lang="en">
<se:head pageHeadDataDto = "${pageViewDto.pageHeadDataDto}"/>
<body>
    <div class="container">
        <%@include file="common/header.jsp"%>
        <jsp:include page="page/${pageViewDto.pageType}.jsp" />

    </div>


<%@include file="common/footer.jsp"%>
</body>

</html>