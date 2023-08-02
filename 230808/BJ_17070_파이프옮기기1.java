import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 백준 17070-파이프 옮기기1
 * 메모리 21508KB
 * 336ms
 * 총 30분 소요
 * */
public class BJ_17070_파이프옮기기1 {
	static int ans=0;
	static int[][] map;
	static int n;
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		map=new int[n][n];
		for(int i=0;i<n;i++) {
			StringTokenizer st=new StringTokenizer(br.readLine()," ");
			for(int j=0;j<n;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		//1벽 2파이프 0빈공간
		map[0][0]=2;
		map[0][1]=2;
		
		dfs(0,1);
		System.out.println(ans);
		
	}
	static void dfs(int r, int c) {
		//n,n에 도착하면 ans 증가
		if(r==n-1&&c==n-1) {
			ans++;return;
		}
		//r,c는 들어왔기 떄문에 무조건 1
		//map[r][c]==2&&map[r][c-1]==2 오른쪽으로 갈 수 있음
		//map[r][c]==2&&map[r-1][c]==2 아래로 갈 수 있음
		//map[r][c+1]==0&&map[r+1][c]==0&&map[r+1][c+1]==0 오른쪽아래방향으로 갈 수 있음
		if(c-1>=0&&c+1<n&&map[r][c-1]==2&&map[r][c+1]==0) {
			map[r][c+1]=2;
			dfs(r,c+1);
			map[r][c+1]=0;
		}
		if(r-1>=0&&r+1<n&&map[r-1][c]==2&&map[r+1][c]==0) {
			map[r+1][c]=2;
			dfs(r+1,c);
			map[r+1][c]=0;
		}
		if(r+1<n&&c+1<n&&map[r][c+1]==0&&map[r+1][c]==0&&map[r+1][c+1]==0) {
			map[r][c+1]=2;
			map[r+1][c]=2;
			map[r+1][c+1]=2;
			dfs(r+1,c+1);
			map[r][c+1]=0;
			map[r+1][c]=0;
			map[r+1][c+1]=0;
		}
	}
}
