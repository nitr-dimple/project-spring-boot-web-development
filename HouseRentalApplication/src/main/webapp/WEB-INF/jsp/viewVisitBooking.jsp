<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp" />

<div class="container-fluid">
  <br>
  <br>
  <br>
  <br>
  <div style="width: 100%;" class="form-group">
    <div style="float: left; width: 20%; min-height:500px; margin-left: 50px;border:1px solid black; text-align: center ">
      <br>
      <form method="get" action="${pageContext.request.contextPath}/user/viewVisits.htm">
        <input class="btn btn-lg" style="background-color: black; color: white; height: 50px; width: 200px" type="submit" value="View Visits" name="btnClicked"/>
      </form>

      <form method="get" action="${pageContext.request.contextPath}/user/viewBooking.htm">
        <h2><input class="btn btn-lg" style="background-color: black; color: white; height: 50px; width: 200px" type="submit" value="View Booking" name="btnClicked"/></h2>
      </form>

    </div>

    <div style="float: right; width: 70%; min-height: 500px; margin-right: 50px; border:1px solid black; ">

      <div style="float: left; margin-left: 30px; width: 90%;">
        <br>

        <%--        If clicked on View Visit      --%>
        <c:if test="${requestScope['btnClicked'] != null && requestScope['btnClicked'].equals('View Visits')}">
          <jsp:include page="viewVisits.jsp" />
        </c:if>

        <%--        If clicked on Schedule Tour      --%>
        <c:if test="${requestScope['btnClicked'] != null && requestScope['btnClicked'].equals('Schedule Tour')}">
          <jsp:include page="scheduleVisitTour.jsp" />
        </c:if>

        <%--        If clicked on View Booking     --%>
        <c:if test="${requestScope['btnClicked'] != null && requestScope['btnClicked'].equals('View Booking')}">
          <jsp:include page="viewBooking.jsp" />
        </c:if>


      </div>

    </div>
  </div>
</div>

<jsp:include page="footer.jsp" />

