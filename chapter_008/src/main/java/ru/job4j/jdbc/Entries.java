package ru.job4j.jdbc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/***
 * class Entries - представление множества Entry для обработки XML файла.
 */
@XmlRootElement (name = "entries")
@XmlAccessorType(XmlAccessType.FIELD)
public class Entries {
    /**
     * Список Entry.
     */
    @XmlElement (name = "entry")
    private List<Entry> entries = new ArrayList<>();

    /**
     * Геттер.
     * @return список Entry.
     */
    public List<Entry> getEntries() {
        return this.entries;
    }

    /**
     * Сеттер.
     * @param entries список Entry.
     */
    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }
}
