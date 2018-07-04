var validUpload = false;	

document.getElementById('file').onchange = function () {
  
  var ext = this.value.split(".");
  if( ext.length === 1 || ( ext[0] === "" && ext.length === 2 ) ) {
      return "";
  }
  
  var checkExt = ext.pop().toLowerCase();
  var fileSize = this.files[0].size;
  
  if (checkExt != "xml"){
	  alert("Error: You can only upload .XML files.");
	  document.getElementById('file').value= null;
  }

  if(fileSize == 0){
	  alert("Error: You can't upload empty files.");
	  document.getElementById('file').value= null;
  }else if(fileSize > 1000000){ //1mb limit
	  alert("Error: Your file can't be larger than 1 Megabyte.");
	  document.getElementById('file').value= null;
  }else{
	  validUpload = true;
  }
};