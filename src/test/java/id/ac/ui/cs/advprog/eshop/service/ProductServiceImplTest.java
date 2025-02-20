package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void testCreateProduct() {
        Product product = new Product();
        product.setProductQuantity(10);
        productService.create(product);
        verify(productRepository).create(product);
        assertEquals(10, product.getProductQuantity());
    }

    @Test
    void testCreateProductNegativeQuantity() {
        Product product = new Product();
        product.setProductQuantity(-5);
        productService.create(product);
        verify(productRepository).create(product);
        assertEquals(0, product.getProductQuantity());
    }

    @Test
    void testFindAll() {
        when(productRepository.findAll()).thenReturn(new java.util.ArrayList<Product>().iterator());
        assertTrue(productService.findAll().isEmpty());
    }

    @Test
    void testFindProductById() {
        Product product = new Product();
        when(productRepository.findProductById("1")).thenReturn(product);
        assertEquals(product, productService.findProductById("1"));
    }

    @Test
    void testEditProduct() {
        Product product = new Product();
        product.setProductQuantity(20);
        productService.edit(product);
        verify(productRepository).edit(product);
        assertEquals(20, product.getProductQuantity());
    }

    @Test
    void testEditProductNegativeQuantity() {
        Product product = new Product();
        product.setProductQuantity(-10);
        productService.edit(product);
        verify(productRepository).edit(product);
        assertEquals(0, product.getProductQuantity());
    }

    @Test
    void testDeleteProduct() {
        productService.delete("1");
        verify(productRepository).deleteProductById("1");
    }
}
