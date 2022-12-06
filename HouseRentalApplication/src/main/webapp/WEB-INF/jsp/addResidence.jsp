<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<div class="col-xs-12 col-sm-12">

  <%--@elvariable id="residence" type=""--%>
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
        <input type="submit" value="Add Residence " class="btn btn-primary" name="addResidence">
      </div>


    </fieldset>
  </form:form>
</div>

