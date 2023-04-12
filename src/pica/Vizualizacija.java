package pica;

import com.formdev.flatlaf.FlatClientProperties;
import org.json.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;

import static pica.Run.*;

public class Vizualizacija extends JFrame {

	static boolean pievienots = false;
	private JLayeredPane contentPane;
	private JLabel tomatiBilde;
	private JLabel tomatuBilde;
	static double buvetCena = 8.79, lastCena = 8.79, gatavaCena = 9.99, lastGatavaCena = 9.99, gatavaBaseCena,
			pedejaVertiba;
	static String temp = "", gatavaNosaukums = "";

	static String vesturesTeksts = lasit();
	static ArrayList<String> buvetSastavdalas = new ArrayList<>(), gatavaSastavdalas = new ArrayList<>();
	static DefaultListModel<Pica> model = new DefaultListModel<>();
	static String buvetApraksts = "", gatavaApraksts = "", adrese = "";
	private static JSpinner spinner = new JSpinner();

	DecimalFormat df = new DecimalFormat("#.##");
	public static JComboBox<String> adreseLauks;
	public static JLabel slot1Procenti, slot2Procenti, slot3Procenti;
	public static JLabel slot1Nosaukums, slot2Nosaukums, slot3Nosaukums;
	public static JProgressBar slot1Progress, slot2Progress, slot3Progress;
	public static JLabel slot1Apraksts, slot2Apraksts, slot3Apraksts;
	public static JLabel slot1Bilde, slot2Bilde, slot3Bilde, gaidaSkaitsLabel, pasutijumuSkaitsLabel;
	public static JToggleButton gatavacm20, gatavacm30, gatavacm60, gatavaplana, gatavavideja, gatavabieza;
	public static JPanel slot1, slot2, slot3;
	public static JTextField uzvardsIevade;
	public static JRadioButton choiceKarte, choiceKarte_1;
	public static JRadioButton choiceSkaidra;
	public static JButton registerButton;
	public static JTextField vardsIevade, numursIevade, vardsDalejaIevade;
	public static JPanel picuProgressPanel;

	// Vizualizācija
	@SuppressWarnings("unchecked")
	public Vizualizacija() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds((1920 - 1000) / 2, (1080 - 850) / 2, 1000, 860);
		contentPane = new JLayeredPane();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton backPoga = new JButton("");
		backPoga.setVisible(false);
		contentPane.setLayer(backPoga, 12);
		backPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		backPoga.setIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/back.png")));
		backPoga.setFocusable(false);
		backPoga.setContentAreaFilled(false);
		backPoga.setBorderPainted(false);
		backPoga.setBounds(10, 0, 35, 35);
		contentPane.add(backPoga);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.setLayer(tabbedPane, 12);
		tabbedPane.setBounds(0, 0, 1000, 900);
		contentPane.add(tabbedPane);

		/*
		 * Sākuma ekrāns sākums ------------------------------------------------------
		 */

		/*
		 * Sākuma ekrāns beigas ------------------------------------------------------
		 * Registrācijas ekrāns sākums
		 */

		JPanel registracijaEkrans = new JPanel();
		registracijaEkrans.setVisible(false);
		registracijaEkrans.setBackground(new Color(255, 255, 255));
		contentPane.setLayer(registracijaEkrans, 12);
		registracijaEkrans.setBounds(0, 0, 1000, 870);
		contentPane.add(registracijaEkrans);
		registracijaEkrans.setLayout(null);

		JLabel registracijaLogo = new JLabel("");
		registracijaLogo.setIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/majamLogo.png")));
		registracijaLogo.setBounds(282, 70, 435, 99);
		registracijaEkrans.add(registracijaLogo);

		JLabel vardsIevadeLabel = new JLabel("Vārds");
		vardsIevadeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		vardsIevadeLabel.setBounds(318, 191, 54, 20);
		registracijaEkrans.add(vardsIevadeLabel);

		vardsIevade = new JTextField();
		vardsIevade.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		vardsIevade.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		vardsIevade.setBounds(318, 214, 155, 30);
		registracijaEkrans.add(vardsIevade);
		vardsIevade.setColumns(10);

		uzvardsIevade = new JTextField();
		uzvardsIevade.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		uzvardsIevade.setColumns(10);
		uzvardsIevade.setBounds(533, 214, 155, 30);
		registracijaEkrans.add(uzvardsIevade);

		JLabel uzvardsLabel = new JLabel("Uzvārds");
		uzvardsLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		uzvardsLabel.setBounds(533, 191, 81, 20);
		registracijaEkrans.add(uzvardsLabel);

		numursIevade = new JTextField();
		numursIevade.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		numursIevade.setColumns(10);
		numursIevade.setBounds(318, 284, 370, 30);
		registracijaEkrans.add(numursIevade);

		JLabel numursIevadeLabel = new JLabel("Telefona numurs");
		numursIevadeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		numursIevadeLabel.setBounds(318, 261, 155, 20);
		registracijaEkrans.add(numursIevadeLabel);

		JLabel apmaksaLabel = new JLabel("Apmaksas veids");
		apmaksaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		apmaksaLabel.setBounds(318, 325, 155, 20);
		registracijaEkrans.add(apmaksaLabel);

		registerButton = new JButton("Turpināt");
		registerButton.setForeground(Color.WHITE);
		registerButton.setBackground(new Color(205, 70, 49));
		registerButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		registerButton.setBounds(546, 422, 142, 42);
		registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		registracijaEkrans.add(registerButton);

		choiceKarte = new JRadioButton("- Karte");
		choiceKarte.setBackground(new Color(255, 255, 255));
		choiceKarte.setSelectedIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/checkbox_checked.png")));
		choiceKarte.setIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/checkbox.png")));
		choiceKarte.setSelected(true);
		choiceKarte.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		choiceKarte.setBounds(318, 352, 90, 30);
		registracijaEkrans.add(choiceKarte);
		choiceKarte.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		choiceSkaidra = new JRadioButton("- Skaidrā naudā");
		choiceSkaidra.setBackground(new Color(255, 255, 255));
		choiceSkaidra.setIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/checkbox.png")));
		choiceSkaidra.setSelectedIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/checkbox_checked.png")));
		choiceSkaidra.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		choiceSkaidra.setBounds(417, 352, 197, 30);
		registracijaEkrans.add(choiceSkaidra);
		choiceSkaidra.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		JLabel registerBilde = new JLabel("");
		registerBilde.setIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/registracijaBilde.png")));
		registerBilde.setBounds(207, 252, 585, 587);
		registracijaEkrans.add(registerBilde);

		JPanel registracijaDalejaEkrans = new JPanel();
		registracijaDalejaEkrans.setVisible(false);
		contentPane.setLayer(registracijaDalejaEkrans, 12);
		registracijaDalejaEkrans.setLayout(null);
		registracijaDalejaEkrans.setBackground(new Color(255, 255, 255));
		registracijaDalejaEkrans.setBounds(0, 0, 1000, 850);
		contentPane.add(registracijaDalejaEkrans);

		JLabel registracijaLogo_1 = new JLabel("");
		registracijaLogo_1.setIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/vietasLogo.png")));
		registracijaLogo_1.setBounds(312, 70, 376, 99);
		registracijaDalejaEkrans.add(registracijaLogo_1);

		JLabel vardsIevadeLabel_1 = new JLabel("Vārds");
		vardsIevadeLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		vardsIevadeLabel_1.setBounds(318, 191, 54, 20);
		registracijaDalejaEkrans.add(vardsIevadeLabel_1);

		vardsDalejaIevade = new JTextField();
		vardsDalejaIevade.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		vardsDalejaIevade.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		vardsDalejaIevade.setColumns(10);
		vardsDalejaIevade.setBounds(318, 214, 370, 30);
		registracijaDalejaEkrans.add(vardsDalejaIevade);

		JLabel apmaksaLabel_1 = new JLabel("Apmaksas veids");
		apmaksaLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		apmaksaLabel_1.setBounds(318, 251, 155, 20);
		registracijaDalejaEkrans.add(apmaksaLabel_1);

		JButton regPoga = new JButton("Turpināt");
		regPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		regPoga.setForeground(Color.WHITE);
		regPoga.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		regPoga.setBackground(new Color(205, 70, 49));
		regPoga.setBounds(546, 357, 142, 42);
		registracijaDalejaEkrans.add(regPoga);

		choiceKarte_1 = new JRadioButton("- Karte");
		choiceKarte_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		choiceKarte_1.setIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/checkbox.png")));
		choiceKarte_1.setSelectedIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/checkbox_checked.png")));
		choiceKarte_1.setSelected(true);
		choiceKarte_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		choiceKarte_1.setBounds(318, 278, 90, 30);
		registracijaDalejaEkrans.add(choiceKarte_1);

		JRadioButton choiceSkaidra_1 = new JRadioButton("- Skaidrā naudā");
		choiceSkaidra_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		choiceSkaidra_1.setIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/checkbox.png")));
		choiceSkaidra_1.setSelectedIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/checkbox_checked.png")));
		choiceSkaidra_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		choiceSkaidra_1.setBounds(417, 278, 197, 30);
		registracijaDalejaEkrans.add(choiceSkaidra_1);

		JLabel registerBilde_1 = new JLabel("");
		registerBilde_1.setIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/registracijaBilde.png")));
		registerBilde_1.setBounds(205, 220, 585, 587);
		registracijaDalejaEkrans.add(registerBilde_1);

		/*
		 * Registrācijas ekrāns beigas
		 * ------------------------------------------------------ Titullapas ekrāns
		 * sākums
		 */

		JPanel TitullapaEkrans = new JPanel();
		TitullapaEkrans.setBackground(new Color(240, 240, 240));
		contentPane.setLayer(TitullapaEkrans, 4);
		TitullapaEkrans.setBounds(0, 0, 1000, 850);
		tabbedPane.addTab("Gatavās picas", null, TitullapaEkrans, "Izvēle ar jau gatavām picām, kuras var pasūtīt!");
		// contentPane.add(TitullapaEkrans);
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
		bbqPoga.setIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/bbq.png")));
		bbqPoga.setBounds(53, 457, 229, 200);
		TitullapaEkrans.add(bbqPoga);

		JButton ciliPoga = new JButton("");
		ciliPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ciliPoga.setContentAreaFilled(false);
		ciliPoga.setBorderPainted(false);
		ciliPoga.setIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/Cili.png")));
		ciliPoga.setBounds(376, 457, 232, 200);
		TitullapaEkrans.add(ciliPoga);

		JButton bekonaPoga = new JButton("");
		bekonaPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bekonaPoga.setContentAreaFilled(false);
		bekonaPoga.setBorderPainted(false);
		bekonaPoga.setIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/bekona.png")));
		bekonaPoga.setBounds(710, 457, 224, 200);
		TitullapaEkrans.add(bekonaPoga);

		JButton sieraPoga = new JButton("");
		sieraPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sieraPoga.setContentAreaFilled(false);
		sieraPoga.setBorderPainted(false);
		sieraPoga.setIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/siera.png")));
		sieraPoga.setBounds(705, 196, 229, 200);
		TitullapaEkrans.add(sieraPoga);

		JButton laukuPoga = new JButton("");
		laukuPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		laukuPoga.setContentAreaFilled(false);
		laukuPoga.setBorderPainted(false);
		laukuPoga.setIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/laukup.png")));
		laukuPoga.setBounds(376, 196, 231, 200);
		TitullapaEkrans.add(laukuPoga);

		JButton salamiPoga = new JButton("");
		salamiPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		salamiPoga.setContentAreaFilled(false);
		salamiPoga.setBorderPainted(false);
		salamiPoga.setIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/salami.png")));
		salamiPoga.setBounds(56, 196, 223, 200);
		TitullapaEkrans.add(salamiPoga);

		JLabel titullapaBg = new JLabel("");
		titullapaBg.setIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/BG.png")));
		titullapaBg.setBounds(0, 30, 1000, 850);
		TitullapaEkrans.add(titullapaBg);

		JPanel buvetEkrans = new JPanel();
		buvetEkrans.setBackground(new Color(240, 240, 240));
		tabbedPane.addTab("Būvē savu picu", null, buvetEkrans,
				"Ja neatradi gatavu pica kura tevi apmierina, vari buvēt savu picu!");
		buvetEkrans.setLayout(null);

