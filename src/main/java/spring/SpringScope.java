package spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringScope {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("spring");
        context.refresh();
        Store store = context.getBean(Store.class);
        store.add("Jho Biden");
        Store another = context.getBean(Store.class);
        another.getAll().forEach(System.out::println);
    }
}
