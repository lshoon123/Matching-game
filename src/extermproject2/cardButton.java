package extermproject2;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class cardButton extends JButton {

   private int num;//서로 같은 이미지인지 확인하는 변수.

   ImageIcon image1,image2;

   //image1: 버튼이 눌렸을시 사용되는 이미지, image2: 버튼이 눌리지 않았을시 사용되는 이미지

   private boolean check;//버튼이 여러번 클릭됐는지 확인하는 변수

   class thread1 extends Thread
   {
	   @Override
	   public void run()
	   {
		   setIcon(image1);
		   try {
			Thread.sleep(111);
		} catch (InterruptedException e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		}
		   setIcon(image2);
		      check=true;
	   }
   }

   public cardButton(String str,int n,ImageIcon image,ImageIcon back) {

      // TODO Auto-generated constructor stub

      super(str,back);//e.getMassage()메소드를 이용하기 위해 String을 받음.

      image1=image;

      image2=back;

      num=n;

      check=true;

   }

   //버튼이 클릭될시 이미지 변경후 중복클릭을 막기위해 check를 false로 변환,그리고 num값을 반환. 만약, 같은 버튼이 2번 눌릴시 -1 반환

   public int clickButton() {

      if(check)
      {

         setIcon(image1);

         check=false;

         return num;

      }

      return -1;

   }

   //이미지를 됫면으로 변경하는 메소드
   public void  returnImage() 
   {
	 Thread thread2 = new thread1();
	 thread2.start();
   }
   
   /*public void  returnImage() {
	   setIcon(image1);
	   try {
		Thread.sleep(100);
	} catch (InterruptedException e) {
		// TODO 자동 생성된 catch 블록
		e.printStackTrace();
	}
	  setIcon(image2);
      check=true; 

   }*/

   //image1를 변경하고 num값을 변경

   public void ChangeImage(ImageIcon image,int n) {

      image1=image;

      num=n;

   }

}