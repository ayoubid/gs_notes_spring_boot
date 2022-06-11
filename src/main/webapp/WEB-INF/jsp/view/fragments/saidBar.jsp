<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>

<div class="container-fluid">
    <div class="row flex-nowrap">
        <div class="col-auto col-md-3 col-xl-2 px-sm-2 px-0 bg-light">
            <div class="d-flex flex-column align-items-center align-items-sm-start px-3 pt-2 text-dark min-vh-100">
                <a href="${pageContext.request.contextPath}/cadreadmin/showHome" class="d-flex align-items-center pb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
                    <span class="fs-5 d-none d-sm-inline">Home</span>
                </a>
                <ul class="nav nav-pills flex-column mb-sm-auto mb-0 align-items-center align-items-sm-start" id="menu">

                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/cadreadmin/inscription" class="nav-link align-middle px-0">
                            <i class="fs-4 bi-house"></i> <span class="ms-1 d-none d-sm-inline">Inscription et reinscription</span>
                        </a>
                    </li>

                </ul>
            </div>
        </div>
        <div class="col py-3">
