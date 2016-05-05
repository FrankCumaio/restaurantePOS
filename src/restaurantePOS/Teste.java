/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurantePOS;

/**
 *
 * @author Frank Cumaio
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Jasse
*/

public class Teste extends JPanel implements ActionListener{
   private JButton bt; 
   public Teste(){
       this.setPreferredSize(new Dimension(300, 300));
       criaGUI();
   } 
   
   private void criaGUI(){
      
      for(int i=0;i<8;i++){
          bt=new JButton("Produto "+i);
          bt.setName("bt"+i);
          this.add(bt);
          bt.addActionListener(this);
      } 
   }
   
   public static void main(String []args){
    //Teste fig=;
    JFrame fr=new JFrame("Teste de Interface"); 
    fr.add(new Teste());
    fr.setSize(500, 300);   
    fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    fr.pack();
    fr.setVisible(true);
   } 

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton alvo=(JButton)e.getSource(); 
        JOptionPane.showMessageDialog(null, alvo.getText()+ " Com nome: "+ alvo.getName());
    }
}