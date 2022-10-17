import java.util.*;

public class TicTacToe
{
	public enum Values 
	{
		X("X"), O("O"), EMPTY(" ");
		
		private String rep;
		
		private Values(String rep)
		{
			this.rep = rep;
		}
		
		public String getString()
		{
			return this.rep;
		}
	}
	
	private Values[][][] board = new Values[4][4][4];
	private String winner = "tie";
	
	public TicTacToe()
	{
		for(int i=0; i<4; i++)
			for(int j=0; j<4; j++)
				for(int k=0; k<4; k++)
					board[i][j][k] = Values.EMPTY;
	}
	
	public boolean verifyPlay(int quadro, int linha, int coluna)
	{
		if( board[quadro][linha][coluna] == Values.EMPTY  )
			return true;
		else
			return false;
	}
	
	public void makePlay()
	{
		Random rand = new Random();
	
		int quadro = rand.nextInt(4);
		int linha = rand.nextInt(4);
		int coluna = rand.nextInt(4);
		
		while ( !verifyPlay(quadro, linha, coluna) )
		{
			quadro = rand.nextInt(4);
			linha = rand.nextInt(4);
			coluna = rand.nextInt(4);
		}
		
		putX(quadro, linha, coluna);
	}
	
	public void takePlay()
	{
		Scanner input = new Scanner(System.in);
		
		System.out.print("Seu movimento: ");
		int quadro = input.nextInt();
		int linha = input.nextInt();
		int coluna = input.nextInt();
			
		while ( !verifyPlay(quadro, linha, coluna) )
		{
			System.out.println("Jogada Invalida");
			System.out.print("Seu movimento: ");
			quadro = input.nextInt();
			linha = input.nextInt();
			coluna = input.nextInt();
		}
		
		putO(quadro, linha, coluna);
	}
	
	private void putX(int quadro, int linha, int coluna)
	{
		board[quadro][linha][coluna] = Values.X;
	}
	
	private void putO(int quadro, int linha, int coluna)
	{
		board[quadro][linha][coluna] = Values.O;
	}
	
	private void setWinner( Values win )
	{
		winner = win.getString();
	}
	
	public String getWinner()
	{
		return this.winner;
	}
	
	public void displayBoard()
	{
		System.out.println("\n3D 4x4x4 TicTacToe");
		System.out.println("==================");
		for(int i=0; i<4; i++)
		{
			for(int j=0; j<4; j++)
			{
				for(int k=0; k<4; k++)
				{
					if( k == 0)
						System.out.printf(" %s ", board[i][j][k].getString() );
					else
						System.out.printf("| %s ", board[i][j][k].getString() );
				}
				System.out.println();
				if(j != 3)
					System.out.println("---------------");
			}
			System.out.println();
			System.out.println();
		}
	}
	
