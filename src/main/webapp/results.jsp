<%-- 
    Document   : results
    Created on : 27/11/2016, 08:44:16 PM
    Author     : EQ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Resultados</title>
        <link rel="stylesheet" href="css/foundation.css">
        <link rel="stylesheet" href="css/foundation.min.css">
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.4.2.min.js"></script>
        <script src="http://code.highcharts.com/highcharts.js"></script>
        <c:set var="resultados" scope="session" value="${sessionScope.resultados}"/>
<script type="text/javascript">
                    $(function() {

  $(document).ready(function() {

    // Build the chart
    Highcharts.chart('container', {
      chart: {
        plotBackgroundColor: null,
        plotBorderWidth: null,
        plotShadow: false,
        type: 'pie'
      },
      title: {
        text: 'Así es como te sientes'
      },
      tooltip: {
        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
      },
      plotOptions: {
        pie: {
          allowPointSelect: true,
          cursor: 'pointer',
          dataLabels: {
            enabled: false
          },
          showInLegend: true
        }
      },
      series: [{
        name: 'Moods',
        colorByPoint: true,
        data: [{
          name: 'Emociones positivas',
          y: 81.27
        }, {
          name: 'Emociones negativas',
          y: 18.73
          //sliced: true,
          //selected: true
        }, {
          name: 'Emociones neutras',
          y: 0.0
        }]
      }]
    });
  });
});
                </script>
    </head>
    <body>
        <div class="row">
            <div class="columns large-12">
                <h1>Resultados</h1>
            </div>
        </div>
        <br>
        <div class="row">
            <div id="container" style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto">
                
            </div>
        </div>
        <br>
        <div class="row">
            <div class="large-5 columns"><p></p></div>
            <div class="large-2 columns">
                <a href="main.jsp" class="button">Regresar</a>
            </div>
            <div class="large-5 columns"><p></p></div>
        </div>
    </body>
</html>
