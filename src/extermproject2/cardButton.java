package extermproject2;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class cardButton extends JButton {

   private int num;//���� ���� �̹������� Ȯ���ϴ� ����.

   ImageIcon image1,image2;

   //image1: ��ư�� �������� ���Ǵ� �̹���, image2: ��ư�� ������ �ʾ����� ���Ǵ� �̹���

   private boolean check;//��ư�� ������ Ŭ���ƴ��� Ȯ���ϴ� ����

   class thread1 extends Thread
   {
	   @Override
	   public void run()
	   {
		   setIcon(image1);
		   try {
			Thread.sleep(111);
		} catch (InterruptedException e) {
			// TODO �ڵ� ������ catch ���
			e.printStackTrace();
		}
		   setIcon(image2);
		      check=true;
	   }
   }

   public cardButton(String str,int n,ImageIcon image,ImageIcon back) {

      // TODO Auto-generated constructor stub

      super(str,back);//e.getMassage()�޼ҵ带 �̿��ϱ� ���� String�� ����.

      image1=image;

      image2=back;

      num=n;

      check=true;

   }

   //��ư�� Ŭ���ɽ� �̹��� ������ �ߺ�Ŭ���� �������� check�� false�� ��ȯ,�׸��� num���� ��ȯ. ����, ���� ��ư�� 2�� ������ -1 ��ȯ

   public int clickButton() {

      if(check)
      {

         setIcon(image1);

         check=false;

         return num;

      }

      return -1;

   }

   //�̹����� �̸����� �����ϴ� �޼ҵ�
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
		// TODO �ڵ� ������ catch ���
		e.printStackTrace();
	}
	  setIcon(image2);
      check=true; 

   }*/

   //image1�� �����ϰ� num���� ����

   public void ChangeImage(ImageIcon image,int n) {

      image1=image;

      num=n;

   }

}