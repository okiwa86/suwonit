
namespace PrototypePattern
{
    public class Developer : IEmployee
    {
        public string Name { get; set; }
        public string Role { get; set; }
        public string langauge { get; set; }

        public IEmployee Clone()
        {
            return (IEmployee)MemberwiseClone();
        }

        public string GetDetailInfo()
        {
            return string.Format("Name : {0}, Role : {1}, langauge : {2}", Name, Role, langauge);
        }
    }
}
