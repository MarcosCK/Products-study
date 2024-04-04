package ApiJwt.apiproducts.model.repository.Produto;

import ApiJwt.apiproducts.model.entity.Produto.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {

}
