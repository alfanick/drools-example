package pl.put.poznan;

import javax.swing.JOptionPane;

public class Fakt {
  private String klucz;
  private String tekst;
  private float liczba;
  private boolean prawda;
  
  public Fakt(String p, String t) {
    klucz = p;
    tekst = t;
    System.out.println("tworze stringa '" + t+"'");
  }
  
  public Fakt(String p, float l) {
    klucz = p;
    liczba = l;
    System.out.println("tworze floata");
  }
  
  public Fakt(String p, boolean pd) {
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
  
  public static boolean upewnij_sie(String s) {
    return JOptionPane.showConfirmDialog(null, "Czy jest to " + s + "?", s, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
  }
}
