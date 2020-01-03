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
import java.util.Timer;

public class GamePanel extends JFrame implements KeyListener
{
	private BufferedImage image;
	private File file = null;
	private GameImagePanel background;
	private JPanel panel, scorePanel, highScorePanel, dodgedBalloonsPanel, gameOverPanel, choicePanel;

	private JLabel player1, player2, player3, player4;
	private JLabel villain1, villain2;
	private JLabel scoreLabel, highScoreLabel, dodgedBalloonsLabel, gameOverLabel, exitLabel, againLabel;
	private JLabel[] balloon = new JLabel[6];
	private JLabel[] balloonSet1 = new JLabel[6], balloonSet2 = new JLabel[6];

	private int x=350, b1, b2, b3, b4, b5, n1, n2, n3, n4, n5, c1=0, c2=0, c3=0, c4=0, c5=0, score, highScore;
	private int y, add1, add2, time1=9, time2=7, color1, color2, interval1, interval2, interval3, dodgedBalloons=0;
	private int position[] = {146, 296, 468, 596, 746};
	private String scoreTemp, dodgedBalloonsTemp, line;
	private boolean flag1=true, flag2=true, flag3=true, flag4=true, flag5=true;
	private boolean hold=false, gameOver=false, over=false, again=false;
	private Random r = new Random();

	private UmbrellaClose uc;
	private Fall fall;

	private GameMainClass game;
	private NewHighScore newHighScore;
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

			player1 = new JLabel(new ImageIcon ("CLOSE.png"));
			player1.setBounds(x, 444, 254, 254);

			player2 = new JLabel(new ImageIcon ("OPEN.png"));
			player2.setBounds(x, 444, 254, 254);

			player3 = new JLabel(new ImageIcon ("WET.png"));
			player3.setBounds(x, 444, 254, 254);

			player4 = new JLabel(new ImageIcon ("CRY.png"));
			player4.setBounds(x, 444, 254, 254);

			villain1 = new JLabel(new ImageIcon ("VILLAIN.png"));
			villain1.setBounds(680,10,200,200);
			
			balloon[0] = new JLabel(new ImageIcon ("RED BALLOON.png"));
			balloon[1] = new JLabel(new ImageIcon ("SHINY BALLOON.png"));
			balloon[2] = new JLabel(new ImageIcon ("BLACK BALLOON.png"));
			balloon[3] = new JLabel(new ImageIcon ("Red Pop.png"));
			balloon[4] = new JLabel(new ImageIcon ("Shiny Pop.png"));
			balloon[5] = new JLabel(new ImageIcon ("Black Pop.png"));
			balloonSet1[0] = new JLabel(new ImageIcon ("RED BALLOON.png"));
			balloonSet2[0] = new JLabel(new ImageIcon ("RED BALLOON.png"));
			balloonSet1[1] = new JLabel(new ImageIcon ("BLACK BALLOON.png"));
			balloonSet2[1] = new JLabel(new ImageIcon ("BLACK BALLOON.png"));
			balloonSet1[2] = new JLabel(new ImageIcon ("SHINY BALLOON.png"));
			balloonSet2[2] = new JLabel(new ImageIcon ("SHINY BALLOON.png"));
			balloonSet1[3] = new JLabel(new ImageIcon ("Red Pop.png"));
			balloonSet2[3] = new JLabel(new ImageIcon ("Red Pop.png"));
			balloonSet1[4] = new JLabel(new ImageIcon ("Black Pop.png"));
			balloonSet2[4] = new JLabel(new ImageIcon ("Black Pop.png"));
			balloonSet1[5] = new JLabel(new ImageIcon ("Shiny Pop.png"));
			balloonSet2[5] = new JLabel(new ImageIcon ("Shiny Pop.png"));
				
			for(int i=0; i<6; i++)
			{
				panel.add(balloon[i]);
			}

			for(int i=0; i<6; i++)
			{
				panel.add(balloonSet1[i]);
			}

			for(int i=0; i<6; i++)
			{
				panel.add(balloonSet2[i]);
			}

