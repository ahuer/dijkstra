package com.points.shortestPath

import static org.junit.Assert.*
import org.junit.Test

class BreadthFirstTest {
	
	@Test
	public void testSmallBFS() {
		def vertA = new Vertex("A")
		def vertB = new Vertex("B")
		def edgeToA = new Edge(vertA, 7)
		def edgeToB = new Edge(vertB, 7)
		vertA.setEdges([edgeToB])
		vertB.setEdges([edgeToA])
		
		def graph = new Graph([vertA, vertB])
		def breadth = new BreadthFirst(graph)
		
		// works from A to B
		def result = breadth.shortestPath(vertA, vertB)
		assertEquals([vertA, vertB], result)
		assertEquals(7, breadth.shortestPathTotal)
		
		// works from B to A
		result = breadth.shortestPath(vertB, vertA)
		assertEquals([vertB, vertA], result)
		assertEquals(7, breadth.shortestPathTotal)		
	}
	
	@Test
	public void testLargeBFS() {
		def vertA = new Vertex("A")
		def vertB = new Vertex("B")
		def vertC = new Vertex("C")
		def vertD = new Vertex("D")
		def vertE = new Vertex("E")
		def edgeAToB = new Edge(vertB, 4)
		def edgeBToA = new Edge(vertA, 4)
		def edgeAToE = new Edge(vertE, 3)
		def edgeEToA = new Edge(vertA, 3)
		def edgeBToE = new Edge(vertE, 7)
		def edgeEToB = new Edge(vertB, 7)
		def edgeBToC = new Edge(vertC, 1)
		def edgeCToB = new Edge(vertB, 1)
		def edgeBToD = new Edge(vertD, 6)
		def edgeDToB = new Edge(vertB, 6)
		def edgeEToC = new Edge(vertC, 5)
		def edgeCToE = new Edge(vertE, 5)
		
		vertA.setEdges([edgeAToB, edgeAToE])
		vertB.setEdges([edgeBToA, edgeBToE, edgeBToC, edgeBToD])
		vertC.setEdges([edgeCToB, edgeCToE])
		vertD.setEdges([edgeDToB])
		vertE.setEdges([edgeEToA, edgeEToB, edgeEToC])
		
		def graph = new Graph([vertA, vertB, vertC, vertD, vertE])
		def breadth = new BreadthFirst(graph)
		
		def result = breadth.shortestPath(vertA, vertD)
		assertEquals([vertA, vertB, vertD], result)
		assertEquals(10, breadth.shortestPathTotal)
	}

}
