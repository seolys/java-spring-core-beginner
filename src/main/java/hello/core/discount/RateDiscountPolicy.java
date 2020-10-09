package hello.core.discount;

import hello.core.discount.annotation.RateDiscount;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.stereotype.Component;

@Component
@RateDiscount
//@Primary // 같은타입의 빈이 여러개 있을때, @Primary가 붙은 Bean이 우선권을 갖는다.
public class RateDiscountPolicy implements DiscountPolicy {

	private int discountPercent = 10;

	@Override
	public int discount(Member member, int price) {
		if (!isVIP(member)) {
			return 0;
		}
		return price * discountPercent / 100;
	}

	private boolean isVIP(Member member) {
		return member.getGrade() == Grade.VIP;
	}
}
