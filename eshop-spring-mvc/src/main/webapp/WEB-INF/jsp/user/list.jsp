<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message var="title" key="user.list.title"/>
<my:pagetemplate title="${title}">
    <jsp:attribute name="body">
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th><fmt:message key="user.list.id"/></th>
                    <th><fmt:message key="user.list.firstname"/></th>
                    <th><fmt:message key="user.list.lastname"/></th>
                    <th><fmt:message key="user.list.email"/></th>
                    <th><fmt:message key="user.list.address"/></th>
                    <th><fmt:message key="user.list.phone"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <th>${user.id}</th>
                        <td>${user.givenName}</td>
                        <td>${user.surname}</td>
                        <td>${user.email}</td>
                        <td>${user.address}</td>
                        <td>${user.phone}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </jsp:attribute>
</my:pagetemplate>