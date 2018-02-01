using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace ChainOfResponsibilityPattern.ex2
{
    /// <summary>
    /// 대출 요청시 필요한 데이터 정의
    /// </summary>
    public struct LoanRequest
    {
        public string Customer { get; set; }
        public int Amount { get; set; }
    }

    /// <summary>
    /// Handler 추상화 클래스
    /// </summary>
    public abstract class RequestHandler
    {
        protected RequestHandler successor { get; set; }

        public string Name { get; set; }

        /// <summary>
        /// 다음 링크(객체)를 구현한다. (선택적 메소드)
        /// </summary>
        /// <param name="successor"></param>
        public void SetSuccessor(RequestHandler successor)
        {
            this.successor = successor;
        }

        /// <summary>
        /// 각 클래스별 요청을 처리하는 세부 기능 구현
        /// 조건에 해당하지 않으면 다음 객체에 요청을 전달하는 기능.
        /// </summary>
        /// <param name="request"></param>
        public abstract void HandlerRequest(LoanRequest request);
    }
}