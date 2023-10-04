package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BJ_2665_미로만들기 {
	static class Pos implements Comparable<Pos> {
		int r, c, k;

		public Pos(int r, int c, int k) {
			this.r = r;
			this.c = c;
			this.k = k;
		}

		@Override
		public int compareTo(Pos o) {
			return Integer.compare(this.k, o.k);
		}

	}

	static int dr[] = { 1, 0, -1, 0 }, dc[] = { 0, 1, 0, -1 };
	static int n, ans = Integer.MAX_VALUE;
	static boolean visited[][];
	static char map[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new char[n][];
		visited = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
		}
		miro();
	}

	static void miro() {
		PriorityQueue<Pos> q = new PriorityQueue<>();
		visited[0][0] = true;
		q.offer(new Pos(0, 0, 0));
		while (!q.isEmpty()) {
			Pos cur = q.poll();
			if(cur.r==n-1 && cur.c==n-1) {
				System.out.println(cur.k);
				return;
			}
			for (int dir = 0; dir < 4; dir++) {
				int nr = cur.r + dr[dir];
				int nc = cur.c + dc[dir];
				if (nr < 0 || nr >= n || nc < 0 || nc >= n || visited[nr][nc])
					continue;
				visited[nr][nc] =  true;
				if(map[nr][nc]=='1') {
					q.offer(new Pos(nr, nc, cur.k));
				}else {
					q.offer(new Pos(nr, nc, cur.k + 1));
				}
			}
		}
	}
}
