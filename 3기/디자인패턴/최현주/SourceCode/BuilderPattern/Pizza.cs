
namespace BuilderPattern.ex1
{
    /// <summary>
    /// Builder를 이용해서 Director가 만들어낸 객체.
    /// </summary>
    public class Pizza
    {
        private string dough = string.Empty;
        private string sauce = string.Empty;
        private string topping = string.Empty;

        public void SetDough(string dough) {
            this.dough = dough;
        }

        public void SetSauce(string sauce) {
            this.sauce = sauce;
        }

        public void SetTopping(string topping) {
            this.topping = topping;
        }
    }
}