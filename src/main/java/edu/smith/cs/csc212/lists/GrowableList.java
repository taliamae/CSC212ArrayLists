package edu.smith.cs.csc212.lists;

import me.jjfoley.adt.ArrayWrapper;
import me.jjfoley.adt.ListADT;
import me.jjfoley.adt.errors.BadIndexError;
import me.jjfoley.adt.errors.TODOErr;

/**
 * A GrowableList is also known as an ArrayList. It starts at a particular size
 * and grows as needed, replacing its inner array with a larger one when more
 * space is necessary.
 * 
 * @author jfoley
 *
 * @param <T> - the type of item stored in this list.
 */
public class GrowableList<T> extends ListADT<T> {
	/**
	 * How big should the initial list be?
	 * This is not private for use in tests.
	 */
	static final int START_SIZE = 10;
	/**
	 * This is the current array held by the GrowableList. It may be replaced.
	 */
	private ArrayWrapper<T> array;
	/**
	 * This is the number of elements in the array that are used.
	 */
	private int fill;

	/**
	 * Construct a new, empty, GrowableList.
	 */
	public GrowableList() {
		this.array = new ArrayWrapper<>(START_SIZE);
		this.fill = 0;
	}

	@Override
	public T removeFront() {
		this.checkNotEmpty();
		return removeIndex(0);
	}

	@Override
	public T removeBack() {
		this.checkNotEmpty();
		return removeIndex(fill - 1);
	}

	@Override
	public T removeIndex(int index) {
		//not working for some reason, changed i=0 to i=index
		for (int i=index; i < array.size() - 1; i++) {
			array.setIndex(i, array.getIndex(i+1));
		}
		array.setIndex(array.size()-1, null);
		//throw new BadIndexError(index);
		throw new TODOErr();
	}

	@Override
	public void addFront(T item) {
		addIndex(0, item);
	}

	@Override
	public void addBack(T item) {
		if (fill >= array.size()) {
			this.resizeArray();
		}
		array.setIndex(fill++, item);
	}

	/**
	 * This private method is called when we need to make room in our GrowableList.
	 */
	private void resizeArray() {
		// TODO: use this where necessary (already called in addBack!)
		throw new TODOErr();
	}

	@Override
	public void addIndex(int index, T item) {
		// slide to the right
		if (this.fill < array.size()) {
			for (int i=this.fill - 1; i < index; i--) {
				array.setIndex(i, array.getIndex(i + 1));
				fill++;
			}
		} else {
			this.resizeArray();
		}
	}

	@Override
	public T getFront() {
		checkNotEmpty();
		return this.getIndex(0);
	}

	@Override
	public T getBack() {
		checkNotEmpty();
		return this.getIndex(this.fill - 1);
	}

	@Override
	public T getIndex(int index) {
		checkNotEmpty();
		checkExclusiveIndex(index);
		return this.array.getIndex(index);
	}

	@Override
	public int size() {
		return this.fill;
	}

	@Override
	public boolean isEmpty() {
		return this.fill == 0;
	}

	@Override
	public void setIndex(int index, T value) {
		checkNotEmpty();
		checkExclusiveIndex(index);
		this.array.setIndex(index, value);
	}

}
