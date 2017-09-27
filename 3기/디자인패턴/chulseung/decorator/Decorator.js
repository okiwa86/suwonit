/**
 * 기존 클래스를 포장하거나 확장 
 * 기존 컴포넌트의 서브클래싱의 대안
 * Adapter나 Bridge와 비슷 
 * 다른 인스턴스를 래핑하고 프록시처럼 호출된다.
 * 인스턴스를 래핑하여 전달하여 런타임 시에 리다이렉션을 수행 
 * 장식자는 통로 역할을 하며 일부내용을 수정하여 전달
 * 전달되는 파라미터를 변경하거나 래핑된 인스턴스를 호출하기 전에 추가적인 동작을 수행
 */
var Westeros;
(function (Westeros) {
    (function (Armor) {
        var BasicArmor = (function () {
            function BasicArmor() {
            }
            BasicArmor.prototype.CalculateDamageFromHit = function (hit) {
                return hit.Strength * .2;
            };
            BasicArmor.prototype.GetArmorIntegrity = function () {
                return 1;
            };
            return BasicArmor;
        })();
        Armor.BasicArmor = BasicArmor;

        var ChainMail = (function () {
            function ChainMail(decoratedArmor) {
                this.decoratedArmor = decoratedArmor;
            }
            ChainMail.prototype.CalculateDamageFromHit = function (hit) {
                hit.Strength = hit.Strength * .8;
                return this.decoratedArmor.CalculateDamageFromHit(hit);
            };
            ChainMail.prototype.GetArmorIntegrity = function () {
                return .9 * this.decoratedArmor.GetArmorIntegrity();
            };
            return ChainMail;
        })();
        Armor.ChainMail = ChainMail;

        var Hit = (function () {
            function Hit() {
            }
            return Hit;
        })();
        Armor.Hit = Hit;
    })(Westeros.Armor || (Westeros.Armor = {}));
    var Armor = Westeros.Armor;
})(Westeros || (Westeros = {}));

var armor = new Westeros.Armor.ChainMail(new Westeros.Armor.BasicArmor());
console.log(armor.CalculateDamageFromHit({ Location: "head", Weapon: "Sock filled with pennies", Strength: 12 }));
