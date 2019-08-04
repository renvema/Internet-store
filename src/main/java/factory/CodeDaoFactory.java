package factory;

import dao.CodeDao;
import dao.impl.CodeHibernateDaoImpl;

public class CodeDaoFactory {

    private static CodeDao instance;

    private CodeDaoFactory() {
    }

    public static CodeDao getCodeDao() {
        if (instance == null) {
            instance = new CodeHibernateDaoImpl();
        }
        return instance;
    }
}
