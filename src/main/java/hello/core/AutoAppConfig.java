package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
//		basePackages = "hello.core",
//		basePackageClasses = AutoAppConfig.class,
		excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Configuration.class) // 기존예제코드를 유지하기위해..
)
public class AutoAppConfig {

	// Bean이 겹치면 스프링부트 실행 시 에러가 발생한다.
//	@Bean(name = "memoryMemberRepository")
//	public MemoryMemberRepository memberRepository() {
//		return new MemoryMemberRepository();
//	}

}
