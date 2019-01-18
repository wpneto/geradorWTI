package util;

public class GeradorNomeSobrenome {
	
	private String[] nomeHomens = {"Miguel","Davi","Arthur","Pedro","Gabriel","Bernardo",
			"Lucas","Matheus","Rafael","Heitor","Enzo","Guilherme","Nicolas","Lorenzo",
			"Gustavo","Felipe","Samuel","Jo�o Pedro","Daniel","Vitor","Leonardo","Henrique",
			"Theo","Murilo","Eduardo","Pedro Henrique","Pietro","Cau�","Isaac","Caio",
			"Vinicius","Benjamin","Jo�o","Lucca","Jo�o Miguel","Bryan","Joaquim","Jo�o Vitor",
			"Thiago","Ant�nio","Davi Lucas","Francisco","Enzo Gabriel","Bruno","Emanuel",
			"Jo�o Gabriel","Ian","Davi Luiz","Rodrigo","Ot�vio","Wilson","Luis","Ewerton","Tiago",
			"Alexandre","Andr�","Ant�nio","Augusto","Breno","Bruno","Caio","Cau�","Daniel",
			"Danilo","Eduardo","Enrico","Enzo","Erick","Felipe","Fernando","Francisco",
			"Frederico","Guilherme","Gustavo","Henrique","Henry","Ian","Isaac","Jo�o","Kaique",
			"Leonardo","Luan","Lucas","Mathias","Murilo","Nathan","Ot�vio","Pietro","Rafael",
			"Raul","Rian","Ricardo","Rodrigo","Samuel","Theo","Thiago","Thomas","Vicente",
			"Vin�cius","Vitor","Yago","Ygor","Yuri","Arthur Gabriel","Arthur Miguel",
			"Carlos Eduardo","Davi Luiz","Davi Miguel","Enzo Gabriel","Enzo Miguel",
			"Jo�o Gabriel","Jo�o Guilherme","Jo�o Lucas","Jo�o Miguel","Jo�o Pedro","Jo�o Vitor",
			"Lucas Gabriel","Luiz Felipe","Luiz Gustavo","Luiz Henrique","Luiz Miguel",
			"Luiz Ot�vio","Pedro Henrique","Pedro Lucas","Pedro Miguel","Vitor Hugo",
			"�lvaro","Amado","Antony","Bartolomeu","Benedito","Benito","Benjamin","Bento","Ben�cio",
			"Calebe","Ciro","Conrado","Cristov�o","C�cero","Dante","Dimitri","Dom","Emanuel","Ernesto",
			"Franco","Gael","Gaspar","Gon�alo","Greg�rio","Guilhermo","Hermano","�caro","In�cio","Levi",
			"Lince","Lino","Louren�o","Martim","Nicolas","Noah","Nuno","Oliver","Oscar","Romeu","Ruan",
			"Salom�o","Santiago","Serafim","Tadeu","Tom�","Valentino","Vince","Zion"};
	
