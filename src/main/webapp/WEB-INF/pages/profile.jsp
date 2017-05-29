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
        <%@include file="../resources/css/profile.css"%>
        <%@include file="../resources/css/bootstrap.css"%>
        <%@include file="../resources/css/style.css"%>
        <%@include file="../resources/css/card.css"%>
    </style>
    <style>
        body {
            background-color: #eeeeee;
        }
    </style>
    <script src="https://cdn.jsdelivr.net/sweetalert2/6.1.1/sweetalert2.min.js"></script>
    <script src="https://cdn.jsdelivr.net/sweetalert2/6.1.1/sweetalert2.js"></script>
    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>

    <script type="text/javascript">
        $(document).ready(function(){

            $(window).scroll(function(){
                if ($(this).scrollTop() > 100) {
                    $('.scrollup').fadeIn();
                } else {
                    $('.scrollup').fadeOut();
                }
            });

            $('.scrollup').click(function(){
                $("html, body").animate({ scrollTop: 0 }, 600);
                return false;
            });

        });
    </script>

</head>
<body style="overflow-y: scroll;">
<jsp:include page="fragments/top.jsp"/>

            TESTTEST<br/>
            TESTTEST<br/>
            TESTTEST<br/>
            TESTTEST<br/>
            TESTTEST<br/>
            TESTTEST<br/>
            TESTTEST<br/>
            TESTTEST<br/>
            TESTTEST<br/>
            TESTTEST<br/>
            TESTTEST<br/>
            TESTTEST<br/>
            TESTTEST<br/>
            TESTTEST<br/>
            TESTTEST<br/>
    </div>
</div>

<script>
    function follow() {
        var username = $(document.getElementById("flw")).val();
        swal({
            title: 'Do you want to follow this user?',
            type: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, follow this user!'

        }).then(function () {
            $.ajax({
                url: '/addfollower/' + username,
                dataType: 'text',
                type: 'GET'
            });
            location.reload();
        })
    }

    function sweetUnf() {
        var id = $(document.getElementById("unflw")).val();
        swal({
            title: 'Are you sure?',
            type: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, unfollow this user!'

        }).then(function () {
            $.ajax({
                url: '/unfollow/' + id,
                data: id,
                dataType: 'text',
                type: 'GET'
            });
            location.reload().fadeIn('fast');
        })
    }
</script>
</body>
</html>

