package com.ssafy.exam;

import java.util.Scanner;

public class SWEA_4047 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++)
		{
		
			boolean[] s=new boolean[14];
			boolean[] d=new boolean[14];
			boolean[] h=new boolean[14];
			boolean[] c=new boolean[14];
			int snum=13, dnum=13, hnum=13, cnum=13;
			String str=sc.next();
			boolean flag=false;
			for(int i=0;i<str.length();i+=3) {
				if(str.charAt(i)=='S') {
					if(s[Integer.parseInt(str.substring(i+1,i+3))]==true) {
						flag=true;break;
					}else {
						s[Integer.parseInt(str.substring(i+1,i+3))]=true;
						snum--;
					}
				}else if(str.charAt(i)=='D') {
					if(d[Integer.parseInt(str.substring(i+1,i+3))]==true) {
						flag=true;break;
					}else {
						d[Integer.parseInt(str.substring(i+1,i+3))]=true;
						dnum--;
					}
				}else if(str.charAt(i)=='H') {
					if(h[Integer.parseInt(str.substring(i+1,i+3))]==true) {
						flag=true;break;
					}else {
						h[Integer.parseInt(str.substring(i+1,i+3))]=true;
						hnum--;
					}
				}else {
					if(str.charAt(i)=='C') {
						if(c[Integer.parseInt(str.substring(i+1,i+3))]==true) {
							flag=true;break;
						}else {
							c[Integer.parseInt(str.substring(i+1,i+3))]=true;
							cnum--;
						}
					}
				}
			}
			
			System.out.printf("#%d ",test_case);
			if(flag==true) {
				System.out.println("ERROR");
			}else {
				System.out.printf("%d %d %d %d\n",snum,dnum,hnum,cnum);
			}

		}
	}
}
