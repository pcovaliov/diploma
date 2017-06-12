<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    .tdName{
        font-weight: bold;
        margin-top: 8px
    }
    .tdVal{
        font-size: 25px;
    }

    .texBox{
        display: block;
        word-wrap: break-word;
        margin: 0 2% 0 2%;
    }
</style>

<div class="modal fade" id="taskModal<%=request.getParameter("id")%>" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h2 class="modal-title"><%=request.getParameter("taskName")%></h2>
            </div>


            <div class="modal-body">
                <table class="table table-striped">
                    <tbody>
                    <tr>
                        <td><p class="tdName">Project Name:</p></td>
                        <td><p class="tdVal"><%=request.getParameter("projectName")%></p></td>
                    </tr>
                    <tr>
                        <td><p class="tdName">Start Period:</p></td>
                        <td><p class="tdVal"><%=request.getParameter("periodStart").substring(0, 10)%></p></td>
                    </tr>
                    <tr>
                        <td><p class="tdName">End Period:</p></td>
                        <td><p class="tdVal"><%=request.getParameter("periodEnd").substring(0, 10)%></p></td>
                    </tr>
                    <tr>
                        <td><p class="tdName">Current Status:</p></td>
                        <td><p class="tdVal"><%=request.getParameter("status")%></p></td>
                    </tr>
                    </tbody>
                </table>
                <label>Description</label>
                <p class="texBox"><%=request.getParameter("description")%></p>
            </div>
            <div class="modal-footer">
                <button onclick="location.href='/editTask<%=request.getParameter("id")%>';"
                        type="button" class="btn btn-info">Edit task</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>

    </div>
</div>
