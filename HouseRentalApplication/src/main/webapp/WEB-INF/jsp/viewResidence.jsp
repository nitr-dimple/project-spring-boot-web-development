<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h3>Residence List</h3>
<form method="get" action="${pageContext.request.contextPath}/user/addResidence.htm">
    <input class="btn btn-primary"  style="float: right; position: relative; top: -50px;" type="submit"value="Add Residence" name="btnClicked"/>
</form>
</br>


<c:if test="${requestScope['residenceDeleteSuccess'] != null}">
    <div class="form-group">
        <div class="col-xs-8">
            <font color="green"><c:out value="${requestScope['residenceDeleteSuccess']}" ></c:out> </font>
        </div>
    </div>
</c:if>
<c:if test="${requestScope['residenceUpdateSuccess'] != null}">
    <div class="form-group">
        <div class="col-xs-8">
            <font color="green"><c:out value="${requestScope['residenceUpdateSuccess']}" ></c:out> </font>
        </div>
    </div>
</c:if>
</br>

<c:forEach var="residence" items="${requestScope['residenceList']}">

        <div class="form-horizontal" style="border: 1px solid black;">
            <br>
            <div class="form-group" >
                <div class="col-xs-8" style="padding-top: 6px; padding-left: 20px;">
                    <c:out value="${residence.residencename}"></c:out> <br/>
                    <c:out value="${residence.street}"/>, <c:out value="${residence.city}" />
                    <c:out value="${residence.zipcode}"></c:out>, <c:out value="${residence.state}"></c:out>
                </div>
                <form method="GET" class="form-horizontal" action="${pageContext.request.contextPath}/user/updateResidence.htm">
                    <div class="col-xs-2 text-center"  style="padding-top: 4px">
                        <input type="hidden" name="residenceUpdateId" value="${residence.getId()}">
                        <input type="submit" value="Update Residence" class="btn btn-primary" name="updateResidence">
                    </div>
                </form>

                <form method="POST" class="form-horizontal" action="${pageContext.request.contextPath}/user/deleteResidence.htm">
                    <div class="col-xs-2 text-center"  style="padding-top: 4px">
                        <input type="hidden" name="residenceDeleteId" value="${residence.getId()}">
                        <input type="submit" value="Delete" class="btn btn-primary" name="deleteResidence">
                    </div>
                </form>
            </div>
            <br>

            <div class="form-group" style="">
                <c:forEach var="residencePhoto" items="${requestScope['residencePhotos']}">
                    <c:if test="${residencePhoto.getResidenceId() eq residence.getId()}">
                            <div class="card col-xs-3" style="width: 20rem; height: 20rem; border: 1px solid black; margin-left: 20px; margin-top: 10px;">
                                <img src="<%=request.getContextPath()%>/images/${residencePhoto.getImagename()}" alt="Avatar" style="width:100%; height:70%">
                                <div class="container">
                                    <br>
                                    <form method="POST" class="" action="${pageContext.request.contextPath}/user/deleteResidencePhoto.htm">
                                        <input type="hidden" name="residencePhotoDeleteId" value="${residencePhoto.getId()}">
                                        <input type="submit" value="Delete" class="btn btn-primary" name="deleteResidencePhoto">
                                    </form>
                                </div>
                            </div>
                    </c:if>
                </c:forEach>
            </div>
        </div>
    </br>
</c:forEach>
