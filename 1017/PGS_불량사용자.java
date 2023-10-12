import java.util.HashSet;
import java.util.Set;

public class PGS_불량사용자 {
	static int answer = 0;
	static Set<Integer> set = new HashSet<>();

	public int solution(String[] user_id, String[] banned_id) {
		badUsers(user_id, banned_id, new boolean[user_id.length], 0);
		return answer;
	}

	private static void badUsers(String[] user_id, String[] banned_id, boolean[] checked, int cnt) {
		if (cnt == banned_id.length) {
			int temp = 0;
			/*
			 * 해시코드같은 느낌으로 키값 생성하고 그게 없으면 셋에 넣고 answer++ 있으면 리턴
			 * */
			for (int i = 0; i < checked.length; i++) {
				if (checked[i]) {
					temp = temp * 10 + i + 1;
				}
			}
			if (!set.contains(temp)) {
				set.add(temp);
				answer++;
			}
			return;
		}
		for (int j = 0; j < user_id.length; j++) {
			if (checked[j])
				continue;
			if (isPossible(user_id[j], banned_id[cnt])) {
				checked[j] = true;
				badUsers(user_id, banned_id, checked, cnt + 1);
				checked[j] = false;
			}
		}
	}

	private static boolean isPossible(String user_id, String banned_id) {
		if (user_id.length() != banned_id.length())
			return false;
		for (int i = 0; i < banned_id.length(); i++) {
			if (banned_id.charAt(i) == '*')
				continue;
			if (banned_id.charAt(i) != user_id.charAt(i))
				return false;
		}
		return true;
	}
}
