using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace BridgePattern.ex3
{
    public class Soldier : Character
    {
        public Soldier(IWeapon weapon) : base(weapon)
        {
            this.weapon = weapon;
            Debug.Log("***총 쏘는 캐릭터 ***");
        }

        // Attack 내부에서 무기를 Use.
        public override void Attack()
        {
            weapon.Use();
        }
    }
}