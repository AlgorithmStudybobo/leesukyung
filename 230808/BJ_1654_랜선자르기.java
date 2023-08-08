import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 메모리 17804KB
 * 172ms
 * 
 * */
public class BJ_1654_랜선자르기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int k = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		// 정답 저장할 변수
		long max = 0;
		int[] lan = new int[k];
		long low = 1, high = 0, mid = 0;
		for (int i = 0; i < k; i++) {
			lan[i] = Integer.parseInt(br.readLine());
			// high에 랜선 길이 중 가장 긴 것 넣기
			if (lan[i] > high)
				high = lan[i];
		}

		// 이분탐색 조건
		while (high >= low) {
			// 이분탐색
			mid = (low + high) / 2;
			// 랜선 개수 세기
			int cnt = 0;
			for (int i = 0; i < k; i++) {
				cnt += lan[i] / mid;
			}
			// 랜선 개수가 n보다 크거나 같으면 길이 더 길게 해서 탐색 가능
			if (cnt >= n) {
				// max 갱신
				max = mid;
				low = mid + 1;
			}
			// n보다 작을 경우 자르는 길이 짧아져야함
			else if (cnt < n) {
				high = mid - 1;
			}
		}
		System.out.println(max);
	}
}
