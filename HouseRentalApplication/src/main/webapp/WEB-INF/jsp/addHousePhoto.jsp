<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
                        <option value="none" disabled selected hidden>Select Residence</option>
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

