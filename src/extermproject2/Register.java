package extermproject2;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import extermproject2.GUI;
import extermproject2.MemberDB;

public class Register extends JPanel{
   
   private JTextField name;
   private JTextField id;
   private JTextField pwd;
   private JTextField age;
   private JButton btnCheckId;
   private JButton btnSave;
   private JButton btnCancel;
   private GUI gm =null;
   private JLabel lblNewName;
   private JLabel lblId;
   private JLabel lblPwd;
   private JLabel lblAge;
   private JLabel lbMessage;
   private boolean checkID = false;
   int score=0;
   
   public Register(GUI gm,MemberDB memberDB){
      this.gm = gm;
      setBackground(SystemColor.activeCaption);
      setLayout(null);
      
      name = new JTextField();
      name.setBounds(227, 113, 255, 21);
      add(name);
      name.setColumns(10);
      
      lblNewName = new JLabel("name");
      lblNewName.setFont(new Font("±¼¸²Ã¼", Font.BOLD, 15));
      lblNewName.setBounds(136, 113, 77, 21);
      add(lblNewName);
      
      id = new JTextField();
      id.setColumns(10);
      id.setBounds(227, 144, 143, 21);
      add(id);
      
      lblId = new JLabel("ID");
      lblId.setFont(new Font("±¼¸²", Font.BOLD, 15));
      lblId.setBounds(136, 144, 77, 21);
      add(lblId);
      
      pwd = new JTextField();
      pwd.setBounds(227, 177, 255, 24);
      pwd.setColumns(10);
      add(pwd);
      
      lblPwd = new JLabel("pwd");
      lblPwd.setFont(new Font("±¼¸²", Font.BOLD, 15));
      lblPwd.setBounds(136, 180, 62, 18);
      add(lblPwd);
      
      age = new JTextField();
      age.setBounds(227, 213, 255, 24);
      add(age);
      age.setColumns(10);
      
      lblAge = new JLabel("age");
      lblAge.setFont(new Font("±¼¸²", Font.BOLD, 15));
      lblAge.setBounds(136, 216, 62, 18);
      add(lblAge);
      
      btnCheckId = new JButton("check id");
      btnCheckId.setBounds(381, 142, 101, 27);
      add(btnCheckId);
      btnCheckId.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            checkID();
         }
      });
      
      btnSave = new JButton("Save");
      btnSave.setBounds(227, 266, 115, 55);
      add(btnSave);
      btnSave.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            insertUser();
            gm.showLogin();
            gm.visible();
         }
      });
      
      btnCancel = new JButton("Cancel");
      btnCancel.setBounds(367, 266, 115, 55);
      add(btnCancel);
      btnCancel.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            gm.showLogin();
            gm.visible();
         }
      });
      
      lbMessage = new JLabel("");
      lbMessage.setBounds(65, 296, 115, 50);
      add(lbMessage);
   }
   
   public void checkID(){
      if(id.getText().equals("")){
         lbMessage.setText("ID is empty.");
      }
      else{
         if(this.gm.memberDB.searchID(id.getText(),"")){
            lbMessage.setText("ID is existed");
         }
         else{
            lbMessage.setText("ID is ok");
            checkID = true;
         }
      }
   }
   
   public void insertUser(){
      if(name.getText().equals("")){
         lbMessage.setText("name is empty");
      }
      else if(id.getText().equals("")){
         lbMessage.setText("id is empty");
      }
      else if(pwd.getText().equals("")){
         lbMessage.setText("pwd is empty");
      }
      else if(age.getText().equals("")){
         lbMessage.setText("age is empty");
      }
      else{
      if(checkID){
         gm.memberDB.insertUser(id.getText(), pwd.getText(), name.getText(), age.getText(),score);
         gm.showLogin();
         gm.visible();
      }
      else{
         lbMessage.setText("please check id.");
      }
   }
   }
}