package extermproject2;

import java.awt.*;
import java.awt.event.*;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.event.*;
import extermproject2.DataBase;

public class MemberDB {
   DataBase db;
   
   String x;
   
   public MemberDB(DataBase db){
      this.db =db;
   }
   
   public boolean searchID(String id,String pwd){
      boolean result = false;
   
      x = new String(id.getBytes());

      try{
         String sql = "";
         if(pwd.equals("")){
            sql = "select * from finalexam where loginID='"+
                  new String(id.getBytes(), "ISO-8859-1") + "'";
         }
         else{
            sql = "select * from finalexam where loginID='"+
                  new String(id.getBytes(),"ISO-8859-1") +"' and loginPWD='"+
                  new String(pwd.getBytes(),"ISO-8859-1") +"'";
         }
         ResultSet searchResult = db.stmt.executeQuery(sql);
         if(searchResult.next()){
            ResultSetMetaData meta = searchResult.getMetaData();
            if(meta.getColumnCount() > 0){
               result = true;
            }
            else{
               System.out.println("검색된 결과가 없습니다.");
            }
         }
         else{
            System.out.println("검색된 결과가 없습니다.");
         }
      } 
      catch (UnsupportedEncodingException e){
         e.printStackTrace();
      } 
      catch (SQLException e){
         e.printStackTrace();
      }
      return result;
   }
   
   public void insertUser(String id,String pwd,String name,String age,int score){
      try{
         db.stmt.executeUpdate("insert into finalexam (loginID, loginPWD, name, age,score) values('"+
               new String(id.getBytes(), "ISO-8859-1") + "', '" +
               new String(pwd.getBytes(), "ISO-8859-1") + "', '" +
               new String(name.getBytes(), "ISO-8859-1") + "', '" +age+ "', '" +0+"');");
      }
      
      catch (UnsupportedEncodingException e){
         e.printStackTrace();
      }
      catch (SQLException e){
         e.printStackTrace();
      }
   }
   
   public void UpdateUser(int score){
      try{
         db.stmt.executeUpdate("update finalexam set score = score +'"+score+ "' where loginID= '"+this.x+"';");
      }
      
      catch (SQLException e){
         e.printStackTrace();
      }
   }
}