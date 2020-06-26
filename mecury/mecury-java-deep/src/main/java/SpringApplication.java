import com.galaxy.mecury.entity.NBAPlayer;
import com.galaxy.mecury.java.deep.config.AppConfig;
import com.galaxy.mecury.java.deep.service.NBAPlayerService;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;
import java.util.ArrayList;
import java.util.List;



public class SpringApplication {
    public static void generator() throws Exception {

        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        //指定逆向工程配置文件
        File configFile = new File("D:\\codes\\springboot\\mecury\\mecury-java-deep\\src\\main\\resources\\db\\generator.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config =cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,
            callback, warnings);
        myBatisGenerator.generate(null);
    }

    public static void main(String[] args) throws Exception {
//        generator();
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();

//        for (String name: context.getBeanDefinitionNames()) {
//            System.out.println(name);
//        }

        NBAPlayerService nbaPlayerService = context.getBean(NBAPlayerService.class);
//        List<NBAPlayer> playerList = nbaPlayerService.getAll();
//        for (NBAPlayer player : playerList) {
//            System.out.println(player);
//        }
        NBAPlayer nbaPlayer = new NBAPlayer();
        nbaPlayer.setCode("test008");
        nbaPlayer.setCountry("中国");
        nbaPlayer.setCountryEn("CHINA");
        nbaPlayerService.insert(nbaPlayer);

        context.close();
    }
}