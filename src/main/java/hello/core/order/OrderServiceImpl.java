package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

	private final MemberRepository memberRepository;
	private final DiscountPolicy discountPolicy;

	// 같은타입의 빈이 여러개가 있어서 충돌나는 경우, 변수명(필드명)과 구현체명을 맞춰주면 스프링이 판단하여 주입해준다.
	@Autowired private DiscountPolicy rateDiscountPolicy;

	// 같은타입의 빈이 여러개가 있어서 충돌나는 경우, 파라메터 과 구현체명을 맞춰주면 스프링이 판단하여 주입해준다.
//	@Autowired
//	public OrderServiceImpl(
//			MemberRepository memberRepository,
//			DiscountPolicy rateDiscountPolicy
//	) {
//		this.memberRepository = memberRepository;
//		this.discountPolicy = rateDiscountPolicy;
//	}

	// 같은타입의 빈이 여러개가 있어서 충돌나는 경우, @Qualifier로 매칭시켜서 선택주입할 수 있다..
//	@Autowired
//	public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
//		this.memberRepository = memberRepository;
//		this.discountPolicy = discountPolicy;
//	}

	@Autowired
	public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
		this.memberRepository = memberRepository;
		this.discountPolicy = discountPolicy;
	}

	@Override
	public Order createOrder(Long memberId, String itemName, int itemPrice) {
		Member member = memberRepository.findById(memberId);
		int discountPrice = discountPolicy.discount(member, itemPrice);

		return new Order(memberId, itemName, itemPrice, discountPrice);
	}

	public MemberRepository getMemberRepository() {
		return memberRepository;
	}

}
