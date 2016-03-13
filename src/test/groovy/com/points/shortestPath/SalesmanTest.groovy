package com.points.shortestPath
import static org.junit.Assert.*

import org.junit.Test

import com.points.model.Edge
import com.points.model.Graph
import com.points.model.Vertex

public class SalesmanTest {

	@Test
	public void testSmallGraph() {
		def vertA = new Vertex("A")
		def vertB = new Vertex("B")
		def edgeAToB = new Edge(vertA, vertB, 7)
		def edgeBToA = new Edge(vertB, vertA, 7)
		
		def graph = new Graph([vertA, vertB], [edgeAToB, edgeBToA])
		def salesman = new Salesman(graph)
		def result = salesman.shortestPath(vertA)
		assertEquals(14, result)
		assertEquals([vertA, vertB, vertA], salesman.getShortestPathList())
	}
	
	@Test
	public void testLargeGraph() {
		def vertA = new Vertex("A")
		def vertB = new Vertex("B")
		def vertC = new Vertex("C")
		def vertD = new Vertex("D")
		def edgeAToB = new Edge(vertA, vertB, 8)
		def edgeAToC = new Edge(vertA, vertC, 9)
		def edgeAToD = new Edge(vertA, vertD, 7)
		def edgeBToA = new Edge(vertB, vertA, 8)
		def edgeBToC = new Edge(vertB, vertC, 5)
		def edgeBToD = new Edge(vertB, vertD, 4)
		def edgeCToA = new Edge(vertC, vertA, 9)
		def edgeCToB = new Edge(vertC, vertB, 5)
		def edgeCToD = new Edge(vertC, vertD, 6)
		def edgeDToA = new Edge(vertD, vertA, 7)
		def edgeDToB = new Edge(vertD, vertB, 4)
		def edgeDToC = new Edge(vertD, vertC, 6)
		
		def edges = [edgeAToB, edgeAToC, edgeAToD, edgeBToA, edgeBToC, edgeBToD,
			edgeCToA, edgeCToB, edgeCToD, edgeDToA, edgeDToB, edgeDToC]
		
		def graph = new Graph([vertA, vertB, vertC, vertD], edges)
		def salesman = new Salesman(graph)
		def result = salesman.shortestPath(vertA)
		assertEquals(25, result)
		assertEquals([vertA, vertD, vertB, vertC, vertA], salesman.getShortestPathList())
	}
}