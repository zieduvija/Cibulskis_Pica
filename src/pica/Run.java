package pica;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.*;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;

import static pica.Vizualizacija.*;

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
			        UIManager.put("JButton.arc", 20 );UIManager.put("TextComponent.arc", 20);UIManager.put("CheckBox.arc", 4); UIManager.put("JTextField.arc", 2);UIManager.put("Button.arc",15);
			        //UIManager.put("TabbedPane.selectedBackground", new Color(205, 70, 49) );
			        UIManager.put("TabbedPane.minimumTabWidth", 150); UIManager.put("TabbedPane.tabAreaAlignment", "center"); UIManager.put("TabbedPane.showTabSeparators", true); UIManager.put("ToggleButton.selectedBackground", new Color(255, 96, 71)); UIManager.put("ToggleButton.selectedForeground", new Color(255, 255, 255));
			      
					Vizualizacija.buvetApraksts = Vizualizacija.buvetAprakstu("20 cm");
					Vizualizacija.buvetApraksts = Vizualizacija.buvetAprakstu("Plāna mīkla");
					
					Vizualizacija frame = new Vizualizacija();
					frame.setUndecorated(true); 
					frame.setVisible(true);
					frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Run.class.getResource("/bildes/ikona.png")));

					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	static void setProgress() {

		JPanel[] sloti = {slot1, slot2, slot3};
		JLabel[] slotApraksts = {slot1Apraksts, slot2Apraksts, slot3Apraksts};
		JLabel[] slotProcenti = {slot1Procenti, slot2Procenti, slot3Procenti};
		JLabel[] slotNosaukums = {slot1Nosaukums, slot2Nosaukums, slot3Nosaukums};
		JProgressBar[] slotProgress = {slot1Progress, slot2Progress, slot3Progress};
		JLabel[] slotBilde = {slot1Bilde, slot2Bilde, slot3Bilde};

		

		if(PasutijumuSaraksts.size() > 3)
			gaidaSkaitsLabel.setText("Gaida: " + (PasutijumuSaraksts.size() - 3) + " pica(s)");
		else
			gaidaSkaitsLabel.setText("Gaida: 0 pica(s)");

		if(PasutijumuSaraksts.size() <= 3)
		pasutijumuSkaitsLabel.setText(PasutijumuSaraksts.size()+"/3");



		for (int i = 0; i < PasutijumuSaraksts.size(); i++)
		{
			if(!sloti[i % 3].isVisible())
			{

			//set necessary components visible
			sloti[i % 3].setVisible(true);
			slotApraksts[i % 3].setVisible(true);
			slotProcenti[i % 3].setVisible(true);
			slotNosaukums[i % 3].setVisible(true);
			slotProgress[i % 3].setVisible(true);
			slotBilde[i % 3].setVisible(true);

			if (PasutijumuSaraksts.get(i).getGataviba() < 1) {
				String piedevas = "<html><body style='width: 10px'>";
				for (int j = 0; j < PasutijumuSaraksts.get(i).getPiedevas().size(); j++) {
					if (j == PasutijumuSaraksts.get(i).getPiedevas().size() - 1)
						piedevas += PasutijumuSaraksts.get(i).getPiedevas().get(j);
					else
						piedevas += PasutijumuSaraksts.get(i).getPiedevas().get(j) + ", ";
				}
				String apraksts = PasutijumuSaraksts.get(i).getIzmers() + ", " + PasutijumuSaraksts.get(i).getMiklasVeids() + ", "
						+ PasutijumuSaraksts.get(i).getMerce() + ", " + PasutijumuSaraksts.get(i).getSiers() + ", " + PasutijumuSaraksts.get(i).getGala() + ", " + piedevas;

				slotApraksts[i % 3].setText(apraksts);
				slotProcenti[i % 3].setText(PasutijumuSaraksts.get(i).getGataviba() + " %");
				slotBilde[i % 3].setIcon(new ImageIcon(((PasutijumuSaraksts.get(i).getBilde().getImage().getScaledInstance(172, 170, java.awt.Image.SCALE_SMOOTH)))));
				slotNosaukums[i % 3].setText(PasutijumuSaraksts.get(i).getNosaukums());
				slotProgress[i % 3].setValue(PasutijumuSaraksts.get(i).getGataviba());

				int finalI = i;
				new Thread(() -> {
					try {
						for (int j = 0; j <= 100; j++) {
							slotProgress[finalI % 3].setValue(j);
							Thread.sleep(new Random().nextLong(100) + 500);
							if (j <= 100)
								slotProcenti[finalI % 3].setText(j + "%");
						}


						SystemTray tray = SystemTray.getSystemTray();
						Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
						TrayIcon trayIcon = new TrayIcon(image, "Pica gatava");
						trayIcon.setImageAutoSize(true);
						trayIcon.setToolTip("Pica gatava");
						tray.add(trayIcon);
						trayIcon.displayMessage("Ziņojums", PasutijumuSaraksts.get(0).getNosaukums() + " gatava!", TrayIcon.MessageType.INFO);
						tray.remove(trayIcon);


						PasutijumuSaraksts.remove(0);
						slotApraksts[finalI % 3].setText("");
						slotProcenti[finalI % 3].setText("");
						slotBilde[finalI % 3].setIcon(null);
						slotNosaukums[finalI % 3].setText("");
						slotProgress[finalI % 3].setValue(0);
						sloti[finalI % 3].setVisible(false);
						slotProgress[finalI % 3].setVisible(false);
						slotApraksts[finalI % 3].setVisible(false);


					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}).start();

			}
		}
		}

	}
	

	static void defaultStateGatavamPogam(){
		gatavacm60.setSelected(false);
		gatavacm30.setSelected(false);
		gatavacm20.setSelected(true);

		gatavaplana.setSelected(true);
		gatavavideja.setSelected(false);
		gatavabieza.setSelected(false);
	}

}
