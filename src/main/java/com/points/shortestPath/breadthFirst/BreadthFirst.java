package com.points.shortestPath.breadthFirst;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.points.model.Edge;
import com.points.model.Graph;
import com.points.model.Vertex;
import com.points.tree.Node;

public class BreadthFirst {
	private Graph graph;
	private List<Vertex> vertices;
	private BreadthNode root;
	
	//private List<Vertex> shortestPath;
	private BreadthNode shortestPathEndNode;
	private int shortestPathTotal;
	private Vertex endPoint;
	
	public BreadthFirst(Graph graph) throws IllegalArgumentException {
		if (graph == null || graph.getVertices() == null ) {
			throw new IllegalArgumentException("Invalid graph provided");
		}
		
		this.graph = graph;
		this.vertices = graph.getVertices();	
	}
	
	public int getShortestPathTotal() {
		return shortestPathTotal;
	}
	
	public BreadthNode getShortestPathEndNode() {
		return shortestPathEndNode;
	}
	
	public List<Vertex> getShortestPathList() {
		if (shortestPathEndNode == null ) {
			return null;
		}
		
		List<Vertex> reversePath = new ArrayList<>();
		BreadthNode currentNode = shortestPathEndNode;
		reversePath.add(currentNode.getData());
		
		while (currentNode.getParent() != null ) {
			BreadthNode parentNode = currentNode.getParent();
			reversePath.add(parentNode.getData());
			currentNode = parentNode;
		}	
		
		List<Vertex> path = new ArrayList<>(reversePath.size());
		for (int i = reversePath.size() - 1; i >= 0; i-- ) {
			path.add(reversePath.get(i));
		}
		
		return path;
	}
		
	public BreadthNode shortestPath(Vertex start, Vertex end) {
		if (!vertices.contains(start) || !vertices.contains(end) ) {
			return null;
		}
		
		BreadthTree breadthTree = new BreadthTree(graph, start);
		root = breadthTree.getRoot();
		
		if (root == null ) {
			return null;
		}
		
		shortestPathEndNode = null;
		shortestPathTotal = Integer.MAX_VALUE;
		endPoint = end;
		int currentTotal = 0;
		
		bfs(currentTotal, root);
		
		return shortestPathEndNode;
	}
	
	private void bfs(int currentTotal, BreadthNode currentNode) {
		Vertex currentVertex = currentNode.getData();
		
		if (currentVertex == endPoint ) {
			if (currentTotal < shortestPathTotal ) {
				shortestPathTotal = currentTotal;
				shortestPathEndNode = currentNode;
			}
			return;
		}
		
		List<BreadthNode> children = (List<BreadthNode>) currentNode.getChildren().values();
		
		for (BreadthNode child : currentNode.getChildren().values() ) {
			int weight = currentVertex.getEdgeWeight(child);
			visited.add(child);
			bfs(currentTotal + weight, child, visited);
			
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
