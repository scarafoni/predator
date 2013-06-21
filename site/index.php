<!DOCTYPE HTML>
	<head>
		<link rel="stylesheet" type="text/css" href="style.css" />
		<title>Predator vs Prey</title>
	</head>

	<body>
		<div id="topBanner"><h1>Predator vs Prey RG Edition</h1></div>
		<div id="leftBanner"></div>
		<div id="instructions">
			<form action="play.php" method="get">
				Type:</br> <input type="radio" name="type" value="predator" />Predator <input type="radio" name="type" value="prey" />Prey				
				</br>
				Team: <input type="text" name="team" />
				Id: <input type="text" name="id" />
				<input type="submit" />
			</form>
			<p>
			Instructions: </br> 
			1. Choose Predator or prey, team, and id </br>
			2. use arrow keys to move, on chessboard, you are "team#:id#"</br>
			3. predators catch prey, prey run for their lives </br>
			4. ????? </br>
			5. profit!
			</p>
		</div>
	</body>
</html>