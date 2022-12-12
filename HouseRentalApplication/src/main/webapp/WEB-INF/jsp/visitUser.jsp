<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<jsp:include page="header.jsp" />

<div style="width: 100%;" class="form-group">
    <br>
    <br>
    <br>
    <br>
    <div style="float: left; width: 90%; min-height:500px; margin-left: 50px;border:1px solid black; ">
        <div style="float: left; margin-left: 30px; width: 90%;">
            <br>
            <form method="POST" class="form-horizontal">
                <h3>Visiting Details: </h3>
                <div class="form-horizontal">
                    <br>

                    <div class="form-group">
                        <div class="col-xs-3 text-right" style="padding-top: 6px">
                            <label for="availability">Add Your Availability:</label>
                        </div>
                        <div class="col-xs-6">
                            <textarea   name="availability" class="form-control form-control-lg" id="availability"></textarea>
                        </div>
                    </div>


                    <div class="form-group" >
                        <div class="col-xs-3" style="padding-top: 6px; padding-left: 20px;">
                            <form method="POST" class="" action="${pageContext.request.contextPath}/user/visitUser.htm">
                                <input type="submit" value="Confirm Visiting" class="btn btn-primary" name="visitUser">
                            </form>
                        </div>
                    </div>

                </div>
            </form>
        </div>
    </div>

</div>

<jsp:include page="footer.jsp" />
