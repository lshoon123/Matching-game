package extermproject2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JPanel{
   private JTextField id,pwd;
   private JLabel lbMesaage;
   GUI gm = null;
   int count =0;
   
   JPanel panelPassword = null;
   private JPanel currentPanel;
   JButton btnPassword = null;
   
   public Login(GUI gm){
      this.gm = gm;
      setPreferredSize(new Dimension(600,400));
      setBackground(SystemColor.menu);
      setLayout(null);
   
      lbMesaage = addLabel("",160,250);
      lbMesaage.setSize(300,30);
      
      addLabel("ID",160,100);
      addLabel ("PWD",160,140);
      //레이블 추가
      
      id = addTextField(240,100);
      
      pwd = new JPasswordField();
      pwd.setSize(240,140);
      pwd.setBounds(240,140,190,30);
      add(pwd);
      
      id.addKeyListener(new KeyAdapter(){
         public void keyReleased(KeyEvent e){
            pressedKeyboard(e,"id");
         }
      });
      pwd.addKeyListener(new KeyAdapter(){
         public void keyReleased(KeyEvent e){
            pressedKeyboard(e,"pwd");
         }
      });
      // id,pwd 필드 추가
       JButton btnRegister = addButton("등록",160,190);
         MyActionListener1 btnRegister1 = new MyActionListener1();
         btnRegister.addActionListener(btnRegister1);
         
         JButton btnLogin = addButton("로그인",250,190);
         MyActionListener listener = new MyActionListener();
         btnLogin.addActionListener(listener);
         
         JButton btnClose = addButton("종료",340,190);
         MyActionListener2 end = new MyActionListener2();
         btnClose.addActionListener(end); 
   }
   
   class MyActionListener implements ActionListener 
      {
         public void actionPerformed(ActionEvent e)
         {
            login();
         }
      }
   
   class MyActionListener1 implements ActionListener 
      {
         public void actionPerformed(ActionEvent e)
         {
            gm.showRegister();
            gm.visible();
         }
      }
      
   class MyActionListener2 implements ActionListener 
      {
         public void actionPerformed(ActionEvent e)
         {
            gm.setVisible(false);
         }
      }
      
   private JTextField addTextField(int x,int y){
      JTextField tf = new JTextField();
      tf.setFont(new Font("Dialog",Font.PLAIN, 20));
      tf.setColumns(10);;
      tf.setBounds(x,y,190,30);
      
      add(tf);
      return tf;
   }
   
   
   private JLabel addLabel(String text,int x,int y){
      JLabel lb = new JLabel(text);
      lb.setFont(new Font("Dialog",Font.PLAIN, 20));
      lb.setBackground(SystemColor.activeCaption);
      lb.setBounds(x,y,60,30);
      add(lb);
      return lb;
   }
   
   private JButton addButton(String text,int x,int y){
      JButton bt = new JButton(text);
      bt.setBounds(x,y,85,30);
      add(bt);
      return bt;
   }
   
   public void login(){
      count++;
      
      if(count >=5){
         lbMesaage.setText("over 5 times");
         gm.setVisible(false);
      }
      else{
         if(id.getText().equals("")){
            lbMesaage.setText("id is empty");
         }
         else if(pwd.getText().equals("")){
            lbMesaage.setText("pwd is empty");
         }   
         else{
             boolean flag = this.gm.memberDB.searchID(id.getText(),"");
             if(flag==true){
                if(this.gm.memberDB.searchID(id.getText(), pwd.getText())){   
                     gm.Start();
                     gm.setVisible(false);
                  }
                  else{
                        lbMesaage.setText("password is failed. -> "+count+"/5");
                  }
             }
             else{
                lbMesaage.setText("ID is incorrect -> "+count+"/5");
             }
         }
      }
   }
   public void pressedKeyboard(KeyEvent e,String type){
      int keyCode = e.getKeyCode();
      if(keyCode==10){
         login();
      }
      else{
         if(type.equals("pwd")){
            if(e.isControlDown()){
               if(keyCode == 82){
                  //showPasswordNumber();
               }
            }
            else if(keyCode == 8){
               
            }
            else if(keyCode == 17){
            
            }
            else if(keyCode <48 || keyCode >105 || (keyCode >57 && keyCode <96)){
               JOptionPane.showMessageDialog(this, "insert only.","password error",JOptionPane.WARNING_MESSAGE);
               String current_value = pwd.getText();
               if(current_value.length()==-1){
                  pwd.requestFocus();
               }
               else{
                  pwd.setText(current_value.substring(0,current_value.length()-1));
               }
               pwd.requestFocus();
            }
         }
      }

   }
}