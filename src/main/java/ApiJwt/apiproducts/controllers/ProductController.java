package ApiJwt.apiproducts.controllers;

import ApiJwt.apiproducts.model.entity.Produto.Product;
import ApiJwt.apiproducts.model.entity.Produto.ProductRequestDTO;
import ApiJwt.apiproducts.model.entity.Produto.ProductResponseDTO;
import ApiJwt.apiproducts.model.repository.Produto.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("produto")
public class ProductController {

    @Autowired
    ProductRepository product;

    @PostMapping
    public ResponseEntity postProduto(@RequestBody @Valid ProductRequestDTO dto){
        Product product = new Product(dto);

        this.product.save(product);
        return ResponseEntity.ok().build();
    }


    @GetMapping
    public ResponseEntity getAllProducts(){
        List<ProductResponseDTO> productList = this.product.findAll().stream().map(ProductResponseDTO::new).toList();

        return ResponseEntity.ok(productList);
    }
}
