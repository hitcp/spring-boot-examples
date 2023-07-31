import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"config"})
@SpringBootApplication
public class JavaSocketApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaSocketApplication.class, args);
    }
}
