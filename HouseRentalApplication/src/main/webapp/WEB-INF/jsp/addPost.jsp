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
      <form method="post" action="${pageContext.request.contextPath}/user/addPost.htm">

        <br>
        <input class="btn btn-lg" style="background-color: black; color: white; height: 50px; width: 200px" type="submit" value="Add Residence" name="btnClicked"/>
        <h2><input class="btn btn-lg" style="background-color: black; color: white; height: 50px; width: 200px" type="submit" value="Add Residence Photo" name="btnClicked"/></h2>
        <h2><input class="btn btn-lg" style="background-color: black; color: white; height: 50px; width: 200px" type="submit" value="Add House" name="btnClicked"/></h2>
        <h2><input class="btn btn-lg" style="background-color: black; color: white; height: 50px; width: 200px" type="submit" value="Add House Details" name="btnClicked"/></h2>
      </form>
    </div>

    <div style="float: right; width: 70%; min-height: 500px; margin-right: 50px; border:1px solid black; ">

      <div style="float: left; margin-left: 30px; width: 90%;">
       <br>

<%--        If clicked on Add Residence      --%>
        <c:if test="${requestScope['btnClicked'] != null && requestScope['btnClicked'].equals('Add Residence')}">
          <div class="col-xs-12 col-sm-12">

            <form:form modelAttribute="residence" method="POST" class="form-horizontal">
              <fieldset>
                <c:if test="${requestScope['residenceAdded'] != null}">
                  <div class="form-group">
                    <div class="col-xs-8">
                      <font color="green"><c:out value="${requestScope['residenceAdded']}" ></c:out> </font>
                    </div>
                  </div>
                </c:if>
                <legend class="mt-1">Add new Residence</legend>

                <div class="form-group">

                  <div class="col-xs-4 text-right" style="padding-top: 6px">
                    <label for="state">Select State:</label>
                  </div>
                  <div class="col-xs-6">
                    <form:select path="state" name="state" class="form-control form-control-lg" id="state">
                      <option value="arizona"> Arizona</option>
                      <option value="california"> California</option>
                      <option value="florida"> Florida</option>
                      <option value="indiana"> Indiana</option>
                      <option value="massachusetts"> Massachusetts</option>
                      <option value="michigan"> Michigan</option>
                      <option value="newyork"> New York</option>
                      <option value="texas"> Texas</option>
                    </form:select>
                    <font color="red"> <form:errors path="state"></form:errors> </font>
                  </div>

                </div>

                <div class="form-group">

                  <div class="col-xs-4 text-right" style="padding-top: 6px">
                    <label for="city">Select City:</label>
                  </div>
                  <div class="col-xs-6">
                    <form:select path="city" name="city" class="form-control form-control-lg" id="city">

                      <option value="arlington"> Arlington</option>
                      <option value="austin"> Austin</option>
                      <option value="dallas"> Dallas</option>
                      <option value="malden"> Malden</option>
                      <option value="medford"> Medford</option>
                      <option value="boston"> Boston</option>
                      <option value="newyork"> New York</option>
                      <option value="natick"> Natick</option>
                      <option value="newton"> Newton</option>
                      <option value="norwood"> Norwood</option>
                      <option value="peabody"> Peabody</option>
                      <option value="somerville"> Somerville</option>
                    </form:select>
                    <font color="red"> <form:errors path="city"></form:errors> </font>
                  </div>

                </div>

                <div class="form-group">
                  <div class="col-xs-4 text-right" style="padding-top: 6px">
                    <label for="street">Street:</label>
                  </div>
                  <div class="col-xs-6">
                    <form:input path="street" type="text" name="street" class="form-control form-control-lg" />
                    <font color="red"> <form:errors path="street"></form:errors> </font>
                  </div>
                </div>

                <div class="form-group">
                  <div class="col-xs-4 text-right" style="padding-top: 6px">
                    <label for="residencename">Residence Name:</label>
                  </div>
                  <div class="col-xs-6">
                    <form:input path="residencename" type="text" name="residencename" class="form-control form-control-lg" />
                    <font color="red"> <form:errors path="residencename"></form:errors> </font>
                  </div>
                </div>

                <div class="form-group">
                  <div class="col-xs-4 text-right" style="padding-top: 6px">
                    <label for="zipcode">Zipcode:</label>
                  </div>
                  <div class="col-xs-6">
                    <form:input path="zipcode" type="text" name="zipcode" class="form-control form-control-lg" />
                    <font color="red"> <form:errors path="zipcode"></form:errors> </font>
                  </div>
                </div>

                <div class="col-1 text-center">
                  <input type="submit" value="Add Residence" class="btn btn-primary" name="addResidence">
                </div>

              </fieldset>
            </form:form>
          </div>

        </c:if>

