import org.example.AlreadyExistsException;
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

    @Test
    public void shouldAddNewProduct() {
        Product item4 = new Product(4, "Ноутбук", 50_000);
        repo.add(item4);

        Product[] expected = {item1, item2, item3, item4};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldThrowExceptionWhenAddExistingId() {
        Product duplicate = new Product(1, "Другая книга", 700);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repo.add(duplicate);
        });
    }
}