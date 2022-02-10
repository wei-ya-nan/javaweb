package com.wyn.service;

/**
 * @author wei-ya-nan
 * @version 1.0
 */

import com.wyn.pojo.Book;
import com.wyn.pojo.Pages;

import java.util.List;

public interface  BookService {
    public void addBook(Book book);

    public void deleteBookById(Integer id);

    public void updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();

    Pages<Book> page(int pageNo, int pageSize);

    Pages<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
