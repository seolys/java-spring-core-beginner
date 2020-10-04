package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.stereotype.Component;

@Component
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
