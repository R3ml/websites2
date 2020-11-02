package com.kea.websites2;

import com.kea.websites2.model.Product;
import com.kea.websites2.repository.ProductRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductControllerTest {
    @Autowired
    ProductRepo repo;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    public void init() {repo.deleteAll();}

    @Test
    public void should_find_no_products() {
        List<Product> productList = repo.findAll();
        assertThat(productList).isEmpty();
    }
}
