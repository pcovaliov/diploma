<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%--MODAL CSS--%>
<div id="myModal" class="mmodalDialog">
    <div>
        <div class="media-body">
            <a href="${pageContext.request.contextPath}/user/profile/" title="<spring:message code='close'/>"
               class="cclose"
               style="text-decoration: none">x</a>

            <form action="/user/profile/edittask/${id}" method="post">
                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
                <input type="hidden" id="id" name="id"/>
                                                        <span class="reply_text"
                                                              style="text-align: left; font-weight: bold">${t.user.username}</span>
                <section id="editable" contenteditable="true">
                                                    <textarea id="task" maxlength=140; rows="3"
                                                              style="font-size:large; border-color: steelblue; overflow: hidden; height: auto;
                                                              box-shadow: none; resize: none; padding: 10px"
                                                              name="text" class="form-control"
                                                              required>${thisTask.text}</textarea>
                </section>
                <p></p>
                <input type="hidden" id="tweet-id" value="${thisTweet.id}">
                <c:if test="${thisTask.image!=null}">
                    <c:if test="${!thisTask.image.equals('')}">
                    <span width="80%" class="pull-right" style="padding-bottom: 10px">
                    <img width="100%" src="<c:url value="/attachemnts/" />${thisTask.image}">
                    </span>
                    </c:if>
                </c:if>
                <p></p>

                <div>
                    <button type="submit" value="Save"
                            class="btn btn-warning btn-sm pull-right"><spring:message code='save_changes'/>
                    </button>
                </div>
                <br>
            </form>
        </div>
    </div>
</div>
