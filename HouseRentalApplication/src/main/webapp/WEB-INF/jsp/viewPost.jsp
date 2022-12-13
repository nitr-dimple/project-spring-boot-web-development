<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h3>All Post</h3>

<c:if test="${requestScope['houseDeleteSuccess'] != null}">
    <div class="form-group">
        <div class="col-xs-8">
            <font color="green"><c:out value="${requestScope['houseDeleteSuccess']}" ></c:out> </font>
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

                    <c:if test="${house.isAvailable() eq false}">
                        <form method="POST" class="form-horizontal" action="${pageContext.request.contextPath}/user/viewPost.htm">
                            <div class="col-xs-2 text-center"  style="padding-top: 4px">
                                <input type="hidden" name="housePostId" value="${house.getId()}">
                                <input type="submit" value="Post" class="btn btn-primary" name="postHouse">
                            </div>
                        </form>
                    </c:if>
                    <c:if test="${house.isAvailable()}">
                        <form method="POST" class="form-horizontal" action="${pageContext.request.contextPath}/user/viewPost.htm">
                            <div class="col-xs-2 text-center"  style="padding-top: 4px">
                                <input type="hidden" name="housePostId" value="${house.getId()}">
                                <input type="submit" value="Remove Post" class="btn btn-primary" name="postHouse">
                            </div>
                        </form>
                    </c:if>
                </div>
                <br/>

                <div class="form-group" style="">
                    <c:forEach var="housePhoto" items="${requestScope['housePhotoList']}">
                        <c:if test="${housePhoto.getHouseId() eq house.getId()}">
                            <div class="card col-xs-3" style="width: 25rem; height: 20rem; border: 1px solid black; margin-left: 20px; margin-top: 10px; padding: 0">
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
