package prolab2;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Huseyin
 */
abstract public class Nesne {

    protected double dayaniklilik;
    protected double seviyePuani;
    protected static double a = 0.2;

    public Nesne(double dayaniklilik, double seviyePuani) {
        this.dayaniklilik = dayaniklilik;
        this.seviyePuani = seviyePuani;
    }

    public Nesne() {
        dayaniklilik = 20;
        seviyePuani = 0;
    }

    public double nesnePuaniGoster() {
        //dayanııklılık ve seviye puanı göster
        return dayaniklilik + seviyePuani;
    }

    abstract double etkiHesapla(Nesne rakip);

    public void durumGuncelle(double hasar, double deneyim) {
        dayaniklilik = dayaniklilik - hasar;
        seviyePuani = seviyePuani + deneyim;
    }

    public double getDayaniklilik() {
        return dayaniklilik;
    }

    public void setDayaniklilik(double dayaniklilik) {
        this.dayaniklilik = dayaniklilik;
    }

    public double getSeviyePuani() {
        return seviyePuani;
    }

    public void setSeviyePuani(double seviyePuani) {
        this.seviyePuani = seviyePuani;
    }

}

class Tas extends Nesne {
    
    
    protected double katilik;

    public Tas(double dayaniklilik, double seviyePuani, double katilik) {
        super(dayaniklilik, seviyePuani);
        this.katilik = katilik;

    }

    public Tas() {
        super.dayaniklilik = 20;
        super.seviyePuani = 0;
        katilik = 2;

    }

    @Override
    public double nesnePuaniGoster() {
        //dayanııklılık ve seviye puanı göster
        return dayaniklilik + seviyePuani;
    }

    @Override
    public double etkiHesapla(Nesne rakip) {
        
        double etki;
        if (rakip instanceof Kagit) {
            if (rakip instanceof OzelKagit) {
                OzelKagit temp = (OzelKagit) rakip;
                etki = katilik / ((1 - a) * temp.nufuz * temp.kalinlik);
            } else {
                Kagit temp = (Kagit) rakip;
                etki = katilik / ((1 - a) * temp.nufuz);
            }

        } else if (rakip instanceof Tas) {
            if (rakip instanceof AgirTas) {
                etki = katilik;
            } else {
                etki = katilik;
            }
        } else {
            if (rakip instanceof UstaMakas) {
                UstaMakas temp = (UstaMakas) rakip;
                etki = katilik / (a * temp.keskinlik * temp.hiz);
            } else {
                Makas temp = (Makas) rakip;
                etki = katilik / ((a) * temp.keskinlik);
            }
        }

        return etki;
    }

    @Override
    public void durumGuncelle(double hasar, double deneyim) {
        dayaniklilik = dayaniklilik - hasar;
        seviyePuani = seviyePuani + deneyim;
    }

   

   

    public double getKatilik() {
        return katilik;
    }

    public void setKatilik(double katilik) {
        this.katilik = katilik;
    }

    public static double getA() {
        return a;
    }

    public static void setA(double a) {
        Tas.a = a;
    }

}

class Kagit extends Nesne {
    
    protected double nufuz;

    public Kagit(double dayaniklilik, double seviyePuani, double nufuz) {
        super(dayaniklilik, seviyePuani);
        this.nufuz = nufuz;
    }

    public Kagit() {
        super.dayaniklilik = 20;
        super.seviyePuani = 0;
        nufuz = 2;
    }

    @Override
    public double nesnePuaniGoster() {
        //dayanııklılık ve seviye puanı göster
        return dayaniklilik + seviyePuani;
    }

    @Override
    public double etkiHesapla(Nesne rakip) {
        
        double etki;
        if (rakip instanceof Kagit) {
            if (rakip instanceof OzelKagit) {
                etki = nufuz;
            } else {
                etki = nufuz;
            }
        } else if (rakip instanceof Tas) {
            if (rakip instanceof AgirTas) {
                AgirTas temp = (AgirTas) rakip;
                etki = nufuz / (a * temp.katilik * temp.sicaklik);
            } else {
                Tas temp = (Tas) rakip;
                etki = nufuz / (a * temp.katilik);
            }
        } else {
            if (rakip instanceof UstaMakas) {
                UstaMakas temp = (UstaMakas) rakip;
                etki = nufuz / ((1 - a) * temp.keskinlik * temp.hiz);

            } else {
                Makas temp = (Makas) rakip;
                etki = nufuz / ((1 - a) * temp.keskinlik);
            }
        }

        return etki;
    }

