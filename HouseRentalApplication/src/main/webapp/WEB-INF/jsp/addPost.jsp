<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp" />

<div class="container-fluid">
  <br>
  <br>
  <br>
  <br>
  <div style="width: 100%;" class="form-group">
    <div style="float: left; width: 20%; height:500px; margin-left: 50px;border:1px solid black; text-align: center ">
      <form method="post" action="${pageContext.request.contextPath}/user/addPost.htm">

        <br>
        <input class="btn btn-lg" style="background-color: black; color: white; height: 50px; width: 200px" type="submit" value="Add Residence" name="btnClicked"/>
        <h2><input class="btn btn-lg" style="background-color: black; color: white; height: 50px; width: 200px" type="submit" value="Add Residence Photo" name="btnClicked"/></h2>
        <h2><input class="btn btn-lg" style="background-color: black; color: white; height: 50px; width: 200px" type="submit" value="Add House" name="btnClicked"/></h2>
        <h2><input class="btn btn-lg" style="background-color: black; color: white; height: 50px; width: 200px" type="submit" value="Add House Details" name="btnClicked"/></h2>
      </form>
    </div>

    <div style="float: right; width: 70%; height: 500px; margin-right: 50px; border:1px solid black; ">

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

      </div>

    </div>
  </div>
</div>

<jsp:include page="footer.jsp" />

