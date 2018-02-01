using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace FactoryMethodPattern
{
    public class Boss : Enemy
    {
        public Boss()
        {
            type = EnemyType.Boss;

            name = "보스 몬스터";
            hp = 1000;
            exp = 85;

            Debug.Log(GetLog());
        }
    }
}