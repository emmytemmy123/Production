/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fcmb.com.good.repo;

import fcmb.com.good.dto.OrderResponse;
import fcmb.com.good.model.Customer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author USER
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT new fcmb.com.good.dto.OrderResponse (c.id,p.image, c.name,c.email,c.gender, p.productName,p.qty, p.price) FROM Customer c JOIN c.products p ")
    public List<OrderResponse> getinformation();


}
