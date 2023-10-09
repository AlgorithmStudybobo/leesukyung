import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PGS_귤고르기 {
	public int solution(int k, int[] tangerine) {
        int answer = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int t:tangerine){
            map.put(t,map.getOrDefault(t,0)+1);
        }
        Integer[] arr = map.values().toArray(new Integer[0]);
		Arrays.sort(arr, Collections.reverseOrder());
        int cnt = 0;
        for(int i = 0; i<arr.length;i++){
            answer++;
            cnt+=arr[i];
            if(cnt>=k)break;
        }
        return answer;
    }
}