    @Override
    public void durumGuncelle(double hasar, double deneyim) {
        dayaniklilik = dayaniklilik - hasar;
        seviyePuani = seviyePuani + deneyim;
    }

    public double getNufuz() {
        return nufuz;
    }

    public void setNufuz(double nufuz) {
        this.nufuz = nufuz;
    }

    public static double getA() {
        return a;
    }

    public static void setA(double a) {
        Kagit.a = a;
    }

}

class Makas extends Nesne {
    
    protected double keskinlik;

    public Makas(double dayaniklilik, double seviyePuani, double keskinlik) {
        super(dayaniklilik, seviyePuani);
        this.keskinlik = keskinlik;
    }

    public Makas() {
        super.dayaniklilik = 20;
        super.seviyePuani = 0;
        keskinlik = 2;
    }

    @Override
    public double nesnePuaniGoster() {
        //dayanııklılık ve seviye puanı göster
        return dayaniklilik + seviyePuani;
    }

    @Override
    public double etkiHesapla(Nesne rakip) {
        
        double etki;
        if (rakip instanceof Kagit) {
            if (rakip instanceof OzelKagit) {
                OzelKagit temp = (OzelKagit) rakip;
                etki = keskinlik / ((a) * temp.nufuz * temp.kalinlik);
            } else {
                Kagit temp = (Kagit) rakip;
                etki = keskinlik / ((a) * temp.nufuz);
            }

        } else if (rakip instanceof Tas) {
            if (rakip instanceof AgirTas) {
                AgirTas temp = (AgirTas) rakip;
                etki = keskinlik / ((1 - a) * temp.katilik * temp.sicaklik);
            } else {
                Tas temp = (Tas) rakip;
                etki = keskinlik / ((1 - a) * temp.katilik);
            }
        } else {
            if (rakip instanceof UstaMakas) {
                etki = keskinlik;
            } else {
                etki = keskinlik;
            }
        }

        return etki;
    }

    @Override
    public void durumGuncelle(double hasar, double deneyim) {
        dayaniklilik = dayaniklilik - hasar;
        seviyePuani = seviyePuani + deneyim;
    }

    public double getKeskinlik() {
        return keskinlik;
    }

    public void setKeskinlik(double keskinlik) {
        this.keskinlik = keskinlik;
    }

    public static double getA() {
        return a;
    }

    public static void setA(double a) {
        Makas.a = a;
    }

}

class AgirTas extends Tas {
    
    protected double sicaklik;

    public AgirTas(double dayaniklilik, double seviyePuani, double katilik, double sicaklik) {
        super(dayaniklilik, seviyePuani, katilik);
        this.sicaklik = sicaklik;

    }

    public AgirTas() {
        super.dayaniklilik = 20;
        super.katilik = 2;
        super.seviyePuani = 0;
        sicaklik = 2;
    }

    @Override
    public double nesnePuaniGoster() {
        //dayanııklılık ve seviye puanı göster
        return dayaniklilik + seviyePuani;
    }

    @Override
    public double etkiHesapla(Nesne rakip) {
        
        double etki;
        if (rakip instanceof Kagit) {
            if (rakip instanceof OzelKagit) {
                OzelKagit temp = (OzelKagit) rakip;
                etki = katilik * sicaklik / ((1 - a) * temp.nufuz * temp.kalinlik);
            } else {
                Kagit temp = (Kagit) rakip;
                etki = katilik * sicaklik / ((1 - a) * temp.nufuz);
            }

        } else if (rakip instanceof Tas) {
            if (rakip instanceof AgirTas) {
                etki = katilik * sicaklik;
            } else {
                etki = katilik * sicaklik;
            }
        } else {
            if (rakip instanceof UstaMakas) {
                UstaMakas temp = (UstaMakas) rakip;
                etki = katilik * sicaklik / (a * temp.keskinlik * temp.hiz);
            } else {
                Makas temp = (Makas) rakip;
                etki = katilik * sicaklik / ((a) * temp.keskinlik);
            }
        }

        return etki;
    }

    @Override
    public void durumGuncelle(double hasar, double deneyim) {
        dayaniklilik = dayaniklilik - hasar;
        seviyePuani = seviyePuani + deneyim;
    }

