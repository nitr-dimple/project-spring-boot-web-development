<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp" />
<br>
<div class="container-fluid">
    <br>
    <br>
    <div class="col-xs-12 col-sm-12">
        <form:form modelAttribute="user" method="POST" class="form-horizontal">
            <fieldset>
                <legend class="mt-1">Update User Details</legend>

                <c:if test="${ requestScope['error'] != null}">
                    <div>
                        <div class="col-xs-3 text-right pd-2">
                        </div>
                        <div class="col-xs-4 alert alert-danger">
<%--                            <div class="" style="width: 45%">--%>
                                ${requestScope['error']}
<%--                            </div>--%>
                        </div>
                    </div>
                    <br/>
                    <br/>
                </c:if>

                <div class="form-group">
                    <div class="col-xs-3 text-right pd-2">
                        <label for="fullname">Full Name:</label>
                    </div>
                    <div class="col-xs-4">
                        <form:input path="fullname" type="text" name="fullname" class="form-control form-control-lg" value="${sessionScope['username'].getFullname()}"/>
                        <font color="red"> <form:errors path="fullname"></form:errors> </font>
                    </div>
                </div>


                <div class="form-group">
                    <div class="col-xs-3 text-right pd-2">
                        <label for="gender">Gender:</label>
                    </div>
                    <div class="col-xs-4">
                        <div class="col-xs-3">
                            <form:radiobutton path="gender"  name="gender" value="Male" />
                            <label for="male">Male</label>
                        </div>
                        <div class="col-xs-4">
                            <form:radiobutton path="gender"   name="gender" value="Female" />
                            <label for="female">Female</label>
                        </div>
                        <div class="col-xs-4">
                            <form:radiobutton path="gender" name="gender" value="Female" />
                            <label for="other">Other</label>
                        </div>
                        <font color="red">  <form:errors path="gender"></form:errors> </font>
                    </div>
                </div>

                <div class="col-1 text-center">
                    <input type="submit" value="Update Details" class="btn btn-primary">
                </div>
            </fieldset>
        </form:form>
    </div>
</div>

<jsp:include page="footer.jsp" />
