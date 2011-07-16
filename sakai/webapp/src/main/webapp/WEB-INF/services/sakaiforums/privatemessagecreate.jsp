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
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Page Title</title>
<link href="${pageContext.request.contextPath}/css/jquery.mobile-1.0b1.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/custom.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/custom.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.mobile-1.0b1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.blockUI.js"></script>


<script type="text/javascript">
$(function(){
	$("#jQPrivateMessageForm").submit(function(){
		$.blockUI({ message: '<h1>Sending Message...</h1>' });
		var $form = $( this ),
		to = $form.find( 'input[name="to_r"]' ).val(),
		title = $form.find( 'input[name="title_r"]' ).val(),
        body = $form.find( 'textArea[name="body_r"]' ).val();
		siteId = "<%= request.getParameter("siteId") %>";
		var referrer = document.referrer;
		$.post("${pageContext.request.contextPath}/sakaiprivatemessages", { 'to': to, 'title': title, 'body': body, 'siteId': siteId},
				   function(data) {
				     //alert("Sent " + data);
				     $.unblockUI();
				     $.mobile.changePage(referrer, "slide", false, true);
				   });

		//$.post("${pageContext.request.contextPath}/sakaiforumsmessages", forumThreadForm, function(data){
		//    confirmationVar.text(data);
		//    hideContentTransition();
		//    showConfirmation();
		//  });
		  return false;
	});
});
</script>
</head>

<body>
<div data-role="page" id="">
  <div data-role="header" data-position="">
    <h1>Create Message</h1>
  </div>
  <!-- /header -->
 
  <div data-role="content" style="padding-top:0px" >
    <form id="jQPrivateMessageForm" method="post">
        <fieldset>
        <div data-role="fieldcontain" >
         <label for="to">To:</label>
         <input type="text" name="to_r" id="to" />
        
        <label for="title">Subject:</label>
        <input type="text" name="title_r" id="title" />
     
        <label for="body">Message:</label>
        <textarea cols="40" rows="8" name="body_r" id="body"></textarea>
     </div>
     	
     	<div data-inline="true">
        <div class="ui-grid-a">
          <div class="ui-block-b"><button type="submit" data-theme="a" name="submit" value="Create" class="ui-btn-hidden" aria-disabled="false">Create</button></div>
        </div>
     	 </div>
     	</fieldset>
     </form>
  </div>
  <!-- /content --> 
  
  <!-- /header --> 
</div>
<!-- /stc --> 

<!-- /page -->

</body>
</html>