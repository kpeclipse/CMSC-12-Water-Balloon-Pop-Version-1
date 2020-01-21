public class Villain{
	private int villainX;

	public int getVillainX(int balloonX, int playerX){	
		int first = 70;
		int second = 245;
		int third = 420;
		int fourth = 595;

		System.out.print("What will the Villain consider: ");
		if(balloonX == 146)
		{
			switch(playerX)
			{
				case 50:
					System.out.println("A1");
					villainX = third;
					break;
				case 225:
					System.out.println("A2");
					villainX = fourth;
					break;
				case 400:
					System.out.println("A3");
					villainX = third;
					break;
				case 575:
					System.out.println("A4");
					villainX = first;
					break;
			}
		}

		else if(balloonX == 321)
		{
			switch(playerX)
			{
				case 50:
					System.out.println("B1");
					villainX = fourth;
					break;
				case 225:
					System.out.println("B2");
					villainX = fourth;
					break;
				case 400:
					System.out.println("B3");
					villainX = fourth;
					break;
				case 575:
					System.out.println("B4");
					villainX = first;
					break;
			}	
		}

		else if(balloonX == 496)
		{
			switch(playerX)
			{
				case 50:
					System.out.println("C1");
					villainX = fourth;
					break;
				case 225:
					System.out.println("C2");
					villainX = first;
					break;
				case 400:
					System.out.println("C3");
					villainX = first;
					break;
				case 575:
					System.out.println("C4");
					villainX = first;
					break;
			}
		}
			
		else if(balloonX == 671)
		{
			switch(playerX)
			{
				case 50:
					System.out.println("D1");
					villainX = fourth;
					break;
				case 225:
					System.out.println("D2");
					villainX = second;
					break;
				case 400:
					System.out.println("D3");
					villainX = first;
					break;
				case 575:
					System.out.println("D4");
					villainX = first;
					break;
			}
		}
		
		return villainX;
	}

	public int getBalloonX(int balloonX, int playerX){
		int first = 146;
		int second = 321;
		int third = 496;
		int fourth = 671;

		if(balloonX == 146)
		{
			switch(playerX)
			{
				case 50:
					balloonX = fourth;
					break;
				case 225:
					balloonX = fourth;
					break;
				case 400:
					balloonX = third;
					break;
				case 575:
					balloonX = first;
					break;
			}
		}

		else if(balloonX == 321)
		{
			switch(playerX)
			{
				case 50:
					balloonX = fourth;
					break;
				case 225:
					balloonX = fourth;
					break;
				case 400:
					balloonX = fourth;
					break;
				case 575:
					balloonX = first;
					break;
			}	
		}

		else if(balloonX == 496)
		{
			switch(playerX)
			{
				case 50:
					balloonX = fourth;
					break;
				case 225:
					balloonX = first;
					break;
				case 400:
					balloonX = first;
					break;
				case 575:
					balloonX = first;
					break;
			}
		}
			
		else if(balloonX == 671)
		{
			switch(playerX)
			{
				case 50:
					balloonX = fourth;
					break;
				case 225:
					balloonX = second;
					break;
				case 400:
					balloonX = first;
					break;
				case 575:
					balloonX = first;
					break;
			}
		}
		
		return balloonX;
	}
}