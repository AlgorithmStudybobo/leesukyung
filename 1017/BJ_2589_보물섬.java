import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2589_보물섬 {
	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	}

	static int dr[] = { -1, 0, 1, 0 }, dc[] = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		char[][] map = new char[n][];
		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
		}
		int ans = 0;
		Queue<Pos> q = new ArrayDeque<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 'L') {
					int[][] visited = new int[n][m];
					visited[i][j] = 1;
					q.offer(new Pos(i, j));
					while (!q.isEmpty()) {
						Pos cur = q.poll();
						for (int dir = 0; dir < 4; dir++) {
							int nr = cur.r + dr[dir];
							int nc = cur.c + dc[dir];
							if (nr < 0 || nr >= n || nc < 0 || nc >= m || visited[nr][nc] != 0||map[nr][nc]!='L')
								continue;
							visited[nr][nc] = visited[cur.r][cur.c]+1;
							ans= Math.max(ans, visited[nr][nc]);
							q.offer(new Pos(nr,nc));
							
						}
					}
				}
			}
		}
		System.out.println(ans-1);
	}
}
