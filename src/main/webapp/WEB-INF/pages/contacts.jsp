<%-- 
    Document   : article
    Created on : 31.12.2016, 13:39:01
    Author     : YARUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Главная страница блога</title>
    </head>
    <body> 
        <%@ include file="/WEB-INF/jspf/header.jspf" %>
        <div id="main">              
            ${param.name}
            <section>
                <article>
                    <h1>Контакти</h1>
                    <div class="text-article">
                        Відповідальний викладач: Радішевський <br>
                        Кафедра ІПЗ: 6.303
                    </div>
                    <div>
                        <div class="customer-field" id="map" style="width: auto; height: 270px;"></div>
                    </div>
                </article>
            </section>
        </div>     
        <%@ include file="/WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
<script>         
    var lat = 50.438057;
    var lng = 30.432067;   
    
    function initMap() {
        var map_center = new google.maps.LatLng(lat, lng);
        var mapOptions = {
            center: map_center,
            zoom: 16,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        var mapCanvas = document.getElementById("map");
        var map = new google.maps.Map(mapCanvas, mapOptions);
        new google.maps.Marker({
            map: map,
            draggable: false,
            position: new google.maps.LatLng(lat, lng)
        });
        google.maps.event.addDomListener(window, 'resize', function () {
            map.setCenter(map_center);
        });
    }    
</script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCT7tBQN8l0fcDdcZUwuxD0XGjgM7qbTL4&callback=initMap"
async defer></script>
<%--Google API Key: AIzaSyCT7tBQN8l0fcDdcZUwuxD0XGjgM7qbTL4 --%>
