
package prolab2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.*;


/**
 *
 * @author Enes
 */
public class secim extends JFrame{

    public secim() {
    }
    
    public void secimUI(Bilgisayar pc,Kullanici bne,Bilgisayar pc1)
    {
        bne.GetOyuncuNL().clear();
        JFrame f = new JFrame("secim");
        ImageIcon img = new ImageIcon("prolab2/dogma.jpg");
        f.setIconImage(img.getImage());
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JPanel p4 = new JPanel();
        JLabel l1 = new JLabel();
        JLabel l2 = new JLabel();
        JLabel l3 = new JLabel();
        JLabel l4 = new JLabel();
        JButton btn_oyuncuvspc = new JButton("Oyuncu vs Bilgisayar");
        JButton btn_pcvspc = new JButton("Bilgisayar vs Bilgisayar");
        ImageIcon sagchad = new ImageIcon(this.getClass().getResource("300x200sagmonitorchad.png"));
        ImageIcon solchad = new ImageIcon(this.getClass().getResource("300x200monitorchad.png"));
        ImageIcon chad = new ImageIcon(this.getClass().getResource("300x200oyuncuchad.png"));
 
        l1.setIcon(chad);
        l2.setIcon(sagchad);
        l3.setIcon(solchad);
        l4.setIcon(solchad);
        
        p1.setBounds(20, 20,300 , 200);
        p2.setBounds(20, 540,300 , 200);
        p3.setBounds(660, 20,300 , 200);
        p4.setBounds(660, 540,300 , 200);
        
        btn_oyuncuvspc.setBounds(350, 70, 300, 100);
        btn_pcvspc.setBounds(340, 600, 315, 100);
        
        btn_pcvspc.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
//                Bilgisayar pc1 = new Bilgisayar(3, "Linux", 0);
//                Bilgisayar pc = new Bilgisayar(2, "Windows", 0);
                JFrame frame = new JFrame();
                Oyun oyun = new Oyun();
                oyun.bilgisayarbilgisayarakarsi(frame, pc, pc1,bne);
                f.dispose();
            }
        });
        
        btn_oyuncuvspc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                Bilgisayar pc = new Bilgisayar(2, "Windows", 0);
//                Kullanici bne = new Kullanici(1, "Hüseyin", 0);
                Oyun sa = new Oyun();
                sa.nesnesecmegui(pc,bne,pc1);
                
                f.dispose();
            }
        });
        
        
        
        
        p1.add(l1);
        p2.add(l2);
        p3.add(l3);
        p4.add(l4);
        p1.setVisible(true);
        p2.setVisible(true);
        p3.setVisible(true);
        p4.setVisible(true);
        
        btn_oyuncuvspc.setVisible(true);
        btn_pcvspc.setVisible(true);
        
        f.add(p1);
        f.add(p2);
        f.add(p3);
        f.add(p4);
        f.add(btn_oyuncuvspc);
        f.add(btn_pcvspc);
        f.setLayout(null);
        f.setSize(1000, 800);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    
    
    
}
