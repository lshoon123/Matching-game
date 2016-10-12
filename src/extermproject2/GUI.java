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
   //카드섞기,이미지 파일 불러오기,GUI 시작 
   
   DataBase db = new DataBase();
   MemberDB memberDB;
   private JTextField textField;

   public GUI() {
      setSize(width,height); // 타이틀 화면 사이즈
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
      //현재 운영체제 정보 얻어오는 법
      Dimension dem = tk.getScreenSize();
      //폭과 높이
      Dimension dem_frame = this.getSize();
      //프레임의 높이
      int xx = (int)(dem.getWidth() / 2 - dem_frame.getWidth() / 2);
      int yy = (int)(dem.getHeight() / 2 - dem_frame.getHeight() / 2);
      this.setLocation(xx,yy);
      // 화면 정중앙 위치 시키기
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
      setVisible(true); // 프레임(화면)보여줄지 지정
   }
   
   public void Start() {
      
      JFrame frm = new JFrame("카드 패 맞추기");

      frm.setBounds(550, 200, 1060, 770);
        // 크기 맞추기

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

            "계속하시겠습니까? \n"+ "    점수 : "+score+"점","축하합니다.",JOptionPane.YES_NO_OPTION);

      return result;

   }

   public GameReset ReturnGame() {

      return Game;

   }
   
}
