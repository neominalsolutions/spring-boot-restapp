package com.mertalptekin.springbootrestapp;

import com.mertalptekin.springbootrestapp.springContext.commit.CommitService;
import com.mertalptekin.springbootrestapp.springContext.commit.ICommit;
import com.mertalptekin.springbootrestapp.springContext.custom.MyCustomBean;
import com.mertalptekin.springbootrestapp.springContext.logger.DemoService;
import com.mertalptekin.springbootrestapp.springContext.logger.ILogger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class SpringBootRestappApplication {

    public static void main(String[] args) {
       ApplicationContext context = SpringApplication.run(SpringBootRestappApplication.class, args);
        runBeanSample(context);
    }

    // Manuel , Config dosyasından Manuel Bean tanımı
    @Bean
    public String getAppName() { // Bean isimleri method ismi ile ayni olur
        return "Spring Boot Rest App";
    }

    public static   void runBeanSample(ApplicationContext context) {
        String name =  context.getBean("getAppName1",String.class); // Retrieve the bean to demonstrate it's working
        System.out.println("Bean " + name);

        DemoService demoService = context.getBean(DemoService.class);
        demoService.test();
        demoService.test();

        // Sass bazlı a tenancy istiyor db logger üzerinden proje çalışsın, b tenenacy text logger üzerinden proje çalışsın istiyor. Parametre göndererek dinamik Spring Context bean çözümlemek için.
        Scanner scanner = new Scanner(System.in);
        System.out.print("Write log type db or text : ");
        String LogType = scanner.nextLine();
        String loggerBeanName = LogType.equalsIgnoreCase("db") ? "dbLogger" : "textLogger";

        //Logger Bean kullanımı
        // Hangi servisten çalışacak.
        // Polimorfizm ile IoC bean çözümleme
        ILogger logger = context.getBean(loggerBeanName,ILogger.class);
        logger.log("Hello World");

        // Eğer Bean name kullanmıyorsak @Primary ile birincil bean tanımı yapılabilir.
        ICommit commit = context.getBean(ICommit.class);
        commit.commitChanges(); // Primary olduğundan CacheCommit çalışır.


        CommitService commitService1 = context.getBean(CommitService.class);
        commitService1.save(); // DbCommit çalışıtırdık.

        // Custom Registration Bean Kullanımı
        MyCustomBean myCustomBean = context.getBean(MyCustomBean.class);
        System.out.println("customBean Def:" + myCustomBean.getName());
    }

}
