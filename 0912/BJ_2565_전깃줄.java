package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_2565_전깃줄 {
	//전기줄 클래스
	static class Line implements Comparable<Line>{
		int from, to;
		//생성자
		public Line(int from, int to) {
			super();
			this.from = from;
			this.to = to;
		}
		//정렬을 위함
		@Override
		public int compareTo(Line o) {
			return this.from-o.from;
		}
		
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		//전기줄 배열
		Line line[] = new Line[n];
		//디피배열
		int[] dp = new int[n];
		//전기줄 담기
		for(int i = 0; i<n;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			line[i]=new Line(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		}
		//정렬하기
		Arrays.sort(line);
//		for(int i = 0; i<n;i++) {
//			System.out.println(line[i].from+" "+line[i].to);
//		}
		//전기줄 하나일 때 최대값 1개
		dp[0]=1;
		//최대로 연결될 수 있는 개수
		int max = 0;
		for(int i = 1;i<n;i++) {
			dp[i]=1;
			//이전 전기줄의 to가 현재 전기줄 to보다 작으면 겹치지 않음
			for(int j = 0;j<i;j++) {
				if(line[j].to<line[i].to) {
					dp[i]=Math.max(dp[i], dp[j]+1);
					if(dp[i]>max)max=dp[i];
				}
			}
		}
		//출력
		System.out.println(n-max);
	}
}
