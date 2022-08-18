/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fcmb.com.good.controller;

import fcmb.com.good.dto.CustomerResponse;
import fcmb.com.good.dto.OrderRequest;
import fcmb.com.good.dto.OrderResponse;
import fcmb.com.good.exception.ResourceNotFoundException;
import fcmb.com.good.model.Customer;
import fcmb.com.good.model.Products;
import fcmb.com.good.service.AppService;
import io.swagger.annotations.ApiOperation;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author USER
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class OrderController {

    @Autowired
    AppService service;

    @PostMapping("/placeOrder")
    @ApiOperation(value = "Endpoint for adding new customer to database", response = String.class)
    public Customer placeOrder(@RequestBody OrderRequest request) {
        return service.getCustomerRepository().save(request.getCustomer());
    }

    @GetMapping("/findOrders")
    @ApiOperation(value = "Endpoint for finding customer from database", response = String.class)
    public List<Customer> findOrders() {
        return service.getCustomerRepository().findAll();
    }

    @GetMapping("/getInfo")
    @ApiOperation(value = "Endpoint for finding selected column from each table from database", response = String.class)
    public List<OrderResponse> getinformation() {
        return service.getCustomerRepository().getinformation();
    }

    @GetMapping("/findProducts")
    @ApiOperation(value = "Endpoint for finding Lists of Products from database", response = String.class)
    public List<Products> findProducts() {
        return service.getProductsRepository().findAll();
    }

    @GetMapping("/customer")
    @ApiOperation(value = "Endpoint for retrieving lists of customers", response = CustomerResponse.class, responseContainer = "List")
    public ResponseEntity getListOfCustomers() {
        try {
            List<Customer> sr = service.getCustomerRepository().findAll();
            List<CustomerResponse> srr = new ArrayList<>();
            SimpleDateFormat ft = new SimpleDateFormat("yyyy/mm/dd HH:mm:ss a");
            for (Customer rs : sr) {
                CustomerResponse load = new CustomerResponse();

                load.setId(rs.getId());
                load.setName(rs.getName());
                load.setEmail(rs.getEmail());
                load.setGender(rs.getGender());

                srr.add(load);
            }
            return ResponseEntity.ok(srr);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("Internal Server Error");
        }
    }

    @GetMapping("/customer/{id}")
    @ApiOperation(value = "Endpoint for fetching customer by id from database", response = String.class)
    public ResponseEntity<Customer> getCustomerById(@PathVariable(value = "id") Long customerId)
            throws ResourceNotFoundException {
        Customer user = service.getCustomerRepository().findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/customer/{id}")
    @ApiOperation(value = "Endpoint for updating customer by id from database", response = String.class)
    public ResponseEntity<Customer> updateCustomer(@PathVariable(value = "id") Long customerId,
            @RequestBody Customer rs) throws ResourceNotFoundException {
        Customer load = service.getCustomerRepository().findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));

        load.setName(rs.getName());
        load.setEmail(rs.getEmail());
        load.setGender(rs.getGender());

        // load.setDatecreated(new Date());
        final Customer updatedUser = service.getCustomerRepository().save(load);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/customer/{id}")
    @ApiOperation(value = "Endpoint for deleting customer by id from database", response = String.class)
    public Map<String, Boolean> deleteCustomer(@PathVariable(value = "id") Long customerId)
            throws ResourceNotFoundException {
        Customer user = service.getCustomerRepository().findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("customer not found for this id :: " + customerId));

        service.getCustomerRepository().delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Customer deleted Successfully", Boolean.TRUE);
        return response;
    }

}
