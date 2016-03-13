package com.points.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph<T> {
	private List<Vertex<T>> vertices;
	private List<Edge<T>> edges;
	private Map<Vertex<T>, Map<Vertex<T>, Number>> edgeWeights;

	public Graph(List<Vertex<T>> vertices, List<Edge<T>> edges) {
		this.vertices = vertices;
		this.edges = edges;
	}
	
//	public Graph(List<Vertex<T>> vertices, List<Edge<T>> edges) {
//		this.vertices = vertices;
//		this.edges = edges;
//	}

	public List<Vertex<T>> getVertices() {
		return vertices;
	}

	public List<Edge<T>> getEdges() {
		return edges;
	}
	
	public Map<Vertex<T>, Map<Vertex<T>, Number>> getEdgeWeights() {
		if (edgeWeights == null) {
			populateEdgeWeights();
		}
		
		return edgeWeights;
	}
	
	private void populateEdgeWeights() {
		edgeWeights = new HashMap<>();
		for (Edge<T> edge : edges) {
			Vertex<T> start = edge.getStartVertex();

			if (!edgeWeights.containsKey(start)) {
				Map<Vertex<T>, Number> endAndWeight = new HashMap<>();
				endAndWeight.put(edge.getEndVertex(), edge.getWeight());
				edgeWeights.put(start, endAndWeight);
				continue;
			}
			
			Map<Vertex<T>, Number> startMap = edgeWeights.get(start);
			startMap.put(edge.getEndVertex(), edge.getWeight());
		}
	}

}
