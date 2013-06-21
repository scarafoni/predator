import java.util.*;
public class AI 
{
	String[] moves = {"w","a","s","d"};
	Random random = new Random();
	//finds a piece, then finds the nearest prey and lunges towards it
	 public String soloMove(String[][][] board,String team, String id)
	    {
	    	int x = 0;
	    	int y = 0;
	    	//find our piece
	    	for(int i = 0; i < board.length; i++)
	    		for(int j = 0; j < board.length; j++)
	    			if(board[i][j][0].equals("M"))
	    			{x = i;y = j;break;}
	    	//start a radial search
			 for(int rad = 0; rad < board.length; rad++)
			 {
				 int[] startPoint = {x-rad,y-rad};
				 //check top
				 for(int i = 0; i < rad+2; i++)
				 {
					 if(foundPrey(board,startPoint[0],startPoint[1]+i))
						 return makeMove(x,y,startPoint[0],startPoint[1]+i);
				 }
				 //right
				 int compensator = (rad-1)*2 + 3;
				 for(int i = 0; i < rad+compensator; i++)
				 {
					 if(foundPrey(board,startPoint[0]+i,startPoint[1]+compensator))
						 return makeMove(x,y,startPoint[0]+i,startPoint[1]+compensator);

				 }
				 //bottom
				 for(int i = 0; i < rad+compensator; i++)
				 {
					 if(foundPrey(board,compensator+startPoint[0],startPoint[1]+i))
						 return makeMove(x,y,compensator+startPoint[0],startPoint[1]+i);
				 }
				 //left
				 for(int i = 0; i < rad+compensator; i++)
				 {
					 if(foundPrey(board,startPoint[0]+i,startPoint[1]))
						 return makeMove(x,y,startPoint[0]+i,startPoint[1]);
				 }
			 }
			 return moves[random.nextInt(moves.length)];
	 }
	 
	 //check for prey in a given spot
	 private boolean foundPrey(String[][][] board,int i,int j)
	 {
		 if(i >= 0 && i < board.length &&
					j >= 0 && j < board.length)
				{
					if(board[i][j][0].equals("0"))
					{
						//System.out.println(i+","+j);
						return true;
					}
				}
		 return false;
	 }
	 
	 //wrapper for move making functions
	 private String makeMove(int predX,int predY,int preyX,int preyY)
	 {
		 int[] vec = makeVector(predX,predY,preyX,preyY);
		 return vec2Move(vec);
	 }
	 
	 //makes a vector
	 private int[] makeVector(int predX,int predY,int preyX,int preyY)
	 {
		 int x = preyX-predX;
		 int y = preyY-predY;
		 int[] coord = {x,y};
		 return coord;
	 }
	 
	 //makes a move from a vector
	 private String vec2Move(int[] coord)
	 {
		 if(coord[0] > 0)
			 return "s";
		 else if(coord[0] < 0)
			 return "w";
		 
		 if(coord[1] > 0)
			 return  "d";
		 else if(coord[1] <  0)
			 return "a";
		 
		 return "w";
	 }
}
