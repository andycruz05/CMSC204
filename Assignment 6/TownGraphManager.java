// @author Andy Cruz

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class TownGraphManager implements TownGraphManagerInterface{

	Graph graph;

	public TownGraphManager() {
		graph=new Graph();
	}
	
	/**
	 * @param town1 name of town 1 
	 * @param town2 name of town 2 
	 * @param roadName name of road
	 * @return true if the road was added successfully
	 */

	@Override
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		Set<Town> towns=graph.vertexSet();
		Town town=null;
		Town town0=null;
		for(Town t:towns) {
			if(t.getName().equalsIgnoreCase(town1)) {
				town=t;
			}
			if(t.getName().equalsIgnoreCase(town2)) {
				town0=t;
			}
			if(town!=null&& town0!=null) {
				break;
			}
		}
		Road r =graph.addEdge(town, town0, weight, roadName);
		if(r==null) {
			return false;
		}
		return true;
	}

	/**
	 * @param town1 name of town 1
	 * @param town2 name of town 2
	 * @return name of road if town 1 and town2 are in the same road, returns null if not
	 */
	
	@Override
	public String getRoad(String town1, String town2) {
		Set<Town> towns=graph.vertexSet();
		Town town=null;
		Town town0=null;
		for(Town t:towns) {
			if(t.getName().equalsIgnoreCase(town1)) {
				town=t;
			}
			if(t.getName().equalsIgnoreCase(town2)) {
				town0=t;
			}
			if(town!=null&& town0!=null) {
				break;
			}
		}
		Road r =graph.getEdge(town, town0);
		if(r==null) {
			return null;
		}
		return r.getName();
	}

	/**
	 * @param v the town's name  
	 * @return true if the town was successfully added, false if not
	 */
	
	@Override
	public boolean addTown(String v) {
		Town town=new Town(v);
		return graph.addVertex(town);
	}

	/**
	 * @param name the town's name 
	 * @return the Town specified by the name, or null if town does not exist
	 */
	
	@Override
	
	public Town getTown(String name) {
		Set<Town> towns=new HashSet<Town>(graph.vertexSet());
		Iterator<Town> it= towns.iterator();
		while(it.hasNext()) {
			Town t= it.next();
			if(t.getName().equalsIgnoreCase(name)) {
				return t;
			}
		}
		return null;
	}

	/**
	 * @param v the town's name 
	 * @return true if the town is in the graph, false if not
	 */
	
	@Override
	public boolean containsTown(String v) {
		Town t=new Town(v);
		return graph.containsVertex(t);
	}

	/**
	 * @param town1 name of town 1 
	 * @param town2 name of town 2 
	 * @return true if the road is in the graph, false if not
	 */
	
	@Override
	public boolean containsRoadConnection(String town1, String town2) {
		Town town=new Town(town1);
		Town town0=new Town(town2);
		return graph.containsEdge(town, town0);
	}

	/**
	 * @return an arraylist of all road titles in sorted order by road name
	 */
	
	@Override
	public ArrayList<String> allRoads() {
		Set<Road> roads=graph.edgeSet();
		ArrayList<String> allRoads=new ArrayList<String>();
		for(Road road:roads) {
			allRoads.add(road.getName());
		}
		Collections.sort(allRoads);
		return allRoads;
	}

	/**
	 * @param town1 name of town 1 
	 * @param town2 name of town 2 
	 * @param road the road name
	 * @return true if the road was successfully deleted, false if not
	 */
	
	@Override
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		Road roadY;
		Town town=new Town(town1);
		Town town0=new Town(town2);
		Road roadX=graph.getEdge(town, town0);
		roadY=graph.removeEdge(town, town0, roadX.getWeight(), road);
		if(roadY!=null) {
			return true;
		}
		return false;
	}
	/**
	 * @param v name of town 
	 * @return true if the town was successfully deleted, false if not
	 */
	
	@Override
	public boolean deleteTown(String v) {
		Town town=new Town(v);
		return graph.removeVertex(town);
	}

	/**
	 * @return an arraylist of all towns in alphabetical order 
	 */
	
	@Override
	public ArrayList<String> allTowns() {
		Set<Town> towns=graph.vertexSet();
		ArrayList<String> allTowns=new ArrayList<String>();
		for(Town town:towns) {
			allTowns.add(town.getName());
		}
		Collections.sort(allTowns);
		return allTowns;
	}

	/**
	 * @param town1 name of town 1 
	 * @param town2 name of town 2 
	 * @return an Arraylist of roads connecting the two towns together, null if the
	 * towns have no path to connect them.
	 */
	
	@Override
	public ArrayList<String> getPath(String town1, String town2) {
		Set<Town> towns=graph.vertexSet();
		Set<Road> roads=graph.edgeSet();
		boolean foundSource=false;
		boolean foundDest=false;
		Town town = null;
		Town town0 = null;
		ArrayList<String> path = new ArrayList<String>();
		try {
			for(Town t:towns) {
				if(t.getName().equalsIgnoreCase(town1)) {
					town=t;
				}
				if(t.getName().equalsIgnoreCase(town2)) {
					town0=t;
				}
				if(town!=null&& town0!=null) {
					break;
				}
			}
			
			for(Road r:roads) {
				if(r.getSource().compareTo(town)==0||r.getDestination().compareTo(town)==0) {
					foundSource=true;
				}
				if(r.getSource().compareTo(town0)==0||r.getDestination().compareTo(town0)==0) {
					foundDest=true;
				}
				if(foundSource==true && foundDest==true) {
					path=graph.shortestPath(town, town0);
					break;
				}
			}
		}
		catch(NullPointerException n) {
			
		}
		
		return path;
	}

	/**
	 * @param selectedFile file to read from
	 * @throws FileNotFoundException thrown when no file if found
	 * @throws IOException otherwise
	 */
	
	public void populateTownGraph(File file) throws FileNotFoundException, IOException {
		// Variables
		ArrayList<String> result = this.readFile(file);
		
		// Loops
		for (String line : result) {
			// Variables
			String[] r = line.split(";");
			String roadName = r[0].split(",")[0];
			int weight = Integer.parseInt(r[0].split(",")[1]);
			String source = r[1].trim();
			String destination = r[2].trim();
			
			// Checks
			this.addTown(source);
			this.addTown(destination);
			this.addRoad(source, destination, weight, roadName);
		}
	}
	
	protected ArrayList<String> readFile(File file) throws FileNotFoundException, IOException {
	
		ArrayList<String> result = new ArrayList<String>();
		Scanner scanner = new Scanner(file);
		
		
		while (scanner.hasNextLine()) {
			result.add(scanner.nextLine());
		}
		
	
		scanner.close();
		return result;
	}

}