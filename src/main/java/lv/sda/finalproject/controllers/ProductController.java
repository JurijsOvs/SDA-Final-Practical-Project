package lv.sda.finalproject.controllers;

import lv.sda.finalproject.dto.FileUploadUtil;
import lv.sda.finalproject.model.Product;
import lv.sda.finalproject.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Value("${uploadDir}")
    private String uploadFolder;

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @GetMapping("/all")
    public String getAllProducts(final ModelMap modelMap){
        modelMap.addAttribute("products", productService.findAllProducts());
        return "viewProducts";
    }

    @GetMapping("/clientView")
    public String showAllProducts(final ModelMap modelMap){
        modelMap.addAttribute("clientProducts", productService.findAllProducts());
        return "clientViewProducts";
    }

    @GetMapping("/add")
    public String productForm(ModelMap modelMap) {
        modelMap.addAttribute("product", new Product());
        return "addProduct";
    }


    @PostMapping("/add")
    public String createProduct(@Valid @ModelAttribute ("product")Product productForm,
                                      Errors errors, ModelMap model, @RequestParam ("image")MultipartFile multipartFile) throws IOException {
    if(errors.hasErrors()){
        model.addAttribute("error", "Error! Please check and try again.");
      //  return "addProduct";
    }
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        productForm.setPhotos(fileName);

        productService.save(productForm);

        Product savedProduct = productForm;

        String uploadDir = "C:/Java/final-practical-project/src/main/resources/static/product-photos/" + savedProduct.getId();

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

    return  "redirect:/products/all";

    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id){
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
