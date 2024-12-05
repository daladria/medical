<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ page pageEncoding="UTF-8" %>



<html>
<head>
    <title>Data Table Examples</title>
 	<script  src="${pageContext.request.contextPath}/jsLib/jquery/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" charset="utf8" src="${pageContext.request.contextPath}/jsLib/DataTables/datatables.js"></script>
 	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jsLib/DataTables/datatables.css">
</head>
<body >
<div  style="width:50%">
<table id="example" class="display" style="width:100%">
        <thead>
            <tr>
                <th>Name</th>
                <th>Position</th>
                <th>Office</th>
                <th>Extn.</th>
                <th>Start date</th>
                <th>Salary</th>
            </tr>
        </thead>
        <tfoot>
            <tr>
                <th>Name</th>
                <th>Position</th>
                <th>Office</th>
                <th>Extn.</th>
                <th>Start date</th>
                <th>Salary</th>
            </tr>
        </tfoot>
    </table>
</div>
</body>
</html>

<script>

$(document).ready(function() {

	var d = {"command":"test"};
    $('#example').DataTable( {
        "ajax":{
        	"url": "testData",
            "type": "POST",
            "contentType": "application/json;charset=UTF-8",
			 "timeout": 30000,
			   "data": function (d) {
				      return JSON.stringify({"command":"test"} );
				    }
        }, 
        "columns": [
            { "data": "name" },
            { "data": "position" },
            { "data": "office" },
            { "data": "extn" },
            { "data": "start_date" },
            { "data": "salary" }
        ]
    } );
} );

</script>