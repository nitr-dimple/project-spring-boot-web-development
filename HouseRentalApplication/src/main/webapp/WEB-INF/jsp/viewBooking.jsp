<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h3>All Booking</h3>

<c:if test="${requestScope['bookhouse-status-update-success'] != null}">
  <div class="form-group">
    <div class="col-xs-8">
      <font color="green"><c:out value="${requestScope['bookhouse-status-update-success']}" ></c:out> </font>
    </div>
  </div>
</c:if>
</br>

<c:forEach var="residence" items="${requestScope['residenceList']}">
  <c:forEach var="house" items="${requestScope['houseList']}">
    <c:if test="${house.getResidenceId() eq residence.getId()}">
      <div class="form-horizontal" style="border: 1px solid black;">
        <br>
        <div class="form-group" >
          <div class="col-xs-8" style="padding-top: 6px; padding-left: 20px;">
            House No. :<c:out value="${house.getHouseno()}"></c:out> <br/>
            Residence Name: <c:out value="${residence.residencename}"></c:out> <br/>
            Address: <c:out value="${residence.street}"/>, <c:out value="${residence.city}" />
            <c:out value="${residence.zipcode}"></c:out>, <c:out value="${residence.state}"></c:out>
          </div>
        </div>
        <br/>
        <div class="form-horizontal">
          <c:forEach var="bookHouse" items="${requestScope['bookHouseList']}">
            <c:if test="${bookHouse.getHouseId() eq house.getId()}">
              <c:forEach var="user" items="${requestScope['userList']}">
                <c:if test="${user.getId() eq bookHouse.getUserId()}">
                  <div class="form-group" style=" border: 1px solid black; margin-left: 30px; margin-right: 15px;">
                    <div class="col-xs-6" style="padding-top: 6px; padding-left: 20px;">
                      Name: ${user.getFullname()} <br/>
                      Email: ${user.getEmail()} <br/>
                      <c:if test="${bookHouse.getBookingApprovedbyOwner() != null}">
                        Booking Status: <c:if test="${bookHouse.getBookingApprovedbyOwner() eq 'Accepted'}"> Accepted </c:if> <c:if test="${bookHouse.getBookingApprovedbyOwner() eq 'Denied'}"> Denied </c:if>
                      </c:if>
                    </div>
                    <form method="post" class="form-horizontal" action="${pageContext.request.contextPath}/user/updateBookingStatus.htm">
                      <div class="col-xs-3 text-center"  style="padding-top: 4px">
                        <input type="hidden" name="bookHouseId" value="${bookHouse.getId()}">
                        <input type="submit" value="Accept Booking" class="btn btn-primary" name="bookingStatus">
                      </div>
                    </form>

                    <form method="post" class="form-horizontal" action="${pageContext.request.contextPath}/user/updateBookingStatus.htm">
                      <div class="col-xs-3 text-center"  style="padding-top: 4px">
                        <input type="hidden" name="bookHouseId" value="${bookHouse.getId()}">
                        <input type="submit" value="Deny Booking" class="btn btn-primary" name="bookingStatus">
                      </div>
                    </form>
                  </div>

                </c:if>
              </c:forEach>
            </c:if>
          </c:forEach>
        </div>
      </div>
      </br>
    </c:if>
  </c:forEach>
</c:forEach>
<br>
<br>
