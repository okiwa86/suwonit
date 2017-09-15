namespace AbstractFactoryPattern.ex2
{
    // *** 도우 *****
    public interface IDough {
        string ToString();
    }

    public class ThinCrustDough : IDough {
        public string ToString() {
            return "얇은 반죽 도우";
        }
    }

    public class ThinkCrustDough : IDough {
        public string ToString() {
            return "매우 두꺼운 반죽 도우";
        }
    }

    // *** 소스 *****
    public interface ISauce {
        string ToString();
    }

    public class MarinaraSauce : ISauce {
        public string ToString() {
            return "마리나라 소스";
        }
    }

    public class TomatoSauce : ISauce {
        public string ToString() {
            return "토마토 소스";
        }
    }

    // *** 치즈 *****
    public interface ICheese {
        string ToString();
    }

    public class CheddarCheese : ICheese {
        public string ToString() {
            return "체다 치즈";
        }
    }

    public class MozzarellaCheese : ICheese {
        public string ToString() {
            return "모짜렐라 치즈";
        }
    }
}