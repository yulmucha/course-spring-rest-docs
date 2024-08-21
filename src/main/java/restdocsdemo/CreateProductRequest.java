package restdocsdemo;

public record CreateProductRequest(
        String name,
        String brand,
        Integer price
) {
}
