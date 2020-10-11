package hello.core.scope;

import static org.assertj.core.api.Assertions.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

/**
 * javax.inject 활용(JAVA표준).
 */
public class SingletonWithPrototypeTest3 {

	@Test
	void singletonClientUsePrototype() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

		ClientBean clientBean1 = ac.getBean(ClientBean.class);
		PrototypeBean prototypeBean1 = clientBean1.logic(); // 깨끗한 PrototypeBean의 addCount가 호출됨. 0 -> 1
		assertThat(prototypeBean1.getCount()).isEqualTo(1);

		ClientBean clientBean2 = ac.getBean(ClientBean.class);
		PrototypeBean prototypeBean2 = clientBean2.logic(); // 깨끗한 PrototypeBean의 addCount가 호출됨. 0 -> 1
		assertThat(prototypeBean2.getCount()).isEqualTo(1);

		assertThat(prototypeBean1).isNotSameAs(prototypeBean2);
	}

	@Scope("singleton")
	@RequiredArgsConstructor
	static class ClientBean {

		private final Provider<PrototypeBean> prototypeBeanProvider; // javax.inject.Provider

		public PrototypeBean logic() {
			PrototypeBean prototypeBean = prototypeBeanProvider.get();
			prototypeBean.addCount();
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
