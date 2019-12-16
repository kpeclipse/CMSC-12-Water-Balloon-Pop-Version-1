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

public class WaterBalloonPop extends JFrame implements MouseListener
{
	private JButton play;
	protected MajorPanel mainClass;
	private GamePanel gameProper;
	private ImageIcon play1, icon2;
	
	public static void main(String[] args)
	{
		new WaterBalloonPop();
	}
	

	public WaterBalloonPop()
	{
		super("WATER BALLOON POP! (Version 2)");
		setSize(1200,725);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);

		mainClass = new MajorPanel();
		mainClass.enabledMusic = true;
		
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
				//remove(mainClass);
			//	if(mainClass.heh() == false)
			//		mainClass.sound.stop();
				mainClass.sound.stop();	
				if(mainClass.enabledMusic == true)
					gameProper = new GamePanel(true);
				else if(mainClass.enabledMusic == false)
					gameProper = new GamePanel(false);
					//System.out.println(gameProper.enabledMusic);
				/*if(mainClass.sfx.getIcon()==mainClass.sfx1) gameProper.enabledSfx=true;
				if(mainClass.sfx.getIcon()==mainClass.sfx3) gameProper.enabledSfx=false;*/
			}
		});

		mainClass.menuPanelForPlay.add(play);

		add(mainClass);

		repaint();
		setVisible(true);
	}

	public void hey(){
		mainClass.enabledMusic = false;
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		if(e.getComponent() == play)
		{
			icon2 = new ImageIcon("play2.png");
			play.setIcon(icon2);
		}
	}

	public void mouseExited(MouseEvent exited)
	{
		play.setIcon(play1);
	}

	public void mouseReleased(MouseEvent e){}
	public void mouseClicked(MouseEvent e){}
	public void mousePressed(MouseEvent e) {}
}