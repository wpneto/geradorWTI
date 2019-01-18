package modelo;

import util.GeradorChassi;
import util.GeradorCpfCnpj;
import util.GeradorDataNascimento;
import util.GeradorEmail;
import util.GeradorNomeSobrenome;
import util.GeradorPlaca;
import util.GeradorRenavam;
import util.GeradorTelefone;

public class Tabela 
{
	GeradorCpfCnpj geradorCpfCnpj = new GeradorCpfCnpj();
	GeradorPlaca geradorPlaca = new GeradorPlaca();
	GeradorRenavam geradorRenavam = new GeradorRenavam();
	GeradorChassi geradorChassi = new GeradorChassi();
	GeradorNomeSobrenome gerarNomeSobrenome = new GeradorNomeSobrenome();
	GeradorEmail geradorEmail = new GeradorEmail();
	GeradorTelefone geradorTelefone = new GeradorTelefone();
	GeradorDataNascimento geradorDataNascimento = new GeradorDataNascimento();
	
	//"ID","NOME","SOBRENOME","DT NASC.","SEXO","CPF","CNPJ","PLACA","CHASSI","RENAVAM"
	private int id = 0;
	private String nome = "";
	private String sobrenome = "";
	private String dataNascimento = "";
	private String sexo = "";
	private String cpf = "";
	private String cnpj = "";
	private String placa = "";
	private String chassi = "";
	private String renavam = "";
	private String telefoneFixo = "";
	private String telefoneCelular = "";
	private String email = "";
	
	public Tabela(int id, String opcaoSexo, boolean mascaraCpf, boolean mascaraCnpj, boolean mascaraTelefoneFixo, 
			      boolean mascaraTelefoneCelular, boolean mascaraPlaca, boolean mascaraRenavam) 
	{
		super();
		
		setId(id);
		
		if ("Aleatório".equals(opcaoSexo))
		{
			String[] sexoOpcoes = { "Feminino", "Masculino" };

			int c = (int) Math.floor(Math.random() * sexoOpcoes.length);
			setSexo(sexoOpcoes[c]);
			
			if ("Feminino".equals(getSexo())) 
			{
				setNome(gerarNomeSobrenome.gerarNome("F"));			
				
			} else if ("Masculino".equals(getSexo())) 
			{
				setNome(gerarNomeSobrenome.gerarNome("M"));
			}		
		} 
		else if ("Masculino".equals(opcaoSexo))
		{
			setSexo(opcaoSexo);
			setNome(gerarNomeSobrenome.gerarNome("M"));
		} 
		else if ("Feminino".equals(opcaoSexo))
		{
			setSexo(opcaoSexo);
			setNome(gerarNomeSobrenome.gerarNome("F"));
		}
		
		setSobrenome(gerarNomeSobrenome.gerarSobrenome());
		setDataNascimento(geradorDataNascimento.geraDataNascimento());
		setCpf(geradorCpfCnpj.cpf(mascaraCpf));
		setCnpj(geradorCpfCnpj.cnpj(mascaraCnpj));
		geradorTelefone.gerarTelefones();
		setTelefoneFixo(geradorTelefone.getFixoGerado(mascaraTelefoneFixo));	
		setTelefoneCelular(geradorTelefone.getCelularGerado(mascaraTelefoneCelular));
		setEmail(geradorEmail.gerarEmail(getNome(), getSobrenome()));
		setPlaca(geradorPlaca.gerarPlaca(mascaraPlaca));
		setChassi(geradorChassi.gerarChassi());
		setRenavam(geradorRenavam.geraNumeroRenavamValido(mascaraRenavam));
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getChassi() {
		return chassi;
	}

	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	public String getRenavam() {
		return renavam;
	}

	public void setRenavam(String renavam) {
		this.renavam = renavam;
	}

	public String getTelefoneFixo() {
		return telefoneFixo;
	}

	public void setTelefoneFixo(String telefoneFixo) {
		this.telefoneFixo = telefoneFixo;
	}

	public String getTelefoneCelular() {
		return telefoneCelular;
	}

	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Object[] linhaTabela() 
	{
		Object[] linha = new Object[13];
		//"ID", "NOME", "SOBRENOME", "DT NASC.", "SEXO", "CPF", "CNPJ","TEL. FIXO", 
		//"CELULAR", "E-MAIL", "PLACA","CHASSI", "RENAVAM"
				
		linha[0] = getId();
		linha[1] = getNome();
		linha[2] = getSobrenome();
		linha[3] = getDataNascimento();
		linha[4] = getSexo();
		linha[5] = getCpf();
		linha[6] = getCnpj();
		linha[7] = getTelefoneFixo();
		linha[8] = getTelefoneCelular();
		linha[9] = getEmail();
		linha[10] = getPlaca();
		linha[11] = getChassi();
		linha[12] = getRenavam();
		
		return linha;
	}
}
