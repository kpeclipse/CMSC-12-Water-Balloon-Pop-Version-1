import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class HighScore
{
	
	private String filename = "High Score.txt";
	public String line;
	private File file;
	private FileReader fileReader;
	private BufferedReader bufferedReader;
	public int num=0;

	public HighScore()
	{
		try {
			file = new File(filename);
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			
			while( (line = bufferedReader.readLine()) != null)
			{
				num=Integer.valueOf(line);
			}
		}catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
}