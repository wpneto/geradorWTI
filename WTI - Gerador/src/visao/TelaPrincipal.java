package visao;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import modelo.Tabela;
import util.GeradorChassi;
import util.GeradorCpfCnpj;
import util.GeradorDataNascimento;
import util.GeradorEmail;
import util.GeradorNomeSobrenome;
import util.GeradorPlaca;
import util.GeradorRenavam;
import util.GeradorTelefone;
import util.SomenteNumeros;

public class TelaPrincipal extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JLabel lblQtd = null, lblLista = null, lblImagemSancor = null, lblDocumento = null, lblVeiculo = null,
			lblTipoDocSelecionado = null, lblDesenvolvido = null, lblSistemaOperacional = null;
	private SomenteNumeros txtQtd = null;
	private JButton btnGerar = null, btnLimpar = null, btnFechar = null, btnExportar = null;
	private JTextArea txtLista = null;
	private JScrollPane sclLista = null;
	private JRadioButton rdoCpf = null, rdoCnpj = null, rdoRenavam = null, rdoPlaca = null, rdoChassi = null;
	private JRadioButton rdoNome = null, rdoSobrenome = null, rdoNomeSobrenome = null, rdoEmail = null, rdoDataNascimento = null;
	private JRadioButton rdoSexoAleatorio = null, rdoSexoFeminino = null, rdoSexoMasculino = null;
	private JRadioButton rdoTelefoneFixo = null, rdoTelefoneCelular = null;
	private ButtonGroup grpTipoDocumento = null, grpSexoTabela = null;
	private JLabel lblDocumentoSelecionado = null, lblPessoa = null, lblSexo = null;
	private JPanel pnlDadosIndividual = null, pnlDadosAgrupados = null;
	private JTabbedPane tabPrincipal = null;
	private JTable tblLista = null;
	private JPanel pnlFundo = null, pnlSexoTabela = null, pnlMascara = null;
	private JCheckBox chkMascaraTabelaCpf = null, chkMascaraTabelaCnpj = null, chkMascaraTabelaTelefoneFixo = null, chkMascaraLista = null;
	private JCheckBox chkMascaraTabelaTelefoneCelular = null, chkMascaraTabelaPlaca = null, chkMascaraTabelaRenavam = null, chkMascaraTabelaTudo = null;
	private DefaultTableModel modTabela = null;
	private String[][] dadosTblLista = new String[][] {};
	private String[] colunasTblLista = new String[] { "ID", "NOME", "SOBRENOME", "DT NASC.", "SEXO", "CPF", "CNPJ","TEL. FIXO", 
			"CELULAR", "E-MAIL", "PLACA","CHASSI", "RENAVAM" };
	private JScrollPane sclTabelaTblLista = null;
	private JComboBox<String> cboSexo = null;

	public TelaPrincipal() 
	{	
		super();
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.err.println("Não foi possível alterar o LookAndFeel");
			e.printStackTrace();
		}
		getContentPane().setFont(new Font("Dialog", Font.PLAIN, 14));
		initialize();
		centralizarPainel();
	}
	
	private void initialize() 
	{
		Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
		int largura = (int) tela.getWidth();
		int altura = (int) tela.getHeight();
		this.setSize(largura, altura);		
		this.setSize(1366, 732);
		this.setMinimumSize(new Dimension(1366, 732));
		this.setPreferredSize(new Dimension(1366, 732));
		this.setTitle("WTI :: Gerador de Massa de Dados para Teste - v1.6");
		this.getContentPane().add(getPnlFundo());
		this.setLocationRelativeTo(null);
		//this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(null); 
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.getRootPane().setDefaultButton(getBtnGerar());		
	}

	public void centralizarPainel(){
		// obter dimensões do pai
		int larguraPai = this.getWidth()-15;
		int alturaPai = this.getHeight()-40;
		
		//getPnlFundo().setSize(larguraPai-40, alturaPai-40);
		
		// obter dimensões do filho
		int larguraFilho = getPnlFundo().getWidth();
		int alturaFilho = getPnlFundo().getHeight();
		
		// calcular novas coordenadas do filho  
		int novoX = (larguraPai - larguraFilho);
		int novoY = (alturaPai - alturaFilho);

		// centralizar filho
		//filho.getParent().setLayout(new GridBagLayout());
		if (novoX < 0)
		{
			novoX = 0;
		}
		if (novoY < 0)
		{
			novoY = 0;
		}
		
		getPnlFundo().setLocation(novoX/2, novoY/2);
		getPnlFundo().repaint();		
	}
	
	private JPanel getPnlFundo() 
	{
		if (pnlFundo == null) 
		{
			pnlFundo = new JPanel();
			pnlFundo.setBorder(new LineBorder(new Color(255, 127, 39)));
			pnlFundo.setSize(1366, 705);
			pnlFundo.setLocation(0, 0);
			pnlFundo.setLayout(null);			
			pnlFundo.add(getTabPrincipal());
			pnlFundo.add(getBtnGerar());
			pnlFundo.add(getBtnLimpar());
			pnlFundo.add(getBtnFechar());
			pnlFundo.add(getBtnExportar());
			pnlFundo.add(getTxtQtd());
			pnlFundo.add(getLblImagemSancor());
			pnlFundo.add(getLblQtd());
			pnlFundo.add(getLblDesenvolvido());
			pnlFundo.add(getLblSistemaOperacional());			
			pnlFundo.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{getTxtQtd(), getBtnGerar(), getBtnLimpar(), getBtnExportar(), getBtnFechar(), getRdoSexoMasculino(), getRdoSexoAleatorio(), getRdoSexoFeminino()}));
			setGrpTipoDocumento();
			setGrpSexoTabela();
			modoInicial();
			setFocusTraversalPolicy(new FocusTraversalOnArray(
					new Component[] { getTxtQtd(), getBtnGerar(), getBtnLimpar(), getBtnExportar(), getBtnFechar(), getRdoSexoMasculino(), getRdoSexoAleatorio(), getRdoSexoFeminino()}));
			setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagens/icone_wilsonti.png")));
		}
		return pnlFundo;
	}

	private JTabbedPane getTabPrincipal() {
		if (tabPrincipal == null) {
			tabPrincipal = new JTabbedPane(JTabbedPane.TOP);
			tabPrincipal.setFont(new Font("Verdana", Font.BOLD, 12));
			tabPrincipal.setBounds(2, 84, 1360, 503);
			tabPrincipal.add("Dados Agrupados", getPnlDadosAgrupados());
			tabPrincipal.add("Dados Individuais", getPnlDadosIndividual());
		}
		return tabPrincipal;
	}

	private JPanel getPnlDadosIndividual() {
		if (pnlDadosIndividual == null) {
			pnlDadosIndividual = new JPanel();
			pnlDadosIndividual.setLayout(null);
			pnlDadosIndividual.setLocation(0, 0);
			pnlDadosIndividual.setSize(564, 511);
			pnlDadosIndividual.add(getLblLista());
			pnlDadosIndividual.add(getSclLista());
			pnlDadosIndividual.add(getRdoRenavam());
			pnlDadosIndividual.add(getRdoChassi());
			pnlDadosIndividual.add(getRdoCpf());
			pnlDadosIndividual.add(getRdoCnpj());
			pnlDadosIndividual.add(getRdoPlaca());
			pnlDadosIndividual.add(getRdoNome());
			pnlDadosIndividual.add(getRdoSobrenome());
			pnlDadosIndividual.add(getRdoDataNascimento());
			pnlDadosIndividual.add(getRdoEmail());
			pnlDadosIndividual.add(getRdoNomeSobrenome());
			pnlDadosIndividual.add(getRdoTelefoneFixo());
			pnlDadosIndividual.add(getRdoTelefoneCelular());
			pnlDadosIndividual.add(getLblDocumentoSelecionado());
			pnlDadosIndividual.add(getLblDocumento());
			pnlDadosIndividual.add(getLblPessoa());
			pnlDadosIndividual.add(getLblVeiculo());
			pnlDadosIndividual.add(getLblSexo());
			pnlDadosIndividual.add(getLblTipoDocSelecionado());
			pnlDadosIndividual.add(getChkMascaraLista());
			pnlDadosIndividual.add(getCboSexo());
			pnlDadosIndividual.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{getRdoCpf(), getRdoCnpj(), getRdoNome(), getRdoNomeSobrenome(), getCboSexo(), getRdoSobrenome(), getRdoEmail(), getRdoTelefoneFixo(), getRdoTelefoneCelular(), getRdoDataNascimento(), getRdoPlaca(), getRdoChassi(), getRdoRenavam(), getLblDocumentoSelecionado()}));
		}
		return pnlDadosIndividual;
	}

	private JComboBox<String> getCboSexo() {
		if (cboSexo == null) {
			cboSexo = new JComboBox<String>();
			cboSexo.setFont(new Font("Verdana", Font.BOLD, 12));
			cboSexo.setEnabled(false);
			cboSexo.setBounds(304, 163, 154, 25);
			cboSexo.addItem("");
			cboSexo.addItem("Aleatório");
			cboSexo.addItem("Feminino");
			cboSexo.addItem("Masculino");
		}
		return cboSexo;
	}
	
	private JPanel getPnlSexoTabela() {
		if (pnlSexoTabela == null) {
			pnlSexoTabela = new JPanel();
			pnlSexoTabela.setLayout(null);
			pnlSexoTabela.setLocation(0, 0);
			pnlSexoTabela.setSize(200, 200);
			pnlSexoTabela.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Sexo", TitledBorder.CENTER, TitledBorder.TOP, new Font("Verdana", Font.BOLD, 12), new Color(0, 0, 0)));
			pnlSexoTabela.setBounds(0, 0, 547, 45);
			pnlSexoTabela.setLayout(null);
			pnlSexoTabela.add(getRdoSexoAleatorio());
			pnlSexoTabela.add(getRdoSexoMasculino());
			pnlSexoTabela.add(getRdoSexoFeminino());
			pnlSexoTabela.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{getRdoSexoMasculino(), getRdoSexoAleatorio(), getRdoSexoFeminino()}));
		}
		return pnlSexoTabela;
	}
	
	private JPanel getPnlMascara() {
		if (pnlMascara == null) {
			pnlMascara = new JPanel();
			pnlMascara.setLayout(null);
			pnlMascara.setLocation(0, 0);
			pnlMascara.setSize(200, 200);
			pnlMascara.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Máscara de Campos", TitledBorder.CENTER, TitledBorder.TOP, new Font("Verdana", Font.BOLD, 12), new Color(0, 0, 0)));
			pnlMascara.setBounds(548, 0, 807, 45);
			pnlMascara.setLayout(null);
			pnlMascara.add(getChkMascaraTabelaCpf());
			pnlMascara.add(getChkMascaraTabelaCnpj());
			pnlMascara.add(getChkMascaraTabelaTelefoneFixo());
			pnlMascara.add(getChkMascaraTabelaTelefoneCelular());
			pnlMascara.add(getChkMascaraTabelaPlaca());
			pnlMascara.add(getChkMascaraTabelaRenavam());
			pnlMascara.add(getChkMascaraTabelaTudo());
			pnlMascara.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{getChkMascaraTabelaCpf(),getChkMascaraTabelaCnpj(), getChkMascaraTabelaTelefoneFixo(),
					getChkMascaraTabelaTelefoneCelular(),getChkMascaraTabelaPlaca(), getChkMascaraTabelaRenavam()}));
		}
		return pnlMascara;
	}
	
	private JCheckBox getChkMascaraTabelaCpf()
	{
		if (chkMascaraTabelaCpf == null)
		{
			chkMascaraTabelaCpf = new JCheckBox("CPF");
			chkMascaraTabelaCpf.setHorizontalAlignment(SwingConstants.CENTER);
			chkMascaraTabelaCpf.setForeground(Color.BLACK);
			chkMascaraTabelaCpf.setFont(new Font("Verdana", Font.BOLD, 12));
			chkMascaraTabelaCpf.setBounds(123, 18, 70, 20);
			chkMascaraTabelaCpf.setActionCommand("Máscara Tabela CPF");
		}
		return chkMascaraTabelaCpf;
	}
	
	private JCheckBox getChkMascaraTabelaCnpj()
	{
		if (chkMascaraTabelaCnpj == null)
		{
			chkMascaraTabelaCnpj = new JCheckBox("CNPJ");
			chkMascaraTabelaCnpj.setHorizontalAlignment(SwingConstants.CENTER);
			chkMascaraTabelaCnpj.setForeground(Color.BLACK);
			chkMascaraTabelaCnpj.setFont(new Font("Verdana", Font.BOLD, 12));
			chkMascaraTabelaCnpj.setBounds(226, 18, 70, 20);
			chkMascaraTabelaCnpj.setActionCommand("Máscara Tabela CNPJ");
		}
		return chkMascaraTabelaCnpj;
	}
	
	private JCheckBox getChkMascaraTabelaTelefoneFixo()
	{
		if (chkMascaraTabelaTelefoneFixo == null)
		{
			chkMascaraTabelaTelefoneFixo = new JCheckBox("Tel. Fixo");
			chkMascaraTabelaTelefoneFixo.setHorizontalAlignment(SwingConstants.CENTER);
			chkMascaraTabelaTelefoneFixo.setForeground(Color.BLACK);
			chkMascaraTabelaTelefoneFixo.setFont(new Font("Verdana", Font.BOLD, 12));
			chkMascaraTabelaTelefoneFixo.setBounds(335, 18, 90, 20);
			chkMascaraTabelaTelefoneFixo.setActionCommand("Máscara Tabela Telefone Fixo");
		}
		return chkMascaraTabelaTelefoneFixo;
	}
	
	private JCheckBox getChkMascaraTabelaTelefoneCelular()
	{
		if (chkMascaraTabelaTelefoneCelular == null)
		{
			chkMascaraTabelaTelefoneCelular = new JCheckBox("Tel. Celular");
			chkMascaraTabelaTelefoneCelular.setHorizontalAlignment(SwingConstants.CENTER);
			chkMascaraTabelaTelefoneCelular.setForeground(Color.BLACK);
			chkMascaraTabelaTelefoneCelular.setFont(new Font("Verdana", Font.BOLD, 12));
			chkMascaraTabelaTelefoneCelular.setBounds(463, 18, 110, 20);
			chkMascaraTabelaTelefoneCelular.setActionCommand("Máscara Tabela Telefone Celular");
		}
		return chkMascaraTabelaTelefoneCelular;
	}
	
	private JCheckBox getChkMascaraTabelaPlaca()
	{
		if (chkMascaraTabelaPlaca == null)
		{
			chkMascaraTabelaPlaca = new JCheckBox("Placa");
			chkMascaraTabelaPlaca.setHorizontalAlignment(SwingConstants.CENTER);
			chkMascaraTabelaPlaca.setForeground(Color.BLACK);
			chkMascaraTabelaPlaca.setFont(new Font("Verdana", Font.BOLD, 12));
			chkMascaraTabelaPlaca.setBounds(599, 18, 70, 20);
			chkMascaraTabelaPlaca.setActionCommand("Máscara Tabela Placa");
		}
		return chkMascaraTabelaPlaca;
	}
	
	private JCheckBox getChkMascaraTabelaRenavam()
	{
		if (chkMascaraTabelaRenavam == null)
		{
			chkMascaraTabelaRenavam = new JCheckBox("Renavam");
			chkMascaraTabelaRenavam.setHorizontalAlignment(SwingConstants.CENTER);
			chkMascaraTabelaRenavam.setForeground(Color.BLACK);
			chkMascaraTabelaRenavam.setFont(new Font("Verdana", Font.BOLD, 12));
			chkMascaraTabelaRenavam.setBounds(687, 18, 90, 20);
			chkMascaraTabelaRenavam.setActionCommand("Máscara Tabela Renavam");
		}
		return chkMascaraTabelaRenavam;
	}
	
	private JCheckBox getChkMascaraTabelaTudo()
	{
		if (chkMascaraTabelaTudo == null)
		{
			chkMascaraTabelaTudo = new JCheckBox("TODOS");
			chkMascaraTabelaTudo.setHorizontalAlignment(SwingConstants.CENTER);
			chkMascaraTabelaTudo.setForeground(Color.RED);
			chkMascaraTabelaTudo.setFont(new Font("Verdana", Font.BOLD, 12));
			chkMascaraTabelaTudo.setBounds(18, 18, 90, 20);
			chkMascaraTabelaTudo.setActionCommand("Máscara Tabela Tudo");
		}
		return chkMascaraTabelaTudo;
	}
	
	private JCheckBox getChkMascaraLista()
	{
		if (chkMascaraLista == null)
		{
			chkMascaraLista = new JCheckBox("M\u00E1scara de Campos");
			chkMascaraLista.setHorizontalAlignment(SwingConstants.LEFT);
			chkMascaraLista.setForeground(new Color(128, 0, 128));
			chkMascaraLista.setFont(new Font("Verdana", Font.BOLD, 12));
			chkMascaraLista.setBounds(10, 45, 200, 25);
		}
		return chkMascaraLista;
	}

	private JPanel getPnlDadosAgrupados() {
		if (pnlDadosAgrupados == null) {
			pnlDadosAgrupados = new JPanel();
			pnlDadosAgrupados.setLayout(null);
			pnlDadosAgrupados.setLocation(0, 0);
			pnlDadosAgrupados.setSize(564, 511);
			pnlDadosAgrupados.add(getSclTabela());			
			pnlDadosAgrupados.add(getPnlSexoTabela());
			pnlDadosAgrupados.add(getPnlMascara());
			pnlDadosAgrupados.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{getRdoSexoMasculino(), getRdoSexoAleatorio(), getRdoSexoFeminino()}));
		}
		return pnlDadosAgrupados;
	}

	private JLabel getLblDocumento() {
		if (lblDocumento == null) {
			lblDocumento = new JLabel("Documento");
			lblDocumento.setForeground(Color.BLUE);
			lblDocumento.setHorizontalAlignment(SwingConstants.LEFT);
			lblDocumento.setFont(new Font("Verdana", Font.BOLD, 12));
			lblDocumento.setBounds(10, 79, 200, 25);
		}
		return lblDocumento;
	}
	
	private JLabel getLblDesenvolvido() {
		if (lblDesenvolvido == null) {
			lblDesenvolvido = new JLabel("Desenvolvido por: Wilson Pinheiro / Skype: wilson.ugf");
			lblDesenvolvido.setFont(new Font("Verdana", Font.BOLD, 11));
			lblDesenvolvido.setHorizontalAlignment(SwingConstants.RIGHT);
			lblDesenvolvido.setBounds(908, 680, 448, 20);
		}
		return lblDesenvolvido;
	}
	
	private JLabel getLblSistemaOperacional() {
		if (lblSistemaOperacional == null) {
			lblSistemaOperacional = new JLabel("Sistema Operacional: "+System.getProperty("os.name")+ " - "+ System.getProperty("os.arch") +" | Versão Java: "+System.getProperty("java.version")+" | Usuário: "+System.getProperty("user.name"));
			lblSistemaOperacional.setFont(new Font("Verdana", Font.BOLD, 11));
			lblSistemaOperacional.setHorizontalAlignment(SwingConstants.LEFT);
			lblSistemaOperacional.setBounds(10, 680, 892, 20);
		}
		return lblSistemaOperacional;
	}

	private JLabel getLblPessoa() {
		if (lblPessoa == null) {
			lblPessoa = new JLabel("Pessoa");
			lblPessoa.setForeground(new Color(0, 128, 0));
			lblPessoa.setHorizontalAlignment(SwingConstants.LEFT);
			lblPessoa.setFont(new Font("Verdana", Font.BOLD, 12));
			lblPessoa.setBounds(218, 79, 240, 25);
		}
		return lblPessoa;
	}
	
	private JLabel getLblSexo() {
		if (lblSexo == null) {
			lblSexo = new JLabel("Sexo");
			lblSexo.setForeground(Color.BLACK);
			lblSexo.setHorizontalAlignment(SwingConstants.LEFT);
			lblSexo.setFont(new Font("Verdana", Font.BOLD, 12));
			lblSexo.setBounds(218, 163, 76, 25);
		}
		return lblSexo;
	}

	private JLabel getLblVeiculo() {
		if (lblVeiculo == null) {
			lblVeiculo = new JLabel("Veículo");
			lblVeiculo.setForeground(new Color(139, 0, 0));
			lblVeiculo.setHorizontalAlignment(SwingConstants.LEFT);
			lblVeiculo.setFont(new Font("Verdana", Font.BOLD, 12));
			lblVeiculo.setBounds(519, 79, 200, 25);
		}
		return lblVeiculo;
	}

	private void setGrpTipoDocumento() {
		if (grpTipoDocumento == null) {
			grpTipoDocumento = new ButtonGroup();
			grpTipoDocumento.add(getRdoRenavam());
			grpTipoDocumento.add(getRdoCpf());
			grpTipoDocumento.add(getRdoCnpj());
			grpTipoDocumento.add(getRdoPlaca());
			grpTipoDocumento.add(getRdoChassi());
			grpTipoDocumento.add(getRdoNome());
			grpTipoDocumento.add(getRdoSobrenome());
			grpTipoDocumento.add(getRdoNomeSobrenome());
			grpTipoDocumento.add(getRdoEmail());
			grpTipoDocumento.add(getRdoTelefoneFixo());
			grpTipoDocumento.add(getRdoTelefoneCelular());
			grpTipoDocumento.add(getRdoDataNascimento());
		}
	}
	
	private void setGrpSexoTabela() {
		if (grpSexoTabela == null) {
			grpSexoTabela = new ButtonGroup();
			grpSexoTabela.add(getRdoSexoAleatorio());
			grpSexoTabela.add(getRdoSexoFeminino());
			grpSexoTabela.add(getRdoSexoMasculino());			
		}
	}

	private JLabel getLblImagemSancor() {
		if (lblImagemSancor == null) {
			lblImagemSancor = new JLabel("");
			lblImagemSancor.setFont(new Font("Verdana", Font.PLAIN, 11));
			lblImagemSancor.setHorizontalAlignment(SwingConstants.LEFT);
			lblImagemSancor.setBounds(10, 8, 325, 72);
			lblImagemSancor.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/imagens/wilsonti_logo_novo.png")));
		}
		return lblImagemSancor;
	}

	private JButton getBtnLimpar() {
		if (btnLimpar == null) {
			btnLimpar = new JButton("Limpar");
			btnLimpar.setFont(new Font("Verdana", Font.BOLD, 12));
			btnLimpar.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/imagens/delete.png")));
			btnLimpar.setBounds(519, 635, 140, 35);
			btnLimpar.setActionCommand("Limpar");
			btnLimpar.setMnemonic(KeyEvent.VK_L);
		}
		return btnLimpar;
	}

	private JButton getBtnFechar() {
		if (btnFechar == null) {
			btnFechar = new JButton("Fechar");
			btnFechar.setFont(new Font("Verdana", Font.BOLD, 12));
			btnFechar.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/imagens/cancel.png")));
			btnFechar.setBounds(838, 635, 140, 35);
			btnFechar.setActionCommand("Fechar");
			btnFechar.setMnemonic(KeyEvent.VK_F);
		}
		return btnFechar;
	}
	
	private JButton getBtnExportar() {
		if (btnExportar == null) {
			btnExportar = new JButton("Exportar");
			btnExportar.setFont(new Font("Verdana", Font.BOLD, 12));
			btnExportar.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/imagens/export.png")));
			btnExportar.setBounds(678, 635, 140, 35);
			btnExportar.setActionCommand("Exportar");
			btnExportar.setMnemonic(KeyEvent.VK_E);
		}
		return btnExportar;
	}

	private JButton getBtnGerar() {
		if (btnGerar == null) {
			btnGerar = new JButton("Gerar");
			btnGerar.setFont(new Font("Verdana", Font.BOLD, 12));
			btnGerar.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/imagens/accept.png")));
			btnGerar.setMnemonic(KeyEvent.VK_G);
			btnGerar.setBounds(359, 635, 140, 35);
			btnGerar.setActionCommand("Gerar");
		}
		return btnGerar;
	}

	private JLabel getLblLista() {
		if (lblLista == null) {
			lblLista = new JLabel("Lista");
			lblLista.setFont(new Font("Verdana", Font.BOLD, 12));
			lblLista.setBounds(853, 11, 485, 25);
		}
		return lblLista;
	}

	private JLabel getLblQtd() {
		if (lblQtd == null) {
			lblQtd = new JLabel("Quantidade");
			lblQtd.setForeground(new Color(0, 0, 0));
			lblQtd.setBounds(519, 595, 140, 25);
			lblQtd.setFont(new Font("Verdana", Font.BOLD, 12));
			lblQtd.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblQtd;
	}

	private JLabel getLblTipoDocSelecionado() {
		if (lblTipoDocSelecionado == null) {
			lblTipoDocSelecionado = new JLabel("Tipo Selecionado");
			lblTipoDocSelecionado.setFont(new Font("Verdana", Font.BOLD, 12));
			lblTipoDocSelecionado.setHorizontalAlignment(SwingConstants.LEFT);
			lblTipoDocSelecionado.setBounds(10, 11, 139, 25);
		}
		return lblTipoDocSelecionado;
	}

	private SomenteNumeros getTxtQtd() {
		if (txtQtd == null) {
			txtQtd = new SomenteNumeros(5);
			txtQtd.setBounds(678, 595, 140, 25);
			txtQtd.setFont(new Font("Verdana", Font.BOLD, 12));
			txtQtd.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return txtQtd;
	}

	private JTextArea getTxtLista() {
		if (txtLista == null) {
			txtLista = new JTextArea();
			txtLista.setForeground(Color.BLACK);
			txtLista.setFont(new Font("Verdana", Font.PLAIN, 13));
			txtLista.setEditable(false);
			txtLista.setRows(15);
			txtLista.setColumns(25);
			txtLista.setWrapStyleWord(true);
		}
		return txtLista;
	}

	private JLabel getLblDocumentoSelecionado() {
		if (lblDocumentoSelecionado == null) {
			lblDocumentoSelecionado = new JLabel("");
			lblDocumentoSelecionado.setHorizontalAlignment(SwingConstants.LEFT);
			lblDocumentoSelecionado.setForeground(Color.RED);
			lblDocumentoSelecionado.setFont(new Font("Verdana", Font.BOLD, 16));
			lblDocumentoSelecionado.setBounds(158, 11, 351, 25);
		}
		return lblDocumentoSelecionado;
	}

	private JRadioButton getRdoCpf() {
		if (rdoCpf == null) {
			rdoCpf = new JRadioButton("CPF");
			rdoCpf.setFont(new Font("Verdana", Font.BOLD, 12));
			rdoCpf.setToolTipText("");
			rdoCpf.setHorizontalAlignment(SwingConstants.LEFT);
			rdoCpf.setBounds(10, 105, 200, 25);
			rdoCpf.setActionCommand("CPF");
		}
		return rdoCpf;
	}

	private JRadioButton getRdoCnpj() {
		if (rdoCnpj == null) {
			rdoCnpj = new JRadioButton("CNPJ");
			rdoCnpj.setHorizontalAlignment(SwingConstants.LEFT);
			rdoCnpj.setFont(new Font("Verdana", Font.BOLD, 12));
			rdoCnpj.setBounds(10, 131, 200, 25);
			rdoCnpj.setActionCommand("CNPJ");
		}
		return rdoCnpj;
	}

	private JRadioButton getRdoNome() {
		if (rdoNome == null) {
			rdoNome = new JRadioButton("Nome");
			rdoNome.setHorizontalAlignment(SwingConstants.LEFT);
			rdoNome.setFont(new Font("Verdana", Font.BOLD, 12));
			rdoNome.setBounds(218, 105, 240, 25);
			rdoNome.setActionCommand("Nome");
		}
		return rdoNome;
	}

	private JRadioButton getRdoSobrenome() {
		if (rdoSobrenome == null) {
			rdoSobrenome = new JRadioButton("Sobrenome");
			rdoSobrenome.setHorizontalAlignment(SwingConstants.LEFT);
			rdoSobrenome.setFont(new Font("Verdana", Font.BOLD, 12));
			rdoSobrenome.setBounds(218, 195, 240, 25);
			rdoSobrenome.setActionCommand("Sobrenome");
		}
		return rdoSobrenome;
	}
	
	private JRadioButton getRdoDataNascimento() {
		if (rdoDataNascimento == null) {
			rdoDataNascimento = new JRadioButton("Data de Nascimento");
			rdoDataNascimento.setHorizontalAlignment(SwingConstants.LEFT);
			rdoDataNascimento.setFont(new Font("Verdana", Font.BOLD, 12));
			rdoDataNascimento.setBounds(218, 299, 240, 25);
			rdoDataNascimento.setActionCommand("Data de Nascimento");
		}
		return rdoDataNascimento;
	}
	
	private JRadioButton getRdoSexoMasculino() {
		if (rdoSexoMasculino == null) {
			rdoSexoMasculino = new JRadioButton("Masculino");
			rdoSexoMasculino.setForeground(Color.BLUE);
			rdoSexoMasculino.setHorizontalAlignment(SwingConstants.LEFT);
			rdoSexoMasculino.setFont(new Font("Verdana", Font.BOLD, 12));
			rdoSexoMasculino.setBounds(60, 18, 130, 20);
			rdoSexoMasculino.setActionCommand("Sexo Masculino");
		}
		return rdoSexoMasculino;
	}
	
	private JRadioButton getRdoSexoFeminino() {
		if (rdoSexoFeminino == null) {
			rdoSexoFeminino = new JRadioButton("Feminino");
			rdoSexoFeminino.setForeground(new Color(218, 112, 214));
			rdoSexoFeminino.setHorizontalAlignment(SwingConstants.LEFT);
			rdoSexoFeminino.setFont(new Font("Verdana", Font.BOLD, 12));
			rdoSexoFeminino.setBounds(395, 18, 130, 20);
			rdoSexoFeminino.setActionCommand("Sexo Feminino");
		}
		return rdoSexoFeminino;
	}
	
	private JRadioButton getRdoSexoAleatorio() {
		if (rdoSexoAleatorio == null) {
			rdoSexoAleatorio = new JRadioButton("Aleatório");
			rdoSexoAleatorio.setForeground(new Color(0, 128, 0));
			rdoSexoAleatorio.setHorizontalAlignment(SwingConstants.LEFT);
			rdoSexoAleatorio.setFont(new Font("Verdana", Font.BOLD, 12));
			rdoSexoAleatorio.setBounds(230, 18, 130, 20);
			rdoSexoAleatorio.setActionCommand("Sexo Aleatório");
		}
		return rdoSexoAleatorio;
	}

	private JRadioButton getRdoNomeSobrenome() {
		if (rdoNomeSobrenome == null) {
			rdoNomeSobrenome = new JRadioButton("Nome + Sobrenome");
			rdoNomeSobrenome.setHorizontalAlignment(SwingConstants.LEFT);
			rdoNomeSobrenome.setFont(new Font("Verdana", Font.BOLD, 12));
			rdoNomeSobrenome.setBounds(218, 131, 240, 25);
			rdoNomeSobrenome.setActionCommand("Nome + Sobrenome");
		}
		return rdoNomeSobrenome;
	}
	
	private JRadioButton getRdoEmail() {
		if (rdoEmail == null) {
			rdoEmail = new JRadioButton("E-mail");
			rdoEmail.setHorizontalAlignment(SwingConstants.LEFT);
			rdoEmail.setFont(new Font("Verdana", Font.BOLD, 12));
			rdoEmail.setBounds(218, 221, 240, 25);
			rdoEmail.setActionCommand("E-mail");
		}
		return rdoEmail;
	}

	private JRadioButton getRdoTelefoneFixo() {
		if (rdoTelefoneFixo == null) {
			rdoTelefoneFixo = new JRadioButton("Telefone Fixo");
			rdoTelefoneFixo.setHorizontalAlignment(SwingConstants.LEFT);
			rdoTelefoneFixo.setFont(new Font("Verdana", Font.BOLD, 12));
			rdoTelefoneFixo.setBounds(218, 247, 240, 25);
			rdoTelefoneFixo.setActionCommand("Telefone Fixo");
		}
		return rdoTelefoneFixo;
	}
	
	private JRadioButton getRdoTelefoneCelular() {
		if (rdoTelefoneCelular == null) {
			rdoTelefoneCelular = new JRadioButton("Telefone Celular");
			rdoTelefoneCelular.setHorizontalAlignment(SwingConstants.LEFT);
			rdoTelefoneCelular.setFont(new Font("Verdana", Font.BOLD, 12));
			rdoTelefoneCelular.setBounds(218, 273, 240, 25);
			rdoTelefoneCelular.setActionCommand("Telefone Celular");
		}
		return rdoTelefoneCelular;
	}	

	private JRadioButton getRdoRenavam() {
		if (rdoRenavam == null) {
			rdoRenavam = new JRadioButton("Renavam");
			rdoRenavam.setHorizontalAlignment(SwingConstants.LEFT);
			rdoRenavam.setFont(new Font("Verdana", Font.BOLD, 12));
			rdoRenavam.setBounds(519, 157, 200, 25);
			rdoRenavam.setActionCommand("Renavam");
		}
		return rdoRenavam;
	}

	private JRadioButton getRdoPlaca() {
		if (rdoPlaca == null) {
			rdoPlaca = new JRadioButton("Placa");
			rdoPlaca.setHorizontalAlignment(SwingConstants.LEFT);
			rdoPlaca.setFont(new Font("Verdana", Font.BOLD, 12));
			rdoPlaca.setBounds(519, 105, 200, 25);
			rdoPlaca.setActionCommand("Placa");
		}
		return rdoPlaca;
	}

	private JRadioButton getRdoChassi() {
		if (rdoChassi == null) {
			rdoChassi = new JRadioButton("Chassi");
			rdoChassi.setHorizontalAlignment(SwingConstants.LEFT);
			rdoChassi.setFont(new Font("Verdana", Font.BOLD, 12));
			rdoChassi.setBounds(519, 131, 200, 25);
			rdoChassi.setActionCommand("Chassi");
		}
		return rdoChassi;
	}

	private JScrollPane getSclLista() {
		if (sclLista == null) {
			sclLista = new JScrollPane(getTxtLista());
			sclLista.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			sclLista.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			sclLista.setBounds(853, 37, 485, 424);
		}
		return sclLista;
	}

	private JScrollPane getSclTabela() {
		if (sclTabelaTblLista == null) {
			sclTabelaTblLista = new JScrollPane(getTblLista());
			// sclTabelaTblLista.setViewportBorder(null);
			sclTabelaTblLista.setBounds(0, 46, 1355, 428);
			//sclTabelaTblLista.getViewport().setForeground(new Color(62,95,149));
			sclTabelaTblLista.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			sclTabelaTblLista.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			//sclTabelaTblLista.setBackground(new Color(62,95,149));
		}
		return sclTabelaTblLista;
	}

	private JTable getTblLista() {
		if (tblLista == null) {
			DefaultTableCellRenderer rendererCentro = new DefaultTableCellRenderer();
			rendererCentro.setHorizontalAlignment(SwingConstants.CENTER);

			modTabela = new DefaultTableModel(dadosTblLista, colunasTblLista) {
				private static final long serialVersionUID = 1L;

				public boolean isCellEditable(int rowIndex, int mColIndex) {
					return false;
				}
			};			
						
			tblLista = new JTable(modTabela);
			tblLista.setCellSelectionEnabled(true);
			JTableHeader cabecalho = tblLista.getTableHeader();
			cabecalho.setOpaque(true);
			cabecalho.setFont(new Font("Verdana", Font.BOLD, 11));
			cabecalho.setBorder(BorderFactory.createBevelBorder(0));
			cabecalho.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			
			tblLista.setShowGrid(true);

			tblLista.setBounds(10, 75, 519, 343);
			tblLista.setPreferredScrollableViewportSize(new Dimension(500, 100));// barra de rolagem
			tblLista.setFillsViewportHeight(true);
			tblLista.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			tblLista.setRowHeight(25);
			tblLista.setFont(new Font("Verdana", Font.PLAIN, 11));
			tblLista.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			tblLista.setSelectionBackground(new Color(62,95,149));

			DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tblLista.getTableHeader().getDefaultRenderer();
			renderer.setHorizontalAlignment(SwingConstants.CENTER);
			
			// centralizar os dados nas colunas
			// "ID","NOME","SOBRENOME","DT. NASC.","SEXO","CPF","CNPJ","TEL. FIXO","CELULAR","E-MAIL","PLACA","CHASSI","RENAVAM"
			TableColumnModel modeloDaColuna = tblLista.getColumnModel();
			
			for (int i = 0; i < tblLista.getColumnCount(); i++) {
				modeloDaColuna.getColumn(i).setCellRenderer(rendererCentro);
			}
			
			// tamanho das colunas
			tblLista.getColumnModel().getColumn(0).setPreferredWidth(50);
			tblLista.getColumnModel().getColumn(1).setPreferredWidth(108);
			tblLista.getColumnModel().getColumn(2).setPreferredWidth(118);
			tblLista.getColumnModel().getColumn(3).setPreferredWidth(80);
			tblLista.getColumnModel().getColumn(4).setPreferredWidth(70);
			tblLista.getColumnModel().getColumn(5).setPreferredWidth(90);
			tblLista.getColumnModel().getColumn(6).setPreferredWidth(110);
			tblLista.getColumnModel().getColumn(7).setPreferredWidth(85);
			tblLista.getColumnModel().getColumn(8).setPreferredWidth(90);
			tblLista.getColumnModel().getColumn(9).setPreferredWidth(235);
			tblLista.getColumnModel().getColumn(10).setPreferredWidth(65);
			tblLista.getColumnModel().getColumn(11).setPreferredWidth(145);
			tblLista.getColumnModel().getColumn(12).setPreferredWidth(90);
		}
		return tblLista;
	}

	public void limparLista() {
		getTxtLista().setText("");
	}

	public void limparTabela() {
		modTabela.setNumRows(0);
		modTabela.fireTableDataChanged();
	}
	
	public void validarCheckBox()
	{
		if (getChkMascaraTabelaCpf().isSelected() || getChkMascaraTabelaCnpj().isSelected() 
			|| getChkMascaraTabelaTelefoneFixo().isSelected() || getChkMascaraTabelaTelefoneCelular().isSelected()
			|| getChkMascaraTabelaPlaca().isSelected() || getChkMascaraTabelaRenavam().isSelected())
		{
			//"ID", "NOME", "SOBRENOME", "DT NASC.", "SEXO", "CPF", "CNPJ","TEL. FIXO","CELULAR", "E-MAIL", "PLACA","CHASSI", "RENAVAM"
			getTblLista().getColumnModel().getColumn(1).setPreferredWidth(100);
			getTblLista().getColumnModel().getColumn(2).setPreferredWidth(100);
			getTblLista().getColumnModel().getColumn(4).setPreferredWidth(65);
			getTblLista().getColumnModel().getColumn(5).setPreferredWidth(100);
			getTblLista().getColumnModel().getColumn(6).setPreferredWidth(124);
			getTblLista().getColumnModel().getColumn(7).setPreferredWidth(95);
			getTblLista().getColumnModel().getColumn(8).setPreferredWidth(105);
			getTblLista().getColumnModel().getColumn(9).setPreferredWidth(215);
			getTblLista().getColumnModel().getColumn(10).setPreferredWidth(70);
			getTblLista().getColumnModel().getColumn(11).setPreferredWidth(138);
			getTblLista().getColumnModel().getColumn(12).setPreferredWidth(94);
		}
		else
		{
			
			getTblLista().getColumnModel().getColumn(0).setPreferredWidth(50);
			getTblLista().getColumnModel().getColumn(1).setPreferredWidth(108);
			getTblLista().getColumnModel().getColumn(2).setPreferredWidth(118);
			getTblLista().getColumnModel().getColumn(3).setPreferredWidth(80);
			getTblLista().getColumnModel().getColumn(4).setPreferredWidth(70);
			getTblLista().getColumnModel().getColumn(5).setPreferredWidth(90);
			getTblLista().getColumnModel().getColumn(6).setPreferredWidth(110);
			getTblLista().getColumnModel().getColumn(7).setPreferredWidth(85);
			getTblLista().getColumnModel().getColumn(8).setPreferredWidth(90);
			getTblLista().getColumnModel().getColumn(9).setPreferredWidth(235);
			getTblLista().getColumnModel().getColumn(10).setPreferredWidth(65);
			getTblLista().getColumnModel().getColumn(11).setPreferredWidth(145);
			getTblLista().getColumnModel().getColumn(12).setPreferredWidth(90);
		}
	}
	
	public void mascaraTabelaCPF()
	{
		validarCheckBox();
		
		if (getChkMascaraTabelaCpf().isSelected() && getTblLista().getRowCount() > 0)
		{			
			for (int linha = 0; linha < getTblLista().getRowCount(); linha++) 
			{
				String cpfAntigo = modTabela.getValueAt(linha, 5).toString();
				
				if (cpfAntigo.length() == 11)
				{
					String cpfNovo = cpfAntigo.substring(0, 3) + "." + cpfAntigo.substring(3, 6) + "." + cpfAntigo.substring(6, 9) + "-" + cpfAntigo.substring(9, 11);
					modTabela.setValueAt(cpfNovo, linha, 5);
				}				
			}
		}
		else if (getTblLista().getRowCount() > 0)
		{			
			for (int linha = 0; linha < getTblLista().getRowCount(); linha++) 
			{
				String cpfAntigo = modTabela.getValueAt(linha, 5).toString();	
				
				if (cpfAntigo.length() == 14)
				{
					String cpfNovo = cpfAntigo.substring(0, 3) + cpfAntigo.substring(4, 7) + cpfAntigo.substring(8, 11) + cpfAntigo.substring(12, 14);
					modTabela.setValueAt(cpfNovo, linha, 5);
				}				
			}		
		}
	}
	
	public void mascaraTabelaCNPJ()
	{
		validarCheckBox();
		
		if (getChkMascaraTabelaCnpj().isSelected() && getTblLista().getRowCount() > 0)
		{			
			for (int linha = 0; linha < getTblLista().getRowCount(); linha++) 
			{
				String cnpjAntigo = modTabela.getValueAt(linha, 6).toString();
				if (cnpjAntigo.length() == 14)
				{									
					String cnpjNovo = cnpjAntigo.substring(0, 2) + "." + cnpjAntigo.substring(2, 5) + "." + cnpjAntigo.substring(5, 8) + "/" + cnpjAntigo.substring(8, 12) + "-" + cnpjAntigo.substring(12, 14);
					modTabela.setValueAt(cnpjNovo, linha, 6);
				}				
			}
		}
		else if (getTblLista().getRowCount() > 0)
		{			
			for (int linha = 0; linha < getTblLista().getRowCount(); linha++) 
			{
				String cnpjAntigo = modTabela.getValueAt(linha, 6).toString();	
				
				if (cnpjAntigo.length() == 18)
				{
					String cnpjNovo = cnpjAntigo.substring(0, 2) + cnpjAntigo.substring(3, 6) + cnpjAntigo.substring(7, 10) + cnpjAntigo.substring(11, 15) + cnpjAntigo.substring(16, 18);
					modTabela.setValueAt(cnpjNovo, linha, 6);
				}				
			}		
		}
	}
	
	public void mascaraTabelaTelefoneFixo()
	{
		validarCheckBox();
		
		if (getChkMascaraTabelaTelefoneFixo().isSelected() && getTblLista().getRowCount() > 0)
		{			
			for (int linha = 0; linha < getTblLista().getRowCount(); linha++) 
			{
				String telefoneFixoAntigo = modTabela.getValueAt(linha, 7).toString();
				
				if (telefoneFixoAntigo.length() == 10)
				{
					String telefoneFixoNovo = "(" + telefoneFixoAntigo.substring(0, 2) + ") " + telefoneFixoAntigo.substring(2, 6) + "-" + telefoneFixoAntigo.substring(6, 10);
					modTabela.setValueAt(telefoneFixoNovo, linha, 7);
				}				
			}
		}
		else if (getTblLista().getRowCount() > 0)
		{			
			for (int linha = 0; linha < getTblLista().getRowCount(); linha++) 
			{
				String telefoneFixoAntigo = modTabela.getValueAt(linha, 7).toString();
				
				if (telefoneFixoAntigo.length() == 14)
				{
					String telefoneFixoNovo = telefoneFixoAntigo.substring(1, 3) + telefoneFixoAntigo.substring(5, 9) + telefoneFixoAntigo.substring(10, 14);
					modTabela.setValueAt(telefoneFixoNovo, linha, 7);
				}				
			}		
		}
	}
	
	public void mascaraTabelaTelefoneCelular()
	{
		validarCheckBox();
		
		if (getChkMascaraTabelaTelefoneCelular().isSelected() && getTblLista().getRowCount() > 0)
		{			
			for (int linha = 0; linha < getTblLista().getRowCount(); linha++) 
			{
				String telefoneCelularAntigo = modTabela.getValueAt(linha, 8).toString();
				if (telefoneCelularAntigo.length() == 11)
				{
					String telefoneCelularNovo = "(" + telefoneCelularAntigo.substring(0, 2) + ") " + telefoneCelularAntigo.substring(2, 7) + "-" + telefoneCelularAntigo.substring(7, 11);
					modTabela.setValueAt(telefoneCelularNovo, linha, 8);
				}				
			}
		}
		else if (getTblLista().getRowCount() > 0)
		{			
			for (int linha = 0; linha < getTblLista().getRowCount(); linha++) 
			{
				String telefoneCelularAntigo = modTabela.getValueAt(linha, 8).toString();	
				if (telefoneCelularAntigo.length() == 15)
				{
					String telefoneCelularNovo = telefoneCelularAntigo.substring(1, 3) + telefoneCelularAntigo.substring(5, 10) + telefoneCelularAntigo.substring(11, 15);
					modTabela.setValueAt(telefoneCelularNovo, linha, 8);
				}
			}		
		}
	}
	
	public void mascaraTabelaPlaca()
	{
		validarCheckBox();
		
		if (getChkMascaraTabelaPlaca().isSelected() && getTblLista().getRowCount() > 0)
		{			
			for (int linha = 0; linha < getTblLista().getRowCount(); linha++) 
			{
				String placaAntiga = modTabela.getValueAt(linha, 10).toString();
				if (placaAntiga.length() == 7)
				{
					String placaNova = placaAntiga.substring(0, 3) + "-" + placaAntiga.substring(3, 7);
					modTabela.setValueAt(placaNova, linha, 10);
				}
			}
		}
		else if (getTblLista().getRowCount() > 0)
		{			
			for (int linha = 0; linha < getTblLista().getRowCount(); linha++) 
			{
				String placaAntiga = modTabela.getValueAt(linha, 10).toString();
				if (placaAntiga.length() == 8)
				{
					String telefoneCelularNovo = placaAntiga.substring(0, 3) + placaAntiga.substring(4, 8);
					modTabela.setValueAt(telefoneCelularNovo, linha, 10);
				}
			}		
		}
	}
	
	public void mascaraTabelaRenavam()
	{
		validarCheckBox();
		
		if (getChkMascaraTabelaRenavam().isSelected() && getTblLista().getRowCount() > 0)
		{			
			for (int linha = 0; linha < getTblLista().getRowCount(); linha++) 
			{
				String renavamAntigo = modTabela.getValueAt(linha, 12).toString();	
				if (renavamAntigo.length() == 11)
				{
					String renavamNovo = renavamAntigo.substring(0, 4) + "." + renavamAntigo.substring(4, 10) + "-" + renavamAntigo.substring(10,11);
					modTabela.setValueAt(renavamNovo, linha, 12);
				}
			}
		}
		else if (getTblLista().getRowCount() > 0)
		{			
			for (int linha = 0; linha < getTblLista().getRowCount(); linha++) 
			{
				String renavamAntigo = modTabela.getValueAt(linha, 12).toString();	
				if (renavamAntigo.length() == 13)
				{
					String renavamNovo = renavamAntigo.substring(0, 4) + renavamAntigo.substring(5, 11) + renavamAntigo.substring(12, 13);
					modTabela.setValueAt(renavamNovo, linha, 12);
				}
			}		
		}
	}

	public void escreverListaRenavam() {
		limparLista();

		String qtd = getTxtQtd().getText();

		if ("".equals(qtd) == true) {
			escreverMensagemAviso("Campo quantidade em branco!");
			getTxtQtd().requestFocus(true);
		} else {
			int qtdFinal = Integer.parseInt(qtd);
			GeradorRenavam gerador = new GeradorRenavam();
			for (int i = 0; i < qtdFinal; i++) {
				String renavam = gerador.geraNumeroRenavamValido(getChkMascaraLista().isSelected());
				getTxtLista().append(renavam + "\n");
			}
		}
	}
	
	public void escreverListaDataNascimento() {
		limparLista();

		String qtd = getTxtQtd().getText();

		if ("".equals(qtd) == true) {
			escreverMensagemAviso("Campo quantidade em branco!");
			getTxtQtd().requestFocus(true);
		} else {
			int qtdFinal = Integer.parseInt(qtd);
			GeradorDataNascimento gerador = new GeradorDataNascimento();
			for (int i = 0; i < qtdFinal; i++) {
				String dataNascimento = gerador.geraDataNascimento();
				getTxtLista().append(dataNascimento + "\n");
			}
		}
	}
	
	public void escreverListaEmail() {
		limparLista();

		String qtd = getTxtQtd().getText();

		if ("".equals(qtd) == true) {
			escreverMensagemAviso("Campo quantidade em branco!");
			getTxtQtd().requestFocus(true);
		} else {
			int qtdFinal = Integer.parseInt(qtd);

			GeradorEmail geradorEmail = new GeradorEmail();
			GeradorNomeSobrenome geradorNomeSobrenome = new GeradorNomeSobrenome();
			
			String[] sexoOpcoes = { "F", "M" };
			int c = (int) Math.floor(Math.random() * sexoOpcoes.length);
			String sexo = sexoOpcoes[c];

			for (int i = 0; i < qtdFinal; i++) {
				String email = geradorEmail.gerarEmail(geradorNomeSobrenome.gerarNome(sexo), geradorNomeSobrenome.gerarSobrenome());
				getTxtLista().append(email + "\n");
			}
		}
	}

	public void escreverListaCpf() {
		limparLista();

		String qtd = getTxtQtd().getText();

		if ("".equals(qtd) == true) {
			escreverMensagemAviso("Campo quantidade em branco!");
			getTxtQtd().requestFocus(true);
		} else {
			int qtdFinal = Integer.parseInt(qtd);

			GeradorCpfCnpj gerador = new GeradorCpfCnpj();

			for (int i = 0; i < qtdFinal; i++) {
				String cpf = gerador.cpf(getChkMascaraLista().isSelected());
				getTxtLista().append(cpf + "\n");
			}
		}
	}

	public void escreverListaTabela() {
		limparTabela();

		String qtd = getTxtQtd().getText();

		if ("".equals(qtd) == true) {
			escreverMensagemAviso("Campo quantidade em branco!");
			getTxtQtd().requestFocus(true);
		} 
		else 
		{
			int qtdFinal = Integer.parseInt(qtd);
			String sexoSelecionado = "";
			
			if (getRdoSexoAleatorio().isSelected())
				sexoSelecionado = "Aleatório";
			else if (getRdoSexoFeminino().isSelected())
				sexoSelecionado = "Feminino";
			else if (getRdoSexoMasculino().isSelected())
				sexoSelecionado = "Masculino";

			Tabela tabela = null;

			for (int i = 0; i < qtdFinal; i++) 
			{
				tabela = new Tabela(i + 1, sexoSelecionado, getChkMascaraTabelaCpf().isSelected(),
						getChkMascaraTabelaCnpj().isSelected(),getChkMascaraTabelaTelefoneFixo().isSelected(),
						getChkMascaraTabelaTelefoneCelular().isSelected(), getChkMascaraTabelaPlaca().isSelected(),
						getChkMascaraTabelaRenavam().isSelected());				
				modTabela.addRow(tabela.linhaTabela());
			}
		}		
	}

	public void escreverListaCnpj() {
		limparLista();

		String qtd = getTxtQtd().getText();

		if ("".equals(qtd) == true) {
			escreverMensagemAviso("Campo quantidade em branco!");
			getTxtQtd().requestFocus(true);
		} else {
			int qtdFinal = Integer.parseInt(qtd);

			GeradorCpfCnpj gerador = new GeradorCpfCnpj();

			for (int i = 0; i < qtdFinal; i++) {
				String cnpj = gerador.cnpj(getChkMascaraLista().isSelected());
				getTxtLista().append(cnpj + "\n");
			}
		}
	}

	public void escreverListaPlaca() {
		limparLista();

		String qtd = getTxtQtd().getText();

		if ("".equals(qtd) == true) {
			escreverMensagemAviso("Campo quantidade em branco!");
			getTxtQtd().requestFocus(true);
		} else {
			int qtdFinal = Integer.parseInt(qtd);

			GeradorPlaca gerador = new GeradorPlaca();

			for (int i = 0; i < qtdFinal; i++) {
				String placa = gerador.gerarPlaca(getChkMascaraLista().isSelected());
				getTxtLista().append(placa + "\n");
			}
		}
	}

	public void escreverListaChassi() {
		limparLista();

		String qtd = getTxtQtd().getText();

		if ("".equals(qtd) == true) {
			escreverMensagemAviso("Campo quantidade em branco!");
			getTxtQtd().requestFocus(true);
		} else {
			int qtdFinal = Integer.parseInt(qtd);

			GeradorChassi gerador = new GeradorChassi();

			for (int i = 0; i < qtdFinal; i++) {
				String chassi = gerador.gerarChassi();
				getTxtLista().append(chassi + "\n");
			}
		}
	}

	public void escreverListaSobrenome() {
		limparLista();

		String qtd = getTxtQtd().getText();

		if ("".equals(qtd) == true) {
			escreverMensagemAviso("Campo quantidade em branco!");
			getTxtQtd().requestFocus(true);
		} else {
			int qtdFinal = Integer.parseInt(qtd);

			GeradorNomeSobrenome gerador = new GeradorNomeSobrenome();

			for (int i = 0; i < qtdFinal; i++) 
			{
				String sobrenome = gerador.gerarSobrenome();
				getTxtLista().append(sobrenome + "\n");
			}
		}
	}
	
	public void escreverListaTelefoneFixo() {
		limparLista();

		String qtd = getTxtQtd().getText();

		if ("".equals(qtd) == true) {
			escreverMensagemAviso("Campo quantidade em branco!");
			getTxtQtd().requestFocus(true);
		} else {
			int qtdFinal = Integer.parseInt(qtd);

			GeradorTelefone gerador = new GeradorTelefone();

			for (int i = 0; i < qtdFinal; i++) 
			{
				String telefoneFixo = gerador.gerarTelefoneFixoLista(getChkMascaraLista().isSelected());
				getTxtLista().append(telefoneFixo + "\n");
			}
		}
	}
	
	public void escreverListaTelefoneCelular() {
		limparLista();

		String qtd = getTxtQtd().getText();

		if ("".equals(qtd) == true) {
			escreverMensagemAviso("Campo quantidade em branco!");
			getTxtQtd().requestFocus(true);
		} else {
			int qtdFinal = Integer.parseInt(qtd);

			GeradorTelefone gerador = new GeradorTelefone();

			for (int i = 0; i < qtdFinal; i++) 
			{
				String telefoneCelular = gerador.gerarTelefoneCelularLista(getChkMascaraLista().isSelected());
				getTxtLista().append(telefoneCelular + "\n");
			}
		}
	}

	public void escreverListaNomeSobrenome(String tipo) {
		limparLista();

		String qtd = getTxtQtd().getText();

		if ("".equals(qtd) == true) {
			escreverMensagemAviso("Campo quantidade em branco!");
			getTxtQtd().requestFocus(true);
		} else if (getCboSexo().getSelectedIndex() <= 0) {
			escreverMensagemAviso("Campo sexo em branco!");
			getCboSexo().requestFocus(true);
		} else {
			int qtdFinal = Integer.parseInt(qtd);
			String sexo = getCboSexo().getSelectedItem().toString();

			if ("Aleatório".equals(sexo)) {
				escreverListaNomeSobrenomeSexoAleatorio(tipo);
			} else {
				GeradorNomeSobrenome gerador = new GeradorNomeSobrenome();

				if ("Masculino".equals(sexo))
					sexo = "M";
				else if ("Feminino".equals(sexo))
					sexo = "F";

				for (int i = 0; i < qtdFinal; i++) {
					if ("Nome".equals(tipo)) {
						String nome = gerador.gerarNome(sexo);
						getTxtLista().append(nome + "\n");
					} else if ("Sobrenome".equals(tipo)) {
						String sobrenome = gerador.gerarSobrenome();
						getTxtLista().append(sobrenome + "\n");
					} else if ("Nome + Sobrenome".equals(tipo)) {
						String nome = gerador.gerarNome(sexo);
						String sobrenome = gerador.gerarSobrenome();
						getTxtLista().append(nome + " " + sobrenome + "\n");
					}
				}
			}
		}
	}

	public void escreverListaNomeSobrenomeSexoAleatorio(String tipo) {
		limparLista();

		String qtd = getTxtQtd().getText();

		int qtdFinal = Integer.parseInt(qtd);
		GeradorNomeSobrenome gerador = new GeradorNomeSobrenome();

		for (int i = 0; i < qtdFinal; i++) {
			String[] sexoOpcoes = { "F", "M" };

			int c = (int) Math.floor(Math.random() * sexoOpcoes.length);
			String sexo = sexoOpcoes[c];

			if ("Nome".equals(tipo)) 
			{
				String nome = gerador.gerarNome(sexo);
				getTxtLista().append(nome + "\n");
			} else if ("Nome + Sobrenome".equals(tipo)) {
				String nome = gerador.gerarNome(sexo);
				String sobrenome = gerador.gerarSobrenome();
				getTxtLista().append(nome + " " + sobrenome + "\n");
			}
		}
	}

	public void escreverMensagemAviso(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Aviso", JOptionPane.INFORMATION_MESSAGE);
	}

	public void escreverMensagemErro(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Erro", JOptionPane.ERROR_MESSAGE);
	}

	public void modoInicial() {
		getRdoCpf().setSelected(true);
		getRdoSexoAleatorio().setSelected(true);
		validarDocumentoSelecionadoAbaIndividual();
		getTxtQtd().requestFocus(true);
		getCboSexo().setSelectedIndex(0);
		getCboSexo().setEnabled(false);
		limparTabela();
	}

	public void validarDocumentoSelecionadoAbaGeral() {
		limparTabela();
		getTxtQtd().requestFocus(true);
	}

	public void validarDocumentoSelecionadoAbaIndividual() {
		if (getRdoRenavam().isSelected()) {
			getLblDocumentoSelecionado().setText("Renavam");
			getCboSexo().setSelectedIndex(0);
			getCboSexo().setEnabled(false);
		} else if (getRdoCpf().isSelected()) {
			getLblDocumentoSelecionado().setText("CPF");
			getCboSexo().setSelectedIndex(0);
			getCboSexo().setEnabled(false);
		} else if (getRdoCnpj().isSelected()) {
			getLblDocumentoSelecionado().setText("CNPJ");
			getCboSexo().setSelectedIndex(0);
			getCboSexo().setEnabled(false);
		} else if (getRdoPlaca().isSelected()) {
			getLblDocumentoSelecionado().setText("Placa");
			getCboSexo().setSelectedIndex(0);
			getCboSexo().setEnabled(false);
		} else if (getRdoChassi().isSelected()) {
			getLblDocumentoSelecionado().setText("Chassi");
			getCboSexo().setSelectedIndex(0);
			getCboSexo().setEnabled(false);
		} else if (getRdoNome().isSelected()) {
			getLblDocumentoSelecionado().setText("Nome");
			getCboSexo().setSelectedIndex(0);
			getCboSexo().setEnabled(true);
		} else if (getRdoDataNascimento().isSelected()) {
			getLblDocumentoSelecionado().setText("Data de Nascimento");
			getCboSexo().setSelectedIndex(0);
			getCboSexo().setEnabled(false);
		} else if (getRdoSobrenome().isSelected()) {
			getLblDocumentoSelecionado().setText("Sobrenome");
			getCboSexo().setSelectedIndex(0);
			getCboSexo().setEnabled(false);
		} else if (getRdoNomeSobrenome().isSelected()) {
			getLblDocumentoSelecionado().setText("Nome + Sobrenome");
			getCboSexo().setSelectedIndex(0);
			getCboSexo().setEnabled(true);
		} else if (getRdoEmail().isSelected()) {
			getLblDocumentoSelecionado().setText("E-mail");
			getCboSexo().setSelectedIndex(0);
			getCboSexo().setEnabled(false);
		} else if (getRdoTelefoneFixo().isSelected()) {
			getLblDocumentoSelecionado().setText("Telefone Fixo");
			getCboSexo().setSelectedIndex(0);
			getCboSexo().setEnabled(false);
		} else if (getRdoTelefoneCelular().isSelected()) {
			getLblDocumentoSelecionado().setText("Telefone Celular");
			getCboSexo().setSelectedIndex(0);
			getCboSexo().setEnabled(false);
		}
		limparLista();
		getTxtQtd().requestFocus(true);
	}

	public void adicionar() {
		int linha = tblLista.getSelectedRow();
		tblLista.removeEditor();

		if ((linha == -1) || ((linha + 1) == modTabela.getRowCount())) {
			modTabela.addRow(new Object[modTabela.getColumnCount()]);
		} else {
			if ((linha + 1) < modTabela.getRowCount()) {
				modTabela.addRow(new Object[modTabela.getColumnCount()]);
				Object transfere;
				for (int i = (modTabela.getRowCount() - 2); i > linha; i--) {
					for (int k = 0; k < modTabela.getColumnCount(); k++) {
						transfere = modTabela.getValueAt(i, k);
						modTabela.setValueAt(transfere, (i + 1), k);
					}
				}
				for (int i = 0; i < modTabela.getColumnCount(); i++) {
					modTabela.setValueAt(new String(""), (linha + 1), i);
				}
			}
		}
	}
	
	//Metodo exportar tabela para Excel
    public void exportarExcelTabela() throws IOException 
    {
    	JFileChooser selecao = new JFileChooser();
    	selecao.setDialogTitle("Exportar Dados para Arquivo Excel (xlsx)");

        //seta para selecionar apenas arquivos
    	selecao.setFileSelectionMode(JFileChooser.FILES_ONLY);

        //desabilita todos os tipos de arquivos
    	selecao.setAcceptAllFileFilterUsed(false);
    	
    	selecao.setMultiSelectionEnabled(false);
        
    	selecao.setFileFilter(new FileNameExtensionFilter("Arquivo Excel (xlsx)","xlsx"));

        //mostra janela para salvar
        int acao = selecao.showSaveDialog(this);

        //executa acao conforme opcao selecionada
        if (acao == JFileChooser.APPROVE_OPTION) 
        {        	
        	String nomeArquivo = selecao.getSelectedFile().getName();
        	
        	if(!nomeArquivo.toString().contains("."))
        	{
        		nomeArquivo = nomeArquivo + ".xlsx";
        	}
        	else if(!nomeArquivo.endsWith(".xlsx") ) 
        	{
        		String arquivoAntigo = nomeArquivo;
        		String arquivoNovo = arquivoAntigo.substring(0, arquivoAntigo.lastIndexOf('.'));   		
        		nomeArquivo = arquivoNovo + ".xlsx";
        	}
        	
        	SXSSFWorkbook workbook = new SXSSFWorkbook(100);
        	Sheet sheetGerador = workbook.createSheet("Dados");

        	int rownum = 0;
        	Row row = sheetGerador.createRow(rownum++);
        	int cellnum = 0;            
        	
            CellStyle style = workbook.createCellStyle();
            
            //style.setFillForegroundColor(IndexedColors.TAN.getIndex());
    	    //style.setFillPattern(CellStyle.SOLID_FOREGROUND);
    	    org.apache.poi.ss.usermodel.Font font = workbook.createFont();
            font.setBoldweight(org.apache.poi.ss.usermodel.Font.BOLDWEIGHT_BOLD);
            style.setFont(font);
        	
        	for (int i = 0; i < getTblLista().getColumnCount(); i++) 
        	{     	
        		Cell celula = row.createCell(cellnum++);
        		celula.setCellStyle(style);
            	celula.setCellValue(getTblLista().getColumnName(i));
            }
        	
            for (int i = 0; i < getTblLista().getRowCount(); i++) 
            {
            	row = sheetGerador.createRow(rownum++);
            	
                for (int j = 0; j < getTblLista().getColumnCount(); j++) 
                {
                    Cell celula = row.createCell(j);
                	celula.setCellValue(getTblLista().getValueAt(i, j).toString());
                }                
            }  
            
            for (int i = 0; i < getTblLista().getColumnCount(); i++) 
        	{     	
            	sheetGerador.autoSizeColumn(i, true);
            }
            
            try {
                FileOutputStream out = new FileOutputStream(selecao.getSelectedFile().getParentFile() + System.getProperty("file.separator") +nomeArquivo);
                workbook.write(out);
                out.flush();
                out.close();
                escreverMensagemAviso("Arquivo \""+nomeArquivo+"\" criado com sucesso.\nDiretório selecionado: "+selecao.getSelectedFile().getParentFile());              
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                   escreverMensagemAviso("Arquivo não encontrado.");
            } catch (IOException e) {
                e.printStackTrace();
                   escreverMensagemErro("Erro na criação do arquivo.");
            }            
            
        } else if (acao == JFileChooser.CANCEL_OPTION) {
        	escreverMensagemAviso("Você cancelou a exportação do arquivo.");
        } else if (acao == JFileChooser.ERROR_OPTION) {
        	escreverMensagemErro("Ocorreu um erro ao exportar o arquivo.");
        }
    }
    
    //Metodo exportar tabela para Excel
    public void exportarExcelLista() throws IOException 
    {
    	JFileChooser selecao = new JFileChooser();
    	selecao.setDialogTitle("Exportar Dados para Arquivo Excel (xlsx)");
    	//selecao.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagens/icone.png")));

        //seta para selecionar apenas arquivos
    	selecao.setFileSelectionMode(JFileChooser.FILES_ONLY);

        //desabilita todos os tipos de arquivos
    	selecao.setAcceptAllFileFilterUsed(false);
    	
    	selecao.setMultiSelectionEnabled(false);
        
    	selecao.setFileFilter(new FileNameExtensionFilter("Arquivo Excel (xlsx)","xlsx"));

        //mostra janela para salvar
        int acao = selecao.showSaveDialog(this);

        //executa acao conforme opcao selecionada
        if (acao == JFileChooser.APPROVE_OPTION) 
        {        	
        	String nomeArquivo = selecao.getSelectedFile().getName();
        	
        	if(!nomeArquivo.toString().contains("."))
        	{
        		nomeArquivo = nomeArquivo + ".xlsx";
        	}
        	else if(!nomeArquivo.endsWith(".xlsx") ) 
        	{
        		String arquivoAntigo = nomeArquivo;
        		String arquivoNovo = arquivoAntigo.substring(0, arquivoAntigo.lastIndexOf('.'));   		
        		nomeArquivo = arquivoNovo + ".xlsx";
        	}
        	
        	SXSSFWorkbook workbook = new SXSSFWorkbook(100);
        	Sheet sheetGerador = workbook.createSheet("Dados");

        	int rownum = 0;
        	Row row = sheetGerador.createRow(rownum++);
        	int cellnum = 0;            
        	
            CellStyle style = workbook.createCellStyle();
            
            //style.setFillForegroundColor(IndexedColors.ORCHID.getIndex());
    	    //style.setFillPattern(CellStyle.SOLID_FOREGROUND);
    	    org.apache.poi.ss.usermodel.Font font = workbook.createFont();
            font.setBoldweight(org.apache.poi.ss.usermodel.Font.BOLDWEIGHT_BOLD);
            style.setFont(font);       	
        	
        	Cell celula = row.createCell(cellnum++);
        	celula.setCellStyle(style);
            celula.setCellValue(lblDocumentoSelecionado.getText().toUpperCase());

            String str[] = getTxtLista().getText().split("\n");
                        
            for(String lista:str)
            {
            	row = sheetGerador.createRow(rownum++);
                celula = row.createCell(0);
                celula.setCellValue(lista);
            }
            
            sheetGerador.autoSizeColumn(0, true);
            
            try {
                FileOutputStream out = new FileOutputStream(selecao.getSelectedFile().getParentFile() + System.getProperty("file.separator") +nomeArquivo);
                workbook.write(out);
                out.close();
                escreverMensagemAviso("Arquivo \""+nomeArquivo+"\" criado com sucesso.\nDiretório selecionado: "+selecao.getSelectedFile().getParentFile());              
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                   escreverMensagemAviso("Arquivo não encontrado.");
            } catch (IOException e) {
                e.printStackTrace();
                   escreverMensagemErro("Erro na criação do arquivo.");
            }
        } else if (acao == JFileChooser.CANCEL_OPTION) {
        	escreverMensagemAviso("Você cancelou a exportação do arquivo.");
        } else if (acao == JFileChooser.ERROR_OPTION) {
        	escreverMensagemErro("Ocorreu um erro ao exportar o arquivo.");
        }
    }
    
    public void setOuvintes(ActionListener controle) 
    {
    	//BOTÕES
		getBtnGerar().addActionListener(controle);
		getBtnExportar().addActionListener(controle);
		getBtnFechar().addActionListener(controle);
		getBtnLimpar().addActionListener(controle);
		//RADIO
		getRdoCpf().addActionListener(controle);
		getRdoCnpj().addActionListener(controle);
		getRdoNome().addActionListener(controle);
		getRdoNomeSobrenome().addActionListener(controle);
		getRdoSobrenome().addActionListener(controle);
		getRdoEmail().addActionListener(controle);
		getRdoTelefoneCelular().addActionListener(controle);
		getRdoTelefoneFixo().addActionListener(controle);
		getRdoDataNascimento().addActionListener(controle);
		getRdoRenavam().addActionListener(controle);
		getRdoPlaca().addActionListener(controle);
		getRdoChassi().addActionListener(controle);	
		getRdoSexoAleatorio().addActionListener(controle);
		getRdoSexoFeminino().addActionListener(controle);
		getRdoSexoMasculino().addActionListener(controle);
		//CHECKBOX
		getChkMascaraTabelaCpf().addActionListener(controle);
		getChkMascaraTabelaCnpj().addActionListener(controle);
		getChkMascaraTabelaTelefoneFixo().addActionListener(controle);
		getChkMascaraTabelaTelefoneCelular().addActionListener(controle);
		getChkMascaraTabelaPlaca().addActionListener(controle);
		getChkMascaraTabelaRenavam().addActionListener(controle);
		getChkMascaraTabelaTudo().addActionListener(controle);
	}
    
    public void setOuvintesTela(WindowListener controle)
    {
    	this.addWindowListener(controle);
    }
    
    public void setOuvintesTela2(WindowStateListener controle)
    {
    	this.addWindowStateListener(controle);
    }
    
    public void setOuvintesComponente(ComponentListener controle)
    {
    	this.addComponentListener(controle);
    }    
    
    public int abaSelecionada()
    {
    	return getTabPrincipal().getSelectedIndex();
    }
    
    public String comboSelecionado()
    {
    	return getLblDocumentoSelecionado().getText();
    }
    
    public int qtdLinhasTabela()
    {
    	return modTabela.getRowCount();
    }
    
    public void focoTxtQtd()
    {
    	getTxtQtd().requestFocus(true);
    }
    
    public String textoLista()
    {
    	return getTxtLista().getText();
    }
    
    public void validarMascaraTabelaTodos()
    {    	
    	if (getChkMascaraTabelaTudo().isSelected())
    	{
    		getChkMascaraTabelaCpf().setSelected(true);
        	getChkMascaraTabelaCnpj().setSelected(true);
        	getChkMascaraTabelaTelefoneFixo().setSelected(true);
        	getChkMascaraTabelaTelefoneCelular().setSelected(true);
        	getChkMascaraTabelaPlaca().setSelected(true);
        	getChkMascaraTabelaRenavam().setSelected(true);
        	
        	getChkMascaraTabelaCpf().setEnabled(false);
        	getChkMascaraTabelaCnpj().setEnabled(false);
        	getChkMascaraTabelaTelefoneFixo().setEnabled(false);
        	getChkMascaraTabelaTelefoneCelular().setEnabled(false);
        	getChkMascaraTabelaPlaca().setEnabled(false);
        	getChkMascaraTabelaRenavam().setEnabled(false);
    	}
    	else
    	{
    		getChkMascaraTabelaCpf().setSelected(false);
        	getChkMascaraTabelaCnpj().setSelected(false);
        	getChkMascaraTabelaTelefoneFixo().setSelected(false);
        	getChkMascaraTabelaTelefoneCelular().setSelected(false);
        	getChkMascaraTabelaPlaca().setSelected(false);
        	getChkMascaraTabelaRenavam().setSelected(false);
        	
        	getChkMascaraTabelaCpf().setEnabled(true);
        	getChkMascaraTabelaCnpj().setEnabled(true);
        	getChkMascaraTabelaTelefoneFixo().setEnabled(true);
        	getChkMascaraTabelaTelefoneCelular().setEnabled(true);
        	getChkMascaraTabelaPlaca().setEnabled(true);
        	getChkMascaraTabelaRenavam().setEnabled(true);
    	}		
    	
    	mascaraTabelaCPF();
    	mascaraTabelaCNPJ();
    	mascaraTabelaTelefoneFixo();
    	mascaraTabelaTelefoneCelular();
    	mascaraTabelaPlaca();
    	mascaraTabelaRenavam();
    }
}
