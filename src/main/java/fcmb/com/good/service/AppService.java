/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fcmb.com.good.service;

import fcmb.com.good.repo.CustomerRepository;
import fcmb.com.good.repo.ProductsRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
@Service
@Data
public class AppService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductsRepository productsRepository;
}
