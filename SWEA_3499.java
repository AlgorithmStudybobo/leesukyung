package com.ssafy.exam;

import java.util.Scanner;

public class SWEA_3499 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++)
		{
		
			int n=sc.nextInt();
			String[] str=new String[n];
			for(int i=0;i<n;i++) {
				str[i]=sc.next();
			}
			System.out.printf("#%d ",test_case);
			
			if(n%2==0) {
				for(int i=0;i<n/2;i++)
					System.out.printf("%s %s ",str[i],str[i+n/2]);
			}else {
				for(int i=0;i<n/2+1;i++) {
					System.out.printf("%s ",str[i]);
					if(i+n/2+1<n) {
						System.out.printf("%s ",str[i+n/2+1]);
					}
				}
			}
			System.out.println();

		}
        sc.close();
	}
}
