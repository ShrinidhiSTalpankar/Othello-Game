
package othello;

import sun.audio.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
//This programme extends the standard black and white 8 * 8 (64 cell) chess board
public class windowOthelloBoard extends objChessBoard implements MouseListener
{
	
	private Image[] imgPlayer = new Image[2]; //Stores the coin images
	private String[] strPlayerName = {"Player1", "Player2"}; //Stores the player names
	private String strStatusMsg = ""; //Stores the current status msg to display
	private objMoveCheck moveCheck = new objMoveCheck(); //Move checker and cell matrix
	private int currentPlayer = 1, coinPlacedRow = 0, coinPlacedColumn = 0; //The current coin placement
	private boolean firstTime = true; //Before you have pressed new game for the first time
	private boolean hasWon = false; //If the board is full
	private boolean coinPlaced = false; //If a coin has been placed, but it has not been confirmed yet
	
	public windowOthelloBoard ()
	{	
                InputStream in;
                try
                {
                    in=new FileInputStream(new File("C:\\Users\\Chandana N T\\Desktop\\Othello2\\src\\othello\\startup.wav"));
                    AudioStream coinclick=new AudioStream(in);
                    AudioPlayer.player.start(coinclick);
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null,e);
                }    
		this.addMouseListener(this); //For mouse clicking
	}
	
	private void setStatusMsg () //Sets a default msg depending on the current game state
	{
		
		if (hasWon) //If a game has been won
		{
                try {
                    strStatusMsg = moveCheck.calculateWinner(strPlayerName);
                } catch (IOException ex) {
                Logger.getLogger(windowOthelloBoard.class.getName()).log(Level.SEVERE, null, ex);
                }
		}
		else if (firstTime) //Displays which player is which colour
		{
			strStatusMsg = "" + strPlayerName[0] + " you are black, " + strPlayerName[1] + " you are white. Press new game to start";
		}
		else //Normal move
		{
			strStatusMsg = "" + strPlayerName[currentPlayer - 1] + " move";
		}
		
	}		 
	
	private void resetBoard () //Resets the game
	{
		
		hasWon = false;
		coinPlaced = false;
		currentPlayer = 1;
		setStatusMsg();
		moveCheck.resetMatrix(); //Resets the cell matrix to the 4 coins in the middle layout
		repaint();
		
	}
	//Set up the coin images
	public void setupImages (Image imgBlack, Image imgWhite, Image imgTile)
	{
		
		imgPlayer[0] = imgBlack;
		imgPlayer[1] = imgWhite;
		this.imgTile = imgTile;
		resetBoard();
		
	}
	//Set up the names (can be done while playing)
	public void setNames (String strPlayer1Name, String strPlayer2Name)
	{
		
		strPlayerName[0] = strPlayer1Name;
		strPlayerName[1] = strPlayer2Name;
		setStatusMsg();
		repaint();
		
	}
	//After the chess board is drawn, this method is called
	protected void drawExtra (Graphics g)
	{
		
		int pieceCell = 0;
		
		for (int row = 0; row < 8; row++)
		{
			
			for (int column = 0; column < 8; column++)
			{
				//Get current cell contents
				pieceCell = moveCheck.getPieceCell(row, column);
				//If coin has been placed
				if ((coinPlaced) && (row == coinPlacedRow) && (column == coinPlacedColumn))
				{
					//Draw the coin and a green border
					g.drawImage(imgPlayer[currentPlayer - 1], ((column + 1) * 50), ((row + 1) * 50), this);
					g.setColor(new Color(31,200,75));
					g.drawRect(((column + 1) * 50), ((row + 1) * 50), 50, 50);
					
				}
				else if (pieceCell != 0) //If the cell is not empty
				{
					g.drawImage(imgPlayer[pieceCell - 1], ((column + 1) * 50), ((row + 1) * 50), this);
				}
				
			}
			
		}
	
		g.setColor(new Color(75,0,153)); //Draws the status msg string
		g.drawString(strStatusMsg, 50, 475);
                
                int p1, p2;
        p1=moveCheck.getCount(strPlayerName[0],strPlayerName);
        p2=moveCheck.getCount(strPlayerName[1],strPlayerName);
		g.setColor(new Color(75,0,153)); //Draws the status msg string
		g.drawString(strStatusMsg, 50, 475);
                g.setColor(new Color(75,0,153));
                g.fill3DRect(50, 5, 400, 30, true);
                g.setColor(new Color(255,255,0));
                g.setFont(new Font("Calibri",Font.BOLD,16));
		g.drawString(strPlayerName[0]+" : "+p1, 75, 25);
                g.drawString(strPlayerName[1]+" : "+p2, 350, 25);
		
	}
	
	public void newGame ()
	{
		
		firstTime = false;
		resetBoard();
				
	}
	
	public void mouseClicked (MouseEvent e) //When mouse is pressed and released
	{
		if(othello.ng==1)
                {    
                InputStream in;
                    try
                    {
                        in=new FileInputStream(new File("C:\\Users\\Chandana N T\\Desktop\\Othello2\\src\\othello\\coinflip.wav"));
                        AudioStream coinclick=new AudioStream(in);
                        AudioPlayer.player.start(coinclick);
                    }
                    catch(Exception f)
                    {
                        JOptionPane.showMessageDialog(null,f);
                    }    
                }
		if (!hasWon && !firstTime) //If can play
		{			
			
			int selectedRow = findWhichTileSelected(e.getY()); //Method in chess board finds the cell that the mouse was over
			int selectedColumn = findWhichTileSelected(e.getX());
			
			if (selectedRow != -1 && selectedColumn != -1) //If not in the border
			{
				//If the cell is empty
				if (moveCheck.getPieceCell(selectedRow, selectedColumn) == 0)
				{
					
					coinPlacedRow = selectedRow; //Save the row and column and mark the coin as being placed
					coinPlacedColumn = selectedColumn;
					coinPlaced = true;
					repaint();
					
				}
				
			}
			
		}
		
	}
	
	public void mouseEntered (MouseEvent e) //Not used, but required since implementing mouseListener
	{
	}
	
	public void mouseExited (MouseEvent e)
	{
	}
	
	public void mousePressed (MouseEvent e)
	{
	}
	
	public void confirm () //Pressed the confirm button
	{
		
		if (coinPlaced) //If a coin has been placed
		{
			
			if (moveCheck.legalMove(coinPlacedRow, coinPlacedColumn, currentPlayer, true)) //If the move is legal (the true says this is an actual move)
			{
				
				moveCheck.checkPossibleMoves();
				
				boolean playerOneCanMove = moveCheck.playerOneCanMove();
				boolean playerTwoCanMove = moveCheck.playerTwoCanMove();
				
				if (!playerOneCanMove && !playerTwoCanMove) //If neither players can move
				{		
								
					hasWon = true;
					setStatusMsg();
					
				}
				else //If at least one player can move
				{
					
					if (currentPlayer == 1 && playerTwoCanMove) //change to the oposite player, providing they can move
					{
						
						currentPlayer = 2;
						setStatusMsg();
						
					}
					else if (currentPlayer == 2 && playerOneCanMove)
					{
						
						currentPlayer = 1;
						setStatusMsg();
						
					}
					else
					{
						
						if (currentPlayer == 1)
						{
							strStatusMsg = "" + strPlayerName[1] + " can not move, " + strPlayerName[0] + " move again";
						}
						else
						{
							strStatusMsg = "" + strPlayerName[0] + " can not move, " + strPlayerName[1] + " move again";
						}
						
					}

					
				}			
				
			}
			else //move not legal
			{
				strStatusMsg = "There are no possible over takes from that square, please choose another";
			}
			
		}
		else //coin not placed
		{
			
			if (hasWon || firstTime)
			{				
				strStatusMsg = "Press New Game";
			}		
			else
			{
				strStatusMsg = "Click where you want to place your coin before clicking confirm";
			}
						
		}
		
		coinPlaced = false;
		repaint();
		
	}			
	
	public void mouseReleased (MouseEvent e)
	{		
	}
			
}