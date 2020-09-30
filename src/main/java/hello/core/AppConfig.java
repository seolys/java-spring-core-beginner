package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

/**
 * 역할과 구현 클래스가 한눈에 들어올 수 있도록 한다.
 * 애플리케이션 전체 구성이 어떻게 되어있는지 빠르게 파악할 수 있다.
 */
public class AppConfig {

	public MemberService memberService() {
		return new MemberServiceImpl(memberRepository()); // 생성자 주입(Constructor Injection).
	}

	public OrderService orderService() {
		return new OrderServiceImpl(memberRepository(), discountPolicy());
	}

	private MemberRepository memberRepository() {
		return new MemoryMemberRepository();
	}

	private DiscountPolicy discountPolicy() {
		return new FixDiscountPolicy();
	}

}
