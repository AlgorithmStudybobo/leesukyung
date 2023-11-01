import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2468_안전영역 {
	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] map = new int[n][n];
		int[] dr = { -1, 0, 1, 0 }, dc = { 0, 1, 0, -1 };
		StringTokenizer st;
		int max = 0, min = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				max = Math.max(max, map[i][j]);
				min = Math.min(min, map[i][j]);
			}
		}
		int ans = 0;
		Queue<Pos> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[n][n];
		for (int i = min; i <= max; i++) {
			int cnt = 0;
			for (int r = 0; r < n; r++) {
				for (int c = 0; c < n; c++) {
					if (!visited[r][c] && map[r][c] > i) {
						cnt++;
						q.offer(new Pos(r, c));
						visited[r][c] = true;
						while (!q.isEmpty()) {
							Pos cur = q.poll();
							for (int dir = 0; dir < 4; dir++) {
								int nr = cur.r + dr[dir];
								int nc = cur.c + dc[dir];
								if (nr < 0 || nr >= n || nc < 0 || nc >= n || visited[nr][nc] || map[nr][nc] <= i)
									continue;
								visited[nr][nc] = true;
								q.offer(new Pos(nr, nc));
							}
						}
					}
				}
			}
			ans = Math.max(ans, cnt);
			for (int k = 0; k < n; k++) {
				Arrays.fill(visited[k], false);
			}
		}
		System.out.println(ans);
	}
}
