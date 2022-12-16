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
            <h3>All Your Booking</h3>

            <c:forEach var="residence" items="${requestScope['residenceList']}">
                <c:forEach var="house" items="${requestScope['houseList']}">
                    <c:if test="${house.getResidenceId() eq residence.getId()}">
                        <c:forEach var="bookhouse" items="${requestScope['bookHouseList']}">
                            <c:if test="${bookhouse.getHouseId() eq house.getId()}">
                                <div class="form-horizontal" style="border: 1px solid black;">
                                    <br>
                                    <div class="form-group" >
                                        <div class="col-xs-8" style="padding-top: 6px; padding-left: 20px;">
                                            House No. :<c:out value="${house.getHouseno()}"></c:out> <br/>
                                            Residence Name: <c:out value="${residence.residencename}"></c:out> <br/>
                                            Address: <c:out value="${residence.street}"/>, <c:out value="${residence.city}" />
                                            <c:out value="${residence.zipcode}"></c:out>, <c:out value="${residence.state}"></c:out> <br>
                                            Booking Status: <c:if test="${bookhouse.getBookingApprovedbyOwner() == null}"> Waiting for owner Approval </c:if> <c:if test="${bookhouse.getBookingApprovedbyOwner() eq 'Accepted'}"> Accepted by owner  </c:if> <c:if test="${bookhouse.getBookingApprovedbyOwner() eq 'Denied'}"> Denied by owner </c:if>

                                        </div>
                                    </div>
                                    <br/>
                                </div>
                                </br>
                            </c:if>
                        </c:forEach>
                    </c:if>
                </c:forEach>
            </c:forEach>
            <br>
            <br>

        </div>
    </div>
</div>

<jsp:include page="footer.jsp" />
