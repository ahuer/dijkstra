package com.points.shortestPath;

import java.util.ArrayList;
import java.util.List;

import com.points.model.Graph;
import com.points.model.Vertex;
import com.points.shortestPath.breadthFirst.BreadthNode;
import com.points.shortestPath.breadthFirst.BreadthTree;

public class BreadthFirst<T> {
	private Graph<T> graph;
	private List<Vertex<T>> vertices;
	private BreadthNode<T> root;
	
	private BreadthNode<T> shortestPathEndNode;
	private int shortestPathTotal;
	private Vertex<T> endPoint;
	
	public BreadthFirst(Graph<T> graph) throws IllegalArgumentException {
		if (graph == null || graph.getVertices() == null || graph.getEdges() == null ) {
			throw new IllegalArgumentException("Invalid graph provided");
		}
		
		this.graph = graph;
		this.vertices = graph.getVertices();	
	}
	
	public int getShortestPathTotal() {
		return shortestPathTotal;
	}
	
	public BreadthNode<T> getShortestPathEndNode() {
		return shortestPathEndNode;
	}
	
	public List<Vertex<T>> getShortestPathList() {
		if (shortestPathEndNode == null ) {
			return null;
		}
		
		List<Vertex<T>> reversePath = new ArrayList<>();
		BreadthNode<T> currentNode = shortestPathEndNode;
		reversePath.add(currentNode.getData());
		
		while (currentNode.getParent() != null ) {
			BreadthNode<T> parentNode = currentNode.getParent();
			reversePath.add(parentNode.getData());
			currentNode = parentNode;
		}	
		
		List<Vertex<T>> path = new ArrayList<>(reversePath.size());
		for (int i = reversePath.size() - 1; i >= 0; i-- ) {
			path.add(reversePath.get(i));
		}
		
		return path;
	}
		
	public int shortestPath(Vertex<T> start, Vertex<T> end) {
		if (!vertices.contains(start) || !vertices.contains(end) ) {
			return 0;
		}
		
		BreadthTree<T> breadthTree = new BreadthTree<>(graph, start);
		root = breadthTree.getRoot();
		
		if (root == null ) {
			return 0;
		}
		
		shortestPathEndNode = null;
		shortestPathTotal = Integer.MAX_VALUE;
		endPoint = end;
		
		bfs(root);
		
		if (shortestPathTotal == Integer.MAX_VALUE ) {
			return 0;
		}
		return shortestPathTotal;
	}
	
	private void bfs(BreadthNode<T> currentNode) {
		Vertex<T> currentVertex = currentNode.getData();
		
		if (currentVertex == endPoint ) {
			if (currentNode.getPathTotal() < shortestPathTotal ) {
				shortestPathTotal = currentNode.getPathTotal();
				shortestPathEndNode = currentNode;
			}
			return;
		}
		
		for (BreadthNode<T> child : currentNode.getChildrenList() ) {
			bfs(child);
		}
	}

}
