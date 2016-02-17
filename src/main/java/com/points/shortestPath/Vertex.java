package com.points.shortestPath;

import java.util.List;

public class Vertex {
	private String name;
	private List<Edge> edges;
	
	public Vertex(String name) {
		this.name = name;
	}
		
	public Vertex(String name, List<Edge> edges) {
		this.name = name;
		this.edges = edges;
	}
	
	public String getName() {
		return name;
	}
	
	public List<Edge> getEdges() {
		return edges;
	}
	
	public void setEdges(List<Edge> edgeList) {
		edges = edgeList;
	}
	
	public int getEdgeWeight(Vertex v) {
		for (Edge e : edges ) {
			if (e.getVertex() == v ) {
				return e.getWeight();
			}
		}
		return 0;
	}

}
