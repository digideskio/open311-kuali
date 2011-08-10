<%--
  Copyright 2011 The Kuali Foundation Licensed under the Educational Community
  License, Version 2.0 (the "License"); you may not use this file except in
  compliance with the License. You may obtain a copy of the License at
  http://www.osedu.org/licenses/ECL-2.0 Unless required by applicable law or
  agreed to in writing, software distributed under the License is distributed
  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
  express or implied. See the License for the specific language governing
  permissions and limitations under the License.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="kme" uri="http://kuali.org/mobility" %>

<kme:page title="News" id="news" homeButton="true" backButton="true" backButtonURL="${pageContext.request.contextPath}/home" cssFilename="news">
    <kme:content>
		<!-- <ul data-role="listview" data-theme="c" class="news-index"> -->
		<kme:listView dataTheme="c" cssClass="news-index">
			<c:if test="${topArticle != null}">
				<kme:listItem cssClass="news-topstory">
			        <div class="bottom-fade"></div>
			        <a href="${pageContext.request.contextPath}/news/${topArticleSourceId}?articleId=${topArticle.articleId}&referrer=home">
			        	<c:choose>
			        		<c:when test="${!empty topArticle.thumbnailImageUrl}">
						    	<img src="${topArticle.thumbnailImageUrl}" alt="topstory">
						    </c:when>
						    <c:otherwise>
						    	<img src="images/news-generic.png" alt="topstory">
						    </c:otherwise>
						</c:choose>
				        <div>
				          <p class="news-title">${topArticle.title}</p>
				          <p class="news-teaser">${topArticle.description}</p>
				        </div>
			        </a> 
		        </kme:listItem>
	        </c:if>
		
			<c:forEach items="${newsStreams}" var="stream" varStatus="status">
			
				<!-- <li data-role="" class="" data-theme="b" data-icon="listview" > -->
				<kme:listItem dataRole="list-divider" dataTheme="b" dataIcon="listview" cssClass="streamTitle">
					<a href="${pageContext.request.contextPath}/news/${stream.sourceId}">${stream.title}</a>
				</kme:listItem>
				<!-- </li> --> 
				
				<c:forEach items="${stream.articles}" var="day" varStatus="status">
					<c:forEach items="${day.articles}" var="article" varStatus="status">
						<kme:listItem>
							<a href="${pageContext.request.contextPath}/news/${stream.sourceId}?articleId=${article.articleId}&referrer=home">
				        		<!-- <p class="news-title">${article.title}</p>-->
				        		<p class="wrap">${article.title}</p>
				        	</a>
				        </kme:listItem>
					</c:forEach>
				</c:forEach>
			</c:forEach>
		</kme:listView>
	</kme:content>
</kme:page>