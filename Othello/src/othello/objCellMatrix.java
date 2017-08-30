
package othello;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.swing.JOptionPane;
import sun.audio.*;

public class objCellMatrix
{
	
	private int[][] pieceMatrix = new int[8][8]; //Stores which piece is in a cell. 0 empty, 1 Black, 2 White
	private int winningNumber = 0;
	PrintWriter write;
	public void objCellMatrix ()
	{
	}
	
	public void resetMatrix ()
	{
		
		for (int row = 0; row < 8; row++)
		{
			
			for (int column = 0; column < 8; column++)
			{
				
				if (row <= 2 || row >= 5) //row 0,1,2 or 5,6,7
				{
					pieceMatrix[row][column] = 0;
				}
				else
				{
					
					if (column <= 2 || column >= 5) //column 0,1,2 or 5,6,7
					{
						pieceMatrix[row][column] = 0;
					}
					else
					{

						if ((row == 3 && column == 3) || (row == 4 && column == 4)) //NW and SE of middle
						{
							pieceMatrix[row][column] = 2;
						}
						else
						{
							pieceMatrix[row][column] = 1;
						}
						
					}
				
				}				
				
			}
			
		}
		
	}
	
	public int getPieceCell (int row, int column)
	{
		return pieceMatrix[row][column];
	}
	
	public void setPieceCell (int row, int column, int piece)
	{
		pieceMatrix[row][column] = piece;
	}
	
	public String calculateWinner (String[] strPlayerName) throws FileNotFoundException, IOException
	{
		
		int black = 0;
		int white = 0;
		int currentCell = 0;
		
		for (int row = 0; row < 8; row++)
		{
			
			for (int column = 0; column < 8; column++)
			{
				
				currentCell = pieceMatrix[row][column];
				
				if (currentCell == 0)
				{
					continue;
				}
				else if (currentCell == 1)
				{
					black++;
				}
				else
				{
					white++;
				}
				
			}
			
		}
		
		if (black > white)
                 
		{
	           InputStream in;
                    try
                    {
                        in=new FileInputStream(new File("C:\\Users\\Chandana N T\\Desktop\\Othello2\\src\\othello\\applause.wav"));
                        AudioStream coinclick=new AudioStream(in);
                        AudioPlayer.player.start(coinclick);
                    }
                    catch(Exception e)
                    {
                        JOptionPane.showMessageDialog(null,e);
                    }    
                    
                    try{
                         
                         write = new PrintWriter(new FileWriter("leader.txt", true));
                         write.append(strPlayerName[0]+" ");
                         write.append(black+"\r\n");
                         write.append(strPlayerName[1]+" ");
                         write.append(white+"\r\n");
                         write.close();
                    }
                    catch(Exception e)
                    {
                        System.out.println(e);
                    }
                   return strPlayerName[0] + " has won, with a score of " + black + ". " + strPlayerName[1] + " got " + white;
		}		
		else if (white > black)
		{
                        InputStream in;
                        try
                    {
                        in=new FileInputStream(new File("C:\\Users\\Chandana N T\\Desktop\\Othello2\\src\\othello\\applause.wav"));
                        AudioStream coinclick=new AudioStream(in);
                        AudioPlayer.player.start(coinclick);
                    }
                    catch(Exception e)
                    {
                        JOptionPane.showMessageDialog(null,e);
                    }    
                        
                        try{
                         
                         write = new PrintWriter(new FileWriter("leader.txt", true));
                         write.append(strPlayerName[0]+" ");
                         write.append(black+"\r\n");
                         write.append(strPlayerName[1]+" ");
                         write.append(white+"\r\n");
                         write.close();
                    }
                    catch(Exception e)
                    {
                        System.out.println(e);
                    }
		   return strPlayerName[1] + " has won, with a score of " + white + ". " + strPlayerName[0] + " got " + black;			
		}
		else
		{
			return "this game was a draw with both players getting a score of " + white;
		}
		
	}
        
        public int getCount(String name, String[] strPlayerName)
        {
            int count=0, player, currentCell;
            if(name.equals(strPlayerName[0]))
                player=1;
            else
                player=2;
            for (int row = 0; row < 8; row++)
		{
			
			for (int column = 0; column < 8; column++)
			{
				
				currentCell = pieceMatrix[row][column];
				
				if (currentCell == player)
				{
					count++;
				}
				
				
			}
			
		}
            return count;
        }
	
}
