package model;

import utils.RandomHelper;

public class Code {
    private String code;
    private User user;

    public Code(User user) {
        this.code = RandomHelper.getFourDigitCode();
        this.user = user;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Code code1 = (Code) o;

        if (code != null ? !code.equals(code1.code) : code1.code != null) return false;
        return user != null ? user.equals(code1.user) : code1.user == null;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
