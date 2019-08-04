package utils;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class SaltHashUtilTest {

    @Test
    public void getSHA512SecurePassword() {

        String salt = SaltHashUtil.getRandomeSalt();
        String password = "test1234";
        String result = SaltHashUtil.getSHA512SecurePassword(password, salt);
        String result2 = SaltHashUtil.getSHA512SecurePassword(password, salt);
        Assert.assertEquals(result, result2);
        Assert.assertEquals(SaltHashUtil.getSHA512SecurePassword("admin", "admin"),
                "d82494f05d6917ba02f7aaa29689ccb444bb73f20380876cb05d1f37537b7892");
        Assert.assertEquals(SaltHashUtil.getSHA512SecurePassword("user", "user"),
                "e172c5654dbc12d78ce1850a4f7956ba6e5a3d2ac40f0925fc6d691ebb54f6bf");
    }
}
