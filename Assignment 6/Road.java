// @author Andy Cruz

public class Road implements Comparable<Road> {
	private String roadName;
	private Town roadSource, roadDestination;
	private int weight;
	
	/**

	 * @param source
	 * @param destination
	 * @param degrees
	 * @param name
	 */
	public Road(Town source, Town destination, int degrees, String name) {
		roadName = name;
		roadSource = source;
		roadDestination = destination;
		weight = degrees;
	}
	
	/**
	 * @param source 
	 * @param destination 
	 * @param name
	 */
	
	public Road(Town source, Town destination, String name) {
		roadName = name;
		roadSource = source;
		roadDestination = destination;
		weight = 1;
	}
	
	/**
	 * @param town
	 * @return true if the town is the road's source or destination and false if not
	 */
	
	public boolean contains(Town town) {
		if(roadSource.equals(town)) {
			return true;
		}
		if(roadDestination.equals(town)) {
			return true;
		}
		return false;
	}
	
	/**
	 * @param o - another road
	 */
	
	@Override
	public int compareTo(Road o) {
		if(o.getName().equalsIgnoreCase(roadName)) {
			return 0;
		} else {
			return 1;
		}
	}
	
	/**
	 * @param r - another road
	 * @return true if the roads are the same and false if not
	 */
	
	@Override
	public boolean equals(Object r) {
		if(((Road) r).getSource().equals(roadSource) && ((Road) r).getDestination().equals(roadDestination)) {
			return true;
		} else if(((Road) r).getSource().equals(roadDestination) && ((Road) r).getDestination().equals(roadSource)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * @return roadName
	 */
	
	public String getName() {
		return roadName;
	}
	
	/**
	 * @return roadDestination
	 */
	
	public Town getDestination() {
		return roadDestination;
	}
	
	/**
	 * @return
	 */
	
	public Town getSource() {
		return roadSource;
	}
	
	/**
	 * @return weight
	 */
	
	public int getWeight() {
		return weight;
	}
	


	public String toString() {
		String s = roadName + "," + roadSource.toString() + "," + roadDestination.toString() + "," + weight;
		return s;
	}

}
