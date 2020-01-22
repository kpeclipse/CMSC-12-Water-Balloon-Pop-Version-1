import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.Timer;

public class GamePanel extends JFrame implements KeyListener
{
	private static final long serialVersionUID = -2657183267005854109L;
	private BufferedImage image;
	private File file = null;
	private GameImagePanel background;
	private JPanel panel, scorePanel, highScorePanel, dodgedBalloonsPanel, gameOverPanel, choicePanel;

	private JLabel villain;
	private JLabel scoreLabel, highScoreLabel, dodgedBalloonsLabel, gameOverLabel, exitLabel, againLabel;
	private JLabel[] player = new JLabel[4];
	private JLabel[] balloon = new JLabel[6];

	private int x = 400, freeFall = 0, blackFreeFall = 180, index, score, highScore;
	private int forVillain, forBalloon, balloonX, column, time = 1, blackTime = 1;
	private int balloonAI, playerAI;
	private int dodgedBalloons;
	private int position[] = {146, 321, 496, 671}, position1[] = {70, 245, 420, 595};

	private boolean hold = false, gameOver = false, over = false, again = false, flag;

	private String scoreTemp;
	private Random r = new Random();
	private UmbrellaClose uc;
	private Fall fall;
	private Collision collide;
	private VillainDrop villainDrop;

	private HighScore prevHScore;

	private SoundClip sound = new SoundClip("travel.wav", 0);
	private SoundClip soundcry = new SoundClip("Crycry.wav", 1);
	private SoundClip soundpop = new SoundClip("pop.wav", 1);

	public GamePanel()
	{
		setSize(1200,725);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);

		try
		{
			file = new File("Play.png");
			image = ImageIO.read(file);
			background = new GameImagePanel(image);
			
			panel = new JPanel();
			panel.setBounds(0, 0, 1200, 725);
			panel.setLayout(null);
			panel.setOpaque(false);

			scorePanel = new JPanel();
			scorePanel.setBounds(885, 200, 265, 75);
			scorePanel.setLayout(new GridLayout(1,1));
			scorePanel.setOpaque(false);

			highScorePanel = new JPanel();
			highScorePanel.setBounds(885, 70, 265, 75);
			highScorePanel.setLayout(new GridLayout(1,1));
			highScorePanel.setOpaque(false);

			dodgedBalloonsPanel = new JPanel();
			dodgedBalloonsPanel.setBounds(885, 350, 265, 75);
			dodgedBalloonsPanel.setLayout(new GridLayout(1,1));
			dodgedBalloonsPanel.setOpaque(false);

			gameOverPanel = new JPanel();
			gameOverPanel.setBounds(85, 265, 800, 75);
			gameOverPanel.setLayout(new GridLayout(1,1));
			gameOverPanel.setOpaque(false);

			choicePanel = new JPanel();
			choicePanel.setBounds(85, 375, 800, 50);
			choicePanel.setLayout(new GridLayout(2,1));
			choicePanel.setOpaque(false);

			add(panel);

			scoreLabel = new JLabel("0");
			scoreLabel.setFont(new Font("Emulogic", Font.PLAIN, 40));
			scoreLabel.setForeground(Color.WHITE);
			scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);

			prevHScore = new HighScore();
			highScore=prevHScore.num;

