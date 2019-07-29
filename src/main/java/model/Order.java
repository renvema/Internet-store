package model;

public class Order {

    private Long idOrder;
    private User user;
    private Basket basket;
    private Code code;
    private String name;
    private String surname;
    private String city;
    private String address;
    private String phone;

    public Order(User user, Basket basket, Code code, String name, String surname,
                 String city, String address, String phone) {
        this.user = user;
        this.basket = basket;
        this.code = code;
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.address = address;
        this.phone = phone;
    }

    public Order(User user, Code code, String name, String surname, String city, String address, String phone) {
        this.user = user;
        this.code = code;
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.address = address;
        this.phone = phone;
    }

    public Order(Long idOrder, User user, Code code, String name, String surname, String city, String address, String phone) {
        this.idOrder = idOrder;
        this.user = user;
        this.code = code;
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.address = address;
        this.phone = phone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (idOrder != null ? !idOrder.equals(order.idOrder) : order.idOrder != null) return false;
        if (user != null ? !user.equals(order.user) : order.user != null) return false;
        if (basket != null ? !basket.equals(order.basket) : order.basket != null) return false;
        if (code != null ? !code.equals(order.code) : order.code != null) return false;
        if (name != null ? !name.equals(order.name) : order.name != null) return false;
        if (surname != null ? !surname.equals(order.surname) : order.surname != null) return false;
        if (city != null ? !city.equals(order.city) : order.city != null) return false;
        if (address != null ? !address.equals(order.address) : order.address != null) return false;
        return phone != null ? phone.equals(order.phone) : order.phone == null;
    }

    @Override
    public int hashCode() {
        int result = idOrder != null ? idOrder.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (basket != null ? basket.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "idOrder=" + idOrder +
                ", user=" + user +
                ", basket=" + basket +
                ", code=" + code +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
