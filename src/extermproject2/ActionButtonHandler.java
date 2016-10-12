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
      
      private int i;//몇번째 버튼이 눌렸는지 확인하는 변수
      
      private cardButton[] btn;//cardButton메소드를 이용하기 위해 선언된 변수
      private int[] arr;//눌린 버튼 1,2의 num값을 저장하기 위해 선언된 버튼

      private int check;//0,1으로 변경이 되며 arr과 iArr값에 영향을 끼침.

      private int [] iArr;//i값을 저장하는 변수로 2개의 눌린 버튼위치를 저장하는 변수

      private int end=0;//게임 논리중 같은 이미지를 찾을 때 마다 1씩증가하며 모든 이미지를 맞추면 게임 종료를 알리기위해 만들어진 변수

      private GUI main;//팝업창과 Game변수를 사용하기 위해 만들어진 변수

      

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

         i=Integer.parseInt(e.getActionCommand());//몇번째 버튼이 눌렸는지 확인

         iArr[check]=i;//버튼의 순서를 저장. 첫번쨰 버튼이 눌리면 iArr[0]에저장, 두번째일 경우 iArr[1]에 저장

         arr[check]=btn[i].clickButton();//눌러진 버튼의 num값을 저장하고 이미지 변경(카드를 뒤집는다

         if(arr[check]==-1)//만약 같은 버튼을 눌렀을시 아무것도 실행하지 않고 메소드를 빠져나온다. check값 변동 없음.

            return;
         
         
          if(check==1)

         {

            if(arr[0]!=arr[1])//num값이 서로 다르면(이미지가 다르면) 카드를 다시 뒤집는다
            {
                     score -=1;
                     btn[iArr[0]].returnImage();
                     btn[iArr[1]].returnImage();
            }

            else //서로 같으면

            {

               end++; 
               score += 10; // 점수

               if(end==9)//게임 클리어 시

               {
                  main.memberDB.UpdateUser(score);
                  int result=main.showPapUp(score);//팝업창 계속할 것인지 물름

                  if(result==JOptionPane.YES_OPTION)

                  {

                     end=0;

                     main.ReturnGame().randNumber();//num값 섞음

                     for(int i=0;i<18;i++)

                     {

                        btn[i].ChangeImage(main.ReturnGame().GetImage(main.ReturnGame().GetArr(i))

                              ,main.ReturnGame().GetArr(i));

                        //이미지 교체&num값 교체

                        btn[i].returnImage();

                        //카드를 뒷면으로 변경

                     }

                  }

                  else

                     System.exit(0);//게임종료

               }

            }

            check=0;//check값을 초기화. check값은 0,1만 돼야 하기 때문에 다시 0으로 초기화해야 한다.

            return;

         }

         check++;//카드가 1장만 뒤집혔을 경우 체크값을 1증가한다.

      }
}