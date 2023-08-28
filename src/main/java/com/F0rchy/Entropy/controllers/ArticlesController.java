package com.F0rchy.Entropy.controllers;

import com.F0rchy.Entropy.models.Articles;
import com.F0rchy.Entropy.repo.ArticlesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class ArticlesController {

    @Autowired
    private ArticlesRepository articlesRepository;

    @GetMapping("/articles")
    public String articles(Model model) {
        Iterable<Articles> articles = articlesRepository.findAllByOrderByIdDesc();
        model.addAttribute("articles", articles);
        model.addAttribute("title", "Игровые статьи | Entropy");

        return "articles";
    }

    @GetMapping("/articles/add")
    public String articlesAdd(Model model) {
        model.addAttribute("title", "Добавление статьи | Entropy");

        return "articles-add";
    }

    @PostMapping("/articles/add")
    public String articlesPostAdd(@RequestParam String title, @RequestParam String anons, @RequestParam String full_text, @RequestParam String author, Model model) {
        Articles articles = new Articles(title, anons, full_text, author);
        articlesRepository.save(articles);

        return "redirect:/articles";
    }

    @GetMapping("/articles/{id}")
    public String articlesFull(@PathVariable(value = "id") long id, Model model) {
        if(!articlesRepository.existsById(id)) {
            return "redirect:/articles";
        }
        Optional<Articles> articles = articlesRepository.findById(id);
        ArrayList<Articles> res = new ArrayList<>();
        articles.ifPresent(res::add);
        model.addAttribute("articles", res);
        model.addAttribute("title", "Подробнее | Entropy");

        return "articles-full";
    }

    @GetMapping("/articles/{id}/edit")
    public String articlesEdit(@PathVariable(value = "id") long id, Model model) {
        if(!articlesRepository.existsById(id)) {
            return "redirect:/articles";
        }
        Optional<Articles> articles = articlesRepository.findById(id);
        ArrayList<Articles> res = new ArrayList<>();
        articles.ifPresent(res::add);
        model.addAttribute("articles", res);
        model.addAttribute("title", "Редактировать | Entropy");

        return "articles-edit";
    }

    @PostMapping("/articles/{id}/edit")
    public String articlesPostUpdate(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String anons, @RequestParam String full_text, @RequestParam String author, Model model) {
        Articles articles = articlesRepository.findById(id).orElseThrow();
        articles.setTitle(title);
        articles.setAnons(anons);
        articles.setAuthor(author);
        articles.setFull_text(full_text);
        articlesRepository.save(articles);

        return "redirect:/articles";
    }

    @PostMapping("/articles/{id}/remove")
    public String articlesPostDelete(@PathVariable(value = "id") long id, Model model) {
        Articles articles = articlesRepository.findById(id).orElseThrow();
        articlesRepository.delete(articles);

        return "redirect:/articles";
    }
}
