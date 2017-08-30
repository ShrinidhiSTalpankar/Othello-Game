
package othello;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JOptionPane;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.audio.*;

public class othello extends JPanel implements ActionListener, KeyListener
{
	static String player1Avatar = "default.png";
        static String player2Avatar = "default.png";
	private windowOthelloBoard mainOthelloBoard;
	private objCreateAppletImage createImage;
	private JButton cmdNewGame, cmdSetNames, cmdConfirm;
	static JTextField txtPlayerOne, txtPlayerTwo;
	private JLabel lblPlayerOne, lblPlayerTwo;
	private JPanel panBottomHalf, panNameArea, panPlayerOne, panPlayerTwo, panNameButton, panGameButtons, panAvatars , panAvatar1, panAvatar2;
	private Color Backclr = new Color(250,241,221);
	private JButton cmdChooseAvatars, cmdLeaderBoard;
        static JLabel player1IconLabel,player2IconLabel;
        public static int ng=0;
        
	public static void main(String[] args) 
	{
        
        
	JFrame.setDefaultLookAndFeelDecorated(true); //Make it look nice
        JFrame frame = new JFrame("Othello"); //Title bar text
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false); //Stops the user from resizing the window
        
        JComponent paneMain = new othello();
        paneMain.setOpaque(true);
	paneMain.setPreferredSize(new Dimension(500, 650)); //Window Size
        frame.setContentPane(paneMain);
                
