package algo;

import java.util.*;
import java.util.Map.*;
/*
    TreeMap : key값으로 자동정렬
*/
class PGS_주차요금계산 {
    public int[] solution(int[] fees, String[] records) {
        TreeMap<String, Integer> out = new TreeMap<>();
        HashMap<String, Integer> in = new HashMap<>();
        //레코드 확인
        for(int i = 0; i<records.length;i++){
            StringTokenizer st = new StringTokenizer(records[i]);
            String t = st.nextToken();
            String carNum = st.nextToken();
            String state = st.nextToken();
            //받은 시간을 분으로 변경
            int m = Integer.parseInt(t.substring(0,2))*60+Integer.parseInt(t.substring(3,5));
            //입차했을 때 in Map에 넣어줌
            if(state.equals("IN")){
                in.put(carNum,m);
            //출차했을 때
            }else{
                //이전 출차 기록이 남아있지 않으면
                if(out.get(carNum)==null){
                    //현재 시간(분)에서 입차시간(분)빼기
                    out.put(carNum,m-in.get(carNum));
                //출차 기록이 남아있으면
                }else{
                    //이전 value값에 현재시간(분)-입차시간(분) 뺀 값을 더해줌
                    out.replace(carNum, out.get(carNum)+m-in.get(carNum));
                }
                //주차 시간 계산된 차는 in Map에서 제거
                in.remove(carNum);
            }
        }
        //출차 기록이 없는 차들(입차기록만 존재)
        //23:59분에 출차된 것으로 간주
        int last = 23*60+59;
        //in Map 처음부터 돌면서
        for(Entry<String, Integer> entry:in.entrySet()){    
            String carNum = entry.getKey();
            //출차 기록 있으면 출차 시간 넣어주고
            if(out.get(carNum)==null){
                    out.put(carNum,last-in.get(carNum));
            //없으면 그전 기록에 더해주기
            }else{
                out.replace(carNum, out.get(carNum)+last-in.get(carNum));
            }
        }
        //out Map이 이미 정렬된 상태이기 때문에 순서대로 answer배열에 주차요금 계산해서 저장
        int idx = 0;
        int[] answer = new int[out.size()];
        for(Entry<String, Integer> entry:out.entrySet()){
            //기본 요금
            answer[idx]=fees[1];
            // 주차시간에서 기본시간 뺀 값이 0 이하이면 기본요금 아니면 추가요금 계산
            int temp = out.get(entry.getKey()) - fees[0];
            if(temp>0){
                answer[idx]+=temp%fees[2]==0?(temp/fees[2])*fees[3]:(temp/fees[2]+1)*fees[3];
            }
            idx++;
        }
        return answer;
    }
}