import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

@SuppressWarnings("unused")
public abstract class MusicalInstrument implements InstrumentFunc<MusicalInstrument> {
	private Number price;
	private String brand;

	public MusicalInstrument(String brand, Number price) {
		setBrand(brand);
		setPrice(price);
	}

	public MusicalInstrument(Scanner scanner) {
		Number price = 0;
		String brand;

		try {
			if (scanner.hasNextInt()) {
				price = scanner.nextInt();
			} else if (scanner.hasNextDouble()) {
				price = scanner.nextDouble();
			}

		} catch (InputMismatchException ex) {
			throw new InputMismatchException("Price not found!");
		}
		setPrice(price);
		scanner.nextLine();
		brand = scanner.nextLine();
		setBrand(brand);
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Number getPrice() {
		return price;
	}

	public void setPrice(Number price) {
		if (price instanceof Integer)
			this.price = price.intValue();
		else if (price instanceof Double)
			this.price = price.doubleValue();
		else
			throw new InputMismatchException("Price must be a positive number!");

	}

	protected boolean isValidType(String[] typeArr, String material) {
		for (int i = 0; i < typeArr.length; i++) {
			if (material.equals(typeArr[i])) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || !(o instanceof MusicalInstrument))
			return false;

		MusicalInstrument otherInstrument = (MusicalInstrument) o;

		return getPrice().doubleValue() == otherInstrument.getPrice().doubleValue()
				&& getBrand().equals(otherInstrument.getBrand());
	}

	@Override
	public String toString() {
		if (this.getPrice() instanceof Integer)
			return String.format("%-8s %-9s| Price: %7d,", getBrand(), getClass().getCanonicalName(), getPrice());
		return String.format("%-8s %-9s| Price: %7.1f,", getBrand(), getClass().getCanonicalName(), getPrice());
	}

	@Override
	public int compareTo(MusicalInstrument o) {
		if (o.getBrand().compareTo(this.getBrand()) < 0)
			return 1;
		else if (o.getBrand().compareTo(this.getBrand()) > 0)
			return -1;

		else if (o.getPrice().doubleValue() < this.getPrice().doubleValue())
			return 1;
		else if (o.getPrice().doubleValue() > this.getPrice().doubleValue())
			return -1;
		else
			return 0;

	}

}
