package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

	@Test
	@DisplayName("모든 빈 출력하기")
	void findBean() {
		String[] beanDefinitionNames = context.getBeanDefinitionNames();
		for (String beanDefinitionName : beanDefinitionNames) {
			Object bean = context.getBean(beanDefinitionName);
			System.out.println("beanDefinitionName = " + beanDefinitionName + ", object = " + bean);
		}
	}

	@Test
	@DisplayName("애플리케이션 빈 출력하기")
	void findApplicationBean() {
		String[] beanDefinitionNames = context.getBeanDefinitionNames();
		for (String beanDefinitionName : beanDefinitionNames) {
			BeanDefinition beanDefinition = context.getBeanDefinition(beanDefinitionName);
//			if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
			if (beanDefinition.getRole() == BeanDefinition.ROLE_INFRASTRUCTURE) {
				System.out.println("beanDefinitionName = " + beanDefinitionName + ", object = " + beanDefinition);
			}
		}
	}

}
