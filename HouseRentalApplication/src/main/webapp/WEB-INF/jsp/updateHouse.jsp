<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<div class="col-xs-12 col-sm-12">

    <%--@elvariable id="house" type=""--%>
    <form:form modelAttribute="house" method="POST" class="form-horizontal" enctype="multipart/form-data">
        <fieldset>

            <c:if test="${requestScope['houseAdded'] != null}">
                <div class="form-group">
                    <div class="col-xs-8">
                        <font color="green"><c:out value="${requestScope['houseAdded']}" ></c:out> </font>
                    </div>
                </div>
            </c:if>
            <c:if test="${requestScope['startdateerror'] != null}">
                <div class="form-group">
                    <div class="col-xs-8">
                        <font color="red"><c:out value="${requestScope['startdateerror']}" ></c:out> </font>
                    </div>
                </div>
            </c:if>
            <c:if test="${requestScope['enddateerror'] != null}">
                <div class="form-group">
                    <div class="col-xs-8">
                        <font color="red"><c:out value="${requestScope['enddateerror']}" ></c:out> </font>
                    </div>
                </div>
            </c:if>
            <legend class="mt-1">Update House Details</legend>

            <div class="form-group">
                <div class="col-xs-3 text-right" style="padding-top: 6px">
                    <label for="residence">Select Residence:</label>
                </div>
                <div class="col-xs-9">
                    <form:select path="residenceId"  name="residence" class="form-control form-control-lg" id="residence">
                        <c:forEach var="residence" items="${requestScope['residenceList']}">
                            <c:if test="${requestScope['updatedHouse'].getResidenceId() eq residence.getId()}" >
                                <option value="${residence.getId()}" selected hidden> ${residence}</option>
                            </c:if>
                            <form:option value="${residence.getId()}">
                                <c:out value="${residence}"></c:out>
                            </form:option>
                        </c:forEach>
                    </form:select>
                </div>
            </div>

            <div class="form-group">
                <div class="col-xs-2 text-right" style="padding-top: 6px">
                    <label for="houseno">House No:</label>
                </div>
                <div class="col-xs-2">
                    <form:input path="houseno" type="text" name="houseno" class="form-control form-control-lg" value="${requestScope['updatedHouse'].getHouseno()}"/>
                    <font color="red">  <form:errors path="houseno"></form:errors> </font>
                </div>
                <div class="col-xs-2 text-right" style="padding-top: 6px">
                    <label for="totalarea">Total Area:</label>
                </div>
                <div class="col-xs-2">
                    <form:input path="totalarea" type="text" name="totalarea" class="form-control form-control-lg" value="${requestScope['updatedHouse'].getTotalarea()}"/>
                    <font color="red">  <form:errors path="totalarea"></form:errors> </font>
                </div>
                <div class="col-xs-2 text-right" style="padding-top: 6px">
                    <label for="bedrooms">Bedrooms:</label>
                </div>
                <div class="col-xs-2">
                    <form:input path="bedrooms" type="number" name="bedrooms" class="form-control form-control-lg"  value="${requestScope['updatedHouse'].getBedrooms()}"/>
                    <font color="red">  <form:errors path="bedrooms"></form:errors> </font>
                </div>
            </div>

            <div class="form-group">
                <div class="col-xs-2 text-right" style="padding-top: 6px">
                    <label for="livingrooms">Living Rooms:</label>
                </div>
                <div class="col-xs-2">
                    <form:input path="livinrooms" type="number" name="livingrooms" class="form-control form-control-lg" value="${requestScope['updatedHouse'].getLivinrooms()}"/>
                    <font color="red">  <form:errors path="livinrooms"></form:errors> </font>
                </div>
                <div class="col-xs-2 text-right" style="padding-top: 6px">
                    <label for="bathrooms">Bathrooms:</label>
                </div>
                <div class="col-xs-2">
                    <form:input path="bathrooms" type="number" name="bathrooms" class="form-control form-control-lg" value="${requestScope['updatedHouse'].getBathrooms()}"/>
                    <font color="red">  <form:errors path="bathrooms"></form:errors> </font>
                </div>
                <div class="col-xs-2 text-right" style="padding-top: 6px">
                    <label for="balcony">Balconies:</label>
                </div>
                <div class="col-xs-2">
                    <form:input path="balcony" type="number" name="balcony" class="form-control form-control-lg" value="${requestScope['updatedHouse'].getBalcony()}"/>
                    <font color="red">  <form:errors path="balcony"></form:errors> </font>
                </div>
            </div>

            <div class="form-group">
                <div class="col-xs-2 text-right" style="padding-top: 6px">
                    <label for="startdate">Start Date:</label>
                </div>
                <div class="col-xs-4">
                    <input type="date" name="startdate" class="form-control form-control-lg" id="startdate" value="${requestScope['startdate']}"/>
                </div>
                <div class="col-xs-2 text-right" style="padding-top: 6px">
                    <label for="enddate">End Date:</label>
                </div>
                <div class="col-xs-4">
                    <input type="date" name="enddate" class="form-control form-control-lg" id="enddate" value="${requestScope['enddate']}"/>
                        <%--                    <font color="red">  <form:errors path="enddate"></form:errors> </font>--%>
                </div>
            </div>

            <div class="form-group">
                <div class="col-xs-2 text-right" style="padding-top: 6px">
                    <label for="monthrent">Monthly Rent:</label>
                </div>
                <div class="col-xs-4">
                    <form:input path="monthrent" type="text" name="monthrent" class="form-control form-control-lg" value="${requestScope['updatedHouse'].getMonthrent()}"/>
                    <font color="red">  <form:errors path="monthrent"></form:errors> </font>
                </div>
                <div class="col-xs-2 text-right" style="padding-top: 6px">
                    <label for="enddate">Advance Payment:</label>
                </div>
                <div class="col-xs-4">
                    <form:input path="advancepayment" type="text" name="advancepayment" class="form-control form-control-lg" value="${requestScope['updatedHouse'].getAdvancepayment()}"/>
                    <font color="red">  <form:errors path="monthrent"></form:errors> </font>
                </div>
            </div>

            <div class="form-group">
                <div class="col-xs-3 text-right pd-2">
                    <label for="gasbill">Gas Bill Included?:</label>
                </div>
                <div class="col-xs-3">
                    <div class="col-xs-6">
                        <c:if test="${requestScope['updatedHouse'].isGasbill()}">
                            <form:radiobutton path="gasbill"  name="gasbill" value="true" checked="checked" />
                        </c:if>
                        <c:if test="${requestScope['updatedHouse'].isGasbill() eq false}">
                            <form:radiobutton path="gasbill"  name="gasbill" value="true" />
                        </c:if>
                        <label for="Yes">Yes</label>
                    </div>
                    <div class="col-xs-6">
                        <c:if test="${requestScope['updatedHouse'].isGasbill()}">
                            <form:radiobutton path="gasbill"   name="gasbill" value="false" />
                        </c:if>
                        <c:if test="${requestScope['updatedHouse'].isGasbill() eq false}">
                            <form:radiobutton path="gasbill"   name="gasbill" value="false" checked="checked" />
                        </c:if>
                        <label for="No">No</label>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="col-xs-3 text-right pd-2">
                    <label for="waterbill">Water Bill Included?:</label>
                </div>
                <div class="col-xs-3">
                    <div class="col-xs-6">
                        <c:if test="${requestScope['updatedHouse'].isWaterbill()}">
                            <form:radiobutton path="waterbill"  name="waterbill" value="true" checked="checked"/>
                        </c:if>
                        <c:if test="${requestScope['updatedHouse'].isWaterbill() eq false}">
                            <form:radiobutton path="waterbill"  name="waterbill" value="true"/>
                        </c:if>
                        <label for="Yes">Yes</label>
                    </div>
                    <div class="col-xs-6">
                        <c:if test="${requestScope['updatedHouse'].isWaterbill()}">
                            <form:radiobutton path="waterbill" name="waterbill" value="false" />
                        </c:if>
                        <c:if test="${requestScope['updatedHouse'].isWaterbill() eq false}">
                            <form:radiobutton path="waterbill" name="waterbill" value="false" checked="checked"/>
                        </c:if>
                        <label for="No">No</label>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="col-xs-3 text-right pd-2">
                    <label for="maintenancecost">Maintenance Cost Included?:</label>
                </div>
                <div class="col-xs-3">
                    <div class="col-xs-6">
                        <c:if test="${requestScope['updatedHouse'].isMaintenancecost()}">
                            <form:radiobutton path="maintenancecost"  name="maintenancecost" value="true" checked="checked" />
                        </c:if>
                        <c:if test="${requestScope['updatedHouse'].isMaintenancecost() eq false}">
                            <form:radiobutton path="maintenancecost"  name="maintenancecost" value="true" />
                        </c:if>
                        <label for="Yes">Yes</label>
                    </div>
                    <div class="col-xs-6">
                        <c:if test="${requestScope['updatedHouse'].isMaintenancecost()}">
                            <form:radiobutton path="maintenancecost"   name="maintenancecost" value="false" />
                        </c:if>
                        <c:if test="${requestScope['updatedHouse'].isMaintenancecost() eq false}">
                            <form:radiobutton path="maintenancecost"   name="maintenancecost" value="false" checked="checked"/>
                        </c:if>
                        <label for="No">No</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-xs-3 text-right" style="padding-top: 6px">
                    <label for="parking">Parking Available:</label>
                </div>
                <div class="col-xs-6">
                    <form:input path="parking" type="text" name="parking" class="form-control form-control-lg" value="${requestScope['updatedHouse'].getParking()}"/>
                </div>
            </div>

            <div class="form-group">
                <div class="col-xs-3 text-right" style="padding-top: 6px">
                    <label for="description">Additional Description:</label>
                </div>
                <div class="col-xs-6">
                    <form:textarea path="description" name="description" class="form-control form-control-lg" value="${requestScope['updatedHouse'].getDescription()}"/>
                </div>
            </div>
            <input type="hidden" name="houseUpdateId" value="${requestScope['updatedHouse'].getId()}">


            <div class="col-1 text-center">
                <input type="submit" value="Update House" class="btn btn-primary" name="updateHouse">
            </div>

        </fieldset>
    </form:form>
    <br/>
    <br/>
    <br/>
</div>