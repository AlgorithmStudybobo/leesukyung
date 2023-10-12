
public class PGS_연속펄스부분수열의합 {
	public long solution(int[] sequence) {
		// +-+-+-
		long[] arr1 = new long[sequence.length];
		// -+-+-+
		long[] arr2 = new long[sequence.length];
		arr1[0] = sequence[0];
		arr2[0] = -sequence[0];
		long answer = Math.max(arr1[0], arr2[0]);
		for (int i = 1; i < sequence.length; i++) {
			if (i % 2 == 1) {
				arr1[i] = Math.max(arr1[i - 1] - sequence[i], -sequence[i]);
				arr2[i] = Math.max(arr2[i - 1] + sequence[i], sequence[i]);
			} else {
				arr1[i] = Math.max(arr1[i - 1] + sequence[i], sequence[i]);
				arr2[i] = Math.max(arr2[i - 1] - sequence[i], -sequence[i]);
			}
			answer = Math.max(Math.max(answer, arr1[i]), arr2[i]);
		}
		return answer;
	}
}
