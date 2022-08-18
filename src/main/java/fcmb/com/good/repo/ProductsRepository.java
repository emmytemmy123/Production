/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fcmb.com.good.repo;

import fcmb.com.good.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 *
 * @author USER
 */
@CrossOrigin(origins = "http://localhost:4200")
public interface ProductsRepository extends JpaRepository<Products,Long> {

//Page<Products> findByCustomerId(@Param("id") Long id, Pageable pageable);
    
}
