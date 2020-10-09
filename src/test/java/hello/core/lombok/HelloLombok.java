package hello.core.lombok;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Getter
@Setter
@ToString
public class HelloLombok {

	private String name;
	private int age;

	@Test
	@DisplayName("lombok테스트")
	void lombokTest() {
		HelloLombok lombok = new HelloLombok();
		lombok.setName("hi");
		lombok.setAge(10);
		System.out.println("lombok.toString() = " + lombok.toString());

		assertThat(lombok.getName()).isEqualTo("hi");
	}
}
