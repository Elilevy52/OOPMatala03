package model;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class NewSet<T extends MultipeChoiseAnswer> implements Set<T>, Serializable {

	ArrayList<T> data = new ArrayList<T>();

	@Override
	public int size() {
		return data.size();

	}

	@Override
	public boolean isEmpty() {
		if (data.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean contains(Object o) {
		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).equals(o)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Iterator<T> iterator() {
		Iterator<T> dataIterator = data.iterator();
		if (dataIterator.hasNext()) {
			return dataIterator;
		}
		return data.iterator();
	}

	@Override
	public Object[] toArray() {
		Object[] arr = new Object[data.size()];
		for (int i = 0; i < data.size(); i++) {
			arr[i] = data.size();
		}
		return arr;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		final T[] arr = (T[]) Array.newInstance(getClass().getComponentType(), data.size());
		for (int i = 0; i < data.size(); i++) {
			arr[i] = (T) data.get(i);
		}
		return arr;
	}

	@Override
	public boolean add(T e) {
		if (data.isEmpty()) {
			data.add(e);
		}
		for (T ss : data) {
			if (ss.equals(e)) {
				return false;
			} else {
				data.add(e);
				return true;
			}
		}
		return true;
	}

	@Override
	public boolean remove(Object o) {
		if (data.contains(o)) {
			data.remove(o);
			return true;
		} else {
			System.out.println("Does not exists.");
			return false;
		}
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		if (data.containsAll(c)) {
			return true;
		} else {
			System.out.println("Instance does not contain whole collection.");
			return false;
		}
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		if (data.addAll(c)) {
			return true;
		}
		System.out.println("Failed to add all collection elements.");
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		if (data.retainAll(c)) {
			return true;
		}
		System.out.println("Failed to retain all collection elements.");
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		if (data.removeAll(c)) {
			return true;
		}
		System.out.println("Failed to remove all collection elements.");
		return false;
	}

	@Override
	public void clear() {
		data.clear();
		System.out.println("Data cleared Successfully.");
	}

	public T get(int index) {
		return data.get(index);

	}
}
