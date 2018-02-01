# Facade 패턴

이 패턴만 4년내내 써와서... 책을 훑어보고 이해한 유일한 패턴...

어쨋든 facade라는 의미는 불어로 건물의 정면이라는 의미라고 합니다.

책에서는 창구 라는 표현을 많이 쓰고 있네요.

창구라는 표현이 적절한듯 합니다.

![UML](https://upload.wikimedia.org/wikipedia/commons/9/96/W3sDesign_Facade_Design_Pattern_UML.jpg)

## 왜쓸까

디자인패턴하면서 매번 쓰는 표현인것 같지만...

고객은 내부사정을 알 필요가 없습니다.

그냥 컴퓨터 켜줘! 하면 컴퓨터가 알아서 켜져야 한다는거죠.

프로그래머가 겪을만한 상황으로 적어보면,

웹개발자, 서버개발자가 분리되어 있다고 할때,

웹개발자는 서버개발자가 만들어놓은 method 하나만 띡! 호출하면 그만입니다.

서버프로그래머에게 '이거 결과가 a가 아니라 b가 나와요' 하면 그만이죠.
(현실은 둘다 혼자 개발...)

온갖 method를 가지고 return받아서 브라우저에서 작업해서 보여줄 필요는 없습니다.

복잡한 로직들은 서버에서 처리하고, 웹에선 적당한 곳에 보여주면 됩니다.

그래야 웹과 서버간의 핑퐁하면서 테스트 해볼 필요도 적어지고, 검증도 쉽죠.

## 코드로보자

책에 있는 예제보다, wiki에 있는 예제가 딱 알맞아 보여서 가져왔습니다.

```java
    / Complex parts */

    class CPU {
        public void freeze() { ... }
        public void jump(long position) { ... }
        public void execute() { ... }
    }

    class Memory {
        public void load(long position, byte[] data) {
            ...
        }
    }

    class HardDrive {
        public byte[] read(long lba, int size) {
            ...
        }
    }

    /* Façade */

    class Computer {
        public void startComputer() {
            CPU cpu = new CPU();
            Memory memory = new Memory();
            HardDrive hardDrive = new HardDrive();
            cpu.freeze();
            memory.load(BOOT_ADDRESS, hardDrive.read(BOOT_SECTOR, SECTOR_SIZE));
            cpu.jump(BOOT_ADDRESS);
            cpu.execute();
        }
    }

    /* Client */

    class You {
        public static void main(String[] args) throws ParseException {
            Computer facade = /* grab a facade instance */;
            facade.startComputer();
        }
    }
```

회사에서 쓰던 예제를 설명드리면, 웹 화면에서 facade method를 호출합니다.

```
서버측 구조

BI (facde용 interface)
FACDE(BI에 있는 내용 구현)
VAS(조회로직) , FAS(저장로직)
VDAO(조회쿼리), DDAO(저장쿼리)

DTO(파라미터 통 or DB 테이블 구조)
```

FACDE에는 단순히 VAS 호출 정도만 있습니다.

VAS에 들어가면, VAS에서 들어온 파라미터를 가지고 여기저기 VDAO에 접근해 쿼리를 읽어 취합한 결과를 return합니다.

이런 구조라면 FACDE에서는 호출할 method정도만 가지고 있고,
복잡한 로직은 VAS에서 관리하니, FACADE를 수정하거나 할 일이 거의 없습니다.

쓰면서 느낀 단점은

1. 아주 쉬운 쿼리도 BI,FACDE,VAS,VDAO,DTO에 각각 만들어주어야 한다
1. 이미 다 구현되어 있으면, 너무나 편해서 개발자가 공부할 필요가 없어 나태해진다
1. VAS/FAS 는 OOP적인 사고로 짜기 어려워진다. -> 미친듯이 복잡해진다.(좀더 유연하게 다른 패턴을 적용할 수 있는 구조여야...)
