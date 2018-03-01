package ru.job4j.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Order model.
 * @author aeremeev.
 * @version 1
 * @since 05.02.2018
 */
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "car_id")
    private Car car;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order", fetch = FetchType.EAGER)
    private List<Image> images = new CopyOnWriteArrayList<>();
    private int price;
    private int mileage;
    @Column(name = "releasedate")
    private Timestamp releaseDate;
    private boolean sold;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "order_date")
    private Timestamp orderDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public Timestamp getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Timestamp releaseDate) {
        this.releaseDate = releaseDate;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order order = (Order) o;
        return getId() == order.getId()
                && getPrice() == order.getPrice()
                && isSold() == order.isSold()
                && Objects.equals(getCar(), order.getCar())
                && Objects.equals(getReleaseDate(), order.getReleaseDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCar(), getPrice(), getReleaseDate(), isSold());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("id=").append(id);
        sb.append(", car=").append(car);
        sb.append(", price=").append(price);
        sb.append(", releaseDate=").append(releaseDate);
        sb.append(", sold=").append(sold);
        sb.append('}');
        return sb.toString();
    }
}
