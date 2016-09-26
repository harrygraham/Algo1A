package uk.ac.cam.hg402.algorithms.tick3;

import uk.ac.cam.rkh23.Algorithms.Tick3.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph extends GraphBase{

public Graph(URL url) throws IOException {
		super(url);
	}

public Graph(String file) throws IOException {
		super(file);
	}

	public Graph(int[][] adj) {
		super(adj);
	}

	@Override
	public List<Integer> getFewestEdgesPath(int src, int target) throws TargetUnreachable {
		
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean[] visited = new boolean[mN];
		int[] predecessorNodes = new int[mN];
		
		visited[src] = true;
		queue.add(src);
		
		while(!queue.isEmpty() && !visited[target]){
			int u = queue.remove();
			for(int v = 0; v <mN; v++){
				if (mAdj[u][v] > 0 && !visited[v]) {
					
					predecessorNodes[v] =u;
					visited[v]= true;
					if (v == target) {
						break;
					}
					queue.add(v);
				}
			}
		}
		
		if(!visited[target]){
			throw new TargetUnreachable();
		}
		LinkedList<Integer> resultPath = new LinkedList<Integer>();
		int current = target;
		resultPath.addFirst(target);
		
		while(current != src){
			current = predecessorNodes[current];
			resultPath.addFirst(current);
		}
		
		return resultPath;
		
	}

	@Override
	public MaxFlowNetwork getMaxFlow(int s, int t) {
	
		
		Graph flowNet = new Graph(new int[mN][mN]);
		// let flownet = current graph instance, this is our flow network, 'this' is our residual.
		
				int flow = 0;
		while(true){
			try {
				
				
				List<Integer> path = this.getFewestEdgesPath(s, t);
				System.out.println(path);
				
				
				int minCapacity = Integer.MAX_VALUE;
				
				for(int i = 0; i < path.size()-1; i++) {
					int u = path.get(i);
					int v = path.get(i+1);
					
					minCapacity = Math.min(mAdj[u][v], minCapacity);
					
				
				}
				
				flow += minCapacity;
				
				for(int i = 0; i < path.size()-1; i++) {
					int u = path.get(i);
					int v = path.get(i+1);
					
					//subtract flow -- add reverse flow to residual capacity graph
					mAdj[u][v] -= minCapacity;
					mAdj[v][u] += minCapacity;
					
					//add flow to flownetwork so flownet[u][v] += mincapacity 
					
				
					
				}
				
				System.out.println(flow);
				
			} catch (TargetUnreachable e) {
				break;
			}
		}
		
		MaxFlowNetwork result = new MaxFlowNetwork(flow, flowNet);
		return result;
	
	}

}
