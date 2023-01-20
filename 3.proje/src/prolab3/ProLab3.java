/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prolab3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.apache.poi.hssf.usermodel.HeaderFooter.date;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Huseyin
 */

public class ProLab3 {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    
    public static void main(String args[]) throws IOException, ParseException {
        int secim=0;
        
        String aranacak = aranacakailebasibulma();
        Kisi ailebasi = ailebasibulma(aranacak);
        ailebasi.agacaramaveolusturma();
        ailebasi.esbulveekle();
        ailebasi.aileagacigoster();
        gui a = new gui();
        if(secim!=0)
            a.framekapa();
        a.frameac();
        a.agacolustur(ailebasi);
        //ailebasi.bekarlarisirala();
        //System.out.println("\n\n\n\n\n\n\n");
        //ailebasi.aileagacigoster();
        System.out.println("\n\n\n\n\n\n\n");
        ailebasi.aileagacigösterimfarkli();
        System.out.println("\n\n\n\n\n\n\n");
        int kkokook = 0;
        ArrayList<Kisi> aranacakkangurubuarlist = new ArrayList<Kisi>();
        String aranacakkangrubu = "A";
        System.out.println("kan gurubu " + aranacakkangrubu + " olan aile üyeleri");
        ailebasi.kangurububul(aranacakkangrubu, aranacakkangurubuarlist);
        for (Kisi kisi : aranacakkangurubuarlist) {
            System.out.println(kisi.getAd());
        }
        
        System.out.println("\n\n\n\n\n\n\n");
        ArrayList <Integer> l  = new ArrayList();
        l.add(0);
        ailebasi.maksderinlik(l);
        System.out.println("Nesil sayisi : "+l);
        ArrayList <Kisi> p = new ArrayList();
        System.out.println("cocugu olmayanlar");
        System.out.println("\n\n");        
        ailebasi.cocuguolmayanlarinyassiralamasi(p);
        System.out.println("\n\n");
        try{
            Collections.sort(p,(o1, o2) -> o1.getDogumdateformat().compareTo(o2.getDogumdateformat()));
        }
        catch(NullPointerException e){
            
        }
        for(Kisi kisi : p){
            System.out.println(kisi.getAd()+" "+kisi.getSoyad()+" "+kisi.getDogumyili());
        }
        System.out.println("\n\n");
        ArrayList <Kisi> r = new ArrayList<>();
                System.out.println("uveyler");
        ailebasi.uveyleribul(r);
        System.out.println("\n\n");        
        Collections.sort(r,(o1,o2)-> o1.getAd().compareTo(o2.getAd()));
        for(Kisi kisi : r){
            System.out.println(kisi.getAd()+" "+kisi.getSoyad());
        }
        System.out.println("\n\n");
        System.out.println("yapilacak islemi seciniz : \n1-)kisinin aile agacini yazin\n2-)Kisiden sonra kac nesil \n3-)iki kisi girin yakinlik gosterilsin\n4-)cikis");      
        Scanner keyboard = new Scanner(System.in);
        secim  = keyboard.nextInt();
        switch (secim) {
            case 1:
                aileagaciicindekisibul(ailebasi);
                a.framekapa();
                break;
            case 2:
                kisidensonrakacnesil(ailebasi);
            case 3:
                
            case 4:
                System.exit(0);
            default:
                System.exit(0);
                
        }
        
        /*
        ArrayList<ArrayList> meslekarraylist = new ArrayList<ArrayList>();

        ailebasi.aynimeslekleribul(meslekarraylist);
        for (ArrayList<Kisi> AL : meslekarraylist) {
            System.out.println(AL.get(0).getMeslek());
            if (AL.get(0).getMeslek().isBlank()) {
                System.out.println("Mesleksizler");
            }
            for (Kisi kisi : AL) {
                System.out.println(kisi.getAd());
            }
            System.out.println("\n\n\n");
        }
        ArrayList<ArrayList> isimarraylist = new ArrayList<ArrayList>();
        ailebasi.ayniisimleribul(isimarraylist);
        for (ArrayList<Kisi> AL : isimarraylist) {
            if (AL.size() > 1) {
                for (Kisi kisi : AL) {
                    System.out.println(kisi.getAd()+(2022-kisi.getDogumyili()));
                }
                System.out.println("\n\n\n");
            }
        }
        */
        

    }
    public static String aranacakailebasibulma() {
        System.out.println("Aranacak Aileyi yaziniz");
        Scanner keyboard = new Scanner(System.in);
        String aranacak = keyboard.nextLine();
        return aranacak;

    }
    public static void kisidensonrakacnesil(Kisi ailebasi){
        System.out.println("Aranacak kisiyi yaziniz");
        Scanner keyboard = new Scanner(System.in);
        String jııojjı = keyboard.nextLine();
        ArrayList <Kisi> o = new ArrayList();
        ailebasi.kisibul(jııojjı, o);
        Kisi kisibulkisisi = o.get(0);
        ArrayList <Integer> maksderinlikAL = new ArrayList();
        maksderinlikAL.add(0);
        kisibulkisisi.maksderinlik(maksderinlikAL);
        System.out.println("kişiden sonra kaç nesili oldugu : "+(maksderinlikAL.get(0)-kisibulkisisi.getDerinlik()));
        
    }
    public static void aileagaciicindekisibul(Kisi ailebasi){
        System.out.println("Aranacak kisiyi yaziniz");
        Scanner keyboard = new Scanner(System.in);
        String jııojjı = keyboard.nextLine();
        ArrayList <Kisi> o = new ArrayList();
        ailebasi.kisibul(jııojjı, o);
        Kisi kisibulkisisi = o.get(0);
        kisibulkisisi.setKardes(null);
        gui h = new gui();
        h.frameac();
        h.agacolustur(kisibulkisisi);
    }
    public static Kisi ailebasibulma(String aranacak) throws IOException, ParseException    {
        FileInputStream fis = new FileInputStream(new File("C:\\Users\\Enes\\Documents\\NetBeansProjects\\proLab3\\Test Dosyası.xlsx"));
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        Kisi ailebasi = new Kisi();
        for (int k = 0; k < wb.getNumberOfSheets(); k++)/*sayfalar arası gezinir */ {
            XSSFSheet sheet = wb.getSheetAt(k);

            Row row = sheet.getRow(1);
            ArrayList<String> atanacaklar = new ArrayList<String>();

            for (int j = 0; j < row.getLastCellNum(); j++)/*bilgiler arası gezinir*/ {
                Cell cell = row.getCell(j);
                atanacaklar.add(cell.toString());
            }
            if (atanacaklar.get(2).equals(aranacak)) {
                ailebasi = new Kisi(Double.valueOf(atanacaklar.get(0)), atanacaklar.get(1), atanacaklar.get(2),
                        atanacaklar.get(3), atanacaklar.get(4), atanacaklar.get(5),
                        atanacaklar.get(6), atanacaklar.get(7), atanacaklar.get(8),
                        atanacaklar.get(10), atanacaklar.get(11));

            }
        }
        return ailebasi;
    }
   
}



