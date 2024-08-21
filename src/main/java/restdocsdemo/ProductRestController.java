package restdocsdemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductRestController {

    @GetMapping("/api/products/{id}")
    public ProductResponse getProduct(@PathVariable Long id) {
        return new ProductResponse(id, "맥북 프로 M4", "애플", 1000);
    }

    @PostMapping("/api/products")
    public ProductResponse create(@RequestBody CreateProductRequest request) {
        return new ProductResponse(33L, request.name(), request.brand(), request.price());
    }
}
