package com.ssafy.exam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * 한시간반쯤걸림
 * */
public class BJ_17281_야구 {
	static int player[][], ans, order[], N;
	static boolean selected[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		//선수가 이닝별 얻는 결과 저장
		player = new int[N][9];
		//타자 순서 순열저장
		order = new int[9];
		//순열 뽑힌건지 확ㅇ니
		selected = new boolean[9];
		StringTokenizer st;
		for(int i = 0; i< N;i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				player[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//순열 만들기 시작
		makeorder(0);
		//정답 출력
		System.out.println(ans);
	}

	private static void makeorder(int cnt) {
		//9타자 순서가 다뽑히면
		if (cnt == 9) {
			//이닝, 아웃개수, 점수 변수
			int inning = 0, out = 0, score = 0;
			// 1,2,3루 주자있는지 없는지 표시
			boolean[] base = { false, false, false, false };
			//시작, N이닝진행하는데 0~N-1이면 진행
			for (int i = 0; inning != N; i++) {
				//1루타
				if (player[inning][order[i % 9]] == 1) {
					//3루에 주자있으면 점수 추가
					if (base[3])
						score++;
					//2루에사람있으면 3루로, 1루에있으면 2루로, 1루에 사람표시
					base[3] = base[2];
					base[2] = base[1];
					base[1] = true;
				//2루타
				} else if (player[inning][order[i % 9]] == 2) {
					//3루나 2루에 사람있으면 점수추가
					if (base[3])
						score++;
					if (base[2])
						score++;
					//1루에있는사람 3루로, 2루에 사람표시, 1루 비우기
					base[3] = base[1];
					base[2] = true;
					base[1] = false;
				//3루타
				} else if (player[inning][order[i % 9]] == 3) {
					//1,2,3루에 사람있으면 점수추가
					if (base[3])
						score++;
					if (base[2])
						score++;
					if (base[1])
						score++;
					//3루에 주자표시1,2루 주자없음
					base[3] = true;
					base[2] = false;
					base[1] = false;
				//홈런
				} else if (player[inning][order[i % 9]] == 4) {
					//1,2,3루에 주자있으면 점수추가, 홈런친 타자 점수 추가
					if (base[3])
						score++;
					if (base[2])
						score++;
					if (base[1])
						score++;
					score++;
					//1,2,3루 다비우기
					Arrays.fill(base, false);
				//아웃
				} else {
					// 0이면 아웃카운트추가
					out++;
				}
				//아웃 3이면 이닝올리고 주자 초기화
				if (out == 3) {
					out = 0;
					inning++;
					Arrays.fill(base, false);
				}
			}
			//최댓값 갱신
			ans = Math.max(ans, score);
			return;
		}
		//4번타자는 무조건 1번 선수
		if (cnt == 3) {
			order[cnt]=0;
			makeorder(cnt + 1);
		}
		//1번 선수는 무조건 4번타자이기 떄문에 뺴고 순열만들기
		for (int i = 1; i < 9; i++) {
			if (selected[i])
				continue;
			order[cnt] = i;
			selected[i] = true;
			makeorder(cnt + 1);
			selected[i] = false;
		}

	}
}
