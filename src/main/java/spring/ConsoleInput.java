package spring;

import org.springframework.stereotype.Component;
import java.util.Scanner;


@Component
public class ConsoleInput {

    private Scanner scanner = new Scanner(System.in);
    String str = "";

    public void askStr() {
      while(!str.equals("Exit")) {
          System.out.println("Type Exit to stop the app or type anything : ");
          str = scanner.nextLine();
          System.out.println("You typed :" + str);
      }

    }

}
