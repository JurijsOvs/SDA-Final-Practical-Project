package lv.sda.finalproject.controllers;

import lv.sda.finalproject.dto.NumberForm;
import lv.sda.finalproject.service.SumHistoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
    private SumHistoryService sumHistoryService;

    public HomeController(SumHistoryService sumHistoryService) {
        this.sumHistoryService = sumHistoryService;
    }

    @GetMapping("/")
    public String homepage(Model model) {
        model.addAttribute("numberForm", new NumberForm());
        return "index";
    }

    @PostMapping("/sum")
    public String calculate(@Valid NumberForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "index";
        }

        Integer sum = sumHistoryService.sum(form);

        model.addAttribute("sum", sum);
        model.addAttribute("history", sumHistoryService.findSumHistory());

        return "index";
    }
}
