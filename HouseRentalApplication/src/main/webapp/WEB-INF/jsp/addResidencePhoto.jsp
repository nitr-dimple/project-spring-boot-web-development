<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
          <label for="residence">Select Residence:</label>
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
