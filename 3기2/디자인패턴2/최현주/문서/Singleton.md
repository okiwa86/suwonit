# Singleton Pattern    

<br />

## 1. Singleton Pattern 정의
- 하나의 인스턴스만 생성되는 클래스
- 전역 범위에서 접근이 가능함

## 2. UML Diagram
![uml](http://java.boot.by/ocpjp7-upgrade/images/020101.gif)

## 3. Singleton의 사용 이유

- 하나의 클래스에서만 정보가 유지되기를 원할 때 사용

## 4. 장/단점

#### [장점]
- 인스턴스를 한번만 생성하므로, 메모리 관리가 효율적이다.

#### [단점]
- 전역 클래스이기 때문에 여러 클래스와 Coupling이 된다.
- 멀티쓰레드일 경우 locking에 신경을 써야한다.

## 5. 코드 설명

#### [일반적인 방법]

~~~~
	public class MonoSingleton : MonoBehaviour {

    private static MonoSingleton instance;

    public static MonoSingleton Instance
    {
        get
        {
            if(instance == null)
            {
                instance = FindObjectOfType<MonoSingleton>();
            }

            if(instance == null)
            {
                instance = new MonoSingleton();
                GameObject obj = new GameObject("SingtonObj");
                instance = obj.AddComponent<MonoSingleton>();
            }
            return instance;
        }
    }
}
~~~~
<br />

#### [Generic을 사용한 방법] - 다중 쓰레드 환경에서 안전한 방법

~~~~
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
~~~~

#### [Generic Singleton 사용 방법]

~~~~
/// <summary>
/// 연산자 Manager 클래스.
/// 모든 연산에 대한 것을 관리.
/// </summary>
public class OperatorMgr : Singleton<OperatorMgr> {

    private int resultVal = 0;

    public void AddValue(int a)
    {
        resultVal += a;
    }

    public void Print()
    {
        Debug.Log("Result : " + resultVal);
    }
}
~~~~
#### [Main 클래스]

~~~~

public class TestMain : MonoBehaviour {

	void Start () {
        OperatorMgr.Instance.AddValue(30);
        OperatorMgr.Instance.AddValue(50);

        OperatorMgr.Instance.Print();
    }
}
~~~~