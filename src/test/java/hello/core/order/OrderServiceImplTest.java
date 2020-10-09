package hello.core.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Test;

class OrderServiceImplTest {

	/**
	 * 생성자주입을 사용해야하는 이유 1.
	 * 생성자 주입을 사용 시, 주입받아야할 항목을 바로 확인할 수 있음.
	 * 주입자체를 생략하려고하면 컴파일오류가 발생하기때문에 사전에 확인할 수 있음.
	 * null을 주입시 오류가 날수있겠다 라는것을 개발자가 인지할수있음.
	 */
	@Test
	void createOrderNullPointExceptionTest() {
		OrderServiceImpl orderService = new OrderServiceImpl(null, null);

		assertThrows(NullPointerException.class,
				() -> orderService.createOrder(1L, "itemA", 10000));
	}

	/**
	 * 생성자주입을 사용해야하는 이유 2.
	 * 생성자 주입을 사용 시, Mock객체를 삽입할 수 있고, final 필드변수로 만들어서 값변경을 제한시킬수 있음.
	 */
	@Test
	void createOrderTest() {
		MemoryMemberRepository memberRepository = new MemoryMemberRepository();
		memberRepository.save(new Member(1L, "name", Grade.VIP));

		OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());
		Order order = orderService.createOrder(1L, "itemA", 10000);
		assertThat(order.getDiscountPrice()).isEqualTo(1000);
	}

}