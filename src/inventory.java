import java.util.ArrayList;

public interface inventory<T extends MusicalInstrument> {

	void addAllWindInstrument(ArrayList<? extends MusicalInstrument> a, ArrayList<? super MusicalInstrument> b);

	void addAllStringInstrument(ArrayList<? extends MusicalInstrument> a, ArrayList<? super MusicalInstrument> b);

	void SortByBrandAndPrice(ArrayList<? extends MusicalInstrument> a);

	int binnarySearchByBrandAndPrice(ArrayList<? extends MusicalInstrument> a, String brand, Number price);

	void addInstrument(ArrayList<T> a, T b);

	boolean removeInstrument(ArrayList<T> a, T b);

	boolean removeAll(ArrayList<? extends MusicalInstrument> a);

}
