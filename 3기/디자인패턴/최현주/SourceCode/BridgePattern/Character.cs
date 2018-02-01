using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace BridgePattern.ex3
{
    public abstract class Character 
    {
        //실제 Bridge 역할?
        protected IWeapon weapon;

        // 캐릭터는 무기를 사용하는 것이기 때문에 
        // 무기에 대한 정보를 받는다.
        public Character(IWeapon weapon)
        {
            this.weapon = weapon;
        }

        public abstract void Attack();
    }
}