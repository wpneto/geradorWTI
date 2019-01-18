package util;

public class GeradorChassi {
	
	private String chassi = "";	
	private static int tamanhoMeio = 8, tamanhoFim = 6;
	
	private String[] caracteresInicio = {"9BD","9BG","9BW","9BF","93H","9BR","936","935","93Y",
			"93X","9BH","95P","94D","98R","988","98M","9BM","99A","99J","9C2","9C6","9CD","93W",
			"93Z","953","9BS","9BV","8AF","8AG","8AW","8AP","8AJ","8A1","8AC","8BC","8AD","8C3",
			"8AT"};
	
	private char[] caracteresAceitos = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9','A','B', 
			'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'R', 'S', 'T', 'U', 'V', 
			'W', 'X', 'Y', 'Z'};
	
	private char[] numerosAceitos = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
	
	public String gerarChassi()
	{
		chassi = "";
		
		int c = (int) Math.floor(Math.random()*this.getCaracteresInicio().length);
		chassi += this.getCaracteresInicio()[c];
		
		for (int i = 0; i < tamanhoMeio; i++) 
		{
			c = (int) Math.floor(Math.random()*this.getCaracteresAceitos().length);
			chassi += this.getCaracteresAceitos()[c];
		}
		
		for (int i = 0; i < tamanhoFim; i++) 
		{
			c = (int) Math.floor(Math.random()*this.getNumerosAceitos().length);
			chassi += this.getNumerosAceitos()[c];
		}
		return chassi;
	}

	private char[] getCaracteresAceitos() {
		return caracteresAceitos;
	}	
	private char[] getNumerosAceitos() {
		return numerosAceitos;
	}
	private String[] getCaracteresInicio() {
		return caracteresInicio;
	}
}
