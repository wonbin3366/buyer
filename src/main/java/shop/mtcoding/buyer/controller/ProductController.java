package shop.mtcoding.buyer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.method.annotation.PrincipalMethodArgumentResolver;

import shop.mtcoding.buyer.model.Product;
import shop.mtcoding.buyer.model.ProductRepository;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping({ "/", "/product" })
    public String home(Model model) {
        List<Product> productlist = productRepository.findAll();
        model.addAttribute("productList", productlist);
        return "product/list";
    }

    @GetMapping("/product/{id}")
    public String findById(@PathVariable int id, Model model) {
        Product product = productRepository.findById(id);
        if (product == null) {
            return "redirect:/notfound";
        } else {
            model.addAttribute("product", product);
            return "/product/detail";
        }
    }

    @GetMapping("/product/{id}/purchaseForm")
    public String updateByQty(@PathVariable int id, int qty) {
        int result = productRepository.updateByQty(id, qty);
        if (result == 1) {
            return "redirect:/product/{id}";
        } else {
            return "redirect:/product/{id}";
        }
    }
}