		tomatiBilde = new JLabel("");
		tomatiBilde.setVisible(false);

		JLabel bbqBilde = new JLabel("");
		bbqBilde.setVisible(false);
		bbqBilde.setFocusable(false);
		bbqBilde.setIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/pica_layers/bbq.png")));
		bbqBilde.setBounds(38, 158, 307, 307);
		buvetEkrans.add(bbqBilde);

		JLabel salamisBilde = new JLabel("");
		salamisBilde.setVisible(false);
		salamisBilde.setFocusable(false);
		salamisBilde.setIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/pica_layers/salami.png")));
		salamisBilde.setBounds(38, 158, 307, 307);
		buvetEkrans.add(salamisBilde);
		tomatiBilde.setFocusable(false);
		tomatiBilde.setIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/pica_layers/tomati.png")));
		tomatiBilde.setBounds(38, 158, 307, 307);
		buvetEkrans.add(tomatiBilde);

		JLabel jalapenoBilde = new JLabel("");
		jalapenoBilde.setVisible(false);
		jalapenoBilde.setFocusable(false);
		jalapenoBilde.setIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/pica_layers/jalapeno.png")));
		jalapenoBilde.setBounds(38, 158, 307, 307);
		buvetEkrans.add(jalapenoBilde);

		JLabel skinkisBilde = new JLabel("");
		skinkisBilde.setVisible(false);
		skinkisBilde.setFocusable(false);
		skinkisBilde.setIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/pica_layers/skinkis.png")));
		skinkisBilde.setBounds(38, 158, 307, 307);
		buvetEkrans.add(skinkisBilde);

		JLabel ananasiBilde = new JLabel("");
		ananasiBilde.setVisible(false);
		ananasiBilde.setFocusable(false);
		ananasiBilde.setIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/pica_layers/ananas.png")));
		ananasiBilde.setBounds(38, 158, 307, 307);
		buvetEkrans.add(ananasiBilde);

		JLabel gurkiBilde = new JLabel("");
		gurkiBilde.setVisible(false);
		gurkiBilde.setFocusable(false);
		gurkiBilde.setIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/pica_layers/gurki.png")));
		gurkiBilde.setBounds(38, 158, 307, 307);
		buvetEkrans.add(gurkiBilde);

		JLabel senesBilde = new JLabel("");
		senesBilde.setVisible(false);
		senesBilde.setFocusable(false);
		senesBilde.setIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/pica_layers/senes.png")));
		senesBilde.setBounds(38, 158, 307, 307);
		buvetEkrans.add(senesBilde);

		JLabel sipoliBilde = new JLabel("");
		sipoliBilde.setVisible(false);
		sipoliBilde.setFocusable(false);
		sipoliBilde.setIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/pica_layers/sipoli.png")));
		sipoliBilde.setBounds(38, 158, 307, 307);
		buvetEkrans.add(sipoliBilde);

		JLabel vistaBilde = new JLabel("");
		vistaBilde.setVisible(false);
		vistaBilde.setFocusable(false);
		vistaBilde.setIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/pica_layers/vista.png")));
		vistaBilde.setBounds(38, 158, 307, 307);
		buvetEkrans.add(vistaBilde);

		JLabel mocarellaBilde = new JLabel("");
		mocarellaBilde.setVisible(false);
		mocarellaBilde.setFocusable(false);
		mocarellaBilde.setIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/pica_layers/mozzarella.png")));
		mocarellaBilde.setBounds(38, 158, 307, 307);
		buvetEkrans.add(mocarellaBilde);

		JLabel zilaisBilde = new JLabel("");
		zilaisBilde.setVisible(false);
		zilaisBilde.setFocusable(false);
		zilaisBilde.setIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/pica_layers/zilais.png")));
		zilaisBilde.setBounds(38, 158, 307, 307);
		buvetEkrans.add(zilaisBilde);

		JLabel siersBilde = new JLabel("");
		siersBilde.setVisible(false);
		siersBilde.setFocusable(false);
		siersBilde.setIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/pica_layers/siers.png")));
		siersBilde.setBounds(38, 158, 307, 307);
		buvetEkrans.add(siersBilde);

		tomatuBilde = new JLabel("");
		tomatuBilde.setVisible(false);
		tomatuBilde.setFocusable(false);
		tomatuBilde.setIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/pica_layers/tomatumerce.png")));
		tomatuBilde.setBounds(38, 158, 307, 307);
		buvetEkrans.add(tomatuBilde);

