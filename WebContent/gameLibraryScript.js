var rIndex, table = document.getElementById("datatable");
        
// display selected row data into input text
function selectedRowToInput()
{
	for(var i = 1; i < table.rows.length; i++)
    {
    	table.rows[i].onclick = function()
        {
        // get the selected row index
        rIndex = this.rowIndex;
        document.getElementById("gameID").value = this.cells[1].innerHTML;
        document.getElementById("gameTitle").value = this.cells[2].innerHTML;
        document.getElementById("author").value = this.cells[3].innerHTML;
        };
    }
}
selectedRowToInput();
        
$(document).ready( function () {
	$('#datatable').dataTable(
	{
		"order": [[ 7, "desc" ]]	//order the 4th column
	});
});