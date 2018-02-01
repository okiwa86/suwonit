using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace CommandPattern.ex4
{
    /// <summary>
    /// 클라이언트 객체.
    /// </summary>
    public class MainProgram : MonoBehaviour
    {
        void Main()
        {
            //작업을 요청하기 위해 Invoker 생성.
            DrawingInvoker drawing = new DrawingInvoker();

            //원을 그린다. (3,5)
            CircleReceiver circleReceiver = new CircleReceiver(3, 5);
            drawing.Execute(new DrawCircleCommand(circleReceiver));

            //네모를 그린다. (123)
            RectReceiver rectReceiver = new RectReceiver(12, 3);
            drawing.Execute(new DrawRectCommand(rectReceiver));

            //네모를 지운다. (12,3)
            drawing.Execute(new EraseRectCommand(rectReceiver));

            //네모를 그린다. (12,3)
            drawing.Execute(new DrawRectCommand(rectReceiver));

            //원을 그린다. (1,1)
            drawing.Execute(new DrawCircleCommand(new CircleReceiver(1, 1)));

            Debug.Log("==========================");

            drawing.Undo(); //원을 지우고 (1,1)
            drawing.Undo(); //네모를 지우고(12,3)
            drawing.Undo(); //네모를 그리고(12,3)

            drawing.Execute(new DrawRectCommand(rectReceiver));
        }
    }
}