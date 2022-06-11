<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags"%>


<jsp:include page="../fragments/userheader.jsp" />
<div class="container">

    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">



            <jsp:include page="../fragments/usermenu.jsp" />


        </div>
    </nav>

    <jsp:include page="../fragments/saidBar.jsp" />






    <div>
        <h3>Importing Excel file for inscription/re-inscription</h3>
        <c:if test="${error!=null}">
            <div class="alert alert-danger" role="alert">${error}</div>
        </c:if>
        <c:if test="${success!=null}">
            <div class="alert alert-success" role="alert">${success}</div>
        </c:if>
        <form action="${pageContext.request.contextPath}/cadreadmin/inscription" method="post" enctype="multipart/form-data">
            <div class="form-group mb-2 d-flex">
                <label for="excel" class="btn btn-primary mx-auto">Select an Excel File</label>
                <input required name="excel" type="file" class="form-control" id="excel"  placeholder="Select File" hidden>
            </div>

            <button id="submit" type="submit" class="btn btn-success">Submit</button>
        </form>
    </div>


    <c:if test="${change!=null&&change.size()!=0}">
        <div>
            <p>Some records has been changed from the last database modification, select the one you want us to update?</p>
            <form action="${pageContext.request.contextPath}/cadreadmin/inscription" method="post">
                <input type="hidden" name="_method" value="update" />
                <table class="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Nom</th>
                        <th scope="col">Prenom</th>
                        <th scope="col">CNE</th>
                        <th scope="col">#</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="student" items="${change}" >
                        <tr>
                            <th scope="row"><c:out value="${student.getId()}" /></th>
                            <td><c:out value="${student.getNom()}" /></td>
                            <td><c:out value="${student.getPrenom()}" /></td>
                            <td><c:out value="${student.getCne()}" /></td>
                            <td><input type="checkbox" name="data"
                                       value="${student.getId()}-${student.getNom()}-${student.getPrenom()}-${student.getCne()}"
                            ></td>
                        </tr>

                    </c:forEach>

                    </tbody>
                </table>
                <button id="update" type="submit" class="btn btn-success mx-auto">Update</button>
            </form>
        </div>
    </c:if>












    <script>
        document.getElementById("submit").addEventListener("click",()=>{
           if(!document.getElementById("excel").value)
               alert("You need to insert a file first");
        });
    </script>



    <jsp:include page="../fragments/userfooter.jsp" />

