package com.ssafy.exam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

//좌표 순서 클래스
class Pos {
	int y;
	int x;
	int cnt;

	public Pos(int y, int x, int cnt) {
		super();
		this.y = y;
		this.x = x;
		this.cnt = cnt;
	}
}

public class BJ_4179_불 {
	public static void main(String[] args) throws Exception {
		//입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		//4방향 이동
		int[][] d = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		char[][] map = new char[r][c];
		boolean[][] visited = new boolean[r][c];
		for (int i = 0; i < r; i++) {
			map[i] = br.readLine().toCharArray();
		}
		//탈출 불가능한 상태에서 시작
		boolean impossible = true;

		//불, 지훈이 이동시킬 때 사용할 큐
		Queue<Pos> fire = new ArrayDeque<Pos>();
		Queue<Pos> jihun = new ArrayDeque<Pos>();

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				//맵에서 J인 곳이 지훈이 시작위치
				if (map[i][j] == 'J') {
					visited[i][j] = true;
					jihun.add(new Pos(i, j, 1));
					map[i][j] = '.';
					//F이면 불 배열에 삽입
				} else if (map[i][j] == 'F') {
					fire.add(new Pos(i, j, 1));
				}
			}
		}

		//시간 변수
		int t;
		for (t = 1;; t++) {
			// 불 이동
			while (!fire.isEmpty() && fire.peek().cnt == t) {
				//네 방향 이동
				for (int dir = 0; dir < 4; dir++) {
					int ny = fire.peek().y + d[dir][0];
					int nx = fire.peek().x + d[dir][1];
					if (ny < 0 || nx < 0 || ny >= r || nx >= c || map[ny][nx] != '.')
						continue;
					map[ny][nx] = 'F';
					fire.add(new Pos(ny, nx, fire.peek().cnt + 1));
				}
				//불 현재 좌표 팝
				fire.poll();
			}
			// 지훈 이동
			while (!jihun.isEmpty() && jihun.peek().cnt == t) {
				//지훈 네방향 이동 가능
				for (int dir = 0; dir < 4; dir++) {
					int ny = jihun.peek().y + d[dir][0];
					int nx = jihun.peek().x + d[dir][1];
					//지훈이가 맵 범위 밖으로 나갈 수 있으면 탈출 성공
					//탈출을 가능한 상태로 변경하고 반복문 빠져나가기
					if (ny < 0 || nx < 0 || ny >= r || nx >= c) {
						impossible = false;
						break;
					}
					//맵이 .이고 이전에 방문하지 않았을 때 지훈이 이동 가능
					if (map[ny][nx] == '.' && !visited[ny][nx]) {
						jihun.add(new Pos(ny, nx, jihun.peek().cnt + 1));
						//지훈이 이미 방문한 곳 다시 방문하지 않도록 방문 처리
						visited[ny][nx] = true;
					}
				}
				//지훈 현재 좌표 팝
				jihun.poll();
			}

			//지훈 큐가 비어있으면 지훈이 더 이상 이동할 수 있는 곳 없음
			//impossible 거짓이면 이미 탈출 가능한 상태
			//시간 증가시키는 for문 탈출
			if (jihun.isEmpty() || impossible == false)
				break;

		}

		//탈출가능하면  t출력 아니면 임파서블출력
		if (impossible == false) {
			System.out.println(t);
		} else {
			System.out.println("IMPOSSIBLE");
		}
	}
}
