package com.asifsid88.coupondunia.dal;

import com.asifsid88.coupondunia.dal.dao.EmailDao;
import com.asifsid88.coupondunia.model.Email;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EmailDaoTest {

    private final EmailDao emailDao = mock(EmailDao.class);

    @Before
    public void setUp() {
        when(emailDao.getEmail()).thenReturn(EmailHelper.getEmailList(10));
        when(emailDao.getEmailsByIdsInBatch(100l, 5)).thenReturn(EmailHelper.getEmailList(5));
        when(emailDao.getEmailsInBatch(100)).thenReturn(EmailHelper.getEmailList(100));
    }

    @Test
    public void getEmail() {
        List<Email> emailList = emailDao.getEmail();
        Assert.assertEquals(10, emailList.size());
    }

    @Test
    public void getEmailsByIdsInBatch() {
        List<Email> emailList = emailDao.getEmailsByIdsInBatch(100l, 5);
        Assert.assertEquals(5, emailList.size());
    }

    @Test
    public void getEmailsInBatch() {
        List<Email> emailList = emailDao.getEmailsInBatch(100);
        Assert.assertEquals(100, emailList.size());
    }
}
