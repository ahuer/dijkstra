package com.points.shortestPath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.points.model.Edge;
import com.points.model.Graph;
import com.points.model.Vertex;

public class BreadthFirst {
	private Graph graph;
	private List<Vertex> vertices;
	private List<Vertex> shortestPath;
	private int shortestPathTotal;
	private Vertex endPoint;
	
	public BreadthFirst(Graph graph) throws IllegalArgumentException {
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
		
		shortestPath = new ArrayList<>();
		shortestPathTotal = Integer.MAX_VALUE;
		endPoint = end;
		
		Map<Vertex, Boolean> visited = new HashMap<>();
		for (Vertex v : vertices ) {
			visited.put(v, false);
		}
		
		int currentTotal = 0;
		List<Vertex> currentPath = new ArrayList<>();
		currentPath.add(start);
		visited.put(start, true);
		
		bfs(currentTotal, currentPath, visited);
		
		return shortestPath;
	}
	
	private void bfs(int currentTotal, List<Vertex> currentPath, Map<Vertex, Boolean> visited) {
		Vertex lastInList = currentPath.get(currentPath.size() - 1);
		
		if (lastInList == endPoint ) {
			if (currentTotal < shortestPathTotal ) {
				shortestPath = new ArrayList<>();
				shortestPath.addAll(currentPath);
				shortestPathTotal = currentTotal;
			}
			return;
		}
		
		List<Vertex> childrenToVisit = getUnvisitedChildren(lastInList, visited);		
		if (childrenToVisit.size() == 0 ) {
			return;
		}
		
		for (Vertex child : childrenToVisit ) {
			int weight = lastInList.getEdgeWeight(child);
			currentPath.add(child);
			visited.put(child, true);
			bfs(currentTotal + weight, currentPath, visited);
			
			//reset for next child
			currentPath.remove(currentPath.size() - 1);
			visited.put(child, false);
		}
	}
	
	private List<Vertex> getUnvisitedChildren(Vertex lastInList, Map<Vertex, Boolean> visited) {
		List<Vertex> childrenToVisit = new ArrayList<>();
		for (Edge edge : lastInList.getEdges() ) {
			Vertex v = edge.getVertex();
			if (visited.get(v) == false) {
				childrenToVisit.add(v);
			}
		}
		return childrenToVisit;
	}	

}
