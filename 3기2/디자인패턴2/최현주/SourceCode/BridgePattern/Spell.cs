using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace BridgePattern.ex3
{
    public class Spell : IWeapon
    {
        public void Use()
        {
            Debug.Log("마법을 건다");
        }
    }
}
