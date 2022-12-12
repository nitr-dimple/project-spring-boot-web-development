<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp" />

<div style="width: 100%;" class="form-group">
  <br>
  <br>
  <br>
  <br>
  <div style="float: left; width: 90%; min-height:500px; margin-left: 50px;border:1px solid black; ">
    <div style="float: left; margin-left: 30px; width: 90%;">

      <c:if test="${ requestScope['userUpdateSucess'] != null}">
        <br/>
        <div class="form-group" >
            <div class="col-xs-6 alert alert-success" style="width: 50%">
                ${ requestScope['userUpdateSucess'] }
            </div>
        </div>
        <br/>
      </c:if>
      <br>
      <h3>User Details: </h3>
      <div class="form-horizontal">
        <br>
        <div class="form-group" >
          <div class="col-xs-8" style="padding-top: 6px; padding-left: 20px;">
            User Type :<c:out value="${sessionScope['username'].getUsertype()}"></c:out> <br/>
            Full Name: <c:out value="${sessionScope['username'].getFullname()}"></c:out> <br/>
            Email: <c:out value="${sessionScope['username'].getEmail()}"></c:out> <br/>
            Gender: <c:out value="${sessionScope['username'].getGender()}"></c:out> <br/>
          </div>
        </div>

        <div class="form-group" >
          <div class="col-xs-3" style="padding-top: 6px; padding-left: 20px;">
            <form method="GET" class="" action="${pageContext.request.contextPath}/user/updateUser.htm">
              <input type="submit" value="Update Details" class="btn btn-primary" name="updateUserDetails">
            </form>
          </div>
          <div class="col-xs-3" style="padding-top: 6px; padding-left: 20px;">
            <form method="POST" class="" action="${pageContext.request.contextPath}/user/deleteUser.htm">
              <input type="submit" value="Delete Account" class="btn btn-primary" name="deleteUser">
            </form>
          </div>
        </div>

      </div>
    </div>
  </div>

</div>

<jsp:include page="footer.jsp" />
