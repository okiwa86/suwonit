import java.util.ArrayList;

import anonymous.MessageBox;
import anonymous.UnderlinePen;
import framework.Manager;
import framework.Product;

public class Main {

  public static void main(String[] args) {
    // ready
    Manager manager = new Manager();
    UnderlinePen uPen = new UnderlinePen('~');
    MessageBox mBox = new MessageBox('*');
    MessageBox sBox = new MessageBox('/');

    // manager's HashMap field put
    manager.register("strong message", uPen);
    manager.register("warning box", mBox);
    manager.register("slash box", sBox);

    // create
    String hello = "Hello world";
    Product p1 = manager.cloneFromRegister("strong message");
    p1.use(hello);
    Product p2 = manager.cloneFromRegister("warning box");
    p2.use(hello);
    Product p3 = manager.cloneFromRegister("slash box");
    p3.use(hello);
    Product p4 = manager.cloneFromRegister("warning box");
    p4.use("yap");

    Product p5 = manager.cloneFromRegister("warning box");
    p5.use("olleh");

    p2.use("olah olah");

    if (p2 == p3) {
      System.out.println("p2 == p3 is same instance");
    } else {
      System.out.println("p2 != p3 is different instance");
      MessageBox m2 = (MessageBox) p2;
      MessageBox m3 = (MessageBox) p3;
      if (m2.getDecochar() == m3.getDecochar()) {
        System.out.println("\nField copy by clone()");
        System.out.println("m2.decochar == m3.decochar = " + m2.getDecochar());
      } else {
        System.out.println("\nField copy by clone()");
        System.out.println(
            "m2.decochar != m3.decochar = " + m2.getDecochar() + " != " + m3.getDecochar() + "\n");
      }
    }
    if (p2 == p4) {
      System.out.println("p2 == p4 is same instance");
    } else {
      System.out.println("p2 != p4 is different instance");
      MessageBox m2 = (MessageBox) p2;
      MessageBox m4 = (MessageBox) p4;
      if (m2.getDecochar() == m4.getDecochar()) {
        System.out.println("\nField copy by clone()");
        System.out.println(
            "m2.decochar == m3.decochar = " + m2.getDecochar() + " == " + m4.getDecochar() + "\n");
      }
    }
  }
}
