package com.ssafy.exam;

import java.util.Scanner;

public class SWEA_5356 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++)
		{
		
			int maxLen=0;
			String[] str=new String[5];
            int[] len=new int[5];
			for(int i=0;i<5;i++) {
				str[i]=sc.next();
                len[i]=str[i].length();
				maxLen=len[i]>maxLen?len[i]:maxLen;
			}
			String ans="";
			for(int i=0;i<maxLen;i++) {
				for(int j=0;j<5;j++) {
					if(i<len[j]) {
						ans+=str[j].charAt(i);
					}
				}
			}
            System.out.println("#"+test_case+" "+ans);
		}
	}
}
