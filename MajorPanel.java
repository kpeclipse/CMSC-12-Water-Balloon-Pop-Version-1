import java.io.*;
import java.util.*;
import javax.swing.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.net.URL;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class MajorPanel extends JPanel implements ActionListener, MouseListener
{
	private BufferedImage image;
	private File file = null;
	private GameImagePanel howbg, creditsbg, exitbg;
	public GameImagePanel mainbg;
	public JPanel mainPanel;
	public JPanel menuPanelForPlay, mainMenuPanel, highscorePanel;
	private JButton howToPlay, credits, exit, back, yes, no, music, sfx;
	public SoundClip sound = new SoundClip("uku.wav", 0);
	private HighScore newHighScore;
	private JLabel highscoreLabel;
	private String scorestr;
	private int highScore;
	private ImageIcon how1, how2; 
	private ImageIcon credits1, credits2; 
	private ImageIcon exit1, exit2;
	private ImageIcon back1, back2;
	private ImageIcon yes1, yes2;
	private ImageIcon no1, no2;

	public JButton button(JButton theButton, ImageIcon icon)
	{
		theButton.setIcon(icon);
		theButton.setContentAreaFilled(false);
		theButton.setFocusPainted(false);
		theButton.setBorder(BorderFactory.createEmptyBorder());
		theButton.addActionListener(this);
		theButton.addMouseListener(this);
		return theButton;
	}

	public MajorPanel(){
	
	setSize(1200,725);
	setLayout(null);
	setOpaque(false);

	mainPanel = new JPanel();
	mainPanel.setBounds(0, 0, 1200, 700);
	mainPanel.setOpaque(false);
	mainPanel.setLayout(null);

	menuPanelForPlay = new JPanel();
	menuPanelForPlay.setBounds(670, 420, 350, 55);
	menuPanelForPlay.setLayout(new GridLayout(1,1));
	menuPanelForPlay.setOpaque(false);

	mainMenuPanel = new JPanel();
	mainMenuPanel.setBounds(670, 475, 350, 165);
	mainMenuPanel.setLayout(new GridLayout(3,1));
	mainMenuPanel.setOpaque(false);

	highscorePanel = new JPanel();
	highscorePanel.setBounds(670, 330, 350, 50);
	highscorePanel.setOpaque(false);

	add(menuPanelForPlay);
	add(mainMenuPanel);
	add(mainPanel);
	add(highscorePanel);

	try
	{
		file = new File("Main.png");
		image = ImageIO.read(file);
		mainbg = new GameImagePanel(image);
		add(mainbg);
	}catch(IOException ioException){System.err.println("IOException occured!");	}
		repaint();
	
	try
	{
		file = new File("How To Play.png");
		image = ImageIO.read(file);
		howbg = new GameImagePanel(image);
	}catch(IOException ioException){System.err.println("IOException occured!");}

	try
	{
		file = new File("Credits.png");
		image = ImageIO.read(file);
		creditsbg = new GameImagePanel(image);
	}catch(IOException ioException){System.err.println("IOException occured!");}

	try
	{
		file = new File("Exit.png");
		image = ImageIO.read(file);
		exitbg = new GameImagePanel(image);
	}catch(IOException ioException){System.err.println("IOException occured!");}		

	howToPlay = new JButton();
	how1 = new ImageIcon("how1.png");
	button(howToPlay, how1);

	credits = new JButton();
	credits1 = new ImageIcon("credits1.png");
	button(credits, credits1);
		
	exit = new JButton();
	exit1 = new ImageIcon("exit1.png");
	button(exit, exit1);

	back = new JButton();
	back1 = new ImageIcon("back1.png");
	button(back, back1);
	back.setBounds(5, 20, 200, 100);
	
	yes = new JButton();
	yes1 = new ImageIcon("yes1.png");
	button(yes,yes1);
	yes.setBounds(250, 200, 200,150);
	
	no = new JButton();
	no1 = new ImageIcon("no1.png");
	button(no, no1);
	no.setBounds(700, 200, 200,150);

	newHighScore = new HighScore();
	highScore = newHighScore.num;
	scorestr=Integer.toString(highScore);

	highscoreLabel = new JLabel("High Score: " + scorestr);
	highscoreLabel.setLocation(0, 0);
	highscoreLabel.setSize(220, 50);
	highscoreLabel.setFont(new Font("Emulogic", Font.BOLD, 18));
	highscoreLabel.setForeground(Color.ORANGE);
	highscoreLabel.setBackground(new Color(94,103,175));
	highscoreLabel.setHorizontalAlignment(JTextField.CENTER);
	highscoreLabel.setOpaque(false);
	highscoreLabel.setBorder(BorderFactory.createEmptyBorder());
	
	highscorePanel.add(highscoreLabel);

	back.addActionListener(this);
	back.addMouseListener(this);

	yes.addActionListener(this);
	yes.addMouseListener(this);

	no.addActionListener(this);
	no.addMouseListener(this);

	howToPlay.addActionListener(this);
	howToPlay.addMouseListener(this);

	credits.addActionListener(this);
	credits.addMouseListener(this);

	exit.addActionListener(this);
	exit.addMouseListener(this);

	mainMenuPanel.add(howToPlay);
	mainMenuPanel.add(credits);
	mainMenuPanel.add(exit);	

	mainPanel.setVisible(true);

	setVisible(true);	
	sound.start();
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		if(e.getComponent() == howToPlay)
		{
			how2 = new ImageIcon("how2.png");
			howToPlay.setIcon(how2);
		}

		if(e.getComponent() == credits)
		{
			credits2 = new ImageIcon("credits2.png");
			credits.setIcon(credits2);
		}

		if(e.getComponent() == exit)
		{
			exit2 = new ImageIcon("exit2.png");
			exit.setIcon(exit2);
		}

		if(e.getComponent() == back)
		{
			back2 = new ImageIcon("back2.png");
			back.setIcon(back2);
		}

		if(e.getComponent() == yes)
		{
			yes2 = new ImageIcon("yes2.png");
			yes.setIcon(yes2);
		}

		if(e.getComponent() == no)
		{
			no2 = new ImageIcon("no2.png");
			no.setIcon(no2);
		}
	}

	public void mouseExited(MouseEvent exited)
	{
		howToPlay.setIcon(how1);
		credits.setIcon(credits1);
		exit.setIcon(exit1);
		back.setIcon(back1);
		yes.setIcon(yes1);
		no.setIcon(no1);
	}

	public void mouseReleased(MouseEvent e)
	{
		howToPlay.setIcon(how1);
		credits.setIcon(credits1);
		exit.setIcon(exit1);
		back.setIcon(back1);
		yes.setIcon(yes1);
		no.setIcon(no1);	
	}
	
	public void mouseClicked(MouseEvent e){}
	public void mousePressed(MouseEvent e){}

	@Override
	public void actionPerformed(ActionEvent event)
	{

		if(event.getSource()==howToPlay)
		{
			remove(mainbg);
			remove(mainMenuPanel);
			remove(menuPanelForPlay);
			remove(highscorePanel);
			add(back);
			add(howbg);
			repaint();
		}

		else if(event.getSource()==credits)
		{
			remove(mainMenuPanel);
			remove(menuPanelForPlay);
			remove(highscorePanel);
			remove(mainbg);
			add(back);
			add(creditsbg);
			repaint();
		}

		else if(event.getSource()==exit)
		{
			remove(mainMenuPanel);
			remove(menuPanelForPlay);
			remove(highscorePanel);
			remove(mainbg);
			add(yes);
			add(no);
			add(exitbg);
			repaint();
		}
			
		else if(event.getSource() == back)
		{
			remove(back);
			remove(howbg);
			remove(creditsbg);
			add(mainMenuPanel);
			add(menuPanelForPlay);
			add(highscorePanel);
			add(mainbg);
			repaint();
		}

		else if(event.getSource() == no)
		{
			remove(yes);
			remove(no);
			remove(exitbg);
			add(mainMenuPanel);
			add(menuPanelForPlay);
			add(highscorePanel);
			add(mainbg);
			repaint();
		}

		else if(event.getSource() == yes)
		{
			System.exit(0);
		}
	}
}
