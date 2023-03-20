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
;

public class Main extends JFrame {

	private JPanel contentPane;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Pievienots look and feel
					FlatLaf.setGlobalExtraDefaults( Collections.singletonMap( "@accentColor", "#f5f2f2" ) );
					UIManager.put( "Component.focusWidth", 1 );
					FlatLightLaf.setup();
			        UIManager.put("Button.arc", 25 );
			        
					Main frame = new Main();
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
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel sakumaEkrans = new JPanel();
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
		 */
		
		
		
	}
}
