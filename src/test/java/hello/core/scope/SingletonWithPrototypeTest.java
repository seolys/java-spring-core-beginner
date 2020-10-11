package hello.core.scope;

import static org.assertj.core.api.Assertions.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class SingletonWithPrototypeTest {

	@Test
	void prototypeFind() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
		PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
		prototypeBean1.addCount();
		assertThat(prototypeBean1.getCount()).isEqualTo(1);

		PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
		prototypeBean2.addCount();
		assertThat(prototypeBean2.getCount()).isEqualTo(1);
	}

	@Test
	void singletonClientUsePrototype() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

		ClientBean clientBean1 = ac.getBean(ClientBean.class);
		int count1 = clientBean1.logic(); // 깨끗한 PrototypeBean의 addCount가 호출됨. 0 -> 1
		assertThat(count1).isEqualTo(1);

		ClientBean clientBean2 = ac.getBean(ClientBean.class);
		int count2 = clientBean2.logic(); // 이미 사용된 PrototypeBean의 addCount가 호출됨. 1 -> 2
		assertThat(count2).isEqualTo(2);

		assertThat(clientBean1.getPrototypeBean()).isSameAs(clientBean2.getPrototypeBean());
	}

	@Scope("singleton")
	@RequiredArgsConstructor
	static class ClientBean {

		private final PrototypeBean prototypeBean; // PrototypeBean을 스프링컨테이너가 자동주입 및 SingletonBean 필드에 저장.(Thread-Safe 하지않음)

		public int logic() {
			prototypeBean.addCount();
			return prototypeBean.getCount();
		}

		public PrototypeBean getPrototypeBean() {
			return prototypeBean;
		}
	}

	@Scope("prototype")
	static class PrototypeBean {

		private int count = 0;

		public void addCount() {
			count++;
		}

		public int getCount() {
			return count;
		}

		@PostConstruct
		public void init() {
			System.out.println("PrototypeBean.init " + this);
		}

		@PreDestroy
		public void destroy() {
			System.out.println("PrototypeBean.destroy");
			throw new RuntimeException("prototype 어차피 호출안됨.");
		}

	}
}
