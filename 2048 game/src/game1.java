
import java.util.Scanner;
import java.util.Random;

public class game1 {


	static int score = 0;
	public static void main(String[] args) {
		int input;
		Scanner in = new Scanner(System.in);
		boolean validInput = false;
		boolean won=false;
		boolean winner = false;
		do {
			System.out.println("Enter a grid number between 4 to 8"); //asking the user to enter the grid how big they wanna play
			input=in.nextInt();
		   if (input>=4 && input<=8) {
			   System.out.println("The entered number between 4-8 is valid so grid will be" + input + "*" + input); //validating the enter values
			   validInput=true;
		   }
		   else {
			   System.out.println("Enter next number");
		   }
	   }
	   while (validInput==false);

	   int[][] grid =new int[input][input];
	   addOne(grid);
	   emptyGrid(grid);
	   displayGrid(grid);
	   do {
		   moveGrid(grid);
		   emptyGrid(grid);
		   displayGrid(grid);
		   score(0);
		   won = winner(grid, won);
		   won = emptyGrid (grid, won);
	   } while (won==false) ;

	}

	public static void displayGrid(int[][] grid) {
 		for (int j=0; j<grid[0].length;j++) {
 			System.out.print( "+------" );  // for upper border of grid
 		}
 		System.out.print("+");
		for(int i = 0; i<grid.length;i++) {
     		System.out.print("\n" + "|" );
     		for (int j=0; j<grid[i].length;j++) {
     			gridspace(grid[i][j]);
			}
			System.out.println();

     		for (int j=0; j<grid[i].length;j++) {
     			System.out.print( "+------" ); //to divide the one column from other
     		}
     		System.out.print("+"); //to complete the grid
		}


	}
	public static void gridspace(int gridValue) { //filling grid with space
		if (gridValue==0) {
			System.out.print( "   " + "   |"); //increasing the grid according the length of the number

		}
		else if (gridValue<10)
		{
			System.out.print( "  " + gridValue + "   " + "|");
		}
		else if (gridValue<100)
		{
			System.out.print( "  " + gridValue + "  " + "|");
		}
		else if (gridValue<1000)
		{
			System.out.print( "   " + gridValue + " " + "|");
		}
		else {
			System.out.println(" "+ gridValue + " " + "|" );
		}
	}


	public static void addOne(int[][] grid)  {
		int i,j;
		Random rnd = new Random();    //adds random one in the empty grid
		i = rnd.nextInt(grid.length);
		j = rnd.nextInt(grid[0].length);
		grid[i][j]=1;

	}

	public static void emptyGrid(int[][] grid) {  //creating array for empty space in the grid
	    int counter=0;
		int[][] emptyGrid=new int[(grid.length* grid[0].length)][2];
		for(int i=0;i<grid.length;i++) {
			for(int j=0;j<grid[i].length;j++) {
				if (grid[i][j]==0) {
					emptyGrid[counter][0]=i;
					emptyGrid[counter][1]=j;
					counter++;

				}

			}
		}

		Random rnd = new Random();

		int randomCell=rnd.nextInt(counter);
		int i = emptyGrid[randomCell][0];
		int j = emptyGrid[randomCell][1];
		System.out.println(i + " " + j);
		grid[i][j]=1;  //add another random one in the available free sapce in the grid
	}

	public static void score(int sco) {   //score method
 		score = score + sco;
 		System.out.println();
 		System.out.print("Your Score: " + score);
 		System.out.println();
	}

