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
      <h3>View all visits: </h3>

      <c:forEach var="residence" items="${requestScope['residenceList']}">
        <c:forEach var="house" items="${requestScope['houseList']}">
          <c:forEach var="visit" items="${requestScope['visitList']}">
            <c:if test="${visit.getHouseId() eq house.getId()}">
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
                </div>
                <br/>
                <div class="form-horizontal">
                    <div class="form-group" style=" border: 1px solid black; margin-left: 30px; margin-right: 15px;">
                        <div class="col-xs-7" style="padding-top: 6px; padding-left: 20px;">
                          Visiting Details: <br>
                          Name: ${visit.getName()} <br/>
                          Email: ${visit.getEmail()} <br/>
                          Phone Number: ${visit.getPhonenumber()} <br/>
                          Availability: ${visit.getDescription()} <br/>
                          Status: <c:if test="${visit.isVisitingStatus()}">Completed</c:if><c:if test="${visit.isVisitingStatus() eq false}">Not visited</c:if> <br>
                          Appointment Scheduled: <c:if test="${visit.isVisitScheduled()}">Scheduled</c:if><c:if test="${visit.isVisitScheduled() eq false}">Not Scheduled</c:if>
                        </div>
                        <c:forEach var="schedule" items="${requestScope['scheduleList']}">
                          <c:if test="${schedule.getVisit().getId() eq visit.getId()}">
                            <div class="col-xs-6 text-center"  style="padding-top: 4px; border-top: 1px solid black;">
                            </div>
                            <div class="col-xs-6"  style="padding-top: 4px;  border-top: 1px solid black;">
                              Appointment Details: <br>
                              Name: ${schedule.getName()} <br>
                              Email: ${schedule.getEmail()} <br>
                              Phone Number: ${schedule.getPhonenumber()} <br>
                              Schedule Date: ${schedule.getScheduleDate()} <br>
                              Time: ${schedule.getScheduledTime()} <br>
                            </div>
                          </c:if>
                        </c:forEach>


                      </div>
                </div>
              </div>
              </br>
          </c:if>
          </c:forEach>
        </c:forEach>
      </c:forEach>
      <br>
      <br>

    </div>
  </div>
</div>

<jsp:include page="footer.jsp" />
