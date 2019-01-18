package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.io.IOException;

import visao.TelaPrincipal;

public class ControleTelaPrincipal implements ActionListener, WindowListener, WindowStateListener, ComponentListener {

	private TelaPrincipal telaPrincipal;

	public ControleTelaPrincipal(TelaPrincipal telaPrincipal) {
		super();
		this.telaPrincipal = telaPrincipal;
		this.telaPrincipal.modoInicial();
		this.telaPrincipal.setOuvintes(this);
		this.telaPrincipal.setOuvintesTela(this);
		this.telaPrincipal.setOuvintesTela2(this);
		this.telaPrincipal.setOuvintesComponente(this);
	}

	public void actionPerformed(ActionEvent evento) {
		int abaSelecionada = telaPrincipal.abaSelecionada();
		String comando = evento.getActionCommand();

		if ("Gerar".equals(comando)) {
			switch (abaSelecionada) {
			case 0:
				telaPrincipal.escreverListaTabela();
				break;
			case 1:
				String comboSelecionado = telaPrincipal.comboSelecionado();
				if ("Renavam".equals(comboSelecionado))
					telaPrincipal.escreverListaRenavam();
				else if ("CPF".equals(comboSelecionado))
					telaPrincipal.escreverListaCpf();
				else if ("CNPJ".equals(comboSelecionado))
					telaPrincipal.escreverListaCnpj();
				else if ("Placa".equals(comboSelecionado))
					telaPrincipal.escreverListaPlaca();
				else if ("Chassi".equals(comboSelecionado))
					telaPrincipal.escreverListaChassi();
				else if ("Data de Nascimento".equals(comboSelecionado))
					telaPrincipal.escreverListaDataNascimento();
				else if ("Nome".equals(comboSelecionado))
					telaPrincipal.escreverListaNomeSobrenome("Nome");
				else if ("Sobrenome".equals(comboSelecionado))
					telaPrincipal.escreverListaSobrenome();
				else if ("Nome + Sobrenome".equals(comboSelecionado))
					telaPrincipal.escreverListaNomeSobrenome("Nome + Sobrenome");
				else if ("E-mail".equals(comboSelecionado))
					telaPrincipal.escreverListaEmail();
				else if ("Telefone Fixo".equals(comboSelecionado))
					telaPrincipal.escreverListaTelefoneFixo();
				else if ("Telefone Celular".equals(comboSelecionado))
					telaPrincipal.escreverListaTelefoneCelular();
				break;
			default:
				System.out.println("Opção inválida!");
			}
		} else if ("Limpar".equals(comando)) {
			switch (abaSelecionada) {
			case 0:
				telaPrincipal.limparTabela();
				break;
			case 1:
				telaPrincipal.limparLista();
				break;
			default:
				System.out.println("Opção inválida!");
			}
		} else if ("Exportar".equals(comando)) {
			switch (abaSelecionada) {
			case 0:
				try {
					if (telaPrincipal.qtdLinhasTabela() > 0) {
						telaPrincipal.exportarExcelTabela();
					} else {
						telaPrincipal.escreverMensagemAviso("Favor gerar os dados para exportação!");
						telaPrincipal.focoTxtQtd();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case 1:
				try {
					if (!"".equals(telaPrincipal.textoLista())) {
						telaPrincipal.exportarExcelLista();
					} else {
						telaPrincipal.escreverMensagemAviso("Favor gerar os dados para exportação!");
						telaPrincipal.focoTxtQtd();
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			default:
				System.out.println("Opção inválida!");
			}
		} else if ("Fechar".equals(comando)) {
			System.exit(0);
		} else if ("Renavam".equals(comando) || "CPF".equals(comando) || "CNPJ".equals(comando)
				|| "Placa".equals(comando) || "Chassi".equals(comando) || "Nome".equals(comando)
				|| "Sobrenome".equals(comando) || "Nome + Sobrenome".equals(comando) || "E-mail".equals(comando)
				|| "Telefone Fixo".equals(comando) || "Telefone Celular".equals(comando)
				|| "Data de Nascimento".equals(comando)) {
			telaPrincipal.validarDocumentoSelecionadoAbaIndividual();
		} else if ("CPF2".equals(comando) || "CNPJ2".equals(comando)) {
			telaPrincipal.validarDocumentoSelecionadoAbaGeral();
		} else if ("Sexo Aleatório".equals(comando) || "Sexo Masculino".equals(comando) || "Sexo Feminino".equals(comando))
		{
			telaPrincipal.limparTabela();
			telaPrincipal.focoTxtQtd();
		} 
		else if ("Máscara Tabela CPF".equals(comando))
		{
			telaPrincipal.mascaraTabelaCPF();
			telaPrincipal.focoTxtQtd();
		}
		else if ("Máscara Tabela CNPJ".equals(comando))
		{
			telaPrincipal.mascaraTabelaCNPJ();
			telaPrincipal.focoTxtQtd();
		}
		else if ("Máscara Tabela Telefone Fixo".equals(comando))
		{
			telaPrincipal.mascaraTabelaTelefoneFixo();
			telaPrincipal.focoTxtQtd();
		}
		else if ("Máscara Tabela Telefone Celular".equals(comando))
		{
			telaPrincipal.mascaraTabelaTelefoneCelular();
			telaPrincipal.focoTxtQtd();
		}
		else if ("Máscara Tabela Placa".equals(comando))
		{
			telaPrincipal.mascaraTabelaPlaca();
			telaPrincipal.focoTxtQtd();
		}
		else if ("Máscara Tabela Renavam".equals(comando))
		{
			telaPrincipal.mascaraTabelaRenavam();
			telaPrincipal.focoTxtQtd();
		}
		else if ("Máscara Tabela Tudo".equals(comando))
		{
			telaPrincipal.validarMascaraTabelaTodos();
			telaPrincipal.focoTxtQtd();
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {

	}

	@Override
	public void windowIconified(WindowEvent e) {

	}

	@Override
	public void windowDeiconified(WindowEvent e) {

	}

	@Override
	public void windowDeactivated(WindowEvent e) {

	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}

	@Override
	public void windowClosed(WindowEvent e) {

	}

	@Override
	public void windowActivated(WindowEvent e) {

	}

	@Override
	public void windowStateChanged(WindowEvent paramWindowEvent) {
		telaPrincipal.centralizarPainel();
	}

	@Override
	public void componentResized(ComponentEvent paramComponentEvent) {
		telaPrincipal.centralizarPainel();
	}

	@Override
	public void componentMoved(ComponentEvent paramComponentEvent) {
		
	}

	@Override
	public void componentShown(ComponentEvent paramComponentEvent) {
		
	}

	@Override
	public void componentHidden(ComponentEvent paramComponentEvent) {
		
	}
}
