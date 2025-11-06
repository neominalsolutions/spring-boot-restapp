package com.mertalptekin.springbootrestapp.presentation.controller;

import com.mertalptekin.springbootrestapp.domain.entity.Category;
import com.mertalptekin.springbootrestapp.domain.entity.Product;
import com.mertalptekin.springbootrestapp.infra.repository.ICategoryRepository;
import com.mertalptekin.springbootrestapp.infra.repository.IProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

    private final ICategoryRepository categoryRepository;
    private final IProductRepository productRepository;

    public CategoryController(ICategoryRepository categoryRepository, IProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    // api/categories?id=5

    @GetMapping
    public ResponseEntity<String> getCategories(@RequestParam(required = false) Integer id) {

       Optional<Category> category = categoryRepository.findById(id);

       // eğer üzerinde çalıştığım nesnede sadece kategoriye ait alanlanlar üzerinde çalışacak isek bu durumda lazy fetch kullanabiliriz. Daha performanslı olur.
        // ama kategori ile birlikte kategoriye ait ürünler üzerinde de çalışacak isek bu durumda eager fetch kullanmak daha mantıklı olur.

       if(category.isPresent()) {
           return ResponseEntity.ok("Category Name: " + category.get().getProducts().get(0).getName());
       } else {
           return ResponseEntity.notFound().build();

       }
    }

    @GetMapping("/withProducts")
    public ResponseEntity<Category> getCategoriesWithProducts(@RequestParam(required = false) Integer id) {
         Optional<Category> category = categoryRepository.findWithProductsByCategoryId(id);

         if(category.isPresent()) {
              return ResponseEntity.ok(category.get());
         } else {
              return ResponseEntity.notFound().build();
         }
    }

    @GetMapping("/findProductBetweenPrices")
    public ResponseEntity<List<Product>> findProductBetweenPrices(@RequestParam Integer min,@RequestParam Integer max) {
        List<Product> products = productRepository.findByPriceBetween(BigDecimal.valueOf(min), BigDecimal.valueOf(max));

        return ResponseEntity.ok(products);

    }

    @GetMapping("/paginationAndSorting")
    public ResponseEntity<Page<Product>> findProductBetweenPricesWithPage(@RequestParam Integer page, @RequestParam Integer size) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by("price").ascending().and(Sort.by("name").descending()));
        Page<Product> pageModel = productRepository.findAll(
                    pageable);

        System.out.println("totalPages" + pageModel.getTotalPages());
        System.out.println("totalElements" + pageModel.getTotalElements());
        System.out.println("size" + pageModel.getSize());

        return ResponseEntity.ok(pageModel);
    }

}
