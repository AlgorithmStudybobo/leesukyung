import java.util.*;
import java.util.Map.*;

public class PGS_뉴스클러스터링 {
	public int solution(String str1, String str2) {
		Map<String, Integer> map1 = new HashMap<>();
		Map<String, Integer> map2 = new HashMap<>();
		str1 = str1.toLowerCase();
		str2 = str2.toLowerCase();
		puts(map1, str1);
		puts(map2, str2);

		// a교, b합
		int a = 0, b = 0;

		for (Entry<String, Integer> e1 : map1.entrySet()) {
			for (Entry<String, Integer> e2 : map2.entrySet()) {
				if (e1.getKey().equals(e2.getKey())) {
					a += Math.min(e1.getValue(), e2.getValue());
				}
			}
		}
		// 합집합
		Map<String, Integer> union = new HashMap<>();
		// map1에 있는거 다 넣고
		for (Entry<String, Integer> e : map1.entrySet()) {
			union.put(e.getKey(), e.getValue());
		}
		// map2 조사 시작
		for (Entry<String, Integer> e : map2.entrySet()) {
			// 합집합에 키가 존재하면 값의 최대값을 저장
			if (union.containsKey(e.getKey())) {
				union.replace(e.getKey(), Math.max(e.getValue(), union.get(e.getKey())));
			// 없으면 그냥 넣기
			} else {
				union.put(e.getKey(), e.getValue());
			}
		}
		for (Entry<String, Integer> e : union.entrySet()) {
			b += e.getValue();
		}

		if (a == b)
			return 65536;
		return (int) ((double) a / b * 65536);
	}

	// str 2글자씩 잘라서 맵에 넣는 함수
	private static void puts(Map<String, Integer> map, String str) {
		for (int i = 0; i < str.length() - 1; i++) {
			// 알파벳 범위 벗어나면 맵에 넣지 않음
			if (str.charAt(i) > 'z' || str.charAt(i) < 'a' || str.charAt(i + 1) > 'z' || str.charAt(i + 1) < 'a')
				continue;
			String temp = str.substring(i, i + 2);
			// 맵에 값이 없으면, 0 + 1, 있으면, 값 + 1
			map.put(temp, map.getOrDefault(temp, 0) + 1);
		}
	}
}