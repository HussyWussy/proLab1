package prolab2;

import java.util.*;
import javax.swing.*;


/**
 *
 * @author Huseyin
 */
public abstract class Oyuncu {

    protected double oyuncuID;
    protected String oyuncuAdi;
    protected double skor;
    protected ArrayList<Nesne> oyuncuNesneListesi = new ArrayList<Nesne>();
    
    

    public Oyuncu(double oyuncuID, String oyuncuAdi, double skor) {
        this.oyuncuID = oyuncuID;
        this.oyuncuAdi = oyuncuAdi;
        this.skor = skor;
    }

    public Oyuncu() {
        this.oyuncuAdi = "Oyuncu";
        this.oyuncuID = 0;
        this.skor = 0;
    }

    public double SkorGoster() {
        double skor=0;
        for(int i=0;i<oyuncuNesneListesi.size();i++){
            if(this.NesneSec(i).dayaniklilik>0){
                skor+=this.NesneSec(i).dayaniklilik;
            }
        }
        return skor;
    }

    public Nesne NesneSec(int giris) {
        return oyuncuNesneListesi.get(giris);
    }

    public void NesneGir(Nesne giris) {
        oyuncuNesneListesi.add(giris);

    }
    public ArrayList GetOyuncuNL(){
        return oyuncuNesneListesi;
    }
    public void SetOyuncuNesne(int giris1,Nesne giris2){
        oyuncuNesneListesi.set(giris1, giris2);
    }
    public int OyundakiNesneSayisi(){
        int saglamsayisi=0;
        for(int i=0;i<this.oyuncuNesneListesi.size();i++){
            if(this.NesneSec(i).dayaniklilik>0)
                saglamsayisi++;
        }
        return saglamsayisi;
    }

    public double getOyuncuID() {
        return oyuncuID;
    }

    public void setOyuncuID(double oyuncuID) {
        this.oyuncuID = oyuncuID;
    }

    public String getOyuncuAdi() {
        return oyuncuAdi;
    }

    public void setOyuncuAdi(String oyuncuAdi) {
        this.oyuncuAdi = oyuncuAdi;
    }

    public double getSkor() {
        return skor;
    }

    public void setSkor(double skor) {
        this.skor = skor;
    }

    public ArrayList<Nesne> getOyuncuNesneListesi() {
        return oyuncuNesneListesi;
    }

    public void setOyuncuNesneListesi(ArrayList<Nesne> oyuncuNesneListesi) {
        this.oyuncuNesneListesi = oyuncuNesneListesi;
    }
    
}

class Kullanici extends Oyuncu {
    protected ArrayList <JButton> oyuncudugmeleri = new ArrayList<JButton>();
    public Kullanici(double oyuncuID, String oyuncuAdi, double skor) {
        super(oyuncuID, oyuncuAdi, skor);

    }
    public JButton dugmeAl(int giris){
        return oyuncudugmeleri.get(giris);
    }
    public void dugmeGir(JButton giris){
        oyuncudugmeleri.add(giris);
    }
    public void setdugme(int degisken)
    {
        
    }
    
    public Kullanici() {
        super.oyuncuAdi = "Kullanici";
        super.oyuncuID = 1;
        super.skor = 0;

    }
    public void isimvefotodegis(int degisken)
    {
        JButton temp = new JButton();
        temp=oyuncudugmeleri.get(degisken);
        String isim = temp.getText();
        if(isim.startsWith("Tas"))
            {
               
               temp.setText("Agir Tas");
               
               ImageIcon foto= new ImageIcon(this.getClass().getResource("agirtas.png"));
               temp.setIcon(foto);
               oyuncudugmeleri.set(degisken, temp);
               oyuncudugmeleri.get(degisken).setName("Agir Tas");
               
            }
            else if(temp.getText().startsWith("Kagit"))
            {
               temp.setText("Özel Kagit");
               ImageIcon foto= new ImageIcon(this.getClass().getResource("özelkagit.png"));
               temp.setIcon(foto);
               oyuncudugmeleri.set(degisken, temp);
                 oyuncudugmeleri.get(degisken).setName("Özel Kagit");
            }
            else if(temp.getText().startsWith("Makas"))
            {
               temp.setText("Usta Makas");
               ImageIcon foto= new ImageIcon(this.getClass().getResource("ustamakas.png"));
               temp.setIcon(foto);
               oyuncudugmeleri.set(degisken, temp);
                oyuncudugmeleri.get(degisken).setName("Usta Makas");
            }
        
    }
    public void durumyaz(int degisken)
    {
      JButton temp = new JButton();
      temp=oyuncudugmeleri.get(degisken);
      String isim = temp.getText();
        
        
        if(isim.startsWith("Tas"))
            {
               
               temp.setText(temp.getName()+"|"+"Dayanıklılık: "+oyuncuNesneListesi.get(degisken).getDayaniklilik()+"| |Seviye Puanı "+oyuncuNesneListesi.get(degisken).getSeviyePuani()+"|");                           
               oyuncudugmeleri.set(degisken, temp);
               
            }
            else if(temp.getText().startsWith("Agir"))
            {
               
               temp.setText(temp.getName()+"|"+"Dayanıklılık: "+oyuncuNesneListesi.get(degisken).getDayaniklilik()+"| |Seviye Puanı "+oyuncuNesneListesi.get(degisken).getSeviyePuani()+"|");                           
               oyuncudugmeleri.set(degisken, temp);
                
            }
            else if(temp.getText().startsWith("Makas"))
            {
                temp.setText(temp.getName()+"|"+"Dayanıklılık: "+oyuncuNesneListesi.get(degisken).getDayaniklilik()+"| |Seviye Puanı "+oyuncuNesneListesi.get(degisken).getSeviyePuani()+"|");                           
              
                oyuncudugmeleri.set(degisken, temp);
               
            }
        else if(temp.getText().startsWith("Usta"))
            {
                temp.setText(temp.getName()+"|"+"Dayanıklılık: "+oyuncuNesneListesi.get(degisken).getDayaniklilik()+"| |Seviye Puanı "+oyuncuNesneListesi.get(degisken).getSeviyePuani()+"|");                           
              
                oyuncudugmeleri.set(degisken, temp);
               
            }
        else if(temp.getText().startsWith("Kagit"))
            {
                temp.setText(temp.getName()+"|"+"Dayanıklılık: "+oyuncuNesneListesi.get(degisken).getDayaniklilik()+"| |Seviye Puanı "+oyuncuNesneListesi.get(degisken).getSeviyePuani()+"|");                           
              
                oyuncudugmeleri.set(degisken, temp);
               
            }
        else if(temp.getText().startsWith("Özel"))
            {
                temp.setText(temp.getName()+"|"+"Dayanıklılık: "+oyuncuNesneListesi.get(degisken).getDayaniklilik()+"| |Seviye Puanı "+oyuncuNesneListesi.get(degisken).getSeviyePuani()+"|");                           
              
                oyuncudugmeleri.set(degisken, temp);
               
            }
    }
    @Override
    public Nesne NesneSec(int giris) {
        return this.oyuncuNesneListesi.get(giris);

    }
    public JButton GetDugme(int giris) {
        return this.oyuncudugmeleri.get(giris);
    }

