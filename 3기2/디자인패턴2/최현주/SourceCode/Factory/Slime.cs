using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace FactoryMethodPattern
{
    public class Slime : Enemy
    {
        public Slime()
        {
            type = EnemyType.Slime;

            name = "슬라임";
            hp = 200;
            exp = 15;

            Debug.Log(GetLog());
        }
    }
}
