<%@tag body-content="empty" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="commentsDto" required="true" type="org.appman.photoblog.page.generic.dto.CommentsDto" %>
<%@tag import="org.appman.photoblog.page.common.pageattributes.PageAttributes" %>
<div class="${PageAttributes.BLOCK_COMMENTS}">
<div id="disqus_thread"></div>
</div>
<script>


    var disqus_config = function () {
        this.page.url = '${commentsDto.pageUrl}';
        this.page.identifier = '${commentsDto.pageIdentifier}';
    };

    (function() {  // DON'T EDIT BELOW THIS LINE
        var d = document, s = d.createElement('script');

        s.src = '//${commentsDto.commentsAccountId}.disqus.com/embed.js';

        s.setAttribute('data-timestamp', +new Date());
        (d.head || d.body).appendChild(s);
    })();
</script>
<noscript>Please enable JavaScript to view the <a href="https://disqus.com/?ref_noscript" rel="nofollow">comments powered by Disqus.</a></noscript>