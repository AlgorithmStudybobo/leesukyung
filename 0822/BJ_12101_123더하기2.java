package com.ssafy.exam;

import java.util.Scanner;

public class BJ_12101_123더하기2 {
	static int numbers[], n, k, order=1;
	static boolean flag;
	public static void main(String[] args) {
		//입력받기
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		k = sc.nextInt();
		//합이 n이면 1이 n개일 때 길이 최장
		numbers = new int[n];
		//시작
		perm(0,0);
		//순열 출력못했으면
		if(!flag) {
			//-1 출력
			System.out.println(-1);
		}
	}
	//중복순열 생성
	private static void perm(int cnt, int sum) {
		//합이 n보다 크면 리턴
		if(sum>n)return;
		//합이 n이고
		if(sum==n) {
			//입력받은 k랑 순열이 완성된 순서 order랑 같으면 출력
			if(order==k) {
			System.out.println(order);
				//출력했음을 표시하는 플래그
				flag = true;
				//출력부
				for(int i = 0; i<cnt-1;i++) {
					System.out.print(numbers[i]+"+");
				}
				System.out.println(numbers[cnt-1]);
			}
			//아니면 순서만 표시해줌
			order++;
			return;
		}
		//123중복순열생성부
		for (int i = 1; i <= 3; i++) {
			numbers[cnt]=i;
			perm(cnt + 1, sum+i);
		}
	}
}
