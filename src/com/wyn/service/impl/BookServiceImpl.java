package com.wyn.service.impl;

import com.wyn.dao.BookDao;
import com.wyn.dao.impl.BookDaoImpl;
import com.wyn.pojo.Book;
import com.wyn.pojo.Pages;
import com.wyn.service.BookService;

import java.util.List;

/**
 * @author wei-ya-nan
 * @version 1.0
 */
public class BookServiceImpl implements BookService {
    //<editor-fold desc="Description">
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Pages<Book> page(int pageNo, int pageSize) {
        Pages<Book> page = new Pages<Book>();

        //设置每页显示的数量即记录数
        page.setPageSize(pageSize);
        //获取总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        //设置总记录数
        page.setPageTotalCount(pageTotalCount);
        //求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal += 1;
        }
        page.setPageTotal(pageTotal);
        // 设置当前页码
        page.setPageNo(pageNo);
        //求开始的索引
        System.out.println(page.getPageNo());
        int begin = (page.getPageNo() - 1) * pageSize;
        //求当前页面的数据
        List<Book> items = bookDao.queryForPageItems(begin, pageSize);
        //设置当前页的数据
        page.setItems(items);
        return page;
    }

    @Override
    public Pages<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {

        Pages<Book> page = new Pages<Book>();

        //设置每页显示的数量即记录数
        page.setPageSize(pageSize);
        //获取总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCountByPrice(min, max);
        //设置总记录数
        page.setPageTotalCount(pageTotalCount);
        //求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal += 1;
        }
        page.setPageTotal(pageTotal);
        // 设置当前页码
        page.setPageNo(pageNo);
        //求开始的索引
        System.out.println(page.getPageNo());
        int begin = (page.getPageNo() - 1) * pageSize;
        //求当前页面的数据
        List<Book> items =bookDao.queryForPageItemsByPrice(begin, pageSize, min, max);
        //设置当前页的数据
        page.setItems(items);
        return page;
    }
    //</editor-fold>
}
