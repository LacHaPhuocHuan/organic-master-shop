<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <script src="<c:url value='/assets/js/jquery-3.3.1.min.js'/>"></script>
    <!-- https://jquery.com/download/ -->
    <script src="<c:url value='/assets/js/moment.min.js'/>"></script>
    <!-- https://momentjs.com/ -->
    <script src="<c:url value='/assets/js/Chart.min.js'/>"></script>
    <!-- http://www.chartjs.org/docs/latest/ -->
    <script src="<c:url value='/assets/js/bootstrap.min.js'/>"></script>
    <!-- https://getbootstrap.com/ -->
    <script src="<c:url value='/assets/js/tooplate-scripts.js'/>"></script>
    <script>
        Chart.defaults.global.defaultFontColor = 'white';
        let ctxLine,
            ctxBar,
            ctxPie,
            optionsLine,
            optionsBar,
            optionsPie,
            configLine,
            configBar,
            configPie,
            lineChart;
        barChart, pieChart;
        // DOM is ready
        $(function () {
            drawLineChart(); // Line Chart
            drawBarChart(); // Bar Chart
            drawPieChart(); // Pie Chart

            $(window).resize(function () {
                updateLineChart();
                updateBarChart();                
            });
        })
    </script>
</body>
</html>