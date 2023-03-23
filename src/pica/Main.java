package pica;

import java.awt.EventQueue;
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
import java.awt.ComponentOrientation;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Main extends JFrame{

	private JLayeredPane contentPane;
	private JTextField vardsIevade;
	private JTextField uzvardsIevade;
	private JTextField numursIevade;
	private JTextField adreseIevade;
	private JTextField vardsDalejaIevade;
	private static JPanel sideBar;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Pievienots look and feel
					FlatLaf.setGlobalExtraDefaults( Collections.singletonMap( "@accentColor", "#FFFFFF" ) );
					UIManager.put( "Component.focusWidth", 0 );
					FlatLightLaf.setup();
			        UIManager.put("Button.arc", 80 );
			        UIManager.put("TextComponent.arc", 20);
			        UIManager.put("CheckBox.arc", 4);
			        
			        
					Main frame = new Main();
					frame.setUndecorated(true); 
					frame.setVisible(true);
					
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	

	
	//Vizualizācija
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds((1920-1000)/2, (1080-850)/2, 1000, 850);
		contentPane = new JLayeredPane();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel sakumaEkrans = new JPanel();
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
		TitullapaEkrans.setBackground(new Color(189, 195, 199));
		contentPane.setLayer(TitullapaEkrans, 4);
		TitullapaEkrans.setBounds(0, 0, 1000, 850);
		contentPane.add(TitullapaEkrans);
		TitullapaEkrans.setLayout(null);
		
		JButton menuButton = new JButton("");
		menuButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				menuButton.setIcon(new ImageIcon(Main.class.getResource("/bildes/hamburgers_hover.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				menuButton.setIcon(new ImageIcon(Main.class.getResource("/bildes/hamburgers.png")));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				sideBar.setVisible(true);
			}
		});
		
		JButton exitButton = new JButton("");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		exitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(new ImageIcon(Main.class.getResource("/bildes/exit_hover.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(new ImageIcon(Main.class.getResource("/bildes/exit.png")));
			}
		});
		
		JButton buvetPoga = new JButton("Turpināt");
		buvetPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buvetPoga.setBackground(new Color(205, 70, 49));
		buvetPoga.setForeground(Color.WHITE);
		buvetPoga.setFont(new Font("Inter", Font.PLAIN, 24));
		buvetPoga.setBounds(348, 686, 303, 73);
		TitullapaEkrans.add(buvetPoga);
		exitButton.setIcon(new ImageIcon(Main.class.getResource("/bildes/exit.png")));
		exitButton.setFocusable(false);
		exitButton.setFocusTraversalKeysEnabled(false);
		exitButton.setFocusPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setBorder(null);
		exitButton.setBackground(new Color(189, 195, 199));
		exitButton.setBounds(920, 21, 55, 55);
		TitullapaEkrans.add(exitButton);
		menuButton.setContentAreaFilled(false);
		menuButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuButton.setFocusable(false);
		menuButton.setFocusTraversalKeysEnabled(false);
		menuButton.setFocusPainted(false);
		menuButton.setBorder(null);
		menuButton.setBackground(new Color(189, 195, 199));
		menuButton.setIcon(new ImageIcon(Main.class.getResource("/bildes/hamburgers.png")));
		menuButton.setBounds(21, 21, 55, 55);
		TitullapaEkrans.add(menuButton);
		
		JPanel rekomendacijasPanel = new JPanel();
		rekomendacijasPanel.setBackground(new Color(217, 217, 217));
		rekomendacijasPanel.setBounds(0, 280, 1000, 335);
		TitullapaEkrans.add(rekomendacijasPanel);
		rekomendacijasPanel.setLayout(null);
		
		JButton labiPoga = new JButton("");
		labiPoga.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				labiPoga.setIcon(new ImageIcon(Main.class.getResource("/bildes/rightButton_hover.png")));
			}
			public void mouseExited(MouseEvent e) {
				labiPoga.setIcon(new ImageIcon(Main.class.getResource("/bildes/rightButton.png")));
			}
		});
		labiPoga.setContentAreaFilled(false);
		labiPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		labiPoga.setBounds(950, 144, 40, 40);
		labiPoga.setIcon(new ImageIcon(Main.class.getResource("/bildes/rightButton.png")));
		rekomendacijasPanel.add(labiPoga);
		
		JButton kreisiPoga = new JButton("");
		kreisiPoga.setContentAreaFilled(false);
		kreisiPoga.setBackground(new Color(217, 217, 217));
		kreisiPoga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		kreisiPoga.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				kreisiPoga.setIcon(new ImageIcon(Main.class.getResource("/bildes/leftButton_hover.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				kreisiPoga.setIcon(new ImageIcon(Main.class.getResource("/bildes/leftButton.png")));
			}
		});
		kreisiPoga.setBounds(10, 144, 40, 40);
		kreisiPoga.setIcon(new ImageIcon(Main.class.getResource("/bildes/leftButton.png")));
		rekomendacijasPanel.add(kreisiPoga);
		
		JLabel bilde1 = new JLabel("");
		bilde1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				bilde1.setIcon(new ImageIcon(Main.class.getResource("/bildes/margarita_hover.png")));
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				bilde1.setIcon(new ImageIcon(Main.class.getResource("/bildes/margarita.png")));
			}
		});
		bilde1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bilde1.setBounds(71, 38, 271, 259);
		bilde1.setIcon(new ImageIcon(Main.class.getResource("/bildes/margarita.png")));
		rekomendacijasPanel.add(bilde1);
		
		JLabel bilde2 = new JLabel("");
		bilde2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				bilde2.setIcon(new ImageIcon(Main.class.getResource("/bildes/lauku_hover.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				bilde2.setIcon(new ImageIcon(Main.class.getResource("/bildes/lauku.png")));
			}
		});
		bilde2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bilde2.setBounds(363, 38, 271, 259);
		bilde2.setIcon(new ImageIcon(Main.class.getResource("/bildes/lauku.png")));
		rekomendacijasPanel.add(bilde2);
		
		JLabel bilde3 = new JLabel("");
		bilde3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				bilde3.setIcon(new ImageIcon(Main.class.getResource("/bildes/3sieru_hover.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				bilde3.setIcon(new ImageIcon(Main.class.getResource("/bildes/3sieru.png")));
			}
		});
		bilde3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bilde3.setBounds(655, 38, 271, 259);
		bilde3.setIcon(new ImageIcon(Main.class.getResource("/bildes/3sieru.png")));
		rekomendacijasPanel.add(bilde3);
		
		JLabel titullapaBg = new JLabel("");
		titullapaBg.setIcon(new ImageIcon(Main.class.getResource("/bildes/titullapaBg.png")));
		titullapaBg.setBounds(-231, -375, 1388, 1290);
		TitullapaEkrans.add(titullapaBg);
		
		JLabel titullapaLogo = new JLabel("");
		titullapaLogo.setIcon(new ImageIcon(Main.class.getResource("/bildes/titullapaLogo.png")));
		titullapaLogo.setBounds(374, 75, 251, 128);
		TitullapaEkrans.add(titullapaLogo);
		
		JLabel tekstiLabel = new JLabel("");
		tekstiLabel.setIcon(new ImageIcon(Main.class.getResource("/bildes/thinText.png")));
		tekstiLabel.setBounds(51, 245, 556, 417);
		TitullapaEkrans.add(tekstiLabel);
		
		JLabel lblNewLabel = new JLabel("Būve savu picu");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Inter", Font.BOLD, 14));
		lblNewLabel.setBounds(398, 758, 204, 25);
		TitullapaEkrans.add(lblNewLabel);
		
		JLabel sideBarExit = new JLabel("");
		sideBarExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				sideBar.setVisible(false);
			}
		});
		sideBarExit.setBounds(340, 0, 224, 810);
		TitullapaEkrans.add(sideBarExit);
		
		/*Registrācijas ekrāns beigas
		 * ------------------------------------------------------
		 Sana malas sākums */
		
		sideBar = new JPanel();
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
		
		
	
		
		
	}
}
