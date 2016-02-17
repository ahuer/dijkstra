package com.points.shortestPath.breadthFirst;

import com.points.model.Vertex;
import com.points.tree.Node;

public class BreadthNode extends Node {
	private BreadthNode parent;
	
	public BreadthNode(BreadthNode parent, Vertex data) {
		super((Comparable) data);
		this.parent = parent;
	}

}
