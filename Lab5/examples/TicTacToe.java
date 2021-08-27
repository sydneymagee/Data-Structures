import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.util.ArrayList;
/**
 *TicTacToe.java
 * This class is a simple GUI Tic tac toe game.
 * there is no win condition
 * @author Neil Butcher
 *	@version 2/23/2012
 */
public class TicTacToe 
{
	JFrame gameFrame;
	JPanel buttonPanel;
	boolean playerOneTurn;
	JLabel display;
	ArrayList<JButton> buttons;
	/*
	 * Constructor
	 * initializes the variables with help of
	 * initialize components method
	 */
	public TicTacToe()
	{
		buttonPanel= new JPanel();
	buttons=new ArrayList<JButton>();
	 gameFrame= new JFrame();
	 gameFrame.setLocation(100, 100);
	 gameFrame.setSize(175, 175);
	 gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 gameFrame.setVisible(true);
	 gameFrame.setTitle("Tic Tac Toe");
	 initializeComponents();
	}
	
	private void initializeComponents()
	{
		addButtons();
		gameFrame.add(buttonPanel);
		
	}

	private void addButtons()
	{
		for(int x=0; x<9; x++)
		{
			JButton btn=new JButton("-");
			buttons.add(btn);
			buttonPanel.add(buttons.get(x));
			System.out.println("added a button!");
			btn.addActionListener( new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					JButton subs=(JButton) e.getSource();
				if(playerOneTurn)
					subs.setText("X");
				else
					subs.setText("O");
				
				playerOneTurn = !playerOneTurn; // switch turn
				}
			});
		}
	}
	public static void main(String args[])
	{
		new TicTacToe();
	}
	
}
