package pl.put.poznan;

import javax.swing.JOptionPane;

public class Odpowiedz {
  private String klucz;
  private String tekst;
  private float liczba;
  private boolean prawda;
  
  public Odpowiedz(String p, String t) {
    klucz = p;
    tekst = t;
    System.out.println("tworze stringa " + t);
  }
  
  public Odpowiedz(String p, float l) {
    klucz = p;
    liczba = l;
    System.out.println("tworze floata");
  }
  
  public Odpowiedz(String p, boolean pd) {
    klucz = p;
    prawda = pd;
    System.out.println("tworze boola");
  }
  
  public String getKlucz() {
    return klucz;
  }
  
  public void setKlucz(String p) {
    klucz = p;
  }
  
  public String getTekst() {
    return tekst;
  }
  
  public void setTekst(String t) {
    tekst = t;
  }
  
  public float getLiczba() {
    return liczba;
  }
  
  public void setLiczba(float l) {
    liczba = l;
  }
  
  public boolean getPrawda() {
    return prawda;
  }
  
  public void setPrawda(boolean p) {
    prawda = p;
  }
  
  public static void oglos(String s) {
    JOptionPane.showMessageDialog(null, s);
  }
}
