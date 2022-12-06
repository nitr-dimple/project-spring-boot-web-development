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
      <br>
      <form method="post" action="${pageContext.request.contextPath}/user/viewPost.htm">
        <input class="btn btn-lg" style="background-color: black; color: white; height: 50px; width: 200px" type="submit" value="View Residence" name="btnClicked"/>
      </form>

      <form method="post" action="${pageContext.request.contextPath}/user/addPost.htm">
        <h2><input class="btn btn-lg" style="background-color: black; color: white; height: 50px; width: 200px" type="submit" value="Add Residence Photo" name="btnClicked"/></h2>
        <h2><input class="btn btn-lg" style="background-color: black; color: white; height: 50px; width: 200px" type="submit" value="Add House" name="btnClicked"/></h2>
        <h2><input class="btn btn-lg" style="background-color: black; color: white; height: 50px; width: 200px" type="submit" value="Add House Photo" name="btnClicked"/></h2>
      </form>
    </div>

    <div style="float: right; width: 70%; min-height: 500px; margin-right: 50px; border:1px solid black; ">

      <div style="float: left; margin-left: 30px; width: 90%;">
       <br>

<%--        If clicked on View Residence      --%>
        <c:if test="${requestScope['btnClicked'] != null && requestScope['btnClicked'].equals('View Residence')}">
          <jsp:include page="viewResidence.jsp" />
        </c:if>

<%--        If clicked on Add Residence      --%>
        <c:if test="${requestScope['btnClicked'] != null && requestScope['btnClicked'].equals('Add Residence')}">
          <jsp:include page="addResidence.jsp" />
        </c:if>


<%--        If clicked on Add Residence      --%>
        <c:if test="${requestScope['btnClicked'] != null && requestScope['btnClicked'].equals('Update Residence')}">
          <jsp:include page="updateResidence.jsp" />
        </c:if>

<%--        If click on Add Residence Photos   --%>
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
                    <input type="file" name="imagename" class="form-control form-control-lg" id="imagename" required/>
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

        <%--        If click on Add House Photos            --%>
        <c:if test="${requestScope['btnClicked'] != null && requestScope['btnClicked'].equals('Add House Photo')}">
          <div class="col-xs-12 col-sm-12">

            <form method="POST" class="form-horizontal" enctype="multipart/form-data" action="${pageContext.request.contextPath}/user/addHousePhoto.htm">
              <fieldset>

                <c:if test="${requestScope['uploadSuccess'] != null}">
                  <div class="form-group">
                    <div class="col-xs-8">
                      <font color="green"><c:out value="${requestScope['uploadSuccess']}" ></c:out> </font>
                    </div>
                  </div>
                </c:if>
                <legend class="mt-1">Add House Photos</legend>

                <div class="form-group">
                  <div class="col-xs-3 text-right" style="padding-top: 6px">
                    <label for="residenceId">Select Residence:</label>
                  </div>
                  <div class="col-xs-9">
                    <select  name="residenceId" class="form-control form-control-lg" id="residenceId">
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
                    <label for="houseId">Select House:</label>
                  </div>
                  <div class="col-xs-9">
                    <select  name="houseId" class="form-control form-control-lg" id="houseId">

                    </select>
                  </div>
                </div>

                <div class="form-group">
                  <div class="col-xs-3 text-right" style="padding-top: 6px">
                    <label for="houseImage">Upload Image:</label>
                  </div>
                  <div class="col-xs-9">
                    <input type="file" name="houseImage" class="form-control form-control-lg" id="houseImage" required/>
                  </div>
                </div>

                <div class="col-1 text-center">
                  <input type="submit" value="Upload House" class="btn btn-primary" name="uploadHousePhoto">
                </div>

              </fieldset>
            </form>
          </div>

        </c:if>

      </div>

    </div>
  </div>
</div>

<script>
  $('select[name="residenceId"]').on('change', function (){
    var residenceId = $(this).val();
    if(residenceId){
      $.ajax({
        url: "/HouseRental/user/residence/" + residenceId,
        type: "GET",
        dataType: "json",
        success: function(data) {
          console.log(data);
          $('select[name="houseId"]').empty();
          $.each(data, function (key, value) {
            $('select[name="houseId"]').append("<option value=" + value.id + ">" + value.houseno + "</option>");
          });
        }
      });
    }else{
      $('select[name="houseId"]').empty();
    }
  });
</script>
<jsp:include page="footer.jsp" />

