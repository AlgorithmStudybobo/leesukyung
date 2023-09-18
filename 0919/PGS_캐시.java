package algo;

import java.util.*;
/*
    약 30분
    set -> 어떤게 최근에 들어갔는지 확인 불가
    queue -> 어떤게 최근에 들어갔는지는 확인 가능하나 중간에 있는 값을 뺐다가 다시 넣을 수 없음
    list -> 한쪽 방향으로 삽입하므로 최근에 들어갔는지 파악 가능, 중간값도 삽입 삭제 가능
    
*/
class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        //캐시가 0인 경우, 힛이 일어날 수 없음
        if(cacheSize==0){
            return 5*cities.length;
        }
        //리스트를 캐시로 쓸 것
        List<String> cache = new ArrayList<>();
        //도시 처음부터 확인하면서
        for(int i = 0; i< cities.length;i++){
            //대소문자 섞여있어서 소문자로 통일
            String city = cities[i].toLowerCase();
            //힛이 일어나는지 확인
            int idx = cache.indexOf(city);
            //미스
            if(idx<0){
                answer+=5;
                //캐시가 꽉차있을 때 가장 오래된 것=리스트 가장 앞에 있는 값을 빼고 새 값 넣어줌
                if(cache.size()==cacheSize)
                    cache.remove(0);
                cache.add(city);
            //힛
            }else{
                answer++;
                //힛이 일어난 값을 빼고 맨 뒤(가장 최근)에 재삽입
                cache.add(cache.remove(idx));
            }
        }
        return answer;
    }
}