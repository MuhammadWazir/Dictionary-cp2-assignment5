package Dictionary;
import java.util.Vector;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Dictionary {
	private BST bst;
	private Vector temp;
	private String file_name;
	public Dictionary() {
		bst=null;
	}
	private void toVector(BSTNode b) {
        // Recursively adds BSTNode's Definition to the Vector
        if (b != null) {
            temp.add(b.getInfo());
        }
        if (b.getLeft() != null) {
            toVector(b.getLeft());
        }
        if (b.getRight() != null) {
            toVector(b.getRight());
        }
    }
	public void delete() {
	    Scanner scan = new Scanner(System.in);

	    System.out.println("Enter the word you want to delete: ");
	    String word = scan.next();
	    Definition d;
	    int count = 2;

	    // Count the occurrences of the word in the dictionary
	    for (Object o : temp) {
	        if (o instanceof Definition) {
	            d = (Definition) o;
	            if ((d.getWord()).equals(word))
	                count++;
	        }
	    }

	    if (count == 2) {
	        System.out.println("The definition you want to delete does not exist.");
	        return;
	    }

	    count = 2;
	    System.out.println("1. Delete all");

	    // Display the options to delete specific definitions
	    for (Object o : temp) {
	        if (o instanceof Definition) {
	            d = (Definition) o;
	            if ((d.getWord()).equals(word)) {
	                System.out.println(count + ". " + d.getDescription());
	                count++;
	            }
	        }
	    }

	    System.out.println("Enter the number of the definition you want to delete: ");
	    int to_delete = scan.nextInt();

	    if (to_delete == 1) {
	        // Delete all occurrences of the word
	        for (Object o : temp) {
	            if (o instanceof Definition) {
	                d = (Definition) o;
	                if ((d.getWord()).equals(word)) {
	                    temp.remove(d);
	                }
	            }
	        }
	    } else {
	        count = 2;
	        // Delete a specific occurrence of the word
	        for (Object o : temp) {
	            if (o instanceof Definition) {
	                d = (Definition) o;
	                if ((d.getWord()).equals(word)) {
	                    if (count == to_delete) {
	                        temp.remove(d);
	                    } else {
	                        count++;
	                    }
	                }
	            }
	        }
	    }

	    // Construct a new BST with the remaining definitions
	    BST new_bst = new BST();
	    for (Object o : temp) {
	        if (o instanceof Definition) {
	            d = (Definition) o;
	            new_bst.add(d);
	        }
	    }
	    bst = new_bst;
	}
	public void search(String word, BSTNode b) {
        // Recursively searches for a Definition with the given word in the BST
        if (((b.getInfo()).getWord()).equals(word)) {
            System.out.println((b.getInfo()).getDescription());
            if (!(((b.getLeft()).getInfo()).getWord()).equals(word)) {
                return;
            } else search(word, b.getLeft());
        } else if (((b.getInfo()).compare(word, 0)).equals("less")) {
            search(word, b.getRight());
        } else {
            search(word, b.getLeft());
        }
    }

    public void createDictionary() throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the name of the file where your definitions are stored: ");
        file_name = scan.next();
        int count = 1;
        do {
            try {
                FileInputStream f = new FileInputStream(file_name);
                InputStreamReader ir = new InputStreamReader(f);
                BufferedReader br = new BufferedReader(ir);
                String line = br.readLine();
                while (line != null) {
                    String[] def_parts = line.split(":");
                    bst.add(new Definition(def_parts[0], def_parts[1]));
                    line = br.readLine();
                }
                break;
            } catch (FileNotFoundException e) {
                System.out.println("File was not found.");
                count++;
            }
        } while (count <= 3);
    }
    public void menu() {
        Scanner scan = new Scanner(System.in);
        System.out.println("1. Create the dictionary\n" +
                           "2. Add a definition\n" +
                           "3. Delete a definition\n" +
                           "4. Search for a definition\n" +
                           "5. Exit\n" +
                           "-----------------------------------\n" +
                           "Enter your choice:");

        int choice = scan.nextInt();

        switch (choice) {
            case 1:
                try {
                    createDictionary(); // Call the createDictionary method to initialize the dictionary from a file
                    break;
                } catch (IOException e) {
                    break;
                }
            case 2:
                System.out.println("Enter the word:");
                String word = scan.next();

                System.out.println("Enter description:");
                String description = scan.next();

                bst.add(new Definition(word, description)); // Add a new definition to the binary search tree
                temp.add(new Definition(word, description)); // Add the new definition to the temporary vector
                break;
            case 3:
                delete(); // Call the delete method to delete a definition from the dictionary
                break;
            case 4:
                System.out.println("Enter the word you want to search for:");
                String to_search = scan.next();
                search(to_search, bst.getRoot()); // Call the search method to find and display the definition of a word
                break;
			case 5:
				//save info to the text file and then exit
				try {
				    FileWriter fileWriter = new FileWriter(file_name);
				    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				    for (Object o : temp) {
				        if (o instanceof Definition) {
				            Definition to_add = (Definition)o;
				            String s= to_add.toString();
				            bufferedWriter.write(s);
				            bufferedWriter.newLine();
				        }
				    }
				    bufferedWriter.close();
				} catch (IOException e) {
				    e.printStackTrace();
				}
				return;
			default:
				break;
		}
		menu();
	}
	public static void main(String[]args) {
		Dictionary dictionary=new Dictionary();
		dictionary.menu();
	}
}
