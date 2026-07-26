package com.contactbook.contactbook.services;

import com.contactbook.contactbook.exceptions.DatabaseException;

public interface DashBoardService {
    String getFullName(String fullname) throws DatabaseException;
    String getCellno(String cellno) throws DatabaseException;
}
