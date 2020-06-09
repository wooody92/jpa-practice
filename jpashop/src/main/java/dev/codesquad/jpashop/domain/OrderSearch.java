package dev.codesquad.jpashop.domain;

import lombok.Getter;

@Getter
public class OrderSearch {

  private String memberName;    //회원 이름
  private OrderStatus orderStatus; //주문 상태[ORDER, CANCEL]
}
