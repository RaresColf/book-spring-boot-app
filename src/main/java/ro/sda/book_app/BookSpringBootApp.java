package ro.sda.book_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class BookSpringBootApp {

    public static void main(String[] args) {
//        SpringApplication.run(BookSpringBootApp.class,args);

        Optional<String> stringOpt = Optional.of("abc");

        if(stringOpt.isPresent()){
            String unWrapped = stringOpt.get();
            System.out.println(unWrapped);
        }

        Optional<String> emptyOpt = Optional.empty();

        if(emptyOpt.isPresent()){
            String unWrapped = emptyOpt.get();
            System.out.println(emptyOpt);
        }

        if(emptyOpt.isEmpty()){
//            String unWrapped = emptyOpt.get();
            System.out.println("Optional empty");
        }

        Optional<String> optOfNull = Optional.ofNullable(null);

        if(optOfNull.isPresent()) {
            System.out.println("Present");
        }
    }
}
