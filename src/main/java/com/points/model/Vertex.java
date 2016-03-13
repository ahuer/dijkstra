package com.points.model;


public class Vertex<T> implements Comparable<Object> {
	private T data;
	
	public Vertex(T data) {
		this.data = data;
	}
			
	public T getData() {
		return data;
	}

	@Override
	public int compareTo(Object arg0) {
		return 0;
	}
	
}
