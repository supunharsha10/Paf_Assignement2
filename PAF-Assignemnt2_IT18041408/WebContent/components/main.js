$(document).ready(function() 
{  
	if ($("#alertSuccess").text().trim() == "")  
	{   
		$("#alertSuccess").hide();  
	} 
	$("#alertError").hide(); 
}); 

//SAVE ============================================ 
$(document).on("click", "#btnSave", function(event) 
{  
	// Clear alerts---------------------  
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 

	// Form validation-------------------  
	var status = validateStockForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 

	// If valid------------------------  
	var t = ($("#hidAppIDSave").val() == "") ? "POST" : "PUT";
	
	$.ajax(
	{
		url : "StockAPI",
		type : t,
		data : $("#formStock").serialize(),
		dataType : "text",
		complete : function(response, status)
		{
			onStockSaveComplete(response.responseText, status);
		}
	});
}); 

function onStockSaveComplete(response, status){
	if(status == "success")
	{
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Saved.");
			$("#alertSuccess").show();
					
			$("#divItemsGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error"){
		$("#alertError").text("Error While Saving.");
		$("#slertError").show();
	}else{
		$("#alertError").text("Unknown Error while Saving.");
		$("#alertError").show();
	}
	$("#hidAppIDSave").val("");
	$("#formStock")[0].reset();
}

//UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event) 
		{     
	$("#hidAppIDSave").val($(this).closest("tr").find('#hidAppIDUpdate').val());     
	$("#mname").val($(this).closest("tr").find('td:eq(0)').text());    
	$("#description").val($(this).closest("tr").find('td:eq(1)').text());     
	$("#amount").val($(this).closest("tr").find('td:eq(2)').text());     
	$("#price").val($(this).closest("tr").find('td:eq(3)').text()); 
	

});


//Remove Operation
$(document).on("click", ".btnRemove", function(event){
	$.ajax(
	{
		url : "StockAPI",
		type : "DELETE",
		data : "id=" + $(this).data("id"),
		dataType : "text",
		complete : function(response, status)
		{
			onStockDeletedComplete(response.responseText, status);
		}
	});
});

function onStockDeletedComplete(response, status)
{
	if(status == "success")
	{
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Deleted.");
			$("#alertSuccess").show();
					
			$("#divItemsGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error"){
		$("#alertError").text("Error While Deleting.");
		$("#alertError").show();
	}else{
		$("#alertError").text("Unknown Error While Deleting.");
		$("#alertError").show();
	}
}

//CLIENTMODEL
function validateStockForm() {  
	// NAME  
	if ($("#mname").val().trim() == "")  {   
		return "Insert mname.";  
		
	} 
	
	 // Description  
	if ($("#description").val().trim() == "")  {   
		return "Insert description.";  
		
	} 
	 
	 
	 // Amount 
	if ($("#amount").val().trim() == "")  {   
		return "Insert amount.";  
		
	} 
	
	// Price  
	if ($("#price").val().trim() == "")  {   
		return "Insert price.";  
		
	} 
	  
	 
	 return true; 
	 
}
