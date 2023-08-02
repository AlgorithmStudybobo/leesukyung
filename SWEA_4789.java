package com.ssafy.exam;

import java.util.Scanner;

public class SWEA_4789 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		String str;
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
		
			str=sc.next();
			int cnt=0;
			int ans=0;
			for(int i=0;i<str.length();i++) {
				if(cnt<i) {
					ans+=(i-cnt);
					cnt+=(i-cnt);
				}
				cnt+=Integer.parseInt(str.charAt(i)+"");
			}
			System.out.println("#"+test_case+" "+ans);

		}
	}
}
