package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class GeradorDataNascimento 
{
	private DateTimeFormatter formataData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public String geraDataNascimento() 
	{
		Random random = new Random();
		int minDay = (int) LocalDate.of(1920, 1, 1).toEpochDay();
		int maxDay = (int) LocalDate.of(2001, 1, 1).toEpochDay();
		long randomDay = minDay + random.nextInt(maxDay - minDay);	

		LocalDate randomBirthDate = LocalDate.ofEpochDay(randomDay);
		
		return randomBirthDate.format(formataData);
	}
}
