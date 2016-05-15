<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Spring 3 MVC Multipe Row Submit</title>
</head>
<body>
 
<h2>Spring MVC Multiple Row Form Submit example</h2>
<form:form method="post" action="userProfile" modelAttribute="userForm">
    <table>
    <tr>
        <th>Skills</th>
        <th>Proficiency</th>
    </tr>
    <c:forEach items="${userForm.skillsProficiency}" var="user" varStatus="status">
        <tr>
        	<td>
        		<input readonly="readonly" style="outline: none; border: 0;" name="skillsProficiency[${status.index}].skill" value="${user.skill}"/>
        		<input type="hidden" name="skillsProficiency[${status.index}].skillId" value="${user.skillId}"/>
        	</td>
        	<td>
	        	<form:radiobutton path="skillsProficiency[${status.index}].proficiency" value="0" />Beginner
	        	<form:radiobutton path="skillsProficiency[${status.index}].proficiency" value="1" />Educated 
	        	<form:radiobutton path="skillsProficiency[${status.index}].proficiency" value="2" />Intermediate
	        	<form:radiobutton path="skillsProficiency[${status.index}].proficiency" value="3" />Expert
        	</td>
        </tr>
    </c:forEach>
</table>  
<br/>
<input type="submit" value="Save" />
     
</form:form>
</body>
</html>