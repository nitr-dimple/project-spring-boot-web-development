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
      <form method="get" action="${pageContext.request.contextPath}/user/viewResidence.htm">
        <input class="btn btn-lg" style="background-color: black; color: white; height: 50px; width: 200px" type="submit" value="View Residence" name="btnClicked"/>
      </form>

      <form method="post" action="${pageContext.request.contextPath}/user/addPost.htm">
        <h2><input class="btn btn-lg" style="background-color: black; color: white; height: 50px; width: 200px" type="submit" value="Add Residence Photo" name="btnClicked"/></h2>
      </form>

      <form method="post" action="${pageContext.request.contextPath}/user/viewHouse.htm">
        <h2><input class="btn btn-lg" style="background-color: black; color: white; height: 50px; width: 200px" type="submit" value="View House" name="btnClicked"/></h2>
      </form>

      <form method="post" action="${pageContext.request.contextPath}/user/addPost.htm">
<%--        <h2><input class="btn btn-lg" style="background-color: black; color: white; height: 50px; width: 200px" type="submit" value="Add House" name="btnClicked"/></h2>--%>
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


<%--        If clicked on update Residence      --%>
        <c:if test="${requestScope['btnClicked'] != null && requestScope['btnClicked'].equals('Update Residence')}">
          <jsp:include page="updateResidence.jsp" />
        </c:if>

<%--        If click on Add Residence Photos   --%>
        <c:if test="${requestScope['btnClicked'] != null && requestScope['btnClicked'].equals('Add Residence Photo')}">
          <jsp:include page="addResidencePhoto.jsp" />
        </c:if>

<%--        If clicked on View House      --%>
        <c:if test="${requestScope['btnClicked'] != null && requestScope['btnClicked'].equals('View House')}">
          <jsp:include page="viewHouse.jsp" />
        </c:if>

<%--        If click on Add House                               --%>
        <c:if test="${requestScope['btnClicked'] != null && requestScope['btnClicked'].equals('Add House')}">
            <jsp:include page="addHouse.jsp" />
        </c:if>

<%--        If clicked on update House      --%>
          <c:if test="${requestScope['btnClicked'] != null && requestScope['btnClicked'].equals('Update House')}">
              <jsp:include page="updateHouse.jsp" />
          </c:if>

<%--        If click on Add House Photos            --%>
        <c:if test="${requestScope['btnClicked'] != null && requestScope['btnClicked'].equals('Add House Photo')}">
            <jsp:include page="addHousePhoto.jsp" />
        </c:if>

      </div>

    </div>
  </div>
</div>

<script>
    $(document).ready(function () {
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
    })
</script>
<jsp:include page="footer.jsp" />

