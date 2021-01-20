package spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("spring");
        context.refresh();
        StartUI ui = context.getBean(StartUI.class);
        ui.askStr();
    }
}
