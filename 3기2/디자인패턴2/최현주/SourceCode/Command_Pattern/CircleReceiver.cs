using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace CommandPattern.ex4
{
    /// <summary>
    /// Receiver 
    /// 실제 작업을 처리하는 객체
    /// 작업 내용을 알고 있는 객체.
    /// </summary>
    public class CircleReceiver
    {
        private int x, y;

        public CircleReceiver(int x, int y)
        {
            this.x = x;
            this.y = y;
        }

        public void Draw()
        {
            Debug.Log(string.Format("원을 그립니다. ({0}, {1})", x, y));
        }

        public void Erase()
        {
            Debug.Log(string.Format("원을 지웁니다. ({0}, {1})", x, y));
        }
    }
}