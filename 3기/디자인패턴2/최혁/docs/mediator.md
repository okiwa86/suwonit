# Mediator 중재자 패턴
- [Mediator 중재자 패턴](#mediator-%EC%A4%91%EC%9E%AC%EC%9E%90-%ED%8C%A8%ED%84%B4)
    - [왜쓸까](#%EC%99%9C%EC%93%B8%EA%B9%8C)
    - [등장인물](#%EB%93%B1%EC%9E%A5%EC%9D%B8%EB%AC%BC)
    - [예제 소스](#%EC%98%88%EC%A0%9C-%EC%86%8C%EC%8A%A4)
        - [Mediator](#mediator)
        - [Colleague](#colleague)
        - [Concrete Colleague](#concrete-colleague)
        - [Concrete Mediator](#concrete-mediator)
        - [Main](#main)
## 왜쓸까

![예제](https://i.gyazo.com/e4d318a385cde961ad92d13298ca6fe9.gif)

위와 같이 로그인 화면 등에, 이벤트마다 연계되어 각 오브젝트들이 enable disable 되는 등 연계되는 프로그램을 짠다고 가정할때, 적용하기 좋은패턴이다.

가장 간단하게 생각이 드는건, 모든 이벤트마다 그 후 동작을 작성하는것일거다.

1. Guest CheckBox 클래스안에서 선택이벤트에 text field 모두 disabled, backgroundcolor 변경, Ok Button 활성화

1. Login Checkbox 클래스안에서 선택이벤트에 id textFeild enabled, backgroundcolor 변경 등등등
1. ...
1. ...

이렇게 따로 작성하다보면, 나중에 테스트 시, 클래스를 하나하나 들여다 보며 로직을 찾아야 한다.

이렇게 복잡하게 엮이지 않고, 중간에 중재자를 한명두고, 중재자를 믿고 이벤트를 수행하도록 작성하도록 하는 패턴이다.

중재자에게 복잡한 로직을 위임하고 통신을 중재자를 통해서 할 때,

자기의 본 업무에 집중 할 수 있고(loose coupling), 로직을 점검할때도 용이 할 수 있다.

책에서 나온 장점.

1. 분산이 화를 부를때

    Concrete Colleague 통신 기능분산이 화를 자초한다.

1. 통신 경로의 증가

| 오브젝트  | 통신 경우의 수 |
| :---: | :------: |
| 2개    | 2가지      |
| 3개    | 6가지      |
| 4개    | 12가지     |
| 5개    | 20가지     |
| 6개    | 30가지     |

1. ConcreteColleague 의존성이 낮아 재이용이 용이하다.(Concrete Mediator는 재이용 힘듬)

## 등장인물

![클래스 다이어그램](https://upload.wikimedia.org/wikipedia/commons/e/e4/Mediator_design_pattern.png)

Mediator(중재자) - interface 정의 / Colleague와 커뮤니케이션

Concrete Mediator - Mediator 인터페이스를 구현하고 Colleague 오브젝트 간의 통신을 조정합니다. 상호 커뮤니 케이 션에 관해서 모든 Colleague와 그 목적을 알고 있습니다.

Colleague(동료?) - interface 정의

Concrete Colleague - interface구현(서로 통신할 객체)

## 예제 소스

### Mediator

```java
    public interface Mediator {
        public abstract void createColleagues(); // colleague 구성 메소드
        public abstract void colleagueChanged(); // colleague들이 호출하는 메소드
        // 예제에선, Concrete Colleague 들이 변경되면, 위 메소드를 실행한다.
        // 이 메소드를 실행하면, enable적용 로직을 처음부터 수행한다.
    }
```

### Colleague

```java
    public interface Colleague {
        public abstract void setMediator(Mediator mediator); // 중개인을 지정
        public abstract void setColleagueEnabled(boolean enabled); // 중개인이 호출 시, 각자의 Enable 처리할 메소드
    }
```

### Concrete Colleague

Button,CheckBox,TextField를 구현

awt 구현에 관련한건 공부할 필요 x 디자인패턴만 집중해서보면

private Mediator mediator;

내 중개자가 누구인지 알고 있다.

setColleagueEnabled(boolean enabled)

true / false 가 오면, 그에 해당하는 각자의 disable enable 메소드로 적용

각자 이벤트 메소드()

mediator.colleagueChanged(); 를 호출해, 자신이 변경됨을 알려, 로직을 수행하도록 호출한다.

```java
    import java.awt.Button;
    import java.awt.HeadlessException;

    public class ColleagueButton extends Button implements Colleague {

        private Mediator mediator;

        public ColleagueButton(String caption) {
            super(caption);
        }

        @Override
        public void setMediator(Mediator mediator) {
            this.mediator = mediator;
        }

        @Override
        public void setColleagueEnabled(boolean enabled) {
            // Mediator 에서 유효 / 무효 지시
            setEnabled(enabled);
        }

    }
```

```java
    import java.awt.Checkbox;
    import java.awt.CheckboxGroup;
    import java.awt.event.ItemEvent;
    import java.awt.event.ItemListener;

    public class ColleagueCheckBox extends Checkbox implements ItemListener, Colleague {

        private Mediator mediator;

        public ColleagueCheckBox(String caption, CheckboxGroup group, boolean state) {
            super(caption,group,state);
        }

        @Override
        public void setMediator(Mediator mediator) {
            this.mediator = mediator;
        }

        @Override
        public void setColleagueEnabled(boolean enabled) {
            setEnabled(enabled);
        }

        @Override
        public void itemStateChanged(ItemEvent e) {
            mediator.colleagueChanged();
        }
    }
```

```java
    import java.awt.Color;
    import java.awt.TextField;
    import java.awt.event.TextEvent;
    import java.awt.event.TextListener;

    public class ColleagueTextField extends TextField implements TextListener, Colleague{

        private Mediator mediator;

        public ColleagueTextField(String text, int columns) {
            super(text, columns);
        }

        @Override
        public void setMediator(Mediator mediator) {
            this.mediator = mediator;
        }

        @Override
        public void setColleagueEnabled(boolean enabled) {
            setEnabled(enabled);
            setBackground(enabled ? Color.white : Color.lightGray);
        }

        @Override
        public void textValueChanged(TextEvent e) {
            mediator.colleagueChanged();
        }
    }
```

### Concrete Mediator

LoginFrame awt 프레임박스를 만들어 놓고, 로직을 중재한다.

```java
    import java.awt.CheckboxGroup;
    import java.awt.Color;
    import java.awt.Frame;
    import java.awt.GridLayout;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;

    public class LoginFrame extends Frame implements ActionListener, Mediator {

        private ColleagueCheckBox checkGuest;
        private ColleagueCheckBox checkLogin;
        private ColleagueTextField textUser;
        private ColleagueTextField textPassword;
        private ColleagueButton buttonOk;
        private ColleagueButton buttonCancel;

        public LoginFrame(String title) {
            // Frame 생성 (안중요)
            super(title);
            setBackground(Color.lightGray);
            setLayout(new GridLayout(4, 2));

            createColleagues(); // colleague 생성(new, mediator setting, listner setting)
            add(checkGuest);
            add(checkLogin);
            add(textUser);
            add(textPassword);
            add(buttonOk);
            add(buttonCancel);
            pack();
            colleagueChanged(); // 중개 로직 동작
            setVisible(true); // 책에선 show() show는 deplecate 된 method라서 java1.8에 맞게 변경
        }

        @Override
        public void createColleagues() { // awt 생성 (안중요)
            // 생성
            CheckboxGroup g = new CheckboxGroup();
            checkGuest = new ColleagueCheckBox("Guest", g, true);
            checkLogin = new ColleagueCheckBox("Login", g, false);
            textUser = new ColleagueTextField("", 10);
            textPassword = new ColleagueTextField("", 10);
            textPassword.setEchoChar('*');

            buttonOk = new ColleagueButton("OK");
            buttonCancel = new ColleagueButton("Cancel");

            // Mediator 구성
            checkGuest.setMediator(this);
            checkLogin.setMediator(this);
            textUser.setMediator(this);
            textPassword.setMediator(this);
            buttonOk.setMediator(this);
            buttonCancel.setMediator(this);

            // Listener 구성
            checkGuest.addItemListener(checkGuest);
            checkLogin.addItemListener(checkLogin);
            textUser.addTextListener(textUser);
            textPassword.addTextListener(textPassword);
            buttonOk.addActionListener(this);
            buttonCancel.addActionListener(this);
        }

        @Override
        public void colleagueChanged() { // 중개 로직!
            if(checkGuest.getState()) {
                textUser.setColleagueEnabled(false);
                textPassword.setColleagueEnabled(false);
                buttonOk.setColleagueEnabled(true);
            } else {
                textUser.setColleagueEnabled(true);
                userpassChanged(); // userId text box 입력 시, password , ok , cancel 버튼 enable 설정
            }
        }

        private void userpassChanged() {
            if (textUser.getText().length() > 0) {
                textPassword.setColleagueEnabled(true);
                if (textPassword.getText().length() > 0) {
                    buttonOk.setColleagueEnabled(true);
                }else {
                    buttonOk.setColleagueEnabled(false);
                }
            } else {
                textPassword.setColleagueEnabled(false);
                buttonOk.setColleagueEnabled(false);
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) { // 버튼 클릭 시, 이벤트 (종료)
            System.out.println(e.toString());
            System.exit(0);
        }
    }
```

### Main

```java
    public class Main {
        public static void main(String[] args) {
            new LoginFrame("Mediator Sample"); // awt 실행됨.
        }
    }
```