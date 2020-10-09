package hello.core.discount;

import hello.core.discount.annotation.FixDiscount;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.stereotype.Component;

@Component
@FixDiscount
public class FixDiscountPolicy implements DiscountPolicy {

	private int discountFixAmount = 1000;

	@Override
	public int discount(Member member, int prices) {
		if (member.getGrade() == Grade.VIP) {
			return discountFixAmount;
		}
		return 0;
	}
}
