package pica;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;

import java.awt.ComponentOrientation;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.JSpinner;
import javax.swing.JToggleButton;
import javax.swing.SpinnerNumberModel;
import java.text.DecimalFormat;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;


public class Main extends JFrame{

	private JLayeredPane contentPane;
	private JTextField vardsIevade;
	private JTextField uzvardsIevade;
	private JTextField numursIevade;
	private JTextField adreseIevade;
	private JTextField vardsDalejaIevade;
	private static JPanel sideBar;
	private JLabel tomatiBilde;
	private JLabel tomatuBilde;
	static double buvetCena = 8.79, lastCena = 8.79, pedejaVertiba;
	static ArrayList<String> buvetSastavdalas = new ArrayList<>();
	static String buvetApraksts = ""; 
	private static JSpinner spinner = new JSpinner();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Look and feel iestatījumi
					FlatLaf.setGlobalExtraDefaults( Collections.singletonMap( "@accentColor", "#CD4631" ) );
					UIManager.put( "Component.focusWidth", 0 );
					FlatLightLaf.setup();
			        UIManager.put("JButton.arc", 20 );
			        UIManager.put("TextComponent.arc", 20);
			        UIManager.put("CheckBox.arc", 4);
			        UIManager.put("JTextField.arc", 10);
			        //UIManager.put("TabbedPane.selectedBackground", new Color(205, 70, 49) );
			        UIManager.put("TabbedPane.minimumTabWidth", 150);
			        UIManager.put("TabbedPane.tabAreaAlignment", "center");
			        UIManager.put("TabbedPane.showTabSeparators", true);
			        UIManager.put("ToggleButton.selectedBackground", new Color(255, 96, 71));
			        UIManager.put("ToggleButton.selectedForeground", new Color(255, 255, 255));
			        UIManager.put("Button.arc",15);
			        
			        //redzams
			        
					buvetApraksts = buvetAprakstu("20 cm");
					buvetApraksts = buvetAprakstu("Plāna mīkla");
					
					Main frame = new Main();
					frame.setUndecorated(true); 
					frame.setVisible(true);
					
					
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	

	DecimalFormat df = new DecimalFormat("#.##");
	
	//Vizualizācija
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds((1920-1000)/2, (1080-850)/2, 1000, 860);
		contentPane = new JLayeredPane();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.setLayer(tabbedPane, 10);
		tabbedPane.setBounds(0, 0, 1000, 900);
		contentPane.add(tabbedPane);
		
		JPanel sakumaEkrans = new JPanel();
		sakumaEkrans.setVisible(false);
		contentPane.setLayer(sakumaEkrans, 0);
		sakumaEkrans.setBackground(new Color(189, 195, 199));
		sakumaEkrans.setBounds(0, 0, 1000, 850);
		contentPane.add(sakumaEkrans);
		sakumaEkrans.setLayout(null);
		
		/*Sākuma ekrāns sākums
		 * ------------------------------------------------------
		 */
		
		JButton uzVietasButton = new JButton("");
		uzVietasButton.setBackground(Color.WHITE);
		uzVietasButton.setIcon(new ImageIcon(Main.class.getResource("/bildes/uzVietasIcon.png")));
		uzVietasButton.setBounds(306, 307, 168, 150);
		uzVietasButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sakumaEkrans.add(uzVietasButton);
		
		JLabel uzVietasLabel = new JLabel("Ēst uz vietas");
		uzVietasLabel.setFont(new Font("Inter", Font.PLAIN, 16));
		uzVietasLabel.setHorizontalAlignment(SwingConstants.CENTER);
		uzVietasLabel.setBounds(342, 467, 100, 19);
		sakumaEkrans.add(uzVietasLabel);
		
		JButton uzMajamButton = new JButton("");
		uzMajamButton.setIcon(new ImageIcon(Main.class.getResource("/bildes/majasIcon.png")));
		uzMajamButton.setBackground(Color.WHITE);
		uzMajamButton.setBounds(524, 307, 168, 150);
		uzMajamButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sakumaEkrans.add(uzMajamButton);
		
		JLabel uzMajamLabel = new JLabel("Pasūtīt uz mājām");
		uzMajamLabel.setHorizontalAlignment(SwingConstants.CENTER);
		uzMajamLabel.setFont(new Font("Inter", Font.PLAIN, 16));
		uzMajamLabel.setBounds(543, 467, 130, 19);
		sakumaEkrans.add(uzMajamLabel);

		
		/*Sākuma ekrāns beigas
		 * ------------------------------------------------------
		 Registrācijas ekrāns sākums */
				
		
		JPanel registracijaEkrans = new JPanel();
		registracijaEkrans.setVisible(false);
		registracijaEkrans.setBackground(new Color(189, 195, 199));
		contentPane.setLayer(registracijaEkrans, 3);
		registracijaEkrans.setBounds(0, -19, 1000, 850);
		contentPane.add(registracijaEkrans);
		registracijaEkrans.setLayout(null);
		
		JLabel registracijaLogo = new JLabel("");
		registracijaLogo.setIcon(new ImageIcon(Main.class.getResource("/bildes/majamLogo.png")));
		registracijaLogo.setBounds(282, 70, 435, 99);
		registracijaEkrans.add(registracijaLogo);
		
		JLabel vardsIevadeLabel = new JLabel("Vārds");
		vardsIevadeLabel.setFont(new Font("Inter", Font.PLAIN, 16));
		vardsIevadeLabel.setBounds(318, 191, 54, 20);
		registracijaEkrans.add(vardsIevadeLabel);
		
		vardsIevade = new JTextField();
		vardsIevade.setFont(new Font("Inter", Font.PLAIN, 14));
		vardsIevade.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		vardsIevade.setBounds(318, 214, 155, 30);
		registracijaEkrans.add(vardsIevade);
		vardsIevade.setColumns(10);
		
		uzvardsIevade = new JTextField();
		uzvardsIevade.setFont(new Font("Inter", Font.PLAIN, 14));
		uzvardsIevade.setColumns(10);
		uzvardsIevade.setBounds(533, 214, 155, 30);
		registracijaEkrans.add(uzvardsIevade);
		
		JLabel uzvardsLabel = new JLabel("Uzvārds");
		uzvardsLabel.setFont(new Font("Inter", Font.PLAIN, 16));
		uzvardsLabel.setBounds(533, 191, 81, 20);
		registracijaEkrans.add(uzvardsLabel);
		
		numursIevade = new JTextField();
		numursIevade.setFont(new Font("Inter", Font.PLAIN, 14));
		numursIevade.setText("+371 ");
		numursIevade.setColumns(10);
		numursIevade.setBounds(318, 284, 370, 30);
		registracijaEkrans.add(numursIevade);
		
		JLabel numursIevadeLabel = new JLabel("Telefona numurs");
		numursIevadeLabel.setFont(new Font("Inter", Font.PLAIN, 16));
		numursIevadeLabel.setBounds(318, 261, 155, 20);
		registracijaEkrans.add(numursIevadeLabel);
		
		adreseIevade = new JTextField();
		adreseIevade.setFont(new Font("Inter", Font.PLAIN, 14));
		adreseIevade.setColumns(10);
		adreseIevade.setBounds(318, 351, 370, 30);
		registracijaEkrans.add(adreseIevade);
		
		JLabel adreseIevadeLabel = new JLabel("Piegādes adrese");
		adreseIevadeLabel.setFont(new Font("Inter", Font.PLAIN, 16));
		adreseIevadeLabel.setBounds(318, 325, 155, 26);
		registracijaEkrans.add(adreseIevadeLabel);
		
		JLabel apmaksaLabel = new JLabel("Apmaksas veids");
		apmaksaLabel.setFont(new Font("Inter", Font.PLAIN, 16));
		apmaksaLabel.setBounds(318, 392, 155, 20);
		registracijaEkrans.add(apmaksaLabel);
		
