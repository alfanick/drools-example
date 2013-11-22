package pl.put.poznan;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.yaml.snakeyaml.*;

public class Pytanie {
  private String id;
  private String tresc;
  private ArrayList<String> odpowiedzi;
  
  public Pytanie() {
    
  }
  
  public String getId() {
    return id;
  }
  
  public void setId(String i) {
    id = i;
  }
  
  public String getTresc() {
    return tresc;
  }
  
  public void setTresc(String t) {
    tresc = t;
  }

  public ArrayList<String> getOdpowiedzi() {
    return odpowiedzi;
  }
  
  public void setOdpowiedzi(ArrayList<String> o) {
    odpowiedzi = o;
  }
  
  public static Odpowiedz zadaj(String id) {
    Pytanie pytanie = Pytanie.baza().get(id);
    
    if (pytanie != null) {
      Object odpowiedz = (Object)JOptionPane.showInputDialog(null, 
          pytanie.getTresc(),
          id,
          JOptionPane.QUESTION_MESSAGE,
          null,
          pytanie.getOdpowiedzi().toArray(),
          pytanie.getOdpowiedzi().get(0));
      
      // zalozmy ze to liczba
      if (odpowiedz instanceof Double) {
        return new Odpowiedz(id, ((Double)odpowiedz).floatValue());
      } else {
        if (((String) odpowiedz).equalsIgnoreCase("tak")) {
          return new Odpowiedz(id, true);
        } else
        if (((String) odpowiedz).equalsIgnoreCase("nie")) {
          return new Odpowiedz(id, false);
        } else {
          return new Odpowiedz(id, (String) odpowiedz);
        }
      }
    } else {
      JOptionPane.showMessageDialog(null, "Pytanie o ID '"+id+"' nie istnieje!");
      
      return null;
    }
  }
  
  private static HashMap<String, Pytanie> zawartosc = new HashMap<String, Pytanie>();
  private static HashMap<String, Pytanie> baza() {
    if (Pytanie.zawartosc.isEmpty()) {
      // wczytaj
      Yaml yaml = new Yaml();
      Map<String, Map<String, Object>> map;
      try {
        map = (Map<String, Map<String, Object>>)yaml.load((InputStream)(new FileInputStream(new File("rsc/pytania.yaml"))));

        
        for (Map.Entry<String, Map<String, Object>> entry : map.entrySet()) {
          Pytanie pytanie = new Pytanie();
          pytanie.setId(entry.getKey());
          pytanie.setTresc((String)(entry.getValue().get("pytanie")));
          pytanie.setOdpowiedzi((ArrayList<String>)(entry.getValue().get("odpowiedzi")));
          
          Pytanie.zawartosc.put(entry.getKey(), pytanie);
        }
      } catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    
    return zawartosc;
  }
}
