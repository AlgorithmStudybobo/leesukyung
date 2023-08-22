package com.ssafy.exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_2212_센서 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());
		int[] s = new int[n];
		int[] d = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i<n;i++) {
			s[i]=Integer.parseInt(st.nextToken());
		}
		//정렬
		Arrays.sort(s);
		//차이 저장
		for(int i =1;i<n;i++) {
			d[i]=s[i]-s[i-1];
		}
		//차이 정렬
		Arrays.sort(d);
		int ans = 0;
		//더하기
		for(int i = 0; i<=n-k;i++) {
			ans+=d[i];
		}
		//출력
		System.out.println(ans);
	}
}
