package programa;

import controle.ControleTelaPrincipal;
import visao.TelaPrincipal;

public class Principal 
{
	public static void main(String[] args) 
	{		
		TelaPrincipal tela = new TelaPrincipal();
		new ControleTelaPrincipal(tela);
		tela.setLocationRelativeTo(null);
		tela.setVisible(true);
	}	
}