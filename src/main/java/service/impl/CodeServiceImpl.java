package service.impl;

import dao.CodeDao;
import factory.CodeDaoFactory;
import model.Code;
import model.User;
import service.CodeService;

import java.util.Optional;

public class CodeServiceImpl implements CodeService {

    private static final CodeDao codeDao = CodeDaoFactory.getCodeDao();

    @Override
    public void add(Code code) {
        codeDao.add(code);
    }

    @Override
    public Optional<Code> getCode(User user) {
        return codeDao.getCode(user);
    }
}
