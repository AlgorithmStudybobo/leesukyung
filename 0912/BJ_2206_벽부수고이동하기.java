package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2206_벽부수고이동하기 {
	//좌표 클래스
	static class Pos {
		int r, c, k;

		public Pos(int r, int c, int k) {
			this.r = r;
			this.c = c;
			this.k = k;
		}

	}

	static int n, m, visited[][][];
	static char map[][];
	static int dr[] = { -1, 0, 1, 0 }, dc[] = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		//입력받아오기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new char[n][];
		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
		}
		visited = new int[n][m][2];

		//답 찾아서 출력
		System.out.println(getAns());
	}

	//답찾기 시작
	private static int getAns() {
		//bfs로 최단거리찾기
		Queue<Pos> q = new ArrayDeque<>();
		visited[0][0][0] = 1;
		q.offer(new Pos(0, 0, 0));
		while (!q.isEmpty()) {
			Pos cur = q.poll();
			//네방향탐색
			for (int dir = 0; dir < 4; dir++) {
				int nr = cur.r + dr[dir];
				int nc = cur.c + dc[dir];
				//범위 초과 처리
				if (nr < 0 || nr >= n || nc < 0 || nc >= m )
					continue;
				//다음좌표가 이동가능하고 방문하지 않았을 때 방문처리하고 큐에 삽입
				if(map[nr][nc]=='0' && visited[nr][nc][cur.k]==0) {
					visited[nr][nc][cur.k]=visited[cur.r][cur.c][cur.k]+1;
					q.offer(new Pos(nr,nc,cur.k));
				}
				//방문할 수 없는 경우이고, 벽을 넘어갈 수 있는 상태이고 벽을 넘어간 곳을 방문하지 않았을 때 방문처리하고 삽입
				if(map[nr][nc]=='1' && cur.k==0 && visited[nr][nc][1]==0) {
					visited[nr][nc][1] = visited[cur.r][cur.c][cur.k]+1;
					q.offer(new Pos(nr,nc,1));
				}
			}
		}
		//정답 반환
		int ans = -1;
		//벽통과 안했을 때 도착했는지 확인 
		if(visited[n-1][m-1][0]!=0)ans=visited[n-1][m-1][0];
		//벽통과했을 때 도착했는지 확인
		if(visited[n-1][m-1][1]!=0) {
			//ans가 -1이면 벽 통과 안했을 때방문 못했음 벽통과했을 때가 답
			if(ans==-1)ans=visited[n-1][m-1][1];
			//아니면 최솟값으로 갱신
			else
				ans= Math.min(ans, visited[n-1][m-1][1]);
		}
		//리턴
		return ans;
	}
}
