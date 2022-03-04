<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<h3>Podsumowanie w miastach</h3>

<table class="table">
	<thead>
		<tr>
			<th scope="col">Miasto</th>
			<th scope="col">Średnia temp.</th>
			<th scope="col">Średnia temp. (dzień)</th>
			<th scope="col">Średnia temp. (noc)</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${cityStatistics}" var="stat">
			<tr>
				<th scope="row">${stat.city}</th>
				<td><fmt:formatNumber pattern="0" value="${stat.avgTemp}"/></td>
				<td><fmt:formatNumber pattern="0" value="${stat.avgTempDay}"/></td>
				<td><fmt:formatNumber pattern="0" value="${stat.avgTempNight}"/></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<h3>Podsumowanie dla kraju</h3>

<table class="table">
	<thead>
		<tr>
			<th scope="col">Średnia temp.</th>
			<th scope="col">Średnia temp. (dzień)</th>
			<th scope="col">Średnia temp. (noc)</th>
		</tr>
	</thead>
	<tbody>
		
			<tr>
				<td><fmt:formatNumber pattern="0" value="${countryStatistics.avgTemp}"/></td>
				<td><fmt:formatNumber pattern="0" value="${countryStatistics.avgTempDay}"/></td>
				<td><fmt:formatNumber pattern="0" value="${countryStatistics.avgTempNight}"/></td>
			</tr>
		
	</tbody>
</table>



<%@ include file="common/footer.jspf" %>