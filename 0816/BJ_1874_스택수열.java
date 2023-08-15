package com.ssafy.exam;

import java.util.Scanner;
import java.util.Stack;

public class BJ_1874_스택수열 {
	public static void main(String[] args) {
		//스캐너로 값 읽어올것임
		Scanner sc = new Scanner(System.in);
		//스트링빌더쓸거임
		StringBuilder sb = new StringBuilder();
		//스택선언
		Stack<Integer> s = new Stack<>();
		//n값 읽어오기
		int n = sc.nextInt();
		//1부터 삽입
		int j = 1;
		//순서대로 값 읽어오기
		for(int i = 0; i < n; i++) {
			int a = sc.nextInt();
			//스택이 비어있거나 스택의 탑이  a보다작으면
			if(s.isEmpty() || s.peek() < a) {
				//j부터 a까지 스택에 삽입
				for(; j <= a; j++) {
					s.push(j);
					sb.append("+\n");
				}
				//a까지 들어갔으면 a 빼기
				sb.append("-\n");
				s.pop();
			//아닌 경우에
			}else {
				//스택 탑이 a면 빼고아니면 불가능
				if(s.peek() == a) {
					sb.append("-\n");
					s.pop();
				}
			}
		}
		//스택 비어있으면 가능한 경우
		if(s.isEmpty())
			System.out.println(sb);
		else
			//비어있지않으면 불가능
			System.out.println("NO");
	}
}