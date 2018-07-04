package papacapstone.EncryptionTools;

import java.security.SecureRandom;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

/**
 * 
 * This class contains modified code taken from Stack Overflow's User Community:
 * https://stackoverflow.com/questions/41107/how-to-generate-a-random-alpha-numeric-string
 * 
 * All credit goes to: Stack Overflow Community
 * 
 * Licensing Note: - The source code within this class adheres to Stack
 * Overflow's License.
 * 
 * @author Stack Overflow Community
 *
 */
public class RandomStringGenerator {
	private final Random random;
	private final char[] symbols;
	private final char[] buf;
	public static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String lower = upper.toLowerCase(Locale.ROOT);
	public static final String digits = "0123456789";
	public static final String alphanum = upper + lower + digits;

	/**
	 * Create session identifiers.
	 */
	public RandomStringGenerator() {
		this(21); // by default, return an alphaString of length 21
	}

	/**
	 * Create an alphanumeric string with param length
	 */
	public RandomStringGenerator(int length) {
		this(length, new SecureRandom());
	}

	/**
	 * Create an alphanumeric string generator.
	 */
	public RandomStringGenerator(int length, Random random) {
		this(length, random, alphanum);
	}

	public RandomStringGenerator(int length, Random random, String symbols) {
		if (length < 1)
			throw new IllegalArgumentException();
		if (symbols.length() < 2)
			throw new IllegalArgumentException();

		this.random = Objects.requireNonNull(random);
		this.symbols = symbols.toCharArray();
		this.buf = new char[length];
	}

	/**
	 * Generate a random string.
	 */
	public String nextString() {
		for (int i = 0; i < buf.length; ++i)
			buf[i] = symbols[random.nextInt(symbols.length)];
		return new String(buf);
	}

}// end of class