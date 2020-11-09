package com.kea.websites2.service;

import com.kea.websites2.model.Product;
import com.kea.websites2.model.utils.PagingHeaders;
import com.kea.websites2.model.utils.PagingResponse;
import com.kea.websites2.repository.ProductRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProductService {
    @Autowired
    ProductRepo repo;

    public PagingResponse getAllProducts(Specification<Product> spec, HttpHeaders headers, Sort sort) {
        if (isRequestPaged(headers)) {
            return getAllProducts(spec, buildPageRequest(headers, sort));
        } else {
            List<Product> entities = getAllProducts(spec, sort);
            return new PagingResponse((long) entities.size(), 0L, 0L, 0L, 0L, entities);
        }
    }

    private boolean isRequestPaged(HttpHeaders headers) {
        return headers.containsKey(PagingHeaders.PAGE_NUMBER.getName()) && headers.containsKey(PagingHeaders.PAGE_SIZE.getName());
    }

    private Pageable buildPageRequest(HttpHeaders headers, Sort sort) {
        int page = Integer.parseInt(Objects.requireNonNull(headers.get(PagingHeaders.PAGE_NUMBER.getName())).get(0));
        int size = Integer.parseInt(Objects.requireNonNull(headers.get(PagingHeaders.PAGE_SIZE.getName())).get(0));
        return PageRequest.of(page, size, sort);
    }

    public PagingResponse getAllProducts(Specification<Product> spec, Pageable pageable) {
        Page<Product> page = repo.findAll(spec, pageable);
        List<Product> content = page.getContent();
        return new PagingResponse(page.getTotalElements(), (long) page.getNumber(), (long) page.getNumberOfElements(), pageable.getOffset(), (long) page.getTotalPages(), content);
    }

    public List<Product> getAllProducts(Specification<Product> spec, Sort sort) {
        return repo.findAll(spec, sort);
    }

}
