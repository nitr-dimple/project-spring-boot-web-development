<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp" />

<div class="container-fluid">
    <br>
    <br>
    <form method="POST" action="${pageContext.request.contextPath}/user/login.htm" class="form">
        <h2 align="center"> Please Login </h2>

        <br>
        <div>
            <c:if test="${ param.error != null}">
                <div>
                    <div class="col-xs-3 text-right pd-2">
                    </div>
                    <div class="col-xs-9 ">
                        <div class="alert alert-danger" style="width: 50%">
                            Invalid Username or Password
                        </div>
                    </div>
                </div>
                <br/>
                <br/>
            </c:if>

<%--            <div>--%>
<%--                <div class="col-xs-3 text-right pd-2">--%>
<%--                    <label for="userType">Login As:</label>--%>
<%--                </div>--%>
<%--                <div class="col-xs-9">--%>
<%--                    <select name="userType" class="form-control form-control-lg" id="userType" style="width: 50%">--%>
<%--                        <option value="owner"> House Owner</option>--%>
<%--                        <option value="seeker"> House Seeker</option>--%>
<%--                    </select>--%>
<%--                </div>--%>
<%--            </div>--%>

<%--            <br/>--%>
<%--            <br/>--%>

            <div>
                <div class="col-xs-3 text-right pd-2">
                    <label>Email: </label>
                </div>
                <div class="col-xs-9" >
                    <input name="username" type="text" class="form-control form-control-lg" style="width: 50%" />
                </div>
            </div>
            <br/>
            <br/>

            <div class="mt-5">
                <div class="col-xs-3 text-right pd-2" >
                    <label>Password: </label>
                </div>
                <div class="col-xs-9" >
                    <input type="password" name="password" class="form-control form-control-lg" style="width: 50%" />
                </div>
            </div>
            <br/>
            <br/>

            <div class="col-1 text-center">
                <input type="submit" value="Log In" class="btn btn-primary">
            </div>
        </div>
    </form>
</div>

<jsp:include page="footer.jsp" />
