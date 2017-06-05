<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!-- Modal -->
<%--<div id="myModal" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title" id="myModalLabel">@${t.user.username}</h4>
            </div>
            <form action="/tweet/${t.id}/addComment" method="post">
                <div class="modal-body">
                    <p>${t.text}</p>
                       <textarea name="text" class="form-control" placeholder="Enter here for tweet..."
                                 rows="5"></textarea>
                    <br>
                </div>
                <div class="modal-footer">
                    <input type="submit" class="btn btn-primary btn-sm pull-right" value="Comment"/>
                    <button type="button" class="btn btn-primary btn-sm pull-right" data-dismiss="modal">Close
                    </button>
                </div>
            </form>
            <div class="clearfix"></div>
            <hr/>
            <ul class="media-list">
                <c:forEach items="${comments}" var="comment">
                    <li class="media">
                        <a href="#" class="pull-left">
                            <img src="${user.avatar}"
                                 alt="" class="img-circle">
                        </a>
                        <div class="media-body">
                                       <span class="text-muted pull-right">
                                           <small class="text-muted">${comment.postDateTime.toLocalDate()} ${t.postDateTime.toLocalTime().withNano(0)}</small>
                                       </span>
                            <strong class="text-success">@ ${comment.user.username}</strong>
                            <div>${comment.text}</div>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>--%>
<!-- MODAL -->

<!-- Modal -->
<div class="modal fade" id="myModal<%=request.getParameter("id")%>" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Confirmation of deletion</h4>
            </div>
            <div class="modal-body">
                <p>Are you shore you want to delete <%=request.getParameter("action")%>?</p>
            </div>
            <div class="modal-footer">
                <button onclick="location.href='<%=request.getParameter("del")%><%=request.getParameter("id")%>';"
                type="button" class="btn btn-danger"><spring:message
                code='deleteproject'/></button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>

    </div>
</div>
