<%@page language="java"%>
<%@taglib prefix="jstl" uri ="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir ="/WEB-INF/tags"%>


<acme:form readonly="true">
	<acme:form-textbox code="administrator.investorRecord.form.label.investorName" path="investorName" />
	<acme:form-textbox code="administrator.investorRecord.form.label.sector" path="sector" />
	<acme:form-textarea code="administrator.investorRecord.form.label.investingStatement" path="investingStatement" />
	<acme:form-double code="administrator.investorRecord.form.label.stars" path="stars" />
	
	<acme:form-submit test="${command == 'show'}"
		code="administrator.investor.Record.form.button.update"
		action="/administrator/investor-record/update"/>
	<acme:form-submit test="${command == 'show'}"
		code="administrator.investor.Record.form.button.delete"
		action="/administrator/investor-record/delete"/>
	<acme:form-submit test="${command == 'create'}"
		code="administrator.investor.Record.form.button.create"
		action="/administrator/investor.Record/create"/>
	<acme:form-submit test="${command == 'update'}"
		code="administrator.investor.Record.form.button.update"
		action="/administrator/investor-record/update"/>
	<acme:form-submit test="${command == 'delete'}"
		code="administrator.investor.Record.form.button.delete"
		action="/administrator/investor-record/delete"/>
	<acme:form-return code="administrator.investorRecord.form.button.return"/>
</acme:form>