import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * �޸� 17804KB
 * 172ms
 * 
 * */
public class BJ_1654_�����ڸ��� {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int k = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		// ���� ������ ����
		long max = 0;
		int[] lan = new int[k];
		long low = 1, high = 0, mid = 0;
		for (int i = 0; i < k; i++) {
			lan[i] = Integer.parseInt(br.readLine());
			// high�� ���� ���� �� ���� �� �� �ֱ�
			if (lan[i] > high)
				high = lan[i];
		}

		// �̺�Ž�� ����
		while (high >= low) {
			// �̺�Ž��
			mid = (low + high) / 2;
			// ���� ���� ����
			int cnt = 0;
			for (int i = 0; i < k; i++) {
				cnt += lan[i] / mid;
			}
			// ���� ������ n���� ũ�ų� ������ ���� �� ��� �ؼ� Ž�� ����
			if (cnt >= n) {
				// max ����
				max = mid;
				low = mid + 1;
			}
			// n���� ���� ��� �ڸ��� ���� ª��������
			else if (cnt < n) {
				high = mid - 1;
			}
		}
		System.out.println(max);
	}
}
