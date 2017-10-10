package ru.job4j.synchronize;

/**
 * class User - пользователь.
 */
public class User {
    /**
     * ID.
     */
    private int id;
    /**
     * Сумма на счету.
     */
    private int amount;

    /**
     * Конструктор.
     * @param id ID.
     * @param amount сумма на счету.
     */
    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    /**
     * Геттер ID.
     * @return ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Геттер суммы.
     * @return сумма.
     */
    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object object) {
        boolean result = false;
        if (this == object) {
            result = true;
        } else {
            if (object != null && getClass() == object.getClass()) {
                User user = (User) object;
                result = Integer.compare(user.id, id) == 0 && Integer.compare(user.amount, amount) == 0;
            }
        }
        return result;
    }

    @Override
    public int hashCode() {
        return 31 * this.id;
    }
}
