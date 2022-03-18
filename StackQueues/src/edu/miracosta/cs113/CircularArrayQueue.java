package edu.miracosta.cs113;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class CircularArrayQueue<E> extends AbstractQueue<E> {

	//Use an object with four int data members and an array data member to represent the queue.
	/** Index of front of the queue */
	private int front;
	/** Index of rear of the queue */
	private int rear;
	/** Current number of elements in queue */
	private int size;
	/** Current capacity of queue */
	private int capacity;
	/** Default capacity */
	private static final int DEFAULT_CAPACITY = 10;
	
	/** Array to hold data */
	private E[] theData;
	
	/** Construct a queue with the specified initial capacity.
	 * @param initialCapacity   the initial capacity
	 */
	@SuppressWarnings("unchecked")
	public CircularArrayQueue(int initialCapacity) {
		capacity = initialCapacity;
		theData = (E[]) new Object[capacity];
		front = 0;
		rear = capacity - 1;
		size = 0;
	}
	
	/** Construct a queue with the default initial capacity */
	public CircularArrayQueue() {
		this(DEFAULT_CAPACITY);
	}
	
	/** Inserts an item at the rear of the queue.
	 * @param item    the element to add
	 * @return true
	 */
	@Override
	public boolean offer(E item) {
		if (size == capacity) {
			reallocate();
		}
		size++;
		rear = (rear + 1) % capacity;
		theData[rear] = item;
		return true;
	}
	
	/** Returns the item at the front of the queue without removing it.
	 * @return if successful, the item at the front of the queue; else 
	 * return null if the queue is empty
	 */
	@Override
	public E peek() {
		if (size == 0) {
			return null;
		}
		else {
			return theData[front];
		}
	}
	
	/** Returns the entry at the front of the queue and returns it is the 
	 * queue if not empty.
	 * @post front    references item that was second in the queue
	 * @return item removed if successful, null if not
	 */
	@Override
	public E poll() {
		if (size == 0) {
			return null;
		}
		E result = theData[front];
		front = (front + 1) % capacity;
		size--;
		return result;
			
	}
	
	/** Double the capacity and reallocate the data when array is filled. */
	@SuppressWarnings("unchecked")
	private void reallocate() {
		int newCapacity = 2 * capacity;
		E[] newData = (E[]) new Object[newCapacity];
		int j = front;
		for (int i = 0; i < size; i++) {
			newData[i] = theData[j];
			j = (j + 1) % capacity;
		}
		front = 0;
		rear = size - 1;
		capacity = newCapacity;
		theData = newData;
	}
	
	/** Retrieves but does not remove the head of the queue
	 * @return head of the queue
	 */
	public E element() {
		if (size != 0) {
			return theData[front];
		}
		else {
			throw new NoSuchElementException();
		}
	}
	
	/** Inserts specified element into the queue if it is possible without
	 * violating capacity restrictions. 
	 * @return true if successful
	 */
	public boolean add(E e) {
		if (size == capacity) {
			reallocate();
		}
		size++;
		rear = (rear + 1) % capacity;
		theData[rear] = e;
		return true;
	}
	
	/** Retrieves and removes the head of this queue.
	 * @return head of queue
	 */
	public E remove() {
		E returnValue = null;
        
		if (size == 0) {
			throw new NoSuchElementException();
		}
		else {
			returnValue = theData[front];	
			front = (front + 1) % capacity;
		}
		size--;
		return returnValue;
	}

	@Override
	public Iterator<E> iterator() {
		return new CircularIterator();
	}

	@Override
	public int size() {
		return size;
	}
	
	private class CircularIterator implements Iterator<E> {
		private int index;
		private int count = 0;
		
		public CircularIterator() {
			index = front;
		}
		
		/** Returns true if there are more elements in queue to access. */
		@Override
		public boolean hasNext() {
			return count < size;
		}
		
		/** Returns next element in the queue.
		 * @return element with subscript index
		 */
		@Override
		public E next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			E returnValue = theData[index];
			index = (index + 1) % capacity;
			count++;
			return returnValue;
		}
		
		/** Remove item accessed by object. */
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

}
