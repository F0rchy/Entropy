package com.F0rchy.Entropy.controllers;

import com.F0rchy.Entropy.models.News;
import com.F0rchy.Entropy.repo.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.*;

@Controller
public class NewsController {

    @Autowired
    private NewsRepository newsRepository;

    @GetMapping("/news")
    public String news(Model model) {
        Iterable<News> news = newsRepository.findAllByOrderByIdDesc();
        model.addAttribute("news", news);
        model.addAttribute("title", "Новости сайта");

        return "news";
    }

    @GetMapping("/news/add")
    public String newsAdd(Model model) {
        return "news-add";
    }

    @PostMapping("/news/add")
    public String newsPostAdd(@RequestParam String title, @RequestParam String anons, @RequestParam String full_text, @RequestParam String author, Model model) {
        News news = new News(title, anons, full_text, author);
        newsRepository.save(news);

        return "redirect:/news";
    }

    @GetMapping("/news/{id}")
    public String newsFull(@PathVariable(value = "id") long id, Model model) {
        if(!newsRepository.existsById(id)) {
            return "redirect:/news";
        }
        Optional<News> news = newsRepository.findById(id);
        ArrayList<News> res = new ArrayList<>();
        news.ifPresent(res::add);
        model.addAttribute("news", res);

        return "news-full";
    }

    @GetMapping("/news/{id}/edit")
    public String newsEdit(@PathVariable(value = "id") long id, Model model) {
        if(!newsRepository.existsById(id)) {
            return "redirect:/news";
        }
        Optional<News> news = newsRepository.findById(id);
        ArrayList<News> res = new ArrayList<>();
        news.ifPresent(res::add);
        model.addAttribute("news", res);

        return "news-edit";
    }

    @PostMapping("/news/{id}/edit")
    public String newsPostUpdate(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String anons, @RequestParam String full_text, @RequestParam String author, Model model) {
        News news = newsRepository.findById(id).orElseThrow();
        news.setTitle(title);
        news.setAnons(anons);
        news.setAuthor(author);
        news.setFull_text(full_text);
        newsRepository.save(news);

        return "redirect:/news";
    }

    @PostMapping("/news/{id}/remove")
    public String newsPostDelete(@PathVariable(value = "id") long id, Model model) {
        News news = newsRepository.findById(id).orElseThrow();
        newsRepository.delete(news);

        return "redirect:/news";
    }
}
