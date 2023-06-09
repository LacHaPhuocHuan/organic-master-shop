<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Product Admin - Dashboard HTML Template</title>
    <jsp:include page="head.jsp" />
    
    <!--
	Product Admin CSS Template
	https://templatemo.com/tm-524-product-admin
	-->
</head>

<body id="reportsPage">
    <div class="" id="home">
        
        <jsp:include page="header.jsp" />

        <div class="container">
            <div class="row">
                <div class="col">
                    <p class="text-white mt-5 mb-5">Welcome back, <b>Admin</b></p>
                </div>
            </div>
            <!-- row -->
            <div class="row tm-content-row">
                <div class="col-sm-12 col-md-12 col-lg-6 col-xl-6 tm-block-col">
                    <div class="tm-bg-primary-dark tm-block">
                        <h2 class="tm-block-title">Latest Hits</h2>
                        <canvas id="lineChart"></canvas>
                    </div>
                </div>
                <div class="col-sm-12 col-md-12 col-lg-6 col-xl-6 tm-block-col">
                    <div class="tm-bg-primary-dark tm-block">
                        <h2 class="tm-block-title">Performance</h2>
                        <canvas id="barChart"></canvas>
                    </div>
                </div>
                <div class="col-sm-12 col-md-12 col-lg-6 col-xl-6 tm-block-col">
                    <div class="tm-bg-primary-dark tm-block tm-block-taller">
                        <h2 class="tm-block-title">Storage Information</h2>
                        <div id="pieChartContainer">
                            <canvas id="pieChart" class="chartjs-render-monitor" width="200" height="200"></canvas>
                        </div>                        
                    </div>
                </div>
                <div class="col-sm-12 col-md-12 col-lg-6 col-xl-6 tm-block-col">
                    <div class="tm-bg-primary-dark tm-block tm-block-taller tm-block-overflow">
                        <h2 class="tm-block-title">Notification List</h2>
                        <div class="tm-notification-items">
                            Quantity notification: ${notificationList.size()}
                            <c:forEach items = "${notificationList}"  var = "item" varStatus="indexLoop">
                                <div class="media tm-notification-item">
                                    <div class="tm-gray-circle"><img style="    width: 100%;" src="<c:url value='/assets/img/avatar.png'/>" alt="Avatar Image" class="rounded-circle"></div>
                                    <div class="media-body">
                                        <p class="mb-2"><b>${item.user.getEmail()}</b></b> sent you new <a href="#"
                                                class="tm-notification-link">${item.getContent()}</a>. Check new orders.</p>
                                        <span class="tm-small tm-text-color-secondary">${item.getTimeAgo()}</span>
                                    </div>
                                </div>
                            </c:forEach>
                           
                        </div>
                    </div>
                </div>
                <div class="col-12 tm-block-col">
                    <div class="tm-bg-primary-dark tm-block tm-block-taller tm-block-scroll">
                        <h2 class="tm-block-title">Orders List</h2>
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">ORDER NO.</th>
                                    <th scope="col">START DATE</th>
                                    <th scope="col">OPERATORS</th>
                                    <th scope="col">TOTALPRICE</th>
                                    <th scope="col">STATUS</th>
                                    <th scope="col">LOCATION</th>
                                    <th scope="col">PHONE</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items = "${bills}"  var = "item" varStatus="indexLoop">
                                    <tr>
                                        <th scope="row"><b>#${item.getId()}</b></th>
                                        <td><b>${item.getSaleOfDate()}</b></td>
                                        <td><b>${item.getUsername()}</b></td>
                                        <td><b>${item.getTotalPrice()}</b></td>
                                        <td>
                                            <div class="tm-status-circle moving">
                                            </div>${item.getStatus()}
                                        </td>
                                        <td>${item.getLocation()}</td>
                                        <td>${item.getPhone()}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <footer class="tm-footer row tm-mt-small">
            <div class="col-12 font-weight-light">
                <p class="text-center text-white mb-0 px-4 small">
                    Copyright &copy; <b>2018</b> All rights reserved. 
                    
                    Design: <a rel="nofollow noopener" href="https://templatemo.com" class="tm-footer-link">Template Mo</a>
                </p>
            </div>
        </footer>
    </div>
    <jsp:include page="footer.jsp" />

    
</body>

</html>