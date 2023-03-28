package pica;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;

public class Run {
	public static ArrayList<Pica> PasutijumuSaraksts = new ArrayList<Pica>();
	public static ArrayList<String> buvetSastavdalas = new ArrayList<String>();

	public static void main(String[] args) {
		sakt();


// es milu piekienas

		
		
		
		
		
	}


	
	
	public static void sakt() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Look and feel iestatījumi
					FlatLaf.setGlobalExtraDefaults( Collections.singletonMap( "@accentColor", "#CD4631" ) );
					UIManager.put( "Component.focusWidth", 0 );
					FlatLightLaf.setup();
					//ieapalojumi
			        UIManager.put("JButton.arc", 20 );UIManager.put("TextComponent.arc", 20);UIManager.put("CheckBox.arc", 4); UIManager.put("JTextField.arc", 10);UIManager.put("Button.arc",15);
			        //UIManager.put("TabbedPane.selectedBackground", new Color(205, 70, 49) );
			        UIManager.put("TabbedPane.minimumTabWidth", 150); UIManager.put("TabbedPane.tabAreaAlignment", "center"); UIManager.put("TabbedPane.showTabSeparators", true); UIManager.put("ToggleButton.selectedBackground", new Color(255, 96, 71)); UIManager.put("ToggleButton.selectedForeground", new Color(255, 255, 255));
			      
					Vizualizacija.buvetApraksts = Vizualizacija.buvetAprakstu("20 cm");
					Vizualizacija.buvetApraksts = Vizualizacija.buvetAprakstu("Plāna mīkla");
					
					Vizualizacija frame = new Vizualizacija();
					frame.setUndecorated(true); 
					frame.setVisible(true);

					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
