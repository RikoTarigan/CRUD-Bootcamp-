$( document ).ready(function() {
	var isCheck = 0;
	var dataTables = $('#tblListUnapproved').DataTable( {
        "processing": true,
        "serverSide": true,
        "bFilter": false,
        "pageLength": 10,
        "ajax": {
        	"type": "POST",
            "url": "./get_listmoduleunapproved",
            "data": function ( data ) {
            	data.isCheck = isCheck
         }},
	});
});