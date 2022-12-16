<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp" />

<div class="container-fluid">
    <br>
    <br>
    <form method="POST" action="${pageContext.request.contextPath}/user/login.htm" class="form">
        <h2 align="center"> Please Login </h2>
<%--        <br>--%>
        <div>
            <c:if test="${ param.error != null}">
                <div class="form-group">
                    <div class="col-xs-3 text-right pd-2">
                    </div>
                    <div class="col-xs-9" style="width: 50%">
                        <font color="red">Invalid Username or Password </font>
                    </div>
                </div>
                <br/>
                <br/>
            </c:if>

            <c:if test="${ param.logout != null}">
                <div class="form-group">
                    <div class="col-xs-3 text-right pd-2">
                    </div>
                    <div class="col-xs-9 " style="width: 50%">
                        <font color="green">Successfully Logged Out!!!</font>
                    </div>
                </div>
                <br/>
                <br/>
            </c:if>

            <c:if test="${ requestScope['authentication-error'] != null}">
                <div class="form-group">
                    <div class="col-xs-3 text-right pd-2">
                    </div>
                    <div class="col-xs-9" style="width: 50%">
                        <font color="red"><c:out value="${ requestScope['authentication-error']}"> </c:out> </font>
                    </div>
                </div>
                <br/>
                <br/>
            </c:if>

            <c:if test="${ requestScope['password-update-success'] != null}">
                <div class="form-group">
                    <div class="col-xs-3 text-right pd-2">
                    </div>
                    <div class="col-xs-9" style="width: 50%">
                        <font color="red"><c:out value="${ requestScope['password-update-success']}"> </c:out> </font>
                    </div>
                </div>
                <br/>
                <br/>
            </c:if>

            <c:if test="${ requestScope['authentication-success'] != null}">
                <div class="form-group">
                    <div class="col-xs-3 text-right pd-2">
                    </div>
                    <div class="col-xs-9" style="width: 50%">
                        <font color="green"><c:out value="${ requestScope['authentication-success']}"> </c:out> </font>
                    </div>
                </div>
                <br/>
                <br/>
            </c:if>

            <div class="form-group">
                <div class="col-xs-3 text-right pd-2">
                    <label>Email: </label>
                </div>
                <div class="col-xs-9" >
                    <input name="username" type="text" class="form-control form-control-lg" style="width: 50%" />
                </div>
            </div>
            <br/>
            <br/>

            <div class="form-group">
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
