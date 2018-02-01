using System.Collections;
using System.Collections.Generic;
using UnityEngine;
namespace BridgePattern.ex3
{
    public class Lux : Character
    {
        public Lux(IWeapon weapon) : base(weapon)
        {
            this.weapon = weapon;
            Debug.Log("*** 마력을 부리는 캐릭터 ***");
        }

        public override void Attack()
        {
            weapon.Use();
        }
    }
}