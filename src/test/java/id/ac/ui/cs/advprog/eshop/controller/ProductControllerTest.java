package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductService service;

    @Mock
    private Model model;

    @InjectMocks
    private ProductController controller;

    @Test
    void testCreateProductPage() {
        String view = controller.createProductPage(model);
        verify(model).addAttribute(eq("product"), any(Product.class));
        assertEquals("createProduct", view);
    }

    @Test
    void testCreateProduct() {
        Product product = new Product();
        String view = controller.createProductPost(product, model);
        verify(service).create(product);
        assertEquals("redirect:list", view);
    }

    @Test
    void testProductListPage() {
        when(service.findAll()).thenReturn(Collections.emptyList());
        String view = controller.productListPage(model);
        verify(model).addAttribute(eq("products"), any(List.class));
        assertEquals("productList", view);
    }

    @Test
    void testEditProductPage() {
        Product product = new Product();
        when(service.findProductById("1")).thenReturn(product);
        String view = controller.editProductPage(model, "1");
        verify(model).addAttribute("product", product);
        assertEquals("editProduct", view);
    }

    @Test
    void testEditProductPost() {
        Product product = new Product();
        String view = controller.editProductPost(product, model, "1");
        assertEquals("1", product.getProductId());
        verify(service).edit(product);
        assertEquals("redirect:../list", view);
    }

    @Test
    void testDeleteProductPage() {
        String view = controller.deleteProductPage(model, "1");
        verify(service).delete("1");
        assertEquals("redirect:/product/list", view);
    }
}
