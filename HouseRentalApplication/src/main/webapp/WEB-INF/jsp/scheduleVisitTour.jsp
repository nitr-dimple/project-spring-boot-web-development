<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<div class="col-xs-12 col-sm-12">
        <%--@elvariable id="schedule" type=""--%>
        <form:form modelAttribute="schedule" method="POST" class="form-horizontal">
            <fieldset>
                <legend class="mt-1">Scheduling Details:</legend>

                <div class="form-group">
                    <div class="col-xs-3 text-right pd-2">
                        <label for="name">Name:</label>
                    </div>
                    <div class="col-xs-6">
                        <form:input path="name"  type="text" name="email" class="form-control form-control-lg" id="name" />
                        <font color="red"> <form:errors path="name"></form:errors> </font>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-xs-3 text-right pd-2">
                        <label for="email">Contact E-mail:</label>
                    </div>
                    <div class="col-xs-6">
                        <form:input path="email"  type="text" name="email" class="form-control form-control-lg" id="email" />
                        <font color="red"> <form:errors path="email"></form:errors> </font>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-xs-3 text-right pd-2">
                        <label for="phone">Phone Number:</label>
                    </div>
                    <div class="col-xs-6">
                        <form:input path="phonenumber"  type="text" name="phone" class="form-control form-control-lg" id="phone" />
                        <font color="red"> <form:errors path="phonenumber"></form:errors> </font>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-xs-3 text-right" style="padding-top: 6px">
                        <label for="scheduleDate">Sceduled Date:</label>
                    </div>
                    <div class="col-xs-6">
                        <form:input type="date" path="scheduleDate"  name="scheduleDate" class="form-control form-control-lg" id="scheduleDate"></form:input>
                        <font color="red"> <form:errors path="scheduleDate"></form:errors> </font>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-xs-3 text-right" style="padding-top: 6px">
                        <label for="scheduledTime">Scheduled Time with Timezone:</label>
                    </div>
                    <div class="col-xs-6">
                        <form:input path="scheduledTime"  name="scheduledTime" class="form-control form-control-lg" id="scheduledTime"></form:input>
                        <font color="red"> <form:errors path="scheduledTime"></form:errors> </font>
                    </div>
                </div>

                <div class="form-group" >
                    <div class="col-xs-8 text-right" style="padding-top: 6px; padding-left: 20px;">
                        <input type="hidden" name="visitHouseId" value="${requestScope['visitHouseId']}">
                        <input type="submit" value="Confirm Visiting" class="btn btn-primary" name="confirmVisiting">
                    </div>
                </div>

            </fieldset>
        </form:form>
    <br/>
    <br/>
    <br/>
</div>


