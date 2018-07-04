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
				class="tablinks">What are Scenes?</button></a> <a id="b" href="UserPanel"><button
				class="tablinks">Go Back Home</button></a>
	</div>
</header>

<body>
	<h1
		style="font-size: 75px; text-align: center; background-color: black; color: white;">TYPES
		OF XML TAGS</h1>



	<div id="TypesOfTagsIntro">
		<h1>Different Types of Tags:</h1>
		<p>XML Tags can either be ELEMENTS or ATTRIBUTES.</p>
		<h1>What's an Element and How do I Write it?</h1>
		<p>
			You can think of an element as just a normal XML Tag as described
			before with our <b>&ltTITLE&gt</b> example. It's written in the same
			format.
		</p>
		<h1>
			What's an Attribute?
			</h3>
			<p>An Attribute is a special value attached to an Element.</p>
			<p>
				For example, writing a game requires the existence of multiple <i>SCENE</i>
				elements, all which have their own unique ID attribute called <i>sid</i>.
			</p>
			<h1>How do I write an Attribute Tag?</h1>
			<p>
				Attribute tags are defined by placing the special keyword within <i>THE
					FIRST BRACKET OF THE ELEMENT</i>:
			</p>
			<p>
				&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp &ltSCENE <b>sid="0"</b>&gt
				Some Value Goes Here &lt/SCENE&gt
			</p>
	</div>

</body>

</html>