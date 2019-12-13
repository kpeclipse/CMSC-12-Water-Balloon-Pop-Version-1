import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class GameImagePanel extends JPanel
{
	private BufferedImage image;

	public GameImagePanel(BufferedImage image)
	{
		this.image = image;
		setSize(image.getWidth(), image.getHeight());
		repaint();
	}

	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Dimension d = getSize();

		g.drawImage(image, 0, 0, this);
	}
}