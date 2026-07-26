package com.contactbook.contactbook.services.impl;

import com.contactbook.contactbook.dao.UserDao;
import com.contactbook.contactbook.exceptions.DatabaseException;
import com.contactbook.contactbook.models.User;
import com.contactbook.contactbook.services.DashBoardService;

public class DashBoardServiceImpl implements DashBoardService {
    private UserDao userDao;
    public DashBoardServiceImpl() {
        this(new UserDao());
    }

    public DashBoardServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }
    @Override
    public String getFullName(String fullname) throws DatabaseException {
        User user = userDao.findByName(fullname);
        if (user == null) return "";
        return user.getFullname() + " - " + user.getCellno() + " - " + user.getEmail();

    }

    @Override
    public String getCellno(String cellno) throws DatabaseException {
        User user = userDao.findByCellNo(cellno);
        if (user == null) return "";
        return user.getCellno();
    }

}
