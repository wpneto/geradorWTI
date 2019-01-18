package util;

import java.text.Normalizer;

public class GeradorEmail 
{	
	String[] provedores = new String[] { "gmail.com", "hotmail.com", "yahoo.com.br",
			"outlook.com", "bol.com.br", "uol.com.br"};
	
	private String removeCaracteres(String entrada) {
		return Normalizer
				.normalize(entrada, Normalizer.Form.NFD)
				.replaceAll("[^\\p{ASCII}]", "");
	}
	
	
	public String gerarEmail(String nome, String sobrenome)
	{
		String email = "";
		int c = (int) Math.floor(Math.random()*provedores.length);
		String provedorGerado = provedores[c];
		
		email = removeCaracteres(nome.toLowerCase() + "_" + sobrenome.toLowerCase());
				
		return email.replaceAll(" ","")+"@"+provedorGerado;
	}
	
}
