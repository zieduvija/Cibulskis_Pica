package pica;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Pica {
	String nosaukums, izmers, miklasVeids, merce, siers, gala, apraksts;
	ArrayList<String> piedevas;
	double cena;
	int skaits;
	ImageIcon bilde;
	int gataviba;

	Pica(int skaits, String nosaukums, String izmers, String miklasVeids, String merce, String siers, String gala,
			ArrayList<String> piedevas, double cena, ImageIcon bilde, String apraksts, int gataviba) {
		this.skaits = skaits;
		this.nosaukums = nosaukums;
		this.izmers = izmers;
		this.miklasVeids = miklasVeids;
		this.merce = merce;
		this.siers = siers;
		this.gala = gala;
		this.piedevas = piedevas;
		this.cena = cena;
		this.bilde = bilde;
		this.apraksts = apraksts;
		this.gataviba = gataviba;
	}

	public String getNosaukums() {
		return this.nosaukums;
	}

	public String getIzmers() {
		return this.izmers;
	}

	public String getMiklasVeids() {
		return this.miklasVeids;
	}

	public String getMerce() {
		return this.merce;
	}

	public String getSiers() {
		return this.siers;
	}

	public String getGala() {
		return this.gala;
	}

	public ArrayList<String> getPiedevas() {
		return this.piedevas;
	}

	public double getCena() {
		return this.cena;
	}

	public ImageIcon getBilde() {
		return this.bilde;
	}

	public String getApraksts() {

		return this.apraksts;
	}

	public int getSkaits() {
		return this.skaits;
	}

	public void setGataviba(int jaunaVertiba) {
		this.gataviba = jaunaVertiba;
	}

	public int getGataviba() {
		return this.gataviba;
	}

}
