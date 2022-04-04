
import java.util.ArrayList;
public class Runner{
	public static void main(String[] args){
	
	Event animals = new Event("Jan");
	animals.addFrame(new Frame("cat"));
	animals.addFrame(new Frame("dog"));
	animals.addFrame(new Frame("giraffe"));

	Event nature = new Event("Feb");
	nature.addFrame(new Frame("tree"));
	nature.addFrame(new Frame("plant"));
	nature.addFrame(new Frame("leaf"));
	nature.addFrame(new Frame("rock"));
	nature.addFrame(new Frame("sun"));
	nature.addFrame(new Frame("water"));

	Event tree = new Event("tree");
	tree.addFrame(new Frame("branch"));
	tree.addFrame(new Frame("canopy"));
	tree.addFrame(new Frame("leaf"));

	Memory m1 = new Memory(animals, nature, tree);


	//System.out.println(Memory.relevance(animals, "cat"));
	System.out.println(m1.remember("tree", "plant", "water", "sun"));
	}

}





class Memory{
	private ArrayList<Event> events;

	public Memory(){
		events = new ArrayList<Event>();
	}

	public Memory(Event... events){
		this.events = new ArrayList<Event>();
		for(Event e: events){
			this.events.add(e);
		}
	}
	
	public Event remember(String... args) throws RuntimeException{
		if(events.get(0) == null)
			throw new RuntimeException("No events");

		
		Event max = new Event("Doesn't ring a bell...");
		double maxRel = 0;
		for(Event e: events){
			//System.out.println(e);
			if(relevance(e, args) > maxRel){
				max = e;
				maxRel = relevance(e, args);

			}
			e.decWeight();

		}

		max.incWeight();

		
		//get raw weights and # of matches
		//keep track of most relevant
		return max;
	}

	public Event createEvent(String id, String[] args){
		Event current = new Event(id);
		for(String s : args){
			current.addFrame(new Frame(s));
		}
		return current;

	}
	private static double relevance(Event e, String... args){
		
		return e.getWeight() * e.findMatches(args);
	}

}

class Event{
	private String eventID;
	private ArrayList<Frame> frames;
	private double weight;
	private String textBlob;
	private int weightMax = 10;
	private int weightMin = 1;

	public Event(String id){
		this.textBlob = "";
		this.eventID = id;
		frames = new ArrayList<Frame>();
		//frames.add(new Frame("Beginning"));
		this.weight = 10;
	}
	public int findMatches(String... args){
		int numMatches = 0;
		/*for(Frame f: frames){
			for(String s: args){
				if(f.getInfo().contains(s)
					numMatches++;
			}
		}*/
		for(String s:args){
			if(textBlob.contains(s))
				numMatches ++;
			if(eventID.contains(s))
				numMatches += 3;
		}
		return numMatches;
	}






	public Event addFrame(Frame current){
		textBlob += current.getInfo();
		frames.add(current);
		return this;
	}

	public void decWeight(){
		if(weight > weightMin - .25){ //probably dont hardcode this
			this.weight = this.weight - 0.25;
		}
		else{
			weight = weightMin;
		}
	}
	public void incWeight(){
		if(weight < weightMax - 2) //probably dont hardcode this
			weight = weight + 2;
		else
			weight = weightMax;
	}

	public String toString(){
		String output = eventID + ":\n";
		for(Frame f: frames){
			output += f.toString();
			output += "\n";
		}
		return output;
	}
	public String getID(){
		return this.eventID;
	}
	public double getWeight(){
		return weight;
	}


}




