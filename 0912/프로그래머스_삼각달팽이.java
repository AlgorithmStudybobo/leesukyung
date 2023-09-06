package algo;

import java.util.Arrays;

public class 프로그래머스_삼각달팽이{
	//이동가능한 방향 하, 우, 좌상
	static int[] dr= {1,0,-1};
	static int[] dc= {0,1,-1};
	class Solution {
	    public int[] solution(int n) {
	    	//달팽이 범위 계산
	        int end = 0;
	        for(int i=1;i<=n;i++)end+=i;
	        //답 담는 배열
	        int[] answer = new int[end];
	        //달팽이 숫자 채우는 배열
	        int[][] map = new int[n][n];
	        //시작 좌표, 시작숫자, 시작 방향
			int r =0, c= 0, a= 1, dir = 0;
			//첫번째 자리에 첫번째 숫자 채우기
			map[r][c]=a++;
			//반복진입
			while(a<=end) {
				//다음 좌표에 다음 방향에 관한 값을 더해줌
				int nr = r+dr[dir];
				int nc = c+dc[dir];
				//맵 범위를 벗어나거나 이미 숫자가 채워져있으면 방향 바꿔서 다시 시도
				if(nr<0||nr>=n||nc<0||nc>=n || map[nr][nc]!=0) {
					dir=(dir+1)%3;continue;
				}
				//통과했으면 채우고 지금 좌표를 다음좌표로 갱신
				map[nr][nc]=a++;
				r=nr;
				c=nc;
			}
			//정답 배열 처음부터 채우기
	        int idx = 0;
	        for(int i = 0; i<n;i++){
	            for(int j = 0; j<n;j++){
	            	//삼각형 모양이라  map 배열의 값이 0이면 다음 행으로 이동
	                if(map[i][j]!=0){
	                    answer[idx++]=map[i][j];
	                }
	            }
	        }
	        //정답 리턴
	        return answer;
	    }
	}
	public static void main(String[] args) {
		
	}
}
