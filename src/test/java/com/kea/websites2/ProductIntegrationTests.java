package com.kea.websites2;

import com.kea.websites2.model.Product;
import com.kea.websites2.repository.ProductRepo;
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
public class ProductIntegrationTests {
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

    @Test
    public void should_save_product() {
        Product product = repo.save(new Product("n1", 100.0, "Homepage", "test", "test"));
        assertThat(product).hasFieldOrPropertyWithValue("name", "n1");
        assertThat(product).hasFieldOrPropertyWithValue("price", 100.0);
        assertThat(product).hasFieldOrPropertyWithValue("type", "Homepage");
        assertThat(product).hasFieldOrPropertyWithValue("description", "test");
        assertThat(product).hasFieldOrPropertyWithValue("imgUrl", "test");
    }

    @Test
    public void should_find_all_products() {
        repo.save(new Product("n1", 100.0, "test", "test", "test"));
        repo.save(new Product("n2", 100.0, "test", "test", "test"));
        repo.save(new Product("n3", 100.0, "test", "test", "test"));

        List<Product> productList = repo.findAll();
        assertThat(productList).hasSize(3);
    }

    @Test
    public void should_find_product_by_id() {
        Product p1 = new Product("p1", 100.0, "test", "test", "test");
        Product p2 = new Product("p2", 100.0, "test", "test", "test");
        entityManager.persist(p1);
        entityManager.persist(p2);

        Product foundProduct = repo.findById(p2.getId()).get();

        assertThat(foundProduct).isEqualTo(p2);
    }

    @Test
    public void should_update_product() {
        Product p = new Product("p1", 100.0, "test1", "test1", "test1");
        entityManager.persist(p);

        Product updatedP = new Product("up1", 101.0, "utest1", "utest1", "utest1");

        //simulate what happens in the controller
        Product _p = repo.findById(p.getId()).get();
        _p.setName(updatedP.getName());
        _p.setPrice(updatedP.getPrice());
        _p.setType(updatedP.getType());
        _p.setDescription(updatedP.getDescription());
        _p.setImgUrl(updatedP.getImgUrl());
        repo.save(_p);

        Product checkP = repo.findById(p.getId()).get();
        assertThat(checkP.getId()).isEqualTo(p.getId());
        assertThat(checkP.getName()).isEqualTo(updatedP.getName());
        assertThat(checkP.getPrice()).isEqualTo(updatedP.getPrice());
        assertThat(checkP.getDescription()).isEqualTo(updatedP.getDescription());
        assertThat(checkP.getType()).isEqualTo(updatedP.getType());
        assertThat(checkP.getImgUrl()).isEqualTo(updatedP.getImgUrl());
    }

    

}
