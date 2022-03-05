<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<h1>Podaj temperaturę dla miasta</h1>


<c:if test="${error != null}">
	<div class="alert alert-danger" role="alert">
		${error}
	</div>
</c:if>

<c:if test="${success != null}">
	<div class="alert alert-success" role="alert">
		${success}
	</div>
</c:if>


<form method="post">
	<div class="form-group">
		<label for="city">Miasto</label>
		<input type="text" class="form-control" id="city" name="city" placeholder="Podaj miasto" value="${city}">
	</div>
	<div class="form-group">
		<label for="temp">Temperatura</label>
		<input type="text" class="form-control" id="temp" name="temp" placeholder="Podaj temperature" value="${temp}">
	</div>
	<button type="submit" class="btn btn-primary">Zapisz</button>
</form>

<%@ include file="common/footer.jspf" %>