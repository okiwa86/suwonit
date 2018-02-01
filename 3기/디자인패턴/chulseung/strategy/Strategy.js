//전략패턴은 어떤 전략으로도 교체할 수 있는 동일한 인터페이스를 구현한다.
//1. 정적 하드코딩 
//2. 데이터 세트 분석 후 적합 전략 선택 + 실패시 fallback 제공
//3. progressive enhancement: 가장 빠르지만 부정확 알고리즘 실행 동시에 가장 느린 알고리즘을 실행하고 완료되었을때 더 정확한 결과로 교체
//4. 무작위 전략 선택: 두개 전략 성능 비교

var Westeros;
(function (Westeros) {
    (function (Travel) {
        var TravelResult = (function () {
            function TravelResult(durationInDays, probabilityOfDeath, cost) {
                this.durationInDays = durationInDays;
                this.probabilityOfDeath = probabilityOfDeath;
                this.cost = cost;
            }
            return TravelResult;
        })();
        Travel.TravelResult = TravelResult;

        var SeaGoingVessel = (function () {
            function SeaGoingVessel() {
            }
            SeaGoingVessel.prototype.Travel = function (source, destination) {
                return new TravelResult(15, .25, 500);
            };
            return SeaGoingVessel;
        })();
        Travel.SeaGoingVessel = SeaGoingVessel;

        var Horse = (function () {
            function Horse() {
            }
            Horse.prototype.Travel = function (source, destination) {
                return new TravelResult(30, .25, 50);
            };
            return Horse;
        })();
        Travel.Horse = Horse;

        var Walk = (function () {
            function Walk() {
            }
            Walk.prototype.Travel = function (source, destination) {
                return new TravelResult(150, .55, 0);
            };
            return Walk;
        })();
        Travel.Walk = Walk;
    })(Westeros.Travel || (Westeros.Travel = {}));
    var Travel = Westeros.Travel;
})(Westeros || (Westeros = {}));

var currentMoney = 70;
var strat;
if (currentMoney > 500)
    strat = new Westeros.Travel.SeaGoingVessel();
else if (currentMoney > 50)
    strat = new Westeros.Travel.Horse();
else
    strat = new Westeros.Travel.Walk();
