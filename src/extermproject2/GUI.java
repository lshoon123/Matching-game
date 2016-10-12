package extermproject2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import extermproject2.DataBase;
import extermproject2.MemberDB;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GUI  extends JFrame {
   int width = 650;
   int height = 450;
   JLabel msg = null;
   Container contentpane = this.getContentPane();
   JPanel centerPanel = null;
   
   private GameReset Game;
   //ī�弯��,�̹��� ���� �ҷ�����,GUI ���� 
   
   DataBase db = new DataBase();
   MemberDB memberDB;
   private JTextField textField;

   public GUI() {
      setSize(width,height); // Ÿ��Ʋ ȭ�� ������
      setResizable(false);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
      
      Game=new GameReset();
      Game.randNumber();
      Game.imageFunction();
      
      if(db.connectionDB()){
         db.makeUserTable();
         memberDB = new MemberDB(db);
         setTitle ("welcome To Game !!!");
         setLocation();
         showLogin();
      }
      
      else{
         setTitle ("DB connection error !!!");
         setError();
      }
      setVisible(true);
   }

   public void setError(){
      getContentPane().add(new JLabel("DB error"),BorderLayout.CENTER);
   }
   
   void setLocation(){
      Toolkit tk = Toolkit.getDefaultToolkit();
      //���� �ü�� ���� ������ ��
      Dimension dem = tk.getScreenSize();
      //���� ����
      Dimension dem_frame = this.getSize();
      //�������� ����
      int xx = (int)(dem.getWidth() / 2 - dem_frame.getWidth() / 2);
      int yy = (int)(dem.getHeight() / 2 - dem_frame.getHeight() / 2);
      this.setLocation(xx,yy);
      // ȭ�� ���߾� ��ġ ��Ű��
   }
   
   public void showLogin()
   {
      if(centerPanel != null)
      {
         contentpane.remove(centerPanel);
      }
      centerPanel = new Login(this);
      contentpane.add(centerPanel, BorderLayout.CENTER);
   }
   
   public void showRegister(){
      if(centerPanel != null){
          contentpane.remove(centerPanel);
      }
      centerPanel = new Register(this,null);
      contentpane.add(centerPanel, BorderLayout.CENTER);
   }
   
   public void visible(){
      setVisible(true); // ������(ȭ��)�������� ����
   }
   
   public void Start() {
      
      JFrame frm = new JFrame("ī�� �� ���߱�");

      frm.setBounds(550, 200, 1060, 770);
        // ũ�� ���߱�

      JPanel bp=new JPanel();

      bp.setLayout(new GridLayout(3,6,0,0));

      cardButton[] btn=new cardButton[18];

      ActionButtonHandler action = new ActionButtonHandler(btn,this);

      for(int i=0;i<18;i++)

      {

         btn[i]=new cardButton(Integer.toString(i),Game.GetArr(i),Game.GetImage(Game.GetArr(i)),Game.GetImage(0));

         btn[i].addActionListener(action);

         bp.add(btn[i]);

      }

      

      

      frm.getContentPane().add(bp);
      frm.setVisible(true);
      frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      

   }

   public int showPapUp(int score) {

      int result;

      result=JOptionPane.showConfirmDialog(null,

            "����Ͻðڽ��ϱ�? \n"+ "    ���� : "+score+"��","�����մϴ�.",JOptionPane.YES_NO_OPTION);

      return result;

   }

   public GameReset ReturnGame() {

      return Game;

   }
   
}
