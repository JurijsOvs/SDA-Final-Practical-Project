package lv.sda.finalproject.controllers;

import lv.sda.finalproject.model.Product;
import lv.sda.finalproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("/all")
    public String getAllProducts(final ModelMap modelMap){
        modelMap.addAttribute("products", productService.findAllProducts());
        return "viewProducts";
    }

    @GetMapping("/add")
    public String productForm(ModelMap modelMap) {
        modelMap.addAttribute("product", new Product());
        return "addProduct";
    }


    @PostMapping("/add")
    public String createProduct(@Valid @ModelAttribute ("product")Product productForm,
                            Errors errors, ModelMap model){
    if(errors.hasErrors()){
        model.addAttribute("error", "Error! Please check and try again.");
        return "addProduct";
    }

    productService.save(productForm);
    //model.addAttribute("products", productService.findAllProducts());

    return "redirect:/products/all";

    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id,ModelMap modelMap){
        productService.delete(id);

        return "redirect:/products/all";

    }

    @GetMapping("/edit/{id}")
    public String editProductForm(@PathVariable Long id, ModelMap modelMap) {
        modelMap.addAttribute("product", productService.findById(id).get());
        return "editProduct";
    }

    @PostMapping("/edit")
    public String editProduct(@Valid @ModelAttribute ("product") Product productForm,
                              Errors errors, ModelMap modelMap){
        if(errors.hasErrors()){
            modelMap.addAttribute("error", "Error! Please check and try again");

            return "editProduct";
        }

        productService.edit(productForm);

        return "redirect:/products/all";

    }




}
