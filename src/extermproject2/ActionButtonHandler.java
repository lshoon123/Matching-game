package extermproject2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import java.util.Timer; 
import java.util.TimerTask;
import java.util.concurrent.TimeUnit; 

public class ActionButtonHandler implements ActionListener {
      Timer timer = new Timer(); 
      Timer timer_count = new Timer();
      GUI gm = null;
      
      int score =0;
      
      private int i;//���° ��ư�� ���ȴ��� Ȯ���ϴ� ����
      
      private cardButton[] btn;//cardButton�޼ҵ带 �̿��ϱ� ���� ����� ����
      private int[] arr;//���� ��ư 1,2�� num���� �����ϱ� ���� ����� ��ư

      private int check;//0,1���� ������ �Ǹ� arr�� iArr���� ������ ��ħ.

      private int [] iArr;//i���� �����ϴ� ������ 2���� ���� ��ư��ġ�� �����ϴ� ����

      private int end=0;//���� ���� ���� �̹����� ã�� �� ���� 1�������ϸ� ��� �̹����� ���߸� ���� ���Ḧ �˸������� ������� ����

      private GUI main;//�˾�â�� Game������ ����ϱ� ���� ������� ����

      

      public ActionButtonHandler(cardButton[] button,GUI main) {

         // TODO Auto-generated constructor stub

         btn=button;

         arr=new int[2];

         check=0;

         iArr=new int[2];

         this.main=main;

      }

      

      @Override

      public void actionPerformed(ActionEvent e) {

         // TODO Auto-generated method stub

         i=Integer.parseInt(e.getActionCommand());//���° ��ư�� ���ȴ��� Ȯ��

         iArr[check]=i;//��ư�� ������ ����. ù���� ��ư�� ������ iArr[0]������, �ι�°�� ��� iArr[1]�� ����

         arr[check]=btn[i].clickButton();//������ ��ư�� num���� �����ϰ� �̹��� ����(ī�带 �����´�

         if(arr[check]==-1)//���� ���� ��ư�� �������� �ƹ��͵� �������� �ʰ� �޼ҵ带 �������´�. check�� ���� ����.

            return;
         
         
          if(check==1)

         {

            if(arr[0]!=arr[1])//num���� ���� �ٸ���(�̹����� �ٸ���) ī�带 �ٽ� �����´�
            {
                     score -=1;
                     btn[iArr[0]].returnImage();
                     btn[iArr[1]].returnImage();
            }

            else //���� ������

            {

               end++; 
               score += 10; // ����

               if(end==9)//���� Ŭ���� ��

               {
                  main.memberDB.UpdateUser(score);
                  int result=main.showPapUp(score);//�˾�â ����� ������ ����

                  if(result==JOptionPane.YES_OPTION)

                  {

                     end=0;

                     main.ReturnGame().randNumber();//num�� ����

                     for(int i=0;i<18;i++)

                     {

                        btn[i].ChangeImage(main.ReturnGame().GetImage(main.ReturnGame().GetArr(i))

                              ,main.ReturnGame().GetArr(i));

                        //�̹��� ��ü&num�� ��ü

                        btn[i].returnImage();

                        //ī�带 �޸����� ����

                     }

                  }

                  else

                     System.exit(0);//��������

               }

            }

            check=0;//check���� �ʱ�ȭ. check���� 0,1�� �ž� �ϱ� ������ �ٽ� 0���� �ʱ�ȭ�ؾ� �Ѵ�.

            return;

         }

         check++;//ī�尡 1�常 �������� ��� üũ���� 1�����Ѵ�.

      }
}