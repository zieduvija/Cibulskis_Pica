package pica;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Pica {
	String nosaukums, izmers, miklasVeids, merce, siers, gala, apraksts;
    ArrayList<String> piedevas;
    double cena; int skaits;
    ImageIcon bilde;
    
    Pica(int skaits, String nosaukums, String izmers, String miklasVeids, String merce,String siers, String gala, ArrayList<String>  piedevas, double cena, ImageIcon bilde , String apraksts )
    {
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
    }
    
    public String getNosaukums() {
    	return this.nosaukums;
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
    
    
 

}
