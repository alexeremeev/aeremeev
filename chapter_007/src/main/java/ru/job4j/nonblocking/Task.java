package ru.job4j.nonblocking;

/**
 * class Task - модель задачи для неблокирующего кэша.
 */
public final class Task {
    /**
     * ID.
     */
    private int id;
    /**
     * Имя.
     */
    private String name;
    /**
     * Описание.
     */
    private String description;
    /**
     * Версия.
     */
    private int version;

    /**
     * Конструктор.
     * @param id ID.
     * @param name имя.
     * @param description описание.
     */
    public Task(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.version = 0;
    }

    /**
     * Геттер ID.
     * @return ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Геттер версии.
     * @return номер версии.
     */
    public int getVersion() {
        return version;
    }

    /**
     * Сеттер версии.
     * @param version номер версии.
     */
    public void setVersion(int version) {
        this.version = version;
    }
    //CHECKSTYLE.OFF
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (getId() != task.getId()) return false;
        if (name != null ? !name.equals(task.name) : task.name != null) return false;
        return description != null ? description.equals(task.description) : task.description == null;
    }
    //CHECKSTYLE.ON
    @Override
    public int hashCode() {
        return 31 * getId();
    }

    @Override
    public String toString() {
        return String.format("Task{ id=%s name=%s description=%s version=%s }",
                this.id, this.name, this.description, this.version);

    }
}
