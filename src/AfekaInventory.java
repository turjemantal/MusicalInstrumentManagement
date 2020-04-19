import java.util.ArrayList;
import java.util.Collections;

public class AfekaInventory<T extends MusicalInstrument> implements inventory<T> {

	private ArrayList<T> instruments;
	double totalPrice;
	boolean ifSorted;

	public AfekaInventory() {
		// TODO Auto-generated constructor stub
		this.instruments = new ArrayList<T>();
		totalPrice = 0;
		ifSorted = false;
	}

	public ArrayList<T> getInstruments() {
		return instruments;
	}

	public void setInstruments(ArrayList<T> instruments) {
		this.instruments = instruments;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public boolean isIfSorted() {
		return ifSorted;
	}

	public void setIfSorted(boolean ifSorted) {
		this.ifSorted = ifSorted;
	}

	@Override
	public void addAllStringInstrument(ArrayList<? extends MusicalInstrument> a,
			ArrayList<? super MusicalInstrument> b) {
		for (int i = 0; i < a.size(); i++) {
			if (a.get(i) instanceof StringInstrument) {
				b.add(a.get(i));
				this.setTotalPrice(this.getTotalPrice() + a.get(i).getPrice().doubleValue());
			}
		}
		System.out.println("All String Instruments Added Successfully");
	}

	@Override
	public void addAllWindInstrument(ArrayList<? extends MusicalInstrument> a, ArrayList<? super MusicalInstrument> b) {
		for (int i = 0; i < a.size(); i++) {
			if (a.get(i) instanceof WindInstrument) {
				b.add(a.get(i));
				this.setTotalPrice(this.getTotalPrice() + a.get(i).getPrice().doubleValue());
			}
		}
		System.out.println("All wind Instruments Added Successfully");
	}

	@Override
	public void addInstrument(ArrayList<T> a, T b) {
		// TODO Auto-generated method stub

	}

	@Override
	public void SortByBrandAndPrice(ArrayList<? extends MusicalInstrument> list) {
		// TODO Auto-generated method stub
		Collections.sort(list);

	}

	@Override
	public int binnarySearchByBrandAndPrice(ArrayList<? extends MusicalInstrument> list, String brand, Number price) {
		if (list.isEmpty() == true)
			return -1;

		int low = 0;
		int high = list.size() - 1;
		while (low <= high) {

			int mid = (low + high) / 2;
			if (brand.compareTo(list.get(mid).getBrand()) < 0)
				high = mid - 1;
			else if (brand.compareTo(list.get(mid).getBrand()) > 0)
				low = mid + 1;
			else if (list.get(mid).getPrice().doubleValue() == price.doubleValue())
				return mid;
			else if (price.doubleValue() < list.get(mid).getPrice().doubleValue())
				high = mid - 1;
			else
				low = mid + 1;
		}
		return -1;

	}

	@Override
	public boolean removeInstrument(ArrayList<T> arr, T instrument) {
		if (arr.remove(instrument) == true) {
			this.setTotalPrice(this.getTotalPrice() + (instrument.getPrice().doubleValue() * -1));

			return true;
		}
		return false;
	}

	@Override
	public boolean removeAll(ArrayList<? extends MusicalInstrument> list) {

		if (list.isEmpty())
			return false;

		while (!list.isEmpty()) {
			list.remove(0);
		}

		System.out.println("All Instruments Deleted Successfully!");
		return true;
	}

	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < this.getInstruments().size(); i++) {
			s += this.getInstruments().get(i).toString() + "\n";

		}

		return s;
	}

}
