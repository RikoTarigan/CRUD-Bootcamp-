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


/*MY SCRIPT*/

/*SEARCH*/


jQuery.expr[":"].containsNoCase = function (el, i, m) {
        var search = m[3];
        if (!search) return false;
        return eval("/" + search + "/i").test($(el).text());
    };

    jQuery(document).ready(function () {
        // used for the first example in the blog post
        jQuery('li:contains(\'DotNetNuke\')').css('color', '#0000ff').css('font-weight', 'bold');

        // hide the cancel search image
        jQuery('#imgSearch').hide();

        // reset the search when the cancel image is clicked
        jQuery('#imgSearch').click(function () {
            resetSearch();
        });

        // cancel the search if the user presses the ESC key
        jQuery('#zz').keyup(function (event) {
            if (event.keyCode == 27) {
                resetSearch();
            }
        });

        // execute the search
        jQuery('#txtSearch').keyup(function () {
            // only search when there are 3 or more characters in the textbox
            if (jQuery('#txtSearch').val().length > 0) {
                // hide all rows
                jQuery('#tblSearch tr').hide();
                // show the header row
                jQuery('#tblSearch tr:first').show();
                // show the matching rows (using the containsNoCase from Rick Strahl)
                jQuery('#tblSearch tr td:containsNoCase(\'' + jQuery('#txtSearch').val() + '\')').parent().show();

                // show the cancel search image
                jQuery('#imgSearch').show();
            }
            else if (jQuery('#txtSearch').val().length == 0) {
                // if the user removed all of the text, reset the search
                resetSearch();
            }

            // if there were no matching rows, tell the user
            if (jQuery('#tblSearch tr:visible').length == 1) {
                // remove the norecords row if it already exists
                jQuery('.norecords').remove();
                // add the norecords row
                jQuery('#tblSearch').append('<tr class="norecords"><td colspan="5" class="Normal">No records were found</td></tr>');
            }
        });
    });

    function resetSearch() {
        // clear the textbox
        jQuery('#txtSearch').val('');
        // show all table rows
        jQuery('#tblSearch tr').show();
        // remove any no records rows
        jQuery('.norecords').remove();
        // remove the cancel search image
        jQuery('#imgSearch').hide();
        // make sure we re-focus on the textbox for usability
        jQuery('#txtSearch').focus();
    }


/*END SEARCH*/
   
    
/*CustCode*/
    $(document).ready(function(){
    	$selected_value=$('#custCode option:selected').val();
    	$('#custCode').change(function(){
    		$selected_value=$('#custCode option:selected').val();
    		
    		$('#cusname').val($selected_value);
    	});
    });
/*END CustCode*/
    
/*Product*/
    var coins;
    var price;
    var totalAmount=parseInt(0);
    $(document).on('input', '#qty', function(){
         coins = $("#qty").val();
    })
    $(document).on('input', '#price', function(){
         price = $("#price").val();
    })
    
    $(document).ready(function(){
    	 $('#btnAddRow').on('click', function() {
    	        var lastRow = $('#tblAddRow tbody tr:last').html();
    	        //alert(lastRow);
    	        $('#tblAddRow tbody').append('<tr>' + lastRow + '</tr>');
    	        $('#tblAddRow tbody tr:last td input').val(0);
    	        $('#tblAddRow tbody tr:last td #total').text('0');
    	        var rowCount = $('.trans tr').length; 
    	        $(".trans tr:eq("+parseInt(rowCount-2)+") td #product").prop('disabled',true);
    	        $(".trans tr:eq("+parseInt(rowCount-2)+") td #qty").prop('disabled',true);
    	        $(".trans tr:eq("+parseInt(rowCount-1)+") td #qty").prop('disabled',false);
    	        $(".trans tr:eq("+parseInt(rowCount-1)+") td #product").prop('disabled',false);

    	 });
    	 
    	 $(this).on('click', 'option:selected', function () {
	 		   $selected_value=$(this,'option:selected').val()
	 		   /*alert($selected_value);*/
	 		   var element = $selected_value;
	 		   var arrayOfStrings = element.split("/");
	 		   if($selected_value=="Choose Product Code"){
	 			   $('#productname').text("");
	 			   $('#price').val(0);
	 			  $('#qty').val(0);
	 			   }
	   			else{
	   				$('table tr:last td:eq(1) #productname').text(arrayOfStrings[0]);
	   				$('table tr:last td:eq(2) #price').val(arrayOfStrings[1]);
	   				}
 			});
    	 
    	 $(this).on('change','#qty',function(){
    		 $('table tr:last td:eq(4) #total').text(parseInt( $('table tr:last td:eq(2) #price').val()) * $(this).val());
        	 $('#totAm').val(calculateTotalAmount);
    	 });
    });
    
    /*Total Amount*/
    function calculateTotalAmount()
    {
    	var sum = 0;
    	$('tr').each(function(){
    		$(this).find('.totalAmount').each(function(){
    			var marks = $(this).text();
    			if(marks.length!==0)
    				sum+=parseFloat(marks);
    		});
    		
    	});  	
    	return sum;
    }
    /*END TOTAL Amount*/
/*END Product*/


//Add button Delete in row
$('.trans tr')
	.find('td')
	.parent() //traversing to 'tr' Element
	.append('<td><a href="#" class="delrow">Delete Row</a></td>');

//Delete row on click in the table
$('#tblAddRow').on('click', 'tr a', function(e) {
    var lenRow = $('#tblAddRow tbody tr').length;
    e.preventDefault();
    if (lenRow == 1 || lenRow <= 1) {
        alert("Tidak dapat menghapus semua baris");
    } else {
        $(this).parents('tr').remove();
        $('#totAm').val(calculateTotalAmount);
        
    }
});


/*$.getJSON('http://localhost:8080/getdata', function(data){
	var list;
	var code=[];
	for (var i = 0; i < data.length; i = i + 1) {
		code.push(data[i]["productCode"]);
	}
	$.each(code, function(index, value) {
	    list += `<option value="${value}">${value}</option>`;
	  });
	$('#product').html(list);
});*/

/*END MY SCRIPT*/