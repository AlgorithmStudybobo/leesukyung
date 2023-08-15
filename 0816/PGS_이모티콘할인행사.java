package com.ssafy.exam;

class Solution {
    //변수 static으로 다올림
    static int picked[], discount[], answer[], user[][], emoticon[];
    public int[] solution(int[][] users, int[] emoticons) {
        user = users;
        emoticon = emoticons;
        //answer[0] 이모티콘 플러스 사용자수 갱신
        //answer[1] 이모티콘 플러스 사용자 수가 똑같을 때 금액이 이전보다 크면 갱신
        answer = new int[]{0, 0};
        discount = new int[]{10, 20, 30, 40};
        picked = new int[emoticons.length];
        perm(0);
        return answer;
    }
    //10, 20, 30, 40으로 이모티콘 길이까지 중복순열생성
    private static void perm(int cnt){
        //이모티콘 개수까지 다 뽑았을 때
        if(cnt == emoticon.length){
            int plus = 0, price = 0;
            for(int i = 0; i < user.length; i++){
                //한명의 유저 구매 비용
                int userPrice = 0;
                for(int j = 0; j < emoticon.length; j++){
                    //할인율이 각 유저의 할인율 기준보다 크거나 같을때
                    if(picked[j] >= user[i][0]){
                        //금액 계산해서 더함
                        userPrice += emoticon[j]*(100-picked[j])/100;
                    }
                }
                //각 유저 금액합이 그 유저의 이모티콘플러스 구매 기준보다 높으면 plus++
                if(userPrice>=user[i][1]){
                    plus++;
                }else{
                    //아니면 그냥 더해줌
                    price+=userPrice;
                }
                //answer 배열 갱신
                if(plus>answer[0]){
                    answer[0]=plus;
                    answer[1]=price;
                }else if(plus==answer[0]){
                    if(answer[1]<price)
                        answer[1]=price;
                }
            }
            return;
        }
        
        //중복순열 생성 부분
        for(int i = 0; i < 4; i++){
            picked[cnt] = discount[i];
            perm(cnt + 1);
        }
    }
}
