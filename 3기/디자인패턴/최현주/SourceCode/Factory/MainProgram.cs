using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace FactoryMethodPattern
{
    public class MainProgram : MonoBehaviour
    {
        void Start()
        {
            Debug.Log("---------- Easy Map 몬스터 생성 ----------");
            EnemyGenerator easyGenerator = new EasyMapGenerator();
            easyGenerator.CreateEnemys();

            Debug.Log("---------- Hard Map 몬스터 생성 ----------");
            EnemyGenerator hardGenerator = new HardMapGenerator();
            hardGenerator.CreateEnemys();
        }
    }
}