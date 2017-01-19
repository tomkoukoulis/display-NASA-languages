<%-- 
    Document   : languages
    Created on : 16-Jan-2017, 21:41:09
    Author     : tomkoukoulis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.bundle.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>NASA Languages</title>
    </head>
    <body>
        <c:if test="${not empty error-message}">
            <p>An error occurred: ${error-message}</p>
        </c:if>
        <c:if test="${not empty languages}">
        <canvas id="myChart" width="400" height="400"></canvas>
        <script>
            var labelsArray = Array();
            var dataArray = Array();
            <c:forEach items="${languages}" var="languages" varStatus="status">
                labelsArray.push("${languages.key}");
                dataArray.push(${languages.value});
            </c:forEach>

            var ctx = document.getElementById("myChart");
            var myChart = new Chart(ctx, {
                type: 'horizontalBar',
                data: {
                    labels: labelsArray,
                    datasets: [{
                        data: dataArray
                    }]
                }
            });
        </script>
        <table>
            <c:forEach items="${languages}" var="languages" varStatus="status">
                <tr>
                    <td>${languages.key}</td>
                    <td>${languages.value}</td>
                </tr>
            </c:forEach>
        </table>
        </c:if>
    </body>
</html>
