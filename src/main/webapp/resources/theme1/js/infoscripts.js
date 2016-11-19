window.onload = function() {
	
	
	$('table.display').DataTable( {
		lengthChange:false,
		pageLength: 6,
        scrollCollapse: true,
        searching: false,
        paging:true,
        ordering:false
    } );
	  var c = document.getElementById('Electric')
	  var d = document.getElementById('Gas')
	  var e = document.getElementById('Water')
	  c.onchange = function() {
	    if (c.checked == true) {document.getElementById('answer1').style.display = 'inline';
	                            document.getElementById('label1').style.display = 'block';}
	    else {document.getElementById('answer1').style.display = '';
	    document.getElementById('label1').style.display = '';
	    }
	  }
	  d.onchange = function() {
		    if (d.checked == true) {document.getElementById('answer2').style.display = 'inline';
		                            document.getElementById('label2').style.display = 'block';}
		    else {document.getElementById('answer2').style.display = '';
		    document.getElementById('label2').style.display = '';
		    }
		  }
	  e.onchange = function() {
		    if (e.checked == true) {document.getElementById('answer3').style.display = 'inline';
		                            document.getElementById('label3').style.display = 'block';}
		    else {document.getElementById('answer3').style.display = '';
		    document.getElementById('label3').style.display = '';
		    }
		  }
	}

//Function To Display Popup
//Validating Empty Field
function check_empty() {
if (document.getElementById('name').value == "" || document.getElementById('email').value == "" || document.getElementById('msg').value == "") {
alert("Fill All Fields !");
} else {
document.getElementById('form').submit();
alert("Form Submitted Successfully...");
}
}
//Function To Display Popup
function showDiv() {
document.getElementById('abc').style.display = "block";
$(document).ready(function() {
	 
	/*$('.table21').DataTable({
		// "scrollY": 550,
		 //"scrollX": true
		 "destroy": true,
		 "paging" : false,
		 "ordering":false,
		 "searching":false,
		 "scrollY": "200px",				 
	});*/
});
}
function showDiv2() {
	document.getElementById('abc2').style.display = "block";
	}
function showDiv3() {
	document.getElementById('abc3').style.display = "block";
	}
//Function to Hide Popup
function div_hide(){
document.getElementById('abc').style.display = "none";
}
function div_hide2(){
	document.getElementById('abc2').style.display = "none";
	}
function div_hide3(){
	document.getElementById('abc3').style.display = "none";
	}

function disableOtherDayCheckBoxes(id, day){
	var id1=0;
	var id2=0;
	if(id==1){
		id1=2; id2=3;
	}
	if(id==2){
		id1=1; id2=3;
	}
	if(id==3){
		id1=1; id2=2;
	}
	
	if($('#'+'bInfoDay'+id+day).is(':checked')){
		// uncheck other checkboxes if checked
		$('#'+'bInfoDay'+id1+day).attr( "checked", false );
		$('#'+'bInfoDay'+id2+day).attr( "checked", false );
	}
}
$(".dropdown-menu").on('click', 'li a', function(){
	  var selText = $(this).children("h9").html();
	  var k=($(this).attr('data-value'));
	  if (k == 2 || k == 3) {document.getElementById('annualDR').style.display = 'inline';}
	   else {document.getElementById('annualDR').style.display = ''; 
	   }
	  if (k == 2 || k == 3) {document.getElementById('DRUtility').style.display = 'inline';}
	   else {document.getElementById('DRUtility').style.display = ''; 
	   }
	  if (k == 2 || k == 3) {document.getElementById('kwIncentive').style.display = 'inline';}
	   else {document.getElementById('kwIncentive').style.display = ''; 
	   }
	  if (k == 2 || k == 3) {document.getElementById('kwhIncentive').style.display = 'inline';}
	   else {document.getElementById('kwhIncentive').style.display = ''; 
	   }
	   if (k == 2 || k == 3) {document.getElementById('icon').style.display = 'inline';}
	   else {document.getElementById('icon').style.display = ''; 
	   }
	});