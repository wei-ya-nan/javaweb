package com.wyn.dao;

import com.wyn.pojo.Book;

import java.util.List;

/**
 * @author wei-ya-nan
 * @version 1.0
 */
public interface BookDao {

    //添加书籍
    public int addBook(Book book);

    //根据id删除书籍
    public int deleteBookById(Integer id);

    //修改书籍
    public int updateBook(Book book);

    //根据id查询书籍
    public Book queryBookById(Integer id);

    //查询所有的数据
    public List<Book> queryBooks();

    Integer queryForPageTotalCount();

    List<Book> queryForPageItems(int begin, int pageSize);

    Integer queryForPageTotalCountByPrice(int min, int max);

    List<Book> queryForPageItemsByPrice(int begin,int pageSize,int min, int max);

}
