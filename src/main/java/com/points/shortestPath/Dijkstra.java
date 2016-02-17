package com.points.shortestPath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.points.model.Graph;
import com.points.model.Vertex;

public class Dijkstra {
	private Graph graph;
	private List<Vertex> vertices;
	
	public Dijkstra(Graph graph) throws IllegalArgumentException {
		if (graph == null || graph.getVertices() == null ) {
			throw new IllegalArgumentException("Invalid graph provided");
		}
		
		this.graph = graph;
		this.vertices = graph.getVertices();
	}
	
	public List<Vertex> shortestPath(Vertex start, Vertex end) {
		
		if (!vertices.contains(start) || !vertices.contains(end) ) {
			return null;
		}
		
		List<Vertex> pathTaken = new ArrayList<>();
		List<Vertex> availableVertices = new ArrayList<>();		
		Map<Vertex, Integer> currentValues = new HashMap<>();
		initialize(availableVertices, currentValues);
		
		currentValues.put(start, 0);
		pathTaken.add(start);
		availableVertices.remove(start);
		int currentTotal = 0;		
		
		
		return null;
	}
	
	private void initialize(List<Vertex> availableVertices, Map<Vertex, Integer> currentValues) {
		for (Vertex v : vertices ) {
			availableVertices.add(v);
		}
		
		for (Vertex v : vertices ) {
			currentValues.put(v, 99999);
		}		
	}

}
