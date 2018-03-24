package ru.job4j.immutable.list;

import java.util.*;

public final class ImmutableList<T> implements List<T> {

    private final List<T> elementsList;

    public ImmutableList(Collection<T> collection) {
        this.elementsList = new ArrayList<>(collection);
    }

    public ImmutableList(T[] array) {
        this.elementsList = new ArrayList<>(Arrays.asList(array));
    }

    @Override public int size() {
        return elementsList.size();
    }

    @Override public boolean isEmpty() {
        return elementsList.isEmpty();
    }

    @Override public boolean contains(Object o) {
        return elementsList.contains(o);
    }

    @Override public Iterator<T> iterator() {
        return elementsList.iterator();
    }

    @Override public Object[] toArray() {
        return elementsList.toArray();
    }

    @Override public <T> T[] toArray(T[] array) {
        return elementsList.toArray(array);
    }

    @Override public boolean add(T t) {
        throw new UnsupportedOperationException();
    }

    @Override public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override public boolean containsAll(Collection<?> collection) {
        return elementsList.containsAll(collection);
    }

    @Override public boolean addAll(Collection<? extends T> collection) {
        throw new UnsupportedOperationException();
    }

    @Override public boolean addAll(int index, Collection<? extends T> collection) {
        throw new UnsupportedOperationException();
    }

    @Override public boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void sort(Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }

    @Override public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override public T get(int index) {
        return elementsList.get(index);
    }

    @Override public T set(int index, T element) {
        throw new UnsupportedOperationException();
    }

    @Override public void add(int index, T element) {
        throw new UnsupportedOperationException();
    }

    @Override public T remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override public int indexOf(Object o) {
        return elementsList.indexOf(o);
    }

    @Override public int lastIndexOf(Object o) {
        return elementsList.lastIndexOf(o);
    }

    @Override public ListIterator<T> listIterator() {
        return elementsList.listIterator();
    }

    @Override public ListIterator<T> listIterator(int index) {
        return elementsList.listIterator(index);
    }

    @Override public List<T> subList(int fromIndex, int toIndex) {
        return elementsList.subList(fromIndex, toIndex);
    }
}
