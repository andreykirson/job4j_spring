package spring;

import org.springframework.stereotype.Component;
import spring.ConsoleInput;

@Component
public class StartUI {

    private ConsoleInput consoleInput;

    public StartUI(ConsoleInput consoleInput) {
        this.consoleInput = consoleInput;
    }

    public void askStr() {
        consoleInput.askStr();
    }
}
