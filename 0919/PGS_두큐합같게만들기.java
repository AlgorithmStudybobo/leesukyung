package algo;

import java.util.ArrayDeque;
import java.util.Queue;

public class PGS_두큐합같게만들기 {
	public int solution(int[] queue1, int[] queue2) {
        //큐 두개에 담기
        Queue<Integer> q1 = new ArrayDeque<>();
        Queue<Integer> q2 = new ArrayDeque<>();
        //종료조건을 위한 큐의 길이 가져오기
        int len = queue1.length;
        //큐1,큐2의 합
        long sum1= 0, sum2 = 0;
        //배열에 저장된 값 큐에 저장하기 + 합 계산하기
        for(int i = 0; i<queue1.length;i++){
            q1.offer(queue1[i]);
            sum1+=queue1[i];
        }
        for(int i = 0; i<queue2.length;i++){
            q2.offer(queue2[i]);
            sum2+=queue2[i];
        }
        //합이 같으면 0리턴
        if(sum1==sum2)return 0;
        int cnt = 0;
        while(true){
            //횟수 카운트
            cnt++;
            //큐1합이 더 크면 큐1에서 값을 빼서 큐2에 넣기
            if(sum1> sum2){
                int num = q1.poll();
                sum1-=num;
                sum2+=num;
                q2.offer(num);
            }else{
                int num = q2.poll();
                sum2-=num;
                sum1+=num;
                q1.offer(num);
            }
            //두 큐 합이 같으면 횟수 리턴
            if(sum1==sum2)return cnt;
            //종료조건인데 정확히 모르겠음
            if(cnt>len*3)break;
        }
        //불가능
        return -1;
    }
}
