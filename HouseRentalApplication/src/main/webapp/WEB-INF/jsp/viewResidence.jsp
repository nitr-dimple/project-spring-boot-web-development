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
</br>

<c:forEach var="residence" items="${requestScope['residenceList']}">

        <div class="form-horizontal">
            <div class="form-group" style="border: 1px solid black;">
                <div class="col-xs-8" style="padding-top: 6px">
                    <c:out value="${residence.residencename}"></c:out> <br/>
                    <c:out value="${residence.street}"/>, <c:out value="${residence.city}" />
                    <c:out value="${residence.zipcode}"></c:out>, <c:out value="${residence.state}"></c:out>
                </div>
                <div class="col-xs-2 text-center"  style="padding-top: 4px">
                    <input type="hidden" id="residenceUpdateId" value="${residence.id}">
                    <input type="submit" value="Update" class="btn btn-primary" name="updateResidence">
                </div>
                <form method="POST" class="form-horizontal" action="${pageContext.request.contextPath}/user/deleteResidence.htm">
                    <div class="col-xs-2 text-center"  style="padding-top: 4px">
                        <input type="hidden" name="residenceDeleteId" value="${residence.getId()}">
                        <input type="submit" value="Delete" class="btn btn-primary" name="deleteResidence">
                    </div>
                </form>
            </div>
        </div>
    </br>
</c:forEach>