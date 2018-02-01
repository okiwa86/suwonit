using UnityEngine;

namespace FactoryMethodPattern
{
    public enum EnemyType
    {
        Slime,
        Boss,
    }

    public abstract class Enemy
    {
        protected string name;

        protected EnemyType type;

        protected int hp;
        protected int exp;

        protected void Attack()
        {
            Debug.Log("공격한다.");
        }

        protected string GetLog()
        {
            return string.Format("{0} 등장 !! ", name);
        }
    }
}