/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prolab3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Huseyin
 */
public class gui {
    JFrame frame;
    public static int a =0;
    public gui(){
        
    }
    public void frameac(){
        this.frame=new JFrame();
        frame.setLayout(new GridBagLayout());
        frame.setVisible(true);
        frame.setSize(1000,1000);
        frame.setAlwaysOnTop(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        c.gridx=0;
        c.gridy=0;
        c.gridwidth=10;
        c.ipady=30;
        c.ipadx=100;
        c.insets=new Insets(30,3,30,0);
        
    }
    public void framekapa(){        frame.setVisible(false);
    }
    GridBagConstraints c = new GridBagConstraints();
  
    public void agacolustur(Kisi ailebasi){
        JLabel yazi;
        if(ailebasi.getKizlikSoyadi()!=null){
            yazi = new JLabel(ailebasi.getAd()+" "+ailebasi.getKizlikSoyadi()+" "+ailebasi.getSoyad());            
        }
        else{
            yazi = new JLabel(ailebasi.getAd()+" "+ailebasi.getSoyad());
        }
        JPanel gnlpnl = new JPanel();
        gnlpnl.setLayout(new FlowLayout());
        JPanel pnl = new JPanel();
        pnl.add(yazi);       
        gnlpnl.add(pnl);


        if(ailebasi.getEsad()!=null && !ailebasi.getEsad().isBlank()){
            JPanel espnl = new JPanel();
            JLabel esyazi;
            if(ailebasi.getEs()!=null)
                esyazi = new JLabel(ailebasi.getEs().getAd()+" "+ailebasi.getEs().getKizlikSoyadi()+" "+ailebasi.getEs().getSoyad());            
            else
                esyazi = new JLabel(ailebasi.getEsad()+" "+ailebasi.getEssoyad());
            
            espnl.add(esyazi);
  
            gnlpnl.add(espnl);

            if(ailebasi.getCinsiyet().equalsIgnoreCase("Erkek")){
                espnl.setBackground(Color.pink);
            }
            else{
                espnl.setBackground(Color.cyan);
            }
        }
        frame.add(gnlpnl,c);
                c.gridwidth=2;
                
        pnl.setBounds(0, 0, 300, 300);
        if(ailebasi.getCinsiyet().equalsIgnoreCase("Erkek")){
            pnl.setBackground(Color.cyan);
        }
        else{
            pnl.setBackground(Color.pink);
        }
        if(ailebasi.getCocuk()!=null){

            
            c.gridx=c.gridx-c.gridy;
            c.gridx+=a;
            a++;
            c.gridy++;
            agacolustur(ailebasi.getCocuk());
            
            c.gridx=c.gridx+c.gridy;
            c.gridy--;
        }
        a=1;
        if(ailebasi.getKardes()!=null){
            
            c.gridx=c.gridx+c.gridy;
            c.gridx+=a;
            a++;
            agacolustur(ailebasi.getKardes());
            c.gridx=c.gridx-c.gridy;
            
        }
        
    }
}