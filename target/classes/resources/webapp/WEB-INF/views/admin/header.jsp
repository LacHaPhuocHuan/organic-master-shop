<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <c:set var="currentUrl" value="${pageContext.request.requestURL}${pageContext.request.queryString != null ? '?' + pageContext.request.queryString : ''}" />
    <nav class="navbar navbar-expand-xl">
        <div class="container h-100">
            <a class="navbar-brand" href="index.html">
                <h1 class="tm-site-title mb-0">Product Admin</h1>
            </a>
            <button class="navbar-toggler ml-auto mr-0" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <i class="fas fa-bars tm-nav-icon"></i>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mx-auto h-100">
                    <li class="nav-item">
                       
                           
                            <c:if test="${fn:endsWith(currentUrl,'index.jsp')}">
                                <a class="nav-link active"  href="<c:url value='/admin/home' />">
                                    <i class="fas fa-tachometer-alt"></i>
                                    Dashboard
                                  <span class="sr-only">(current)</span>
                                </a>
                            </c:if>
                            <c:if test="${!fn:endsWith(currentUrl,'index.jsp')}">
                                <a class="nav-link"  href="<c:url value='/admin/home' />">
                                    <i class="fas fa-tachometer-alt"></i>
                                    Dashboard
                                </a>
                            </c:if>
                   
                       
                    </li>
                    <li class="nav-item">
                        <c:if test="${fn:endsWith(currentUrl,'products.jsp')}">
                            <a class="nav-link active" href="<c:url value='/admin/product/' />">
                                <i class="fas fa-tachometer-alt"></i>
                                Products
                              <span class="sr-only">(current)</span>
                            </a>
                        </c:if>
                        <c:if test="${!fn:endsWith(currentUrl,'products.jsp')}">
                            <a class="nav-link " href="<c:url value='/admin/product/' />">
                                <i class="fas fa-tachometer-alt"></i>
                                Products
                            </a>
                        </c:if>
                        
                    </li>

                    <li class="nav-item">
                        <c:if test="${fn:endsWith(currentUrl,'accounts.jsp')}">
                            <a class="nav-link active" href="<c:url value='/admin/account/' />">
                                <i class="fas fa-tachometer-alt"></i>
                                Accounts
                              <span class="sr-only">(current)</span>
                            </a>
                        </c:if>
                        <c:if test="${!fn:endsWith(currentUrl,'accounts.jsp')}">
                            <a class="nav-link " href="<c:url value='/admin/account/' />">
                                <i class="fas fa-tachometer-alt"></i>
                                Accounts
                            </a>
                        </c:if>
                       
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
                            aria-haspopup="true" aria-expanded="false">
                            <i class="fas fa-cog"></i>
                            <span>
                                Settings <i class="fas fa-angle-down"></i>
                            </span>
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="#">Profile</a>
                            <a class="dropdown-item" href="#">Billing</a>
                            <a class="dropdown-item" href="#">Customize</a>
                        </div>
                    </li>
                </ul>
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link d-block" href="login.html">
                            Admin, <b>Logout</b>
                        </a>
                    </li>
                </ul>
                <ul class="navbar-nav">
                    <a href="<c:url value='/api/u/home'/>">HOME</a>
                </ul>
            </div>
        </div>
    </nav>
</body>
</html>