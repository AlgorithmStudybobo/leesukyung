import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_13460_구슬탈출2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		char[][] map = new char[n][];
		for (int i = 0; i < n; i++)
			map[i] = br.readLine().toCharArray();
		int[] first = new int[5];
		// ry, rx, by, bx, cnt
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 'R') {
					first[0] = i;
					first[1] = j;
					map[i][j] = '.';
				}
				if (map[i][j] == 'B') {
					first[2] = i;
					first[3] = j;
					map[i][j] = '.';
				}
			}
		}
		int dr[] = { -1, 0, 1, 0 }, dc[] = { 0, 1, 0, -1 };
		boolean flag = false;
		boolean[][][][] visited = new boolean[n][m][n][m];
		visited[first[0]][first[1]][first[2]][first[3]] = true;
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(first);
		while (!q.isEmpty()) {
			// ry, rx, by, bx, cnt
			int[] cur = q.poll();
			if (cur[4] > 10) {
				break;
			}
			if (map[cur[0]][cur[1]] == 'O') {
				System.out.println(cur[4]);
				flag = true;
				break;
			}

			for (int dir = 0; dir < 4; dir++) {
				int ry = cur[0], rx = cur[1], by = cur[2], bx = cur[3];
				while (map[ry][rx] == '.') {
					ry += dr[dir];
					rx += dc[dir];
				}
				while (map[by][bx] == '.') {
					by += dr[dir];
					bx += dc[dir];
				}
				// 둘다 구멍에 빠졌을 때 처리
				if (map[ry][rx] == 'O' && map[by][bx] == 'O')
					continue;

				if (map[ry][rx] == '#') {
					ry -= dr[dir];
					rx -= dc[dir];
				}

				if (map[by][bx] == '#') {
					by -= dr[dir];
					bx -= dc[dir];
				}
				// 같은 좌표에 있을 때
				if (ry == by && rx == bx) {
					if (dir == 0) {
						if (cur[0] < cur[2])
							by++;
						else
							ry++;
					} else if (dir == 1) {
						if (cur[1] < cur[3])
							rx--;
						else
							bx--;
					} else if (dir == 2) {
						if (cur[0] < cur[2])
							ry--;
						else
							by--;
					} else {
						if (cur[1] < cur[3])
							bx++;
						else
							rx++;
					}
				}
				if (!visited[ry][rx][by][bx]) {
					visited[ry][rx][by][bx] = true;
					q.offer(new int[] { ry, rx, by, bx, cur[4] + 1 });
				}
			}

		}
		if (flag == false) {
			System.out.println("-1");
		}
	}
}
