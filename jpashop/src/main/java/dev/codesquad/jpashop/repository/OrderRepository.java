package dev.codesquad.jpashop.repository;

import dev.codesquad.jpashop.domain.Order;
import dev.codesquad.jpashop.domain.OrderSearch;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

  private final EntityManager em;

  public void save(Order order) {
    em.persist(order);
  }

  public Order findOne(Long id) {
    return em.find(Order.class, id);
  }

  public List<Order> findAll(OrderSearch orderSearch) {
    // 검색 로직
    // JPQL
    // JPA Criteria
    // ..
    return new ArrayList<>();
  }
}
