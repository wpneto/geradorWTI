package util;

public class GeradorNomeSobrenome {
	
	private String[] nomeHomens = {"Miguel","Davi","Arthur","Pedro","Gabriel","Bernardo",
			"Lucas","Matheus","Rafael","Heitor","Enzo","Guilherme","Nicolas","Lorenzo",
			"Gustavo","Felipe","Samuel","João Pedro","Daniel","Vitor","Leonardo","Henrique",
			"Theo","Murilo","Eduardo","Pedro Henrique","Pietro","Cauã","Isaac","Caio",
			"Vinicius","Benjamin","João","Lucca","João Miguel","Bryan","Joaquim","João Vitor",
			"Thiago","Antônio","Davi Lucas","Francisco","Enzo Gabriel","Bruno","Emanuel",
			"João Gabriel","Ian","Davi Luiz","Rodrigo","Otávio","Wilson","Luis","Ewerton","Tiago",
			"Alexandre","André","Antônio","Augusto","Breno","Bruno","Caio","Cauã","Daniel",
			"Danilo","Eduardo","Enrico","Enzo","Erick","Felipe","Fernando","Francisco",
			"Frederico","Guilherme","Gustavo","Henrique","Henry","Ian","Isaac","João","Kaique",
			"Leonardo","Luan","Lucas","Mathias","Murilo","Nathan","Otávio","Pietro","Rafael",
			"Raul","Rian","Ricardo","Rodrigo","Samuel","Theo","Thiago","Thomas","Vicente",
			"Vinícius","Vitor","Yago","Ygor","Yuri","Arthur Gabriel","Arthur Miguel",
			"Carlos Eduardo","Davi Luiz","Davi Miguel","Enzo Gabriel","Enzo Miguel",
			"João Gabriel","João Guilherme","João Lucas","João Miguel","João Pedro","João Vitor",
			"Lucas Gabriel","Luiz Felipe","Luiz Gustavo","Luiz Henrique","Luiz Miguel",
			"Luiz Otávio","Pedro Henrique","Pedro Lucas","Pedro Miguel","Vitor Hugo",
			"Álvaro","Amado","Antony","Bartolomeu","Benedito","Benito","Benjamin","Bento","Benício",
			"Calebe","Ciro","Conrado","Cristovão","Cícero","Dante","Dimitri","Dom","Emanuel","Ernesto",
			"Franco","Gael","Gaspar","Gonçalo","Gregório","Guilhermo","Hermano","Ícaro","Inácio","Levi",
			"Lince","Lino","Lourenço","Martim","Nicolas","Noah","Nuno","Oliver","Oscar","Romeu","Ruan",
			"Salomão","Santiago","Serafim","Tadeu","Tomé","Valentino","Vince","Zion"};
	
