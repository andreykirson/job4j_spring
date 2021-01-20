package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StartUI {

    @Autowired
    private ConsoleInput consoleInput;


    public void askStr() {
        consoleInput.askStr();
    }
}
