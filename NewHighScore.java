import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class NewHighScore
{
	
	private String filename = "High Score.txt";
	private File file;
	private FileWriter fileWriter;
	private BufferedWriter bufferedWriter;
	public int highScore;

	public NewHighScore(int score)
	{
		try {
			file = new File ( filename );
			fileWriter = new FileWriter(file);
			bufferedWriter = new BufferedWriter(fileWriter);
			
			bufferedWriter.write(Integer.toString(score));
			
			bufferedWriter.close();
			fileWriter.close();
		}catch(IOException ioException) {
			ioException.printStackTrace();
		}
	
	}
}