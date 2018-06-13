<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 
<title>Sign Up</title>
 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
 
</head>
<body>
 
 
   <%-- <jsp:include page="_header.jsp" />
   <jsp:include page="_menu.jsp" />
  --%>
 
 
   <div class="page-title">Sign Up</div>
 
   <div class="login-container">
 
       <h3>Enter Details</h3>
       <br>
       <!-- /login?error=true -->
       <c:if test="${param.error == 'true'}">
           <div style="color: red; margin: 10px 0px;">
 
               Login Failed!!!<br /> Reason :
               ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
 
           </div>
       </c:if>
 
 		<form name='signUp' action="<c:url value='/signUp' />" modelAttribute="user" method='POST'>

			<table>
				<tr>
					<td>User:</td>
					<td><input type='text' name='username'></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type='password' name='password' /></td>
				</tr>
				
				<tr>
					<td colspan='2'><input name="submit" type="submit"
						value="submit" /></td>
				</tr>
			</table>

			<%-- <input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" /> --%>

		</form>
 	   <a href="${pageContext.request.contextPath}/signUp">Sign Up Here</a> --%>
       <span class="error-message">${error }</span>
 
   </div>
 
 
   <jsp:include page="_footer.jsp" />
 
</body>
</html>