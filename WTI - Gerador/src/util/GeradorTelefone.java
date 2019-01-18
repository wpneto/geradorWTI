package util;

public class GeradorTelefone 
{	
	private String telefone = "";	
	private static int tamanhoTelFixo = 8, tamanhoTelCel = 9;
	private String fixoGerado = "", celularGerado = "", dddGerado = "";
	
	private String[] ddd = {"11","12","13","14","15","16","17","18","19","22",
			                "21","24","27","28","31","32","33","34","35","37",
			                "38","41","42","43","44","45","46","47","48","49",
			                "51","53","54","55","61","62","63","64","65","66",
			                "67","68","69","71","73","74","75","77","79","81",
			                "82","83","84","85","86","87","88","89","91","92",
			                "93","94","95","96","97","98","99"};
	private char[] inicioFixo = {'2', '3', '4', '5', '6'};
	
	private char[] inicioCelular = {'9'};	
	private String[] meioCelular = {"70","71","72","73","74","75","76","77",
			                          "78","79","80","81","82","83","84","85",
			                          "86","87","88","89","91","92","93","94",
			                          "95","96","97","98","99"};
	private char[] numerosAceitos = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
	
	public void gerarTelefones()
	{
		fixoGerado = "";
		
		int c = (int) Math.floor(Math.random()*this.getDdd().length);
		dddGerado = this.getDdd()[c];
		
		
		fixoGerado = dddGerado;
		celularGerado = dddGerado+getInicioCelular()[0];
		
		//Gerando telefone FIXO
		c = (int) Math.floor(Math.random()*this.getInicioFixo().length);
		fixoGerado += this.getInicioFixo()[c];
		
		for (int i = 0; i < tamanhoTelFixo-1; i++) 
		{
			c = (int) Math.floor(Math.random()*this.getNumerosAceitos().length);
			fixoGerado += this.getNumerosAceitos()[c];
		}
		
		//Gerando telefone celular com o mesmo DDD do FIXO
		c = (int) Math.floor(Math.random()*this.getMeioCelular().length);
		celularGerado += this.getMeioCelular()[c];
		
		for (int i = 0; i < tamanhoTelCel-3; i++) 
		{
			c = (int) Math.floor(Math.random()*this.getNumerosAceitos().length);
			celularGerado += this.getNumerosAceitos()[c];
		}	
	}
	
	public String gerarTelefoneCelularLista(boolean mascara) 
	{
		telefone = "";
		
		int c = (int) Math.floor(Math.random()*this.getDdd().length);
		telefone += this.getDdd()[c]+getInicioCelular()[0];
		
		c = (int) Math.floor(Math.random()*this.getMeioCelular().length);
		telefone += this.getMeioCelular()[c];
		
		for (int i = 0; i < tamanhoTelCel-3; i++) 
		{
			c = (int) Math.floor(Math.random()*this.getNumerosAceitos().length);
			telefone += this.getNumerosAceitos()[c];
		}
		
		if (mascara)
		{
			telefone = "(" + telefone.substring(0, 2) + ") " + telefone.substring(2, 7) + "-" + telefone.substring(7, 11);		
		}
				
		return telefone;
	}
	
	public String gerarTelefoneFixoLista(boolean mascara)
	{
		telefone = "";
		
		int c = (int) Math.floor(Math.random()*this.getDdd().length);
		telefone += this.getDdd()[c];
		
		c = (int) Math.floor(Math.random()*this.getInicioFixo().length);
		telefone += this.getInicioFixo()[c];
		
		for (int i = 0; i < tamanhoTelFixo-1; i++) 
		{
			c = (int) Math.floor(Math.random()*this.getNumerosAceitos().length);
			telefone += this.getNumerosAceitos()[c];
		}
				
		if (mascara)
		{
			telefone = "(" + telefone.substring(0, 2) + ") " + telefone.substring(2, 6) + "-" + telefone.substring(6, 10);
		}
				
		return telefone;
	}
	
	private char[] getNumerosAceitos() {
		return numerosAceitos;
	}
	
	private char[] getInicioFixo() {
		return inicioFixo;
	}
	
	private char[] getInicioCelular() {
		return inicioCelular;
	}
	
	private String[] getMeioCelular() {
		return meioCelular;
	}
	
	private String[] getDdd() {
		return ddd;
	}	
	
	public String getCelularGerado(boolean mascara) {
		
		if (mascara)
		{
			celularGerado = "(" + celularGerado.substring(0, 2) + ") " + celularGerado.substring(2, 7) + "-" + celularGerado.substring(7, 11);
		}			
		return celularGerado;
	}
	
	public String getFixoGerado(boolean mascara) 
	{
		if (mascara)
		{
			fixoGerado = "(" + fixoGerado.substring(0, 2) + ") " + fixoGerado.substring(2, 6) + "-" + fixoGerado.substring(6, 10);
		}	
		
		return fixoGerado;
	}
}
