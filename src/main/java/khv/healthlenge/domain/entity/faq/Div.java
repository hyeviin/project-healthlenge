package khv.healthlenge.domain.entity.faq;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Div {
	BEST("자주물어보는질문", "best"),
	METHOD("방식/규칙", "method"),
	ETC("기타", "etc"),
	PRICE("참가비/환급/상금", "price"),
	CARD("하트/옐로우/레드카드", "card"),
	MEMBER("회원가입 및 탈퇴", "member"),
	HEALTHLENGE("개설", "healthlenge"),
	FUNCTION("기능/오류", "function"),
	COMPANY("헬린저스 소개", "company");
	
	final String title;
	final String url;
}