		JButton registerButton = new JButton("Turpināt");
		registerButton.setForeground(Color.WHITE);
		registerButton.setBackground(new Color(205, 70, 49));
		registerButton.setFont(new Font("Inter", Font.PLAIN, 16));
		registerButton.setBounds(546, 489, 142, 42);
		registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		registracijaEkrans.add(registerButton);
		
		JRadioButton choiceKarte = new JRadioButton("- Karte");
		choiceKarte.setSelectedIcon(new ImageIcon(Main.class.getResource("/bildes/checkbox_checked.png")));
		choiceKarte.setIcon(new ImageIcon(Main.class.getResource("/bildes/checkbox.png")));
		choiceKarte.setSelected(true);
		choiceKarte.setFont(new Font("Inter", Font.PLAIN, 16));
		choiceKarte.setBounds(318, 419, 90, 30);
		registracijaEkrans.add(choiceKarte);
		choiceKarte.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JRadioButton choiceSkaidra = new JRadioButton("- Skaidrā naudā");
		choiceSkaidra.setIcon(new ImageIcon(Main.class.getResource("/bildes/checkbox.png")));
		choiceSkaidra.setSelectedIcon(new ImageIcon(Main.class.getResource("/bildes/checkbox_checked.png")));
		choiceSkaidra.setFont(new Font("Inter", Font.PLAIN, 16));
		choiceSkaidra.setBounds(417, 419, 197, 30);
		registracijaEkrans.add(choiceSkaidra);
		choiceSkaidra.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JLabel registerBilde = new JLabel("");
		registerBilde.setIcon(new ImageIcon(Main.class.getResource("/bildes/registracijaBilde.png")));
		registerBilde.setBounds(207, 356, 585, 587);
		registracijaEkrans.add(registerBilde);
		
		JPanel registracijaDalejaEkrans = new JPanel();
		registracijaDalejaEkrans.setVisible(false);
		contentPane.setLayer(registracijaDalejaEkrans, 2);
		registracijaDalejaEkrans.setLayout(null);
		registracijaDalejaEkrans.setBackground(new Color(189, 195, 199));
		registracijaDalejaEkrans.setBounds(0, 0, 1000, 850);
		contentPane.add(registracijaDalejaEkrans);
		
		
		JLabel registracijaLogo_1 = new JLabel("");
		registracijaLogo_1.setIcon(new ImageIcon(Main.class.getResource("/bildes/vietasLogo.png")));
		registracijaLogo_1.setBounds(312, 70, 376, 99);
		registracijaDalejaEkrans.add(registracijaLogo_1);
		
		JLabel vardsIevadeLabel_1 = new JLabel("Vārds");
		vardsIevadeLabel_1.setFont(new Font("Inter", Font.PLAIN, 16));
		vardsIevadeLabel_1.setBounds(318, 191, 54, 20);
		registracijaDalejaEkrans.add(vardsIevadeLabel_1);
		
		vardsDalejaIevade = new JTextField();
		vardsDalejaIevade.setFont(new Font("Inter", Font.PLAIN, 14));
		vardsDalejaIevade.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		vardsDalejaIevade.setColumns(10);
		vardsDalejaIevade.setBounds(318, 214, 155, 30);
		registracijaDalejaEkrans.add(vardsDalejaIevade);
		
		JLabel apmaksaLabel_1 = new JLabel("Apmaksas veids");
		apmaksaLabel_1.setFont(new Font("Inter", Font.PLAIN, 16));
		apmaksaLabel_1.setBounds(318, 251, 155, 20);
		registracijaDalejaEkrans.add(apmaksaLabel_1);
		
		JButton registerButton_1 = new JButton("Turpināt");
		registerButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		registerButton_1.setForeground(Color.WHITE);
		registerButton_1.setFont(new Font("Inter", Font.PLAIN, 16));
		registerButton_1.setBackground(new Color(205, 70, 49));
		registerButton_1.setBounds(546, 357, 142, 42);
		registracijaDalejaEkrans.add(registerButton_1);
		
		JRadioButton choiceKarte_1 = new JRadioButton("- Karte");
		choiceKarte_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		choiceKarte_1.setIcon(new ImageIcon(Main.class.getResource("/bildes/checkbox.png")));
		choiceKarte_1.setSelectedIcon(new ImageIcon(Main.class.getResource("/bildes/checkbox_checked.png")));
		choiceKarte_1.setSelected(true);
		choiceKarte_1.setFont(new Font("Inter", Font.PLAIN, 16));
		choiceKarte_1.setBounds(318, 278, 90, 30);
		registracijaDalejaEkrans.add(choiceKarte_1);
		
		JRadioButton choiceSkaidra_1 = new JRadioButton("- Skaidrā naudā");
		choiceSkaidra_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		choiceSkaidra_1.setIcon(new ImageIcon(Main.class.getResource("/bildes/checkbox.png")));
		choiceSkaidra_1.setSelectedIcon(new ImageIcon(Main.class.getResource("/bildes/checkbox_checked.png")));
		choiceSkaidra_1.setFont(new Font("Inter", Font.PLAIN, 16));
		choiceSkaidra_1.setBounds(417, 278, 197, 30);
		registracijaDalejaEkrans.add(choiceSkaidra_1);
		
		JLabel registerBilde_1 = new JLabel("");
		registerBilde_1.setIcon(new ImageIcon(Main.class.getResource("/bildes/registracijaBilde.png")));
		registerBilde_1.setBounds(205, 220, 585, 587);
		registracijaDalejaEkrans.add(registerBilde_1);
		
		/*Registrācijas ekrāns beigas
		 * ------------------------------------------------------
		 Titullapas ekrāns sākums */
		
		JPanel TitullapaEkrans = new JPanel();
		TitullapaEkrans.setBackground(new Color(240, 240, 240));
		contentPane.setLayer(TitullapaEkrans, 4);
		TitullapaEkrans.setBounds(0, 0, 1000, 850);
		tabbedPane.addTab("Gatavās picas", new ImageIcon(Main.class.getResource("/bildes/pasutijumi_icon.png")), TitullapaEkrans, null);
		//contentPane.add(TitullapaEkrans);
		TitullapaEkrans.setLayout(null);
		
		JLabel lblBekonaPica = new JLabel("Bekona pica");
		lblBekonaPica.setHorizontalAlignment(SwingConstants.CENTER);
		lblBekonaPica.setFont(new Font("Inter Medium", Font.PLAIN, 16));
		lblBekonaPica.setBounds(705, 652, 229, 35);
		TitullapaEkrans.add(lblBekonaPica);
		
		JLabel lblilliPica = new JLabel("Čilli pica");
		lblilliPica.setHorizontalAlignment(SwingConstants.CENTER);
		lblilliPica.setFont(new Font("Inter Medium", Font.PLAIN, 16));
		lblilliPica.setBounds(376, 652, 205, 35);
		TitullapaEkrans.add(lblilliPica);
		
		JLabel lblBbqPica = new JLabel("BBQ pica");
		lblBbqPica.setHorizontalAlignment(SwingConstants.CENTER);
		lblBbqPica.setFont(new Font("Inter Medium", Font.PLAIN, 16));
		lblBbqPica.setBounds(56, 652, 205, 35);
		TitullapaEkrans.add(lblBbqPica);
		
		JLabel lblSieraPica = new JLabel("Siera pica");
		lblSieraPica.setHorizontalAlignment(SwingConstants.CENTER);
		lblSieraPica.setFont(new Font("Inter Medium", Font.PLAIN, 16));
		lblSieraPica.setBounds(705, 391, 207, 35);
		TitullapaEkrans.add(lblSieraPica);
		
		JLabel lblLaukuPica = new JLabel("Lauku pica");
		lblLaukuPica.setHorizontalAlignment(SwingConstants.CENTER);
		lblLaukuPica.setFont(new Font("Inter Medium", Font.PLAIN, 16));
		lblLaukuPica.setBounds(376, 391, 207, 35);
		TitullapaEkrans.add(lblLaukuPica);
		
