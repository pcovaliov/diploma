<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>User Profile</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/sweetalert2/6.1.1/sweetalert2.css">
    <style type="text/css">
        <%@include file="../resources/css/card.css"%>
        <%@include file="../resources/css/profile.css"%>
        <%@include file="../resources/css/bootstrap.css"%>
        <%@include file="../resources/css/style.css"%>
        .form-control {
            padding: 0px;
        }

    </style>
    <script src="https://cdn.jsdelivr.net/sweetalert2/6.1.1/sweetalert2.min.js"></script>
    <script src="https://cdn.jsdelivr.net/sweetalert2/6.1.1/sweetalert2.js"></script>
    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
    <script>
        $(document).ready(function () {
            $(".nav-tabs a").click(function () {
                $(this).tab('show');
            });
        });
    </script>
    <script type="text/javascript">
        $(document).ready(function () {

            $(window).scroll(function () {
                if ($(this).scrollTop() > 100) {
                    $('.scrollup').fadeIn();
                } else {
                    $('.scrollup').fadeOut();
                }
            });

            $('.scrollup').click(function () {
                $("html, body").animate({scrollTop: 0}, 600);
                return false;
            });

        });
    </script>

    <script>
        function confirmDelete(delForm, delUrl) { // <--- changed here
            if (confirm("Are you sure ?")) {
                delForm.action = delUrl;          // <--- changed here
                return true;                      // <--- changed here
            }
            return false;                         // <--- changed here
        }
    </script>
</head>
<style>
    .shirina {
        width: 33%;
        valign: top;
        padding: 10px;
        background-color: #eeeeee;
    }


</style>

<body style="overflow-y: scroll;background-color: #eeeeee;">
<jsp:include page="fragments/top.jsp"/>

