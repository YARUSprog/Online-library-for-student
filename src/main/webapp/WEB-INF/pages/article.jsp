<%-- 
    Document   : article
    Created on : 31.12.2016, 13:39:01
    Author     : YARUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
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
                    <h1>${article.title}</h1>
                    <div class="text-article">
                        ${article.text}
                    </div>
                    <div class="fotter-article">                        
                        <span >Автори: 
                            <c:forEach var="author" items="${article.usersCollection}">
                                <p style="display: inline;">${author.firstName} ${fn:substring(author.middleName,0,1)}. ${fn:substring(author.lastName,0,1)}.  </p>
                            </c:forEach>
                        </span>           
                        <span class="date-article">Дата статті: 
                                <fmt:formatDate value="${article.date}" pattern="MM-dd-yyyy"/>
                            </span>
                    </div>
                </article>
            </section>
        </div>       
        <%@ include file="/WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
