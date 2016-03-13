package com.points.shortestPath

import static org.junit.Assert.*

import org.junit.Test

import com.points.model.Edge
import com.points.model.Graph
import com.points.model.Vertex

class DijkstraTest {
	
	@Test
	public void testSmallGraph() {
		def vertA = new Vertex("A")
		def vertB = new Vertex("B")
		def edgeAToB = new Edge(vertA, vertB, 7)
		def edgeBToA = new Edge(vertB, vertA, 7)
		
		def graph = new Graph([vertA, vertB], [edgeAToB, edgeBToA])
		def dijkstra = new Dijkstra(graph)
		def result = dijkstra.shortestPath(vertA, vertB)
		assertEquals(7, result)
		assertEquals([vertA, vertB], dijkstra.getShortestPathList())
	}
	
	@Test
	public void testLargeGraph() {
		def vertA = new Vertex("A")
		def vertB = new Vertex("B")
		def vertC = new Vertex("C")
		def vertD = new Vertex("D")
		def vertE = new Vertex("E")
		def edgeAToB = new Edge(vertA, vertB, 4)
		def edgeBToA = new Edge(vertB, vertA, 4)
		def edgeAToE = new Edge(vertA, vertE, 3)
		def edgeEToA = new Edge(vertE, vertA, 3)
		def edgeBToE = new Edge(vertB, vertE, 7)
		def edgeEToB = new Edge(vertE, vertB, 7)
		def edgeBToC = new Edge(vertB, vertC, 1)
		def edgeCToB = new Edge(vertC, vertB, 1)
		def edgeBToD = new Edge(vertB, vertD, 6)
		def edgeDToB = new Edge(vertD, vertB, 6)
		def edgeEToC = new Edge(vertE, vertC, 5)
		def edgeCToE = new Edge(vertC, vertE, 5)
		
		def edges = [edgeAToB, edgeAToE, edgeBToA, edgeBToE, edgeBToC, edgeBToD, 
			edgeCToB, edgeCToE, edgeDToB, edgeEToA, edgeEToB, edgeEToC]
		
		def graph = new Graph([vertA, vertB, vertC, vertD, vertE], edges)
		def dijkstra = new Dijkstra(graph)
		def result = dijkstra.shortestPath(vertA, vertD)
		assertEquals(10, result)	
		assertEquals([vertA, vertB, vertD], dijkstra.getShortestPathList())	
	}

}
