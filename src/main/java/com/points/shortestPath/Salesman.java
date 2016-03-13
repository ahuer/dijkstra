package com.points.shortestPath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.points.model.Graph;
import com.points.model.Vertex;

public class Salesman<T> {
	private List<Vertex<T>> vertices;
	private Map<Vertex<T>, Map<Vertex<T>, Number>> edgeWeights; 
	private List<Vertex<T>> shortestPathList;
	private Map<Vertex<T>, Vertex<T>> predecessors;
	
	public Salesman (Graph<T> graph) throws IllegalArgumentException {
		if (graph == null || graph.getVertices() == null ) {
			throw new IllegalArgumentException("Invalid graph provided");
		}
		
		this.vertices = graph.getVertices();
		this.edgeWeights = graph.getEdgeWeights();
	}
	
	public int shortestPath(Vertex<T> start) throws IllegalArgumentException {
		if (!vertices.contains(start) ) {
			return -1;
		}
		
		Set<Vertex<T>> unvisitedNeighbors = new HashSet<>();
		for (Vertex<T> v : vertices ) {
			unvisitedNeighbors.add(v);
		}		
		unvisitedNeighbors.remove(start);
		
		int shortestPathTotal = 0;
		predecessors = new HashMap<>();
		Vertex<T> currentVertex = start;
		
		while (!unvisitedNeighbors.isEmpty() ) {
			Vertex<T> nearestNeighbor = findNearestNeighbor(currentVertex, unvisitedNeighbors);
			
			if (nearestNeighbor == null ) {
				throw new IllegalArgumentException("Path could not be found");
			}
			
			shortestPathTotal += edgeWeights.get(currentVertex).get(nearestNeighbor).intValue();
			predecessors.put(nearestNeighbor, currentVertex);
			unvisitedNeighbors.remove(nearestNeighbor);
			currentVertex = nearestNeighbor;
		}
		
		// final vertex connects back to start
		int returnWeight = edgeWeights.get(currentVertex).get(start).intValue();
		
		updateShortestPathList(start, currentVertex);
		return shortestPathTotal + returnWeight;
	}
	
	private Vertex<T> findNearestNeighbor(Vertex<T> currentVertex, Set<Vertex<T>> unvisitedNeighbors) {
		int nearestNeighborValue = Integer.MAX_VALUE;
		Vertex<T> nearestNeighbor = null;
		
		Map<Vertex<T>, Number> currentVertexEdges = edgeWeights.get(currentVertex);
		for (Vertex<T> neighbor : currentVertexEdges.keySet() ) {

			if (!unvisitedNeighbors.contains(neighbor) ) {
				continue;
			}
			
			int neighborWeight = currentVertexEdges.get(neighbor).intValue();
			if (neighborWeight < nearestNeighborValue ) {
				nearestNeighborValue = neighborWeight;
				nearestNeighbor = neighbor;					
			}				
		}
		
		return nearestNeighbor;
	}
	
	public void updateShortestPathList(Vertex<T> start, Vertex<T> end) {
		shortestPathList = new ArrayList<>();
		List<Vertex<T>> reversePath = new ArrayList<>();
		reversePath.add(start);
		
		Vertex<T> currentVertex = end;
		while (currentVertex != null ) {
			reversePath.add(currentVertex);
			currentVertex = predecessors.get(currentVertex);
		}
		
		for (int i = reversePath.size() - 1; i >=0; i-- ) {
			shortestPathList.add(reversePath.get(i));
		}
	}
	
	public List<Vertex<T>> getShortestPathList() {
		return shortestPathList;
	}

}
