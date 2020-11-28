import http from "../http-common";

class ProductService {
    getAll() {
        return http.get("/products");
    }

    getById(id) {
        return http.get(`/products/${id}`);
    }

    create(data) {
        return http.post("/products", data);
    }

    update(id, data) {
        return http.put(`/products/${id}`, data);
    }

    delete(id) {
        return http.delete(`/products/${id}`);
    }
}

export default new ProductService();