<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp" />
<br>
<div class="container-fluid">
    <br>
    <br>
    <div class="col-xs-12 col-sm-12">
        <form method="POST" class="form-horizontal" action="${pageContext.request.contextPath}/user/updateUserPassword.htm">
            <fieldset>
                <legend class="mt-1">Update User Password</legend>

                <c:if test="${ requestScope['newpassword-confirmpassword-error'] != null}">
                    <div>
                        <div class="col-xs-3 text-right pd-2">
                        </div>
                        <div class="col-xs-4">
                               <font color="red">  ${requestScope['newpassword-confirmpassword-error']} </font>
                        </div>
                    </div>
                    <br/>
                    <br/>
                </c:if>

                <c:if test="${ requestScope['oldpassword-error'] != null}">
                    <div>
                        <div class="col-xs-3 text-right pd-2">
                        </div>
                        <div class="col-xs-4">
                               <font color="red"> ${requestScope['oldpassword-error']} </font>
                        </div>
                    </div>
                    <br/>
                    <br/>
                </c:if>

                <div class="form-group">
                    <div class="col-xs-3 text-right pd-2">
                        <label for="oldpassword">Old Password:</label>
                    </div>
                    <div class="col-xs-4">
                        <input type="password" name="oldpassword" class="form-control form-control-lg" id="oldpassword"/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-xs-3 text-right pd-2">
                        <label for="newpassword">New Password:</label>
                    </div>
                    <div class="col-xs-4">
                        <input type="password" name="newpassword" class="form-control form-control-lg" id="newpassword"/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-xs-3 text-right pd-2">
                        <label for="confirmpassword">Confirm Password:</label>
                    </div>
                    <div class="col-xs-4">
                        <input type="password" name="confirmpassword" class="form-control form-control-lg" id="confirmpassword"/>
                    </div>
                </div>


                <div class="col-1 text-center">
                    <input type="submit" value="Update Password" class="btn btn-primary">
                </div>
            </fieldset>
        </form>
    </div>
</div>

<jsp:include page="footer.jsp" />

