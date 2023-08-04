package com.ssafy.exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 그리디로 3시간했는데 안됨 ㅠ
 * 재귀로 2시간
 * */
public class BJ_17136_색종이붙이기 {
	final static int N = 10;
	static int ans = 0x7fffffff, map[][], paper[];

	static void attach(int r, int c, int cnt) {
		// cnt가 ans보다 크거나 같으면 최소 개수 아님
		if (cnt >= ans) {
			return;
		}
		//c가 행크기보다 크면 다음 열로 이동
		if (c >= N) {
			attach(r + 1, 0, cnt);
			return;
		}
		//r이 열크기보다 크면 색종이 다 붙인 경우임 ans 갱신
		if (r >= N) {
			ans = ans > cnt ? cnt : ans;
			return;
		}
		//현재 위치가 0이면 다음 위치로 이동
		if (map[r][c] == 0) {
			attach(r, c + 1, cnt);
		} else {
			//색종이 사이즈 1~5
			for (int size = 1; size <= 5; size++) {
				//해당 위치에서 붙일 생종이 사이즈가 map 크기를 넘으면 중단
				if (r + size > N || c + size > N)
					break;
				//색종이 붙일 수 있는지 확인하기 위한 플래그
				boolean flag = true;
				//확인
				for (int i = 0; i < size; i++) {
					for (int j = 0; j < size; j++) {
						//색종이 붙이는 자리가 아니면 중단
						if (map[r + i][c + j] == 0) {
							flag = false;
							break;
						}
					}
					//중단
					if (flag == false)
						break;
				}
				//붙일 수 있을 때
				if (flag == true) {
					//해당 사이즈 색종이가 남아있으면
					if(paper[size] > 0 ) {
						//해당 사이즈의 색종이--
						paper[size]--;
						//map에도 변경
						for (int i = 0; i < size; i++) {
							Arrays.fill(map[r + i], c, c + size, 0);
						}
						//붙인 뒤에 검색
						attach(r, c + size, cnt + 1);
						//다시 원상복구
						for (int i = 0; i < size; i++) {
							Arrays.fill(map[r + i], c, c + size, 1);
						}
						paper[size]++;
					}
					//지금 크기 이상의 색종이는 붙일 수 없을 때 중단
				}else {
					break;
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		map = new int[N][N];
		paper = new int[] { 0, 5, 5, 5, 5, 5 };
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//시작
		attach(0, 0, 0);
		
		//ans가 초기값 그대로면 -1 출력 아니면 ans출력
		if (ans == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else
			System.out.println(ans);
	}
}
