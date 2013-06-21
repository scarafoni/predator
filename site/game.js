var teamCol;
//update the board
function drawBoard(id)
{
	//get the needed file
	var tex = pollFile("../"+id+"_map.txt");
	var ta = document.getElementById("board");
	var texCount = 0;
	//go through each row
	var rowL =ta.getElementsByTagName("tbody")[0].getElementsByTagName("tr");
	if(tex[0] == "y")
		document.getElementById("alertPlace").value = "you are eaten, please wait for the next game to respawn";
	else
	{
		for(var i = 0; i < rowL.length; i++)
		{
			//for each cell in row r
			var colL = rowL[i].getElementsByTagName("td"); 
			var j = 0;
			while(j < colL.length)
			{	
				var cell = colL[j];
				//; == newline, don't draw
				if(tex[texCount] == ";")
					texCount++;
				//, == empty
				else if(tex[texCount] == ",")
				{
					//empty out cell
					if(cell.style.backgroundColor != "white")
						cell.style.backgroundColor = "white";
					if(cell.innerHTML != "")
						cell.innerHTML = "";

					texCount++;
					j++;
				}
				//X == hidden
				else if(tex[texCount] == "X")
				{
					//make background black
					if(cell.style.backgroundColor != "black")
						cell.style.backgroundColor = "black";
					if(cell.innerHTML != "")
						cell.innerHTML = "";

					texCount++;
					j++;
				}
				//if its non of the above it's a piece
				else
				{
					/*
					//check side
					var red = 0; 
					var green = 0;
					var blue = 0;
					//predator
					if(tex[texCount] == "1")
						red = 255
					//prey
					else
						blue = 255       	
					//write team
					green = 20*parseInt(tex[texCount]); texCount++;
					*/

					//optimized begin
					var sidel = tex[texCount]; texCount++;
					var teaml = tex[texCount]; texCount++;
					var inner = teaml+":";
					cell.style.backgroundColor= "rgb("+teamCol[sidel][teaml]+")";
					//optimized end

					//cell.style.backgroundColor= "rgb("+red+","+green+","+blue+")";
					//write id
					var idl = "";
					while(tex[texCount] != "|")
					{	
						idl += tex[texCount];		
						inner += tex[texCount];
						texCount++;
					}
					cell.innerHTML = inner;
					if(idl == id || idl == 0+id)
						cell.style.backgroundColor="green";
					texCount++;
					j++;
				}
			}
		}
	}
}

//set up connection, fork over credentials, receive initial board
function Initialize(type,team,id,move) 
{
	teamCol = teamColors();
	var toWrite = "type="+type+"&&team="+team+"&&id="+id+"&&move="+move;
	try
	{
		passInfo(toWrite);
		drawBoard(id);
	}
	catch(err)
	{
		alert("error");
	}   
}

function teamColors()
{
	var teamCol = new Array();
	teamCol[0] = new Array();
	teamCol[1] = new Array();

	for(var i = 0; i < 10; i++)
	{
		var green = i * 20;
		var pred = "255,"+green+",0";
		var prey = "0,"+green+",255";
		teamCol[0][i] = prey;
		teamCol[1][i] = pred
	}
	return teamCol;
}
//get map
function pollFile(toRead) 
{
	var datafile = toRead;
	var line = "";
  	objXml = new XMLHttpRequest();
    	objXml.open("GET",datafile,false);
    	objXml.send(null);
    	return objXml.responseText;
}

//send move to server
function passInfo(toWrite) 
{
    objXml = new XMLHttpRequest();
    objXml.open("POST","write.php",false);
    objXml.setRequestHeader("Content-Type", "application/x-www-form-urlencoded"); 
    objXml.send(toWrite);
}