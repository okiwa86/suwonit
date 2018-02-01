using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace BridgePattern.ex3
{
    public class MainProgram : MonoBehaviour
    {
        void Start()
        {
            IWeapon gun = new Gun();
            IWeapon bomb = new Bomb();

            Character soldier = new Soldier(gun);
            soldier.Attack();

            Character jiggs = new Jiggs(bomb);
            jiggs.Attack();
        }
    }
}
