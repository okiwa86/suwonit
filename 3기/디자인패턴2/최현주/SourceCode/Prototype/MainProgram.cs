using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace PrototypePattern
{
    public class MainProgram : MonoBehaviour
    {
        void Start()
        {
            Developer dev = new Developer();
            dev.Name = "Sam";
            dev.Role = "Team Leader";
            dev.langauge = "C++";

            Developer copyDev = dev.Clone() as Developer;
            copyDev.Name = "Jenny";
            copyDev.Role = "Programmer";

            Debug.Log(dev.GetDetailInfo());
            Debug.Log(copyDev.GetDetailInfo());

            Designer designer = new Designer();
            designer.Name = "Tom";
            designer.Role = "Designer ";
            designer.Tool = "PhotoShop";

            Designer copyDesigner = designer.Clone() as Designer;
            copyDesigner.Tool = "Grace";
            copyDesigner.Tool = "illust";

            Debug.Log(designer.GetDetailInfo());
            Debug.Log(copyDesigner.GetDetailInfo());
        }
    }
}