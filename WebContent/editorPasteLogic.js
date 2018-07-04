// Load text editor with data (if any)
window.onload = loadData;

var curSID = "HOME";
var nextSID = 0;
		
var editor = CodeMirror( document.getElementById("codeeditor"), {
	theme: "colorforth",
	lineNumbers: true,
	extraKeys: {"Ctrl-Space": "autocomplete"}
});
		
function loadData(){
	// Get Editor references
	var doc = editor.getDoc();
	var cursor = doc.getCursor();
	var line = doc.getLine(cursor.line); 
	var pos = { 
			line: cursor.line,
			ch: line.length - 1 
	}
	
	// Retrieve number of scenes in file
	// Set global counters as needed
	var numScenesInFile = document.getElementById("sceneCountFromFile").value;
	if(numScenesInFile != 0){
		curSID = numScenesInFile-1;
		nextSID = numScenesInFile;
	}
	
	// Retrieve text data from file
	// Set textarea with data
	var data = document.getElementById("dataFromFile").value;
	var replaced = data.split('@@@').join('"');
	doc.replaceRange(replaced, pos);
}

function clearScreen(){
	editor.setValue("");
	editor.clearHistory();
	curSID = "HOME";
	nextSID = 0;
}
			
function saveFile(filename, type) {
	var file = new Blob([editor.getValue()], {type: type});
	if (window.navigator.msSaveOrOpenBlob) // IE10+
		window.navigator.msSaveOrOpenBlob(file, filename);
	else { 
		var a = document.createElement("a"),
		url = URL.createObjectURL(file);
		a.href = url;
		a.download = filename;
		document.body.appendChild(a);
		a.click();
		setTimeout(function() {
			document.body.removeChild(a);
			window.URL.revokeObjectURL(url);  
		}, 0); 
	}
}
			
function addToDocument(referencedButton){
	var doc = editor.getDoc();
	var cursor = doc.getCursor();
	var line = doc.getLine(cursor.line); 
	var pos = { 
			line: cursor.line,
			ch: line.length - 1 
	}
				
	var text;
				
	switch(referencedButton){
		case "XMLButton":
			text = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
			break;
		case "StoryTreeHeader":
			text = "<STORYFILE>\n\n\n\n\n</STORYFILE>\n";
			doc.replaceRange(text, pos);
			editor.focus();
			editor.setCursor({line:3, ch:5});
			break;
		case "Title":
			text = "\t<TITLE> ??? </TITLE>\n";
			break;
		case "ClassID":
			text = "\t<CLASS_ID> ??? </CLASS_ID>\n";
			break;
		case "AddZeroScenes":
			var paths = 0;
			text = "\n\t<SCENE sid = \"" + curSID + "\">\n" + 
				   "\t\t<NUM_PATHS>"+paths+"</NUM_PATHS>\n" +
				   "\t\t<PHOTO> ??? </PHOTO>\n" + 
				   "\t\t<DESCRIPTION>\n" + 
				   "\t\t???\n" +
				   "\t\t</DESCRIPTION>\n" +
				   "\t</SCENE>\n"; 
			incrementCurSID();
			incrementNextSID();
			break;			
		case "AddOneScene":
			var paths = 1;
			text = "\n\t<SCENE sid = \"" + curSID + "\">\n" + 
			       "\t\t<NUM_PATHS>"+paths+"</NUM_PATHS>\n" +
			       "\t\t<PHOTO> ??? </PHOTO>\n" + 
			       "\t\t<DESCRIPTION>\n" + 
			       "\t\t???\n" +
			       "\t\t</DESCRIPTION>\n" +
			       "\t\t<PATH1 link = \"" + incrementNextSID() + "\"> ??? </PATH1>\n" +
			       "\t</SCENE>\n"; 
			incrementCurSID();
			break;			
		case "AddTwoScenes":
			var tempNextSIDCounter = incrementNextSID();
			var paths = 2;
			text = "\n\t<SCENE sid = \"" + curSID + "\">\n" + 
				   "\t\t<NUM_PATHS>"+paths+"</NUM_PATHS>\n" +
				   "\t\t<PHOTO> ??? </PHOTO>\n" + 
				   "\t\t<DESCRIPTION>\n" + 
				   "\t\t???\n" +
				   "\t\t</DESCRIPTION>\n" +
				   "\t\t<PATH1 link = \"" + tempNextSIDCounter + "\"> ??? </PATH1>\n" +
				   "\t\t<PATH2 link = \"" + ++tempNextSIDCounter + "\"> ??? </PATH2>\n" +
				   "\t</SCENE>\n"; 
			incrementCurSID();
			break;			
		case "AddThreeScenes":
			var tempNextSIDCounter = incrementNextSID();
			var paths = 3;
			text = "\n\t<SCENE sid = \"" + curSID + "\">\n" + 
			       "\t\t<NUM_PATHS>"+paths+"</NUM_PATHS>\n" +
			       "\t\t<PHOTO> ??? </PHOTO>\n" + 
			       "\t\t<DESCRIPTION>\n" + 
			       "\t\t???\n" +
			       "\t\t</DESCRIPTION>\n" +
			       "\t\t<PATH1 link = \"" + tempNextSIDCounter + "\"> ??? </PATH1>\n" +
			       "\t\t<PATH2 link = \"" + ++tempNextSIDCounter + "\"> ??? </PATH2>\n" +
			       "\t\t<PATH3 link = \"" + ++tempNextSIDCounter + "\"> ??? </PATH3>\n" +
			       "\t</SCENE>\n"; 
			incrementCurSID();
			break;			
		case "AddFourScenes":
			var tempNextSIDCounter = incrementNextSID();
			var paths = 4;
			text = "\n\t<SCENE sid = \"" + curSID + "\">\n" + 
			       "\t\t<NUM_PATHS>"+paths+"</NUM_PATHS>\n" +
			       "\t\t<PHOTO> ??? </PHOTO>\n" + 
			       "\t\t<DESCRIPTION>\n" + 
			       "\t\t???\n" +
			       "\t\t</DESCRIPTION>\n" +
			       "\t\t<PATH1 link = \"" + tempNextSIDCounter + "\"> ??? </PATH1>\n" +
			       "\t\t<PATH2 link = \"" + ++tempNextSIDCounter + "\"> ??? </PATH2>\n" +
			       "\t\t<PATH3 link = \"" + ++tempNextSIDCounter + "\"> ??? </PATH3>\n" +
			       "\t\t<PATH4 link = \"" + ++tempNextSIDCounter + "\"> ??? </PATH4>\n" +
			       "\t</SCENE>\n"; 
			incrementCurSID();
			break;		
	}//end of switch statement
						
	// Create this check to ignore special StoryTreeHeader case
	// where cursor needs to be reset.
	if(referencedButton != "StoryTreeHeader"){
		doc.replaceRange(text, pos);
	}
				
}//end of addToDocument()
			
function incrementCurSID(){
	if(curSID == "HOME"){
		curSID = 0;
	}else{
		curSID++;
	}
	return curSID;				
}
			
function incrementNextSID(){
	nextSID++;
	return nextSID-1;
}