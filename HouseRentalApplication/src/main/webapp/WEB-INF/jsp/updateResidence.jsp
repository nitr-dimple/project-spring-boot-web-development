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
            <legend class="mt-1">Update Residence</legend>

            <div class="form-group">

                <div class="col-xs-4 text-right" style="padding-top: 6px">
                    <label for="state">Select State:</label>
                </div>
                <div class="col-xs-6">
                    <form:select path="state" name="state" class="form-control form-control-lg" id="state">
                        <option value="${requestScope['updatedResidence'].getState()}" selected hidden>${requestScope['updatedResidence'].getState()}</option>
                        <form:option value="arizona"> Arizona</form:option>
                        <form:option value="california"> California</form:option>
                        <form:option value="florida"> Florida</form:option>
                        <form:option value="indiana"> Indiana</form:option>
                        <form:option value="massachusetts"> Massachusetts</form:option>
                        <form:option value="michigan"> Michigan</form:option>
                        <form:option value="newyork"> New York</form:option>
                        <form:option value="texas"> Texas</form:option>
                    </form:select>
                    <font color="red"> <form:errors path="state"></form:errors> </font>
                </div>

            </div>

            <div class="form-group">

                <div class="col-xs-4 text-right" style="padding-top: 6px">
                    <label for="city">Select City:</label>
                </div>
                <div class="col-xs-6">
                    <form:select path="city" name="city" class="form-control form-control-lg" id="city" >
                        <option value="${requestScope['updatedResidence'].getCity()}" selected hidden>${requestScope['updatedResidence'].getCity()}</option>
                        <form:option value="arlington"> Arlington</form:option>
                        <form:option value="austin"> Austin</form:option>
                        <form:option value="dallas"> Dallas</form:option>
                        <form:option value="malden"> Malden</form:option>
                        <form:option value="medford"> Medford</form:option>
                        <form:option value="boston"> Boston</form:option>
                        <form:option value="newyork"> New York</form:option>
                        <form:option value="natick"> Natick</form:option>
                        <form:option value="newton"> Newton</form:option>
                        <form:option value="norwood"> Norwood</form:option>
                        <form:option value="peabody"> Peabody</form:option>
                        <form:option value="somerville"> Somerville</form:option>
                    </form:select>
                    <font color="red"> <form:errors path="city"></form:errors> </font>
                </div>

            </div>

            <div class="form-group">
                <div class="col-xs-4 text-right" style="padding-top: 6px">
                    <label for="street">Street:</label>
                </div>
                <div class="col-xs-6">
                    <form:input path="street" type="text" name="street" class="form-control form-control-lg" value="${requestScope['updatedResidence'].getStreet()}"/>
                    <font color="red"> <form:errors path="street"></form:errors> </font>
                </div>
            </div>

            <div class="form-group">
                <div class="col-xs-4 text-right" style="padding-top: 6px">
                    <label for="residencename">Residence Name:</label>
                </div>
                <div class="col-xs-6">
                    <form:input path="residencename" type="text" name="residencename" class="form-control form-control-lg" value="${requestScope['updatedResidence'].getResidencename()}"/>
                    <font color="red"> <form:errors path="residencename"></form:errors> </font>
                </div>
            </div>

            <div class="form-group">
                <div class="col-xs-4 text-right" style="padding-top: 6px">
                    <label for="zipcode">Zipcode:</label>
                </div>
                <div class="col-xs-6">
                    <form:input path="zipcode" type="text" name="zipcode" class="form-control form-control-lg" value="${requestScope['updatedResidence'].getZipcode()}"/>
                    <font color="red"> <form:errors path="zipcode"></form:errors> </font>
                </div>
            </div>
            <input type="hidden" name="residenceUpdateId" value="${requestScope['updatedResidence'].getId()}">


            <div class="col-1 text-center">
                <input type="submit" value="Update" class="btn btn-primary" name="updateResidence">
            </div>


        </fieldset>
    </form:form>
</div>

