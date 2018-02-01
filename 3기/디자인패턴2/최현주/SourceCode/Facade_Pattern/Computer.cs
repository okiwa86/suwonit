using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace FacadePattern.ex2
{
    /// <summary>
    /// Facade 클래스 역할
    /// 각 부품들을 작동시키기 위해 필요한 부품들을 가지고 있는다.
    /// </summary>
    public class Computer
    {
        private Bios bios;
        private CPU cpu;
        private Memory memory;
        private HDD hdd;

        public Computer()
        {
            bios = new Bios();
            cpu = new CPU();
            memory = new Memory();
            hdd = new HDD();
        }

        //필요한 여러 부품들을 작동시킨다.
        public void Booting()
        {
            Debug.Log("== 컴퓨터 부팅 시작 ==");

            bios.Operation();
            cpu.Operation();
            memory.Operation();
            hdd.Operation();
        }
    }
}