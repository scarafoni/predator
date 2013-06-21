import java.util.*;
public class AI 
{
	public String preyMove(String[][][] board,String team, String id)
	{
		String[] moves = {"w","a","s","d"};
		Random random = new Random();
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
				 if(foundPred(board,startPoint[0],startPoint[1]+i))
					 return makePreyMove(x,y,startPoint[0],startPoint[1]+i);
			 }
			 //right
			 int compensator = (rad-1)*2 + 3;
			 for(int i = 0; i < rad+compensator; i++)
			 {
				 if(foundPred(board,startPoint[0]+i,startPoint[1]+compensator))
					 return makePreyMove(x,y,startPoint[0]+i,startPoint[1]+compensator);

			 }
			 //bottom
			 for(int i = 0; i < rad+compensator; i++)
			 {
				 if(foundPred(board,compensator+startPoint[0],startPoint[1]+i))
					 return makePreyMove(x,y,compensator+startPoint[0],startPoint[1]+i);
			 }
			 //left
			 for(int i = 0; i < rad+compensator; i++)
			 {
				 if(foundPred(board,startPoint[0]+i,startPoint[1]))
					 return makePreyMove(x,y,startPoint[0]+i,startPoint[1]);
			 }
		 }
		 return moves[random.nextInt(moves.length)];
	}
	 
	private boolean foundPred(String[][][] board,int i,int j)
	{
		if(i >= 0 && i < board.length &&
				j >= 0 && j < board.length)
		{
			if(board[i][j][0].equals("1"))
			{
				//System.out.println(i+","+j);
				return true;
			}
		}
		return false;
	}
	 
	 //wrapper for move making functions
	 private String makePreyMove(int preyX,int preyY,int predX,int predY)
	 {
		 int[] vec = makeVectorPrey(preyX,preyY,predX,predY);
		 return vec2MovePrey(vec);
	 }
	 
	 //makes a vector
	 private int[] makeVectorPrey(int preyX,int preyY,int predX,int predY)
	 {
		 int x = predX-preyX;
		 int y = predY-preyY;
		 int[] coord = {x,y};
		 return coord;
	 }
	 
	 //makes a move from a vector
	 private String vec2MovePrey(int[] coord)
	 {
		 if(coord[0] > 0 && coord[0] < 5)
			 return "w";
		 else if(coord[0] < 0 && coord[0] > -5)
			 return "s";
		 
		 if(coord[1] > 0 && coord[1] < 5)
			 return  "a";
		 else if(coord[1] <  0 && coord[1] > -5)
			 return "d";
		 
		 return "w";
	 }
}
