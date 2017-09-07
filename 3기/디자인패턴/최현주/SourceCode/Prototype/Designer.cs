
namespace PrototypePattern
{
    public class Designer : IEmployee
    {
        public string Name { get; set; }
        public string Role { get; set; }
        public string Tool { get; set; }

        public IEmployee Clone()
        {
            return (IEmployee)MemberwiseClone();
        }

        public string GetDetailInfo()
        {
            return string.Format("Name : {0}, Role : {1}, Tool : {2}", Name, Role, Tool);
        }
    }
}
