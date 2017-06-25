<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

    <head>
        <!--<link rel="stylesheet" type="text/css" href="${springCss}" />-->
        <link rel="stylesheet" type="text/css" href="<s:url value="/css/style.css"/>">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Главная страница блога</title>


    </head>
    <body>   
        <%@ include file="/WEB-INF/jspf/header.jspf" %> 
        
        <div class="search">
            <div class="group-search">
                <form action="/index" method="GET" id="searchForm">
                    <input id="searchText" class="search-text" type="text" name="searchText" placeholder="Пошук по статтям" value="${searchText}" />
                    <select class="search-select" name="searchType" id="searchType">
                        <option name="selectType" value="Title">Назва</option>                
                        <option name="selectType" value="Author">Автор</option>             
                    </select>
                    <input class="search-button" type="submit" value="Пошук" id="searchButton"/>
                    <!--<div class="select-editable">
                        <select onchange="this.nextElementSibling.value = this.value">                            
                            <option value="Політ">Політ</option>
                            <option value="Друга концеренція">Друга концеренція</option>
                            <option value="Ще якась">Ще якась</option>
                        </select>
                        <input type="search" name="theme" placeholder="Конференція2" value="" />
                    </div>-->                      
                    <p>Конференція: </p> 
                    <select class="search-select" name="searchConf" id="searchConf">  
                        <option value=""></option>
                        <c:forEach var="conference" items="${allConferences}">
                            <option value="${conference.name}">${conference.name}</option>
                        </c:forEach>                    
                    </select>
                    <p>Тематика: </p>
                    <select id="searchSubject" class="search-select" name="searchSubject" placeholder="Тематика">
                        <option name="selectSubject"></option>
                        <c:forEach var="subject" items="${allSubjects}">
                            <option name="selectSubject" value="${subject.name}">${subject.name}</option>
                        </c:forEach>                                        
                    </select>
                    <p>Рік: </p>
                    <select class="search-select" name="searchYear" placeholder="Рік" id="searchYear">
                        <option name="selectYear" value=""></option>
                        <c:forEach begin="1980" end="2050" varStatus="i">
                            <option name="selectYear" value="${i.index}">${i.index}</option>
                        </c:forEach>   
                    </select>
                </form>
            </div>
        </div>
        <div id="main">                      
            <section>          
                <c:forEach var="article" items="${allArticles}">
                    <article>
                        <h1>${article.title}</h1>
                        <div class="text-article">
                            ${fn:substring(article.text,0,300)} ...
                        </div>
                        <div class="fotter-article">
                            <span class="read"><a href="article?article_id=${article.id}">
                                    Читать...</a></span>
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
                </c:forEach>
            </section>            
        </div>  
        <div class="pagination-div">
            <ul class="pagination">
                <li><a href="index?groupId=1&searchText=${searchText}&searchType=${searchType}&searchConf=${searchConf}&searchSubject=${searchSubject}&searchYear=${searchYear}">початок</a></li>
                <li><a href="index?groupId=${currentGroup-1}&searchText=${searchText}&searchType=${searchType}&searchConf=${searchConf}&searchSubject=${searchSubject}&searchYear=${searchYear}">«</a></li>
                <c:forEach begin="${startGroup}" end="${endGroup}" varStatus="i">
                    <c:if test="${currentGroup == i.index}" >
                        <li class="active"><a href="/index?groupId=${i.index}&searchText=${searchText}&searchType=${searchType}&searchConf=${searchConf}&searchSubject=${searchSubject}&searchYear=${searchYear}">${i.index}</a></li>
                        <!--<li class="active"><a href="/index?groupId=${i.index}">${i.index}</a></li>-->
                    </c:if>
                    <c:if test="${currentGroup != i.index}" >
                        <li><a href="/index?groupId=${i.index}&searchText=${searchText}&searchType=${searchType}&searchConf=${searchConf}&searchSubject=${searchSubject}&searchYear=${searchYear}">${i.index}</a></li>
                    </c:if>
                </c:forEach>
                
                <!--<li class="active"><a href="#">1</a></li>
                <li class="disabled"><a href="#">2</a></li>
                <li><a href="#">3</a></li>
                <li><a href="#">4</a></li>
                <li><a href="#">5</a></li>-->
                <li><a href="/index?groupId=${currentGroup+1}&searchText=${searchText}&searchType=${searchType}&searchConf=${searchConf}&searchSubject=${searchSubject}&searchYear=${searchYear}">»</a></li>
                <li class="disabled"><a href=""> ... ${countGroups}</a></li>
                <li><a href="index?groupId=${countGroups}&searchText=${searchText}&searchType=${searchType}&searchConf=${searchConf}&searchSubject=${searchSubject}&searchYear=${searchYear}">кінець</a></li>
            </ul>
        </div>
        <%@ include file="/WEB-INF/jspf/footer.jspf" %>        
    </body>
    <script>
        
        
        //document.getElementById("searchText").value = "${searchText}";
        
        setSelect("searchType", "${searchType}");        
        setSelect("searchConf", "${searchConf}");
        setSelect("searchSubject", "${searchSubject}");
        setSelect("searchYear", "${searchYear}");

        function setSelect(id, value) {
            var i, o,
                    sel = document.getElementById(id),
                    opt = sel.options;

            for (i = 0; i < opt.length; i++) {
                o = opt[i];
                if (o.value == value) {
                    o.selected = true;
                    break;
                }
            }
            sel.style.display = 'none';
            sel.style.display = '';
        }
    </script>
</html>

