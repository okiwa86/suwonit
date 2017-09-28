using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace CompositePattern.ex2
{
    public class MainProgram : MonoBehaviour
    {
        void Start()
        {
            Directory rootDir = new Directory("root");

            Directory binDir = new Directory("bin");
            Directory tempDir = new Directory("temp");
            Directory userDir = new Directory("user");

            rootDir.Add(binDir);
            rootDir.Add(tempDir);
            rootDir.Add(userDir);

            binDir.Add(new File("bin_file_1", 100));
            binDir.Add(new File("bil_file_2", 200));

            rootDir.Print();

            Debug.Log("==================");

            Directory KimDir = new Directory("Kim");
            Directory ChoiDir = new Directory("Choi");
            Directory ShinDir = new Directory("Shin");

            userDir.Add(KimDir);
            userDir.Add(ChoiDir);
            userDir.Add(ShinDir);

            KimDir.Add(new File("data.xml", 50));
            KimDir.Add(new File("composite.cs", 100));

            ChoiDir.Add(new File("r&d.doc", 200));
            ChoiDir.Add(new File("bae_platform.pptx", 150));

            ShinDir.Add(new File("clip.mat", 400));

            rootDir.Print();
        }
    }
}