		JLabel miklaBilde = new JLabel("");
		miklaBilde.setFocusable(false);
		miklaBilde.setIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/pica_layers/mikla.png")));
		miklaBilde.setBounds(38, 158, 307, 307);
		buvetEkrans.add(miklaBilde);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(217, 217, 217));
		panel_1.setBounds(38, 487, 307, 225);
		buvetEkrans.add(panel_1);
		panel_1.setLayout(null);

		JLabel aprakstsLabel = new JLabel("<html><body style='width: 220px'>" + buvetApraksts);
		aprakstsLabel.setVerticalAlignment(SwingConstants.TOP);
		aprakstsLabel.setBounds(16, 18, 275, 128);
		aprakstsLabel.setHorizontalAlignment(SwingConstants.LEFT);
		aprakstsLabel.setForeground(new Color(79, 79, 79));
		aprakstsLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel_1.add(aprakstsLabel);

		JButton pirktPoga = new JButton("Pasūtīt");
		pirktPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pirktPoga.setLocation(123, 157);
		pirktPoga.setSize(new Dimension(173, 41));
		pirktPoga.setForeground(Color.WHITE);
		pirktPoga.setBackground(new Color(205, 70, 49));
		pirktPoga.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel_1.add(pirktPoga);

		spinner = new JSpinner();
		spinner.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		spinner.setModel(new SpinnerNumberModel(1, 1, 9, 1));
		spinner.setFocusCycleRoot(true);
		spinner.setFocusTraversalPolicyProvider(true);
		spinner.setFocusable(false);
		spinner.setFont(new Font("Segoe UI", Font.BOLD, 14));
		spinner.setBounds(58, 160, 55, 34);
		panel_1.add(spinner);

		JToggleButton cm20 = new JToggleButton("20cm");
		cm20.setSelected(true);
		cm20.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cm20.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		cm20.setBounds(425, 187, 115, 34);
		cm20.putClientProperty("JToggleButton.buttonType", "roundRect");
		buvetEkrans.add(cm20);

		JToggleButton cm30 = new JToggleButton("30cm");
		cm30.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cm30.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		cm30.setBounds(560, 187, 115, 34);
		buvetEkrans.add(cm30);

		JToggleButton cm60 = new JToggleButton("60cm");
		cm60.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cm60.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		cm60.setBounds(695, 187, 115, 34);
		buvetEkrans.add(cm60);

		JToggleButton planaPoga = new JToggleButton("Plāna");
		planaPoga.setSelected(true);
		planaPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		planaPoga.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		planaPoga.setBounds(425, 282, 115, 34);
		buvetEkrans.add(planaPoga);

		JToggleButton videjaPoga = new JToggleButton("Vidēja");
		videjaPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		videjaPoga.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		videjaPoga.setBounds(560, 282, 115, 34);
		buvetEkrans.add(videjaPoga);

		JToggleButton biezaPoga = new JToggleButton("Bieza");
		biezaPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		biezaPoga.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		biezaPoga.setBounds(695, 282, 115, 34);
		buvetEkrans.add(biezaPoga);

		JToggleButton tomatuPoga = new JToggleButton("Tomātu");
		tomatuPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tomatuPoga.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tomatuPoga.setBounds(425, 365, 115, 34);
		buvetEkrans.add(tomatuPoga);

		JToggleButton bbqsPoga = new JToggleButton("BBQ");
		bbqsPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bbqsPoga.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		bbqsPoga.setBounds(560, 365, 115, 34);
		buvetEkrans.add(bbqsPoga);

		JToggleButton bezmercePoga = new JToggleButton("Bez");
		bezmercePoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bezmercePoga.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		bezmercePoga.setBounds(695, 365, 115, 34);
		buvetEkrans.add(bezmercePoga);

		JToggleButton parastsPoga = new JToggleButton("Parastais");
		parastsPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		parastsPoga.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		parastsPoga.setBounds(425, 456, 115, 34);
		buvetEkrans.add(parastsPoga);

		JToggleButton zilaisPoga = new JToggleButton("Zilais");
		zilaisPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		zilaisPoga.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		zilaisPoga.setBounds(560, 456, 115, 34);
		buvetEkrans.add(zilaisPoga);

		JToggleButton mocarellaPoga = new JToggleButton("Mocarella");
		mocarellaPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mocarellaPoga.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mocarellaPoga.setBounds(695, 456, 115, 34);
		buvetEkrans.add(mocarellaPoga);

		JToggleButton bezsiersPoga = new JToggleButton("Bez");
		bezsiersPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bezsiersPoga.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		bezsiersPoga.setBounds(835, 456, 115, 34);
		buvetEkrans.add(bezsiersPoga);

		JToggleButton bezgalaPoga = new JToggleButton("Bez");
		bezgalaPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bezgalaPoga.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		bezgalaPoga.setBounds(835, 544, 115, 34);
		buvetEkrans.add(bezgalaPoga);

		JToggleButton vistaPoga = new JToggleButton("Vista");
		vistaPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		vistaPoga.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		vistaPoga.setBounds(695, 544, 115, 34);
		buvetEkrans.add(vistaPoga);

		JToggleButton skinkisPoga = new JToggleButton("Šķinķis");
		skinkisPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		skinkisPoga.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		skinkisPoga.setBounds(560, 544, 115, 34);
		buvetEkrans.add(skinkisPoga);

		JToggleButton salamisPoga = new JToggleButton("Salami");
		salamisPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		salamisPoga.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		salamisPoga.setBounds(425, 544, 115, 34);
		buvetEkrans.add(salamisPoga);

		JToggleButton tomatiPoga = new JToggleButton("Tomāti");
		tomatiPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tomatiPoga.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tomatiPoga.setBounds(835, 633, 115, 34);
		buvetEkrans.add(tomatiPoga);

		JToggleButton ananasiPoga = new JToggleButton("Ananāsi");
		ananasiPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ananasiPoga.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		ananasiPoga.setBounds(695, 633, 115, 34);
		buvetEkrans.add(ananasiPoga);

		JToggleButton gurkiPoga = new JToggleButton("Gurķi");
		gurkiPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		gurkiPoga.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		gurkiPoga.setBounds(560, 633, 115, 34);
		buvetEkrans.add(gurkiPoga);

		JToggleButton senesPoga = new JToggleButton("Sēnes");
		senesPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		senesPoga.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		senesPoga.setBounds(425, 633, 115, 34);
		buvetEkrans.add(senesPoga);

		JToggleButton sipoliPoga = new JToggleButton("Sīpoli");
		sipoliPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sipoliPoga.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		sipoliPoga.setBounds(560, 678, 115, 34);
		buvetEkrans.add(sipoliPoga);

		JToggleButton jalapenoPoga = new JToggleButton("Jalapeno");
		jalapenoPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jalapenoPoga.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		jalapenoPoga.setBounds(425, 678, 115, 34);
		buvetEkrans.add(jalapenoPoga);

		JToggleButton bezpiedevasPoga = new JToggleButton("Bez");
		bezpiedevasPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bezpiedevasPoga.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		bezpiedevasPoga.setBounds(695, 678, 115, 34);
		buvetEkrans.add(bezpiedevasPoga);

		JLabel iedalijumiBilde = new JLabel("");
		iedalijumiBilde.setIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/builder.png")));
		iedalijumiBilde.setBounds(425, 150, 463, 479);
		buvetEkrans.add(iedalijumiBilde);

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/picasbuvetajs.png")));
		lblNewLabel_4.setBounds(349, 60, 297, 70);
		buvetEkrans.add(lblNewLabel_4);

		JLabel bgBilde = new JLabel("");
		bgBilde.setIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/garlic.png")));
		bgBilde.setBounds(715, 649, 407, 253);
		buvetEkrans.add(bgBilde);

		JPanel pasutijumiEkrans = new JPanel();
		tabbedPane.addTab("Pasūtījumi", null, pasutijumiEkrans,
				"Šeit vari apskatīt kā gatavojas pašreizējie pasūtījumi un pasūtījumu vēsturi!");
		pasutijumiEkrans.setLayout(null);

		JLabel pasutijumiLabel = new JLabel("");
		pasutijumiLabel.setIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/pasutijumi logo.png")));
		pasutijumiLabel.setBounds(349, 11, 297, 147);
		pasutijumiEkrans.add(pasutijumiLabel);

		picuProgressPanel = new JPanel();
		picuProgressPanel.setVisible(false);
		picuProgressPanel.setBounds(30, 129, 928, 481);
		pasutijumiEkrans.add(picuProgressPanel);
		picuProgressPanel.setLayout(null);

		slot1 = new JPanel();
		slot1.setBounds(32, 117, 249, 321);
		picuProgressPanel.add(slot1);
		slot1.setVisible(false);
		slot1.setLayout(null);

		slot1Procenti = new JLabel("15%");
		slot1Procenti.setForeground(new Color(240, 240, 240));
		slot1Procenti.setFont(new Font("Segoe UI", Font.BOLD, 24));
		slot1Procenti.setHorizontalAlignment(SwingConstants.CENTER);
		slot1Procenti.setBounds(0, 5, 249, 172);
		slot1.add(slot1Procenti);

		slot1Bilde = new JLabel("");
		slot1Bilde.setBounds(39, 5, 170, 172);
		slot1Bilde.setIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/blurpizza.png")));
		slot1.add(slot1Bilde);

		slot1Nosaukums = new JLabel("Salami pica");
		slot1Nosaukums.setFont(new Font("Segoe UI", Font.BOLD, 16));
		slot1Nosaukums.setBounds(0, 171, 249, 38);
		slot1Nosaukums.setHorizontalAlignment(SwingConstants.CENTER);
		slot1.add(slot1Nosaukums);

		slot1Progress = new JProgressBar();
		slot1Progress.setValue(68);
		slot1Progress.setBounds(34, 210, 180, 8);
		slot1.add(slot1Progress);

		slot1Apraksts = new JLabel(
				"<html><body style='width: 140px'>25 cm, Vidēja mīkla, Tomātu mērce, Bez siera, Vistas gaļa, Jalapeno un Sīpoli");
		slot1Apraksts.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		slot1Apraksts.setVerticalAlignment(SwingConstants.TOP);
		slot1Apraksts.setBounds(39, 224, 182, 92);
		slot1Apraksts.setHorizontalAlignment(SwingConstants.CENTER);
		slot1.add(slot1Apraksts);

		slot2 = new JPanel();
		slot2.setBounds(343, 117, 249, 321);
		picuProgressPanel.add(slot2);
		slot2.setVisible(false);
		slot2.setLayout(null);

		slot2Procenti = new JLabel("15%");
		slot2Procenti.setHorizontalAlignment(SwingConstants.CENTER);
		slot2Procenti.setForeground(new Color(240, 240, 240));
		slot2Procenti.setFont(new Font("Segoe UI", Font.BOLD, 24));
		slot2Procenti.setBounds(0, 5, 249, 172);
		slot2.add(slot2Procenti);

		slot2Bilde = new JLabel("");
		slot2Bilde.setIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/blurpizza.png")));
		slot2Bilde.setBounds(39, 5, 170, 172);
		slot2.add(slot2Bilde);

		slot2Nosaukums = new JLabel("Salami pica");
		slot2Nosaukums.setFont(new Font("Segoe UI", Font.BOLD, 16));
		slot2Nosaukums.setHorizontalAlignment(SwingConstants.CENTER);
		slot2Nosaukums.setBounds(0, 171, 249, 38);
		slot2.add(slot2Nosaukums);

		slot2Progress = new JProgressBar();
		slot2Progress.setBounds(34, 210, 180, 8);
		slot2.add(slot2Progress);

		slot2Apraksts = new JLabel(
				"<html><body style='width: 140px'>25 cm, Vidēja mīkla, Tomātu mērce, Bez siera, Vistas gaļa, Jalapeno un Sīpoli");
		slot2Apraksts.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		slot2Apraksts.setVerticalAlignment(SwingConstants.TOP);
		slot2Apraksts.setHorizontalAlignment(SwingConstants.CENTER);
		slot2Apraksts.setBounds(33, 220, 182, 95);
		slot2.add(slot2Apraksts);

		slot3 = new JPanel();
		slot3.setBounds(654, 117, 249, 321);
		picuProgressPanel.add(slot3);
		slot3.setVisible(false);
		slot3.setLayout(null);

		slot3Procenti = new JLabel("15%");
		slot3Procenti.setHorizontalAlignment(SwingConstants.CENTER);
		slot3Procenti.setForeground(new Color(240, 240, 240));
		slot3Procenti.setFont(new Font("Segoe UI", Font.BOLD, 24));
		slot3Procenti.setBounds(0, 5, 249, 172);
		slot3.add(slot3Procenti);

		slot3Bilde = new JLabel("");
		slot3Bilde.setIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/blurpizza.png")));
		slot3Bilde.setBounds(39, 5, 170, 172);
		slot3.add(slot3Bilde);

		slot3Nosaukums = new JLabel("Salami pica");
		slot3Nosaukums.setFont(new Font("Segoe UI", Font.BOLD, 16));
		slot3Nosaukums.setHorizontalAlignment(SwingConstants.CENTER);
		slot3Nosaukums.setBounds(0, 171, 249, 38);
		slot3.add(slot3Nosaukums);

		slot3Progress = new JProgressBar();
		slot3Progress.setBounds(34, 210, 180, 8);
		slot3.add(slot3Progress);

		slot3Apraksts = new JLabel(
				"<html><body style='width: 140px'>25 cm, Vidēja mīkla, Tomātu mērce, Bez siera, Vistas gaļa, Jalapeno un Sīpoli");
		slot3Apraksts.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		slot3Apraksts.setVerticalAlignment(SwingConstants.TOP);
		slot3Apraksts.setHorizontalAlignment(SwingConstants.CENTER);
		slot3Apraksts.setBounds(33, 223, 182, 90);
		slot3.add(slot3Apraksts);

		pasutijumuSkaitsLabel = new JLabel("3/3");
		pasutijumuSkaitsLabel.setVerticalAlignment(SwingConstants.TOP);
		pasutijumuSkaitsLabel.setBounds(358, 11, 218, 55);
		picuProgressPanel.add(pasutijumuSkaitsLabel);
		pasutijumuSkaitsLabel.setForeground(Color.DARK_GRAY);
		pasutijumuSkaitsLabel.setFont(new Font("Segoe UI", Font.BOLD, 34));
		pasutijumuSkaitsLabel.setHorizontalAlignment(SwingConstants.CENTER);

		gaidaSkaitsLabel = new JLabel("1 gaida");
		gaidaSkaitsLabel.setBounds(358, 63, 218, 43);
		picuProgressPanel.add(gaidaSkaitsLabel);
		gaidaSkaitsLabel.setVerticalAlignment(SwingConstants.TOP);
		gaidaSkaitsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gaidaSkaitsLabel.setForeground(Color.DARK_GRAY);
		gaidaSkaitsLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));

		JLabel lblNewLabel_6 = new JLabel("Veic pasūtījumu, lai redzētu progresu!");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_6.setBounds(314, 394, 366, 75);
		pasutijumiEkrans.add(lblNewLabel_6);

		JButton vestureButton = new JButton("Apskatīt");
		vestureButton.setBounds(420, 691, 155, 36);
		pasutijumiEkrans.add(vestureButton);
		vestureButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		vestureButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));

		JLabel pasutijumuVestureLabel = new JLabel("");
		pasutijumuVestureLabel.setBounds(389, 637, 216, 43);
		pasutijumiEkrans.add(pasutijumuVestureLabel);
		pasutijumuVestureLabel
				.setIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/Pasūtījumu vēsture.png")));

