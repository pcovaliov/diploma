<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Task Manager</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <%--<link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <link rel="stylesheet" href="/resources/css/profile.css">
    <link rel="stylesheet" href="/resources/css/style.css">
    <link rel="stylesheet" href="/resources/css/card.css">
    <%--&lt;%&ndash;<link rel="stylesheet" href="/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css">&ndash;%&gt;--%>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.js"></script>
    <script src="http://malsup.github.com/jquery.form.js"></script>
    <script src="http://code.jquery.com/jquery-1.11.1.js"></script>


    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/sweetalert2/6.1.1/sweetalert2.css">
    <style type="text/css">
        <%@include file="../resources/css/card.css"%>
        <%@include file="../resources/css/profile.css"%>
        <%@include file="../resources/css/bootstrap.css"%>
        <%@include file="../resources/css/style.css"%>

    </style>


    <style>
        /*.texBox{
        display: block;
        word-wrap: break-word;
        overflow-y: scroll;
        height: 40px;
        margin: 0 5% 0 5%;
        }*/

        .modal-open {
            padding-right: 0 !important;
        }

        .address_hover { background-color: lightgrey; }

        .address_hover:hover { background-color: #bfbfbf; }
    </style>

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

        function simulateKeyPress(character) {
            jQuery.event.trigger({type: 'keypress', which: character.charCodeAt(0)});
        }
    </script>

    <script>
        $(document).ready(function () {
            $('.liking').on('click', function () { //bind click handler
                var data_id = $(this).data('like');
                var data_usr = $(this).data('like2');
                var thisButt = $(this);
                $.ajax({
                    type: 'POST',
                    url: '/addLike',
                    data: {tweet_id: data_id, user_id: data_usr},
                    success: function (data) {
                        if (data === true) {
                            return;
                        }
                        thisButt.attr("disabled", true);
                        var current = parseInt(thisButt.siblings(".likeCount").text()) + 1;
                        thisButt.siblings(".likeCount").html(current);
                    }
                });
            })
        });
        function commentAdd() {
            $('.spec').keydown(function (e) {
                if (e.keyCode == 13) {
                    var data_id = $(this).data('commid');
                    var data_text = $(this).val();
                    var thisThis = $(this);
                    if ((data_text.trim(data_text)).length > 1) {
                        $.ajax({
                            type: 'POST',
                            url: '/addComment',
                            data: {id: data_id, text: data_text},
                            success: function (data) {
                                thisThis.val('');
                                thisThis.closest('.parent').find('.fake').click();
                            }
                        });
                    } else {
                        alert('Empty comment!');
                    }
                }
            });
        }
        ;

        $(document).ready(function () {

            commentAdd();

        });


    </script>
    <script>
        function insertAtCaret(areaId, text) {
            var txtarea = document.getElementById(areaId);
            if (!txtarea) {
                return;
            }

            var scrollPos = txtarea.scrollTop;
            var strPos = 0;
            var br = ((txtarea.selectionStart || txtarea.selectionStart == '0') ?
                    "ff" : (document.selection ? "ie" : false ) );
            if (br == "ie") {
                txtarea.focus();
                var range = document.selection.createRange();
                range.moveStart('character', -txtarea.value.length);
                strPos = range.text.length;
            } else if (br == "ff") {
                strPos = txtarea.selectionStart;
            }

            var front = (txtarea.value).substring(0, strPos);
            var back = (txtarea.value).substring(strPos, txtarea.value.length);
            txtarea.value = front + text + back;
            strPos = strPos + text.length;
            if (br == "ie") {
                txtarea.focus();
                var ieRange = document.selection.createRange();
                ieRange.moveStart('character', -txtarea.value.length);
                ieRange.moveStart('character', strPos);
                ieRange.moveEnd('character', 0);
                ieRange.select();
            } else if (br == "ff") {
                txtarea.selectionStart = strPos;
                txtarea.selectionEnd = strPos;
                txtarea.focus();
            }

            txtarea.scrollTop = scrollPos;
        }
    </script>

    <style>
        .image-upload > input {
            display: none;
        }

        body {
            background-color: #eeeeee;
        }
    </style>

</head>
<body style="overflow-y: scroll;">

<jsp:include page="fragments/top.jsp"/>
<div class="container text-center" >

    <!-- MIDDLE PART -->
    <div class="col-sm-18 abc" style="margin-top: 52px;">
        <br>
        <form:form method="POST" action="/sortTask" class="form-signin" modelAttribute="projects" enctype="multipart/form-data">
            <div class="form-group">
                <label for="projectList">Sort by Project</label>
                <form:select class="form-control" id="projectList" path="projectName">
                    <form:option value="Show by all Projects"/>
                    <form:options items="${projectList}" itemValue="projectName" itemLabel="projectName"/>
                </form:select>
                <p style="color: red" class="form-text text-muted"><form:errors path="projectName"/></p>
                <button type="submit" class="btn btn-primary" style="padding: 7px 80px;">Sort</button>
            </div>
        </form:form>
        <div class="row">
            <div class="col-sm-3" style="background-color:lavender;">
                <div style="padding: 20px; color: maroon; font-weight: bold;">TASKS IN TODO</div>

                <c:forEach var="todoItem" items="${todo}">
                    <c:if test="${todoItem.status.id == 1}">
                        <jsp:include page="js-modal2.jsp">
                            <jsp:param name="id" value="${todoItem.id}"/>
                            <jsp:param name="taskName" value="${todoItem.name}"/>
                            <jsp:param name="projectName" value="${todoItem.project.projectName}"/>
                            <jsp:param name="periodEnd" value="${todoItem.period.endDate}"/>
                            <jsp:param name="periodStart" value="${todoItem.period.startDate}"/>
                            <jsp:param name="status" value="${todoItem.status.description}"/>
                            <jsp:param name="description" value="${todoItem.text}"/>
                        </jsp:include>
                        <div id="${todoItem.id}" href="#taskModal${todoItem.id}" class="" data-toggle="modal">
                            <address class="address_hover">
                                <br/>
                                <h4><strong>${todoItem.name}</strong></h4>
                                <form:form method="POST" action="/change/startProgress/${todoItem.id}">
                                    <input hidden name="currentProject" value="${currentProject}">
                                    <input hidden name="taskId" value="${todoItem.id}">
                                    <button style="padding: 7px 15px;" type="submit" class="btn btn-success">Start progress</button>
                                </form:form>
                                <br/>
                            </address>

                        </div>
                    </c:if>
                </c:forEach>
            </div>
            <div class="col-sm-3" style="background-color:lavender;">
                <div style="padding: 20px; color: maroon; font-weight: bold;">TASKS IN PROGRESS</div>
                <c:forEach var="todoItem" items="${todo}">
                    <c:if test="${todoItem.status.id == 2}">
                        <jsp:include page="js-modal2.jsp">
                            <jsp:param name="id" value="${todoItem.id}"/>
                            <jsp:param name="taskName" value="${todoItem.name}"/>
                            <jsp:param name="projectName" value="${todoItem.project.projectName}"/>
                            <jsp:param name="periodEnd" value="${todoItem.period.endDate}"/>
                            <jsp:param name="periodStart" value="${todoItem.period.startDate}"/>
                            <jsp:param name="status" value="${todoItem.status.description}"/>
                            <jsp:param name="description" value="${todoItem.text}"/>
                        </jsp:include>
                        <div id="${todoItem.id}" href="#taskModal${todoItem.id}" class="" data-toggle="modal">
                            <address class="address_hover">
                                <br/>
                                <h4><strong>${todoItem.name}</strong></h4>
                                <form:form method="POST" action="/change/moveToReview/${todoItem.id}">
                                    <input hidden name="currentProject" value="${currentProject}">
                                    <input hidden name="taskId" value="${todoItem.id}">
                                    <button style="padding: 7px 30px;" type="submit" class="btn btn-info">To review</button>
                                </form:form>
                                <br/>
                            </address>

                        </div>
                    </c:if>
                </c:forEach>
            </div>
            <div class="col-sm-3" style="background-color:lavender;">
                <div style="padding: 20px; color: maroon; font-weight: bold;">TASKS IN REVIEW</div>
                <c:forEach var="todoItem" items="${todo}">
                    <c:if test="${todoItem.status.id == 3}">
                        <jsp:include page="js-modal2.jsp">
                            <jsp:param name="id" value="${todoItem.id}"/>
                            <jsp:param name="taskName" value="${todoItem.name}"/>
                            <jsp:param name="projectName" value="${todoItem.project.projectName}"/>
                            <jsp:param name="periodEnd" value="${todoItem.period.endDate}"/>
                            <jsp:param name="periodStart" value="${todoItem.period.startDate}"/>
                            <jsp:param name="status" value="${todoItem.status.description}"/>
                            <jsp:param name="description" value="${todoItem.text}"/>
                        </jsp:include>
                        <div id="${todoItem.id}" href="#taskModal${todoItem.id}" class="" data-toggle="modal">
                            <address class="address_hover">
                                <br/>
                                <h4><strong>${todoItem.name}</strong></h4>
                                <form:form method="POST" action="/change/moveToDell/${todoItem.id}">
                                    <input hidden name="currentProject" value="${currentProject}">
                                    <input hidden name="taskId" value="${todoItem.id}">
                                    <button style="padding: 7px 40px;" type="submit" class="btn btn-warning">Close</button>
                                </form:form>
                                <br/>
                            </address>

                        </div>
                    </c:if>
                </c:forEach>
            </div>
            <div class="col-sm-3" style="background-color:lavender;">
                <div style="padding: 20px; color: maroon; font-weight: bold;">CLOSED TASKS</div>
                <c:forEach var="todoItem" items="${todo}">
                    <c:if test="${todoItem.status.id == 4}">
                        <jsp:include page="js-modal2.jsp">
                            <jsp:param name="id" value="${todoItem.id}"/>
                            <jsp:param name="taskName" value="${todoItem.name}"/>
                            <jsp:param name="projectName" value="${todoItem.project.projectName}"/>
                            <jsp:param name="periodEnd" value="${todoItem.period.endDate}"/>
                            <jsp:param name="periodStart" value="${todoItem.period.startDate}"/>
                            <jsp:param name="status" value="${todoItem.status.description}"/>
                            <jsp:param name="description" value="${todoItem.text}"/>
                        </jsp:include>
                        <div id="${todoItem.id}" href="#taskModal${todoItem.id}" class="" data-toggle="modal">
                            <address class="address_hover">
                                <br/>
                                <h4><strong>${todoItem.name}</strong></h4>
                                <form:form method="POST" action="/deleteTask/${todoItem.id}">
                                    <input hidden name="currentProject" value="${currentProject}">
                                    <input hidden name="taskId" value="${todoItem.id}">
                                    <button style="padding: 7px 40px;" type="submit" class="btn btn-danger">Delete</button>
                                </form:form>
                                <br/>
                            </address>

                        </div>
                    </c:if>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
</div>
</div>

<%--<a href="#taskModal" role="button" class="btn" data-toggle="modal">Launch demo modal</a>--%>
<%--<button type="button" data-toggle="modal" data-target="#myModal">Launch modal</button>--%>
<%--<div href="#taskModal" class="" data-toggle="modal">Launch demo modal</div>--%>

<%--<!-- Modal -->--%>
<%--<div class="modal fade" id="taskModal" role="dialog">--%>
    <%--<div class="modal-dialog">--%>

        <%--<!-- Modal content-->--%>
        <%--<div class="modal-content">--%>
            <%--<div class="modal-header">--%>
                <%--<button type="button" class="close" data-dismiss="modal">&times;</button>--%>
                <%--<h4 class="modal-title">Confirmation of deletion</h4>--%>
            <%--</div>--%>
            <%--<div class="modal-body">--%>
                <%--<p>Are you shore you want to delete</p>--%>
            <%--</div>--%>
            <%--<div class="modal-footer">--%>
                <%--<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>--%>
            <%--</div>--%>
        <%--</div>--%>

    <%--</div>--%>
<%--</div>--%>

<script>
    $(document).ready(function () {
        $('[data-toggle="tooltip"]').tooltip();
    });
</script>
<script>

    $('#tweet').keyup(function () {
        console.log(this.value.length);

        if (this.value.length > 139) {
            $('#addTweet').prop('disabled', true);
            return false;
        }
        else  $('#addTweet').prop('disabled', false);
        $("#counter").html((140 - this.value.length));
    });

</script>
<script>

    $('#file2').change(function () {
        if ((/\.(gif|jpg|jpeg|tiff|png)$/i).test($('#file2').val()) == true) {
            $('#file-button').click();
        }
        else {
            alert('Wrong image type!');
        }
    });

    function uploadFormData() {
        $('#result').html('');

        var oMyForm = new FormData();
        oMyForm.append("file", file2.files[0]);

        $.ajax({
            url: '/uploadFile',
            data: oMyForm,
            dataType: 'text',
            processData: false,
            contentType: false,
            type: 'POST',
            success: function (data) {
                $('#result2').attr('value', data);
                $('#file-image-container').html("<a class='pull-right' href='/tweet/'>" + 'X' + "</a>" + "<img src=" + "/attachemnts/" + data + " style=\"width:100%; padding-bottom:10px;\"/>");
                return false;
            }
        });
    }

</script>

<script>

    window.count = 1;
    var scroll = 0;
    window.onscroll = function (ev) {
        var maxcount = $(document.getElementById("page-count")).val();
        if (((window.innerHeight + window.scrollY) > document.body.offsetHeight - 500) && window.count * 25 < maxcount && window.scrollY > scroll + 200) {

            getNext(window.count);
            // you're at the bottom of the page
            window.count = window.count + 1;
            scroll = window.scrollY;
        }
    };


    function getMonthFromString(mon) {
        return new Date(Date.parse(mon + " 1, 2012")).getMonth() + 1
    }


    function getNext(count) {
        var userTable = $('#test-id');
        $.ajax({

            url: '/getnexttweets/' + count,
            dataType: 'json',

            success: function (data) {

                console.log("All data fetched ... ");

                var users = '';
                var email = '';
                var trHTML = '';

                for (var i = 0; i < data.length; i++) {
                    trHTML += '<div class="row">' +
                            '<div class="col-sm-12">' +
                            '<div class="well reply_wrap" style="text-align:left">' +
                            '<div class="col-sm-2 reply-image">' +
                            '<img src="' + data[i].user.avatar + '" class="reply-img" height="55" width="55" alt="Avatar">' +
                            '</div>' +
                            '<div class="reply_text">' +
                            '<input type="hidden" id="id" name="id">' +
                            '<span style="text-align: left; font-weight: bold">' +
                            '<a href="/user/profile/' + data[i].user.username + '"' +
                            'style="text-decoration: none">' + data[i].user.username + '</a>' +
                            '</span>' +
                            '<span class="reply_footer pull-right"><small>' +
                            data[i].postDateTime.year + '-' + getMonthFromString(data[i].postDateTime.month) + '-' + data[i].postDateTime.dayOfMonth +
                            ' ' + data[i].postDateTime.hour + ':' + data[i].postDateTime.minute + ':' + data[i].postDateTime.second + '</small></span>' +
                            '<br>' +
                            '<span>' +
                            '<p style="padding-bottom: 10px; padding-top: 10px; font-size: larger">' + data[i].text + '</p>' +
                            '</span>';
                    if (data[i].image != null) {
                        if (data[i].image = '') {
                            trHTML += '<img style="width: 100%; padding-bottom: 10px" width="100%"' +
                                    'src="<c:url value="/attachemnts/" />' + data[i].image + '">';
                        }
                    }

                    trHTML += '<article style="padding-bottom: 10px; border-bottom: 1px #777;">' +
                            '<span data-toggle="tooltip" data-placement="bottom" title="I like it!"' +
                            'onmouseover="style=\'color:maroon; font-size: large; border: none; background-color: #FFFFFF; border-color: #FFFFFF; cursor: pointer\'"' +

                            'onmouseout="style=\'color: rgba(219, 0, 0, 0.52); font-size: large; border: none; background-color: #FFFFFF; border-color: #FFFFFF\'"' +
                            'onmousedown="style=\'color: rgba(219, 0, 0, 0.52); font-size: large; border: none; background-color: #FFFFFF; border-color: #FFFFFF\'"' +
                            'onmouseup="style=\'color: rgba(219, 0, 0, 0.52); font-size: large; border: none; background-color: #FFFFFF; border-color: #FFFFFF; cursor: pointer\'"' +

                            'onclick="style=\'color: maroon; font-size: large; border: none; background-color: #FFFFFF; border-color: #FFFFFF\'"' +
                            'class="glyphicon glyphicon-heart liking like-btn "' +
                            'data-like="' + data[i].id + '"' +
                            'data-like2="' + '${currentUser.id}"' +
                            'style="font-size: large; color: rgba(219, 0, 0, 0.52); border: none; background-color: #FFFFFF; border-color: #FFFFFF; cursor: pointer;"></span>' +
                            '<span class="small muted likeCount">' + data[i].likes + '</span>' +
                            '</article>' +
                            '<spring:message code='show_comments' var="show_comments"/>' +
                            '<spring:message code='add_comment' var="add_comments"/>' +
                            '<div class="parent">' +
                            '<div style="padding-bottom: 10px" class="fake" data-comment="' + data[i].id + '"' +
                            'onclick="getComments(' + data[i].id + ',this)">' +
                            '<a class="small " href="javascript:void(0);">${show_comments}</a>' +
                            '</div>' +
                            '<div>' +
                            '<input class="form-control spec" placeholder="${add_comments}" maxlength="140" data-commid="' + data[i].id + '"name="text"/>' +
                            '</div>' +
                            '</div>' +
                            '</div>' +
                            '</div>' +
                            '</div>' +
                            '</div>';

                }


                userTable.append(trHTML);

                $(document).ready(function () {

                    commentAdd();
                });
            }
        });
    }
</script>


<script>


    function getComments(id, elem) {

        $('.fake').attr("data-comment", id);
        var trHTML = '<ul class="media-list">';
        var comments = $('.fake');


        $.ajax({
            url: "/getComments",
            data: {tweet_id: id},
            dataType: "text json",
            success: function (data) {
                for (var i = 0; i < data.length; i++) {

                    trHTML +=

                            '<li class="small">' +
                            '<a href="/user/profile/' + data[i].user.username + '"' +
                            'class="pull-left" style="text-decoration: none">' +
                            '<div class="col-sm-2 reply_imgage">' +
                            '<img src="' + data[i].user.avatar + '"' +
                            'class="reply_img pull-left"' +

                            'height="25" width="25" alt="Avatar">' +
                            '</div></a>' +

                            '<div class="reply_text">' +

                            '<span class="text-muted pull-right">' +
                            '<small class="text-muted">' + data[i].postDateTime.year + '-' + getMonthFromString(data[i].postDateTime.month) + '-' + data[i].postDateTime.dayOfMonth +
                            ' ' + data[i].postDateTime.hour + ':' + data[i].postDateTime.minute + ':' + data[i].postDateTime.second + '</small>' +
                            '</span>' +
                            '<a href="/user/profile/' + data[i].user.username + '"><strong' +
                            'class="text-success" style="text-decoration: none">@' + data[i].user.username + '</strong></a>' +

                            '<div>' + data[i].text + '</div>' +

                            '</div>' +
                            '</li>' +
                            '<hr>';
                }
                trHTML += '</ul>';
//                elem.append(trHTML);
//                console.log(elem.firstChild);
//                elem.append(trHTML);
                elem.innerHTML = trHTML;
            }
        });
    }
</script>

<input hidden id="page-count" value="${maxPage}">
<a href="#" class="scrollup">ScrollUp</a>

</body>
</html>

