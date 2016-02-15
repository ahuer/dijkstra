package com.points.shortestPath;

import java.util.List;

public class Vertex {
	private String name;
	private List<ConnectedNode> connectedNodes;
	
	public Vertex(String name) {
		this.name = name;
	}
		
	public Vertex(String name, List<ConnectedNode> connectedNodes) {
		this.name = name;
		this.connectedNodes = connectedNodes;
	}

}
