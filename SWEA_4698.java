package com.ssafy.exam;

import java.util.Scanner;

public class SWEA_4698 {
	final static int MAX=1000000;
	public static void main(String[] args) {
		boolean[] primenum=new boolean[MAX+1];
		primenum[0]=true;
		primenum[1]=true;
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		for(int i=2;i*i<=1000000;i++){
        	for(int j=2;i*j<=1000000;j++){
            primenum[i*j]=true;
            }
        }
		for(int test_case = 1; test_case <= T; test_case++)
		{
		
			int d=sc.nextInt();
			int a=sc.nextInt();
			int b=sc.nextInt();
			String str=Integer.toString(d);
			int cnt=0;
			for(int i=a;i<=b;i++) {
				if(primenum[i]==false) {
					if(Integer.toString(i).contains(str))cnt++;
				}
			}
			System.out.println("#"+test_case+" "+cnt);
		}
	}
}