		JLabel lblNewLabel = new JLabel("Salami pica");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Inter Medium", Font.PLAIN, 16));
		lblNewLabel.setBounds(66, 391, 205, 35);
		TitullapaEkrans.add(lblNewLabel);
		
		JButton bbqPoga = new JButton("");
		bbqPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bbqPoga.setContentAreaFilled(false);
		bbqPoga.setBorderPainted(false);
		bbqPoga.setIcon(new ImageIcon(Main.class.getResource("/bildes/bbq.png")));
		bbqPoga.setBounds(53, 457, 229, 200);
		TitullapaEkrans.add(bbqPoga);
		
		JButton ciliPoga = new JButton("");
		ciliPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ciliPoga.setContentAreaFilled(false);
		ciliPoga.setBorderPainted(false);
		ciliPoga.setIcon(new ImageIcon(Main.class.getResource("/bildes/Cili.png")));
		ciliPoga.setBounds(376, 457, 232, 200);
		TitullapaEkrans.add(ciliPoga);
		
		JButton bekonaPoga = new JButton("");
		bekonaPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bekonaPoga.setContentAreaFilled(false);
		bekonaPoga.setBorderPainted(false);
		bekonaPoga.setIcon(new ImageIcon(Main.class.getResource("/bildes/bekona.png")));
		bekonaPoga.setBounds(710, 457, 224, 200);
		TitullapaEkrans.add(bekonaPoga);
		
		JButton sieraPoga = new JButton("");
		sieraPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sieraPoga.setContentAreaFilled(false);
		sieraPoga.setBorderPainted(false);
		sieraPoga.setIcon(new ImageIcon(Main.class.getResource("/bildes/siera.png")));
		sieraPoga.setBounds(705, 196, 229, 200);
		TitullapaEkrans.add(sieraPoga);
		
		JButton laukuPoga = new JButton("");
		laukuPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		laukuPoga.setContentAreaFilled(false);
		laukuPoga.setBorderPainted(false);
		laukuPoga.setIcon(new ImageIcon(Main.class.getResource("/bildes/laukup.png")));
		laukuPoga.setBounds(376, 196, 231, 200);
		TitullapaEkrans.add(laukuPoga);
		
		JButton salamiPoga = new JButton("");
		salamiPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		salamiPoga.setContentAreaFilled(false);
		salamiPoga.setBorderPainted(false);
		salamiPoga.setIcon(new ImageIcon(Main.class.getResource("/bildes/salami.png")));
		salamiPoga.setBounds(56, 196, 223, 200);
		TitullapaEkrans.add(salamiPoga);
		
		JLabel titullapaBg = new JLabel("");
		titullapaBg.setIcon(new ImageIcon(Main.class.getResource("/bildes/BG.png")));
		titullapaBg.setBounds(0, 30, 1000, 850);
		TitullapaEkrans.add(titullapaBg);
		
		JPanel buvetEkrans = new JPanel();
		buvetEkrans.setBackground(new Color(240, 240, 240));
		tabbedPane.addTab("Būvē savu picu", null, buvetEkrans, null);
		buvetEkrans.setLayout(null);
		
		tomatiBilde = new JLabel("");
		tomatiBilde.setVisible(false);
		
		JLabel bbqBilde = new JLabel("");
		bbqBilde.setVisible(false);
		bbqBilde.setFocusable(false);
		bbqBilde.setIcon(new ImageIcon(Main.class.getResource("/bildes/pica_layers/bbq.png")));
		bbqBilde.setBounds(38, 158, 307, 307);
		buvetEkrans.add(bbqBilde);
		
		JLabel salamisBilde = new JLabel("");
		salamisBilde.setVisible(false);
		salamisBilde.setFocusable(false);
		salamisBilde.setIcon(new ImageIcon(Main.class.getResource("/bildes/pica_layers/salami.png")));
		salamisBilde.setBounds(38, 158, 307, 307);
		buvetEkrans.add(salamisBilde);
		tomatiBilde.setFocusable(false);
		tomatiBilde.setIcon(new ImageIcon(Main.class.getResource("/bildes/pica_layers/tomati.png")));
		tomatiBilde.setBounds(38, 158, 307, 307);
		buvetEkrans.add(tomatiBilde);
		
		JLabel jalapenoBilde = new JLabel("");
		jalapenoBilde.setVisible(false);
		jalapenoBilde.setFocusable(false);
		jalapenoBilde.setIcon(new ImageIcon(Main.class.getResource("/bildes/pica_layers/jalapeno.png")));
		jalapenoBilde.setBounds(38, 158, 307, 307);
		buvetEkrans.add(jalapenoBilde);
		
		JLabel skinkisBilde = new JLabel("");
		skinkisBilde.setVisible(false);
		skinkisBilde.setFocusable(false);
		skinkisBilde.setIcon(new ImageIcon(Main.class.getResource("/bildes/pica_layers/skinkis.png")));
		skinkisBilde.setBounds(38, 158, 307, 307);
		buvetEkrans.add(skinkisBilde);
		
		JLabel ananasiBilde = new JLabel("");
		ananasiBilde.setVisible(false);
		ananasiBilde.setFocusable(false);
		ananasiBilde.setIcon(new ImageIcon(Main.class.getResource("/bildes/pica_layers/ananas.png")));
		ananasiBilde.setBounds(38, 158, 307, 307);
		buvetEkrans.add(ananasiBilde);
		
		JLabel gurkiBilde = new JLabel("");
		gurkiBilde.setVisible(false);
		gurkiBilde.setFocusable(false);
		gurkiBilde.setIcon(new ImageIcon(Main.class.getResource("/bildes/pica_layers/gurki.png")));
		gurkiBilde.setBounds(38, 158, 307, 307);
		buvetEkrans.add(gurkiBilde);
		
		JLabel senesBilde = new JLabel("");
		senesBilde.setVisible(false);
		senesBilde.setFocusable(false);
		senesBilde.setIcon(new ImageIcon(Main.class.getResource("/bildes/pica_layers/senes.png")));
		senesBilde.setBounds(38, 158, 307, 307);
		buvetEkrans.add(senesBilde);
		
		JLabel sipoliBilde = new JLabel("");
		sipoliBilde.setVisible(false);
		sipoliBilde.setFocusable(false);
		sipoliBilde.setIcon(new ImageIcon(Main.class.getResource("/bildes/pica_layers/sipoli.png")));
		sipoliBilde.setBounds(38, 158, 307, 307);
		buvetEkrans.add(sipoliBilde);
		
		JLabel vistaBilde = new JLabel("");
		vistaBilde.setVisible(false);
		vistaBilde.setFocusable(false);
		vistaBilde.setIcon(new ImageIcon(Main.class.getResource("/bildes/pica_layers/vista.png")));
		vistaBilde.setBounds(38, 158, 307, 307);
		buvetEkrans.add(vistaBilde);
		
		JLabel mocarellaBilde = new JLabel("");
		mocarellaBilde.setVisible(false);
		mocarellaBilde.setFocusable(false);
		mocarellaBilde.setIcon(new ImageIcon(Main.class.getResource("/bildes/pica_layers/mozzarella.png")));
		mocarellaBilde.setBounds(38, 158, 307, 307);
		buvetEkrans.add(mocarellaBilde);
		
		JLabel zilaisBilde = new JLabel("");
		zilaisBilde.setVisible(false);
		zilaisBilde.setFocusable(false);
		zilaisBilde.setIcon(new ImageIcon(Main.class.getResource("/bildes/pica_layers/zilais.png")));
		zilaisBilde.setBounds(38, 158, 307, 307);
		buvetEkrans.add(zilaisBilde);
		
