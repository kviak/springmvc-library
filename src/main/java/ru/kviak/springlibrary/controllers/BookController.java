package ru.kviak.springlibrary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kviak.springlibrary.dao.BookDAO;
import ru.kviak.springlibrary.dao.PersonDAO;
import ru.kviak.springlibrary.models.Book;
import ru.kviak.springlibrary.models.Person;

import java.util.Optional;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("books", bookDAO.index());
        return "book/index";
    }
    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book){
        return "book/new";
    }
    @PostMapping()
    public String create(@ModelAttribute("book") Book book){
        bookDAO.create(book);
        return "redirect:/book";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person")Person person){

        Optional<Person> bookOwner = bookDAO.isHadOwner(id);
        if (bookOwner.isPresent())
            model.addAttribute("owner", bookOwner.get());
        else
            model.addAttribute("people", personDAO.index());
        model.addAttribute("book", bookDAO.show(id));

        return "book/show";
    }
    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id){
        bookDAO.free(id);
        return "redirect:/book/"+id;
    }

    @PatchMapping("/set")
    public String set(@ModelAttribute("person") Person person){
        bookDAO.set(person.getFullName());
        return "redirect:/book";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        bookDAO.delete(id);
        return "redirect:/book";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("book", bookDAO.show(id));
        return "book/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book")  Book book, @PathVariable("id") int id){
        bookDAO.update(id, book);
        return "redirect:/book";
    }
}
