<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h3>All Visits</h3>

<c:if test="${requestScope['visit-update-success'] != null}">
    <div class="form-group">
        <div class="col-xs-8">
            <font color="green"><c:out value="${requestScope['visit-update-success']}" ></c:out> </font>
        </div>
    </div>
</c:if>
<c:if test="${requestScope['houseUpdateSuccess'] != null}">
    <div class="form-group">
        <div class="col-xs-8">
            <font color="green"><c:out value="${requestScope['houseUpdateSuccess']}" ></c:out> </font>
        </div>
    </div>
</c:if>
<c:if test="${requestScope['housePhotoDeleteSuccess'] != null}">
    <div class="form-group">
        <div class="col-xs-8">
            <font color="green"><c:out value="${requestScope['housePhotoDeleteSuccess']}" ></c:out> </font>
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
                        <c:forEach var="visit" items="${requestScope['visitList']}">
                            <c:if test="${visit.getHouseId() eq house.getId()}">
                            <div class="form-group" style=" border: 1px solid black; margin-left: 30px; margin-right: 15px;">
                                <div class="col-xs-7" style="padding-top: 6px; padding-left: 20px;">
                                    Name: ${visit.getName()} <br/>
                                    Email: ${visit.getEmail()} <br/>
                                    Phone Number: ${visit.getPhonenumber()} <br/>
                                    Availability: ${visit.getDescription()} <br/>
                                    Status: <c:if test="${visit.isVisitingStatus()}">Completed</c:if><c:if test="${visit.isVisitingStatus() eq false}">Not visited</c:if>
                                </div>
                                <form method="POST" class="form-horizontal" action="${pageContext.request.contextPath}/user/scheduleVisitTour.htm">
                                    <div class="col-xs-2 text-center"  style="padding-top: 4px">
                                        <input type="hidden" name="visitId" value="${visit.getId()}">
                                        <input type="submit" value="Schedule Tour" class="btn btn-primary" name="scheduleVisitTour">
                                    </div>
                                </form>
                                <c:if test="${visit.isVisitingStatus() eq false}">
                                    <form method="POST" class="form-horizontal" action="${pageContext.request.contextPath}/user/markVisitComplete.htm">
                                        <div class="col-xs-3 text-center"  style="padding-top: 4px">
                                            <input type="hidden" name="visitId" value="${visit.getId()}">
                                            <input type="submit" value="Mark Visit Complete" class="btn btn-primary" name="markVisitComplete">
                                        </div>
                                    </form>
                                </c:if>
                                <c:if test="${visit.isVisitingStatus()}">
                                    <form method="POST" class="form-horizontal" action="${pageContext.request.contextPath}/user/markVisitComplete.htm">
                                        <div class="col-xs-3 text-center"  style="padding-top: 4px">
                                            <input type="hidden" name="visitId" value="${visit.getId()}">
                                            <input type="submit" value="Mark Visit Incomplete" class="btn btn-primary" name="markVisitComplete">
                                        </div>
                                    </form>
                                </c:if>


                            </div>
                            </c:if>
                        </c:forEach>
                </div>
            </div>
            </br>
        </c:if>
    </c:forEach>
</c:forEach>
