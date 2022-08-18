/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fcmb.com.good.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author USER
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Customer {
    
    @Id
    @GeneratedValue
    
    private Long id;
    private String name;
    private String email;
    private String gender;
    
    @OneToMany(targetEntity = Products.class,cascade = CascadeType.ALL)
    @JoinColumn(name="cp_fk",referencedColumnName ="id")
    private List<Products> products;
    
    
}
