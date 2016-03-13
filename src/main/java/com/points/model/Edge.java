package com.points.model;

public class Edge<T> {
	private Vertex<T> startVertex;
	private Vertex<T> endVertex;
	private Number weight;
	
	public Edge(Vertex<T> startVertex, Vertex<T> endVertex, Number weight) {
		this.startVertex = startVertex;
		this.endVertex = endVertex;
		this.weight = weight;
	}
	
	public Number getWeight() {
		return weight;
	}
	
	public Vertex<T> getStartVertex() {
		return startVertex;
	}
	
	public Vertex<T> getEndVertex() {
		return endVertex;
	}
}