<div class="container text-center abc">
    <div class="row">
        <%--User's left bar--%>
        <div class="col-sm-3 well" style="background-color: #71c3ec;" id="leftPanel">
            <a style="color: whitesmoke" href="/user/profile/settings"><span
                    class="pull-right btn btn-warning btn-xs glyphicon glyphicon-cog"></span></a>

            <div class="row">
                <div class="col-md-12" style="margin: 0 auto;">
                    <div>
                        <img src="${currentUser.avatar}"
                             alt="User's avatar" class="img-circle" height="150" width="150">
                        <br>
                        <br>

                        <p>
                            <small class="status_name">@${currentUser.username.toUpperCase()}</small>
                        </p>
                        <h4 class="status_name">${currentUser.first_name} ${currentUser.last_name}</h4>

                        <%--<p class="status_name">${currentUser.description}</p>--%>

                        <div class="twPc-divStats">
                            <ul class="twPc-Arrange">
                                <li class="twPc-ArrangeSizeFit">
                                    <span class="twPc-StatLabel twPc-block status_name"><spring:message
                                            code='tasks'/></span>
                                    <span class="twPc-StatValue status_name">${tasks.size()}</span>
                                </li>
                                <li class="twPc-ArrangeSizeFit">
                                    <span class="twPc-StatLabel twPc-block status_name"><spring:message
                                            code='projects'/></span>
                                    <span class="twPc-StatValue status_name">${projects.size()}</span>
                                </li>
                                <li class="twPc-ArrangeSizeFit">
                                    <span class="twPc-StatLabel twPc-block status_name"><spring:message
                                            code='periods'/></span>
                                    <span class="twPc-StatValue status_name">${periods.size()}</span>
                                </li>
                            </ul>
                            <br>
                            <ul class="twPc-Arrange">
                                <li><button type="button" class="btn btn-success pull-left " style="padding: 8px 20px;"
                                            onclick="location.href='/user/project/createProject'">
                                    <spring:message code='newproject'/></button></li>
                                <li><button type="button" class="btn btn-success pull-right" style="padding: 8px 20px;"
                                            onclick="location.href='/user/profile/createPeriod'">
                                    <spring:message code='newperiod'/></button></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <ul class="nav nav-tabs  col-sm-6">
            <li class="active"><a href="#projects"><spring:message code='projects'/></a></li>
            <li><a href="#periods"><spring:message code='periods'/></a></li>
        </ul>

        <div class="tab-content">
            <div id="projects" class="tab-pane fade in active">

                <!-- MIDDLE PART -->
                <div class="col-sm-6"><br>
                    <table class="table table-striped">
                        <tr class="info">
                            <td><spring:message code='id'/></td>
                            <td><spring:message code='projectname'/></td>
                            <td><spring:message code='shortname'/></td>
                            <td><spring:message code='actions'/></td>
                        </tr>
                        <div id="project-display-id">
                            <c:forEach items="${projects}" var="project" varStatus="status">
                                <tr>
                                    <td> ${project.id}</td>
                                    <td> ${project.projectName}</td>
                                    <td> ${project.shortName}</td>
                                    <td>
                                        <button onsubmit="return confirmDelete(this, '/user/project/editProject/${project.id}')"
                                                onclick="location.href='/user/project/editProject/${project.id}';"
                                                type="button" class="btn btn-info"><spring:message
                                                code='editproject'/></button>
                                        <%--<button onclick="location.href='/user/profile/deleteProject/${project.id}';"--%>
                                                <%--type="button" class="btn btn-danger"><spring:message--%>
                                                <%--code='deleteproject'/></button>--%>
                                        <jsp:include page="js-modal.jsp">
                                            <jsp:param name="id" value="${project.id}"/>
                                            <jsp:param name="del" value="/user/profile/deleteProject/"/>
                                            <jsp:param name="action" value="period ${project.projectName}"/>
                                        </jsp:include>
                                        <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#myModal${project.id}"><spring:message
                                                code='deleteproject'/></button>

                                    </td>
                                </tr>
                            </c:forEach>
                        </div>
                    </table>
                </div>
            </div>

            <div id="periods" class="tab-pane fade">
                <%--<h3>Following</h3>--%>
                <div class="col-sm-9" style="text-align: center;">
                    <div id="refresh">
                        <br>

                        <!-- MIDDLE PART -->
                        <div class="col-sm-10"><br>
                            <table class="table table-striped">
                                <tr class="info">
                                    <td><spring:message code='id'/></td>
                                    <td><spring:message code='periodname'/></td>
                                    <td><spring:message code='startdate'/></td>
                                    <td><spring:message code='enddate'/></td>
                                    <td><spring:message code='actions'/></td>
                                </tr>
                                <div id="period-display-id">
                                    <c:forEach items="${periods}" var="period" varStatus="status">
                                        <tr>
                                            <td> ${period.id}</td>
                                            <td> ${period.periodName}</td>
                                            <td> <fmt:formatDate value="${period.startDate}" pattern="MM.dd.yyyy"/> </td>
                                            <td> <fmt:formatDate value="${period.endDate}" pattern="MM.dd.yyyy"/></td>
                                            <td>
                                                <button onclick="location.href='/user/profile/editPeriod/${period.id}';"
                                                        type="button" class="btn btn-info"><spring:message
                                                        code='editproject'/></button>
                                                <%--<button onclick="location.href='/user/profile/deletePeriod/${period.id}';"--%>
                                                        <%--type="button" class="btn btn-danger"><spring:message--%>
                                                        <%--code='deleteproject'/></button>--%>
                                                <jsp:include page="js-modal.jsp">
                                                    <jsp:param name="id" value="${period.id}"/>
                                                    <jsp:param name="del" value="/user/profile/deletePeriod/"/>
                                                    <jsp:param name="action" value="period ${period.periodName}"/>
                                                </jsp:include>
                                                <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#myModal${period.id}"><spring:message
                                                        code='deleteproject'/></button>
                                            </td>
                                        </tr>

                                    </c:forEach>
                                    <%--<jsp:include page="modal.jsp"/>--%>
                                </div>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div id="menu2" class="tab-pane fade">
                <%--<h3>Followers</h3>--%>
                <div class="col-sm-9" style="text-align: center;">
                    <div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


    <%--<script>--%>
        <%--&lt;%&ndash;REMEMBER CURRENT TAB&ndash;%&gt;--%>
        <%--$(document).ready(function () {--%>
            <%--if (location.hash) {--%>
                <%--$('a[href=' + location.hash + ']').tab('show');--%>
            <%--}--%>
            <%--$(document.body).on("click", "a[data-toggle]", function (event) {--%>
                <%--location.hash = this.getAttribute("href");--%>
            <%--});--%>
        <%--});--%>
        <%--$(window).on('popstate', function () {--%>
            <%--var anchor = location.hash || $("a[data-toggle=tab]").first().attr("href");--%>
            <%--$('a[href=' + anchor + ']').tab('show');--%>
        <%--});--%>

        <%--function sweetUnf(username) {--%>
<%--//            var username = $(this).data('unflw');--%>
            <%--swal({--%>
                <%--title: 'Are you sure?',--%>
                <%--text: "You won't be able to revert this!",--%>
                <%--type: 'warning',--%>
                <%--showCancelButton: true,--%>
                <%--confirmButtonColor: '#3085d6',--%>
                <%--cancelButtonColor: '#d33',--%>
                <%--confirmButtonText: 'Yes, unfollow this user!'--%>

            <%--}).then(function () {--%>
                <%--$.ajax({--%>
                    <%--url: '/unfollow/' + username,--%>
                    <%--data: username,--%>
                    <%--dataType: 'text',--%>
                    <%--type: 'GET',--%>
                    <%--success: function (data) {--%>
                        <%--$('#' + username).hide();--%>
                        <%--$('#refresh').load(document.URL + ' #refresh');--%>
                    <%--}--%>
                <%--});--%>
            <%--})--%>
        <%--}--%>

        <%--function sweetDelTwit(id) {--%>
            <%--swal({--%>
                <%--title: 'Are you sure?',--%>
                <%--text: "You won't be able to revert this!",--%>
                <%--type: 'warning',--%>
                <%--showCancelButton: true,--%>
                <%--confirmButtonColor: '#3085d6',--%>
                <%--cancelButtonColor: '#d33',--%>
                <%--confirmButtonText: 'Yes, delete it!'--%>
            <%--}).then(function () {--%>

                <%--location.href = '/tweet/deleteTweet/' + id;--%>

            <%--})--%>
        <%--}--%>
    <%--</script>--%>
    <%--<script>--%>

    <%--</script>--%>
    <%--<a href="#" class="scrollup">Наверх</a>--%>
</body>
</html>

