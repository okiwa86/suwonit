using UnityEngine;

namespace ChainOfResponsibilityPattern.ex2
{
    public class Manager : RequestHandler
    {
        public override void HandlerRequest(LoanRequest request)
        {
            //조건에 만족하면 해당 클래스에서 아래 작업을 처리.
            if (request.Amount < 10000)
            {
                Debug.Log(string.Format("[승인 완료] {0}에게 대출 완료 - 담당자 {1}", request.Customer, this.Name));
            }
            else if (successor != null) //만족하지 못하면 다음 객체에 요청사항을 전달.
            {
                Debug.Log(string.Format("[승인 거부] 담당자 변경 - {1}", request.Customer, this.Name));

                successor.HandlerRequest(request);
            }
        }
    }
}