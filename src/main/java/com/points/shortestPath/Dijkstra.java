package com.points.shortestPath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.points.model.Graph;
import com.points.model.Vertex;

public class Dijkstra<T> {
	private List<Vertex<T>> vertices;
	private Map<Vertex<T>, Map<Vertex<T>, Number>> edgeWeights; 
	private Map<Vertex<T>, Vertex<T>> predecessors = new HashMap<>();
	private List<Vertex<T>> path;
	
	public Dijkstra(Graph<T> graph) throws IllegalArgumentException {
		if (graph == null || graph.getVertices() == null || graph.getEdges() == null ) {
			throw new IllegalArgumentException("Invalid graph provided");
		}
		
		this.vertices = graph.getVertices();
		this.edgeWeights = graph.getEdgeWeights();
	}
	
	public List<Vertex<T>> getShortestPathList() {
		return path;
	}
	
	private void calculateShortestPathList(Vertex<T> start, Vertex<T> end) {
		List<Vertex<T>> reversePath = new ArrayList<>();
		Vertex<T> currentVertex = end;
		reversePath.add(end);
		
		while (currentVertex != start ) {
			Vertex<T> previous = predecessors.get(currentVertex); 
			reversePath.add(previous);
			currentVertex = previous;
		}
		
		path = new ArrayList<>();
		for (int i = reversePath.size() - 1; i >= 0; i-- ) {
			path.add(reversePath.get(i));
		}
	}
	
	public int shortestPath(Vertex<T> start, Vertex<T> end) {
		if (!vertices.contains(start) || !vertices.contains(end) ) {
			return 0;
		}
		
		Set<Vertex<T>> verticesTraversed = new HashSet<>();		
		Map<Vertex<T>, Integer> currentValues = new HashMap<>();
		for (Vertex<T> v : vertices ) {
			currentValues.put(v, Integer.MAX_VALUE);
		}
		
		currentValues.put(start, 0);
		verticesTraversed.add(start);
		
		Vertex<T> currentVertex = start;
		
		while(currentVertex != null) {
			int lowestWeight = Integer.MAX_VALUE;
			Vertex<T> nextVertex = null;
			
			Map<Vertex<T>, Number> currentVertexEdges = edgeWeights.get(currentVertex);
			
			for (Vertex<T> vertex : currentVertexEdges.keySet()) {
				
				if (verticesTraversed.contains(vertex) ) {
					continue;
				}
				
				int weight = currentValues.get(currentVertex) + currentVertexEdges.get(vertex).intValue();
				if (weight < (currentValues.get(vertex)) ) {
					currentValues.put(vertex, weight);
					predecessors.put(vertex, currentVertex);
				}
				
				if (currentValues.get(vertex) < lowestWeight ) {
					lowestWeight = currentValues.get(vertex);
					nextVertex = vertex;
				}
			}
			
			if (nextVertex != null ) {
				verticesTraversed.add(nextVertex);
			}
			currentVertex = nextVertex;			
		}		
		
		calculateShortestPathList(start, end);
		return currentValues.get(end);
	}
	
}
