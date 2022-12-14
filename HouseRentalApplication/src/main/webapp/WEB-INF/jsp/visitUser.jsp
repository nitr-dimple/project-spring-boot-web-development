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
            <form:form modelAttribute="visit" method="POST" class="form-horizontal">
                <h3>Visiting Details: </h3>
                <div class="form-horizontal">
                    <br>
                    <c:if test="${requestScope['visit-success'] != null}">
                        <div class="form-group">
                            <div class="col-xs-8">
                                <font color="green"><c:out value="${requestScope['visit-success']}" ></c:out> </font>
                            </div>
                        </div>
                    </c:if>

                    <div class="form-group">
                        <div class="col-xs-3 text-right pd-2">
                            <label for="email">Contact E-mail:</label>
                        </div>
                        <div class="col-xs-6">
                            <form:input path="email"  type="text" name="email" class="form-control form-control-lg" id="email" />
                            <font color="red"> <form:errors path="email"></form:errors> </font>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-3 text-right pd-2">
                            <label for="phone">Phone Number:</label>
                        </div>
                        <div class="col-xs-6">
                            <form:input path="phonenumber"  type="text" name="phone" class="form-control form-control-lg" id="phone" />
                            <font color="red"> <form:errors path="phonenumber"></form:errors> </font>
                        </div>
                    </div>


                    <div class="form-group">
                        <div class="col-xs-3 text-right" style="padding-top: 6px">
                            <label for="availability">Add Your Availability with time:</label>
                        </div>
                        <div class="col-xs-6">
                            <form:textarea path="description"  name="availability" class="form-control form-control-lg" id="availability"></form:textarea>
                            <font color="red"> <form:errors path="description"></form:errors> </font>

                        </div>
                    </div>


                    <div class="form-group" >
                        <div class="col-xs-8 text-right" style="padding-top: 6px; padding-left: 20px;">
                                <input type="submit" value="Confirm Visiting" class="btn btn-primary" name="visitUser">
                        </div>
                    </div>

                </div>
            </form:form>
        </div>
    </div>

</div>

<jsp:include page="footer.jsp" />
