package prolab2;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Huseyin
 */
public class Oyun implements ActionListener {

    public Oyun() {
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Bilgisayar pc = new Bilgisayar(2, "Windows", 0);
        Kullanici bne = new Kullanici(1, "Hüseyin", 0);
        Bilgisayar pc1 = new Bilgisayar(3, "Linux", 0);
        secim secim = new secim();
        secim.secimUI(pc, bne, pc1);

    }
    static int sayacac = 0;
    static int roundsayac = 1;
    static int bilgisayarakarsioynanacakroundsayisi = 10;
    static int bilgisayarbilgisayarakarsiroundsayisi = 10;
    int nesnesayisi = 0;

    public void nesnesecmegui(Bilgisayar pc, Kullanici bne, Bilgisayar pc1) {

        JFrame f = new JFrame("nesnesecim");
        ImageIcon img = new ImageIcon(this.getClass().getResource("dogma.jpg"));
        f.setIconImage(img.getImage());
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JPanel p4 = new JPanel();

        JLabel l1 = new JLabel();
        JLabel l2 = new JLabel();
        JLabel l3 = new JLabel();
        JLabel l4 = new JLabel();

        FlowLayout flowlayout = new FlowLayout();

        ImageIcon makas = new ImageIcon(this.getClass().getResource("makas.png"));
        ImageIcon tas = new ImageIcon(this.getClass().getResource("tas.png"));
        ImageIcon kagit = new ImageIcon(this.getClass().getResource("kagit.png"));

        l1.setIcon(tas);
        l2.setIcon(kagit);
        l3.setIcon(makas);
        bne.GetOyuncuNL().clear();
        JLabel bcd = new JLabel("Lütfen " + (5 - nesnesayisi) + " tane nesne seciniz.");
        p4.add(bcd);
        JButton btn_tas = new JButton("tas");
        JButton btn_kagit = new JButton("kagit");
        JButton btn_makas = new JButton("makas");
        btn_tas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Oyun Oyun3 = new Oyun();
                JFrame frame = new JFrame();

                Tas tas = new Tas(20, 0, 2);
                bne.NesneGir(tas);
                nesnesayisi++;
                bcd.setText("Lütfen " + (5 - nesnesayisi) + " tane nesne seciniz.");
                if (nesnesayisi == 5) {
                    f.dispose();
                    Oyun3.bilgisayaraKarsiOyunOyna(frame, bne, pc);
                }
            }
        });

        btn_kagit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Oyun Oyun3 = new Oyun();
                JFrame frame = new JFrame();

                Kagit kagit = new Kagit(20, 0, 2);
                bne.NesneGir(kagit);
                nesnesayisi++;
                bcd.setText("Lütfen " + (5 - nesnesayisi) + " tane nesne seciniz.");
                if (nesnesayisi == 5) {
                    f.dispose();
                    Oyun3.bilgisayaraKarsiOyunOyna(frame, bne, pc);
                }

            }
        });

        btn_makas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Oyun Oyun3 = new Oyun();
                JFrame frame = new JFrame();
                nesnesayisi++;
                bcd.setText("Lütfen " + (5 - nesnesayisi) + " tane nesne seciniz.");
                Makas makas = new Makas(20, 0, 2);
                bne.NesneGir(makas);
                if (nesnesayisi == 5) {
                    f.dispose();
                    Oyun3.bilgisayaraKarsiOyunOyna(frame, bne, pc);
                }

            }
        });

        p1.setBounds(0, 0, 300, 600);
        p2.setBounds(330, 0, 300, 600);
        p3.setBounds(660, 0, 300, 600);
        p4.setBounds(0, 700, 500, 200);
        f.setLayout(flowlayout);
        bcd.setSize(200, 200);
        btn_kagit.setSize(250, 150);
        btn_tas.setSize(250, 150);
        btn_makas.setSize(250, 150);

        p1.setVisible(true);
        p2.setVisible(true);
        p3.setVisible(true);
        p4.setVisible(true);

        p1.add(l1);
        p2.add(l2);
        p3.add(l3);

        p4.add(btn_tas);
        p4.add(btn_kagit);
        p4.add(btn_makas);

        f.add(p1);
        f.add(p2);
        f.add(p3);
        f.add(p4);
        f.add(btn_tas);
        f.add(btn_kagit);
        f.add(btn_makas);
        f.setSize(1200, 800);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void bilgisayaraKarsiOyunOyna(JFrame frame, Kullanici bne, Bilgisayar pc) {

        JPanel oyunpaneli = new JPanel();
        pc.NesneOlustur();

        int tassayac = 1;
        int kagıtsayac = 1;
        int makassayac = 1;
        for (int i = 0; i < bne.GetOyuncuNL().size(); i++) {
            String temp = bne.NesneSec(i).getClass().toString().substring(14);
            if (bne.NesneSec(i) instanceof Tas) {
                temp = temp + tassayac;
                tassayac++;

            } else if (bne.NesneSec(i) instanceof Kagit) {
                temp = temp + kagıtsayac;
                kagıtsayac++;
            } else if (temp.equals("Makas")) {

                temp = temp + makassayac;
                makassayac++;
            }
            JButton button = new JButton(temp);
            //button.setSize(200, 200);
            if (temp.startsWith("Tas")) {
                ImageIcon foto = new ImageIcon(this.getClass().getResource("tas1.png"));
                button.setIcon(foto);
            } else if (temp.startsWith("Kagit")) {
                ImageIcon foto = new ImageIcon(this.getClass().getResource("kagit1.png"));
                button.setIcon(foto);
            } else if (temp.startsWith("Makas")) {
                ImageIcon foto = new ImageIcon(this.getClass().getResource("makas1.png"));
                button.setIcon(foto);
            }

            bne.dugmeGir(button);
            bne.GetDugme(i).setName(temp);
            oyunpaneli.add(bne.dugmeAl(i));
            bne.durumyaz(i);
        }

        oyunpaneli.setBounds(0, 0, 1200, 500);
        oyunpaneli.setLayout(new FlowLayout());
        JPanel secimkasilastirmasi1 = new JPanel();
        JPanel secimkasilastirmasi2 = new JPanel();
        JPanel oyuncusecimnesne = new JPanel();
        JPanel rakipsecimnesne = new JPanel();

        frame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = 2;
        frame.add(oyunpaneli, c);
        c.gridwidth = 1;
        frame.add(secimkasilastirmasi1);
        frame.add(secimkasilastirmasi2);
        JLabel oyuncusecim = new JLabel();
        oyuncusecim.setText(" = Oyuncunun seçtiği ||  Nesnenin adlığı hasar  ||");
        JLabel rakipsecim = new JLabel();
        rakipsecim.setText("= Bilgisayarın seçtiği ||  Nesnenin aldığı hasar  ||");
        oyuncusecim.setIcon(new ImageIcon(this.getClass().getResource("300x200oyuncuchad.png")));
        rakipsecim.setIcon(new ImageIcon(this.getClass().getResource("300x200monitorchad.png")));
        secimkasilastirmasi1.add(oyuncusecim);
        secimkasilastirmasi2.add(rakipsecim);

        secimkasilastirmasi1.setBounds(20, 540, 600, 200);
        secimkasilastirmasi2.setBounds(660, 540, 600, 200);
        for (int i = 0; i < bne.oyuncudugmeleri.size(); i++) {
            aksiyon(frame, bne.dugmeAl(i), bne, pc, oyuncusecim, rakipsecim);
        }
        frame.setLayout(null);
        frame.setSize(1400, 1200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void bilgisayarbilgisayarakarsi(JFrame frame, Bilgisayar pc, Bilgisayar pc1, Kullanici bne) {
        JPanel yenipanel = new JPanel();
        JButton yeniden = new JButton("Yeniden Oyna");
        yeniden.setSize(200, 200);
        yenipanel.setLayout(new BorderLayout());
        yeniden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secim secim = new secim();
                secim.secimUI(pc, bne, pc1);
                frame.dispose();
            }
        });

        TextArea text = new TextArea();
        yenipanel.add(text, BorderLayout.CENTER);
        yenipanel.add(yeniden, BorderLayout.PAGE_END);
        frame.add(yenipanel);
        frame.pack();

        text.setFont(text.getFont().deriveFont(text.getFont().getSize() + 3.0f));
        text.setEditable(false);
        int sayac = 0;
        int sayac1 = 0;
        String yazi = new String();
        pc.NesneOlustur();
        pc1.NesneOlustur();
        for (int i = 0; i < pc.OyundakiNesneSayisi(); i++) {
            yazi += pc.NesneSec(i).toString() + "\n";
        }
        String yazi1 = new String();
        for (int i = 0; i < pc1.OyundakiNesneSayisi(); i++) {
            yazi1 += pc1.NesneSec(i).toString() + "\n";
        }
        text.setText(text.getText() + "ilk bilgisayar nesneleri : \n" + yazi + "\n");
        text.setText(text.getText() + "ikinci bilgisayar nesneleri : \n" + yazi1 + "\n");
        for (int i = 1; i < bilgisayarbilgisayarakarsiroundsayisi + 1; i++) {
            if (pc.OyundakiNesneSayisi() == 0 || pc1.OyundakiNesneSayisi() == 0) {
                break;
            }
            if (sayac >= pc.GetOyuncuNL().size()) {
                sayac = 0;
                pc.NesneSec(-1);
            }
            while (pc.NesneSec(sayac).dayaniklilik <= 0) {
                sayac++;
                if (sayac >= pc.GetOyuncuNL().size()) {
                    sayac = 0;
                }
            }
            if (sayac1 >= pc1.GetOyuncuNL().size()) {
                sayac1 = 0;
                pc1.NesneSec(-1);
            }
            while (pc1.NesneSec(sayac1).dayaniklilik <= 0) {
                sayac1++;
                if (sayac1 >= pc1.GetOyuncuNL().size()) {
                    sayac1 = 0;
                }
            }
            double ilkbilgisayarattack = pc.NesneSec(sayac).etkiHesapla(pc1.NesneSec(sayac1));
            double ikincibilgisayarattack = pc1.NesneSec(sayac1).etkiHesapla(pc.NesneSec(sayac));
            text.setText(text.getText() + "\n\n" + i + "'nci round :              \n");
            text.setText(text.getText() + "ilk bilgisayar nesnesi : " + pc.NesneSec(sayac).toString() + "   dayaniklilik : " + pc.NesneSec(sayac).dayaniklilik + " aldığı hasar -" + ikincibilgisayarattack + "   yeni dayanıklılığı :     " + String.valueOf(pc.NesneSec(sayac).dayaniklilik - ikincibilgisayarattack));
            text.setText(text.getText() + "\nikinci bilgisayar nesnesi : " + pc1.NesneSec(sayac1).toString() + "   dayaniklilik : " + pc1.NesneSec(sayac1).dayaniklilik + " aldığı hasar -" + ilkbilgisayarattack + "   yeni dayanıklılığı :     " + String.valueOf(pc1.NesneSec(sayac1).dayaniklilik - ilkbilgisayarattack));
            if (ilkbilgisayarattack > ikincibilgisayarattack) {
                pc.NesneSec(sayac).durumGuncelle(ikincibilgisayarattack, 20);
                pc1.NesneSec(sayac1).durumGuncelle(ilkbilgisayarattack, 0);
            } else if (ikincibilgisayarattack > ilkbilgisayarattack) {
                pc.NesneSec(sayac).durumGuncelle(ikincibilgisayarattack, 0);
                pc1.NesneSec(sayac1).durumGuncelle(ilkbilgisayarattack, 20);
            } else {
                pc.NesneSec(sayac).durumGuncelle(ikincibilgisayarattack, 0);
                pc1.NesneSec(sayac1).durumGuncelle(ilkbilgisayarattack, 0);
            }
            Nesne tempust = pc.NesneSec(sayac);
            Nesne tempast = pc1.NesneSec(sayac1);
            LevelUp(sayac, pc);
            LevelUp(sayac1, pc1);
            if (pc.NesneSec(sayac).getClass() != tempust.getClass()) {
                text.setText(text.getText() + "\n" + tempust + "seviye atlayıp " + pc.NesneSec(sayac) + "e dönüşmüştür\n");

            } else if (pc1.NesneSec(sayac1).getClass() != tempast.getClass()) {
                text.setText(text.getText() + "\n" + tempast + " seviye atlayıp " + pc1.NesneSec(sayac1) + "e dönüşmüştür\n");

            }
            if (tempust.getDayaniklilik() <= 0) {
                text.setText(text.getText() + "\n" + tempust + " yenilmiştir\n");

            }
            if (tempast.getDayaniklilik() <= 0) {
                text.setText(text.getText() + "\n" + tempast + " yenilmiştir\n");

            }

            sayac++;
            sayac1++;

            frame.setSize(1000, 800);
            frame.setVisible(true);
            frame.setLayout(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        String bitisyazisi = new String();
        if (pc.SkorGoster() > pc1.SkorGoster()) {
            bitisyazisi = "\n\nilk bilgisayar skor : " + pc.SkorGoster() + "\nikinci bilgisayar skor : " + pc1.SkorGoster() + "\n KAZANAN İLK BİLGİSAYAR!!!!!\n\n\n";
        } else if (pc.SkorGoster() < pc1.SkorGoster()) {
            bitisyazisi = "\n\nilk bilgisayar skor : " + pc.SkorGoster() + "\nikinci bilgisayar skor : " + pc1.SkorGoster() + "\n KAZANAN İKİNCİ BİLGİSAYAR!!!!!\n\n\n";
        } else {
            bitisyazisi = "\n\nilk bilgisayar skor : " + pc.SkorGoster() + "\nikinci bilgisayar skor : " + pc1.SkorGoster() + "\n BERABERE!!!!!\n\n\n";
        }
        text.setText(text.getText() + bitisyazisi);
        try {
            FileWriter yazici = yaz();
            yazici.write(text.getText());
            yazici.close();
        } catch (IOException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    static String str = new String();

    public static void aksiyon(JFrame frame, JButton button, Kullanici bne, Bilgisayar rakipOyuncu, JLabel oyuncusecim, JLabel rakipsecim) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int degisken = bne.oyuncudugmeleri.indexOf(e.getSource());
                //aksiyon(aksiyongirisi, bne, pc);
                if (sayacac == rakipOyuncu.GetOyuncuNL().size()) {
                    sayacac = 0;
                    rakipOyuncu.NesneSec(-1);
                }
                while (rakipOyuncu.NesneSec(sayacac).getDayaniklilik() <= 0) {
                    sayacac++;
                    if (sayacac >= rakipOyuncu.GetOyuncuNL().size()) {
                        sayacac = 0;
                    }
                }
                int saglamsayisi = bne.OyundakiNesneSayisi();
                bne.GetDugme(degisken).setVisible(false);

                double kullaniciattack = bne.NesneSec(degisken).etkiHesapla(rakipOyuncu.NesneSec(sayacac));
                double rakipattack = rakipOyuncu.NesneSec(sayacac).etkiHesapla(bne.NesneSec(degisken));
                if (kullaniciattack > rakipattack) {
                    bne.NesneSec(degisken).durumGuncelle(rakipattack, 20);
                    rakipOyuncu.NesneSec(sayacac).durumGuncelle(kullaniciattack, 0);
                } else if (rakipattack > kullaniciattack) {
                    bne.NesneSec(degisken).durumGuncelle(rakipattack, 0);
                    rakipOyuncu.NesneSec(sayacac).durumGuncelle(kullaniciattack, 20);
                } else {
                    bne.NesneSec(degisken).durumGuncelle(rakipattack, 0);
                    rakipOyuncu.NesneSec(sayacac).durumGuncelle(kullaniciattack, 0);

                }
                bne.durumyaz(degisken);

                Nesne tempust = bne.NesneSec(degisken);
                Nesne tempast = rakipOyuncu.NesneSec(sayacac);

                oyuncusecim.setIcon(bne.GetDugme(degisken).getIcon());
                oyuncusecim.setText(Double.toString(rakipattack * (-1)));
                rakipOyuncu.fotodegis(sayacac, rakipsecim);
                rakipsecim.setText(Double.toString(kullaniciattack * (-1)));
                LevelUp(degisken, bne);
                LevelUp(sayacac, rakipOyuncu);
                if (bne.NesneSec(degisken).getDayaniklilik() <= 0) {
                    oyuncusecim.setText(oyuncusecim.getText() + " yenildi");
                } else if (bne.NesneSec(degisken) != tempust) {
                    oyuncusecim.setText(oyuncusecim.getText() + " seviye atladı");
                }

                if (rakipOyuncu.NesneSec(sayacac).getDayaniklilik() <= 0) {
                    rakipsecim.setText(rakipsecim.getText() + " yenildi");
                } else if (rakipOyuncu.NesneSec(sayacac) != tempast) {
                    rakipsecim.setText(rakipsecim.getText() + " seviye atladı");
                }
                int kapalidugmesayisi = 0;
                for (int i = 0; i < bne.GetOyuncuNL().size(); i++) {
                    if (!bne.GetDugme(i).isVisible()) {
                        kapalidugmesayisi++;
                    }
                }

                if (kapalidugmesayisi == bne.GetOyuncuNL().size()) {
                    for (int i = 0; i < bne.GetOyuncuNL().size(); i++) {
                        if (bne.NesneSec(i).getDayaniklilik() > 0) {
                            bne.GetDugme(i).setVisible(true);
                        }
                    }
                }

                try {

                    FileWriter yazici = yaz();
                    if (roundsayac == 1) {
                        str += ("Kullanici Nesneler  ---------- Bilgisayar Nesneler\n");
                        for (int i = 0; i < bne.GetOyuncuNL().size(); i++) {
                            str += (bne.NesneSec(i).toString() + "--------" + rakipOyuncu.NesneSec(i) + "\n");
                        }
                    }
                    str += ("\n\n" + roundsayac + "'nci round :              \n");
                    str += ("Kullanıcı Nesnesi : " + tempust.toString() + "   dayaniklilik : " + (bne.NesneSec(degisken).getDayaniklilik() + rakipattack) + " aldığı hasar -" + rakipattack + "   yeni dayanıklılığı :     " + String.valueOf(bne.NesneSec(degisken).dayaniklilik));
                    str += ("\nBilgisayar nesnesi : " + tempast.toString() + "   dayaniklilik : " + (rakipOyuncu.NesneSec(sayacac).getDayaniklilik() + kullaniciattack) + " aldığı hasar -" + kullaniciattack + "   yeni dayanıklılığı :     " + String.valueOf(rakipOyuncu.NesneSec(sayacac).dayaniklilik));

                    if (bne.NesneSec(degisken).getClass() != tempust.getClass()) {
                        str += "\n" + tempust + "seviye atlayıp " + bne.NesneSec(degisken) + "e dönüşmüştür\n";
                    } else if (rakipOyuncu.NesneSec(sayacac).getClass() != tempast.getClass()) {
                        str += "\n" + tempast + " seviye atlayıp " + rakipOyuncu.NesneSec(sayacac) + "e dönüşmüştür\n";
                    }
                    if (tempust.getDayaniklilik() <= 0) {
                        str += "\n" + tempust + " yenilmiştir\n";
                    }
                    if (tempast.getDayaniklilik() <= 0) {
                        str += "\n" + tempast + " yenilmiştir\n";
                    }
                    if (roundsayac == bilgisayarakarsioynanacakroundsayisi || bne.OyundakiNesneSayisi() == 0 || rakipOyuncu.OyundakiNesneSayisi() == 0) {
                        Oyun bitis = new Oyun();
                        BitisEkrani(frame, bne, rakipOyuncu);
                        if (bne.SkorGoster() > rakipOyuncu.SkorGoster()) {
                            str += "\n\nKullanıcı skor : " + bne.SkorGoster() + "\nBilgisayar skor : " + rakipOyuncu.SkorGoster() + "\n KAZANAN KULLANICI!!!!!\n\n\n";
                        } else if (bne.SkorGoster() < rakipOyuncu.SkorGoster()) {
                            str += "\n\nKullanıcı skor : " + bne.SkorGoster() + "\nBilgisayar skor : " + rakipOyuncu.SkorGoster() + "\n KAZANAN BİLGİSAYAR!!!!!\n\n\n";
                        } else {
                            str += "\n\nKullanıcı skor : " + bne.SkorGoster() + "\nBilgisayar skor : " + rakipOyuncu.SkorGoster() + "\n BERABERE!!!!!\n\n\n";
                        }
                        yazici.write(str);
                        yazici.close();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
                }

                sayacac++;
                roundsayac++;
            }

        });

    }

    public static void LevelUp(int degisken, Oyuncu oyuncu) {

        if (oyuncu.NesneSec(degisken) instanceof AgirTas || oyuncu.NesneSec(degisken) instanceof OzelKagit || oyuncu.NesneSec(degisken) instanceof UstaMakas) {
        } else if (oyuncu.NesneSec(degisken).getSeviyePuani() > 30) {
            String temp = oyuncu.NesneSec(degisken).getClass().toString().substring(14);
            if (oyuncu.NesneSec(degisken) instanceof Kagit) {
                if (oyuncu instanceof Kullanici) {
                    Kullanici tempoyuncu = (Kullanici) oyuncu;
                    OzelKagit okagit = new OzelKagit(tempoyuncu.NesneSec(degisken).getDayaniklilik(), 0, 2, 2);
                    tempoyuncu.SetOyuncuNesne(degisken, okagit);
                    tempoyuncu.isimvefotodegis(degisken);
                    tempoyuncu.durumyaz(degisken);

                }
                OzelKagit okagit = new OzelKagit(oyuncu.NesneSec(degisken).getDayaniklilik(), 0, 2, 2);
                oyuncu.SetOyuncuNesne(degisken, okagit);

            } else if (oyuncu.NesneSec(degisken) instanceof Tas) {

                if (oyuncu instanceof Kullanici) {
                    Kullanici tempoyuncu = (Kullanici) oyuncu;
                    AgirTas atas = new AgirTas(tempoyuncu.NesneSec(degisken).getDayaniklilik(), 0, 2, 2);
                    tempoyuncu.SetOyuncuNesne(degisken, atas);
                    tempoyuncu.isimvefotodegis(degisken);
                    tempoyuncu.durumyaz(degisken);

                }
                AgirTas atas = new AgirTas(oyuncu.NesneSec(degisken).getDayaniklilik(), 0, 2, 2);
                oyuncu.SetOyuncuNesne(degisken, atas);

            } else {
                if (oyuncu instanceof Kullanici) {
                    Kullanici tempoyuncu = (Kullanici) oyuncu;
                    UstaMakas umakas = new UstaMakas(tempoyuncu.NesneSec(degisken).getDayaniklilik(), 0, 2, 2);
                    tempoyuncu.GetOyuncuNL().set(degisken, umakas);
                    tempoyuncu.isimvefotodegis(degisken);
                    tempoyuncu.durumyaz(degisken);
                }
                UstaMakas umakas = new UstaMakas(oyuncu.NesneSec(degisken).getDayaniklilik(), 0, 2, 2);
                oyuncu.SetOyuncuNesne(degisken, umakas);
            }

        }
    }

    public static void BitisEkrani(JFrame frame, Kullanici bne, Bilgisayar rakipOyuncu) {
        Bilgisayar pc1 = new Bilgisayar(3, "Linux", 0);
        Bilgisayar cu = new Bilgisayar(2, "Windows", 0);
        Kullanici cuk = new Kullanici(1, "Hüseyin", 0);
        frame.dispose();
        JFrame sonframe = new JFrame();
        JButton yeniden = new JButton("Yeniden Oyna");
        yeniden.setSize(200, 200);
        yeniden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                roundsayac = 1;
                secim secim = new secim();
                secim.secimUI(cu, cuk, pc1);
                bne.GetOyuncuNL().clear();
                pc1.NesneOlustur();
                rakipOyuncu.NesneOlustur();
                sonframe.dispose();
            }
        });
        sonframe.setSize(1200, 800);
        sonframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        sonframe.setVisible(true);
        JLabel yazik = new JLabel();
        JLabel yaziq = new JLabel();

        yazik.setIcon(new ImageIcon("C:\\Users\\Enes\\Desktop\\proLab2\\build\\classes\\prolab2\\300x200oyuncuchad.png"));
        yaziq.setIcon(new ImageIcon("C:\\Users\\Enes\\Desktop\\proLab2\\build\\classes\\prolab2\\300x200monitorchad.png"));

        JPanel ust = new JPanel();
        JPanel alt = new JPanel();
        sonframe.setLayout(new GridLayout(2, 1));
        sonframe.add(ust);
        sonframe.add(alt);
        yazik.setText("Kullanici skoru : " + bne.SkorGoster() + "                  ");
        yaziq.setText("Bilgisayar skoru : " + rakipOyuncu.SkorGoster());
        ust.add(yazik);
        ust.add(yaziq);
        JLabel kazanan = new JLabel();
        if (bne.SkorGoster() > rakipOyuncu.SkorGoster()) {
            kazanan.setIcon(new ImageIcon("C:\\Users\\Enes\\Desktop\\proLab2\\build\\classes\\prolab2\\300x200oyuncuchadkazanan.png"));
            kazanan.setText("Kazanan KULLANICI !!!!!!!! ");
        } else if (bne.SkorGoster() < rakipOyuncu.SkorGoster()) {
            kazanan.setIcon(new ImageIcon("C:\\Users\\Enes\\Desktop\\proLab2\\build\\classes\\prolab2\\300x200monitorchadchadkazanan.png"));
            kazanan.setText("KAZANAN BİLGİSAYAR !!!!!!!! ");
        } else {
            kazanan.setText("BERABERLİK !!!!!!!! ");
        }
        alt.setLayout(new FlowLayout());
        alt.add(kazanan);
        alt.add(yeniden);
    }

    public static FileWriter yaz() throws IOException {
        FileWriter yazici = new FileWriter("log.txt");
        return yazici;
    }
}
