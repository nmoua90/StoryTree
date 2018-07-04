<!doctype HTML>
<html>
<head>

<link rel="stylesheet" type="text/css" href="HeaderBase.css">
<link rel="stylesheet" type="text/css" href="Glossary.css">
<script src="HeaderBase.js"></script>

</head>

<header style="text-align: center;">
	<div class="tab">
		<a id="d" href="HelperTable"><button class="tablinks">View
				Appendix of XML Tags</button></a> <a id="a" href="WhatAreXMLTags"><button
				class="tablinks">What are XML Tags?</button></a> <a id="c"
			href="DifferentTypesOfTags"><button class="tablinks">Different
				types of XML Tags</button></a> <a id="b" href="WhatAreScenes"><button
				class="tablinks">What are Scenes?</button></a> <a id="b"
			href="UserPanel"><button class="tablinks">Go Back Home</button></a>
	</div>
</header>

<body>
	<h1
		style="font-size: 75px; text-align: center; background-color: black; color: white;">APPENDIX
		OF XML TAGS</h1>


	<div id="GenericTags">
		<h3 style="color: white;">Generic Elements</h3>
		<table class="blueTable">
			<tr>
				<th>Tag Name</th>
				<th>Description</th>
				<th>Syntax</th>
			</tr>

			<tr>
				<td>STORYFILE</td>
				<td>The root element of all XML Game files. All other tags must
					exist within this tag.</td>
				<td>&ltSTORYFILE&gt &nbsp&nbsp&nbsp &lt/STORYFILE&gt</td>
			</tr>

			<tr>
				<td>TITLE</td>
				<td>This element is used to define the title of the game.</td>
				<td>&ltTITLE&gt &nbsp&nbsp&nbsp &lt/TITLE&gt</td>
			</tr>

			<tr>
				<td>CLASS_ID</td>
				<td>This element is used to link each game with a specific
					classroom.</td>
				<td>&ltCLASS_ID&gt &nbsp&nbsp&nbsp &lt/CLASS_ID&gt</td>
			</tr>
		</table>
	</div>

	<div id="SceneTags">
		<h3>Scene Elements</h3>
		<table class="blueTable">
			<tr>
				<th>Tag Name</th>
				<th>Element Description</th>
				<th>Has Attributes?</th>
				<th>Attribute Name</th>
				<th>Syntax</th>
			</tr>

			<tr>
				<td>SCENE</td>
				<td>The root element which all of the other tags within this
					table must fall under.</td>
				<td>Yes</td>
				<td>sid</td>
				<td>&ltSCENE <b>sid="&nbsp"</b>&gt &nbsp&nbsp&nbsp &lt/SCENE&gt
				</td>
			</tr>

			<tr>
				<td>NUM_PATHS</td>
				<td>This element defines the number of paths that exist within
					the current Scene.</td>
				<td>No</td>
				<td>---</td>
				<td>&ltNUM_PATHS&gt &nbsp&nbsp&nbsp &lt/NUM_PATHS&gt</td>
			</tr>

			<tr>
				<td>PHOTO</td>
				<td>Each Scene allows for a photo to be displayed. This element
					defines the URL of the photo to be shown.</td>
				<td>No</td>
				<td>---</td>
				<td>&ltPHOTO&gt &nbsp&nbsp&nbsp &lt/PHOTO&gt</td>
			</tr>

			<tr>
				<td>DESCRIPTION</td>
				<td>Each Scene allows for a body of text to be shown under the
					photo. This element defines what that body of text should be.</td>
				<td>No</td>
				<td>---</td>
				<td>&ltDESCRIPTION&gt &nbsp&nbsp&nbsp &lt/DESCRIPTION&gt</td>
			</tr>

			<tr>
				<td>PATH_1</td>
				<td>Each Scene allows for up to four buttons to be shown.
					Clicking a button will direct the player to a new Scene. This
					element defines what the text on the <b>FIRST BUTTON</b> should
					read, and what forwarding Scene the player should be directed to
					after its button click.
				</td>
				<td>Yes</td>
				<td>link</td>
				<td>&ltPATH_1 <b>link="&nbsp"</b>&gt &nbsp&nbsp&nbsp
					&lt/PATH_1&gt
				</td>
			</tr>

			<tr>
				<td>PATH_2</td>
				<td>This element defines what the text on the <b>SECOND
						BUTTON</b> should read, and forwarding next Scene the player should be
					directed to after its button click.
				</td>
				<td>Yes</td>
				<td>link</td>
				<td>&ltPATH_2 <b>link="&nbsp"</b>&gt &nbsp&nbsp&nbsp
					&lt/PATH_2&gt
				</td>
			</tr>

			<tr>
				<td>PATH_3</td>
				<td>This element defines what the text on the <b>THIRD
						BUTTON</b> should read, and forwarding next Scene the player should be
					directed to after its button click.
				</td>
				<td>Yes</td>
				<td>link</td>
				<td>&ltPATH_3 <b>link="&nbsp"</b>&gt &nbsp&nbsp&nbsp
					&lt/PATH_3&gt
				</td>
			</tr>

			<tr>
				<td>PATH_4</td>
				<td>This element defines what the text on the <b>FOURTH
						BUTTON</b> should read, and forwarding next Scene the player should be
					directed to after its button click.
				</td>
				<td>Yes</td>
				<td>link</td>
				<td>&ltPATH_4 <b>link="&nbsp"</b>&gt &nbsp&nbsp&nbsp
					&lt/PATH_4&gt
				</td>
			</tr>
		</table>
	</div>



	<div id="SceneAttributes">
		<h3>Scene Attributes</h3>
		<table class="blueTable">
			<tr>
				<th>Tag Name(s)</th>
				<th>Attribute</th>
				<th>Attribute Description</th>
			</tr>

			<tr>
				<td>Scene</td>
				<td>sid</td>
				<td>Every Scene must have a unique ID that identifies it. This
					attribute defines that the current Scene's ID is.</td>
			</tr>

			<tr>
				<td>Path_1<br>Path_2<br>Path_3<br>Path_4
				</td>
				<td>link</td>
				<td>This attribute defines what Scene the player should be
					directed to after button click. The defined value must refer to a
					sid (Scene ID) already in the file.</td>
			</tr>

		</table>
	</div>

</body>

</html>