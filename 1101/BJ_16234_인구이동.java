package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_16234_인구이동 {
	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int[][] map = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int dr[] = { -1, 0, 1, 0 }, dc[] = { 0, 1, 0, -1 };
		Queue<Pos> q = new ArrayDeque<>();
		List<Pos> list = new ArrayList<>();
		int ans = 0;
		boolean flag = false;
		for (;; ans++) {
			flag = false;
			boolean[][] visited = new boolean[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (!visited[i][j]) {
						int sum = map[i][j];
						visited[i][j] = true;
						q.offer(new Pos(i, j));
						list.add(new Pos(i, j));
						while (!q.isEmpty()) {
							Pos cur = q.poll();
							for (int dir = 0; dir < 4; dir++) {
								int nr = cur.r + dr[dir];
								int nc = cur.c + dc[dir];
								if (nr < 0 || nr >= n || nc < 0 || nc >= n || visited[nr][nc])
									continue;
								int d = Math.abs(map[cur.r][cur.c] - map[nr][nc]);

								if (d >= l && d <= r) {
									visited[nr][nc] = true;
									sum += map[nr][nc];
									q.offer(new Pos(nr, nc));
									list.add(new Pos(nr, nc));
								}
							}
						}
						if (list.size() > 1) {
							sum /= list.size();
							for (int k = 0; k < list.size(); k++) {
								map[list.get(k).r][list.get(k).c] = sum;
							}
							flag = true;
						}
						list.clear();
					}
				}
			}
			if (flag == false) {
				System.out.println(ans);
				break;
			}
		}

	}
}
