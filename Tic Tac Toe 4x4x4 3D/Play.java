import java.util.Scanner;

public class Play
{
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		String nome;
		String primeiro;
		TicTacToe game = new TicTacToe();
		boolean winner = false;
		
		System.out.println("3D 4x4x4 TicTacToe");
		System.out.println("==================");
		System.out.print("Seu Nome: ");
		nome = input.next();
		System.out.printf("%s, voce gostaria de jogar primeiro?(S/N)", nome);
		primeiro = input.next();
		System.out.println();
		System.out.println();
		
		if( primeiro.equals("S") )
			game.takePlay();			
		
		while( !winner )
		{
			game.displayBoard();
			winner = game.verifyWinner();
			if( winner )
				break;
			game.makePlay();
			game.displayBoard();
			winner = game.verifyWinner();
			if( winner )
				break;
			game.takePlay();
		}
		
		if( game.getWinner().equals("O"))
			System.out.println("Voce Ganhou");
		if( game.getWinner().equals("X"))
			System.out.println("Voce Perdeu");
		if( game.getWinner().equals("tie"))
			System.out.println("Empatou");
	}
}