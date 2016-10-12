package extermproject2;

   import java.sql.Connection;
   import java.sql.DriverManager;
   import java.sql.SQLException;
   import java.sql.Statement;

   public class DataBase {
      public Connection conn;
      public Statement stmt;
      public boolean connectionDB(){
         boolean result = true;
         try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/finalexam","root","mysql");
            System.out.println("DB.connection is OK !!!");
            stmt = conn.createStatement();
            System.out.println("Statement is generated !!!");
         } catch(ClassNotFoundException e){
            System.out.println("JDBC driver error");
            result = false;
         } catch (SQLException e){
            System.out.println("DB connection is Failed");
            result = false;
         }
         return result;
      }
      
      public void makeUserTable(){
         String table_name = "finalexam";
         String query = "create table if not exists "+table_name+" ("+
                     // not exists 뒤에 공백이 있어야 문법오류가 안나서 query가 정상 실행된다.
                     "lno int(10) primary key auto_increment, "+
                     "loginID varchar(20) not null, " +
                     "loginPWD varchar(20) not null, " +
                     "name varchar(10) not null, " +
                     "age int(3) not null," +
                     "score int(3) not null)";
         try{
            stmt.executeUpdate(query);
         }
         catch (SQLException e){
            e.printStackTrace();
         }
      }
   }