	private String[] nomeMulheres = {"Sophia","Alice","Julia","Isabella","Manuela","Laura","Luisa",
			"Valentina","Giovanna","Maria Eduarda","Helena","Beatriz","Maria Luiza","Luiza",
			"Lara","Mariana","Nicole","Rafaela","Heloísa","Isadora","Lívia","Maria Clara",
			"Ana Clara","Lorena","Gabriela","Yasmin","Isabelly","Sarah","Ana Julia","Letícia",
			"Ana Luiza","Melissa","Marina","Clara","Cecília","Esther","Emanuelly","Rebeca",
			"Ana Beatriz","Lavínia","Vitória","Bianca","Catarina","Larissa","Maria Fernanda",
			"Fernanda","Amanda","Alícia","Carolina","Agatha","Gabrielly","Jaqueline","Maria Luiza",
			"Patricia","Mônica","Cleuza","Maria Irene","Miriam","Cintia","Kelly","Janaina",
			"Ágda","Agnes","Aidê","Alana","Aléxia","Allegra","Amálie","Antonella","Ariella","Augusta",
			"Augustine","Ayla","Beatrice","Bertha","Brígida","Brigitte","Carlota","Catherina","Celine",
			"Charlote","Clarita","Claudine","Cloe","Constança","Corina","Daphne","Desirée","Diná","Dinorá",
			"Dinorah","Diva","Dolores","Donatella","Donna","Dora","Doralice","Dorothea","Edviges","Eloá",
			"Eloah","Emiliana","Emma","Esperanza","Ethel","Eudora","Eugênia","Eulália","Evangeline","Felícia",
			"Florence","Francis","Frieda","Giselle","Giulia","Graciela","Gretta","Guida","Guilhermina","Haidê",
			"Haydeé","Hilda","Ieda","Ingrid","Iolanda","Isa","Isabela","Izabela","Ivy","Jamile","Janaína",
			"Janice","Jéssica","Joaquina","Johanna","Jordana","Juliette","Justine","Laila","Laíse","Lara","Lauren",
			"Lavínia","Léa","Leandra","Leda","Leilane","Lenice","Leona","Leonora","Lia","Lídia","Lina","Linda",
			"Lívia","Lolita","Loren","Loretta","Lucille","Luciene","Luisa","Luiza","Luma","Maitê","Maíra",
			"Maria Irene","Maria Luisa","Mônica","Monique","Mariá","Malu","Morgana","Mila","Mirela","Megan",
			"Margot","Madeleine","Marietta","Natasha","Nívia","Nice","Olívia","Ohana","Pandora","Patricia",
			"Pilar","Paloma","Rúbia","Ramona","Rosária","Rosemarie","Stela","Selena","Scarlet","Sarai",
			"Tarsila","Theodora","Túlia","Taila","Tamara","Tamires","Úrsula","Violetta","Verusca","Virna",
			"Vida","Velma","Virgínia","Veranice","Zara","Zélia","Zoé","Zaira","Zilá","Zuleica"};
	
	private String[] sobrenomes = {"Almeida","Alves","Andrade","Barbosa","Barros","Batista","Borges",
			"Campos","Cardoso","Carvalho","Castro","Costa","da Silva","Dias","Duarte","Freitas",
			"Fernandes","Ferreira","Garcia","Gomes","Gonçalves","Lima","Lopes","Machado","Marques",
			"Martins","Medeiros","Melo","Mendes","Miranda","Monteiro","Moraes","Moreira","Moura",
			"Nascimento","Nunes","Oliveira","Pereira","Pinheiro","Ramos","Reis","Ribeiro","Rocha",
			"Rodrigues","Santana","Santos","Silva","Soares","Souza","Teixeira","Vieira","Abelho",
			"Abranches","Agostinho","Aguiar","Albuquerque","Alencar","Almada","Almeida",
			"Alves","Alvim","Amaral","Andrade","Anlicoara","Antas","Antunes","Aranha","Araújo",
			"Ávila","Baldaia","Bandeira","Barbosa","Benevides","Berenguer","Bettencourt","Borges",
			"Botelho","Bragançãos","Brito","Caldas","Camargo","Camelo","Campos","Canto","Carneiro",
			"Carvalho","Castro","Chávez","Corte-Real","Vasco","Costa","Cotrim","Esteves",
			"Pais Barreto","Feijó","Fernandes","Ferreira","Ferronha","Figueiredo","Filgueiras",
			"Frade","Gama","Garcia","Gomes","Gonçalves","Guedes","Guimarães","Hildebrando",
			"Holtreman","Inácio","Junqueira","Lancastre","Lima","Maciel","Magalhães","Martins",
			"Martins Alfena","Mascarenhas","Mata","Medeiros","Melo","Monteiro","Montenegro","Morais",
			"Neves","Noronha","Oliveira","Paes","Penha","Pereira","Pinheiro","Prado","Proença",
			"Ramírez","Ramos","Henriques","Reis","Resende","Rodrigues","Sá","Santos","Sarmento",
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
