package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary//제일 자주쓰는 방법
//Primary보다 Qualifier이 우선 순위가 더 높음
//Spring은 좁은 범위의 선택권이 우선 순위가 더 높음
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else{
            return 0;
        }
    }
}
