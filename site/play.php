<?php
	$type = $_REQUEST["type"];
	$team = $_REQUEST["team"];
	$id = $_REQUEST["id"];
	$toPass = $type.','.$team.','.$id;
?>
<!DOCTYPE HTML>
	<head>
		<script src="jquery-1.4.4.min.js" type="text/javascript"></script>
		<script src="game.js" type="text/javascript"></script>
		<link rel="stylesheet" type="text/css" href="style.css" />
		<title>Predator vs Prey</title>
	</head>

	<body onkeydown="handleKey(event)">
		<script>
			//get info
			var type = '<?php echo $type; ?>';
			var team = '<?php echo $team; ?>';
			var id = '<?php echo $id; ?>';

			//draw board on startup, set up interval
			$(document).ready(function()
			{
				drawBoardM(10);
				try {
				Initialize(type,team,id,'w');
				}catch(err){alert("error");}
				setInterval(function(){drawBoard(id)},100);
			});
			
			//when a key is pressed
			function handleKey(e)
			{
				var keyNum;
				if(window.event)
					keyNum = e.keyCode;
				else
					keyNum = e.which;
	
				if(keyNum == 38)
					Initialize(type,team,id,'w');
				else if(keyNum == 40)
					Initialize(type,team,id,'s');
				else if(keyNum == 37)
					Initialize(type,team,id,'a');
				else if(keyNum = 39)
					Initialize(type,team,id,'d');
			}
			//draw the board
			function drawBoardM(size)
			{
				var ta = document.getElementById("board");
				for(var i = 0; i < size; i++)
				{
					var row = ta.insertRow(i);
					for(var j = 0; j < size; j++)
						row.insertCell(j);
				}
			}

		</script>
		<div id="topBanner"><h1>Predator vs Prey RG Edition</h1></div>
		<div id="leftBanner"></div>
		<div id="boardDiv">
			<table border="1" id="board"></table>
			<textarea id="alertPlace"></textarea>
		</div>
	</body>
