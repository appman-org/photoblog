<%@tag body-content="empty" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="se" tagdir="/WEB-INF/tags" %>
<p><a>iets met foto</a></p>

<div class="panel panel-default">
  <div class="panel-heading">Foto album</div>
  <div class="panel-body">
    <div class="row">
        <se:photoInAlbumPost/>
        <se:photoInAlbumPost/>
        <se:photoInAlbumPost/>
        <se:photoInAlbumPost/>
    </div>
  </div>
</div>