	public static void moveGrid(int[][] grid) { //moves grid according to the specefic letter
		char moveValue;
		System.out.print("\n Enter valid Move which is 'd' for right, 'a' for left, 's' for down and 'w' for up");
		Scanner in = new Scanner(System.in);
		moveValue = in.next().charAt(0);
		if(moveValue == 'w'|| moveValue=='s' || moveValue=='d' || moveValue=='a') {
			if (moveValue=='d') {  //moves right if pressed d
			    int sco = 0;
			    boolean blocked = false;
			    boolean addRandom = false;
				for(int i=0; i<grid.length; i++) {
					for(int j=0; j<grid.length; j++) {

						try {
								if(grid[i][j]==grid[i][j+1]) {  //move and if number matches add the value in score
									grid[i][j+1]=grid[i][j]+grid[i][j+1];
									grid[i][j] = 0;
									sco = sco + grid[i][j]+grid[i][j+1];
									if(addRandom == false) {
									addRandom = true;
								}
								}
								if(grid[i][j+1]==0 && grid[i][j]>0) {
									grid[i][j+1] = grid[i][j];
									grid[i][j] = 0;
									if(addRandom == false) {
									addRandom = true;
								}
								}
								else if(grid[i][j+1]>0 && grid[i][j]>0) {
									blocked = true;
									if(addRandom == false) {
									addRandom = true;
								}
								}

						}catch(Exception e) {						}
					}
				}
				if (blocked == true){      //move the value if the grid is empty
				for(int i=0; i<grid.length; i++) {
					for(int j=0; j<grid.length; j++) {

						try {
								if(grid[i][j+1]==0 && grid[i][j]>0) {
									grid[i][j+1] = grid[i][j];
									grid[i][j] = 0;
								}


						}catch(Exception e) {
						}
					}
				}
				}
             if(addRandom == true) {
					emptyGrid(grid);
				}

				displayGrid(grid);
                score(sco);
				moveGrid(grid);
				displayGrid(grid);
			}

			else if (moveValue=='a') {  //move towards left
				boolean addRandom = false;
				boolean blocked = false;
				int sco = 0;
				for(int i=grid.length-1; i>=0; i--) {
					for(int j=grid.length-1;j>=0; j--) {
						try {
							if(grid[i][j]==grid[i][j+1]) { //move and if number matches add the value in score
								grid[i][j]=grid[i][j]+grid[i][j+1];
								grid[i][j+1] = 0;
								sco = sco +grid[i][j]+grid[i][j+1];
								if(addRandom == false) {
									addRandom = true;
								}
							}
							if(grid[i][j]==0 && grid[i][j+1]>0) {
								grid[i][j] = grid[i][j]+grid[i][j+1];
								grid[i][j+1] = 0;
								if(addRandom == false) {
									addRandom = true;
								}
							}
							else if(grid[i][j+1]>0 && grid[i][j]>0) {
									blocked = true;
								}
						}catch(Exception e) {
						}

					}
				}

				if (blocked == true){
				for(int i=grid.length-1; i>=0; i--) {
					for(int j=grid.length-1;j>=0; j--) {

						try {
								if(grid[i][j]==0 && grid[i][j+1]>0) {
								grid[i][j] = grid[i][j]+grid[i][j+1];
								grid[i][j+1] = 0;

				}

						}catch(Exception e) {
						}
					}
				}
				}
				if(addRandom == true) {
					emptyGrid(grid);
				}

				displayGrid(grid);
                score(sco);
				moveGrid(grid);
				displayGrid(grid);
			}
			else if (moveValue=='w') { //moves right if pressed w
				int readingVal=0;
				boolean addRandom = false;
				boolean blocked = false;
				int sco = 0;
				int[] movingGrids=new int[(grid.length)];
				for(int i=grid.length-1; i>=0; i--) {
					for(int j=grid.length-1;j>=0; j--) {
						try {
							if(grid[i][j]== grid[i-1][j]) {
								grid[i-1][j]= grid[i-1][j] + grid[i][j];  //adding the score if the number matches
								grid[i][j] = 0;
								sco = sco + grid[i-1][j] + grid[i][j];
								if(addRandom == false) {
									addRandom = true;
								}
							}
							if(grid[i-1][j]==0 && grid[i][j]>0) {
								grid[i-1][j] = grid[i][j];
								grid[i][j] = 0;
								if(addRandom == false) {
									addRandom = true;
								}
							}
							else if(grid[i-1][j]>0 && grid[i][j]>0) {
									blocked = true;
								}

						}catch(Exception e) {
						}

					}
				}
				if (blocked == true){
				for(int i=grid.length-1; i>=0; i--) {
					for(int j=grid.length-1;j>=0; j--) {

						try {
				if(grid[i-1][j]==0 && grid[i][j]>0) {
								grid[i-1][j] = grid[i][j];
								grid[i][j] = 0;
							}

						}catch(Exception e) {
						}
					}
				}
				}
             if(addRandom == true) {
					emptyGrid(grid);
				}
				displayGrid(grid);
             score(sco);
				moveGrid(grid);
				displayGrid(grid);
			}
			else if (moveValue=='s') { //moves down if pressed s
				int readingVal=0;
				int sco = 0;
				boolean addRandom = false;
				boolean blocked = false;
				int[] movingGrid=new int[(grid.length)];
				for(int i=0; i<grid.length; i++) {
					for(int rightRow=0;rightRow<grid.length;rightRow++) {
						try {
							if(grid[i-1][rightRow] == grid[i][rightRow]) {   //adding the score if the number matches
								grid[i][rightRow] =  grid[i-1][rightRow] +grid[i][rightRow];
								grid[i-1][rightRow] = 0;
								sco = sco + grid[i-1][rightRow] +grid[i][rightRow];
								if(addRandom == false) {
									addRandom = true;
								}
							}
							if(grid[i][rightRow]==0 && grid[i-1][rightRow]>0) {
								grid[i][rightRow] = grid[i-1][rightRow];
								grid[i-1][rightRow] = 0;
								if(addRandom == false) {
									addRandom = true;
								}
							}
							else if(grid[i][rightRow]>0 && grid[i-1][rightRow]>0) {
									blocked = true;
								}
						}catch(Exception e) {
						}

						readingVal = readingVal+1;


					}

				}
				if (blocked == true){
				for(int i=0; i<grid.length; i++) {
					for(int rightRow=0;rightRow<grid.length;rightRow++) {

						try {



				if(grid[i][rightRow]==0 && grid[i-1][rightRow]>0) {
								grid[i][rightRow] = grid[i-1][rightRow];
								grid[i-1][rightRow] = 0;
							}

						}catch(Exception e) {
						}
					}
				}
				}

				if(addRandom == true) {
					emptyGrid(grid);
				}
				displayGrid(grid);
                score(sco);
				moveGrid(grid);
				displayGrid(grid);
				displayGrid(grid);

			}
				}
		else {
			System.out.println("\n The letter you pressed is invalid so try another letter");
			moveGrid(grid);
		}


	}
	public static boolean winner(int[][] grid, boolean won) { //if winner gets the score 1024, he wins
		won = false;
		for (int i=0; i<grid.length; i++) {
			for (int j=0; j<grid.length; j++) {
			if (grid[i][j]==1024) {
				System.out.println("You won the game");
				won=true;
			}
			}
		}
		char choose;
		Scanner in = new Scanner(System.in);
		if (won) {
			System.out.println("You won the game if you want to continue select y or n");
			choose = in.next().charAt(0);
			if (choose=='y') {
			 won=false;

			}
			else if (choose=='n' ) {
				won=true;
			}
			else {
				do {
					System.out.println("Invalid input try again");
				choose = in.next().charAt(0);
				if (choose=='y') {
				 won=false;

				}
				else if (choose=='n' ) {
					won=true;
				}
			}while (choose!='y'&& choose!='n');
			}
			return won;
		}
		return won;
	}
	public static boolean emptyGrid(int[][] grid, boolean won) {
		int counter=0;     //no empty tile is game over
		boolean emptyTile=false;
		int[][] emptyGrid=new int[(grid.length* grid[0].length)][2];
		for(int i=0;i<grid.length;i++) {
			for(int j=0;j<grid[i].length;j++) {
				if (grid[i][j]==0) {
					emptyGrid[counter][0]=i;
					emptyGrid[counter][1]=j;

					counter++;

				}

			}
		}



		if (emptyTile=true) {
			System.out.println("\n Game Over");
		}
		return emptyTile;
	}
}

