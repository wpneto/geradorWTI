package util;

import java.util.Random;

public class GeradorRenavam 
{
	public String geraNumeroRenavamValido(boolean mascara) {
		Random randomizador = new Random();
		String renavamGeradoAleatoriamente = "";
		for (int i = 0; i < 10; i++) {
			renavamGeradoAleatoriamente += Math.abs(randomizador.nextInt(8));
		}

		String renavamSemDigito = renavamGeradoAleatoriamente.substring(0, 10);

		String renavamReversoSemDigito = new StringBuffer(renavamSemDigito).reverse().toString();

		int soma = 0;
		for (int i = 0; i < 8; i++) {
			Integer algarismo = Integer.parseInt(renavamReversoSemDigito.substring(i, i + 1));
			Integer multiplicador = i + 2;
			soma += algarismo * multiplicador;
		}

		soma += Character.getNumericValue(renavamReversoSemDigito.charAt(8)) * 2;
		soma += Character.getNumericValue(renavamReversoSemDigito.charAt(9)) * 3;

		int mod11 = soma % 11;
		int ultimoDigitoCalculado = 11 - mod11;
		ultimoDigitoCalculado = (ultimoDigitoCalculado >= 10 ? 0 : ultimoDigitoCalculado);
		
		String renavamFinal = renavamGeradoAleatoriamente + ultimoDigitoCalculado;
				
		if (mascara)
		{
			renavamFinal = renavamFinal.substring(0, 4) + "." + renavamFinal.substring(4, 10) + "-" + renavamFinal.substring(10, 11);
		}		
				
		return renavamFinal;
	}
}
