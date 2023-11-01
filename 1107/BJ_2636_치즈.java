package algo;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2636_치즈 {
	static int n, m, map[][], dr[] = { -1, 0, 1, 0 }, dc[] = { 0, 1, 0, -1 }, cur, prev;
	static Queue<Point> q = new ArrayDeque<>();
	static boolean[][] visited = new boolean[n][m];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		visited = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1)
					cur++;
			}
		}
		// 외곽 처리
		outskirts(0, 0);
		int ans = 0;
		for (;; ans++) {
			for (int i = 0; i < n; i++) {
				Arrays.fill(visited[i], false);
			}
			boolean flag = false;
			int cnt = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (map[i][j] != 1)
						continue;
					for (int dir = 0; dir < 4; dir++) {
						int nr = i + dr[dir];
						int nc = j + dc[dir];
						if (nr < 0 || nr >= n || nc < 0 || nc >= m || visited[nr][nc] || map[nr][nc] != 2)
							continue;
						cnt++;
						map[i][j] = 2;
						visited[i][j] = true;
						flag = true;
						break;
					}
				}
			}
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (visited[i][j]) {
						outskirts(i, j);
					}
				}
			}

			if (!flag) {
				System.out.println(ans);
				System.out.println(prev);
				break;
			}
			prev = cnt;
			cur -= prev;
		}
	}

	static void outskirts(int r, int c) {
		q.offer(new Point(r, c));
		map[r][c] = 2;
		while (!q.isEmpty()) {
			Point cur = q.poll();
			for (int dir = 0; dir < 4; dir++) {
				int nr = cur.x + dr[dir];
				int nc = cur.y + dc[dir];
				if (nr < 0 || nr >= n || nc < 0 || nc >= m || map[nr][nc] != 0)
					continue;
				map[nr][nc] = 2;
				q.offer(new Point(nr, nc));
			}
		}
	}
}
