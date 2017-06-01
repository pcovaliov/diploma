<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
</head>


<body style="overflow-y: scroll;">

<jsp:include page="fragments/top.jsp"/>
<div class="container text-center">

    <div class="col-md-8 col-md-offset-2 abc" style="border-top-color: red" style="margin-top: 50px;">
        <form:form method="POST" action="/saveProject" modelAttribute="project" class="form-signin">
            <div class="form-group" >
                <label for="projectName">Project name</label>
                <form:input type="text" path="projectName" class="form-control" id="projectName" placeholder="Project Name"
                            maxlength="50" minlength="3"/>
                <c:set var="domainNameErrors"><form:errors path="projectName"/></c:set>
                <c:if test="${empty domainNameErrors}">
                    <small id="emailHelp" class="form-text text-muted">Project Name.</small>
                </c:if>
                <p style="color: red" class="form-text text-muted"><form:errors path="projectName"/></p>

            </div>
            <div class="form-group">
                <label for="projectShortName">Project Short Name</label>
                <form:input type="text" path="shortName" class="form-control" id="projectShortName" placeholder="Project Short Name"
                            maxlength="3" minlength="3"/>
                <c:set var="domainNameErrors"><form:errors path="shortName"/></c:set>
                <c:if test="${empty domainNameErrors}">
                    <small id="emailHelp" class="form-text text-muted">Lenght should be 3.</small>
                </c:if>
                <p style="color: red" class="form-text text-muted"><form:errors path="shortName"/></p>

            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
            <form:input type="hidden" path="id" name="id" value="${project.id}"/>
        </form:form>
    </div>
</div>
</body>
</html>

