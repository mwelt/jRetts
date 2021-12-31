package de.ubmw.jRetts;

import java.util.List;

public class U {
	public static void nonNull(Object o, String name) throws JRettsError{
		if(o == null) {
			throw new JRettsError(name + " can not be null.");
		}
	}
	
	public <T> List<T> take(List<T> l, int n) throws JRettsError {
		nonNull(l, "list");

		if(n < 0) { 
			throw new JRettsError("headN - n must be greater or equal to zero."); 
		}

		if(l.size() < n) { 
			throw new JRettsError("headN - n must be greater or equal to size of l."); 
		}

		return l.subList(0, n);
	}
	
	public record Pair<K, T>(K left, T right){};
	
	public static String indent(int indent) {
		StringBuffer b = new StringBuffer();
		for(int i = 0; i < indent; i++) {
			b.append("  ");
		}
		return b.toString();
	}
	
}
