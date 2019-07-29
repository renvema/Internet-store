package factory;

import dao.CodeDao;
import dao.impl.CodeMysqlDaoImpl;

public class CodeDaoFactory {

    private static CodeDao instance;

    private CodeDaoFactory() {
    }

    public static CodeDao getCodeDao() {
        if (instance == null) {
            instance = new CodeMysqlDaoImpl ();
        }
        return instance;
    }
}
