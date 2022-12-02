<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<jsp:include page="header.jsp" />
<br>
<div class="container-fluid">
    <div class="col-xs-12 col-sm-12">
        <form:form modelAttribute="user" method="POST" class="form-horizontal">
            <fieldset>
                <legend class="mt-1">Create an Account</legend>

                <div class="form-group">
                    <div class="col-xs-3 text-right pd-2">
                        <label for="userType">Register As:</label>
                    </div>
                    <div class="col-xs-4">
                        <form:select path="usertype" name="userType" class="form-control form-control-lg" id="userType">
                            <option value="owner"> House Owner</option>
                            <option value="seeker"> House Seeker</option>
                        </form:select>
                        <font color="red"> <form:errors path="usertype"></form:errors> </font>
                    </div>

                </div>

                <div class="form-group">
                    <div class="col-xs-3 text-right pd-2">
                        <label for="fullname">Full Name:</label>
                    </div>
                    <div class="col-xs-4">
                        <form:input path="fullname" type="text" name="fullname" class="form-control form-control-lg" />
                        <font color="red"> <form:errors path="fullname"></form:errors> </font>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-xs-3 text-right pd-2">
                        <label for="email">E-mail:</label>
                    </div>
                    <div class="col-xs-4">
                        <form:input path="email" type="text" name="email" class="form-control form-control-lg" />
                        <font color="red"> <form:errors path="email"></form:errors> </font>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-xs-3 text-right pd-2">
                        <label for="password">Password:</label>
                    </div>
                    <div class="col-xs-4">
                        <form:input path="password" type="password" name="password" class="form-control form-control-lg" />
                        <font color="red"> <form:errors path="password"></form:errors> </font>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-xs-3 text-right pd-2">
                        <label for="gender">Gender:</label>
                    </div>
                    <div class="col-xs-4">
                        <div class="col-xs-3">
                            <form:radiobutton path="gender"  name="gender" value="Male" />
                            <label for="male">Male</label>
                        </div>
                        <div class="col-xs-4">
                            <form:radiobutton path="gender"   name="gender" value="Female" />
                            <label for="female">Female</label>
                        </div>
                        <div class="col-xs-4">
                            <form:radiobutton path="gender" name="gender" value="Female" />
                            <label for="other">Other</label>
                        </div>
                        <font color="red">  <form:errors path="gender"></form:errors> </font>
                    </div>
                </div>

                <div class="col-1 text-center">
                    <input type="submit" value="Submit" class="btn btn-primary">
                </div>
            </fieldset>
        </form:form>
    </div>
</div>

<jsp:include page="footer.jsp" />
