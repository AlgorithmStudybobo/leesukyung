package algo;

import java.util.Scanner;

public class BJ_1965_상자넣기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		//최소는 1 
		int max = 1;
		int[] size = new int[n+1];
		int[] dp = new int[n+1];
		for(int i =1;i<=n;i++) {
			//상자 넣을 수 없을 때 자신만 들어갈 수 있는 경우 개수 1
			dp[i]=1;
			size[i]=sc.nextInt();
            if(i==1)continue;
            //앞부터 비교하면서 비교대상이 지금 사이즈보다 작을 때 비교대상까지 최대로 넣을 수 잇는 상자 개수 +1 갱신
            for(int j = 1;j<i;j++) {
				if(size[j]<size[i]) {
					dp[i]=Math.max(dp[i], dp[j]+1);
					max = Math.max(max, dp[i]);
				}
			}
		}
		System.out.println(max);
	}
}
