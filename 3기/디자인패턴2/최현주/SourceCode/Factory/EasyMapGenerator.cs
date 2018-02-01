using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace FactoryMethodPattern
{
    public class EasyMapGenerator : EnemyGenerator
    {
        public override void CreateEnemys()
        {
            enemyList.Add(new Slime());
            enemyList.Add(new Slime());
            enemyList.Add(new Slime());
            enemyList.Add(new Boss());
        }
    }
}
