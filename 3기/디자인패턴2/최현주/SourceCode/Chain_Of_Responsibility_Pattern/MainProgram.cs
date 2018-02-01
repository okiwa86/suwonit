using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace ChainOfResponsibilityPattern.ex2
{
    public class MainProgram : MonoBehaviour
    {
        void Start()
        {
            //요청 사항을 생성.
            List<LoanRequest> requestList = new List<LoanRequest>();
            requestList.Add(new LoanRequest() { Amount = 800, Customer = "지니" });
            requestList.Add(new LoanRequest() { Amount = 2500, Customer = "헤르미온느" });
            requestList.Add(new LoanRequest() { Amount = 9600, Customer = "말포이" });
            requestList.Add(new LoanRequest() { Amount = 200000, Customer = "해리포터" });

            //Chain 생성
            //요청사항을 처리할 Handler 객체들을 생성한다. 
            RequestHandler cashier = new Cashier() { Name = "김사원, Cachier"};
            RequestHandler teamLeader = new TeamLeader() { Name = "박팀장, TeamLeader" };
            RequestHandler manager = new Manager() { Name = "하점장, Manager" };

            //Handler 객체들의 고리 순서 설정(연쇄)한다.
            //클라이언트 입장에서 Chain는 자유자재로 변경이 가능.
            cashier.SetSuccessor(teamLeader);
            teamLeader.SetSuccessor(manager);

            for(int i=0; i<requestList.Count; i++) {
                cashier.HandlerRequest(requestList[i]);
            }
        }
    }
}