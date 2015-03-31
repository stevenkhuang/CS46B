import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class encapsulates an order in
 * an online store.
 * 
 * Draft: Add the methods in the Loadable
 * interface. Leave the implementation
 * empty.
 * 
 * Final: Add the implementation for the 
 * Loadable interface. 
 *
 */
public class Order implements Loadable
{
	private ArrayList<Integer> productId;
	private ArrayList<Integer> quantity;

	public Order()
	{
		productId = new ArrayList<Integer>();
		quantity = new ArrayList<Integer>();
	}

	public void addProduct(int id, int qty)
	{
		productId.add(id);
		quantity.add(qty);
	}

	public int size()
	{
		return productId.size();
	}

	public String toString()
	{
		return "Order [productId=" + productId.toString() + ", quantity=" + quantity.toString() + "]";
	}

	@Override
	public void saveToFile(String filename) {
		try(PrintWriter out = new PrintWriter(filename)){
			for(int i:productId){

				out.print( productId.get(i-1));
				out.print(","+quantity.get(i-1)+"\n");
			}
			out.close();
		}

		catch(Exception FilenotFound){
			System.out.println("Unable to save order to "+filename +"\n");
		}
	}


	@Override
	public void loadFromFile(String filename) {
		try(Scanner in = new Scanner(new File(filename))){
			while(in.hasNext()){
				in.useDelimiter(",|\\n");
				int ID=Integer.parseInt(in.next());
				int quan=Integer.parseInt(in.next().replaceAll("\\s", ""));
				addProduct(ID,quan) ;
			}
			in.close();
		}
		catch(FileNotFoundException e  ){
			System.out.println("Unable to load order from"+filename+ ".");
		}
		catch(Exception i){
			System.out.println("Unable to load order from "+filename+".");
		}
	}

}
