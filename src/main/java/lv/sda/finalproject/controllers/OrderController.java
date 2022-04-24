package lv.sda.finalproject.controllers;

import lv.sda.finalproject.model.Order;
import lv.sda.finalproject.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/orders")
public class OrderController{


    @Autowired
    private OrderService orderService;

    @GetMapping("/all")
    public String getAllOrders(final ModelMap modelMap){
        modelMap.addAttribute("orders", orderService.findAllOrders());
        return "viewAllOrders";
    }

    @GetMapping("/create")
    public String createOrder(ModelMap modelMap){
        modelMap.addAttribute("order", new Order());
        return "addOrder";
    }

    @PostMapping("/create")
    public String addToOrder(@Valid @ModelAttribute ("order") Order order,
                             Errors errors, ModelMap modelMap){
        if(errors.hasErrors()){
            modelMap.addAttribute("error", "Error! Please check and try again!");
            return "addOrder";
        }

        Order updateOrder = orderService.save(order);


        return "redirect:/orders/update/"+updateOrder.getId();
    }

    @GetMapping("/update/{id}")
    public String updateOrder(@PathVariable Long id, ModelMap modelMap){
        modelMap.addAttribute("order", orderService.findById(id).get());
        return "updateOrder";
    }

    @PostMapping("/update")
    public String updateOrder(@Valid @ModelAttribute ("order") Order order,
                              Errors errors, ModelMap modelMap) {
        if (errors.hasErrors()) {
            modelMap.addAttribute("error", "Error! Please check and try again");
            return "updateOrder";
        }

        orderService.update(order);

        return "redirect:/orders/all";
    }

    @DeleteMapping("/{orderId}/removeProduct/{productId}")
    public String removeProduct(@PathVariable Long orderId, @PathVariable Long productId){
        orderService.removeProduct(orderId,productId);
        return "redirect:/orders/update/"+orderId;
    }

    @GetMapping("/{orderId}/addProduct/{productId}")
    public String addProduct(@PathVariable Long orderId, @PathVariable Long productId){
        orderService.addProduct(orderId, productId);
        return "redirect:/orders/update/"+orderId;
    }


}
