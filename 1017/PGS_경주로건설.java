import java.util.PriorityQueue;

public class PGS_경주로건설 {
	static int dr[] = { -1, 0, 1, 0 }, dc[] = { 0, 1, 0, -1 };

	static class Pos implements Comparable<Pos> {
		int r, c, dir, price;

		public Pos(int r, int c, int dir, int price) {
			this.r = r;
			this.c = c;
			this.dir = dir;
			this.price = price;
		}

		@Override
		public int compareTo(Pos o) {
			return this.price - o.price;
		}
	}

	public int solution(int[][] board) {
		int size = board.length;
		int[][][] min = new int[size][size][4];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				for (int k = 0; k < 4; k++) {
					min[i][j][k] = 0x7fffffff;
				}
			}
		}
		// 특정 r,c까지 좌표에서 최소 비용이었다고 우측하단에서 최소값이 된다는 보장 없음
		// 중간까지 비용이 비쌌어도 우측하단 좌표에서 최소일 수 있기 때문에 방향별로 최소비용을 찾음
		
		min[0][0][0] = 0;
		min[0][0][1] = 0;
		min[0][0][2] = 0;
		min[0][0][3] = 0;

		PriorityQueue<Pos> pq = new PriorityQueue<>();
		pq.offer(new Pos(0, 0, -1, 0));

		while (!pq.isEmpty()) {
			Pos cur = pq.poll();
			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				if (nr < 0 || nr >= size || nc < 0 || nc >= size || board[nr][nc] == 1)
					continue;
				if (cur.dir == -1 || cur.dir == d) {
					if (cur.price + 100 <= min[nr][nc][d]) {
						min[nr][nc][d] = cur.price + 100;
						pq.offer(new Pos(nr, nc, d, cur.price + 100));
					}
				} else {
					if (cur.price + 600 <= min[nr][nc][d]) {
						min[nr][nc][d] = cur.price + 600;
						pq.offer(new Pos(nr, nc, d, cur.price + 600));
					}
				}
			}
		}
		int answer = 0x7fffffff;
		for (int i = 0; i < 4; i++) {
			answer = Math.min(answer, min[size - 1][size - 1][i]);
		}
		return answer;
	}
}
