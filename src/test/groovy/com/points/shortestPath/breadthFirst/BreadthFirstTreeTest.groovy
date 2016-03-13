package com.points.shortestPath.breadthFirst

import static org.junit.Assert.*

import org.junit.Test

import com.points.model.Edge
import com.points.model.Graph
import com.points.model.Vertex

class BreadthFirstTreeTest {
	
	@Test
	public void testSmallTree() {
		def vertA = new Vertex("A")
		def vertB = new Vertex("B")
		def edgeAToB = new Edge(vertA, vertB, 7)
		def edgeBToA = new Edge(vertB, vertA, 7)
		
		def graph = new Graph([vertA, vertB], [edgeAToB, edgeBToA])
		def tree = new BreadthTree(graph, vertA)
		def root = tree.getRoot()
		assertEquals(vertA, root.getData())
		assertEquals(vertB, root.getChild(0).getData())
		assertEquals(root, root.getChild(0).getParent())
	}
	
	@Test
	public void testLargeTree() {
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
		def tree = new BreadthTree(graph, vertA)
		def root = tree.getRoot()
		assertEquals(vertA, root.getData())
		
		// verify tree structure
		def child0 = root.getChild(0)
		def child1 = root.getChild(1)
		assertEquals(vertB, child0.getData())
		assertEquals(vertE, child1.getData())
		
		def child00 = child0.getChild(0)
		def child01 = child0.getChild(1)
		def child02 = child0.getChild(2)
		def child10 = child1.getChild(0)
		def child11 = child1.getChild(1)
		assertEquals(vertE, child00.getData())
		assertEquals(vertC, child01.getData())
		assertEquals(vertD, child02.getData())
		assertEquals(vertB, child10.getData())
		assertEquals(vertC, child11.getData())
		
		def child000 = child00.getChild(0)
		def child010 = child01.getChild(0)
		def child020 = child02.getChild(0)
		def child100 = child10.getChild(0)
		def child101 = child10.getChild(1)
		def child110 = child11.getChild(0)
		def child1100 = child110.getChild(0)
		assertEquals(vertC, child000.getData())
		assertEquals(vertE, child010.getData())
		assertEquals(null, child020)
		assertEquals(vertC, child100.getData())
		assertEquals(vertD, child101.getData())
		assertEquals(vertB, child110.getData())
		assertEquals(vertD, child1100.getData())
		
		assertEquals(null, child000.getChild(0))
		assertEquals(null, child010.getChild(0))
		assertEquals(null, child100.getChild(0))
		assertEquals(null, child101.getChild(0))
		assertEquals(null, child1100.getChild(0))
	}

}
