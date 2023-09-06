package algo;

import java.util.ArrayDeque;
import java.util.Queue;

public class 프로그래머스_아이템줍기 {
	//0~3까지 사방 탐색, 0~7까지 팔방탐색 
    static int[] dr = { -1, 0, 1, 0, -1, -1, 1, 1};
	static int[] dc = { 0, 1, 0, -1, -1, 1, 1, -1 };
    static int[][] map;
    //좌표 저장 클래스
	class Pos{
	    int r, c;
	    Pos(int r, int c){
	        this.r = r;
	        this.c = c;
	    }
	}
	class Solution {
	    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
	        //맵 두배로 선언
	    	//ㄷ자의 경우 1배로 그리면 파악 불가능
	    	map = new int[101][101];
	        //visited 배열도 두배로 설정
	    	int[][] visited = new int[101][101];
	        //사각형 2배로 그리기
	    	for(int i = 0; i<rectangle.length;i++){
	            int sx = rectangle[i][0], sy = rectangle[i][1], ex = rectangle[i][2], ey = rectangle[i][3];
	            for(int r = 2*sy;r<=2*ey;r++){
	                for(int c=2*sx; c<=2*ex;c++){
	                    map[r][c]=1;
	                }
	            }
	        }
	        //bfs 시작
	        Queue<Pos> q= new ArrayDeque<>();
	        //시작 좌표  x,y 두배로 해서 시작
	        visited[2*characterY][2*characterX]=1;
	        q.offer(new Pos(2*characterY, 2*characterX));
	        while(!q.isEmpty()){
	        	//큐에서 뽑기
	            Pos cur = q.poll();
	            //4방향 탐색
				for (int dir = 0; dir < 4; dir++) {
					//다음 좌표
					int nr = cur.r + dr[dir];
					int nc = cur.c + dc[dir];
					//좌표 범위 넘어가거나 갈 수 없는 곳 또는 이미 방문한 곳일 때 넘어가기
					if (nr < 0 || nr > 100 || nc < 0 || nc > 100 || !canGo(nr, nc) || visited[nr][nc] != 0||map[nr][nc]==0)
						continue;
					visited[nr][nc] = visited[cur.r][cur.c] + 1;
					q.offer(new Pos(nr,nc));
				}
	        }
	        //도착좌표의 visited 값에서 2로 나눠서 리턴
	        //map 두배로 그렸기 때문
	        return visited[2*itemY][2*itemX]/2;
	    }   
	}
	//갈 수 있는지 확인 한 좌표에서 8방탐색해서 하나라도 0이면 외곽선
	private static boolean canGo(int r, int c) {
		for (int dir = 0; dir < 8; dir++) {
			int nr = r + dr[dir];
			int nc = c + dc[dir];
			if (nr < 0 || nr > 100 || nc < 0 || nc > 100 || map[nr][nc] == 0)
				return true;
		}
		return false;
	}
	public static void main(String[] args) {
		
	}
}
