package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class StartUI {

    @Autowired
    private ConsoleInput consoleInput;


    public void askStr() {
        consoleInput.askStr();
    }
}
