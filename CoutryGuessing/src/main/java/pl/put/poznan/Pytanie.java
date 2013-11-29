package pl.put.poznan;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.yaml.snakeyaml.*;

public class Pytanie {
  private String id;
  private String tresc;
  private String wartosc;
  private Map<String, String> odpowiedzi;

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

  public String getWartosc() {
    return wartosc;
  }

  public void setWartosc(String w) {
    wartosc = w;
  }

  public Map<String, String> getOdpowiedzi() {
    return odpowiedzi;
  }

  public void setOdpowiedzi(Map<String, String> o) {
    odpowiedzi = o;
  }

  public String getKeyByValue(String value) {
    for (Entry<String, String> entry : odpowiedzi.entrySet()) {
        if (value.equalsIgnoreCase(entry.getValue())) {
            return entry.getKey();
        }
    }
    return null;
}

  public static Fakt zadaj(String id) {
    Pytanie pytanie = Pytanie.baza().get(id);

    if (pytanie != null) {
      Object odpowiedz;

      do {
        odpowiedz = (Object)JOptionPane.showInputDialog(null,
            pytanie.getTresc(),
            id,
            JOptionPane.QUESTION_MESSAGE,
            null,
            pytanie.getOdpowiedzi().values().toArray(),
            pytanie.getOdpowiedzi().get(0));
      } while (odpowiedz == null);

      // zalozmy ze to liczba
      if (odpowiedz instanceof Double) {
        return new Fakt(pytanie.wartosc, ((Double)odpowiedz).floatValue());
      } else {
        String key = pytanie.getKeyByValue((String) odpowiedz);

        System.out.println(key);
        if (key.equalsIgnoreCase("t")) {
          return new Fakt(pytanie.wartosc, true);
        } else
        if (key.equalsIgnoreCase("n")) {
          return new Fakt(pytanie.wartosc, false);
        } else
        if (key.equalsIgnoreCase("nw")) {
          return null;
        } else {
          return new Fakt(pytanie.wartosc, key);
        }
      }
    } else {
      JOptionPane.showMessageDialog(null, "Pytanie o ID '"+id+"' nie istnieje!");

      return null;
    }
  }

  private static HashMap<String, Pytanie> zawartosc = new HashMap<String, Pytanie>();
  @SuppressWarnings("unchecked")
  private static HashMap<String, Pytanie> baza() {
    if (Pytanie.zawartosc.isEmpty()) {
      // wczytaj
      Yaml yaml = new Yaml();
      Map<String, Map<String, Object>> map;
        map = (Map<String, Map<String, Object>>)yaml.load((InputStream)(Pytanie.class.getResourceAsStream("/pytania.yaml")));


        for (Map.Entry<String, Map<String, Object>> entry : map.entrySet()) {
          Pytanie pytanie = new Pytanie();
          pytanie.setId(entry.getKey());
          pytanie.setWartosc((String)(entry.getValue().get("wartosc")));
          pytanie.setTresc((String)(entry.getValue().get("pytanie")));
          pytanie.setOdpowiedzi((Map<String, String>)(entry.getValue().get("odpowiedzi")));
          System.out.println((Map<String, String>)(entry.getValue().get("odpowiedzi")));
          Pytanie.zawartosc.put(entry.getKey(), pytanie);
        }
    }

    return zawartosc;
  }
}
