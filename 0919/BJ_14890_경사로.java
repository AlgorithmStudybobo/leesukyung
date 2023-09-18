package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_14890_경사로 {
	static int dr[] = {1, 0, -1, 0};
	static int dc[] = {0, 1, 0, -1};
	static int n, l, map[][], ans;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		//경사로길이
		l = Integer.parseInt(st.nextToken());
		//지도 입력받기
		map = new int[n][n];
		for(int i = 0; i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<n;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//길 세기
		count();
		//정답 출력
		System.out.println(ans);
	}
	// 길세기
	static void count() {
		for(int i = 0; i<n;i++) {
			//가로
			if(move(i,0,1)==true)ans++;
			//세로
			if(move(0,i,0)==true)ans++;
		}
	}
	//가보면서 세기
	static boolean move(int r, int c, int dir) {
		//경사로 놓은 곳 확인
		boolean[] puts = new boolean[n];
		
		for(int i = 1; i<n;i++) {
			int nr = r + dr[dir];
			int nc = c + dc[dir];
			if(map[nr][nc]==map[r][c]) {
				//현재위치를 다음위치로 갱신
				r=nr;
				c=nc;
			//다음위치가 현재위치보다 1높이 높을 때
			}else if(map[nr][nc]==map[r][c]+1) {
				//현재위치부터 시작해서 0부터 l-1개까지 높이 같은지 검사
				for(int j =0;j<l;j++) {
					int br = r + j*dr[dir+2];
					int bc = c + j*dc[dir+2];
					//맵 범위 초과하거나 경사로 설치할 구간이 평평하지 않으면 불가능
					if(br<0 || br>=n || bc<0 || bc>=n || map[br][bc]!=map[r][c])return false;
					//가로세로 방향에 따른 경사로 위치 표시 -> 이미 경사로가 있으면 불가능
					if(dir==0) {
						if(puts[br]==true)return false;
						else puts[br]=true;
					}else {
						if(puts[bc]==true)return false;
						else puts[bc]=true;
					}
				}
				r=nr;
				c=nc;
			//다음위치가 현재위치보다 1높이 낮을 때
			}else if(map[nr][nc]==map[r][c]-1) {
				//다음위치부터 시작해야돼서 1부터시작
				for(int j =1;j<=l;j++) {
					int br = r + j*dr[dir];
					int bc = c + j*dc[dir];
					//맵 범위 초과하거나 경사로 설치할 구간이 평평하지 않으면 불가능
					if(br<0 || br>=n || bc<0 || bc>=n || map[br][bc]!=map[nr][nc])return false;
					//가로세로 방향에 따른 경사로 위치 표시 -> 이미 경사로가 있으면 불가능
					if(dir==0) {
						if(puts[br]==true)return false;
						else puts[br]=true;
					}else {
						if(puts[bc]==true)return false;
						else puts[bc]=true;
					}
				}
				r=nr;
				c=nc;
			//높이가 같지않거나 2이상 차이날때 불가능
			}else {
				return false;
			}
		}
		return true;
	}
}
