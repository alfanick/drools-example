package pl.put.poznan;

import javax.swing.JOptionPane;

public class Odpowiedz {
  private String pytanie;
  private String tekst;
  private float liczba;
  private boolean prawda;
  
  public Odpowiedz(String p, String t) {
    pytanie = p;
    tekst = t;
    System.out.println("tworze stringa");
  }
  
  public Odpowiedz(String p, float l) {
    pytanie = p;
    liczba = l;
    System.out.println("tworze floata");
  }
  
  public Odpowiedz(String p, boolean pd) {
    pytanie = p;
    prawda = pd;
    System.out.println("tworze boola");
  }
  
  public String getPytanie() {
    return pytanie;
  }
  
  public void setPytanie(String p) {
    pytanie = p;
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
