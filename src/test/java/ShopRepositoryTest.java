import org.example.NotFoundException;
import org.example.Product;
import org.example.ShopRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ShopRepositoryTest {
    ShopRepository repo = new ShopRepository();

    Product item1 = new Product(1, "Книга", 500);
    Product item2 = new Product(2, "Смартфон", 30_000);
    Product item3 = new Product(3, "Чайник", 2_500);

    @BeforeEach
    public void setup() {
        repo.add(item1);
        repo.add(item2);
        repo.add(item3);
    }

    @Test
    public void shouldRemoveExistingId() {
        repo.removeById(2);

        Product[] expected = {item1, item3};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldThrowExceptionIfIdNotFound() {
        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.removeById(100);
        });
    }
}