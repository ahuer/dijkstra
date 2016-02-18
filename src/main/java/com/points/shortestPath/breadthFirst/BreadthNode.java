package com.points.shortestPath.breadthFirst;

import java.util.List;

import com.points.model.Vertex;
import com.points.tree.Node;

public class BreadthNode extends Node<Vertex> {
	private BreadthNode parent;
	
	public BreadthNode(Vertex data, BreadthNode parent) {
		super(data);
		this.parent = parent;
	}
	
	public BreadthNode getParent() {
		return parent;
	}

}
