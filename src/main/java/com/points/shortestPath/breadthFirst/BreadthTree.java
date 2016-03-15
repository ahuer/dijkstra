package com.points.shortestPath.breadthFirst;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.points.model.Graph;
import com.points.model.Vertex;

public class BreadthTree<T> {
	private BreadthNode<T> root;
	private List<Vertex<T>> vertices;
	private Map<Vertex<T>, Map<Vertex<T>, Number>> edges; 
	
	public BreadthTree(Graph<T> graph, Vertex<T> start) {
		this.vertices = graph.getVertices();
		this.edges = graph.getEdgeWeights();
		root = populateRootNode(graph, start);
	}
	
	private BreadthNode<T> populateRootNode(Graph<T> graph, Vertex<T> start) {
		if (graph == null || vertices == null || edges == null  ) {
			return null;
		}
		
		if (start == null || !vertices.contains(start) ) {
			return null;
		}
		
		BreadthNode<T> rootNode = new BreadthNode<>(start, null, 0);
		Queue<BreadthNode<T>> nodeQueue = new ConcurrentLinkedQueue<>();
		nodeQueue.add(rootNode);
		
		while (!nodeQueue.isEmpty() ) {
			BreadthNode<T> currentNode = nodeQueue.poll();
			Vertex<T> currentVertex = currentNode.getData();
			Set<Vertex<T>> ancestors = getAncestors(currentNode);
			
			int childCount = 0;
			for (Vertex<T> child : edges.get(currentVertex).keySet() ) {

				if (ancestors.contains(child) ) {
					continue;
				}
				
				int currentPath = edges.get(currentVertex).get(child).intValue();
				BreadthNode<T> childNode = new BreadthNode<>(child, currentNode, currentNode.getPathTotal() + currentPath);
				nodeQueue.add(childNode);
				currentNode.setChild(childNode, childCount);
				childCount ++;								
			}
		}
		
		return rootNode;
	}
	
	private Set<Vertex<T>> getAncestors(BreadthNode<T> node) {
		Set<Vertex<T>> ancestors = new HashSet<>();
		BreadthNode<T> currentNode = node;
		
		while (currentNode.getParent() != null ) {
			BreadthNode<T> parentNode = currentNode.getParent();
			ancestors.add(parentNode.getData());
			currentNode = parentNode;
		}		
		
		return ancestors;
	}
	
	public BreadthNode<T> getRoot() {
		return root;
	}

}
