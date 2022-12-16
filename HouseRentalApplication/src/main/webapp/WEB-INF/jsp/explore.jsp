<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp" />

<div style="width: 100%;" class="form-group">
  <br>
  <br>
  <br>
  <br>
  <div style="float: left; width: 90%; min-height:500px; margin-left: 50px;border:1px solid black; ">
    <div style="float: left; margin-left: 30px; width: 90%;">
      <br>
      <h3>All House List</h3>
      <%--    <form method="get" action="${pageContext.request.contextPath}/user/addHouse.htm">--%>
      <%--      <input class="btn btn-primary"  style="float: right; position: relative; top: -50px;" type="submit"value="Add House" name="btnClicked"/>--%>
      <%--    </form>--%>
      </br>
      </br>
      <c:forEach var="residence" items="${requestScope['residenceList']}">
        <c:forEach var="house" items="${requestScope['houseList']}">
          <c:if test="${house.isAvailable() && house.getResidenceId() eq residence.getId()}">
            <div class="form-horizontal" style="border: 1px solid black;">
              <br>
              <div class="form-group" >
                <div class="col-xs-8" style="padding-top: 6px; padding-left: 20px;">
                  House No. :<c:out value="${house.getHouseno()}"></c:out> <br/>
                  Total Area: <c:out value="${house.getTotalarea()}"></c:out> <br/>
                  Bed Rooms: <c:out value="${house.getBedrooms()}"></c:out> <br/>
                  Bathrooms: <c:out value="${house.getBathrooms()}"></c:out> <br/>
                  Balconies: <c:out value="${house.getBalcony()}"></c:out> <br/>
                  Living Rooms: <c:out value="${house.getLivinrooms()}"></c:out> <br/>
                  Advance Payment: <c:out value="${house.getAdvancepayment()}"></c:out> <br/>
                  Monthy Rent: <c:out value="${house.getMonthrent()}"></c:out> <br/>
                  Start Date: <c:out value="${house.getStartdate()}"></c:out> <br/>
                  End Date: <c:out value="${house.getEnddate()}"></c:out> <br/>
                  Water Bill Included ? :
                  <c:choose>
                    <c:when test="${house.isWaterbill() eq true}"> Yes</c:when>
                    <c:when test="${house.isWaterbill() eq false}"> No</c:when>
                  </c:choose> <br/>
                  Gas Bill Included ? :
                  <c:choose>
                    <c:when test="${house.isGasbill() eq true}"> Yes</c:when>
                    <c:when test="${house.isGasbill() eq false}"> No</c:when>
                  </c:choose> <br/>
                  Maintenace Cost Included ? :
                  <c:choose>
                    <c:when test="${house.isMaintenancecost() eq true}"> Yes</c:when>
                    <c:when test="${house.isMaintenancecost() eq false}"> No</c:when>
                  </c:choose> <br/>
                  <c:if test="${house.getParking()}">
                    Parking Details: <c:out value="${house.getParking()}"></c:out>  <br/>
                  </c:if>
                  <c:if test="${house.getDescription()}">
                    Descriptions: <c:out value="${house.getDescription()}"></c:out> <br/>
                  </c:if>
                  Residence Name: <c:out value="${residence.residencename}"></c:out> <br/>
                  Address: <c:out value="${residence.street}"/>, <c:out value="${residence.city}" />
                  <c:out value="${residence.zipcode}"></c:out>, <c:out value="${residence.state}"></c:out>
                </div>
                <form method="GET" class="form-horizontal" action="${pageContext.request.contextPath}/user/visitHouse.htm">
                  <div class="col-xs-2 text-center"  style="padding-top: 4px">
                    <input type="hidden" name="visitHouseId" value="${house.getId()}">
                    <input type="submit" value="Make Visit" class="btn btn-primary" name="visithouse">
                  </div>
                </form>

                <form method="GET" class="form-horizontal" action="${pageContext.request.contextPath}/user/bookHouse.htm">
                  <div class="col-xs-2 text-center"  style="padding-top: 4px">
                    <input type="hidden" name="visitHouseId" value="${house.getId()}">
                    <input type="submit" value="Book" class="btn btn-primary" name="Book">
                  </div>
                </form>
              </div>
              <br/>

              <div class="form-group" style="">
                <c:forEach var="housePhoto" items="${requestScope['housePhotos']}">
                  <c:if test="${housePhoto.getHouseId() eq house.getId()}">
                    <div class="col-xs-3" style="width: 25rem; height: 20rem; border: 1px solid black; margin-left: 20px; margin-top: 10px; padding: 0">
                      <img src="<%=request.getContextPath()%>/images/${housePhoto.getImagename()}" alt="Avatar" style="width:100%; height:100%">
                    </div>
                  </c:if>
                </c:forEach>
                <c:forEach var="residencePhoto" items="${requestScope['residencePhotoList']}">
                  <c:if test="${residencePhoto.getResidenceId() eq residence.getId()}">
                    <div class="col-xs-3" style="width: 25rem; height: 20rem; border: 1px solid black; margin-left: 20px; margin-top: 10px; padding: 0">
                      <img src="<%=request.getContextPath()%>/images/${residencePhoto.getImagename()}" alt="Avatar" style="width:100%; height:100%">
                    </div>
                  </c:if>
                </c:forEach>
              </div>
            </div>
            </br>
          </c:if>
        </c:forEach>
      </c:forEach>
    </div>
  </div>

</div>

<jsp:include page="footer.jsp" />