        frame.pack();
        frame.setVisible(true);
        
    }
	
	public othello ()
	{
		
		this.setLayout(new BorderLayout());			
		mainOthelloBoard = new windowOthelloBoard();
		createImage = new objCreateAppletImage();
		
		mainOthelloBoard.setSize(new Dimension(500, 500));
		
		cmdNewGame = new JButton("New Game");
		cmdSetNames = new JButton("Set Names");
		cmdConfirm = new JButton("Confirm Move");
                cmdChooseAvatars = new JButton("Choose Avatars");
                cmdLeaderBoard = new JButton("Leader Board");
                
                player1IconLabel = new JLabel();
                player2IconLabel = new JLabel();
                
                
		
		cmdNewGame.addActionListener(this);
		cmdSetNames.addActionListener(this);
		cmdConfirm.addActionListener(this);
                cmdChooseAvatars.addActionListener(this);
                cmdLeaderBoard.addActionListener(this);
		
		txtPlayerOne = new JTextField("Player1", 10);
		txtPlayerTwo = new JTextField("Player2", 10);
		
		txtPlayerOne.addKeyListener(this);
		txtPlayerTwo.addKeyListener(this);
		
		lblPlayerOne = new JLabel("", JLabel.RIGHT);
		lblPlayerTwo = new JLabel("", JLabel.RIGHT);
		
		try
		{
				
			Image imgBlack = createImage.getImage(this, "black.png", 3000);
			Image imgWhite = createImage.getImage(this, "white.png", 3000);
			Image imgTile = createImage.getImage(this, "tile.png", 2000);
			mainOthelloBoard.setupImages(imgBlack, imgWhite, imgTile);
			
		}
		catch (NullPointerException e)
		{
			
			JOptionPane.showMessageDialog(null, "Unable to load images.Make sure that these images are in the project folder", "Unable to load images", JOptionPane.WARNING_MESSAGE);
			cmdNewGame.setEnabled(false);
			cmdSetNames.setEnabled(false);
			cmdConfirm.setEnabled(false);
                        cmdLeaderBoard.setEnabled(false);
			
		}
		
		panBottomHalf = new JPanel(new BorderLayout());
		panNameArea = new JPanel(new GridLayout(3,1,2,2));
		panPlayerOne = new JPanel();
		panPlayerTwo = new JPanel();
		panNameButton = new JPanel();
		panGameButtons = new JPanel();
                //panAvatars = new JPanel();
                panAvatar1 = new JPanel();
                panAvatar2 = new JPanel();
                panAvatars = new JPanel(new GridLayout(0,2));
		
		add(mainOthelloBoard, BorderLayout.NORTH);
		add(panBottomHalf, BorderLayout.SOUTH);
                //add(panAvatars,BorderLayout.EAST);
		panBottomHalf.add(panNameArea, BorderLayout.WEST);
                panBottomHalf.add(panAvatars,BorderLayout.EAST);
		panNameArea.add(panPlayerOne);
		panPlayerOne.add(lblPlayerOne);
		panPlayerOne.add(txtPlayerOne);
		panNameArea.add(panPlayerTwo);
		panPlayerTwo.add(lblPlayerTwo);
		panPlayerTwo.add(txtPlayerTwo);
		panNameArea.add(panNameButton);
		panNameButton.add(cmdSetNames);
		panBottomHalf.add(panGameButtons, BorderLayout.SOUTH);
		panGameButtons.add(cmdConfirm);
		panGameButtons.add(cmdNewGame);
                panGameButtons.add(cmdChooseAvatars);
                panGameButtons.add(cmdLeaderBoard);
                
                ImageIcon icon1 = new ImageIcon(getClass().getResource(player1Avatar));
               player1IconLabel.setIcon( icon1 );
                ImageIcon icon2 = new ImageIcon(getClass().getResource(player2Avatar));
                player2IconLabel.setIcon( icon2 );
                //player1IconLabel.setText("");
                //player2IconLabel.setText("");
                
                panAvatar1.add(player1IconLabel);
                panAvatar2.add(player2IconLabel);
                panAvatars.add(panAvatar1);
		panAvatars.add(panAvatar2);
                
                
		setBackground(Backclr);
		panBottomHalf.setBackground(Backclr);
		panNameArea.setBackground(Backclr);
		panPlayerOne.setBackground(Backclr);
		panPlayerTwo.setBackground(Backclr);
		panNameButton.setBackground(Backclr);
		panGameButtons.setBackground(Backclr);
                panAvatars.setBackground(Backclr);
                panAvatar1.setBackground(Backclr);
                panAvatar2.setBackground(Backclr);
		
		lblPlayerOne.setBackground(new Color(236,17,17)); //red
		lblPlayerOne.setForeground(new Color(236,17,17));
		lblPlayerTwo.setBackground(new Color(17,27,237)); //blue
		lblPlayerTwo.setForeground(new Color(17,27,237));
		
		panGameButtons.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		
	}
	
    @Override
	public void actionPerformed(ActionEvent e) 
	{
		System.out.println("HI!!!");
		if (e.getSource() == cmdSetNames)
		{
                
                
                if (txtPlayerOne.getText().equals(""))
                {
                    txtPlayerOne.setText("Enter name");
                }
                
                if (txtPlayerTwo.getText().equals(""))
                {
                    txtPlayerTwo.setText("Enter name");
                }
                
                mainOthelloBoard.setNames(txtPlayerOne.getText(), txtPlayerTwo.getText());
            
			
		}
		else if (e.getSource() == cmdNewGame)
		{
                        ng=1;
			mainOthelloBoard.newGame();
		}
		else if (e.getSource() == cmdConfirm)
		{
			mainOthelloBoard.confirm();
		}
                else if (e.getSource() == cmdChooseAvatars)
                {
                    System.out.println("Avatar button was pressed");
                    ChooseAvatarFrame AvatarFrame = new ChooseAvatarFrame();
                    AvatarFrame.show();
                }  
                else if (e.getSource() == cmdLeaderBoard)
                {
                    System.out.println("leader board button was pressed");
                    Player p = new Player();
                   
                }
               
		
	}
	
    
	 public void keyTyped(KeyEvent e)
	{
		
                
		String strBuffer = "";
		char c = e.getKeyChar();
		
		if (e.getSource() == txtPlayerOne)
		{
			strBuffer = txtPlayerOne.getText();
		}
		else
		{
			strBuffer = txtPlayerTwo.getText();
		}
		
		if (strBuffer.length() > 10 && !((c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))
		{
			e.consume();
		}
		
	}
	
	public void keyPressed(KeyEvent e)
	{
	}
	
	public void keyReleased(KeyEvent e)
	{
	}
	
}