<%--        If click on Add Residence Photos            --%>
        <c:if test="${requestScope['btnClicked'] != null && requestScope['btnClicked'].equals('Add Residence Photo')}">
          <div class="col-xs-12 col-sm-12">

            <form method="POST" class="form-horizontal" enctype="multipart/form-data" action="${pageContext.request.contextPath}/user/addResidencePhoto.htm">
              <fieldset>

                <c:if test="${requestScope['uploadSuccess'] != null}">
                  <div class="form-group">
                    <div class="col-xs-8">
                      <font color="green"><c:out value="${requestScope['uploadSuccess']}" ></c:out> </font>
                    </div>
                  </div>
                </c:if>
                <legend class="mt-1">Add Residence Photos</legend>

                <div class="form-group">

                  <div class="col-xs-3 text-right" style="padding-top: 6px">
                    <label for="state">Select Residence:</label>
                  </div>
                  <div class="col-xs-9">
                    <select  name="residence" class="form-control form-control-lg" id="residence">
                      <c:forEach var="residence" items="${requestScope['residenceList']}">
                        <option value="${residence.getId()}">
                          <c:out value="${residence}"></c:out>
                        </option>
                      </c:forEach>
                    </select>
                  </div>

                </div>

                <div class="form-group">
                  <div class="col-xs-3 text-right" style="padding-top: 6px">
                    <label for="imagename">Upload Image:</label>
                  </div>
                  <div class="col-xs-9">
                    <input type="file" name="imagename" class="form-control form-control-lg" required/>
                  </div>
                </div>

                <div class="col-1 text-center">
                  <input type="submit" value="Upload Residence" class="btn btn-primary" name="uploadResidencePhoto">
                </div>

              </fieldset>
            </form>
          </div>

        </c:if>


<%--        If click on Add House                               --%>

        <c:if test="${requestScope['btnClicked'] != null && requestScope['btnClicked'].equals('Add House')}">
          <div class="col-xs-12 col-sm-12">

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
                <legend class="mt-1">Add House Details</legend>

                <div class="form-group">
                  <div class="col-xs-3 text-right" style="padding-top: 6px">
                    <label for="state">Select Residence:</label>
                  </div>
                  <div class="col-xs-9">
                    <form:select path="residenceId"  name="residence" class="form-control form-control-lg" id="residence">
                      <c:forEach var="residence" items="${requestScope['residenceList']}">
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
                    <form:input path="houseno" type="text" name="houseno" class="form-control form-control-lg"/>
                    <font color="red">  <form:errors path="houseno"></form:errors> </font>
                  </div>
                  <div class="col-xs-2 text-right" style="padding-top: 6px">
                    <label for="totalarea">Total Area:</label>
                  </div>
                  <div class="col-xs-2">
                    <form:input path="totalarea" type="text" name="totalarea" class="form-control form-control-lg"/>
                    <font color="red">  <form:errors path="totalarea"></form:errors> </font>
                  </div>
                  <div class="col-xs-2 text-right" style="padding-top: 6px">
                    <label for="bedrooms">Bedrooms:</label>
                  </div>
                  <div class="col-xs-2">
                    <form:input path="bedrooms" type="number" name="bedrooms" class="form-control form-control-lg"/>
                    <font color="red">  <form:errors path="bedrooms"></form:errors> </font>
                  </div>
                </div>

                <div class="form-group">
                  <div class="col-xs-2 text-right" style="padding-top: 6px">
                    <label for="livingrooms">Living Rooms:</label>
                  </div>
                  <div class="col-xs-2">
                    <form:input path="livinrooms" type="number" name="livingrooms" class="form-control form-control-lg"/>
                    <font color="red">  <form:errors path="livinrooms"></form:errors> </font>
                  </div>
                  <div class="col-xs-2 text-right" style="padding-top: 6px">
                    <label for="bathrooms">Bathrooms:</label>
                  </div>
                  <div class="col-xs-2">
                    <form:input path="bathrooms" type="number" name="bathrooms" class="form-control form-control-lg"/>
                    <font color="red">  <form:errors path="bathrooms"></form:errors> </font>
                  </div>
                  <div class="col-xs-2 text-right" style="padding-top: 6px">
                    <label for="balcony">Balconies:</label>
                  </div>
                  <div class="col-xs-2">
                    <form:input path="balcony" type="number" name="balcony" class="form-control form-control-lg"/>
                    <font color="red">  <form:errors path="balcony"></form:errors> </font>
                  </div>
                </div>

                <div class="form-group">
                  <div class="col-xs-2 text-right" style="padding-top: 6px">
                    <label for="startdate">Start Date:</label>
                  </div>
                  <div class="col-xs-4">
                    <input type="date" name="startdate" class="form-control form-control-lg" id="startdate"/>
