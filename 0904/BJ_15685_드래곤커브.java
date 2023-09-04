package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_15685_드래곤커브 {
	static final int size = 100;
	static int n;
	static int dr[] = { 0, -1, 0, 1 }, dc[] = { 1, 0, -1, 0 };
	static boolean[][] map;
	static ArrayList<Integer> direction = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new boolean[size + 1][size + 1];
		n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			dragoncurve(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		System.out.println(count());
	}

	private static void dragoncurve(int c, int r, int dir, int generation) {
		map[r][c] = true;
		for (int i = 0; i <= generation; i++) {
			if (i == 0) {
				direction.add(dir);
				r += dr[dir];
				c += dc[dir];
				map[r][c] = true;
			} else {
				for (int j = direction.size() - 1; j >= 0; j--) {
					dir = (direction.get(j) + 1) % 4;
					direction.add(dir);
					r += dr[dir];
					c += dc[dir];
					map[r][c] = true;
				}
			}
		}
		direction.clear();
	}

	private static int count() {
		int cnt = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (map[i][j] && map[i + 1][j] && map[i][j + 1] && map[i + 1][j + 1])
					cnt++;
			}
		}
		return cnt;
	}
}