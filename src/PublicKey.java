import java.math.BigInteger;
import java.util.Random;

public class PublicKey {
	
	private BigInteger n;
	private BigInteger m;
	private BigInteger e;
	
	public PublicKey() {
		create_public_keys();
		System.out.println("n = " + n);
		System.out.println("m = " + m);
		System.out.println("e = " + e);
	}
	
	public BigInteger get_n() {
		return n;
	}
	
	public BigInteger get_m() {
		return m;
	}
	
	public BigInteger get_e() {
		return e;
	}
	
	public boolean check_different(BigInteger p, BigInteger q) {
		return p.equals(q);
	}
	
	public void create_public_keys() {
		BigInteger maxValue = new BigInteger("500000");
		Random rand = new Random();
		BigInteger p = BigInteger.probablePrime(maxValue.bitLength(), rand);
		BigInteger q = BigInteger.probablePrime(maxValue.bitLength(), rand);
		System.out.println("1er Etape");
		while(p.compareTo(maxValue) >= 0 && q.compareTo(maxValue) >= 0 && check_different(p, q)) {
			p = BigInteger.probablePrime(maxValue.bitLength(), rand);
			q = BigInteger.probablePrime(maxValue.bitLength(), rand);
		}
		n = p.multiply(q);
		BigInteger one = new BigInteger("1");
		m = p.subtract(one).multiply(q.subtract(one));
		maxValue = new BigInteger("100");
		e = new BigInteger("500");
		System.out.println("p = " + p);
		System.out.println("q = " + q);
		System.out.println("m = " + m);

		System.out.println("2e Etape");
		while(!m.gcd(e).equals(one)) {
			e = BigInteger.probablePrime(maxValue.bitLength(), rand);
		}
	}
}