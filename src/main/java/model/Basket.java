package model;

import org.apache.log4j.Logger;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "basket")
public class Basket {

    private static final Logger logger = Logger.getLogger(Basket.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idBasket;

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "product_basket",
            joinColumns = {@JoinColumn(name = "basket_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")}
    )
    private List<Product> products = new ArrayList<>();

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Basket() {
    }

    public Basket(List<Product> products) {
        this.products = products;
    }

    public Basket(List<Product> products, User user) {
        this.products = products;
        this.user = user;
    }

    public Basket(Long idBasket, List<Product> products, User user) {
        this.idBasket = idBasket;
        this.products = products;
        this.user = user;
    }

    public Basket(User user) {
        this.user = user;
    }

    public int getSize() {
        return products.size();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getIdBasket() {
        return idBasket;
    }

    public void setIdBasket(Long idBasket) {
        this.idBasket = idBasket;
    }

    public void addProductInBasket(Product product) {
        products.add(product);
        logger.info("Product " + product.getTitle() + "added in your basket");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Basket basket = (Basket) o;

        if (idBasket != null ? !idBasket.equals(basket.idBasket) : basket.idBasket != null)
            return false;
        if (products != null ? !products.equals(basket.products) : basket.products != null)
            return false;
        return user != null ? user.equals(basket.user) : basket.user == null;
    }

    @Override
    public int hashCode() {
        int result = idBasket != null ? idBasket.hashCode() : 0;
        result = 31 * result + (products != null ? products.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
