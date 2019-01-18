package util;

public class GeradorPlaca 
{
	public String gerarPlaca(boolean mascara) 
	{
		String[] letras = new String[] { "A", "B", "C", "D", "E", "F", "G",
				"H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S",
				"T", "U", "V", "W", "X", "Y", "Z" };

		String placa = "";

		for (int i = 0; i < 3; i++) {
			placa += letras[(int) (Math.random() * 26)];
		}
		//placa += "-";
		for (int i = 0; i < 4; i++) {
			placa += (int) (Math.random() * 10);
		}
				
		if (mascara)
		{
			placa = placa.substring(0, 3) + "-" + placa.substring(3, 7);
		}
				
		return placa;	
	}
}
