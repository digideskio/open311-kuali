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

<kme:page title="${tour.name}" id="tours" backButton="true" homeButton="true" cssFilename="tours">
    <kme:content>
        <kme:listView filter="false">
        	<kme:listItem cssClass="link-gps">
               	<a href="${pageContext.request.contextPath}/tours/map/${tour.tourId}">
               		View Map<br />
               		<span class="wrap" style="font-weight:normal; font-size:.8em">Open the tour in an interactive map and show your GPS location.</span>
               	</a>
             </kme:listItem>
            <kme:listItem dataRole="list-divider" dataTheme="b" dataIcon="listview">
				Points of Interest
			</kme:listItem>
            <c:forEach items="${tour.pointsOfInterest}" var="poi" varStatus="status">
                <kme:listItem>
                	<a href="${pageContext.request.contextPath}/tours/details/${poi.poiId}">
                		<h3>${status.index+1}. ${poi.name}</h3>
               	 	</a>
                </kme:listItem>
            </c:forEach>
        </kme:listView>
    </kme:content>
</kme:page>
