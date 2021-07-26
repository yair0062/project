package app.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2

public class ProjectCopounApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ProjectCopounApplication.class, args);
//		 ConfigurableApplicationContext ctx =
//		Test t = ctx.getBean(Test.class);
//		t.test();
//
//		System.out.println("Shutdown in 15 seconds");
//		Thread.sleep(15_000);
//		((ConfigurableApplicationContext) ctx).close();
	}

}