			panel.add(villain1);
			add(gameOverPanel);
			add(choicePanel);
			add(scorePanel);
			add(highScorePanel);
			add(dodgedBalloonsPanel);
			add(background);


			panel.add(player1);
			panel.add(player2);
			panel.add(player3);
			panel.add(player4);
			highScorePanel.add(highScoreLabel);
			scorePanel.add(scoreLabel);
			dodgedBalloonsPanel.add(dodgedBalloonsLabel);
			gameOverPanel.add(gameOverLabel);
			choicePanel.add(againLabel);
			choicePanel.add(exitLabel);
			addKeyListener(this);

			player2.setVisible(false);
			player3.setVisible(false);
			player4.setVisible(false);
			balloon[3].setVisible(false);
			balloon[4].setVisible(false);
			balloon[5].setVisible(false);
			balloonSet1[3].setVisible(false);
			balloonSet1[4].setVisible(false);
			balloonSet1[5].setVisible(false);
			balloonSet2[3].setVisible(false);
			balloonSet2[4].setVisible(false);
			balloonSet2[5].setVisible(false);
			gameOverPanel.setVisible(false);
			choicePanel.setVisible(false);

			repaint();

			y=0;
			score = 0;

			setVisible(true);
			
		}catch(IOException ioException){
			System.err.println("IOException occured!");
		}

		Start();
	}

	public void Start()
	{
		sound.start();

		TimerTask task = new TimerTask()
		{
			public void run()
			{
				BeforeFall(1);

				if(y>=10)
				{
					BeforeFall(2);
				}

				if(y>=20)
				{
					BeforeFall(3);

					try{
						BeforeFall(4);

						Thread.sleep(1000);

						BeforeFall(5);
					}catch(Exception e){e.printStackTrace();}
				}

				if(gameOver==true)
				{
					if(over==true)
					{
						End();
						cancel();
					}

					if(again==true)
					{
						dispose();
						sound.stop();

						newHighScore = new NewHighScore(highScore);

						new GamePanel();
						cancel();
					}
				}
			}
		};

		Timer timer = new Timer("Timer");

		long delay = 1000L;
		long period = 1000L;
		timer.scheduleAtFixedRate(task, delay, period);
	}

	public void End()
	{
		dispose();
		sound.stop();

		newHighScore = new NewHighScore(highScore);

		game = new GameMainClass();
	}

	public void BeforeFall(int number)
	{
		try{
			setVisible(true);
			switch (number){
				case 1:
					n1 = r.nextInt(5);
					interval1 = r.nextInt(1500)+2500;

					fall = new Fall();
					fall.start(1);

					Thread.sleep(interval1);

					if(c1==4 && time1>5)
					{
						time1--;
						c1=0;
					}

					while(interval1 >100)
						interval1-=300;

					flag1=true;
					break;
				case 2:
					add1=1000;

					n2 = r.nextInt(5);
					interval2 = r.nextInt(1500) + add1;

					fall = new Fall();
					fall.start(2);

					Thread.sleep(interval2);

					if(c2==4 && time2>5)
					{
						time2--;
						c2=0;
					}

					while(interval2 >100)
					{
						interval2-=300;
						add1-=200;
					}

					flag2=true;
					break;
				case 3:
					add2 = 275;
					interval3 = r.nextInt(200)+add2;

					n3 = r.nextInt(5);

					fall = new Fall();
					fall.start(3);

					Thread.sleep(325);

					flag3=true;
					break;
				case 4:
					n4 = r.nextInt(3) + 1;
					color1 = r.nextInt(2);

					fall = new Fall();
					fall.start(4);

					Thread.sleep(500);

					flag4=true;
					break;
				case 5:
					n5 = r.nextInt(3) + 1;
					color2 = r.nextInt(2);

					fall = new Fall();
					fall.start(5);

					Thread.sleep(500);

					flag5=true;
					break;
			}
		}catch(Exception e){e.printStackTrace();}
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		int move = 150;

		if(key == KeyEvent.VK_LEFT && gameOver==false)
		{
			while(hold==false)
			{
				x -= move;
				player4.setVisible(false);
				player3.setVisible(false);
				player2.setVisible(false);
				player1.setVisible(true);

				if(x<0)
				{
					x+= move;
					player1.setBounds(x, 444, 254, 254);
					player2.setBounds(x, 444, 254, 254);
					player3.setBounds(x, 444, 254, 254);
					player4.setBounds(x, 444, 254, 254);
				}

				else
				{
					player1.setBounds(x, 444, 254, 254);
					player2.setBounds(x, 444, 254, 254);
					player3.setBounds(x, 444, 254, 254);
					player4.setBounds(x, 444, 254, 254);
				}
				hold = true;
			}
		}

		if(key == KeyEvent.VK_RIGHT && gameOver==false)
		{
			while(hold==false)
			{
				x += move;
				player4.setVisible(false);
				player3.setVisible(false);
				player2.setVisible(false);
				player1.setVisible(true);

				if(x>750)
				{
					x-= move;
					player1.setBounds(x, 444, 254, 254);
					player2.setBounds(x, 444, 254, 254);
					player3.setBounds(x, 444, 254, 254);
					player4.setBounds(x, 444, 254, 254);
				}

				else
				{
					player1.setBounds(x, 444, 254, 254);
					player2.setBounds(x, 444, 254, 254);
					player3.setBounds(x, 444, 254, 254);
					player4.setBounds(x, 444, 254, 254);
				}
				hold=true;
			}

		}

		if(key == KeyEvent.VK_SPACE && gameOver==false)
		{
			while(hold==false)
			{
				player1.setVisible(false);
				player3.setVisible(false);
				player4.setVisible(false);
				player2.setVisible(true);

				uc = new UmbrellaClose();
				uc.start();
				hold=true;
			}
		}

		if(key == KeyEvent.VK_ESCAPE)
		{
			gameOver=true;
			over=true;
		}

		if(key == KeyEvent.VK_ENTER)
		{
			if(gameOver==true)
				again=true;
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
					player2.setVisible(false);
					player1.setVisible(true);
					running=true;
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}while(running==false);
		}
	}

	class Fall extends Thread
	{
		public void start(int number)
		{
			switch (number){
				case 1:
					while(flag1==true && gameOver==false)
					{
						try
						{
							balloon[0].setVisible(true);

							balloon[0].setBounds(position[n1], b1, 61, 90);
							balloon[3].setBounds(position[n1], b1, 61, 90);

							Thread.sleep(time1);
							b1+=3;
							
							balloon[0].setBounds(position[n1], b1, 61, 90);
							balloon[3].setBounds(position[n1], b1, 61, 90);

							Collision collide = new Collision();
							collide.start(1);

							if(b1>815)
							{
								flag1=false;
								b1=0;
								c1++;
								y++;
								score+=1;
								scoreTemp=Integer.toString(score);
								scoreLabel.setText(scoreTemp);

								if(score>highScore)
								{
									highScore=score;
									highScoreLabel.setText(scoreTemp=Integer.toString(highScore));
								}

								dodgedBalloons+=1;

								if(dodgedBalloons<=5)
								{
									dodgedBalloonsTemp=Integer.toString(dodgedBalloons);
									dodgedBalloonsLabel.setText(dodgedBalloonsTemp);
								}

								if(dodgedBalloons==4)
								{
									dodgedBalloonsLabel.setForeground(Color.ORANGE);
									dodgedBalloonsTemp=Integer.toString(dodgedBalloons);
									dodgedBalloonsLabel.setText(dodgedBalloonsTemp);
								}

								if(dodgedBalloons==5)
								{
									dodgedBalloonsLabel.setForeground(Color.RED);
									dodgedBalloonsTemp=Integer.toString(dodgedBalloons);
									dodgedBalloonsLabel.setText(dodgedBalloonsTemp);
								}

								if(dodgedBalloons>5)
								{
									gameOver=true;
									sound.stop();
									soundcry.start();

									player1.setVisible(false);
									player2.setVisible(false);
									player3.setVisible(false);
									player4.setVisible(true);

									gameOverPanel.setVisible(true);
									choicePanel.setVisible(true);

									flag1=false;

									score-=1;
									scoreTemp=Integer.toString(score);
									scoreLabel.setText(scoreTemp);
								}
							}
						}catch(Exception e){e.printStackTrace();}
					}
					break;
				case 2:
					while(flag2==true && gameOver==false)
					{
						try
						{
							balloon[1].setVisible(true);

							balloon[1].setBounds(position[n2], b2, 61, 90);
							balloon[4].setBounds(position[n2], b2, 61, 90);

							Thread.sleep(time2);
							b2+=3;

							balloon[1].setBounds(position[n2], b2, 61, 90);
							balloon[4].setBounds(position[n2], b2, 61, 90);

							Collision collide = new Collision();
							collide.start(2);

							if(b2>815)
							{
								flag2=false;
								b2=0;
								c2++;
								score += 1;
								scoreTemp=Integer.toString(score);
								scoreLabel.setText(scoreTemp);

								if(score>highScore)
								{
									highScore=score;
									highScoreLabel.setText(scoreTemp=Integer.toString(highScore));
								}

								dodgedBalloons+=1;

								if(dodgedBalloons<=5)
								{
									dodgedBalloonsTemp=Integer.toString(dodgedBalloons);
									dodgedBalloonsLabel.setText(dodgedBalloonsTemp);
								}

								if(dodgedBalloons==4)
								{
									dodgedBalloonsLabel.setForeground(Color.ORANGE);
									dodgedBalloonsTemp=Integer.toString(dodgedBalloons);
									dodgedBalloonsLabel.setText(dodgedBalloonsTemp);
								}

								if(dodgedBalloons==5)
								{
									dodgedBalloonsLabel.setForeground(Color.RED);
									dodgedBalloonsTemp=Integer.toString(dodgedBalloons);
									dodgedBalloonsLabel.setText(dodgedBalloonsTemp);
								}

								if(dodgedBalloons>5)
								{
									gameOver=true;
									sound.stop();
									soundcry.start();

									player1.setVisible(false);
									player2.setVisible(false);
									player3.setVisible(false);
									player4.setVisible(true);

									gameOverPanel.setVisible(true);
									choicePanel.setVisible(true);

									flag1=false;

									score -= 1;
									scoreTemp=Integer.toString(score);
									scoreLabel.setText(scoreTemp);
								}
							}
						}catch(Exception e){e.printStackTrace();}
					}

					break;
				case 3:
					while(flag3==true && gameOver==false)
					{
							try
							{
								balloon[2].setVisible(true);

								balloon[2].setBounds(position[n3], b3, 61, 90);
								balloon[5].setBounds(position[n3], b3, 61, 90);

								Thread.sleep(5);
								b3+=3;

								balloon[2].setBounds(position[n3], b3, 61, 90);
								balloon[5].setBounds(position[n3], b3, 61, 90);

								Collision collide = new Collision();
								collide.start(3);

								if(b3>815)
								{
									flag3=false;
									b3=0;
									score -= 5;

									if(score > 0)
									{	
										scoreTemp=Integer.toString(score);
										scoreLabel.setText(scoreTemp);
									}
									else if(score <= 0)
									{
										score = 0;
										scoreTemp=Integer.toString(score);
										scoreLabel.setText(scoreTemp);
									}

									if(score>highScore)
									{
										highScore=score;
										highScoreLabel.setText(scoreTemp=Integer.toString(highScore));
									}

									dodgedBalloons+=1;

									if(dodgedBalloons<=5)
									{
										dodgedBalloonsTemp=Integer.toString(dodgedBalloons);
										dodgedBalloonsLabel.setText(dodgedBalloonsTemp);
									}

									if(dodgedBalloons==4)
									{
										dodgedBalloonsLabel.setForeground(Color.ORANGE);
										dodgedBalloonsTemp=Integer.toString(dodgedBalloons);
										dodgedBalloonsLabel.setText(dodgedBalloonsTemp);
									}

									if(dodgedBalloons==5)
									{
										dodgedBalloonsLabel.setForeground(Color.RED);
										dodgedBalloonsTemp=Integer.toString(dodgedBalloons);
										dodgedBalloonsLabel.setText(dodgedBalloonsTemp);
									}

									if(dodgedBalloons>5)
									{
										gameOver=true;
										sound.stop();
										soundcry.start();

										player1.setVisible(false);
										player2.setVisible(false);
										player3.setVisible(false);
										player4.setVisible(true);

										gameOverPanel.setVisible(true);
										choicePanel.setVisible(true);

										flag1=false;

										score += 5;
										if(score > 0)
										{	
											scoreTemp=Integer.toString(score);
											scoreLabel.setText(scoreTemp);
										}
										else if(score <= 0)
										{
											score = 0;
											scoreTemp=Integer.toString(score);
											scoreLabel.setText(scoreTemp);
										}
									}
								}
							}catch(Exception e){e.printStackTrace();}
					}
					break;
				case 4:
					while(flag4==true && gameOver==false)
					{
					try
					{
						balloonSet1[color1].setVisible(true);

						balloonSet1[color1].setBounds(position[n4], b4, 61, 90);
						balloonSet1[color1+3].setBounds(position[n4], b4, 61, 90);

						Thread.sleep(4);
						b4+=3;

						balloonSet1[color1].setBounds(position[n4], b4, 61, 90);
						balloonSet1[color1+3].setBounds(position[n4], b4, 61, 90);

						Collision collide = new Collision();
						collide.start(4);

						if(b4>815)
						{
							flag4=false;
							b4=0;

							if(color1==2)
								score -= 5;

							else score += 1;

							if(score > 0)
							{	
								scoreTemp=Integer.toString(score);
								scoreLabel.setText(scoreTemp);
							}
							else if(score <= 0)
							{
								score = 0;
								scoreTemp=Integer.toString(score);
								scoreLabel.setText(scoreTemp);
							}

							if(score>highScore)
							{
								highScore=score;
								highScoreLabel.setText(scoreTemp=Integer.toString(highScore));
							}

							dodgedBalloons+=1;

							if(dodgedBalloons<=5)
							{
								dodgedBalloonsTemp=Integer.toString(dodgedBalloons);
								dodgedBalloonsLabel.setText(dodgedBalloonsTemp);
							}

							if(dodgedBalloons==4)
							{
								dodgedBalloonsLabel.setForeground(Color.ORANGE);
								dodgedBalloonsTemp=Integer.toString(dodgedBalloons);
								dodgedBalloonsLabel.setText(dodgedBalloonsTemp);
							}

							if(dodgedBalloons==5)
							{
								dodgedBalloonsLabel.setForeground(Color.RED);
								dodgedBalloonsTemp=Integer.toString(dodgedBalloons);
								dodgedBalloonsLabel.setText(dodgedBalloonsTemp);
							}

							if(dodgedBalloons>5)
							{
								gameOver=true;
								sound.stop();
								soundcry.start();

								player1.setVisible(false);
								player2.setVisible(false);
								player3.setVisible(false);
								player4.setVisible(true);

								gameOverPanel.setVisible(true);
								choicePanel.setVisible(true);

								flag1=false;

								if(color1==2) score += 5;
								else score -= 1;

								if(score > 0)
								{	
									scoreTemp=Integer.toString(score);
									scoreLabel.setText(scoreTemp);
								}
								else if(score <= 0)
								{
									score = 0;
									scoreTemp=Integer.toString(score);
									scoreLabel.setText(scoreTemp);
								}
							}
						}
					}catch(Exception e){e.printStackTrace();}
					}
					break;
				case 5:
					while(flag5==true && gameOver==false)
					{
						try
						{
							balloonSet2[color2].setVisible(true);

							balloonSet2[color2].setBounds(position[n5], b5, 61, 90);
							balloonSet2[color2+3].setBounds(position[n5], b5, 61, 90);

							Thread.sleep(4);
							b5+=3;

							balloonSet2[color2].setBounds(position[n5], b5, 61, 90);
							balloonSet2[color2+3].setBounds(position[n5], b5, 61, 90);

							Collision collide = new Collision();
							collide.start(5);

							if(b5>815)
							{
								flag5=false;
								b5=0;

								if(color2==2)
									score -= 5;

								else score += 1;

								if(score > 0)
								{	
									scoreTemp=Integer.toString(score);
									scoreLabel.setText(scoreTemp);
								}
								else if(score <= 0)
								{
									score = 0;
									scoreTemp=Integer.toString(score);
									scoreLabel.setText(scoreTemp);
								}

								if(score>highScore)
								{
									highScore=score;
									highScoreLabel.setText(scoreTemp=Integer.toString(highScore));
								}

								dodgedBalloons+=1;

								if(dodgedBalloons<=5)
								{
									dodgedBalloonsTemp=Integer.toString(dodgedBalloons);
									dodgedBalloonsLabel.setText(dodgedBalloonsTemp);
								}

								if(dodgedBalloons==4)
								{
									dodgedBalloonsLabel.setForeground(Color.ORANGE);
									dodgedBalloonsTemp=Integer.toString(dodgedBalloons);
									dodgedBalloonsLabel.setText(dodgedBalloonsTemp);
								}

								if(dodgedBalloons==5)
								{
									dodgedBalloonsLabel.setForeground(Color.RED);
									dodgedBalloonsTemp=Integer.toString(dodgedBalloons);
									dodgedBalloonsLabel.setText(dodgedBalloonsTemp);
								}

								if(dodgedBalloons>5)
								{
									gameOver=true;
									sound.stop();
									soundcry.start();

									player1.setVisible(false);
									player2.setVisible(false);
									player3.setVisible(false);
									player4.setVisible(true);

									gameOverPanel.setVisible(true);
									choicePanel.setVisible(true);

									flag1=false;

									if(color2==2) score += 5;
									else score -= 1;

									if(score > 0)
									{	
										scoreTemp=Integer.toString(score);
										scoreLabel.setText(scoreTemp);
									}
									else if(score <= 0)
									{
										score = 0;
										scoreTemp=Integer.toString(score);
										scoreLabel.setText(scoreTemp);
									}
								}
							}
						}catch(Exception e){e.printStackTrace();}
					}
					break;
			}
		}

	}

	class Collision extends Thread
	{

		//@Override
		Rectangle wBalloon;
		public void start(int number)
		{
			switch (number) {
				case 1:
					wBalloon = new Rectangle(position[n1], b1, 61, 90);
					break;
				case 2:
					wBalloon = new Rectangle(position[n2], b2, 61, 90);
					break;
				case 3:
					wBalloon = new Rectangle(position[n3], b3, 61, 90);
					break;
				case 4:
					wBalloon = new Rectangle(position[n4], b4, 61, 90);
					break;
				case 5:
					wBalloon = new Rectangle(position[n5], b5, 61, 90);
					break;
			}

			Rectangle open = new Rectangle(x+50, 444, 179, 254);
			Rectangle close = new Rectangle(x+50, 520, 179, 184);
			boolean popped = false;

			if(wBalloon.intersects(close) && player1.isVisible())
			{	
				gameOver=true;

				switch (number) {
				case 1:
					balloon[3].setBounds(position[n1], 531, 61, 90);
					balloon[0].setVisible(false);
					balloon[3].setVisible(true);
					flag1=false;
					break;
				case 2:
					balloon[4].setBounds(position[n2], 531, 61, 90);
					balloon[1].setVisible(false);
					balloon[4].setVisible(true);
					flag2=false;
					break;
				case 3:
					balloon[5].setBounds(position[n3], 531, 61, 90);
					balloon[2].setVisible(false);
					balloon[5].setVisible(true);
					flag3=false;
					break;
				case 4:
					balloonSet1[color1+3].setBounds(position[n4], 531, 61, 90);
					balloonSet1[color1].setVisible(false);
					balloonSet1[color1+3].setVisible(true);
					flag4=false;
					break;
				case 5:
					balloonSet2[color1+3].setBounds(position[n5], 531, 61, 90);
					balloonSet2[color1].setVisible(false);
					balloonSet2[color1+3].setVisible(true);
					flag5=false;
					break;
				}

				sound.stop();
				soundpop.start();
				soundcry.start();

				player1.setVisible(false);
				player3.setVisible(true);

				gameOverPanel.setVisible(true);
				choicePanel.setVisible(true);	
			}

			else if(wBalloon.intersects(open) && player2.isVisible())
			{
				switch (number) {
					case 1:
						balloon[0].setVisible(false);
						balloon[3].setVisible(true);

						while(flag1==true)
						{
							score+=10;
							scoreTemp=Integer.toString(score);
							scoreLabel.setText(scoreTemp);

							if(score>highScore)
							{
								highScore=score;
								highScoreLabel.setText(scoreTemp=Integer.toString(highScore));
							}

							flag1=false;
						}

						c1++;
						y++;
						break;
					case 2:
						balloon[1].setVisible(false);
						balloon[4].setVisible(true);

						while(flag2==true)
						{
							score+=30;
							scoreTemp=Integer.toString(score);
							scoreLabel.setText(scoreTemp);

							if(score>highScore)
							{
								highScore=score;
								highScoreLabel.setText(scoreTemp=Integer.toString(highScore));
							}

							flag2=false;
						}

						c2++;
						break;
					case 3:
						balloon[2].setVisible(false);
						balloon[5].setVisible(true);

						while(flag3==true)
						{
							score+=20;
							scoreTemp=Integer.toString(score);
							scoreLabel.setText(scoreTemp);

							if(score>highScore)
							{
								highScore=score;
								highScoreLabel.setText(scoreTemp=Integer.toString(highScore));
							}

							flag3=false;
						}
						break;
					case 4:
						balloonSet1[color1].setVisible(false);
						balloonSet1[color1+3].setVisible(true);

						while(flag4==true)
						{
							if(color1==0) score+=10;
							if(color1==1) score+=30;
							if(color1==2) score+=20;
							scoreTemp=Integer.toString(score);
							scoreLabel.setText(scoreTemp);

							if(score>highScore)
							{
								highScore=score;
								highScoreLabel.setText(scoreTemp=Integer.toString(highScore));
							}

							flag4=false;
						}
						break;
					case 5:
						balloonSet2[color2].setVisible(false);
						balloonSet2[color2+3].setVisible(true);

						while(flag5==true)
						{
							if(color2==0) score+=10;
							if(color2==1) score+=30;
							if(color2==2) score+=20;
							scoreTemp=Integer.toString(score);
							scoreLabel.setText(scoreTemp);

							if(score>highScore)
							{
								highScore=score;
								highScoreLabel.setText(scoreTemp=Integer.toString(highScore));
							}

							flag5=false;
						}
						break;
				}

				do
				{
					try
					{
						soundpop.start();
						Thread.sleep(250);
						switch (number) {
							case 1:
								balloon[3].setVisible(false);
								break;
							case 2:
								balloon[4].setVisible(false);
								break;
							case 3:
								balloon[5].setVisible(false);
								break;
							case 4:
								balloonSet1[color1+3].setVisible(false);
								break;
							case 5:
								balloonSet2[color2+3].setVisible(false);
								break;
						}
						soundpop.pause();
						switch (number) {
							case 1:
								b1=0;
								break;
							case 2:
								b2=0;
								break;
							case 3:
								b3=0;
								break;
							case 4:
								b4=0;
								break;
							case 5:
								b5=0;
								break;
						}
						popped=true;
					}catch(Exception ex){
						ex.printStackTrace();}
				}while(popped==false);
			}
		}
	}
}