		JLabel siersBilde = new JLabel("");
		siersBilde.setVisible(false);
		siersBilde.setFocusable(false);
		siersBilde.setIcon(new ImageIcon(Main.class.getResource("/bildes/pica_layers/siers.png")));
		siersBilde.setBounds(38, 158, 307, 307);
		buvetEkrans.add(siersBilde);
		
		tomatuBilde = new JLabel("");
		tomatuBilde.setVisible(false);
		tomatuBilde.setFocusable(false);
		tomatuBilde.setIcon(new ImageIcon(Main.class.getResource("/bildes/pica_layers/tomatumerce.png")));
		tomatuBilde.setBounds(38, 158, 307, 307);
		buvetEkrans.add(tomatuBilde);
		
		JLabel miklaBilde = new JLabel("");
		miklaBilde.setFocusable(false);
		miklaBilde.setIcon(new ImageIcon(Main.class.getResource("/bildes/pica_layers/mikla.png")));
		miklaBilde.setBounds(38, 158, 307, 307);
		buvetEkrans.add(miklaBilde);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(217, 217, 217));
		panel_1.setBounds(38, 487, 307, 225);
		buvetEkrans.add(panel_1);
		panel_1.setLayout(null);
		
		
		JLabel aprakstsLabel = new JLabel("<html><body style='width: 220px'>"+buvetApraksts);
		aprakstsLabel.setVerticalAlignment(SwingConstants.TOP);
		aprakstsLabel.setBounds(16, 18, 275, 128);
		aprakstsLabel.setHorizontalAlignment(SwingConstants.LEFT);
		aprakstsLabel.setForeground(new Color(79, 79, 79));
		aprakstsLabel.setFont(new Font("Inter", Font.PLAIN, 16));
		panel_1.add(aprakstsLabel);
		
		JButton pirktPoga = new JButton("Pasūtīt");
		pirktPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pirktPoga.setLocation(123, 157);
		pirktPoga.setSize(new Dimension(173, 41));
		pirktPoga.setForeground(Color.WHITE);
		pirktPoga.setBackground(new Color(205, 70, 49));
		pirktPoga.setFont(new Font("Inter", Font.PLAIN, 16));
		panel_1.add(pirktPoga);
		
		spinner = new JSpinner();
		spinner.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		spinner.setModel(new SpinnerNumberModel(1, 1, 9, 1));
		spinner.setFocusCycleRoot(true);
		spinner.setFocusTraversalPolicyProvider(true);
		spinner.setFocusable(false);
		spinner.setFont(new Font("Inter", Font.BOLD, 14));
		spinner.setBounds(58, 160, 55, 34);
		panel_1.add(spinner);
		
		JToggleButton cm20 = new JToggleButton("20cm");
		cm20.setSelected(true);
		cm20.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cm20.setFont(new Font("Inter", Font.PLAIN, 16));
		cm20.setBounds(425, 187, 115, 34);
		cm20.putClientProperty( "JToggleButton.buttonType", "roundRect" );
		buvetEkrans.add(cm20);
		
		JToggleButton cm30 = new JToggleButton("30cm");
		cm30.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cm30.setFont(new Font("Inter", Font.PLAIN, 16));
		cm30.setBounds(560, 187, 115, 34);
		buvetEkrans.add(cm30);
		
		JToggleButton cm60 = new JToggleButton("60cm");
		cm60.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cm60.setFont(new Font("Inter", Font.PLAIN, 16));
		cm60.setBounds(695, 187, 115, 34);
		buvetEkrans.add(cm60);
		
		JToggleButton planaPoga = new JToggleButton("Plāna");
		planaPoga.setSelected(true);
		planaPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		planaPoga.setFont(new Font("Inter", Font.PLAIN, 16));
		planaPoga.setBounds(425, 282, 115, 34);
		buvetEkrans.add(planaPoga);
		
		JToggleButton videjaPoga = new JToggleButton("Vidēja");
		videjaPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		videjaPoga.setFont(new Font("Inter", Font.PLAIN, 16));
		videjaPoga.setBounds(560, 282, 115, 34);
		buvetEkrans.add(videjaPoga);
		
		JToggleButton biezaPoga = new JToggleButton("Bieza");
		biezaPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		biezaPoga.setFont(new Font("Inter", Font.PLAIN, 16));
		biezaPoga.setBounds(695, 282, 115, 34);
		buvetEkrans.add(biezaPoga);
		
		JToggleButton tomatuPoga = new JToggleButton("Tomātu");
		tomatuPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tomatuPoga.setFont(new Font("Inter", Font.PLAIN, 16));
		tomatuPoga.setBounds(425, 365, 115, 34);
		buvetEkrans.add(tomatuPoga);
		
		JToggleButton bbqsPoga = new JToggleButton("BBQ");
		bbqsPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bbqsPoga.setFont(new Font("Inter", Font.PLAIN, 16));
		bbqsPoga.setBounds(560, 365, 115, 34);
		buvetEkrans.add(bbqsPoga);
		
		JToggleButton bezmercePoga = new JToggleButton("Bez");
		bezmercePoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bezmercePoga.setFont(new Font("Inter", Font.PLAIN, 16));
		bezmercePoga.setBounds(695, 365, 115, 34);
		buvetEkrans.add(bezmercePoga);
		
		JToggleButton parastsPoga = new JToggleButton("Parastais");
		parastsPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		parastsPoga.setFont(new Font("Inter", Font.PLAIN, 16));
		parastsPoga.setBounds(425, 456, 115, 34);
		buvetEkrans.add(parastsPoga);
		
		JToggleButton zilaisPoga = new JToggleButton("Zilais");
		zilaisPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		zilaisPoga.setFont(new Font("Inter", Font.PLAIN, 16));
		zilaisPoga.setBounds(560, 456, 115, 34);
		buvetEkrans.add(zilaisPoga);
		
		JToggleButton mocarellaPoga = new JToggleButton("Mocarella");
		mocarellaPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mocarellaPoga.setFont(new Font("Inter", Font.PLAIN, 16));
		mocarellaPoga.setBounds(695, 456, 115, 34);
		buvetEkrans.add(mocarellaPoga);
		
		JToggleButton bezsiersPoga = new JToggleButton("Bez");
		bezsiersPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bezsiersPoga.setFont(new Font("Inter", Font.PLAIN, 16));
		bezsiersPoga.setBounds(835, 456, 115, 34);
		buvetEkrans.add(bezsiersPoga);
		
		JToggleButton bezgalaPoga = new JToggleButton("Bez");
		bezgalaPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bezgalaPoga.setFont(new Font("Inter", Font.PLAIN, 16));
		bezgalaPoga.setBounds(835, 544, 115, 34);
		buvetEkrans.add(bezgalaPoga);
		
		JToggleButton vistaPoga = new JToggleButton("Vista");
		vistaPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		vistaPoga.setFont(new Font("Inter", Font.PLAIN, 16));
		vistaPoga.setBounds(695, 544, 115, 34);
		buvetEkrans.add(vistaPoga);
		
		JToggleButton skinkisPoga = new JToggleButton("Šķinķis");
		skinkisPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		skinkisPoga.setFont(new Font("Inter", Font.PLAIN, 16));
		skinkisPoga.setBounds(560, 544, 115, 34);
		buvetEkrans.add(skinkisPoga);
		
		JToggleButton salamisPoga = new JToggleButton("Salami");
		salamisPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		salamisPoga.setFont(new Font("Inter", Font.PLAIN, 16));
		salamisPoga.setBounds(425, 544, 115, 34);
		buvetEkrans.add(salamisPoga);
		
		JToggleButton tomatiPoga = new JToggleButton("Tomāti");
		tomatiPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tomatiPoga.setFont(new Font("Inter", Font.PLAIN, 16));
		tomatiPoga.setBounds(835, 633, 115, 34);
		buvetEkrans.add(tomatiPoga);
		
		JToggleButton ananasiPoga = new JToggleButton("Ananāsi");
		ananasiPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ananasiPoga.setFont(new Font("Inter", Font.PLAIN, 16));
		ananasiPoga.setBounds(695, 633, 115, 34);
		buvetEkrans.add(ananasiPoga);
		
		JToggleButton gurkiPoga = new JToggleButton("Gurķi");
		gurkiPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		gurkiPoga.setFont(new Font("Inter", Font.PLAIN, 16));
		gurkiPoga.setBounds(560, 633, 115, 34);
		buvetEkrans.add(gurkiPoga);
		
		JToggleButton senesPoga = new JToggleButton("Sēnes");
		senesPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		senesPoga.setFont(new Font("Inter", Font.PLAIN, 16));
		senesPoga.setBounds(425, 633, 115, 34);
		buvetEkrans.add(senesPoga);
		
		JToggleButton sipoliPoga = new JToggleButton("Sīpoli");
		sipoliPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sipoliPoga.setFont(new Font("Inter", Font.PLAIN, 16));
		sipoliPoga.setBounds(560, 678, 115, 34);
		buvetEkrans.add(sipoliPoga);
		
		JToggleButton jalapenoPoga = new JToggleButton("Jalapeno");
		jalapenoPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jalapenoPoga.setFont(new Font("Inter", Font.PLAIN, 16));
		jalapenoPoga.setBounds(425, 678, 115, 34);
		buvetEkrans.add(jalapenoPoga);
		
		JToggleButton bezpiedevasPoga = new JToggleButton("Bez");
		bezpiedevasPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bezpiedevasPoga.setFont(new Font("Inter", Font.PLAIN, 16));
		bezpiedevasPoga.setBounds(695, 678, 115, 34);
		buvetEkrans.add(bezpiedevasPoga);
		
		JLabel iedalijumiBilde = new JLabel("");
		iedalijumiBilde.setIcon(new ImageIcon(Main.class.getResource("/bildes/builder.png")));
		iedalijumiBilde.setBounds(425, 150, 463, 479);
		buvetEkrans.add(iedalijumiBilde);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(Main.class.getResource("/bildes/picasbuvetajs.png")));
		lblNewLabel_4.setBounds(349, 60, 297, 70);
		buvetEkrans.add(lblNewLabel_4);
		
		JLabel bgBilde = new JLabel("");
		bgBilde.setIcon(new ImageIcon(Main.class.getResource("/bildes/garlic.png")));
		bgBilde.setBounds(715, 649, 407, 253);
		buvetEkrans.add(bgBilde);
		
		JPanel pasutijumiEkrans = new JPanel();
		tabbedPane.addTab("Pasūtījumi", null, pasutijumiEkrans, "Apskati pašreizejos pasūtījumus un pasūtījumu vēsturi");
		
		JPanel grozsEkrans = new JPanel();
		tabbedPane.addTab("Grozs", null, grozsEkrans, null);
		
		/*Titullapa ekrāns beigas
		 * ------------------------------------------------------
		 Sana malas sākums */
		
		sideBar = new JPanel();
		sideBar.setVisible(false);
		sideBar.setBackground(Color.WHITE);
		contentPane.setLayer(sideBar, 5);
		sideBar.setBounds(0, 0, 339, 850);
		contentPane.add(sideBar);
		sideBar.setLayout(null);
		
		JLabel sveiksLabel = new JLabel("<html><body style='width: 200px'>Sveiks, Kaspars!");
		sveiksLabel.setFont(new Font("Inter Medium", Font.BOLD, 40));
		sveiksLabel.setBounds(16, 27, 304, 115);
		sideBar.add(sveiksLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Main.class.getResource("/bildes/linijas.png")));
		lblNewLabel_1.setBounds(16, 135, 255, 33);
		sideBar.add(lblNewLabel_1);
		
		JButton titullapaPoga = new JButton(" Titullapa");
		titullapaPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sideBar.setVisible(false);
			}
		});
		titullapaPoga.setIcon(new ImageIcon(Main.class.getResource("/bildes/titullapa_icon.png")));
		titullapaPoga.setContentAreaFilled(false);
		titullapaPoga.setBorder(null);
		titullapaPoga.setBackground(Color.WHITE);
		titullapaPoga.setHorizontalAlignment(SwingConstants.LEFT);
		titullapaPoga.setFont(new Font("Inter", Font.PLAIN, 12));
		titullapaPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		titullapaPoga.setBounds(12, 220, 292, 33);
		sideBar.add(titullapaPoga);
		
		JButton pasutijumiPoga = new JButton("   Pasūtījumi");
		pasutijumiPoga.setIcon(new ImageIcon(Main.class.getResource("/bildes/pasutijumi_icon.png")));
		pasutijumiPoga.setContentAreaFilled(false);
		pasutijumiPoga.setBorder(null);
		pasutijumiPoga.setBackground(Color.WHITE);
		pasutijumiPoga.setHorizontalAlignment(SwingConstants.LEFT);
		pasutijumiPoga.setFont(new Font("Inter", Font.PLAIN, 12));
		pasutijumiPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pasutijumiPoga.setBounds(16, 264, 292, 33);
		sideBar.add(pasutijumiPoga);
		
		JButton koduPoga = new JButton("   Izmantot atlaides kodu");
		koduPoga.setIcon(new ImageIcon(Main.class.getResource("/bildes/kodi_icon.png")));
		koduPoga.setContentAreaFilled(false);
		koduPoga.setBorder(null);
		koduPoga.setBackground(Color.WHITE);
		koduPoga.setHorizontalAlignment(SwingConstants.LEFT);
		koduPoga.setFont(new Font("Inter", Font.PLAIN, 12));
		koduPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		koduPoga.setBounds(16, 352, 292, 33);
		sideBar.add(koduPoga);
		
		JButton vesturePoga = new JButton(" Vēsture");
		vesturePoga.setIcon(new ImageIcon(Main.class.getResource("/bildes/history_icon.png")));
		vesturePoga.setContentAreaFilled(false);
		vesturePoga.setBorder(null);
		vesturePoga.setBackground(Color.WHITE);
		vesturePoga.setHorizontalAlignment(SwingConstants.LEFT);
		vesturePoga.setFont(new Font("Inter", Font.PLAIN, 12));
		vesturePoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		vesturePoga.setBounds(16, 308, 292, 33);
		sideBar.add(vesturePoga);
		
		JLabel sideBarImageLabel = new JLabel("");
		sideBarImageLabel.setIcon(new ImageIcon(Main.class.getResource("/bildes/sideimage.png")));
		sideBarImageLabel.setBounds(0, 470, 329, 408);
		sideBar.add(sideBarImageLabel);
		
		JButton exitPoga = new JButton("");
		exitPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		exitPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		exitPoga.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitPoga.setIcon(new ImageIcon(Main.class.getResource("/bildes/exit_hover.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				exitPoga.setIcon(new ImageIcon(Main.class.getResource("/bildes/exit.png")));
			}
		});
		exitPoga.setContentAreaFilled(false);
		exitPoga.setBorderPainted(false);
		exitPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		exitPoga.setIcon(new ImageIcon(Main.class.getResource("/bildes/exit.png")));
		exitPoga.setFocusable(false);
		contentPane.setLayer(exitPoga, 11);
		exitPoga.setBounds(955, 0, 35, 35);
		contentPane.add(exitPoga);
		
		
		
		
		
		
		
		
		
		
		
		cm20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cm20.isSelected())
				{
					//buvetApraksts.add("Tomātu mērce");
					buvetApraksts = buvetAprakstsDzest("30 cm");
					buvetApraksts = buvetAprakstsDzest("60 cm");
					buvetApraksts = buvetAprakstu("20 cm");
					buvetCena -= lastCena;
					aprakstsLabel.setText(buvetApraksts);
					buvetCena+=8.79;
					pirktPoga.setText(df.format(buvetCena)+"€");
					lastCena = 8.79;
					
				}else
					cm20.setSelected(true);
				
				cm30.setSelected(false);
				cm60.setSelected(false);
					
				
			}
		});
		
		cm20.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				cm20.setText("8.79€");
			}
			public void mouseExited(MouseEvent e) {
				cm20.setText("20 cm");
			}
		});
		
		cm30.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cm30.isSelected())
				{
					//buvetApraksts.add("Tomātu mērce");
					buvetApraksts = buvetAprakstsDzest("20 cm");
					buvetApraksts = buvetAprakstsDzest("60 cm");
					buvetApraksts = buvetAprakstu("30 cm");
					aprakstsLabel.setText(buvetApraksts);
					buvetCena -= lastCena;
					buvetCena += 12.59;
					lastCena = 12.59;
					pirktPoga.setText(df.format(buvetCena)+"€");
					
				}else
					cm30.setSelected(true);
				
				cm20.setSelected(false);
				cm60.setSelected(false);
					
				
			}
		});
		
		cm30.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				cm30.setText("12.59€");
			}
			public void mouseExited(MouseEvent e) {
				cm30.setText("30 cm");
			}
		});
		
		
		cm60.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cm60.isSelected())
				{
					//buvetApraksts.add("Tomātu mērce");
					buvetApraksts = buvetAprakstsDzest("20 cm");
					buvetApraksts = buvetAprakstsDzest("30 cm");
					buvetApraksts = buvetAprakstu("60 cm");
					aprakstsLabel.setText(buvetApraksts);
					buvetCena -= lastCena;
					buvetCena += 19.19;
					pirktPoga.setText(df.format(buvetCena)+"€");
					lastCena = 19.19;
					
				}else
					cm60.setSelected(true);
				
				cm20.setSelected(false);
				cm30.setSelected(false);
					
				
			}
		});
		
		cm60.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				cm60.setText("19.19€");
			}
			public void mouseExited(MouseEvent e) {
				cm60.setText("60 cm");
			}
		});
		
		/*	Izmera pogu darbību beigas
		 * ---------------------------------------------------------------------------------------
		 Mīklas pogu darbības sākums*/
		planaPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(planaPoga.isSelected())
				{
					buvetApraksts = buvetAprakstsDzest("Bieza mīkla");
					buvetApraksts = buvetAprakstsDzest("Vidēji bieza mīkla");
					buvetApraksts = buvetAprakstu("Plāna mīkla");
					aprakstsLabel.setText(buvetApraksts);
					
				}else
					planaPoga.setSelected(true);
				
				videjaPoga.setSelected(false);
				biezaPoga.setSelected(false);
					
				
			}
		});
		
		
		videjaPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(videjaPoga.isSelected())
				{
					//buvetApraksts.add("Tomātu mērce");
					buvetApraksts = buvetAprakstsDzest("Plāna mīkla");
					buvetApraksts = buvetAprakstsDzest("Bieza mīkla");
					buvetApraksts = buvetAprakstu("Vidēji bieza mīkla");
					aprakstsLabel.setText(buvetApraksts);
					
				}else
					videjaPoga.setSelected(true);
				
				planaPoga.setSelected(false);
				biezaPoga.setSelected(false);
					
				
			}
		});
		
		biezaPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(biezaPoga.isSelected())
				{
					//buvetApraksts.add("Tomātu mērce");
					buvetApraksts = buvetAprakstsDzest("Vidēji bieza mīkla");
					buvetApraksts = buvetAprakstsDzest("Plāna mīkla");
					buvetApraksts = buvetAprakstu("Bieza mīkla");
					aprakstsLabel.setText(buvetApraksts);
					
				}else
					biezaPoga.setSelected(true);
				
				planaPoga.setSelected(false);
				videjaPoga.setSelected(false);
					
				
			}
		});

		
		
		/*	Mīklas pogu darbības beigas
		 * ---------------------------------------------------------------------------------------
		 Mērču pogu darbības sākums*/
		
		//ja piespiesta poga cenai pieliek vai nonem 1.89 un parada to
		tomatuPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tomatuPoga.isSelected())
				{
					//buvetApraksts.add("Tomātu mērce");
					aprakstsLabel.setText(buvetAprakstu("Tomātu mērce"));
					buvetCena+=1.89;
					tomatuBilde.setVisible(true);
					pirktPoga.setText(df.format(buvetCena)+"€");
					bezmercePoga.setSelected(false);
					
				}else
				{
					aprakstsLabel.setText(buvetAprakstsDzest("Tomātu mērce"));
					buvetCena -= 1.89;
					pirktPoga.setText(df.format(buvetCena)+"€");
					tomatuBilde.setVisible(false);
				}
					
				
			}
		});
		
		tomatuPoga.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				tomatuPoga.setText("1.89€");
			}
			public void mouseExited(MouseEvent e) {
				tomatuPoga.setText("Tomātu");
			}
		});
		
		//ja piespiesta poga cenai pieliek vai nonem 3.29 un parada to
		bbqsPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(bbqsPoga.isSelected())
				{
					aprakstsLabel.setText(buvetAprakstu("BBQ mērce"));
					buvetCena+=3.29;
					bbqBilde.setVisible(true);
					pirktPoga.setText(df.format(buvetCena)+"€");
					bezmercePoga.setSelected(false);
				}else
				{
					aprakstsLabel.setText(buvetAprakstsDzest("BBQ mērce"));
					buvetCena -= 3.29;
					pirktPoga.setText(df.format(buvetCena)+"€");
					bbqBilde.setVisible(false);
				}
			}
		});
		
		bbqsPoga.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				bbqsPoga.setText("3.29€");
			}
			public void mouseExited(MouseEvent e) {
				bbqsPoga.setText("BBQ");
			}
		});
		
		//ja piespiesta poga nonem merces no picas
		bezmercePoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(bbqsPoga.isSelected())
				{
					aprakstsLabel.setText(buvetAprakstsDzest("BBQ mērce"));
					buvetCena -= 3.29;
					pirktPoga.setText(df.format(buvetCena)+"€");
					bbqBilde.setVisible(false);
					bbqsPoga.setSelected(false);
					
				}
				if(tomatuPoga.isSelected())
				{
					aprakstsLabel.setText(buvetAprakstsDzest("Tomātu mērce"));
					buvetCena -= 1.89;
					pirktPoga.setText(df.format(buvetCena)+"€");		
					tomatuBilde.setVisible(false);
					tomatuPoga.setSelected(false);
				}
				bezmercePoga.setSelected(true);
				
			}
		});
		
		
		/*	Mērču pogu darbības beigas
		 * ---------------------------------------------------------------------------------------
		 Siera pogu darbības sākums*/
		
		parastsPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(parastsPoga.isSelected())
				{
					aprakstsLabel.setText(buvetAprakstu("Siers"));
					buvetCena+=4.99;
					siersBilde.setVisible(true);
					pirktPoga.setText(df.format(buvetCena)+"€");
					bezsiersPoga.setSelected(false);
				}else
				{
					aprakstsLabel.setText(buvetAprakstsDzest("Siers"));
					buvetCena -= 4.99;
					pirktPoga.setText(df.format(buvetCena)+"€");
					siersBilde.setVisible(false);
				}
				
			}
		});
		
		parastsPoga.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				parastsPoga.setText("4.99€");
			}
			public void mouseExited(MouseEvent e) {
				parastsPoga.setText("Parastais");
			}
		});
		
		zilaisPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(zilaisPoga.isSelected())
				{
					aprakstsLabel.setText(buvetAprakstu("Zilais siers"));
					buvetCena+=2.49;
					zilaisBilde.setVisible(true);
					pirktPoga.setText(df.format(buvetCena)+"€");
					bezsiersPoga.setSelected(false);
				}else
				{
					aprakstsLabel.setText(buvetAprakstsDzest("Zilais siers"));
					buvetCena -= 2.49;
					pirktPoga.setText(df.format(buvetCena)+"€");
					zilaisBilde.setVisible(false);
				}
				
			}
		});
		
		zilaisPoga.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				zilaisPoga.setText("2.49€");
			}
			public void mouseExited(MouseEvent e) {
				zilaisPoga.setText("Zilais");
			}
		});
		
		mocarellaPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mocarellaPoga.isSelected())
				{
					aprakstsLabel.setText(buvetAprakstu("Mocarellas siers"));
					buvetCena+=2.49;
					mocarellaBilde.setVisible(true);
					pirktPoga.setText(df.format(buvetCena)+"€");
					bezsiersPoga.setSelected(false);
				}else
				{
					aprakstsLabel.setText(buvetAprakstsDzest("Mocarellas siers"));
					buvetCena -= 2.49;
					pirktPoga.setText(df.format(buvetCena)+"€");
					mocarellaBilde.setVisible(false);
				}
				
			}
		});
		
		mocarellaPoga.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				mocarellaPoga.setText("2.49€");
			}
			public void mouseExited(MouseEvent e) {
				mocarellaPoga.setText("Mocarella");
			}
		});
		
		bezsiersPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(parastsPoga.isSelected())
				{
					aprakstsLabel.setText(buvetAprakstsDzest("Siers"));
					buvetCena -= 4.99;
					pirktPoga.setText(df.format(buvetCena)+"€");
					siersBilde.setVisible(false);
					parastsPoga.setSelected(false);
					
				}
				if(mocarellaPoga.isSelected())
				{
					aprakstsLabel.setText(buvetAprakstsDzest("Mocarellas siers"));
					buvetCena -= 2.49;
					pirktPoga.setText(df.format(buvetCena)+"€");
					mocarellaBilde.setVisible(false);
					mocarellaPoga.setSelected(false);
					
				}
				if(zilaisPoga.isSelected())
				{
					aprakstsLabel.setText(buvetAprakstsDzest("Zilais siers"));
					buvetCena -= 2.49;
					pirktPoga.setText(df.format(buvetCena)+"€");		
					zilaisBilde.setVisible(false);
					zilaisPoga.setSelected(false);
				}
				bezsiersPoga.setSelected(true);
				
			}
		});
		
		
		/*	Siera pogu darbības beigas
		 * ---------------------------------------------------------------------------------------
		 Gaļas pogu darbības sākums*/
		
		
		
		
		salamisPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(salamisPoga.isSelected())
				{
					aprakstsLabel.setText(buvetAprakstu("Salami"));
					buvetCena+=3.99;
					salamisBilde.setVisible(true);
					pirktPoga.setText(df.format(buvetCena)+"€");
					bezgalaPoga.setSelected(false);
				}else
				{
					aprakstsLabel.setText(buvetAprakstsDzest("Salami"));
					buvetCena -= 3.99;
					pirktPoga.setText(df.format(buvetCena)+"€");
					salamisBilde.setVisible(false);
				}
				
			}
		});
		
		salamisPoga.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				salamisPoga.setText("3.99€");
			}
			public void mouseExited(MouseEvent e) {
				salamisPoga.setText("Salami");
			}
		});
		
		
		skinkisPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(skinkisPoga.isSelected())
				{
					aprakstsLabel.setText(buvetAprakstu("Šķiņķis"));
					buvetCena +=4.19;
					skinkisBilde.setVisible(true);
					pirktPoga.setText(df.format(buvetCena)+"€");
					bezgalaPoga.setSelected(false);
				}else
				{
					aprakstsLabel.setText(buvetAprakstsDzest("Šķiņķis"));
					buvetCena -= 4.19;
					pirktPoga.setText(df.format(buvetCena)+"€");
					skinkisBilde.setVisible(false);
				}
				
			}
		});
		
		skinkisPoga.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				skinkisPoga.setText("4.19€");
			}
			public void mouseExited(MouseEvent e) {
				skinkisPoga.setText("Šķiņķis");
			}
		});
		
		vistaPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(vistaPoga.isSelected())
				{
					aprakstsLabel.setText(buvetAprakstu("Vista"));
					buvetCena += 4.59;
					vistaBilde.setVisible(true);
					pirktPoga.setText(df.format(buvetCena)+"€");
					bezgalaPoga.setSelected(false);
				}else
				{
					aprakstsLabel.setText(buvetAprakstsDzest("Vista"));
					buvetCena -= 4.59;
					pirktPoga.setText(df.format(buvetCena)+"€");
					vistaBilde.setVisible(false);
				}
				
			}
		});
		
		vistaPoga.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				vistaPoga.setText("4.59€");
			}
			public void mouseExited(MouseEvent e) {
				vistaPoga.setText("Vista");
			}
		});
		
		
		
		bezgalaPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(salamisPoga.isSelected())
				{
					aprakstsLabel.setText(buvetAprakstsDzest("Salami"));
					buvetCena -= 3.99;
					pirktPoga.setText(df.format(buvetCena)+"€");
					salamisBilde.setVisible(false);
					salamisPoga.setSelected(false);
					
				}
				if(skinkisPoga.isSelected())
				{
					aprakstsLabel.setText(buvetAprakstsDzest("Šķiņķis"));
					buvetCena -= 4.19;
					pirktPoga.setText(df.format(buvetCena)+"€");
					skinkisBilde.setVisible(false);
					skinkisPoga.setSelected(false);
					
				}
				if(vistaPoga.isSelected())
				{
					aprakstsLabel.setText(buvetAprakstsDzest("Vista"));
					buvetCena -= 4.59;
					pirktPoga.setText(df.format(buvetCena)+"€");		
					vistaBilde.setVisible(false);
					vistaPoga.setSelected(false);
				}
				bezgalaPoga.setSelected(true);
				
			}
		});
		
		
		/*	Gaļas pogu darbības beigas
		 * ---------------------------------------------------------------------------------------
		 Piedevu pogu darbības sākums*/
		
		senesPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(senesPoga.isSelected())
				{
					aprakstsLabel.setText(buvetAprakstu("Sēnes"));
					buvetCena += 2.19;
					senesBilde.setVisible(true);
					pirktPoga.setText(df.format(buvetCena)+"€");
					bezpiedevasPoga.setSelected(false);
				}else
				{
					aprakstsLabel.setText(buvetAprakstsDzest("Sēnes"));
					buvetCena -= 2.19;
					pirktPoga.setText(df.format(buvetCena)+"€");
					senesBilde.setVisible(false);
				}
				
			}
		});
		
		senesPoga.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				senesPoga.setText("2.19€");
			}
			public void mouseExited(MouseEvent e) {
				senesPoga.setText("Sēnes");
			}
		});
		
		gurkiPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(gurkiPoga.isSelected())
				{
					aprakstsLabel.setText(buvetAprakstu("Marinēti gurķi"));
					buvetCena += 2.19;
					gurkiBilde.setVisible(true);
					pirktPoga.setText(df.format(buvetCena)+"€");
					bezpiedevasPoga.setSelected(false);
				}else
				{
					aprakstsLabel.setText(buvetAprakstsDzest("Marinēti gurķi"));
					buvetCena -= 2.19;
					pirktPoga.setText(df.format(buvetCena)+"€");
					gurkiBilde.setVisible(false);
				}
				
			}
		});
		
		gurkiPoga.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				gurkiPoga.setText("2.19€");
			}
			public void mouseExited(MouseEvent e) {
				gurkiPoga.setText("Gurķi");
			}
		});
		
		ananasiPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ananasiPoga.isSelected())
				{
					aprakstsLabel.setText(buvetAprakstu("Ananāsi"));
					buvetCena += 2.19;
					ananasiBilde.setVisible(true);
					pirktPoga.setText(df.format(buvetCena)+"€");
					bezpiedevasPoga.setSelected(false);
				}else
				{
					aprakstsLabel.setText(buvetAprakstsDzest("Ananāsi"));
					buvetCena -= 2.19;
					pirktPoga.setText(df.format(buvetCena)+"€");
					ananasiBilde.setVisible(false);
				}
				
			}
		});
		
		ananasiPoga.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ananasiPoga.setText("2.19€");
			}
			public void mouseExited(MouseEvent e) {
				ananasiPoga.setText("Ananāsi");
			}
		});
		
		tomatiPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tomatiPoga.isSelected())
				{
					aprakstsLabel.setText(buvetAprakstu("Tomāti"));
					buvetCena += 2.19;
					tomatiBilde.setVisible(true);
					pirktPoga.setText(df.format(buvetCena)+"€");
					bezpiedevasPoga.setSelected(false);
				}else
				{
					aprakstsLabel.setText(buvetAprakstsDzest("Tomāti"));
					buvetCena -= 2.19;
					pirktPoga.setText(df.format(buvetCena)+"€");
					tomatiBilde.setVisible(false);
				}
				
			}
		});
		
		tomatiPoga.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				tomatiPoga.setText("2.19€");
			}
			public void mouseExited(MouseEvent e) {
				tomatiPoga.setText("Tomāti");
			}
		});
		
		jalapenoPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(jalapenoPoga.isSelected())
				{
					aprakstsLabel.setText(buvetAprakstu("Jalapeno pipari"));
					buvetCena += 2.19;
					jalapenoBilde.setVisible(true);
					pirktPoga.setText(df.format(buvetCena)+"€");
					bezpiedevasPoga.setSelected(false);
				}else
				{
					aprakstsLabel.setText(buvetAprakstsDzest("Jalapeno pipari"));
					buvetCena -= 2.19;
					pirktPoga.setText(df.format(buvetCena)+"€");
					jalapenoBilde.setVisible(false);
				}
				
			}
		});
		
		jalapenoPoga.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				jalapenoPoga.setText("2.19€");
			}
			public void mouseExited(MouseEvent e) {
				jalapenoPoga.setText("Jalapeno");
			}
		});
		
		sipoliPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(sipoliPoga.isSelected())
				{
					aprakstsLabel.setText(buvetAprakstu("Sīpoli"));
					buvetCena += 0.49;
					sipoliBilde.setVisible(true);
					pirktPoga.setText(df.format(buvetCena)+"€");
					bezpiedevasPoga.setSelected(false);
				}else
				{
					aprakstsLabel.setText(buvetAprakstsDzest("Sīpoli"));
					buvetCena -= 0.49;
					pirktPoga.setText(df.format(buvetCena)+"€");
					sipoliBilde.setVisible(false);
				}
				
			}
		});
		
		sipoliPoga.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				sipoliPoga.setText("0.49€");
			}
			public void mouseExited(MouseEvent e) {
				sipoliPoga.setText("Sīpoli");
			}
		});
		
		bezpiedevasPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(senesPoga.isSelected())
				{
					aprakstsLabel.setText(buvetAprakstsDzest("Sēnes"));
					buvetCena -= 2.19;
					pirktPoga.setText(df.format(buvetCena)+"€");
					senesBilde.setVisible(false);
					senesPoga.setSelected(false);
					
				}
				if(gurkiPoga.isSelected())
				{
					aprakstsLabel.setText(buvetAprakstsDzest("Marinēti gurķi"));
					buvetCena -= 2.19;
					pirktPoga.setText(df.format(buvetCena)+"€");
					gurkiBilde.setVisible(false);
					gurkiPoga.setSelected(false);
					
				}
				if(ananasiPoga.isSelected())
				{
					aprakstsLabel.setText(buvetAprakstsDzest("Ananāsi"));
					buvetCena -= 2.19;
					pirktPoga.setText(df.format(buvetCena)+"€");
					ananasiBilde.setVisible(false);
					ananasiPoga.setSelected(false);
					
				}
				if(tomatiPoga.isSelected())
				{
					aprakstsLabel.setText(buvetAprakstsDzest("Tomāti"));
					buvetCena -= 2.19;
					pirktPoga.setText(df.format(buvetCena)+"€");
					tomatiBilde.setVisible(false);
					tomatiPoga.setSelected(false);
					
				}
				if(jalapenoPoga.isSelected())
				{
					aprakstsLabel.setText(buvetAprakstsDzest("Jalapeno pipari"));
					buvetCena -= 2.19;
					pirktPoga.setText(df.format(buvetCena)+"€");
					jalapenoBilde.setVisible(false);
					jalapenoPoga.setSelected(false);
					
				}
				if(sipoliPoga.isSelected())
				{
					aprakstsLabel.setText(buvetAprakstsDzest("Sīpoli"));
					buvetCena -= 0.49;
					pirktPoga.setText(df.format(buvetCena)+"€");
					sipoliBilde.setVisible(false);
					sipoliPoga.setSelected(false);
					
				}
				
				
				bezpiedevasPoga.setSelected(true);
				
			}
		});
		
		
		/*Titullapa ekrāns beigas
		 * ------------------------------------------------------
		 Sana malas sākums */
		
		
		// picu skaits x cena
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				double cena = Double.parseDouble(pirktPoga.getText().substring(0, pirktPoga.getText().length()-1));
				int reizinatajs = Integer.parseInt( spinner.getValue().toString());
				double originalaCena;
				
				if(reizinatajs > pedejaVertiba)
				{
					originalaCena = cena / (reizinatajs-1);
					pirktPoga.setText(df.format(originalaCena * reizinatajs)+"€");
				}
				else
				{
					originalaCena = cena / (reizinatajs+1);
					pirktPoga.setText(df.format(cena - originalaCena)+"€");
				}
				pedejaVertiba = reizinatajs;
			}
		});