			highScoreLabel = new JLabel(scoreTemp=Integer.toString(highScore));
			highScoreLabel.setFont(new Font("Emulogic", Font.PLAIN, 40)); 
			highScoreLabel.setForeground(Color.WHITE);
			highScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);

			dodgedBalloonsLabel = new JLabel("0");
			dodgedBalloonsLabel.setFont(new Font("Emulogic", Font.PLAIN, 40));
			dodgedBalloonsLabel.setForeground(Color.WHITE);
			dodgedBalloonsLabel.setHorizontalAlignment(SwingConstants.CENTER);

			gameOverLabel = new JLabel("GAME OVER!");
			gameOverLabel.setFont(new Font("Emulogic", Font.PLAIN, 60));
			gameOverLabel.setForeground(Color.ORANGE);
			gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);

			againLabel = new JLabel("(Press ENTER to play again)");
			againLabel.setFont(new Font("Emulogic", Font.PLAIN, 20));
			againLabel.setForeground(Color.WHITE);
			againLabel.setHorizontalAlignment(SwingConstants.CENTER);

			exitLabel = new JLabel("(Press ESC to exit)");
			exitLabel.setFont(new Font("Emulogic", Font.PLAIN, 20));
			exitLabel.setForeground(Color.WHITE);
			exitLabel.setHorizontalAlignment(SwingConstants.CENTER);

			player[0] = new JLabel(new ImageIcon ("CLOSE.png"));
			player[0].setBounds(x, 435, 254, 254);

			player[1] = new JLabel(new ImageIcon ("OPEN.png"));
			player[1].setBounds(x, 435, 254, 254);

			player[2] = new JLabel(new ImageIcon ("WET.png"));
			player[2].setBounds(x, 435, 254, 254);

			player[3] = new JLabel(new ImageIcon ("CRY.png"));
			player[3].setBounds(x, 435, 254, 254);

			villain = new JLabel(new ImageIcon ("VILLAIN.png"));
			villain.setBounds(position1[2], 20, 200,200);
			
			balloon[0] = new JLabel(new ImageIcon ("RED BALLOON.png"));
			balloon[1] = new JLabel(new ImageIcon ("SHINY BALLOON.png"));
			balloon[2] = new JLabel(new ImageIcon ("BLACK BALLOON.png"));
			balloon[3] = new JLabel(new ImageIcon ("Red Pop.png"));
			balloon[4] = new JLabel(new ImageIcon ("Shiny Pop.png"));
			balloon[5] = new JLabel(new ImageIcon ("Black Pop.png"));

			for(int i=0; i<6; i++)
				panel.add(balloon[i]);

			panel.add(villain);
			add(gameOverPanel);
			add(choicePanel);
			add(scorePanel);
			add(highScorePanel);
			add(dodgedBalloonsPanel);
			add(background);

			panel.add(player[0]);
			panel.add(player[1]);
			panel.add(player[2]);
			panel.add(player[3]);
			highScorePanel.add(highScoreLabel);
			scorePanel.add(scoreLabel);
			dodgedBalloonsPanel.add(dodgedBalloonsLabel);
			gameOverPanel.add(gameOverLabel);
			choicePanel.add(againLabel);
			choicePanel.add(exitLabel);
			addKeyListener(this);

			player[1].setVisible(false);
			player[2].setVisible(false);
			player[3].setVisible(false);
			balloon[3].setVisible(false);
			balloon[4].setVisible(false);
			balloon[5].setVisible(false);
			gameOverPanel.setVisible(false);
			choicePanel.setVisible(false);

			repaint();

			score = 0;

			setVisible(true);
			
		}catch(IOException ioException){System.err.println("IOException occured!");}

		Start();
	}

	public void Start()
	{
		sound.start();

		TimerTask task = new TimerTask()
		{
			public void run()
			{
				if (gameOver == false)
					BeforeFall();

				else
				{
					if(over == true)
					{
						End();
						cancel();
					}

					if(again == true)
					{
						dispose();
						sound.stop();
 
						new NewHighScore(highScore);

						new GamePanel();
						cancel();
					}
				}
			}
		};

		Timer timer = new Timer("Timer");

		long delay = 100L;
		long period = 10L;
		timer.scheduleAtFixedRate(task, delay, period);

	}

	public void End()
	{
		dispose();
		sound.stop();

		new NewHighScore(highScore);
		new WaterBalloonPop();
	}

	public void BeforeFall()
	{
		try{
			column = r.nextInt(4);
			System.out.println("First Drop is at Column " + (column + 1));
			balloonX = position[column];

			index = r.nextInt(2);

			fall = new Fall();
			fall.start();

			Thread.sleep(100);

			villainDrop = new VillainDrop();
			villainDrop.start();
		}catch(Exception e){e.printStackTrace();}
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		int move = 175;

		if (key == KeyEvent.VK_LEFT && gameOver == false) {
			while(hold == false){
				x -= move;

				for(int i = 1; i < 4; i++)
					player[i].setVisible(false);
				
				player[0].setVisible(true);

				if (x < 0)
					x +=  move;
			
				for(int i = 0; i < 4; i++)
					player[i].setBounds(x, 435, 254, 254);

				hold = true;
			}
		}

		if (key == KeyEvent.VK_RIGHT && gameOver == false) {
			while(hold==false){
				x += move;
				
				for(int i = 1; i < 4; i++)
					player[i].setVisible(false);
				
				player[0].setVisible(true);

				if (x > 700)
					x -=  move;

				for(int i = 0; i < 4; i++)
					player[i].setBounds(x, 435, 254, 254);

				hold = true;
			}

		}

		if (key == KeyEvent.VK_SPACE && gameOver == false) {
			while (hold == false) {
				player[0].setVisible(false);
				player[2].setVisible(false);
				player[3].setVisible(false);
				player[1].setVisible(true);

				uc = new UmbrellaClose();
				uc.start();
				hold = true;
			}
		}

		if (key == KeyEvent.VK_ESCAPE) {
			gameOver = true;
			over = true;
		}

		if (key == KeyEvent.VK_ENTER) {
			if (gameOver == true)
				again = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();

		if(key == KeyEvent.VK_SPACE || key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT)
			hold=false;
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	class UmbrellaClose extends Thread
	{
		private boolean running=false;

		@Override
		public void run()
		{
			do
			{
				try
				{
					Thread.sleep(125);
					player[1].setVisible(false);
					player[0].setVisible(true);
					running=true;
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}while(running==false);
		}
	}

	class Fall extends Thread
	{
		public void start(){
			while(flag == true && gameOver == false){
				try {

					// System.out.println("Next villainX should be == " + forVillain);
					// System.out.println("Next balloonX should be == " + forBalloon + "\n");
					balloon[index].setVisible(true);
					
					balloon[index].setBounds(balloonX, freeFall, 61, 90);
					balloon[index + 3].setBounds(balloonX, freeFall, 61, 90);

					freeFall += 0.1 * time;
					time += 1;

					Thread.sleep(9);

					collide = new Collision();
					collide.start();
					
					// System.out.println(balloon[index].getY());
					if (balloon[index].getY() == 0) {
						balloonAI = balloon[index].getX();
						playerAI = player[0].getX();
					}
			
					if (freeFall > 800) {		
						freeFall = 0;
						time = 1;
						dodgedBalloons += 1;
						
						if(dodgedBalloons <= 5)
							score += 1;
	
						scoreLabel.setText(Integer.toString(score));
		
						if (score > highScore) {
							highScore=score;
							highScoreLabel.setText(Integer.toString(highScore));
						}

						if (dodgedBalloons <= 5) {
							
							dodgedBalloonsLabel.setText(Integer.toString(dodgedBalloons));
							
							if (dodgedBalloons == 4)
								dodgedBalloonsLabel.setForeground(Color.ORANGE);

							if (dodgedBalloons == 5) 
								dodgedBalloonsLabel.setForeground(Color.RED);
						}

						else if (dodgedBalloons > 5) {
							gameOver = true;
							sound.stop();
							soundcry.start();

							for(int i = 0; i < 3; i++)
								player[i].setVisible(false);
							
							player[3].setVisible(true);

							gameOverPanel.setVisible(true);
							choicePanel.setVisible(true);

							/*if(index == 0 || index == 1)
								score -= 1;
							else if(index == 3)
								score += 10;
							scoreTemp=Integer.toString(score);
							scoreLabel.setText(scoreTemp);*/
						}

						flag = false;
					}
				} catch (Exception e) {e.printStackTrace();}
			}
		}
	}

	class VillainDrop extends Thread
	{
		public void start(){
			try{
				Villain cp = new Villain();
				forVillain = cp.getVillainX(balloonAI, playerAI);
				forBalloon = cp.getBalloonX(balloonAI, playerAI);

				while(flag == false && gameOver == false){
					balloon[2].setVisible(true);

					villain.setBounds(forVillain, 20, 200, 200);
					balloon[2].setBounds(forBalloon, blackFreeFall, 61, 90);
					balloon[5].setBounds(forBalloon, blackFreeFall, 61, 90);
				
					Thread.sleep(9);
					BlackCollision blackCollide = new BlackCollision();
					blackCollide.start();
	
					blackFreeFall += 0.1 * blackTime;
					blackTime += 1;
					
					if (blackFreeFall > 800) {		
						blackFreeFall = 180;
						dodgedBalloons += 1;
						blackTime = 1;
								
						if(index == 2  && dodgedBalloons <= 5){
							score -= 10;
							if(score < 0)
							score = 0;
						}
		
						scoreLabel.setText(Integer.toString(score));
				
						if (score > highScore) {
							highScore=score;
							highScoreLabel.setText(Integer.toString(highScore));
						}
		
						if (dodgedBalloons <= 5) {
							dodgedBalloonsLabel.setText(Integer.toString(dodgedBalloons));
						
							if (dodgedBalloons == 4)
								dodgedBalloonsLabel.setForeground(Color.ORANGE);
		
							if (dodgedBalloons == 5) 
								dodgedBalloonsLabel.setForeground(Color.RED);
						}
		
						else if (dodgedBalloons > 5) {
							gameOver = true;
							sound.stop();
							soundcry.start();
		
							for(int i = 0; i < 3; i++)
								player[i].setVisible(false);
									
								player[3].setVisible(true);
		
								gameOverPanel.setVisible(true);
								choicePanel.setVisible(true);
		
									/*if(index == 0 || index == 1)
										score -= 1;
									else if(index == 3)
										score += 10;
									scoreTemp=Integer.toString(score);
									scoreLabel.setText(scoreTemp);*/
						}
		
						flag = true;
					}
				}
			} catch (Exception e) {e.printStackTrace();}
		}	
	}
	

	class Collision extends Thread
	{
		public void start()
		{
			Rectangle wBalloon = new Rectangle(balloonX, freeFall, 61, 90);
			Rectangle open = new Rectangle(x + 50, 444, 179, 254);
			Rectangle close = new Rectangle(x + 50, 535, 179, 184);
			boolean popped = false;
								
			// DID NOT POP
			if(wBalloon.intersects(close) && player[0].isVisible())
			{	
				System.out.println("HIT! \n----------------------------");
				gameOver = true;

				balloon[index + 3].setBounds(balloonX, balloon[index].getY(), 61, 90);
				balloon[index].setVisible(false);
				balloon[index + 3].setVisible(true);
				
				//flag = false;

				sound.stop();
				soundpop.start();
				soundcry.start();

				player[0].setVisible(false);
				player[2].setVisible(true);

				gameOverPanel.setVisible(true);
				choicePanel.setVisible(true);	
			}

			// POP
			else if(wBalloon.intersects(open) && player[1].isVisible())
			{
				if(index == 0 || index == 1)
					time = 1;

				balloon[index + 3].setVisible(true);
				balloon[index].setVisible(false);
				System.out.println("POPPED! \n----------------------------");

				while (flag == true) {
					switch (index) {
						case 0:
							score += 10;
							break;
						case 1:
							score += 30;
							break;
					}
					
					scoreTemp=Integer.toString(score);
					scoreLabel.setText(scoreTemp);

					if (score > highScore) {
						highScore = score;
						highScoreLabel.setText(scoreTemp=Integer.toString(highScore));
					}

					flag = false;
				}

				do {
					try {
						soundpop.start();
						Thread.sleep(250);
						balloon[index + 3].setVisible(false);
						soundpop.pause();
						freeFall = 0;				
						popped=true;
					} catch (Exception ex) {ex.printStackTrace();}
				} while (popped == false);
				// firstDrop = false;
			}
		}
	}

	class BlackCollision extends Thread
	{
		public void start()
		{
			Rectangle wBalloon = new Rectangle(forBalloon, blackFreeFall, 61, 90);
			Rectangle open = new Rectangle(x + 50, 444, 179, 254);
			Rectangle close = new Rectangle(x + 50, 535, 179, 184);
			boolean popped = false;
								
			// DID NOT POP
			if(wBalloon.intersects(close) && player[0].isVisible())
			{	
				System.out.println("HIT! \n----------------------------");
				gameOver = true;

				balloon[5].setBounds(forBalloon, balloon[2].getY(), 61, 90);
				balloon[2].setVisible(false);
				balloon[5].setVisible(true);
				
				//flag = false;

				sound.stop();
				soundpop.start();
				soundcry.start();

				player[0].setVisible(false);
				player[2].setVisible(true);

				gameOverPanel.setVisible(true);
				choicePanel.setVisible(true);	
			}

			// POP
			else if(wBalloon.intersects(open) && player[1].isVisible())
			{
				blackTime = 1;

				balloon[5].setVisible(true);
				balloon[2].setVisible(false);
				System.out.println("POPPED! \n----------------------------");

				while (flag == false){ 
					score += 20;
					
					scoreTemp=Integer.toString(score);
					scoreLabel.setText(scoreTemp);

					if (score > highScore) {
						highScore = score;
						highScoreLabel.setText(scoreTemp=Integer.toString(highScore));
					}

					flag = true;
				}

				do {
					try {
						soundpop.start();
						Thread.sleep(250);
						balloon[5].setVisible(false);
						soundpop.pause();

						blackFreeFall = 180;				
						popped = true;
					} catch (Exception ex) {ex.printStackTrace();}
				} while (popped == false);
				// firstDrop = false;
			}
		}
	}
}