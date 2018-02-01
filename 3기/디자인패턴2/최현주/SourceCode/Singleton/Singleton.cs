using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Singleton<T> where T : class, new() {

    private static T instance;

    private static Object lockObj = new Object();

    public static T Instance
    {
        get
        {
            if (instance == null)
            {
                // 다중 쓰레드 환경일 때 Lock이 필요함.
                // 하나의 쓰레드만 접근할 수 있도록 lock을 걸어준다.
                lock (lockObj)
                {
                    instance = new T();
                }
            }

            return instance;
        }
    }
}
