package main.java.br.com.mybeatz.beat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller 
@RequestMapping("/beat")
public class BeatController {

    @Autowired
    FilmeService service;

    @GetMapping
    public String index(Model model, @AuthenticationPrincipal OAuth2User user){
        model.addAttribute("username", user.getAttribute("name"));
        model.addAttribute("avatar_url", user.getAttribute("avatar_url"));
        model.addAttribute("tasks", service.findAll());
        return "beat/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect){
        if (service.delete(id)){
            redirect.addFlashAttribute("success", "Beat apagado com sucesso");
        }else{
            redirect.addFlashAttribute("error", "Beat não encontrado");
        }
        return "redirect:/beat";
    }

    @GetMapping("new")
    public String form(Task task){
        return "beat/form";
    }

    @PostMapping
    public String create(@Valid Task task, BindingResult binding, RedirectAttributes redirect){
        if (binding.hasErrors()) return "/beat/form";

        service.save(task);
        redirect.addFlashAttribute("success", "Beat cadastrado com sucesso");
        return "redirect:/beat";
    }

}