package com.points.shortestPath;

public class ConnectedNode {
	private String name;
	private int weight;
	
	public ConnectedNode(String name, int weight) {
		this.name = name;
		this.weight = weight;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public String getName() {
		return name;
	}
}
