using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using System;

namespace MementoPattern.ex2
{ 
    public class MainProgram : MonoBehaviour {

        void Main()
        {
            //Originator 객체(Player) 생성.
            Player player = new Player();

            player.Level = 10;
            player.HP = 500;
            player.PlayTime = DateTime.Parse("2017-08-07");

            //CareTaker를 통해서 메멘토 객체를 저장한다.
            PlayerCareTaker careTaker = new PlayerCareTaker();
            careTaker.AddMemento(player.SaveDataMemento());

            //캐릭터가 성장
            player.Level = 35;
            player.HP = 2100;
            player.PlayTime = DateTime.Parse("2017-09-15");

            careTaker.AddMemento(player.SaveDataMemento());

            //캐릭터가 성장
            player.Level = 50;
            player.HP = 3400;
            player.PlayTime = DateTime.Now;

            //CareTaker를 통해서 Player의 데이터를 저장된 메멘토 복원시킨다. 
            player.RestoreDataMemento(careTaker.GetLatestData());
        }
    }
}