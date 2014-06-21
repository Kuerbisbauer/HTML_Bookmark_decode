package kb.bookmarkUnits;

import java.util.ArrayList;

public class Directory extends Item {

	private ArrayList<Link> linkList = new ArrayList<Link>();

	public void addLinkToList(Link link){
		linkList.add(link);
	}
	
	public ArrayList<Link> getLinkList() {
		return linkList;
	}
	public void setLinkList(ArrayList<Link> linkList) {
		this.linkList = linkList;
	}
}
