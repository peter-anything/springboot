import com.mecury.spring.Student;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.mecury.spring")
public class SpringLearn {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(SpringLearn.class);
        context.refresh();
        Student stu = context.getBean(Student.class);
        System.out.println(stu);
    }
}
