package ru.kviak.springlibrary.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kviak.springlibrary.models.Book;
import ru.kviak.springlibrary.models.Person;
import ru.kviak.springlibrary.services.BookService;
import ru.kviak.springlibrary.services.PeopleService;

import java.util.Optional;


@Controller
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final PeopleService peopleService;

    @GetMapping("")
    public String index(@RequestParam(defaultValue = "false", name = "sort") boolean sort,
                        @RequestParam(defaultValue = "0",name = "page") int page,
                        @RequestParam (defaultValue = "100",name ="books_per_page") int perBooks, Model model,
                        @ModelAttribute("person")Person person){
        model.addAttribute("books", bookService.findAll(page, perBooks, sort));
        return "book/index";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book){
        return "book/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") Book book){
        bookService.save(book);
        return "redirect:/book";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person")Person person){
        Optional<Person> bookOwner = bookService.hasOwner(id);
        if (bookOwner.isPresent())
            model.addAttribute("owner", bookOwner.get());
        else
            model.addAttribute("people", peopleService.findAll());
        model.addAttribute("book", bookService.findById(id).orElse(null));
        return "book/show";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id){
        bookService.free(id);
        return "redirect:/book/"+id;
    }

    @PatchMapping("/set/{id}")
    public String set(@ModelAttribute("person") Person person, @PathVariable("id") int id){
        bookService.set(id, person);
        return "redirect:/book";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        bookService.delete(id);
        return "redirect:/book";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("book", bookService.findById(id).orElse(null));
        return "book/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book")  Book book, @PathVariable("id") int id){
        bookService.update(id, book);
        return "redirect:/book";
    }

    @GetMapping("/search")
    public String search(@RequestParam(defaultValue = "", name = "title") String title, Model model){
        Optional<Book> s = bookService.search(title);
        System.out.println(s);
        if (s.isPresent()) model.addAttribute(s.get());
        return "book/search";
    }

}