//		
		
		pirktPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		
		
		
		
	
		
		
	}
	
	static String buvetAprakstu(String sastavdala) {
		String temp = "<html><body style='width: 200px'>";
		spinner.setValue(1);
		
		//izmeru parāda pirmo un biezumu kā otro
		if(sastavdala.equals("20 cm") || sastavdala.equals("30 cm") || sastavdala.equals("60 cm"))
		{
			 Collections.reverse(buvetSastavdalas);
			 buvetSastavdalas.add(sastavdala);
			 Collections.reverse(buvetSastavdalas);
		}
		else if((sastavdala.equals("Plāna mīkla") || sastavdala.equals("Vidēji bieza mīkla") || sastavdala.equals("Bieza mīkla")) && buvetSastavdalas.size() > 1)
		{
			String tmp = buvetSastavdalas.get(1);
			buvetSastavdalas.set(1, sastavdala);
			buvetSastavdalas.add(tmp);
		}
		else 
		buvetSastavdalas.add(sastavdala);
		
		for(int i = 0; i < buvetSastavdalas.size(); i++)
		{
			if(i == 0)
				temp += buvetSastavdalas.get(i)+", ";
			else if(i == buvetSastavdalas.size()-1)
				temp += buvetSastavdalas.get(i)+"!";
			else if(i == buvetSastavdalas.size()-2)
				temp += buvetSastavdalas.get(i)+" un ";
			else temp += buvetSastavdalas.get(i)+", ";
		}
		
		return temp;
	}
	
	static String buvetAprakstsDzest (String sastavdala) {
		buvetSastavdalas.remove(sastavdala);
		spinner.setValue(1);
		String temp = "<html><body style='width: 200px'>";
		if(buvetSastavdalas.size() == 1)
			return buvetSastavdalas.get(0)+"!";
		else {
		for(int i = 0; i < buvetSastavdalas.size(); i++)
		{
			if(i == 0)
				temp += buvetSastavdalas.get(i)+", ";
			else if(i == buvetSastavdalas.size()-1)
				temp += buvetSastavdalas.get(i)+"!";
			else if(i == buvetSastavdalas.size()-2)
				temp += buvetSastavdalas.get(i)+" un ";
			else temp += buvetSastavdalas.get(i)+", ";
		}
		}
		return temp;
	}
}
