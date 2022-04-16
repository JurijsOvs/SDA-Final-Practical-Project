package lv.sda.finalproject.controllers;

/*import lv.sda.finalproject.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final List<User> USERS = new ArrayList<User>(
            Arrays.asList(new User(1L,"Manager","Admin","manager@email.com","Manager"),
                    new User(2L,"Manager2","Admin2","manager2@email.com","Manager"),
                    new User(3L,"Manage3","Admin3","manager3@email.com","Manager")
            )
    );

    @PostMapping("/create")
    public String createUser(@Valid @ModelAttribute User user,
                           Errors errors, ModelMap modelMap){
        if(errors.hasErrors()){
            return "addUser";
        }

        USERS.add(user);
        modelMap.addAttribute("users", USERS);

        return "redirect:/welcome";
    }

    @GetMapping("/add")
    public String addUser(final ModelMap modelMap){
        modelMap.addAttribute("user",new User());
        return "addUser";
    }


    @PutMapping("/update")
    public String updateUser(@Valid @ModelAttribute ("user") User user,
                             Errors errors, ModelMap modelMap){
        if(errors.hasErrors()){
            return "addUser";
        }

        USERS.add(user);
        modelMap.addAttribute("users", USERS);

        return "redirect:/welcome";
    }

    //Admin only
    @GetMapping("/edit/{index}")
    public String editUser(@PathVariable(name = "index") Integer index, final ModelMap modelMap){
        User user = USERS.get(index);
        modelMap.addAttribute("book", new User());
        return "updateUser";
    }



    //Templates to be added

}*/

import lv.sda.finalproject.model.User;
import lv.sda.finalproject.service.SecurityService;
import lv.sda.finalproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/registration")
    public String registration(Model model) {
        if (securityService.isAuthenticated()) {
            return "redirect:/";
        }

        model.addAttribute("userForm", new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (securityService.isAuthenticated()) {
            return "redirect:/products/all";
        }

        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @GetMapping({"/", "/welcome"})
    public String welcome(Model model) {
        return "welcome";
    }
}
