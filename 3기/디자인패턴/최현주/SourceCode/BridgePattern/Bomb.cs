using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace BridgePattern.ex3
{
    public class Bomb : IWeapon
    {
        public void Use()
        {
            Debug.Log("폭탄을 던진다");
        }
    }
}
