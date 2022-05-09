package com.gondor.isildur.controller;

import java.sql.Timestamp;


import com.alibaba.fastjson.JSONObject;
import com.gondor.isildur.entity.Book;
import com.gondor.isildur.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/{bookId}")
    public JSONObject get(@PathVariable long bookId) {
        return bookService.get(bookId);
    }

    @DeleteMapping("/{bookId}")
    public JSONObject delete(@PathVariable long bookId) {
        return bookService.delete(bookId);
    }

    @PutMapping("/{bookId}")
    public JSONObject put(@PathVariable long bookId, @RequestBody Book book) {
        return bookService.put(bookId, book);
    }

    @PostMapping()
    public JSONObject create(@RequestBody Book book) {
        book.setCreateTime(new Timestamp(System.currentTimeMillis()));
        book.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        return bookService.create(book);
    }

    @GetMapping()
    public JSONObject getByPage(@RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        return bookService.getByPage(page, size);
    }

    @GetMapping("/count")
    public JSONObject getCount() {
        return bookService.getCount();
    }

    @GetMapping("/search")
    public JSONObject search(@RequestParam(value = "bookTitle", defaultValue = "") String bookTitle) {
        System.out.println("bookTitle: " + bookTitle);
        return bookService.searchByBookTitle(bookTitle);
    }
}