class Kisi {

    private int derinlik = 1;
    private Kisi es;
    private Kisi cocuk;
    private Kisi kardes;
    private double id;
    private String ad;
    private String soyad;
    private String dogumTarihi;
    private String anneAdi;
    private String babaAdi;
    private String kanGrubu;
    private String meslek;
    private String kizlikSoyadi;
    private String cinsiyet;
    private int dogumyili, dogumgunu;
    private Date dogumdateformat;
    private String esad;
    private String essoyad;
    public Kisi(Double id, String ad, String soyad, String dogumTarihi, String esadvesoyad, String anneAdi, String babaAdi, String kanGrubu, String meslek, String kizlikSoyadi, String cinsiyet) {
        if (esadvesoyad.indexOf(' ') != -1 && esadvesoyad != null) {
            this.esad = esadvesoyad.substring(0, esadvesoyad.indexOf(' '));
            this.essoyad = esadvesoyad.substring(esadvesoyad.indexOf(' '));
        } else if (esadvesoyad.isBlank()) {

        } else {
            this.esad = esadvesoyad;
            this.essoyad = soyad;
        }
        SimpleDateFormat format1 = new SimpleDateFormat("dd-MMM-yy");
        try {
            this.dogumdateformat = format1.parse(dogumTarihi);
        } catch (ParseException ex) {
            // Logger.getLogger(Kisi.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.ad = ad;
        this.anneAdi = anneAdi;
        this.babaAdi = babaAdi;
        this.cinsiyet = cinsiyet;
        this.dogumTarihi = dogumTarihi;
        this.dogumyili = Integer.parseInt(dogumTarihi.substring(7));
        this.dogumgunu = Integer.parseInt(dogumTarihi.substring(0, 2));
        this.id = id;
        this.kanGrubu = kanGrubu;
        this.kizlikSoyadi = kizlikSoyadi;
        this.meslek = meslek;
        this.soyad = soyad;
        
    }
    
    public Kisi(String ad, String soyad) {
        this.ad = ad;
        this.soyad = soyad;
    }

    public Kisi() {

    }
    
    public void maksderinlik(ArrayList<Integer>a){
        if(this.derinlik>a.get(0))
            a.set(0,this.derinlik);
        if(this.cocuk!=null)
            this.cocuk.maksderinlik(a);
        if(this.kardes!=null)
            this.kardes.maksderinlik(a);
    }
    private int enbuyukkardes = 0;
    public int makskardessayisi(){
        if(this.cocuk!=null)
            this.cocuk.makskardessayisi();
        if(this.kardes!=null){
            enbuyukkardes++;
            this.kardes.makskardessayisi();
        }
            
        return enbuyukkardes;
    }
    private int kardessayisi=0;
    public int kardessayisibul(){
        if(this.kardes!=null){
            kardessayisi++;
            this.kardes.kardessayisibul();
        }
        return kardessayisi;
    }
    public void uveyleribul(ArrayList <Kisi> uveyler){
        if(this.kardes!=null){
            if((((this.anneAdi.equalsIgnoreCase(this.kardes.anneAdi)&&!this.babaAdi.equalsIgnoreCase(this.kardes.babaAdi)))||((!this.anneAdi.equalsIgnoreCase(this.kardes.anneAdi)&&this.babaAdi.equalsIgnoreCase(this.kardes.babaAdi))))&&!uveyler.contains(this.kardes)){
                System.out.println(this.kardes.ad+" eklendi");
                uveyler.add(this.kardes);
            }
            this.kardes.uveyleribul(uveyler);
        }
        if(this.cocuk!=null){
            if(this.esad!=null && (((!this.ad.equalsIgnoreCase(this.cocuk.anneAdi)&&this.esad.equalsIgnoreCase(this.cocuk.babaAdi))||(this.ad.equalsIgnoreCase(this.cocuk.babaAdi)&&!this.esad.equalsIgnoreCase(this.cocuk.anneAdi)))||(((this.ad.equalsIgnoreCase(this.cocuk.anneAdi)&&!this.esad.equalsIgnoreCase(this.cocuk.babaAdi))||(!this.ad.equalsIgnoreCase(this.cocuk.babaAdi)&&this.esad.equalsIgnoreCase(this.cocuk.anneAdi)))))&&!uveyler.contains(this.cocuk)){
                System.out.println(this.cocuk.ad+" eklendi");
                uveyler.add(this.cocuk);
            }
            this.cocuk.uveyleribul(uveyler);
        }
    }
    public void kangurububul(String aranacakkangrubu, ArrayList<Kisi> arlst) {
        if (this.kanGrubu.substring(0, this.kanGrubu.indexOf('(')).equalsIgnoreCase(aranacakkangrubu) || this.kanGrubu.equalsIgnoreCase(aranacakkangrubu)) {
            arlst.add(this);
        }
        if (this.es != null && (this.es.kanGrubu.substring(0, this.es.kanGrubu.indexOf('(')).equalsIgnoreCase(aranacakkangrubu) || this.es.kanGrubu.equalsIgnoreCase(aranacakkangrubu))) {
            arlst.add(this.es);
        }
        if (this.cocuk != null) {
            this.cocuk.kangurububul(aranacakkangrubu, arlst);
        }
        if (this.kardes != null) {
            this.kardes.kangurububul(aranacakkangrubu, arlst);
        }
    }

    public void aynimeslekleribul(ArrayList<ArrayList> meslekarraylist) {
        int sayac = 0;
        for (ArrayList<Kisi> kisiAL : meslekarraylist) {
            if (kisiAL.get(0).meslek.equalsIgnoreCase(this.meslek)) {
                kisiAL.add(this);
                sayac = 1;
                break;
            }
        }
        if (sayac == 0) {
            ArrayList<Kisi> kisiarylist = new ArrayList<Kisi>();
            kisiarylist.add(this);
            meslekarraylist.add(kisiarylist);
        }
        int essayac = 0;
        for (ArrayList<Kisi> kisiAL : meslekarraylist) {
            if (this.es != null && kisiAL.get(0).meslek.equalsIgnoreCase(this.es.meslek)) {
                kisiAL.add(this.es);
                essayac = 1;
                break;
            }
        }
        if (this.es != null && essayac == 0) {
            ArrayList<Kisi> kisiarylist = new ArrayList<Kisi>();
            kisiarylist.add(this.es);
            meslekarraylist.add(kisiarylist);
        }
        if (this.cocuk != null) {
            this.cocuk.aynimeslekleribul(meslekarraylist);
        }
        if (this.kardes != null) {
            this.kardes.aynimeslekleribul(meslekarraylist);
        }

    }

    public void ayniisimleribul(ArrayList<ArrayList> isimler) {
        int sayac = 0;
        for (ArrayList<Kisi> kisiAL : isimler) {
            if (kisiAL.get(0).ad.equalsIgnoreCase(this.ad)) {
                kisiAL.add(this);
                sayac = 1;
                break;
            }
        }
        if (sayac == 0) {
            ArrayList<Kisi> kisiarylist = new ArrayList<Kisi>();
            kisiarylist.add(this);
            isimler.add(kisiarylist);
        }
        int essayac = 0;
        for (ArrayList<Kisi> kisiAL : isimler) {
            if (this.es != null && kisiAL.get(0).ad.equalsIgnoreCase(this.es.ad)) {
                kisiAL.add(this.es);
                essayac = 1;
                break;
            }
        }
        if (this.es != null && essayac == 0) {
            ArrayList<Kisi> kisiarylist = new ArrayList<Kisi>();
            kisiarylist.add(this.es);
            isimler.add(kisiarylist);
        }
        if (this.cocuk != null) {
            this.cocuk.ayniisimleribul(isimler);
        }
        if (this.kardes != null) {
            this.kardes.ayniisimleribul(isimler);
        }
    }

    public void cocuguolmayanlarinyassiralamasi(ArrayList <Kisi> v) {
        
        if (this.cocuk != null) {
            this.cocuk.cocuguolmayanlarinyassiralamasi(v);
        }
        else{
            v.add(this);
            System.out.println(this.ad+"eklendi");
        }
        if (this.kardes != null) {
            this.kardes.cocuguolmayanlarinyassiralamasi(v);
        }
    }
    public void kisibul(String isim,ArrayList<Kisi> o){
        if(o.size()>=1)
            return;
        if(this.ad.equalsIgnoreCase(isim) || this.ad.concat(this.soyad).equalsIgnoreCase(isim) || this.ad.concat(" ").concat(this.soyad).equalsIgnoreCase(isim)){
            o.add(this);
            return;
        }
        if(this.cocuk!=null)
            this.cocuk.kisibul(isim,o);
        if(this.kardes!=null)
            this.kardes.kisibul(isim,o);
    }
    public void aileagacigoster() {
        System.out.print(" id : " + (int) this.id + " " + this.ad + " " + this.soyad + "   esi : ");
        if (this.es != null) {
            System.out.print(this.es.id);
        }

        System.out.print(" " + this.esad + " " + this.essoyad);
        System.out.print(" derinlikleri : " + this.derinlik);
        System.out.print("  ///////////////  ");
        if (this.kardes != null) {
            this.kardes.aileagacigoster();
        }
        System.out.println("");
        if (this.cocuk != null) {
            this.cocuk.aileagacigoster();
        }

    }
    public void aileagacigösterimfarkli()
    {
        System.out.print(this.id +" "+this.ad+ " "+ this.soyad+" ");
        if(this.es!=null){
            System.out.println("esi : "+es.id+" "+es.ad+" "+es.kizlikSoyadi+" "+es.soyad);
        }
        else if(esad!=null){
            System.out.println("esi : "+this.esad);
        }
        System.out.println();
        if(this.cocuk!=null)
        {
            this.cocuk.aileagacigösterimfarkli();
        }
        if(this.kardes!=null)
        {
            this.kardes.aileagacigösterimfarkli();
        }
    }

    public void agacaramaveolusturma() throws IOException {
        FileInputStream fis = new FileInputStream(new File("C:\\Users\\Enes\\Documents\\NetBeansProjects\\proLab3\\Test Dosyası.xlsx"));
        XSSFWorkbook wb = new XSSFWorkbook(fis);

        for (int k = 0; k < wb.getNumberOfSheets(); k++)/*sayfalar arası gezinir */ {
            XSSFSheet sheet = wb.getSheetAt(k);
            Row acim = sheet.getRow(1);
            String acimid = acim.getCell(0).toString();
            if (Double.valueOf(acimid) == this.id) {

                for (int i = 1; i < sheet.getLastRowNum() + 1; i++)/*kişiler arası gezinir*/ {
                    Row row = sheet.getRow(i);
                    ArrayList<String> atanacaklar = new ArrayList<String>();

                    for (int j = 0; j < row.getLastCellNum(); j++)/*bilgiler arası gezinir*/ {
                        Cell cell = row.getCell(j);
                        if (cell != null) {
                            atanacaklar.add(cell.toString());
                        } else if (cell == null) {
                            atanacaklar.add("-1");
                        }
                    }
                    Kisi eklenecekkisi = new Kisi(Double.valueOf(atanacaklar.get(0)), atanacaklar.get(1), atanacaklar.get(2),
                            atanacaklar.get(3), atanacaklar.get(4), atanacaklar.get(5),
                            atanacaklar.get(6), atanacaklar.get(7), atanacaklar.get(8),
                            atanacaklar.get(10), atanacaklar.get(11));

                    this.kisiekle(eklenecekkisi);
                    //System.out.println(atanacaklar);
                    //System.out.println();
                }
            }
        }

    }

    public void esbulveekle() throws IOException {
        FileInputStream fis = new FileInputStream(new File("C:\\Users\\Enes\\Documents\\NetBeansProjects\\proLab3\\Test Dosyası.xlsx"));
        XSSFWorkbook wb = new XSSFWorkbook(fis);

        for (int k = 0; k < wb.getNumberOfSheets(); k++)/*sayfalar arası gezinir */ {
            XSSFSheet sheet = wb.getSheetAt(k);
            for (int i = 1; i < sheet.getLastRowNum() + 1; i++)/*kişiler arası gezinir*/ {
                Row row = sheet.getRow(i);
                ArrayList<String> atanacaklar = new ArrayList<String>();

                for (int j = 0; j < row.getLastCellNum(); j++)/*bilgiler arası gezinir*/ {
                    Cell cell = row.getCell(j);
                    if (cell != null) {
                        atanacaklar.add(cell.toString());
                    } else if (cell == null) {
                        atanacaklar.add("-1");
                    }
                }
                Kisi eklenecekkisi = new Kisi(Double.valueOf(atanacaklar.get(0)), atanacaklar.get(1), atanacaklar.get(2),
                        atanacaklar.get(3), atanacaklar.get(4), atanacaklar.get(5),
                        atanacaklar.get(6), atanacaklar.get(7), atanacaklar.get(8),
                        atanacaklar.get(10), atanacaklar.get(11));

                this.esekle(eklenecekkisi);
                //System.out.println(atanacaklar);
                //System.out.println();

            }
        }
    }

    public void esekle(Kisi eklenecekkisi) {
        if (this.esad != null && this.es == null && this.esad.equals(eklenecekkisi.ad) && eklenecekkisi.esad.equals(this.ad)) {
            this.es = eklenecekkisi;
            eklenecekkisi.es = this;
        } else if (this.esad != null) {
            if (this.cocuk != null) {
                this.cocuk.esekle(eklenecekkisi);
            }
            if (this.kardes != null) {
                this.kardes.esekle(eklenecekkisi);
            }
        }
    }

    public void kisiekle(Kisi eklenecekkisi) {

        if (this.id == eklenecekkisi.id) {
        } else if (this.es != null && this.es.id == eklenecekkisi.id) {
        } else if (((this.ad.equals(eklenecekkisi.anneAdi)) || (this.ad.equals(eklenecekkisi.babaAdi)))) {
            if (this.cocuk == null) {
                this.cocuk = eklenecekkisi;
                this.cocuk.derinlik = this.derinlik + 1;

            } else {

                this.cocuk.kisiekle(eklenecekkisi);
            }
        } else if ((eklenecekkisi.anneAdi.equals(this.anneAdi) && eklenecekkisi.babaAdi.equals(this.babaAdi))) {
            if (this.kardes == null) {
                this.kardes = eklenecekkisi;
                this.kardes.derinlik = this.derinlik;
            } else {
                this.kardes.kisiekle(eklenecekkisi);
            }
        } else {
            if (this.cocuk != null) {
                this.cocuk.kisiekle(eklenecekkisi);
            }
            if (this.kardes != null) {
                this.kardes.kisiekle(eklenecekkisi);
            }

        }

    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getDogumTarihi() {
        return dogumTarihi;
    }

    public void setDogumTarihi(String dogumTarihi) {
        this.dogumTarihi = dogumTarihi;
    }

    public String getAnneAdi() {
        return anneAdi;
    }

    public void setAnneAdi(String anneAdi) {
        this.anneAdi = anneAdi;
    }

    public String getBabaAdi() {
        return babaAdi;
    }

    public void setBabaAdi(String babaAdi) {
        this.babaAdi = babaAdi;
    }

    public String getKanGrubu() {
        return kanGrubu;
    }

    public void setKanGrubu(String kanGrubu) {
        this.kanGrubu = kanGrubu;
    }

    public String getMeslek() {
        return meslek;
    }

    public void setMeslek(String meslek) {
        this.meslek = meslek;
    }

    public String getKizlikSoyadi() {
        return kizlikSoyadi;
    }

    public void setKizlikSoyadi(String kizlikSoyadi) {
        this.kizlikSoyadi = kizlikSoyadi;
    }

    public String getCinsiyet() {
        return cinsiyet;
    }

    public void setCinsiyet(String cinsiyet) {
        this.cinsiyet = cinsiyet;
    }

    public Kisi getEs() {
        return es;
    }

    public void setEs(Kisi es) {
        this.es = es;
    }

    public Kisi getCocuk() {
        return cocuk;
    }

    public void setCocuk(Kisi cocuk) {
        this.cocuk = cocuk;
    }

    public Kisi getKardes() {
        return kardes;
    }

    public void setKardes(Kisi kardes) {
        this.kardes = kardes;
    }

    public int getDerinlik() {
        return derinlik;
    }

    public void setDerinlik(int derinlik) {
        this.derinlik = derinlik;
    }

    public int getDogumyili() {
        return dogumyili;
    }

    public void setDogumyili(int dogumyili) {
        this.dogumyili = dogumyili;
    }

    public int getDogumgunu() {
        return dogumgunu;
    }

    public void setDogumgunu(int dogumgunu) {
        this.dogumgunu = dogumgunu;
    }

    public Date getDogumdateformat() {
        return dogumdateformat;
    }

    public void setDogumdateformat(Date dogumdateformat) {
        this.dogumdateformat = dogumdateformat;
    }

    public String getEsad() {
        return esad;
    }

    public void setEsad(String esad) {
        this.esad = esad;
    }

    public String getEssoyad() {
        return essoyad;
    }

    public void setEssoyad(String essoyad) {
        this.essoyad = essoyad;
    }

}
