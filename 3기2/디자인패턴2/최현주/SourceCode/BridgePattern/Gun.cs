using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace BridgePattern.ex3
{
    public class Gun : IWeapon
    {
        public void Use()
        {
            Debug.Log("총을 쏜다");
        }
    }
}