    public ArrayList<JButton> getOyuncudugmeleri() {
        return oyuncudugmeleri;
    }

    public void setOyuncudugmeleri(ArrayList<JButton> oyuncudugmeleri) {
        this.oyuncudugmeleri = oyuncudugmeleri;
    }
    
}
    class Bilgisayar extends Oyuncu {

        public Bilgisayar(double oyuncuID, String oyuncuAdi, double skor) {
            super(oyuncuID, oyuncuAdi, skor);            
        }

        public Bilgisayar() {
            super.oyuncuAdi = "Bilgisayar";
            super.oyuncuID = 2;
            super.skor = 0;
            
        }
        public void NesneOlustur() {
            oyuncuNesneListesi.clear();
        for (int i = 0; i < 5; i++) {
            Random rand = new Random();
            
            int randomInt = rand.nextInt(3);
            switch (randomInt) {
                case 0 -> {
                    Tas tas = new Tas(20, 0, 2);
                    oyuncuNesneListesi.add(tas);
                }
                case 1 -> {
                    Kagit kagit = new Kagit(20, 0, 2);
                    oyuncuNesneListesi.add(kagit);
                }
                case 2 -> {
                    Makas makas = new Makas(20, 0, 2);
                    oyuncuNesneListesi.add(makas);
                }
                default -> {
                }
            }
        }
        }
        @Override
        public Nesne NesneSec(int giris){
            if (giris == -1) {
                Collections.shuffle(oyuncuNesneListesi);
                return oyuncuNesneListesi.get(0);
            }
            return oyuncuNesneListesi.get(giris);

        }
        public void fotodegis(int sayacac,JLabel rakipsecim){
            Nesne temp = this.NesneSec(sayacac);
            if(temp instanceof Tas){
                if(temp instanceof AgirTas){
                    ImageIcon foto= new ImageIcon(this.getClass().getResource("agirtas.png"));
                    rakipsecim.setIcon(foto);
                }
                else{
                    ImageIcon foto= new ImageIcon(this.getClass().getResource("tas1.png"));
                    rakipsecim.setIcon(foto);
                }
            }
            else if(temp instanceof Kagit){
                if(temp instanceof OzelKagit){
                    ImageIcon foto= new ImageIcon(this.getClass().getResource("özelkagit.png"));
                    rakipsecim.setIcon(foto);
                }
                else{
                    ImageIcon foto= new ImageIcon(this.getClass().getResource("kagit1.png"));
                    rakipsecim.setIcon(foto);
                }
            }
            else if(temp instanceof Makas){
                if(temp instanceof UstaMakas){
                    ImageIcon foto= new ImageIcon(this.getClass().getResource("ustamakas.png"));
                    rakipsecim.setIcon(foto);
                }
                else{
                    ImageIcon foto= new ImageIcon(this.getClass().getResource("makas1.png"));
                    rakipsecim.setIcon(foto);
                }
            }
            
        }

    public double getOyuncuID() {
        return oyuncuID;
    }

    public void setOyuncuID(double oyuncuID) {
        this.oyuncuID = oyuncuID;
    }

    public String getOyuncuAdi() {
        return oyuncuAdi;
    }

    public void setOyuncuAdi(String oyuncuAdi) {
        this.oyuncuAdi = oyuncuAdi;
    }

    public double getSkor() {
        return skor;
    }

    public void setSkor(double skor) {
        this.skor = skor;
    }

    public ArrayList<Nesne> getOyuncuNesneListesi() {
        return oyuncuNesneListesi;
    }

    public void setOyuncuNesneListesi(ArrayList<Nesne> oyuncuNesneListesi) {
        this.oyuncuNesneListesi = oyuncuNesneListesi;
    }
        
    }


