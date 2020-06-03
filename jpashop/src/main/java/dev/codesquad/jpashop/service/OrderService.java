package dev.codesquad.jpashop.service;

import dev.codesquad.jpashop.domain.Delivery;
import dev.codesquad.jpashop.domain.DeliveryStatus;
import dev.codesquad.jpashop.domain.Item;
import dev.codesquad.jpashop.domain.Member;
import dev.codesquad.jpashop.domain.Order;
import dev.codesquad.jpashop.domain.OrderItem;
import dev.codesquad.jpashop.repository.MemberRepository;
import dev.codesquad.jpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

  private final MemberRepository memberRepository;
  private final OrderRepository orderRepository;
  private final ItemService itemService;

  /** 주문 */
  @Transactional
  public Long order(Long memberId, Long itemId, int count) {

    //엔티티 조회
    Member member = memberRepository.findOne(memberId);
    Item item = itemService.findOne(itemId);

    //배송정보 생성
    Delivery delivery = new Delivery();
    delivery.setAddress(member.getAddress());
    delivery.setStatus(DeliveryStatus.READY);

    //주문상품 생성
    OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

    //주문 생성
    Order order = Order.createOrder(member, delivery, orderItem);

    //주문 저장
    orderRepository.save(order);
    return order.getId();
  }

  //** 주문 취소 */
  @Transactional
  public void cancelOrder(Long orderId) {

    //주문 엔티티 조회
    Order order = orderRepository.findOne(orderId);

    //주문 취소
    order.cancel();
  }
}