    public double getSicaklik() {
        return sicaklik;
    }

    public void setSicaklik(double sicaklik) {
        this.sicaklik = sicaklik;
    }

    public static double getA() {
        return a;
    }

    public static void setA(double a) {
        AgirTas.a = a;
    }

}

class OzelKagit extends Kagit {
   
    protected double kalinlik;

    public OzelKagit(double dayaniklilik, double seviyePuani, double nufuz, double kalinlik) {
        super(dayaniklilik, seviyePuani, nufuz);
        this.kalinlik = kalinlik;
    }

    public OzelKagit() {
        super.dayaniklilik = 20;
        super.nufuz = 2;
        super.seviyePuani = 0;
        kalinlik = 2;
    }

    @Override
    public double nesnePuaniGoster() {
        //dayanııklılık ve seviye puanı göster
        return dayaniklilik + seviyePuani;
    }

    @Override
    public double etkiHesapla(Nesne rakip) {
        double etki;
        if (rakip instanceof Kagit) {
            if (rakip instanceof OzelKagit) {
                etki = nufuz * kalinlik;
            } else {
                etki = nufuz * kalinlik;
            }
        } else if (rakip instanceof Tas) {
            if (rakip instanceof AgirTas) {
                AgirTas temp = (AgirTas) rakip;
                etki = nufuz * kalinlik / (a * temp.katilik * temp.sicaklik);
            } else {
                Tas temp = (Tas) rakip;
                etki = nufuz * kalinlik / (a * temp.katilik);
            }
        } else {
            if (rakip instanceof UstaMakas) {
                UstaMakas temp = (UstaMakas) rakip;
                etki = nufuz * kalinlik / ((1 - a) * temp.keskinlik * temp.hiz);

            } else {
                Makas temp = (Makas) rakip;
                etki = nufuz * kalinlik / ((1 - a) * temp.keskinlik);
            }
        }

        return etki;
    }

    @Override
    public void durumGuncelle(double hasar, double deneyim) {
        dayaniklilik = dayaniklilik - hasar;
        seviyePuani = seviyePuani + deneyim;
    }

    public double getKalinlik() {
        return kalinlik;
    }

    public void setKalinlik(double kalinlik) {
        this.kalinlik = kalinlik;
    }

    public static double getA() {
        return a;
    }

    public static void setA(double a) {
        OzelKagit.a = a;
    }

}

class UstaMakas extends Makas {
   
    protected double hiz;

    public UstaMakas(double dayaniklilik, double seviyePuani, double keskinlik, double hiz) {
        super(dayaniklilik, seviyePuani, keskinlik);
        this.hiz = hiz;
    }

    public UstaMakas() {
        super.dayaniklilik = 20;
        super.keskinlik = 2;
        super.seviyePuani = 0;
        hiz = 2;
    }

    @Override
    public double nesnePuaniGoster() {
        //dayanııklılık ve seviye puanı göster
        return dayaniklilik + seviyePuani;
    }

    @Override
    public double etkiHesapla(Nesne rakip) {
        double etki;
        if (rakip instanceof Kagit) {
            if (rakip instanceof OzelKagit) {
                OzelKagit temp = (OzelKagit) rakip;
                etki = keskinlik * hiz / ((a) * temp.nufuz * temp.kalinlik);
            } else {
                Kagit temp = (Kagit) rakip;
                etki = keskinlik * hiz / ((a) * temp.nufuz);
            }

        } else if (rakip instanceof Tas) {
            if (rakip instanceof AgirTas) {
                AgirTas temp = (AgirTas) rakip;
                etki = keskinlik * hiz / ((1 - a) * temp.katilik * temp.sicaklik);
            } else {
                Tas temp = (Tas) rakip;
                etki = keskinlik * hiz / ((1 - a) * temp.katilik);
            }
        } else {
            if (rakip instanceof UstaMakas) {
                etki = keskinlik * hiz;
            } else {
                etki = keskinlik * hiz;
            }
        }

        return etki;
    }

    @Override
    public void durumGuncelle(double hasar, double deneyim) {
        dayaniklilik = dayaniklilik - hasar;
        seviyePuani = seviyePuani + deneyim;
    }

    public double getHiz() {
        return hiz;
    }

    public void setHiz(double hiz) {
        this.hiz = hiz;
    }

    
}
