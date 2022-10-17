import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class KnightTour3
{
	// Variaveis estaticas--------------------------------------------------------
	private static int[][] table = new int[8][8]; // O tabuleiro de xadrez
	private static int[] horizontal = new int[8]; // Marca o movimento horizontal
	private static int[] vertical = new int[8]; // Marca o movimento vertical
	private static int[][] accessibility = new int[8][8]; // Marca a acessibilidade da posicao (quantidade de movimentos validos)
	// ---------------------------------------------------------------------------

	// Bloco estatico para inicializar as tabelas---------------------------------
	static
	{
		vertical[0] = 2;
		vertical[1] = 1;
		vertical[2] = -1;
		vertical[3] = -2;
		vertical[4] = -2;
		vertical[5] = -1;
		vertical[6] = 1;
		vertical[7] = 2;
		horizontal[0] = -1;
		horizontal[1] = -2;
		horizontal[2] = -2;
		horizontal[3] = -1;
		horizontal[4] = 1;
		horizontal[5] = 2;
		horizontal[6] = 2;
		horizontal[7] = 1;

		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				table[i][j] = 0;

		accessibility[0][0] = 2;
		accessibility[0][1] = 3;
		accessibility[0][2] = 4;
		accessibility[0][3] = 4;
		accessibility[0][4] = 4;
		accessibility[0][5] = 4;
		accessibility[0][6] = 3;
		accessibility[0][7] = 2;
		accessibility[1][0] = 3;
		accessibility[1][1] = 4;
		accessibility[1][2] = 6;
		accessibility[1][3] = 6;
		accessibility[1][4] = 6;
		accessibility[1][5] = 6;
		accessibility[1][6] = 4;
		accessibility[1][7] = 3;
		accessibility[2][0] = 4;
		accessibility[2][1] = 6;
		accessibility[2][2] = 8;
		accessibility[2][3] = 8;
		accessibility[2][4] = 8;
		accessibility[2][5] = 8;
		accessibility[2][6] = 6;
		accessibility[2][7] = 4;
		accessibility[3][0] = 4;
		accessibility[3][1] = 6;
		accessibility[3][2] = 8;
		accessibility[3][3] = 8;
		accessibility[3][4] = 8;
		accessibility[3][5] = 8;
		accessibility[3][6] = 6;
		accessibility[3][7] = 4;
		accessibility[4][0] = 4;
		accessibility[4][1] = 6;
		accessibility[4][2] = 8;
		accessibility[4][3] = 8;
		accessibility[4][4] = 8;
		accessibility[4][5] = 8;
		accessibility[4][6] = 6;
		accessibility[4][7] = 4;
		accessibility[5][0] = 4;
		accessibility[5][1] = 6;
		accessibility[5][2] = 8;
		accessibility[5][3] = 8;
		accessibility[5][4] = 8;
		accessibility[5][5] = 8;
		accessibility[5][6] = 6;
		accessibility[5][7] = 4;
		accessibility[6][0] = 3;
		accessibility[6][1] = 4;
		accessibility[6][2] = 6;
		accessibility[6][3] = 6;
		accessibility[6][4] = 6;
		accessibility[6][5] = 6;
		accessibility[6][6] = 4;
		accessibility[6][7] = 3;
		accessibility[7][0] = 2;
		accessibility[7][1] = 3;
		accessibility[7][2] = 4;
		accessibility[7][3] = 4;
		accessibility[7][4] = 4;
		accessibility[7][5] = 4;
		accessibility[7][6] = 3;
		accessibility[7][7] = 2;
	}

	// ---------------------------------------------------------------------------

	public static void main(String[] args)
	{
		// Obtem a posicao inicial------------------------------------------------
		Scanner input = new Scanner(System.in);

		boolean valido = false;
		int ver = 0;
		int hor = 0;

		// Pede para inserir a posiao, ate ser valida
		while (!valido)
		{

			System.out.println("Insira a posicao horizontal: ");
			hor = input.nextInt();

			System.out.println("Insira a posicao vertical: ");
			ver = input.nextInt();

			if (hor < 0 || hor > 7 || ver < 0 || ver > 7)
				System.out.println("Movimentoi invalido\n: ");
			else
				valido = true;
		}
		// -----------------------------------------------------------------------

		// Faz os movimentos ate onde for possivel--------------------------------
		table[ver][hor] = 1;

		boolean hasMoviment = true; // Varivel que marca se ainda ha movimentos a serem feitos
		int moves = 1; // Marca o numero do movimento feito

		// Enquanto houver movimento possivel
		while (hasMoviment)
		{
			valido = false;

			// Inicializa a Lista de movimentos, ordenando de acordo com a menor acessibilidade
			List<Moviment2> listaMoviments = new ArrayList<Moviment2>();

			// Verifica se ha movimentos para serem feitos na posicao atual do cavalo e calcula a acessibilidade do movimento
			hasMoviment = false;
			for (int k = 0; k < 8; k++)
			{
				int horAux = hor + horizontal[k];
				int verAux = ver + vertical[k];

				// Verifica se o movimento e valido
				if (!(horAux < 0 || horAux > 7 || verAux < 0 || verAux > 7))
				{
					if (table[verAux][horAux] == 0)
					{
						// Se for valido entao ha movimentos a serem feitos
						hasMoviment = true;

						// A partir desse movimento, verificar qual o menor valor de acessibilidade dos proximos movimentos
						int minimoAcessbility = Moviment2.INACCESSIBLE;
						for (int j = 0; j < 8; j++)
						{
							int horAux2 = horAux + horizontal[j];
							int verAux2 = verAux + vertical[j];

							if (!(horAux2 < 0 || horAux2 > 7 || verAux2 < 0 || verAux2 > 7))
							{
								if (table[verAux][horAux] == 0)
								{
									if (accessibility[verAux2][horAux2] < minimoAcessbility)
										minimoAcessbility = accessibility[verAux2][horAux2];
								}
							}
						}

						listaMoviments.add(new Moviment2(accessibility[verAux][horAux], k, minimoAcessbility));
					}
				} else
				{
					listaMoviments.add(new Moviment2(Moviment2.INACCESSIBLE, k, 0));
				}
			}

			// Se nao tiver mais movimento, terminar o laco
			if (!hasMoviment)
				break;

			// Ordena os movimentos
			Collections.sort(listaMoviments);

			// Obtem o movimento valido
			for (Moviment2 move : listaMoviments)
			{
				int horAux = hor;
				int verAux = ver;
				horAux = hor + horizontal[move.getMovimentNumber()];
				verAux = ver + vertical[move.getMovimentNumber()];

				// Verifica consistencia, ou seja, se o movimento nao leva o cavalo para fora do tabuleiros
				if (move.getAcessbilityRange() < Moviment2.INACCESSIBLE)
				{
					// Verifica se o movimento ainda nao foi feito
					if (table[verAux][horAux] == 0)
					{
						hor = horAux;
						ver = verAux;

						// Atualiza a tabela
						moves++;
						table[ver][hor] = moves;

						// System.out.println(move.getAcessbilityRange() + " " + move.getMinAccessbility());
						// printTable();
						// System.out.println();
						// System.out.println();
						// System.out.println();
						// System.out.println();
						// System.out.println();
						// System.out.println();
						// System.out.println();
						// System.out.println();

						accessibility[ver][hor]--;

						break;
					}
				}
			}
		}
		// -----------------------------------------------------------------------

		System.out.println("O passeio durou " + moves + " movimentos");
		printTable();
	}

	private static void printTable()
	{
		for (int i = 0; i < 7; i++)
		{
			System.out.printf(" %2d | %2d | %2d | %2d | %2d | %2d | %2d | %2d", table[0][i], table[1][i], table[2][i], table[3][i], table[4][i], table[5][i], table[6][i], table[7][i]);
			System.out.println(" \n________________________________________\n");
		}
		System.out.printf(" %2d | %2d | %2d | %2d | %2d | %2d | %2d | %2d", table[0][7], table[1][7], table[2][7], table[3][7], table[4][7], table[5][7], table[6][7], table[7][7]);
	}
}
