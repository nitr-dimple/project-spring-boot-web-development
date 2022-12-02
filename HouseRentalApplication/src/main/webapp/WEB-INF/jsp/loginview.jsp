<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp" />

<div class="container-fluid">
    <c:if test="${requestScope.error}">
        <h2 style="text-align: center"> ${requestScope.error} </h2>
    </c:if>
    <form:form modelAttribute="user" method="POST">
        <h2 align="center"> Please Login </h2>

        <div>
            <div>
                <div class="col-xs-3 text-right pd-2">
                </div>
                <div class="col-xs-9">
                    <c:if test="${requestScope['error'] != null}">
                        <font color="red"> <c:out value="${requestScope['error']}" ></c:out> </font>
                    </c:if>
                </div>
            </div>

            <br>
            <div>
                <div class="col-xs-3 text-right pd-2">
                    <label for="userType">Login As:</label>
                </div>
                <div class="col-xs-9">

                    <form:select path="usertype" name="userType" class="form-control form-control-lg" id="userType" style="width: 50%">
                        <option value="owner"> House Owner</option>
                        <option value="seeker"> House Seeker</option>
                    </form:select>
                    <font color="red"> <form:errors path="usertype"></form:errors> </font>
                </div>
            </div>

            <br/>
            <br/>

            <div>
                <div class="col-xs-3 text-right pd-2">
                    <label>Email: </label>
                </div>
                <div class="col-xs-9" >
                    <form:input path="email" type="text" class="form-control form-control-lg" style="width: 50%" />
                    <font color="red"> <form:errors path="email"></form:errors> </font>
                </div>
            </div>
            <br/>
            <br/>

            <div class="mt-5">
                <div class="col-xs-3 text-right pd-2" >
                    <label>Password: </label>
                </div>
                <div class="col-xs-9" >
                    <form:input path="password" type="password" name="password" class="form-control form-control-lg" style="width: 50%" />
                    <font color="red"> <form:errors path="password"></form:errors> </font>
                </div>
            </div>
            <br/>
            <br/>

            <div class="col-1 text-center">
                <input type="submit" value="Log In" class="btn btn-primary">
            </div>
        </div>
    </form:form>
</div>

<jsp:include page="footer.jsp" />
