package com.test.service.impl;

import com.test.controller.client.BookClient;
import com.test.controller.client.UserClient;
import com.test.entity.Book;
import com.test.entity.Borrow;
import com.test.entity.User;
import com.test.entity.UserBorrowDetail;
import com.test.mapper.BorrowMapper;
import com.test.service.BorrowService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowServiceImpl implements BorrowService {
    @Resource
    private BorrowMapper borrowMapper;

    @Resource
    UserClient userClient;

    @Resource
    BookClient bookClient;

    @Override
    public UserBorrowDetail getUserBorrowDetailByUid(int uid) {
        final List<Borrow> borrows = borrowMapper.getBorrowsByUid(uid);
        // 通过getForObject调用其他服务，并将结果进行自动封装
        // 获取User信息
        final User user = userClient.getUserById(uid);
        final List<Book> books = borrows.stream().map(borrow
                        -> bookClient.getBookById(borrow.getBid()))
                .collect(Collectors.toList());
        return new UserBorrowDetail(user,books);
    }
}
