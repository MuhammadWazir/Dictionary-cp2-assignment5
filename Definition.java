package Dictionary;

public class Definition {
	private String word, description;
	
	//Constructor
	public Definition(String w, String d) {
		word =w;description=d;
	}
	//accessors
	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String compare(Definition d, int i) {
	    // Compare the characters at index 'i' in the two words
	    if ((int) getWord().charAt(i) < (int) d.getWord().charAt(i)) {
	        return "less";
	    } else if ((int) getWord().charAt(i) > (int) d.getWord().charAt(i)) {
	        return "greater";
	    } else {
	        if (i < getWord().length() && i < d.getWord().length()) {
	            // If characters are equal, compare the next characters recursively
	            return compare(d, i + 1);
	        } else if (i < getWord().length()) {
	            return "greater";
	        } else if (i < d.getWord().length()) {
	            return "less";
	        } else {
	            return "equal";
	        }
	    }
	}

	public String compare(String s, int i) {
	    // Compare the characters at index 'i' in the word and the provided string
	    if ((int) getWord().charAt(i) < (int) s.charAt(i)) {
	        return "less";
	    } else if ((int) getWord().charAt(i) > (int) s.charAt(i)) {
	        return "greater";
	    } else {
	        if (i < getWord().length() && i < s.length()) {
	            // If characters are equal, compare the next characters recursively
	            return compare(s, i + 1);
	        } else if (i < getWord().length()) {
	            return "greater";
	        } else if (i < s.length()) {
	            return "less";
	        } else {
	            return "equal";
	        }
	    }
	}
	//String representation
	public String toString() {
		return word + ":"+description;
	}
}