	public boolean verifyWinner()
	{
		boolean winner = false;
		
		//Verificar as retas horizontal
		for(int i=0; i<4; i++)
		{
			for(int j=0; j<4; j++)
			{
				if( board[i][j][0] == board[i][j][1] && board[i][j][1] == board[i][j][2] &&
				board[i][j][2] == board[i][j][3] && board[i][j][0] != Values.EMPTY )
				{
					winner = true;	
					setWinner( board[i][j][0] );
					System.out.printf("\n Horizontal quadro %d linha %d\n", i, j);
				}
			}
		}
		
		
		//Verificar as retas verticais
		for(int i=0; i<4; i++)
		{
			for(int k=0; k<4; k++)
			{
				if( board[i][0][k] == board[i][1][k] && board[i][1][k] == board[i][2][k] &&
				board[i][2][k] == board[i][3][k] && board[i][0][k] != Values.EMPTY )
				{
					winner = true;	
					setWinner( board[i][0][k] );
					System.out.printf("\n Vertical quadro %d coluna %d\n", i, k);
				}
			}
		}
		
		//Verificar as retas diagonais principais
		for(int i=0; i<4; i++)
		{
			if( board[i][0][0] == board[i][1][1] && board[i][1][1] == board[i][2][2] &&
			board[i][2][2] == board[i][3][3] && board[i][0][0] != Values.EMPTY )
			{
				winner = true;	
				setWinner( board[i][0][0] );
				System.out.printf("\n Diagonal Principal quadro %d\n", i);
			}
		}
		
		//Verificar as retas diagonais secundarias
		for(int i=0; i<4; i++)
		{
			if( board[i][0][3] == board[i][1][2] && board[i][1][2] == board[i][2][1] &&
			board[i][2][1] == board[i][3][0] && board[i][0][3] != Values.EMPTY )
			{
				winner = true;	
				setWinner( board[i][0][3] );
				System.out.printf("\n Diagonal Secundaria quadro %d\n", i);
			}	
		}
		
		//Verificar as retas horizontal em 3D Ponto 1
		for(int j=0; j<4; j++)
			{
			if( board[0][j][0] == board[1][j][1] && board[1][j][1] == board[2][j][2] &&
			board[2][j][2] == board[3][j][3] && board[0][j][0] != Values.EMPTY )
			{
				winner = true;	
				setWinner( board[0][j][0] );
				System.out.printf("\n Horizontal 3D linha %d\n", j);
			}
		}
		
		//Verificar as retas horizontal em 3D Ponto 2
		for(int j=0; j<4; j++)
			{
			if( board[0][j][3] == board[1][j][2] && board[1][j][2] == board[2][j][1] &&
			board[2][j][1] == board[3][j][0] && board[0][j][3] != Values.EMPTY )
			{
				winner = true;	
				setWinner( board[0][j][3] );
				System.out.printf("\n Horizontal 3D linha %d\n", j);
			}
		}
		
		//Verificar as retas verticais em 3D Ponto 1
		for(int k=0; k<4; k++)
		{
			if( board[0][0][k] == board[1][1][k] && board[1][1][k] == board[2][2][k] &&
			board[2][2][k] == board[3][3][k] && board[0][0][k] != Values.EMPTY )
			{
				winner = true;	
				setWinner( board[0][0][k] );
				System.out.printf("\n Vertical 3D coluna %d\n", k);
			}
		}
		
		//Verificar as retas verticais em 3D Ponto 2
		for(int k=0; k<4; k++)
		{
			if( board[0][3][k] == board[1][3][k] && board[1][3][k] == board[2][3][k] &&
			board[1][2][k] == board[0][3][k] && board[0][3][k] != Values.EMPTY )
			{
				winner = true;	
				setWinner( board[0][3][k] );
				System.out.printf("\n Vertical 3D coluna %d\n", k);
			}
		}
		
		//Verificar as retas diagonais principais 3D Ponto 1
		if( board[0][0][0] == board[1][1][1] && board[1][1][1] == board[2][2][2] &&
		board[2][2][2] == board[3][3][3] && board[0][0][0] != Values.EMPTY )
		{
			winner = true;	
			setWinner( board[0][0][0] );
			System.out.printf("\n Diagonal Principal 3D\n");
		}
		
		//Verificar as retas diagonais principais 3D Ponto 2
		if( board[0][3][3] == board[1][2][2] && board[1][2][2] == board[2][1][1] &&
		board[2][1][1] == board[3][0][0] && board[0][3][3] != Values.EMPTY )
		{
			winner = true;	
			setWinner( board[0][3][3] );
			System.out.printf("\n Diagonal Principal 3D\n");
		}
		
		//Verificar as retas diagonais secundarias 3D Ponto 1
		if( board[0][0][3] == board[1][1][2] && board[1][1][2] == board[2][2][1] &&
		board[2][2][1] == board[3][3][0] && board[0][0][3] != Values.EMPTY )
		{
			winner = true;	
			setWinner( board[0][0][3] );
			System.out.printf("\n Diagonal Secundaria 3D\n");
		}	

		//Verificar as retas diagonais secundarias 3D Ponto 2
		if( board[0][3][0] == board[1][2][1] && board[1][2][1] == board[2][1][2] &&
		board[2][1][2] == board[3][0][3] && board[0][3][0] != Values.EMPTY )
		{
			winner = true;	
			setWinner( board[0][3][0] );
			System.out.printf("\n Diagonal Secundaria 3D\n");
		}	
		
		return winner;
	}
}