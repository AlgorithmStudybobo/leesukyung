package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_1005_ACMCraft {
	static class Node {
		int vertex;
		Node next;

		public Node(int vertex, Node next) {
			super();
			this.vertex = vertex;
			this.next = next;
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int tc = Integer.parseInt(br.readLine());
		for (int t = 0; t < tc; t++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			int[] time = new int[n + 1];
			st = new StringTokenizer(br.readLine());
			int[] totalTime = new int[n + 1];
			for (int i = 1; i <= n; i++) {
				time[i] = Integer.parseInt(st.nextToken());
			}
			Node[] adjList = new Node[n + 1];
			int[] inDegree = new int[n + 1];
			for (int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				adjList[from] = new Node(to, adjList[from]);
				inDegree[to]++;
			}
			Queue<Integer> q = new ArrayDeque<>();
			for (int i = 1; i <= n; i++) {
				if (inDegree[i] == 0) {
					q.offer(i);
					totalTime[i] = time[i];
				}
			}

			while (!q.isEmpty()) {
				int cur = q.poll();
				for (Node temp = adjList[cur]; temp != null; temp = temp.next) {
					totalTime[temp.vertex] = Math.max(totalTime[temp.vertex], totalTime[cur] + time[temp.vertex]);
					if(--inDegree[temp.vertex]==0)
						q.offer(temp.vertex);
				}
			}

			System.out.println(totalTime[Integer.parseInt(br.readLine())]);
		}
	}
}