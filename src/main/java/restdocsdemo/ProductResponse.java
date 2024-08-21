package restdocsdemo;

public record ProductResponse(
        Long id,
        String name,
        String brand,
        int price
) {
}
