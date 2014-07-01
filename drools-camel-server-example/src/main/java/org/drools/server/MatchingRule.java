package org.drools.server;

public class MatchingRule {
	public int id;
	public String content;
	
	public MatchingRule(int id, String content) {
		this.id = id;
		this.content = content;
	}
	
	@Override
	public String toString() {
		return "" + id + "-" + content;
	}
}
