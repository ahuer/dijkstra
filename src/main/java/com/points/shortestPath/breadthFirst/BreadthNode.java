package com.points.shortestPath.breadthFirst;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.points.model.Vertex;
import com.points.tree.Node;

public class BreadthNode<T> extends Node<Vertex<T>> {
	private BreadthNode<T> parent;
	private int pathTotal;
	
	public BreadthNode(Vertex<T> data, BreadthNode<T> parent, int pathTotal) {
		super(data);
		this.parent = parent;
		this.pathTotal = pathTotal;
	}
	
	public BreadthNode<T> getParent() {
		return parent;
	}
	
	public int getPathTotal() {
		return pathTotal;
	}
	
	public List<BreadthNode<T>> getChildrenList() {
		Map<Integer, Node<Vertex<T>>> childMap = this.getChildren();
		List<BreadthNode<T>> children = new ArrayList<>();
		
		for (Node<Vertex<T>> node : childMap.values() ) {
			children.add((BreadthNode<T>) node);
		}
		return children;
	}

}
