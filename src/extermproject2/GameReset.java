package extermproject2;

import java.util.Random;
import javax.swing.ImageIcon;

   public class GameReset {

      private ImageIcon[] image;
      private int [] arr;

      //���� Ŭ������ ����� Ŭ����
      public GameReset() {
         image=new ImageIcon[10];
         //���۽� ī�� ¦ ���� +�������� ���
         arr=new int[18];
      }

      public void imageFunction() {
         image[0]=new ImageIcon("images/01.jpg");
         image[1]=new ImageIcon("images/02.jpg");
         image[2]=new ImageIcon("images/03.jpg");
         image[3]=new ImageIcon("images/04.jpg");
         image[4]=new ImageIcon("images/05.jpg");
         image[5]=new ImageIcon("images/06.jpg");
         image[6]=new ImageIcon("images/07.jpg");
         image[7]=new ImageIcon("images/08.jpg");
         image[8]=new ImageIcon("images/09.jpg");
         image[9]=new ImageIcon("images/10.jpg");
      }

      public void randNumber()
      {
         int check=0;

         Random rand=new Random();

         for(int i=0;i<arr.length;i++)
         {
            arr[i]=rand.nextInt(9)+1;
            for(int x=0;x<i;x++)
            {
               if(arr[i]==arr[x])
                  check++;
            }
            if(check==2)
            {
               check=0;
               i--;
               continue;
            }
            check=0;
         }
      }

      public int GetArr(int i) {
         return arr[i];
      }

      public ImageIcon GetImage(int i) {
         return image[i];
      }

   }