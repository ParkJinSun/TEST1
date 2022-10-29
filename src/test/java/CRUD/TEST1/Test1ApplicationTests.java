package CRUD.TEST1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class Test1ApplicationTests {

	@Test
	void contextLoads() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		String[] beanDefinitionNames = ac.getBeanDefinitionNames();
	}


}
