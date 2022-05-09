package com.gondor.isildur.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.gondor.isildur.DTO.BaseDTO;
import com.gondor.isildur.DTO.BookDTO;
import com.gondor.isildur.entity.Book;
import com.gondor.isildur.repository.BookRepository;
import com.gondor.isildur.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookServiceImpl extends BaseImpl<Book, Long> implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public JSONObject put(long bookId, Book book) {
        Book oldBook = bookRepository.findById(bookId).orElse(null);
        if (oldBook == null) {
            return new BaseDTO().build(1, "book not found");
        }
        oldBook.setBookTitle(book.getBookTitle());
        oldBook.setBookAuthor(book.getBookAuthor());
        oldBook.setBookIsbn(book.getBookIsbn());
        oldBook.setBookImage(book.getBookImage());
        oldBook.setBookCategory(book.getBookCategory());
        oldBook.setBookPublisher(book.getBookPublisher());
        bookRepository.save(oldBook);
        return new BaseDTO().build();
    }

    public JSONObject searchByBookTitle(String bookTitle) {
        List<Book> books = bookRepository.findByBookTitleContaining(bookTitle);
        List<BookDTO> bookDTOs = new ArrayList<>();
        for (Book book : books) {
            BookDTO bookDTO = book.toDTO();
            bookDTOs.add(bookDTO);
        }
        return new BaseDTO().build(bookDTOs);
    }

}
