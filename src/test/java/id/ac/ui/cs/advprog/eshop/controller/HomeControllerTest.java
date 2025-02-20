package id.ac.ui.cs.advprog.eshop.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;

class HomeControllerTest {

    @Test
    void testHomePage() {
        HomeController controller = new HomeController();
        Model model = mock(Model.class);

        String viewName = controller.homePage(model);

        assertEquals("HomePage", viewName);
        verify(model).addAttribute("title", "ADV Shop");
    }
}
