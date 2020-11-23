package com.kea.websites2;

import com.kea.websites2.model.Customer;
import com.kea.websites2.model.Product;
import com.kea.websites2.repository.CustomerRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerIntegrationTests {
    @Autowired
    CustomerRepo repo;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    public void init() {repo.deleteAll();}

    @Test
    public void should_find_no_customers() {
        List<Customer> customerList = repo.findAll();
        assertThat(customerList).isEmpty();
    }

    @Test
    public void should_save_customer() {
        Customer customer = repo.save(new Customer("test", "testLn", "test@gmail.com));
        assertThat(customer).hasFieldOrPropertyWithValue("firstName", "test");
        assertThat(customer).hasFieldOrPropertyWithValue("lastName", "testLn");
        assertThat(customer).hasFieldOrPropertyWithValue("email", "test@gmail.com");
    }


    @Test
    public void should_find_all_customers() {
        repo.save(new Customer("test1", "testLn1", "test1@gmail.com"));
        repo.save(new Customer("test2", "testLn2", "test2@gmail.com"));
        repo.save(new Customer("test3", "testLn3", "test3@gmail.com"));
        List<Customer> customerList = repo.findAll();
        assertThat(customerList).hasSize(3);
    }

    @Test
    public void should_find_customer_by_id() {
        Customer c1 = new Customer("test1", "testLn1", "test1@gmail.com");
        Customer c2 = new Customer("test2", "testLn2", "test2@gmail.com");
        entityManager.persist(c1);
        entityManager.persist(c2);

        Product foundProduct = repo.findById(c2.getId()).get();
        assertThat(foundProduct).isEqualTo(c2);
    }


    @Test
    public void should_update_customer() {
        Customer c = new Customer("test1", "testLn1", "test1@gmail.com");
        entityManager.persist(c);

        Customer updatedC = new Customer("updated", "updated", "updated@gmail.com");

        //simulate what happens in the controller
        Customer _c = repo.findById(c.getId()).get();
        _c.setFirstName(updatedC.getFirstName());
        _c.setLastName(updatedC.getLastName());
        _c.setEmail(updatedC.getEmail());
        repo.save(_c);

        Customer checkC = repo.findById(c.getId()).get();
        assertThat(checkC.getId()).isEqualTo(c.getId());
        assertThat(checkC.getFirstName()).isEqualTo(updatedC.getFirstName());
        assertThat(checkC.getLastName()).isEqualTo(updatedC.getLastName());
        assertThat(checkC.getEmail()).isEqualTo(updatedC.getEmail());

    }

    @Test
    public void should_delete_by_id() {
        Customer c1 = new Customer("test1", "testLn1", "test1@gmail.com");
        Customer c2 = new Customer("test2", "testLn2", "test2@gmail.com");
        Customer c3 = new Customer("test3", "testLn3", "test3@gmail.com");
        entityManager.persist(c1);
        entityManager.persist(c2);
        entityManager.persist(c3);

        repo.deleteById(c3.getId());

        List<Customer> customerList = repo.findAll();

        assertThat(customerList).hasSize(2);
        assertThat(customerList).contains(c1, c2);
    }
}