<%--                    <font color="red">  <form:errors path="startdate"></form:errors> </font>--%>
                  </div>
                  <div class="col-xs-2 text-right" style="padding-top: 6px">
                    <label for="enddate">End Date:</label>
                  </div>
                  <div class="col-xs-4">
                    <input type="date" name="enddate" class="form-control form-control-lg" id="enddate"/>
<%--                    <font color="red">  <form:errors path="enddate"></form:errors> </font>--%>
                  </div>
                </div>

                <div class="form-group">
                  <div class="col-xs-2 text-right" style="padding-top: 6px">
                    <label for="monthrent">Monthly Rent:</label>
                  </div>
                  <div class="col-xs-4">
                    <form:input path="monthrent" type="text" name="monthrent" class="form-control form-control-lg"/>
                    <font color="red">  <form:errors path="monthrent"></form:errors> </font>
                  </div>
                  <div class="col-xs-2 text-right" style="padding-top: 6px">
                    <label for="enddate">Advance Payment:</label>
                  </div>
                  <div class="col-xs-4">
                    <form:input path="advancepayment" type="text" name="monthrent" class="form-control form-control-lg"/>
                    <font color="red">  <form:errors path="monthrent"></form:errors> </font>
                  </div>
                </div>

                <div class="form-group">
                  <div class="col-xs-3 text-right pd-2">
                    <label for="gasbill">Gas Bill Included?:</label>
                  </div>
                  <div class="col-xs-3">
                    <div class="col-xs-6">
                      <form:radiobutton path="gasbill"  name="gasbill" value="true" />
                      <label for="Yes">Yes</label>
                    </div>
                    <div class="col-xs-6">
                      <form:radiobutton path="gasbill"   name="gasbill" value="false" />
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
                      <form:radiobutton path="waterbill"  name="waterbill" value="true" />
                      <label for="Yes">Yes</label>
                    </div>
                    <div class="col-xs-6">
                      <form:radiobutton path="waterbill"   name="waterbill" value="false" />
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
                      <form:radiobutton path="maintenancecost"  name="maintenancecost" value="true" />
                      <label for="Yes">Yes</label>
                    </div>
                    <div class="col-xs-6">
                      <form:radiobutton path="maintenancecost"   name="maintenancecost" value="false" />
                      <label for="No">No</label>
                    </div>
                  </div>
                </div>
                <div class="form-group">
                  <div class="col-xs-3 text-right" style="padding-top: 6px">
                    <label for="parking">Parking Available:</label>
                  </div>
                  <div class="col-xs-6">
                    <form:input path="parking" type="text" name="parking" class="form-control form-control-lg"/>
                  </div>
                </div>

                <div class="form-group">
                  <div class="col-xs-3 text-right" style="padding-top: 6px">
                    <label for="description">Additional Description:</label>
                  </div>
                  <div class="col-xs-6">
                    <form:textarea path="description" name="description" class="form-control form-control-lg"/>
                  </div>
                </div>

                <div class="col-1 text-center">
                  <input type="submit" value="Add House" class="btn btn-primary" name="addHouse">
                </div>

              </fieldset>
            </form:form>
            <br/>
            <br/>
            <br/>
          </div>

        </c:if>

      </div>

    </div>
  </div>
</div>

<jsp:include page="footer.jsp" />