	private String[] nomeMulheres = {"Sophia","Alice","Julia","Isabella","Manuela","Laura","Luisa",
			"Valentina","Giovanna","Maria Eduarda","Helena","Beatriz","Maria Luiza","Luiza",
			"Lara","Mariana","Nicole","Rafaela","Helo�sa","Isadora","L�via","Maria Clara",
			"Ana Clara","Lorena","Gabriela","Yasmin","Isabelly","Sarah","Ana Julia","Let�cia",
			"Ana Luiza","Melissa","Marina","Clara","Cec�lia","Esther","Emanuelly","Rebeca",
			"Ana Beatriz","Lav�nia","Vit�ria","Bianca","Catarina","Larissa","Maria Fernanda",
			"Fernanda","Amanda","Al�cia","Carolina","Agatha","Gabrielly","Jaqueline","Maria Luiza",
			"Patricia","M�nica","Cleuza","Maria Irene","Miriam","Cintia","Kelly","Janaina",
			"�gda","Agnes","Aid�","Alana","Al�xia","Allegra","Am�lie","Antonella","Ariella","Augusta",
			"Augustine","Ayla","Beatrice","Bertha","Br�gida","Brigitte","Carlota","Catherina","Celine",
			"Charlote","Clarita","Claudine","Cloe","Constan�a","Corina","Daphne","Desir�e","Din�","Dinor�",
			"Dinorah","Diva","Dolores","Donatella","Donna","Dora","Doralice","Dorothea","Edviges","Elo�",
			"Eloah","Emiliana","Emma","Esperanza","Ethel","Eudora","Eug�nia","Eul�lia","Evangeline","Fel�cia",
			"Florence","Francis","Frieda","Giselle","Giulia","Graciela","Gretta","Guida","Guilhermina","Haid�",
			"Hayde�","Hilda","Ieda","Ingrid","Iolanda","Isa","Isabela","Izabela","Ivy","Jamile","Jana�na",
			"Janice","J�ssica","Joaquina","Johanna","Jordana","Juliette","Justine","Laila","La�se","Lara","Lauren",
			"Lav�nia","L�a","Leandra","Leda","Leilane","Lenice","Leona","Leonora","Lia","L�dia","Lina","Linda",
			"L�via","Lolita","Loren","Loretta","Lucille","Luciene","Luisa","Luiza","Luma","Mait�","Ma�ra",
			"Maria Irene","Maria Luisa","M�nica","Monique","Mari�","Malu","Morgana","Mila","Mirela","Megan",
			"Margot","Madeleine","Marietta","Natasha","N�via","Nice","Ol�via","Ohana","Pandora","Patricia",
			"Pilar","Paloma","R�bia","Ramona","Ros�ria","Rosemarie","Stela","Selena","Scarlet","Sarai",
			"Tarsila","Theodora","T�lia","Taila","Tamara","Tamires","�rsula","Violetta","Verusca","Virna",
			"Vida","Velma","Virg�nia","Veranice","Zara","Z�lia","Zo�","Zaira","Zil�","Zuleica"};
	
	private String[] sobrenomes = {"Almeida","Alves","Andrade","Barbosa","Barros","Batista","Borges",
			"Campos","Cardoso","Carvalho","Castro","Costa","da Silva","Dias","Duarte","Freitas",
			"Fernandes","Ferreira","Garcia","Gomes","Gon�alves","Lima","Lopes","Machado","Marques",
			"Martins","Medeiros","Melo","Mendes","Miranda","Monteiro","Moraes","Moreira","Moura",
			"Nascimento","Nunes","Oliveira","Pereira","Pinheiro","Ramos","Reis","Ribeiro","Rocha",
			"Rodrigues","Santana","Santos","Silva","Soares","Souza","Teixeira","Vieira","Abelho",
			"Abranches","Agostinho","Aguiar","Albuquerque","Alencar","Almada","Almeida",
			"Alves","Alvim","Amaral","Andrade","Anlicoara","Antas","Antunes","Aranha","Ara�jo",
			"�vila","Baldaia","Bandeira","Barbosa","Benevides","Berenguer","Bettencourt","Borges",
			"Botelho","Bragan��os","Brito","Caldas","Camargo","Camelo","Campos","Canto","Carneiro",
			"Carvalho","Castro","Ch�vez","Corte-Real","Vasco","Costa","Cotrim","Esteves",
			"Pais Barreto","Feij�","Fernandes","Ferreira","Ferronha","Figueiredo","Filgueiras",
			"Frade","Gama","Garcia","Gomes","Gon�alves","Guedes","Guimar�es","Hildebrando",
			"Holtreman","In�cio","Junqueira","Lancastre","Lima","Maciel","Magalh�es","Martins",
			"Martins Alfena","Mascarenhas","Mata","Medeiros","Melo","Monteiro","Montenegro","Morais",
			"Neves","Noronha","Oliveira","Paes","Penha","Pereira","Pinheiro","Prado","Proen�a",
			"Ram�rez","Ramos","Henriques","Reis","Resende","Rodrigues","S�","Santos","Sarmento",
			"Silva","Silveira","Soares","Sousa","Soverosa","Taveira","Teixeira","Teles","Vaz",
			"Veloso","Viegas","Vieira"};
	
	private String nomeGerado = "";
	
	public String gerarNome(String sexo) 
	{
		nomeGerado = "";
		int c;
		
		if ("M".equals(sexo))
		{
			c = (int) Math.floor(Math.random()*nomeHomens.length);
			nomeGerado = nomeHomens[c];
		}
		else if ("F".equals(sexo))
		{
			c = (int) Math.floor(Math.random()*nomeMulheres.length);
			nomeGerado = nomeMulheres[c];
		}

		return nomeGerado;
	}
	
	public String gerarSobrenome() 
	{
		int c = (int) Math.floor(Math.random()*sobrenomes.length);
		return sobrenomes[c];
	}
}
