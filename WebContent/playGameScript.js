window.onload = checkAndHideButtons;
	        
function checkAndHideButtons() {
	if(document.getElementById("Path1").value == "void") {
		document.getElementById("Path1").style.visibility = "hidden";
	} 
	
	if(document.getElementById("Path2").value == "void") {
		document.getElementById("Path2").style.visibility = "hidden";
	} 
		        
	if(document.getElementById("Path3").value == "void") {
		document.getElementById("Path3").style.visibility = "hidden";
	} 
	
	if(document.getElementById("Path4").value == "void") {
		document.getElementById("Path4").style.visibility = "hidden";
	}
}

document.addEventListener('contextmenu', event => event.preventDefault());
window.location.hash="no-back-button";
window.location.hash="Again-No-back-button";
window.onhashchange=function(){window.location.hash="no-back-button";}