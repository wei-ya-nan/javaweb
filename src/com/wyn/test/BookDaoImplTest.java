package com.wyn.test;

import com.wyn.dao.BookDao;
import com.wyn.dao.impl.BookDaoImpl;
import com.wyn.pojo.Book;
import com.wyn.pojo.Pages;
import com.wyn.service.BookService;
import com.wyn.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author wei-ya-nan
 * @version 1.0
 */
public class BookDaoImplTest {

    //<editor-fold desc="Description">
    private BookDao bookDao = new BookDaoImpl();
    private BookService bookService =  new BookServiceImpl();

    @Test
    public void addBook() {
        bookDao.addBook(new Book(null, "国哥为什么这么帅！", "191125",
                new BigDecimal(9999), 1100000, 0, null
        ));
    }

    @Test
    public void deleteBookById() {
        bookDao.deleteBookById(21);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(21, "大家都可以这么帅！", "国哥",
                new BigDecimal(9999), 1100000, 0, null
        ));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookDao.queryBookById(21));
    }

    @Test
    public void queryBooks() {
        for (Book queryBook : bookDao.queryBooks()) {
            System.out.println(queryBook);
        }
    }

    @Test
    public void queryForPageTotalCount() {
        System.out.println(bookDao.queryForPageTotalCount());
    }

    @Test
    public void queryBookItems() {
        List<Book> books = bookDao.queryForPageItems(8, Pages.PAGE_SIZE);
        for (Book book : books) {
            System.out.println(book);
        }

    }
    @Test
    public void queryForPageTotalCountryByPrice(){
        Integer integer = bookDao.queryForPageTotalCountByPrice(10, 50);
        System.out.println(integer);
    }
    @Test
    public void queryForPageItemsByPrice(){
        List<Book> books = bookDao.queryForPageItemsByPrice(0, Pages.PAGE_SIZE, 10, 50);
        for (Book book : books) {
            System.out.println(book);
        }
    }
    @Test
    public void page(){
        System.out.println(bookService.page(2, Pages.PAGE_SIZE));

    }

    //</editor-fold>
}






