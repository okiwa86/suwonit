using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace BridgePattern.ex3
{
    public class Jiggs : Character
    {
        public Jiggs(IWeapon weapon) : base(weapon)
        {
            this.weapon = weapon;
            Debug.Log("*** 폭탄 던지는 캐릭터 ***");
        }

        public override void Attack()
        {
            weapon.Use();
        }
    }
}    