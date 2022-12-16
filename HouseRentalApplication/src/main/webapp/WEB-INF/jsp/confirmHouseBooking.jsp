<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp" />

<div class="container-fluid">
  <br>
  <br>
  <br>
  <br>

  <c:if test="${requestScope['sent-booking-request-success'] != null}">
    <h4>${requestScope['sent-booking-request-success']} </h4>
  </c:if>

  <c:if test="${requestScope['sent-booking-request-success'] == null}">

    <h4> Are you sure you want to confirm this booking ?</h4>
    <br>
    <div class="col-xs-12">
      <div class="col-xs-1">
        <form method="post" action="${pageContext.request.contextPath}/user/bookHouse.htm">
          <input type="hidden" value="${requestScope['visitHouseId']}" name="visitHouseId">
          <input type="submit" value="Yes" class="btn btn-primary" name="confirmHouseBooking">
        </form>
      </div>
      <div class="col-xs-1 ">
        <form method="get" action="${pageContext.request.contextPath}/user/explore.htm">
          <input type="submit" value="No" class="btn btn-primary" name="denyHouseBooking">
        </form>
      </div>
    </div>
  </c:if>



</div>

<jsp:include page="footer.jsp" />

