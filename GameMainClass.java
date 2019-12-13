import java.io.*;
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

public class GameMainClass extends JFrame implements MouseListener
{
	private JButton play;
	private JButton music;
	private JButton sfx;
	private MajorPanel mainClass;
	private GamePanel gameProper;

	private ImageIcon play1, icon2;
	private ImageIcon music1, music2, music3, music4;
	private ImageIcon sfx1, sfx2, sfx3, sfx4;

	public static void main(String[] args)
	{
		new GameMainClass();
	}

	public GameMainClass()
	{
		super("WATER BALLOON POP! (Version 2)");
		setSize(1200,725);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);

		mainClass = new MajorPanel();
		
		play = new JButton();
		play1 = new ImageIcon("play1.png");
		play.setContentAreaFilled(false);
		play.setIcon(play1);
		play.setFocusPainted(false);
		play.setBorder(BorderFactory.createEmptyBorder());	
		play.addMouseListener(this);
		play.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				dispose();
				remove(mainClass);
				mainClass.sound.stop();	
				gameProper = new GamePanel();
			}
		});

		music = new JButton();
		music1 = new ImageIcon("music1.png");
		music3 = new ImageIcon("music3.png");
		music.setBounds(1000,600,80,80);
		music.setContentAreaFilled(false);
		music.setIcon(music1);
		music.setFocusPainted(false);
		music.setBorder(BorderFactory.createEmptyBorder());	
		music.addMouseListener(this);
		music.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				if(music.getIcon() == music2)
					music.setIcon(music3);
				if(music.getIcon() == music4)
					music.setIcon(music1);
			}
		});

		sfx = new JButton();
		sfx1 = new ImageIcon("sfx1.png");
		sfx3 = new ImageIcon("sfx3.png");
		sfx.setBounds(1090,600,80,80);
		sfx.setContentAreaFilled(false);
		sfx.setIcon(sfx1);
		sfx.setFocusPainted(false);
		sfx.setBorder(BorderFactory.createEmptyBorder());	
		sfx.addMouseListener(this);
		sfx.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				if(sfx.getIcon() == sfx2)
					sfx.setIcon(sfx3);
				if(sfx.getIcon() == sfx4)
					sfx.setIcon(sfx1);
			}
		});


		mainClass.mainPanel.add(music);
		mainClass.mainPanel.add(sfx);
		mainClass.menuPanelForPlay.add(play);

		add(mainClass);

		repaint();
		setVisible(true);
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		if(e.getComponent() == play)
		{
			icon2 = new ImageIcon("play2.png");
			play.setIcon(icon2);
		}

		if(e.getComponent() == music && music.getIcon() == music1)
		{
			music2 = new ImageIcon("music2.png");
			music.setIcon(music2);
		}

		if(e.getComponent() == music && music.getIcon() == music3)
		{
			music4 = new ImageIcon("music4.png");
			music.setIcon(music4);
		}

		if(e.getComponent() == sfx && sfx.getIcon() == sfx1)
		{
			sfx2 = new ImageIcon("sfx2.png");
			sfx.setIcon(sfx2);
		}

		if(e.getComponent() == sfx && sfx.getIcon() == sfx3)
		{
			sfx4 = new ImageIcon("sfx4.png");
			sfx.setIcon(sfx4);
		}
	}

	public void mouseExited(MouseEvent exited)
	{
		play.setIcon(play1);
		if(music.getIcon() == music2)
			music.setIcon(music1);
		if(music.getIcon() == music4)
			music.setIcon(music3);
		if(sfx.getIcon() == sfx2)
			sfx.setIcon(sfx1);
		if(sfx.getIcon() == sfx4)
			sfx.setIcon(sfx3);
	}

	public void mouseReleased(MouseEvent e){}
	public void mouseClicked(MouseEvent e){}
	public void mousePressed(MouseEvent e) {}
}