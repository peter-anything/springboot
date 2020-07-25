import com.galaxy.mecury.java.deep.components.circle.CircleEntityA;
import com.galaxy.mecury.java.deep.components.circle.CircleEntityB;
import com.galaxy.mecury.java.deep.config.AppConfig;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Injector;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class SpringApplication {
    public static void generator() throws Exception {

        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;


        File configFile = new File("D:\\codes\\springboot\\mecury\\mecury-java-deep\\src\\main\\resources\\db\\generator.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,
                callback, warnings);
        myBatisGenerator.generate(null);
    }

    public static void main(String[] args) throws Exception {
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
//        context.register(AppConfig.class);
//        context.refresh();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(classLoader.toString());
        System.out.println(classLoader.getParent().toString());
        System.out.println(classLoader.getParent().getParent());
        Injector.findLoadedClass
    }

    public static void perm(char[] chars, ArrayList result) {
        if (result.size() == 5) {
            return;
        }

        for (int i = 0; i < chars.length; i++) {
            result.add(chars[i]);
            for (int j = i + 1; j < chars.length; j++) {
                swap(chars, i, j);
                swap(chars, i, j);
            }
        }
    }

    public static void swap(char[] chars, int i, int j) {
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }
}