//		vestureButton.addActionListener(new ActionListener() {
//		    public void actionPerformed(ActionEvent e) {
//		        String data = "";
//		        
//		        for (Map.Entry<Pasutitajs, ArrayList<Pica>> entry : Run.vesture.entrySet()) {
//		            Pasutitajs cilveks = entry.getKey();
//		            ArrayList<Pica> picas = entry.getValue();
//		            if (cilveks != null && picas != null && !picas.isEmpty()) {
//		                String picasStr = "";
//		                for (Pica pica : picas) {
//		                    picasStr += pica.getNosaukums() + ", " + pica.getIzmers() + ", " + pica.getCena() + "€ x" + pica.getSkaits() + " | ";
//		                }
//		                data += cilveks.getVards() + " - " + picasStr;
//		                data += "--------------------------------------------------------------------\n";
//		            }
//		        }
//
//		        JOptionPane.showMessageDialog(picuProgressPanel, data);
//		        data = "";
//		    }
//		});

		vestureButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String data = vesturesTeksts;

				for (Map.Entry<Pasutitajs, ArrayList<Pica>> entry : Run.vesture.entrySet()) {
					Pasutitajs cilveks = entry.getKey();
					ArrayList<Pica> picas = entry.getValue();
					if (cilveks != null && picas != null && !picas.isEmpty()) {
						String picasStr = "";
						for (Pica pica : picas) {
							picasStr += pica.getNosaukums() + ", " + pica.getIzmers() + ", " + pica.getCena() + "€ x"
									+ pica.getSkaits() + " | ";
						}
						data += cilveks.getVards() + " (" + cilveks.getEsana() + ") " + " - " + picasStr + "\n";
					}
				}

				JTextArea textArea = new JTextArea(data);
				vesturesTeksts = data;
				textArea.setEditable(false);
				JScrollPane scrollPane = new JScrollPane(textArea);
				scrollPane.setPreferredSize(new Dimension(400, 300));
				JOptionPane.showMessageDialog(picuProgressPanel, scrollPane, "Pasūtījumu vēsture",
						JOptionPane.DEFAULT_OPTION);
			}
		});

		JPanel grozsEkrans = new JPanel();
		grozsEkrans.setBackground(new Color(240, 240, 240));
		tabbedPane.addTab("Grozs", null, grozsEkrans, "Tavs groziņš, šeit vari norādīt kur ēdīsi un apmaksāt pirkumu!");
		grozsEkrans.setLayout(null);

		JPanel piegadePanel = new JPanel();
		piegadePanel.setBackground(new Color(240, 240, 240));
		piegadePanel.setBounds(531, 150, 421, 619);
		grozsEkrans.add(piegadePanel);
		piegadePanel.setLayout(null);

		JPanel kartePanel = new JPanel();
		kartePanel.putClientProperty(FlatClientProperties.STYLE, "arc: 20");
		kartePanel.setBounds(0, 0, 420, 303);
		kartePanel.setBackground(Color.WHITE);
		piegadePanel.add(kartePanel);

		JLabel img = new JLabel("");
		//img.putClientProperty(FlatClientProperties.STYLE, "arc: 20");
		img.setLocation(0, 0);
		img.setSize(420, 263);
		img.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		img.setAlignmentX(Component.RIGHT_ALIGNMENT);
		img.setHorizontalAlignment(SwingConstants.CENTER);
		img.setIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/uzvietasmap.png")));

		JLabel merkisLabel = new JLabel("<html><b>Liepājas Valsts tehnikums</b><br>Ventspils iela 51");
		merkisLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		merkisLabel.setBounds(20, 237, 274, 44);
		merkisLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		merkisLabel.setAlignmentY(Component.TOP_ALIGNMENT);
		kartePanel.setLayout(null);
		kartePanel.add(img);
		kartePanel.add(merkisLabel);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		kartePanel.add(horizontalStrut);

		Component verticalStrut = Box.createVerticalStrut(20);
		kartePanel.add(verticalStrut);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		kartePanel.add(horizontalStrut_1);

		JPanel adresePanel = new JPanel();
		adresePanel.putClientProperty(FlatClientProperties.STYLE, "arc: 20");
		adresePanel.setBackground(Color.WHITE);
		adresePanel.setBounds(0, 309, 420, 76);
		piegadePanel.add(adresePanel);
		adresePanel.setLayout(null);

		adreseLauks = new JComboBox();

		adreseLauks.putClientProperty(FlatClientProperties.STYLE, "arc: 20");
		adreseLauks.setToolTipText("Norādiet savu adresi šeit!");
		adreseLauks.setEditable(true);
		// adreseLauks.setText("Ievadi adresi");
		adreseLauks.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		adreseLauks.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		// adreseLauks.setHorizontalAlignment(SwingConstants.LEFT);
		// adreseLauks.setIcon(new
		// ImageIcon(Vizualizacija.class.getResource("/bildes/marker2.png")));
		adreseLauks.setBounds(10, 31, 400, 32);
		adresePanel.add(adreseLauks);

		JLabel lblNewLabel_7 = new JLabel("Norādiet adresi: ");
		lblNewLabel_7.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNewLabel_7.setBounds(16, 11, 167, 14);
		adresePanel.add(lblNewLabel_7);

		JPanel summaPanel = new JPanel();
		summaPanel.putClientProperty(FlatClientProperties.STYLE, "arc: 20");
		summaPanel.setBackground(Color.WHITE);
		summaPanel.setBounds(0, 390, 420, 195);
		piegadePanel.add(summaPanel);
		summaPanel.setLayout(null);

		JLabel kopaLbl = new JLabel("0.00€");
		kopaLbl.setFont(new Font("Segoe UI", Font.BOLD, 12));
		kopaLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		kopaLbl.setBounds(353, 94, 46, 14);
		summaPanel.add(kopaLbl);

		JLabel piegadeLbl = new JLabel("0.00€");
		piegadeLbl.setFont(new Font("Segoe UI", Font.BOLD, 12));
		piegadeLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		piegadeLbl.setBounds(353, 61, 46, 14);
		summaPanel.add(piegadeLbl);

		JLabel summaLbl = new JLabel("0.00€");
		summaLbl.setFont(new Font("Segoe UI", Font.BOLD, 12));
		summaLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		summaLbl.setBounds(352, 29, 46, 14);
		summaPanel.add(summaLbl);

		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_5.setBounds(21, 21, 378, 90);
		lblNewLabel_5.setIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/checkout2.png")));
		summaPanel.add(lblNewLabel_5);

		JButton piegadePasutitPoga = new JButton("Pasūtīt");
		piegadePasutitPoga.putClientProperty(FlatClientProperties.STYLE, "arc: 50");
		piegadePasutitPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		piegadePasutitPoga.setForeground(Color.WHITE);
		piegadePasutitPoga.setFont(new Font("Segoe UI", Font.BOLD, 14));
		piegadePasutitPoga.setBackground(new Color(205, 70, 49));
		piegadePasutitPoga.setBounds(116, 124, 183, 47);
		summaPanel.add(piegadePasutitPoga);

		JToggleButton piegadePoga = new JToggleButton("Piegāde");
		piegadePoga.putClientProperty(FlatClientProperties.STYLE, "arc: 50");
		piegadePoga.setFont(new Font("Inter Medium", Font.PLAIN, 14));
		piegadePoga.setSelected(true);
		piegadePoga.setBorderPainted(false);
		piegadePoga.setFocusable(false);
		piegadePoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		piegadePoga.setBounds(343, 40, 150, 35);
		grozsEkrans.add(piegadePoga);

		JToggleButton uzvietasPoga = new JToggleButton("Uz vietas");
		uzvietasPoga.putClientProperty(FlatClientProperties.STYLE, "arc: 50");
		uzvietasPoga.setFont(new Font("Inter Medium", Font.PLAIN, 14));
		uzvietasPoga.setBorderPainted(false);
		uzvietasPoga.setFocusable(false);
		uzvietasPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		uzvietasPoga.setBounds(497, 40, 150, 35);
		grozsEkrans.add(uzvietasPoga);

		JPanel tglbtnNewToggleButton_1 = new JPanel();
		tglbtnNewToggleButton_1.putClientProperty(FlatClientProperties.STYLE, "arc: 50");
		tglbtnNewToggleButton_1.setFocusable(false);
		tglbtnNewToggleButton_1.setBackground(new Color(255, 255, 255));
		tglbtnNewToggleButton_1.setBounds(336, 34, 318, 46);
		grozsEkrans.add(tglbtnNewToggleButton_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setFocusable(false);
		scrollPane.setBounds(50, 150, 443, 619);
		grozsEkrans.add(scrollPane);

		JList<Pica> pizzaList = new JList<>(model);
		pizzaList.setLayoutOrientation(JList.VERTICAL_WRAP);
		scrollPane.setViewportView(pizzaList);
		pizzaList.setBackground(new Color(240, 240, 240));

		pizzaList.setCellRenderer(new ListCellRenderer<Pica>() {
			@Override
			public Component getListCellRendererComponent(JList<? extends Pica> list, Pica value, int index,
					boolean isSelected, boolean cellHasFocus) {
				JPanel panel = new JPanel(new BorderLayout());
				JLabel apraksts = new JLabel(value.getApraksts());
				JLabel imageLabel = new JLabel(
						new ImageIcon(value.getBilde().getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
				JButton nonemtPoga = new JButton("x");
				nonemtPoga.setFont(new Font("Inter Medium", Font.PLAIN, 14));
				nonemtPoga.setBorderPainted(false);
				nonemtPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				nonemtPoga.setBackground(new Color(240, 240, 240));
				nonemtPoga.addActionListener(e -> {
					model.remove(index);
				});
				panel.add(imageLabel, BorderLayout.WEST);
				panel.add(apraksts, BorderLayout.CENTER);
				panel.add(nonemtPoga, BorderLayout.EAST);
				return panel;
			}
		});

		pizzaList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = pizzaList.locationToIndex(e.getPoint());

				Rectangle buttonRect = pizzaList.getCellBounds(index, index);

				if (buttonRect.contains(e.getPoint()))
					model.remove(index);

				Run.PasutijumuSaraksts.remove(index);
				double summaCena = 0;
				for (int i = 0; i < Run.PasutijumuSaraksts.size(); i++)
					summaCena += Run.PasutijumuSaraksts.get(i).getCena();

				summaLbl.setText(df.format(summaCena) + "€");
				if (piegadePoga.isSelected()) {
					piegadeLbl.setText(df.format(summaCena / 10) + "€");
					kopaLbl.setText(df.format(summaCena + (summaCena / 10)) + "€");
				} else {
					piegadeLbl.setText("0.00€");
					kopaLbl.setText(df.format(summaCena) + "€");
				}

			}
		});

		JButton exitPoga = new JButton("");
		exitPoga.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		exitPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				saglabat(vesturesTeksts);
			}
		});
		exitPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		exitPoga.setContentAreaFilled(false);
		exitPoga.setBorderPainted(false);
		exitPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		exitPoga.setIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/exit.png")));
		exitPoga.setFocusable(false);
		contentPane.setLayer(exitPoga, 50);
		exitPoga.setBounds(955, 0, 35, 35);
		contentPane.add(exitPoga);

		JPanel sakumaEkrans = new JPanel();
		contentPane.setLayer(sakumaEkrans, 2);
		sakumaEkrans.setBackground(new Color(240, 240, 240));
		sakumaEkrans.setBounds(0, 0, 1000, 850);
		contentPane.add(sakumaEkrans);
		sakumaEkrans.setLayout(null);

		JButton uzVietasButton = new JButton("");
		uzVietasButton.setBackground(Color.WHITE);
		uzVietasButton.setIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/uzVietasIcon.png")));
		uzVietasButton.setBounds(306, 307, 168, 150);
		uzVietasButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sakumaEkrans.add(uzVietasButton);

		JLabel uzVietasLabel = new JLabel("Ēst uz vietas");
		uzVietasLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		uzVietasLabel.setHorizontalAlignment(SwingConstants.CENTER);
		uzVietasLabel.setBounds(342, 467, 100, 19);
		sakumaEkrans.add(uzVietasLabel);

		JButton uzMajamButton = new JButton("");
		uzMajamButton.setIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/majasIcon.png")));
		uzMajamButton.setBackground(Color.WHITE);
		uzMajamButton.setBounds(524, 307, 168, 150);
		uzMajamButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sakumaEkrans.add(uzMajamButton);

		JLabel uzMajamLabel = new JLabel("Pasūtīt uz mājām");
		uzMajamLabel.setHorizontalAlignment(SwingConstants.CENTER);
		uzMajamLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		uzMajamLabel.setBounds(543, 467, 130, 19);
		sakumaEkrans.add(uzMajamLabel);

		JPanel pizza = new JPanel();
		pizza.setBackground(new Color(240, 240, 240));
		contentPane.setLayer(pizza, 12);
		pizza.setBounds(0, 0, 1000, 850);
		contentPane.add(pizza);
		pizza.setLayout(null);

		JLabel gatavaLabel = new JLabel("Salami pica");
		gatavaLabel.setFont(new Font("Inter Medium", Font.BOLD, 50));
		gatavaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gatavaLabel.setBounds(350, 30, 300, 50);
		pizza.add(gatavaLabel);

		JLabel gatavaBilde = new JLabel("");
		gatavaBilde.setIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/pica_gatavas/salami.png")));
		gatavaBilde.setBounds(62, 140, 300, 300);
		pizza.add(gatavaBilde);

		gatavacm20 = new JToggleButton("20cm");
		gatavacm20.setSelected(true);
		gatavacm20.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		gatavacm20.setBounds(423, 170, 115, 34);
		gatavacm20.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pizza.add(gatavacm20);

		gatavacm30 = new JToggleButton("30cm");
		gatavacm30.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		gatavacm30.setBounds(558, 170, 115, 34);
		gatavacm30.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pizza.add(gatavacm30);

		gatavacm60 = new JToggleButton("60cm");
		gatavacm60.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		gatavacm60.setBounds(693, 170, 115, 34);
		gatavacm60.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pizza.add(gatavacm60);

		gatavaplana = new JToggleButton("Plāna");
		gatavaplana.setSelected(true);
		gatavaplana.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		gatavaplana.setBounds(423, 265, 115, 34);
		gatavaplana.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pizza.add(gatavaplana);

		gatavavideja = new JToggleButton("Vidēja");
		gatavavideja.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		gatavavideja.setBounds(558, 265, 115, 34);
		gatavavideja.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pizza.add(gatavavideja);

		gatavabieza = new JToggleButton("Bieza");
		gatavabieza.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		gatavabieza.setBounds(693, 265, 115, 34);
		gatavabieza.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pizza.add(gatavabieza);

		JLabel lblNewLabel_8_1 = new JLabel("");
		lblNewLabel_8_1.setIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/pizza.png")));
		lblNewLabel_8_1.setBounds(425, 127, 532, 137);
		pizza.add(lblNewLabel_8_1);

		JPanel gatavaPanel = new JPanel();
		gatavaPanel.setLayout(null);
		gatavaPanel.setBackground(new Color(217, 217, 217));
		gatavaPanel.setBounds(55, 480, 307, 225);
		pizza.add(gatavaPanel);

		JLabel gatavaAprakstsLabel = new JLabel();
		gatavaAprakstsLabel.setVerticalAlignment(SwingConstants.TOP);
		gatavaAprakstsLabel.setHorizontalAlignment(SwingConstants.LEFT);
		gatavaAprakstsLabel.setForeground(new Color(79, 79, 79));
		gatavaAprakstsLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		gatavaAprakstsLabel.setBounds(16, 18, 275, 128);
		gatavaPanel.add(gatavaAprakstsLabel);

		JButton pirktGatavaPoga = new JButton("Pasūtīt");
		pirktGatavaPoga.setSize(new Dimension(173, 41));
		pirktGatavaPoga.setForeground(Color.WHITE);
		pirktGatavaPoga.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		pirktGatavaPoga.setBackground(new Color(205, 70, 49));
		pirktGatavaPoga.setBounds(123, 157, 173, 41);
		pirktGatavaPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		gatavaPanel.add(pirktGatavaPoga);

		JSpinner pirktSpinner = new JSpinner();
		pirktSpinner.setModel(new SpinnerNumberModel(1, 1, 9, 1));
		pirktSpinner.setFont(new Font("Segoe UI", Font.BOLD, 14));
		pirktSpinner.setFocusable(false);
		pirktSpinner.setFocusTraversalPolicyProvider(true);
		pirktSpinner.setFocusCycleRoot(true);
		pirktSpinner.setBounds(58, 160, 55, 34);
		gatavaPanel.add(pirktSpinner);

		pizza.setVisible(false);

		///// ds
		gatavacm20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gatavacm20.isSelected()) {

					gatavaApraksts = buvetAprakstsDzest("30 cm");
					gatavaApraksts = buvetAprakstsDzest("60 cm");
					gatavaApraksts = buvetAprakstu("20 cm");
					gatavaAprakstsLabel.setText(gatavaApraksts);
					gatavaCena -= lastGatavaCena;
					gatavaCena = gatavaBaseCena;
					pirktGatavaPoga.setText(df.format(gatavaCena) + "€");
					lastGatavaCena = gatavaCena;

				} else
					gatavacm20.setSelected(true);

				gatavacm30.setSelected(false);
				gatavacm60.setSelected(false);

			}
		});

		gatavacm30.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gatavacm30.isSelected()) {

					gatavaApraksts = buvetAprakstsDzest("20 cm");
					gatavaApraksts = buvetAprakstsDzest("60 cm");
					gatavaApraksts = buvetAprakstu("30 cm");
					gatavaAprakstsLabel.setText(gatavaApraksts);
					gatavaCena -= lastGatavaCena;
					gatavaCena = gatavaBaseCena + 2.60;
					pirktGatavaPoga.setText(df.format(gatavaCena) + "€");
					lastGatavaCena = gatavaCena;

				} else
					gatavacm30.setSelected(true);

				gatavacm20.setSelected(false);
				gatavacm60.setSelected(false);

			}
		});

		gatavacm60.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gatavacm60.isSelected()) {
					gatavaApraksts = buvetAprakstsDzest("30 cm");
					gatavaApraksts = buvetAprakstsDzest("20 cm");
					gatavaApraksts = buvetAprakstu("60 cm");
					gatavaAprakstsLabel.setText(gatavaApraksts);
					gatavaCena -= lastGatavaCena;
					gatavaCena = gatavaBaseCena + 10.18;
					pirktGatavaPoga.setText(df.format(gatavaCena) + "€");
					lastGatavaCena = gatavaCena;

				} else
					gatavacm60.setSelected(true);

				gatavacm20.setSelected(false);
				gatavacm30.setSelected(false);

			}
		});

		gatavaplana.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (planaPoga.isSelected()) {
					gatavaApraksts = buvetAprakstsDzest("Bieza mīkla");
					gatavaApraksts = buvetAprakstsDzest("Vidēji bieza mīkla");
					gatavaApraksts = buvetAprakstu("Plāna mīkla");
					gatavaAprakstsLabel.setText(gatavaApraksts);

				} else
					gatavaplana.setSelected(true);

				gatavavideja.setSelected(false);
				gatavabieza.setSelected(false);

			}
		});

		gatavavideja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (planaPoga.isSelected()) {
					gatavaApraksts = buvetAprakstsDzest("Bieza mīkla");
					gatavaApraksts = buvetAprakstsDzest("Plāna mīkla");
					gatavaApraksts = buvetAprakstu("Vidēji bieza mīkla");
					gatavaAprakstsLabel.setText(gatavaApraksts);

				} else
					gatavavideja.setSelected(true);

				gatavaplana.setSelected(false);
				gatavabieza.setSelected(false);

			}
		});

		gatavabieza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (planaPoga.isSelected()) {
					gatavaApraksts = buvetAprakstsDzest("Vidēji bieza mīkla");
					gatavaApraksts = buvetAprakstsDzest("Plāna mīkla");
					gatavaApraksts = buvetAprakstu("Bieza mīkla");
					gatavaAprakstsLabel.setText(gatavaApraksts);

				} else
					gatavabieza.setSelected(true);

				gatavaplana.setSelected(false);
				gatavavideja.setSelected(false);

			}
		});

		pirktSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				double cena = Double.parseDouble(pirktGatavaPoga.getText()
						.substring(0, pirktGatavaPoga.getText().length() - 1).replace(',', '.'));
				int reizinatajs = Integer.parseInt(pirktSpinner.getValue().toString());
				double originalaCena;

				if (reizinatajs > pedejaVertiba) {
					originalaCena = cena / (reizinatajs - 1);
					pirktGatavaPoga.setText(df.format(originalaCena * reizinatajs) + "€");
				} else {
					originalaCena = cena / (reizinatajs + 1);
					pirktGatavaPoga.setText(df.format(cena - originalaCena) + "€");
				}
				pedejaVertiba = reizinatajs;
			}
		});

		pirktGatavaPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backPoga.setVisible(false);
				tabbedPane.setVisible(true);

				int skaits = Integer.parseInt(pirktSpinner.getValue().toString());
				String izmers = buvetSastavdalas.get(0);
				String mikla = buvetSastavdalas.get(1);
				String merce = "", siers = "", gala = "";
				ArrayList<String> piedevas = new ArrayList<>();

				double cena = Double.parseDouble(pirktGatavaPoga.getText()
						.substring(0, pirktGatavaPoga.getText().length() - 1).replace(',', '.'));

				for (String s : buvetSastavdalas) {
					switch (s) {
					case "Tomātu mērce":
						merce = "Tomātu mērce";
						break;
					case "BBQ mērce":
						merce = "BBQ mērce";
						break;
					case "Parastais siers":
						siers = "Parastais siers";
						break;
					case "Mocarella siers":
						siers = "Mocarella siers";
						break;
					case "Salami":
						gala = "Salami";
						break;
					case "Šķiņķis":
						gala = "Šķiņķis";
						break;
					case "Vista":
						gala = "Vista";
						break;
					case "Bekons":
						gala = "Bekons";
						break;
					case "Tomāti":
						piedevas.add("Tomāti");
						break;
					case "Jalapeno pipari":
						piedevas.add("Jalapeno pipari");
						break;
					case "Sīpoli":
						piedevas.add("Sīpoli");
						break;
					case "Zaļumi":
						piedevas.add("Zaļumi");
						break;
					}

				}
				System.out.println(skaits);
				String apraksts = "<html><body style='width: 200px'><b>" + gatavaNosaukums + " </b>" + "x"
						+ String.valueOf(skaits) + "</b><br> " + temp + "<br><b>" + cena + "€</b>";
				// String merce,String siers, String gala, ArrayList<String> piedevas, double
				// cena, ImageIcon bilde
				ImageIcon icon = new ImageIcon(Vizualizacija.class.getResource("/bildes/pica_gatavas/bbq.png"));
				switch (gatavaNosaukums) {
				case "Salami Pica":
					icon = new ImageIcon(Vizualizacija.class.getResource("/bildes/pica_gatavas/salami.png"));
					break;
				case "BBQ Pica":
					icon = new ImageIcon(Vizualizacija.class.getResource("/bildes/pica_gatavas/bbq.png"));
					break;
				case "Siera Pica":
					icon = new ImageIcon(Vizualizacija.class.getResource("/bildes/pica_gatavas/siera.png"));
					break;
				case "Bekona Pica":
					icon = new ImageIcon(Vizualizacija.class.getResource("/bildes/pica_gatavas/bekona.png"));
					break;
				case "Lauku Pica":
					icon = new ImageIcon(Vizualizacija.class.getResource("/bildes/pica_gatavas/lauku.png"));
					break;
				case "Čillī Pica":
					icon = new ImageIcon(Vizualizacija.class.getResource("/bildes/pica_gatavas/cili.png"));
					break;

				}

				ImageIcon jaunaBilde = new ImageIcon(icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
				// Pica tavaPica = new Pica(skaits,"Tava pica",izmers,mikla,merce,siers,
				// gala,piedevas,cena, new ImageIcon(icon.getImage().getScaledInstance(100,100,
				// Image.SCALE_SMOOTH)), apraksts,0);
				Pica tavaPica = new Pica(skaits, gatavaNosaukums, izmers, mikla, merce, siers, gala, piedevas, cena,
						jaunaBilde, apraksts, 0);
				model.addElement(tavaPica);
				Run.PasutijumuSaraksts.add(tavaPica);

				double summaCena = 0;
				for (int i = 0; i < Run.PasutijumuSaraksts.size(); i++)
					summaCena += Run.PasutijumuSaraksts.get(i).getCena();

				summaLbl.setText(df.format(summaCena) + "€");
				piegadeLbl.setText(df.format(summaCena / 10) + "€");
				kopaLbl.setText(df.format(summaCena + (summaCena / 10)) + "€");

				pizza.setVisible(false);

			}
		});

		gatavacm30.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				gatavacm30.setText("2.60€");
			}

			public void mouseExited(MouseEvent e) {
				gatavacm30.setText("30 cm");
			}
		});
		gatavacm60.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				gatavacm60.setText("10.18€");
			}

			public void mouseExited(MouseEvent e) {
				gatavacm60.setText("60 cm");
			}
		});
		salamiPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backPoga.setVisible(true);
				defaultStateGatavamPogam();
				gatavaBaseCena = 9.99;
				tabbedPane.setVisible(false);
				pirktSpinner.setValue(1);
				gatavaNosaukums = "Salami Pica";
				gatavaLabel.setText(gatavaNosaukums);
				temp = "";
				gatavaSastavdalas.clear();
				buvetSastavdalas.clear();

				buvetAprakstu("20 cm");
				buvetAprakstu("Plāna mīkla");
				buvetAprakstu("Parastais siers");
				buvetAprakstu("Tomātu mērce");
				gatavaApraksts = buvetAprakstu("Salami desa");

				gatavaBilde.setIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/pica_gatavas/salami.png")));
				pirktGatavaPoga.setText("9.99€");
				gatavaAprakstsLabel.setText(gatavaApraksts);
				pizza.setVisible(true);

			}
		});

		bbqPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backPoga.setVisible(true);
				defaultStateGatavamPogam();
				gatavaBaseCena = 14.99;
				tabbedPane.setVisible(false);
				pirktSpinner.setValue(1);
				gatavaNosaukums = "BBQ Pica";
				gatavaLabel.setText(gatavaNosaukums);
				temp = "";
				gatavaSastavdalas.clear();
				buvetSastavdalas.clear();

				buvetAprakstu("20 cm");
				buvetAprakstu("Plāna mīkla");
				buvetAprakstu("Parastais siers");
				buvetAprakstu("BBQ mērce");
				gatavaApraksts = buvetAprakstu("Jalapeno pipari");

				gatavaBilde.setIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/pica_gatavas/bbq.png")));
				pirktGatavaPoga.setText("14.99€");
				gatavaAprakstsLabel.setText(gatavaApraksts);
				pizza.setVisible(true);

			}
		});

		sieraPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backPoga.setVisible(true);
				defaultStateGatavamPogam();
				gatavaBaseCena = 12.99;
				tabbedPane.setVisible(false);
				pirktSpinner.setValue(1);
				gatavaNosaukums = "Siera Pica";
				gatavaLabel.setText(gatavaNosaukums);
				temp = "";
				gatavaSastavdalas.clear();
				buvetSastavdalas.clear();

				buvetAprakstu("20 cm");
				buvetAprakstu("Plāna mīkla");
				buvetAprakstu("Tomātu mērce");
				gatavaApraksts = buvetAprakstu("Parastais siers");

				gatavaBilde.setIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/pica_gatavas/siera.png")));
				pirktGatavaPoga.setText("7.99€");
				gatavaAprakstsLabel.setText(gatavaApraksts);
				pizza.setVisible(true);

			}
		});

		laukuPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backPoga.setVisible(true);
				defaultStateGatavamPogam();
				gatavaBaseCena = 12.99;
				tabbedPane.setVisible(false);
				pirktSpinner.setValue(1);
				gatavaNosaukums = "Lauku Pica";
				gatavaLabel.setText(gatavaNosaukums);
				temp = "";
				gatavaSastavdalas.clear();
				buvetSastavdalas.clear();

				buvetAprakstu("20 cm");
				buvetAprakstu("Plāna mīkla");
				buvetAprakstu("Tomātu mērce");
				buvetAprakstu("Mocarella siers");
				buvetAprakstu("Ķiršu tomāti");
				gatavaApraksts = buvetAprakstu("Zaļumi");

				gatavaBilde.setIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/pica_gatavas/lauku.png")));
				pirktGatavaPoga.setText("12.99€");
				gatavaAprakstsLabel.setText(gatavaApraksts);
				pizza.setVisible(true);

			}
		});

		ciliPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backPoga.setVisible(true);
				defaultStateGatavamPogam();
				gatavaBaseCena = 10.18;
				tabbedPane.setVisible(false);
				pirktSpinner.setValue(1);
				gatavaNosaukums = "Čillī Pica";
				gatavaLabel.setText(gatavaNosaukums);
				temp = "";
				gatavaSastavdalas.clear();
				buvetSastavdalas.clear();

				buvetAprakstu("20 cm");
				buvetAprakstu("Plāna mīkla");
				buvetAprakstu("Tomātu mērce");
				buvetAprakstu("Parastais siers");
				buvetAprakstu("Tomāti");
				buvetAprakstu("Sīpoli");
				gatavaApraksts = buvetAprakstu("Jalapeno pipari");

				gatavaBilde.setIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/pica_gatavas/cili.png")));
				pirktGatavaPoga.setText("14.99€");
				gatavaAprakstsLabel.setText(gatavaApraksts);
				pizza.setVisible(true);

			}
		});

		bekonaPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backPoga.setVisible(true);
				defaultStateGatavamPogam();
				gatavaBaseCena = 14.99;
				tabbedPane.setVisible(false);
				pirktSpinner.setValue(1);
				gatavaNosaukums = "Bekona Pica";
				gatavaLabel.setText(gatavaNosaukums);
				temp = "";
				gatavaSastavdalas.clear();
				buvetSastavdalas.clear();

				buvetAprakstu("20 cm");
				buvetAprakstu("Plāna mīkla");
				buvetAprakstu("Tomātu mērce");
				buvetAprakstu("Parastais siers");
				buvetAprakstu("Tomāti");
				buvetAprakstu("Sīpoli");
				gatavaApraksts = buvetAprakstu("Bekons");

				gatavaBilde.setIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/pica_gatavas/bekona.png")));
				pirktGatavaPoga.setText("12.99€");
				gatavaAprakstsLabel.setText(gatavaApraksts);
				pizza.setVisible(true);

			}
		});

		uzMajamButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sakumaEkrans.setVisible(false);
			}
		});

		cm20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cm20.isSelected()) {
					// buvetApraksts.add("Tomātu mērce");
					buvetApraksts = buvetAprakstsDzest("30 cm");
					buvetApraksts = buvetAprakstsDzest("60 cm");

					buvetCena -= lastCena;
					aprakstsLabel.setText(buvetApraksts);
					buvetCena += 8.79;
					pirktGatavaPoga.setText(df.format(buvetCena) + "€");
					lastCena = 8.79;

				} else
					cm20.setSelected(true);

				cm30.setSelected(false);
				cm60.setSelected(false);

			}
		});

		cm30.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cm30.isSelected()) {
					// buvetApraksts.add("Tomātu mērce");
					buvetApraksts = buvetAprakstsDzest("20 cm");
					buvetApraksts = buvetAprakstsDzest("60 cm");
					buvetApraksts = buvetAprakstu("30 cm");
					aprakstsLabel.setText(buvetApraksts);
					buvetCena -= lastCena;
					buvetCena += 12.59;
					lastCena = 12.59;
					pirktGatavaPoga.setText(df.format(buvetCena) + "€");

				} else
					cm30.setSelected(true);

				cm20.setSelected(false);
				cm60.setSelected(false);

			}
		});

		cm60.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cm60.isSelected()) {
					// buvetApraksts.add("Tomātu mērce");
					buvetApraksts = buvetAprakstsDzest("20 cm");
					buvetApraksts = buvetAprakstsDzest("30 cm");
					buvetApraksts = buvetAprakstu("60 cm");
					aprakstsLabel.setText(buvetApraksts);
					buvetCena -= lastCena;
					buvetCena += 19.19;
					pirktGatavaPoga.setText(df.format(buvetCena) + "€");
					lastCena = 19.19;

				} else
					cm60.setSelected(true);

				cm20.setSelected(false);
				cm30.setSelected(false);

			}
		});

		/*
		 * Izmera pogu darbību beigas
		 * -----------------------------------------------------------------------------
		 * ---------- Mīklas pogu darbības sākums
		 */
		planaPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (planaPoga.isSelected()) {
					buvetApraksts = buvetAprakstsDzest("Bieza mīkla");
					buvetApraksts = buvetAprakstsDzest("Vidēji bieza mīkla");
					buvetApraksts = buvetAprakstu("Plāna mīkla");
					aprakstsLabel.setText(buvetApraksts);

				} else
					planaPoga.setSelected(true);

				videjaPoga.setSelected(false);
				biezaPoga.setSelected(false);

			}
		});

		videjaPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (videjaPoga.isSelected()) {
					// buvetApraksts.add("Tomātu mērce");
					buvetApraksts = buvetAprakstsDzest("Plāna mīkla");
					buvetApraksts = buvetAprakstsDzest("Bieza mīkla");
					buvetApraksts = buvetAprakstu("Vidēji bieza mīkla");
					aprakstsLabel.setText(buvetApraksts);

				} else
					videjaPoga.setSelected(true);

				planaPoga.setSelected(false);
				biezaPoga.setSelected(false);

			}
		});

		biezaPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (biezaPoga.isSelected()) {
					// buvetApraksts.add("Tomātu mērce");
					buvetApraksts = buvetAprakstsDzest("Vidēji bieza mīkla");
					buvetApraksts = buvetAprakstsDzest("Plāna mīkla");
					buvetApraksts = buvetAprakstu("Bieza mīkla");
					aprakstsLabel.setText(buvetApraksts);

				} else
					biezaPoga.setSelected(true);

				planaPoga.setSelected(false);
				videjaPoga.setSelected(false);

			}
		});

		/*
		 * Mīklas pogu darbības beigas
		 * -----------------------------------------------------------------------------
		 * ---------- Mērču pogu darbības sākums
		 */

		// ja piespiesta poga cenai pieliek vai nonem 1.89 un parada to
		tomatuPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tomatuPoga.isSelected()) {
					aprakstsLabel.setText(buvetAprakstu("Tomātu mērce"));
					buvetCena += 1.89;
					tomatuBilde.setVisible(true);
					pirktPoga.setText(df.format(buvetCena) + "€");
					bezmercePoga.setSelected(false);

				} else {
					aprakstsLabel.setText(buvetAprakstsDzest("Tomātu mērce"));
					buvetCena -= 1.89;
					pirktPoga.setText(df.format(buvetCena) + "€");
					tomatuBilde.setVisible(false);
				}

			}
		});

		// ja piespiesta poga cenai pieliek vai nonem 3.29 un parada to
		bbqsPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (bbqsPoga.isSelected()) {
					aprakstsLabel.setText(buvetAprakstu("BBQ mērce"));
					buvetCena += 3.29;
					bbqBilde.setVisible(true);
					pirktPoga.setText(df.format(buvetCena) + "€");
					bezmercePoga.setSelected(false);
				} else {
					aprakstsLabel.setText(buvetAprakstsDzest("BBQ mērce"));
					buvetCena -= 3.29;
					pirktPoga.setText(df.format(buvetCena) + "€");
					bbqBilde.setVisible(false);
				}
			}
		});

		// ja piespiesta poga nonem merces no picas
		bezmercePoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (bbqsPoga.isSelected()) {
					aprakstsLabel.setText(buvetAprakstsDzest("BBQ mērce"));
					buvetCena -= 3.29;
					pirktPoga.setText(df.format(buvetCena) + "€");
					bbqBilde.setVisible(false);
					bbqsPoga.setSelected(false);

				}
				if (tomatuPoga.isSelected()) {
					aprakstsLabel.setText(buvetAprakstsDzest("Tomātu mērce"));
					buvetCena -= 1.89;
					pirktPoga.setText(df.format(buvetCena) + "€");
					tomatuBilde.setVisible(false);
					tomatuPoga.setSelected(false);
				}
				bezmercePoga.setSelected(true);

			}
		});

		/*
		 * Mērču pogu darbības beigas
		 * -----------------------------------------------------------------------------
		 * ---------- Siera pogu darbības sākums
		 */

		parastsPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parastsPoga.isSelected()) {
					aprakstsLabel.setText(buvetAprakstu("Siers"));
					buvetCena += 4.99;
					siersBilde.setVisible(true);
					pirktPoga.setText(df.format(buvetCena) + "€");
					bezsiersPoga.setSelected(false);
				} else {
					aprakstsLabel.setText(buvetAprakstsDzest("Siers"));
					buvetCena -= 4.99;
					pirktPoga.setText(df.format(buvetCena) + "€");
					siersBilde.setVisible(false);
				}

			}
		});

		zilaisPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (zilaisPoga.isSelected()) {
					aprakstsLabel.setText(buvetAprakstu("Zilais siers"));
					buvetCena += 2.49;
					zilaisBilde.setVisible(true);
					pirktPoga.setText(df.format(buvetCena) + "€");
					bezsiersPoga.setSelected(false);
				} else {
					aprakstsLabel.setText(buvetAprakstsDzest("Zilais siers"));
					buvetCena -= 2.49;
					pirktPoga.setText(df.format(buvetCena) + "€");
					zilaisBilde.setVisible(false);
				}

			}
		});

		mocarellaPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (mocarellaPoga.isSelected()) {
					aprakstsLabel.setText(buvetAprakstu("Mocarellas siers"));
					buvetCena += 2.49;
					mocarellaBilde.setVisible(true);
					pirktPoga.setText(df.format(buvetCena) + "€");
					bezsiersPoga.setSelected(false);
				} else {
					aprakstsLabel.setText(buvetAprakstsDzest("Mocarellas siers"));
					buvetCena -= 2.49;
					pirktPoga.setText(df.format(buvetCena) + "€");
					mocarellaBilde.setVisible(false);
				}

			}
		});

		bezsiersPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parastsPoga.isSelected()) {
					aprakstsLabel.setText(buvetAprakstsDzest("Siers"));
					buvetCena -= 4.99;
					pirktPoga.setText(df.format(buvetCena) + "€");
					siersBilde.setVisible(false);
					parastsPoga.setSelected(false);

				}
				if (mocarellaPoga.isSelected()) {
					aprakstsLabel.setText(buvetAprakstsDzest("Mocarellas siers"));
					buvetCena -= 2.49;
					pirktPoga.setText(df.format(buvetCena) + "€");
					mocarellaBilde.setVisible(false);
					mocarellaPoga.setSelected(false);

				}
				if (zilaisPoga.isSelected()) {
					aprakstsLabel.setText(buvetAprakstsDzest("Zilais siers"));
					buvetCena -= 2.49;
					pirktPoga.setText(df.format(buvetCena) + "€");
					zilaisBilde.setVisible(false);
					zilaisPoga.setSelected(false);
				}
				bezsiersPoga.setSelected(true);

			}
		});

		/*
		 * Siera pogu darbības beigas
		 * -----------------------------------------------------------------------------
		 * ---------- Gaļas pogu darbības sākums
		 */

		salamisPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (salamisPoga.isSelected()) {
					aprakstsLabel.setText(buvetAprakstu("Salami"));
					buvetCena += 3.99;
					salamisBilde.setVisible(true);
					pirktPoga.setText(df.format(buvetCena) + "€");
					bezgalaPoga.setSelected(false);
				} else {
					aprakstsLabel.setText(buvetAprakstsDzest("Salami"));
					buvetCena -= 3.99;
					pirktPoga.setText(df.format(buvetCena) + "€");
					salamisBilde.setVisible(false);
				}

			}
		});

		skinkisPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (skinkisPoga.isSelected()) {
					aprakstsLabel.setText(buvetAprakstu("Šķiņķis"));
					buvetCena += 4.19;
					skinkisBilde.setVisible(true);
					pirktPoga.setText(df.format(buvetCena) + "€");
					bezgalaPoga.setSelected(false);
				} else {
					aprakstsLabel.setText(buvetAprakstsDzest("Šķiņķis"));
					buvetCena -= 4.19;
					pirktPoga.setText(df.format(buvetCena) + "€");
					skinkisBilde.setVisible(false);
				}

			}
		});

		vistaPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (vistaPoga.isSelected()) {
					aprakstsLabel.setText(buvetAprakstu("Vista"));
					buvetCena += 4.59;
					vistaBilde.setVisible(true);
					pirktPoga.setText(df.format(buvetCena) + "€");
					bezgalaPoga.setSelected(false);
				} else {
					aprakstsLabel.setText(buvetAprakstsDzest("Vista"));
					buvetCena -= 4.59;
					pirktPoga.setText(df.format(buvetCena) + "€");
					vistaBilde.setVisible(false);
				}

			}
		});

		bezgalaPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (salamisPoga.isSelected()) {
					aprakstsLabel.setText(buvetAprakstsDzest("Salami"));
					buvetCena -= 3.99;
					pirktPoga.setText(df.format(buvetCena) + "€");
					salamisBilde.setVisible(false);
					salamisPoga.setSelected(false);

				}
				if (skinkisPoga.isSelected()) {
					aprakstsLabel.setText(buvetAprakstsDzest("Šķiņķis"));
					buvetCena -= 4.19;
					pirktPoga.setText(df.format(buvetCena) + "€");
					skinkisBilde.setVisible(false);
					skinkisPoga.setSelected(false);

				}
				if (vistaPoga.isSelected()) {
					aprakstsLabel.setText(buvetAprakstsDzest("Vista"));
					buvetCena -= 4.59;
					pirktPoga.setText(df.format(buvetCena) + "€");
					vistaBilde.setVisible(false);
					vistaPoga.setSelected(false);
				}
				bezgalaPoga.setSelected(true);

			}
		});

		/*
		 * Gaļas pogu darbības beigas
		 * -----------------------------------------------------------------------------
		 * ---------- Piedevu pogu darbības sākums
		 */

		senesPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (senesPoga.isSelected()) {
					aprakstsLabel.setText(buvetAprakstu("Sēnes"));
					buvetCena += 2.19;
					senesBilde.setVisible(true);
					pirktPoga.setText(df.format(buvetCena) + "€");
					bezpiedevasPoga.setSelected(false);
				} else {
					aprakstsLabel.setText(buvetAprakstsDzest("Sēnes"));
					buvetCena -= 2.19;
					pirktPoga.setText(df.format(buvetCena) + "€");
					senesBilde.setVisible(false);
				}

			}
		});

		gurkiPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gurkiPoga.isSelected()) {
					aprakstsLabel.setText(buvetAprakstu("Marinēti gurķi"));
					buvetCena += 2.19;
					gurkiBilde.setVisible(true);
					pirktPoga.setText(df.format(buvetCena) + "€");
					bezpiedevasPoga.setSelected(false);
				} else {
					aprakstsLabel.setText(buvetAprakstsDzest("Marinēti gurķi"));
					buvetCena -= 2.19;
					pirktPoga.setText(df.format(buvetCena) + "€");
					gurkiBilde.setVisible(false);
				}

			}
		});

		ananasiPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ananasiPoga.isSelected()) {
					aprakstsLabel.setText(buvetAprakstu("Ananāsi"));
					buvetCena += 2.19;
					ananasiBilde.setVisible(true);
					pirktPoga.setText(df.format(buvetCena) + "€");
					bezpiedevasPoga.setSelected(false);
				} else {
					aprakstsLabel.setText(buvetAprakstsDzest("Ananāsi"));
					buvetCena -= 2.19;
					pirktPoga.setText(df.format(buvetCena) + "€");
					ananasiBilde.setVisible(false);
				}

			}
		});

		tomatiPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tomatiPoga.isSelected()) {
					aprakstsLabel.setText(buvetAprakstu("Tomāti"));
					buvetCena += 2.19;
					tomatiBilde.setVisible(true);
					pirktPoga.setText(df.format(buvetCena) + "€");
					bezpiedevasPoga.setSelected(false);
				} else {
					aprakstsLabel.setText(buvetAprakstsDzest("Tomāti"));
					buvetCena -= 2.19;
					pirktPoga.setText(df.format(buvetCena) + "€");
					tomatiBilde.setVisible(false);
				}

			}
		});

		jalapenoPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (jalapenoPoga.isSelected()) {
					aprakstsLabel.setText(buvetAprakstu("Jalapeno pipari"));
					buvetCena += 2.19;
					jalapenoBilde.setVisible(true);
					pirktPoga.setText(df.format(buvetCena) + "€");
					bezpiedevasPoga.setSelected(false);
				} else {
					aprakstsLabel.setText(buvetAprakstsDzest("Jalapeno pipari"));
					buvetCena -= 2.19;
					pirktPoga.setText(df.format(buvetCena) + "€");
					jalapenoBilde.setVisible(false);
				}

			}
		});

		sipoliPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (sipoliPoga.isSelected()) {
					aprakstsLabel.setText(buvetAprakstu("Sīpoli"));
					buvetCena += 0.49;
					sipoliBilde.setVisible(true);
					pirktPoga.setText(df.format(buvetCena) + "€");
					bezpiedevasPoga.setSelected(false);
				} else {
					aprakstsLabel.setText(buvetAprakstsDzest("Sīpoli"));
					buvetCena -= 0.49;
					pirktPoga.setText(df.format(buvetCena) + "€");
					sipoliBilde.setVisible(false);
				}

			}
		});

		bezpiedevasPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (senesPoga.isSelected()) {
					aprakstsLabel.setText(buvetAprakstsDzest("Sēnes"));
					buvetCena -= 2.19;
					pirktPoga.setText(df.format(buvetCena) + "€");
					senesBilde.setVisible(false);
					senesPoga.setSelected(false);

				}
				if (gurkiPoga.isSelected()) {
					aprakstsLabel.setText(buvetAprakstsDzest("Marinēti gurķi"));
					buvetCena -= 2.19;
					pirktPoga.setText(df.format(buvetCena) + "€");
					gurkiBilde.setVisible(false);
					gurkiPoga.setSelected(false);

				}
				if (ananasiPoga.isSelected()) {
					aprakstsLabel.setText(buvetAprakstsDzest("Ananāsi"));
					buvetCena -= 2.19;
					pirktPoga.setText(df.format(buvetCena) + "€");
					ananasiBilde.setVisible(false);
					ananasiPoga.setSelected(false);

				}
				if (tomatiPoga.isSelected()) {
					aprakstsLabel.setText(buvetAprakstsDzest("Tomāti"));
					buvetCena -= 2.19;
					pirktPoga.setText(df.format(buvetCena) + "€");
					tomatiBilde.setVisible(false);
					tomatiPoga.setSelected(false);

				}
				if (jalapenoPoga.isSelected()) {
					aprakstsLabel.setText(buvetAprakstsDzest("Jalapeno pipari"));
					buvetCena -= 2.19;
					pirktPoga.setText(df.format(buvetCena) + "€");
					jalapenoBilde.setVisible(false);
					jalapenoPoga.setSelected(false);

				}
				if (sipoliPoga.isSelected()) {
					aprakstsLabel.setText(buvetAprakstsDzest("Sīpoli"));
					buvetCena -= 0.49;
					pirktPoga.setText(df.format(buvetCena) + "€");
					sipoliBilde.setVisible(false);
					sipoliPoga.setSelected(false);

				}

				bezpiedevasPoga.setSelected(true);

			}
		});

		piegadePasutitPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if ((Run.PasutijumuSaraksts.size() > 0 && piegadePoga.isSelected())
							&& adreseLauks.getSelectedItem().toString().length() > 0) {
						registracijaEkrans.setVisible(true);
						picuProgressPanel.setVisible(true);
						model.clear();

						String galapunkts = tulkot(adreseLauks.getSelectedItem().toString());
						galapunkts = galapunkts.replaceAll("[^\\d,\\w\\s]", "").replaceAll("\\s+", "").trim();
						System.out.println(galapunkts);

						Image image = new ImageIcon(new URL("https://www.mapquestapi.com/staticmap/v5/" + "map?start="
								+ "Ventspilsiela51,Liepaja" + "&end=" + galapunkts
								+ "&size=394,210@2x&key=sMnc2yKdeYrC6jVRfMKRHD60NRnG2zWa&circle-812DD3-sm&type=light"))
								.getImage().getScaledInstance(394, 210, Image.SCALE_SMOOTH);
						ImageIcon icon = new ImageIcon(image);
						img.setIcon(icon);

						String apiEndpoint = "https://www.mapquestapi.com/directions/v2/optimizedroute?key="
								+ "sMnc2yKdeYrC6jVRfMKRHD60NRnG2zWa" + "&from=" + "Ventspilsiela51,Liepaja" + "&to="
								+ galapunkts;

						try {
							URL url = new URL(apiEndpoint);
							HttpURLConnection con = (HttpURLConnection) url.openConnection();
							con.setRequestMethod("GET");

							BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
							String inputLine;
							StringBuffer response = new StringBuffer();

							while ((inputLine = in.readLine()) != null) {
								response.append(inputLine);
							}

							in.close();

							JSONObject jsonResponse = new JSONObject(response.toString());
							double distanceInKm = jsonResponse.getJSONObject("route").getDouble("distance");

							merkisLabel.setText(merkisLabel.getText() + " -> "
									+ adreseLauks.getSelectedItem().toString() + "(" + df.format(distanceInKm*2)+ " km)");

						} catch (Exception f) {
							f.printStackTrace();
						}
						contentPane.setLayer(registracijaEkrans, 13);

					} else if (Run.PasutijumuSaraksts.size() > 0 && uzvietasPoga.isSelected()) {
						img.setIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/uzvietasmap.png")));
						contentPane.setLayer(registracijaDalejaEkrans, 13);
						registracijaDalejaEkrans.setVisible(true);
						picuProgressPanel.setVisible(true);
						model.clear();
					}

				} catch (Exception f) {
				}

			}
		});

		piegadePoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (piegadePoga.isSelected()) {
					piegadePanel.setVisible(true);
				} else
					piegadePoga.setSelected(true);

				uzvietasPoga.setSelected(false);

				adreseLauks.setEnabled(true);

				double summaCena = 0;
				for (int i = 0; i < Run.PasutijumuSaraksts.size(); i++)
					summaCena += Run.PasutijumuSaraksts.get(i).getCena();

				summaLbl.setText(df.format(summaCena) + "€");
				piegadeLbl.setText("5.00€");
				kopaLbl.setText(df.format(summaCena + 5) + "€");

			}
		});

		uzvietasPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!uzvietasPoga.isSelected())
					uzvietasPoga.setSelected(true);

				piegadePoga.setSelected(false);
				piegadePanel.setEnabled(false);

				adreseLauks.setEnabled(false);

				double summaCena = 0;
				for (int i = 0; i < Run.PasutijumuSaraksts.size(); i++)
					summaCena += Run.PasutijumuSaraksts.get(i).getCena();

				summaLbl.setText(df.format(summaCena) + "€");
				piegadeLbl.setText("0.00€");
				kopaLbl.setText(df.format(summaCena) + "€");

			}
		});

		choiceKarte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!choiceKarte.isSelected())
					choiceKarte.setSelected(true);

				choiceSkaidra.setSelected(false);

			}
		});

		choiceSkaidra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!choiceSkaidra.isSelected())
					choiceSkaidra.setSelected(true);

				choiceKarte.setSelected(false);

			}
		});

		choiceKarte_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!choiceKarte_1.isSelected())
					choiceKarte_1.setSelected(true);

				choiceSkaidra_1.setSelected(false);

			}
		});

		choiceSkaidra_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!choiceSkaidra_1.isSelected())
					choiceSkaidra_1.setSelected(true);

				choiceKarte_1.setSelected(false);

			}
		});

		/*
		 * Titullapa ekrāns beigas
		 * ------------------------------------------------------ Sana malas sākums
		 */

		// picu skaits x cena
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				double cena = Double.parseDouble(
						pirktPoga.getText().substring(0, pirktPoga.getText().length() - 1).replace(',', '.'));
				int reizinatajs = Integer.parseInt(spinner.getValue().toString());
				double originalaCena;

				if (reizinatajs > pedejaVertiba) {
					originalaCena = cena / (reizinatajs - 1);
					pirktPoga.setText(df.format(originalaCena * reizinatajs) + "€");
				} else {
					originalaCena = cena / (reizinatajs + 1);
					pirktPoga.setText(df.format(cena - originalaCena) + "€");
				}
				pedejaVertiba = reizinatajs;
			}
		});

		pirktPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int skaits = Integer.parseInt(spinner.getValue().toString());
				String izmers = buvetSastavdalas.get(0);
				String mikla = buvetSastavdalas.get(1);
				String merce = "", siers = "", gala = "";
				ArrayList<String> piedevas = new ArrayList<>();
				double cena = Double.parseDouble(
						pirktPoga.getText().substring(0, pirktPoga.getText().length() - 1).replace(',', '.'));

				for (String s : buvetSastavdalas) {
					switch (s) {
					case "Tomātu mērce":
						merce = "Tomātu mērce";
						break;
					case "BBQ mērce":
						merce = "BBQ mērce";
						break;
					case "Parastais siers":
						siers = "Parastais siers";
						break;
					case "Zilais siers":
						siers = "Zilais siers";
						break;
					case "Mocarella siers":
						siers = "Mocarella siers";
						break;
					case "Salami":
						gala = "Salami";
						break;
					case "Šķiņķis":
						gala = "Šķiņķis";
						break;
					case "Vista":
						gala = "Vista";
						break;
					case "Sēnes":
						piedevas.add("Sēnes");
						break;
					case "Gurķi":
						piedevas.add("Marinēti gurķi");
						break;
					case "Ananāsi":
						piedevas.add("Ananāsi");
						break;
					case "Tomāti":
						piedevas.add("Tomāti");
						break;
					case "Jalapeno pipari":
						piedevas.add("Jalapeno pipari");
						break;
					case "Sīpoli":
						piedevas.add("Sīpoli");
						break;
					}

				}
				System.out.println(skaits);
				String apraksts = "<html><body style='width: 200px'><b>Tava pica </b>" + "x" + String.valueOf(skaits)
						+ "</b><br> " + temp + "<br><b>" + cena + "€</b>";
				// String merce,String siers, String gala, ArrayList<String> piedevas, double
				// cena, ImageIcon bilde

				ImageIcon icon = new ImageIcon(Vizualizacija.class.getResource("/bildes/tavaPica.png"));
				Pica tavaPica = new Pica(skaits, "Tava pica", izmers, mikla, merce, siers, gala, piedevas, cena, icon,
						apraksts, 0);
				model.addElement(tavaPica);
				Run.PasutijumuSaraksts.add(tavaPica);

				double summaCena = 0;
				for (int i = 0; i < Run.PasutijumuSaraksts.size(); i++)
					summaCena += Run.PasutijumuSaraksts.get(i).getCena();

				summaLbl.setText(df.format(summaCena) + "€");
				piegadeLbl.setText(df.format(summaCena / 10) + "€");
				kopaLbl.setText(df.format(summaCena + (summaCena / 10)) + "€");

			}
		});

		backPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setVisible(true);
				pizza.setVisible(false);
				temp = "";
				gatavaSastavdalas.clear();
				buvetSastavdalas.clear();
				buvetAprakstu("20 cm");
				aprakstsLabel.setText(buvetAprakstu("Plāna mīkla"));
				backPoga.setVisible(false);

			}
		});

		HashSet<String> addedItems = new HashSet<>();
		adreseLauks.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!pievienots) {
					adrese = adreseLauks.getSelectedItem().toString();
					if (!addedItems.contains(adrese)) {
						System.out.println(adrese);
						adreseLauks.addItem(adrese);
						addedItems.add(adrese);
					}
					pievienots = true;
				}

			}
		});
		adreseLauks.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				pievienots = false;
			}
		});

		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String numurs = numursIevade.getText().trim(), vards = vardsIevade.getText().trim(),
						uzvards = uzvardsIevade.getText().trim();

				boolean derigsNr = false, derigsVards = false, derigsUzvards = false;

				if (vards.isEmpty())
					vardsIevade.setBackground(new Color(255, 150, 150));
				else if (vards.matches(".*\\d+.*") || vards.length() <= 2)
					vardsIevade.setBackground(new Color(255, 150, 150));
				else {
					derigsVards = true;
					vardsIevade.setBackground(Color.WHITE);
				}

				if (uzvards.isEmpty())
					uzvardsIevade.setBackground(new Color(255, 150, 150));
				else if (uzvards.matches(".*\\d+.*") || uzvards.length() <= 2)
					uzvardsIevade.setBackground(new Color(255, 150, 150));
				else {
					derigsUzvards = true;
					uzvardsIevade.setBackground(Color.WHITE);
				}

				if (numurs.isEmpty())
					numursIevade.setBackground(new Color(255, 150, 150));
				else {
					try {
						if (numurs.length() != 8) {
							numursIevade.setBackground(new Color(255, 150, 150));
						} else {
							derigsNr = true;
							numursIevade.setBackground(Color.WHITE);
						}
					} catch (NumberFormatException f) {
						numursIevade.setBackground(new Color(255, 150, 150));
					}
				}

				if (derigsVards && derigsUzvards && derigsNr) {
					Run.Registracija();
					registracijaEkrans.setVisible(false);
				}

			}
		});

		numursIevade.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				numursIevade.setBackground(Color.WHITE);
			}
		});
		vardsIevade.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				vardsIevade.setBackground(Color.WHITE);
			}
		});
		vardsDalejaIevade.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				vardsDalejaIevade.setBackground(Color.WHITE);
			}
		});
		uzvardsIevade.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				uzvardsIevade.setBackground(Color.WHITE);
			}
		});

		regPoga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean derigsVards = false;

				String vards = vardsDalejaIevade.getText().trim();

				if (vards.isEmpty())
					vardsDalejaIevade.setBackground(new Color(255, 150, 150));
				else if (vards.matches(".*\\d+.*") || vards.length() <= 2)
					vardsDalejaIevade.setBackground(new Color(255, 150, 150));
				else {
					derigsVards = true;
					vardsDalejaIevade.setBackground(Color.WHITE);
				}

				if (derigsVards) {
					Run.dalejaRegistracija();
					registracijaDalejaEkrans.setVisible(false);
				}
			}
		});

		// hover prieks pogam
		cm20.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				cm20.setText("8.99€");
			}

			public void mouseExited(MouseEvent e) {
				cm20.setText("20 cm");
			}
		});
		cm30.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				cm30.setText("12.59€");
			}

			public void mouseExited(MouseEvent e) {
				cm30.setText("30 cm");
			}
		});
		cm60.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				cm60.setText("17.99€");
			}

			public void mouseExited(MouseEvent e) {
				cm60.setText("60 cm");
			}
		});

		tomatuPoga.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				tomatuPoga.setText("1.89€");
			}

			public void mouseExited(MouseEvent e) {
				tomatuPoga.setText("Tomātu");
			}
		});
		bbqsPoga.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				bbqsPoga.setText("3.29€");
			}

			public void mouseExited(MouseEvent e) {
				bbqsPoga.setText("BBQ");
			}
		});
		parastsPoga.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				parastsPoga.setText("4.99€");
			}

			public void mouseExited(MouseEvent e) {
				parastsPoga.setText("Parasts");
			}
		});
		zilaisPoga.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				zilaisPoga.setText("2.49€");
			}

			public void mouseExited(MouseEvent e) {
				zilaisPoga.setText("Zilais");
			}
		});
		mocarellaPoga.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				mocarellaPoga.setText("2.49€");
			}

			public void mouseExited(MouseEvent e) {
				mocarellaPoga.setText("Mocarella");
			}
		});
		salamisPoga.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				salamisPoga.setText("3.99€");
			}

			public void mouseExited(MouseEvent e) {
				salamisPoga.setText("Salami");
			}
		});
		skinkisPoga.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				skinkisPoga.setText("4.19€");
			}

			public void mouseExited(MouseEvent e) {
				skinkisPoga.setText("Šķiņkis");
			}
		});
		vistaPoga.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				vistaPoga.setText("2.49€");
			}

			public void mouseExited(MouseEvent e) {
				vistaPoga.setText("Vista");
			}
		});
		senesPoga.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				senesPoga.setText("2.29€");
			}

			public void mouseExited(MouseEvent e) {
				senesPoga.setText("Sēnes");
			}
		});
		ananasiPoga.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				ananasiPoga.setText("2.29€");
			}

			public void mouseExited(MouseEvent e) {
				ananasiPoga.setText("Ananāsi");
			}
		});

		tomatiPoga.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				tomatiPoga.setText("2.19€");
			}

			public void mouseExited(MouseEvent e) {
				tomatiPoga.setText("Tomāti");
			}
		});
		jalapenoPoga.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				jalapenoPoga.setText("2.19€");
			}

			public void mouseExited(MouseEvent e) {
				jalapenoPoga.setText("Jalapeno");
			}
		});
		sipoliPoga.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				sipoliPoga.setText("0.49€");
			}

			public void mouseExited(MouseEvent e) {
				sipoliPoga.setText("Sīpoli");
			}
		});
		exitPoga.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitPoga.setIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/exit_hover.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				exitPoga.setIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/exit.png")));
			}
		});
		backPoga.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backPoga.setIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/back_hover.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				backPoga.setIcon(new ImageIcon(Vizualizacija.class.getResource("/bildes/back.png")));
			}
		});

	}

	static String buvetAprakstu(String sastavdala) {
		temp = "<html><body style='width: 200px'>";
		spinner.setValue(1);

		// izmeru parāda pirmo un biezumu kā otro
		if (sastavdala.equals("20 cm") || sastavdala.equals("30 cm") || sastavdala.equals("60 cm")) {
			Collections.reverse(buvetSastavdalas);
			buvetSastavdalas.add(sastavdala);
			Collections.reverse(buvetSastavdalas);
		} else if ((sastavdala.equals("Plāna mīkla") || sastavdala.equals("Vidēji bieza mīkla")
				|| sastavdala.equals("Bieza mīkla")) && buvetSastavdalas.size() > 1) {
			String tmp = buvetSastavdalas.get(1);
			buvetSastavdalas.set(1, sastavdala);
			buvetSastavdalas.add(tmp);
		} else
			buvetSastavdalas.add(sastavdala);

		for (int i = 0; i < buvetSastavdalas.size(); i++) {
			if (i == 0)
				temp += buvetSastavdalas.get(i) + ", ";
			else if (i == buvetSastavdalas.size() - 1)
				temp += buvetSastavdalas.get(i) + "!";
			else if (i == buvetSastavdalas.size() - 2)
				temp += buvetSastavdalas.get(i) + " un ";
			else
				temp += buvetSastavdalas.get(i) + ", ";
		}

		return temp;
	}

	static String buvetAprakstsDzest(String sastavdala) {
		buvetSastavdalas.remove(sastavdala);
		spinner.setValue(1);
		temp = "<html><body style='width: 200px'>";
		if (buvetSastavdalas.size() == 1)
			return buvetSastavdalas.get(0) + "!";
		else {
			for (int i = 0; i < buvetSastavdalas.size(); i++) {
				if (i == 0)
					temp += buvetSastavdalas.get(i) + ", ";
				else if (i == buvetSastavdalas.size() - 1)
					temp += buvetSastavdalas.get(i) + "!";
				else if (i == buvetSastavdalas.size() - 2)
					temp += buvetSastavdalas.get(i) + " un ";
				else
					temp += buvetSastavdalas.get(i) + ", ";
			}
		}
		return temp;
	}

	public static String tulkot(String ievade) {
		String izvade = ievade;
		izvade = izvade.replaceAll("[Āā]", "a");
		izvade = izvade.replaceAll("[Čč]", "c");
		izvade = izvade.replaceAll("[Ģģ]", "g");
		izvade = izvade.replaceAll("[Īī]", "i");
		izvade = izvade.replaceAll("[Ķķ]", "k");
		izvade = izvade.replaceAll("[Ļļ]", "l");
		izvade = izvade.replaceAll("[Ņņ]", "n");
		izvade = izvade.replaceAll("[Šš]", "s");
		izvade = izvade.replaceAll("[Ūū]", "u");
		izvade = izvade.replaceAll("[Žž]", "z");
		return izvade;
	}
}
