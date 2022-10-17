import java.io.File;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FileTest extends JFrame
{
	JTextArea campoTexto;
	JButton gravarBotao;
	JButton recuperarBotao;
	JPanel painelBotao;
	BufferedWriter escritor;
	BufferedReader leitor;
	File arquivo;
	
	public FileTest()
	{
		super("Escrevendo em um arquivo");
		
		try
		{
			arquivo = new File("Arquivo.txt");
			if( !arquivo.exists() )
				arquivo.createNewFile();
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}
		
		setLayout(new FlowLayout());
		
		campoTexto = new JTextArea(10, 15);
		
		gravarBotao = new JButton("Gravar");
		recuperarBotao = new JButton("Recuperar");

		painelBotao = new JPanel();
		painelBotao.add(gravarBotao);
		painelBotao.add(recuperarBotao);
		
		gravarBotao.addActionListener(
			new ActionListener()
			{
				public void actionPerformed( ActionEvent event )
				{
					try
					{
						escritor = new BufferedWriter( new FileWriter(arquivo) );
						
						String texto = campoTexto.getText();
						
						char[] textoChar = new char[texto.length()];
						texto.getChars(0, texto.length(), textoChar, 0);
						for(char c: textoChar)
							if( c == '\n' )
								escritor.newLine();
							else
								escritor.write( c );
						
						escritor.flush();
						escritor.close();
					}
					catch( IOException e)
					{
						System.out.println("Erro de escrita");
					}
				}
			}
		);
		
		recuperarBotao.addActionListener(
			new ActionListener()
			{
				public void actionPerformed( ActionEvent event )
				{
					try
					{
						leitor = new BufferedReader( new FileReader(arquivo) );
						
						String texto = "";
						String linha = "";
						
						while ( linha != null )
						{
							linha = leitor.readLine();
							if( linha != null )
								texto += linha + "\n";
						}		
						
						leitor.close();
						
						campoTexto.setText( texto );
					}
					catch( IOException e)
					{
						System.out.println("Erro de leitura");
					}
				}
			}
		);
		
		add(campoTexto);
		add(painelBotao);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(250, 250);
		setVisible(true);
	}
	
	public static void main(String[] args)
	{
		FileTest app = new FileTest();
	}
}