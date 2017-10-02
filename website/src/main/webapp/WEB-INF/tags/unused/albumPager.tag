<%@tag body-content="empty" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="albumOverviewItemDto" required="false" type="org.appman.photoblog.page.album.dto.AlbumOverviewItemDto" %>
             <div class="row">
              <div class="col-sm-4">
                <ul class="pager">
                  <li><a href="#">Previous</a></li>
                </ul>
              </div>
              <div class="col-sm-4">
                    <ul class="pagination pagination text-center">
                        <li><a href="#">First</a></li>
                        <li><a href="#">1</a></li>
                        <li><a href="#">2</a></li>
                        <li><a href="#">3</a></li>
                        <li><a href="#">Last</a></li>
                    </ul>
              </div>
              <div class="col-sm-4">
                <ul class="pager">
                  <li><a href="#">Next</a></li>
                </ul>
              </div>
            </div>

