package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_1113_수영장만들기 {
	static class Pos {
		int r, c;

		public Pos(int r, int c) {
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
		char[][] temp = new char[n][m];
		for (int i = 0; i < n; i++) {
			temp[i] = br.readLine().toCharArray();
		}
		int[][] map = new int[n][m];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				map[i][j] = temp[i][j] - '0';
		int ans = 0;
		boolean[][] visited = new boolean[n][m];
		int[][] visit = new int[n][m];
		Queue<Pos> q = new ArrayDeque<>();
		List<Pos> list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (!visited[i][j]) {
					visited[i][j] = true;
					if (i == 0 || i == n - 1 || j == 0 || j == m - 1)
						continue;
					for (int k = 0; k < n; k++) {
						Arrays.fill(visit[k], 0);
					}
					for (int max = 9; max >= map[i][j]; max--) {
						visit[i][j] = max;
						q.offer(new Pos(i, j));
						list.add(new Pos(i, j));
						boolean flag = true;
						while (!q.isEmpty()) {
							Pos cur = q.poll();
							for (int dir = 0; dir < 4; dir++) {
								int nr = cur.r + dr[dir];
								int nc = cur.c + dc[dir];
								if (nr < 0 || nr >= n || nc < 0 || nc >= m || visit[nr][nc] == max
										|| map[nr][nc] > max)
									continue;
								if (nr == 0 || nr == n - 1 || nc == 0 || nc == m - 1) {
									flag = false;
									q.clear();
									list.clear();
									break;
								}
								visit[nr][nc] = max;
								q.offer(new Pos(nr, nc));
								list.add(new Pos(nr, nc));
							}
						}
						if (flag == true) {
							for (Pos p : list) {
								visited[p.r][p.c] = true;
								ans += (max - map[p.r][p.c]+1);
							}
							list.clear();
							break;
						}
						q.clear();
						list.clear();
					}
				}
			}
		}
		System.out.println(ans);
	}
}
