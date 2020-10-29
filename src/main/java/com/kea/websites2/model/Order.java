package com.kea.websites2.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "orders", schema = "heroku_5debde97bb52f42")
public class Order {
    private int id;
    private Product productsByProductsId;
    private Customer customersByCustomersId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "products_id", referencedColumnName = "id", nullable = false)
    public Product getProductsByProductsId() {
        return productsByProductsId;
    }

    public void setProductsByProductsId(Product productsByProductsId) {
        this.productsByProductsId = productsByProductsId;
    }

    @ManyToOne
    @JoinColumn(name = "customers_id", referencedColumnName = "id", nullable = false)
    public Customer getCustomersByCustomersId() {
        return customersByCustomersId;
    }

    public void setCustomersByCustomersId(Customer customersByCustomersId) {
        this.customersByCustomersId = customersByCustomersId;
    }
}
