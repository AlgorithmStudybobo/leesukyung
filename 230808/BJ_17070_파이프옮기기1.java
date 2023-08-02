import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * ���� 17070-������ �ű��1
 * �޸� 21508KB
 * 336ms
 * �� 30�� �ҿ�
 * */
public class BJ_17070_�������ű��1 {
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
		//1�� 2������ 0�����
		map[0][0]=2;
		map[0][1]=2;
		
		dfs(0,1);
		System.out.println(ans);
		
	}
	static void dfs(int r, int c) {
		//n,n�� �����ϸ� ans ����
		if(r==n-1&&c==n-1) {
			ans++;return;
		}
		//r,c�� ���Ա� ������ ������ 1
		//map[r][c]==2&&map[r][c-1]==2 ���������� �� �� ����
		//map[r][c]==2&&map[r-1][c]==2 �Ʒ��� �� �� ����
		//map[r][c+1]==0&&map[r+1][c]==0&&map[r+1][c+1]==0 �����ʾƷ��������� �� �� ����
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
