import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

public class PublicKey {
	
	private BigInteger n;
	private BigInteger m;
	private BigInteger e;
	
	public PublicKey() {
		create_public_keys();
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
	
	//Première étape
	public ArrayList<BigInteger> convert_ascii(String s){
		ArrayList<BigInteger> asciiList = new ArrayList<BigInteger>();
		char[] characters = s.toCharArray();
		for(char c : characters) {
			asciiList.add(BigInteger.valueOf((long) c));
		}

		return asciiList;
	}
	
	//Seconde étape
	public ArrayList<BigInteger> chiffrement(ArrayList<BigInteger> asciiList){
		ArrayList<BigInteger> chiffreList = new ArrayList<BigInteger>();
		for(BigInteger c: asciiList) {
			BigInteger Si = c.modPow(e, n);
			chiffreList.add(Si);
		}
		return chiffreList;
	}
	
	public void create_public_keys() {
		BigInteger maxValue = new BigInteger("500000");
		Random rand = new Random();
		BigInteger p = BigInteger.probablePrime(maxValue.bitLength(), rand);
		BigInteger q = BigInteger.probablePrime(maxValue.bitLength(), rand);
		while(p.compareTo(maxValue) >= 0 && q.compareTo(maxValue) >= 0 && check_different(p, q)) {
			p = BigInteger.probablePrime(maxValue.bitLength(), rand);
			q = BigInteger.probablePrime(maxValue.bitLength(), rand);
		}
		n = p.multiply(q);
		BigInteger one = new BigInteger("1");
		m = p.subtract(one).multiply(q.subtract(one));
		maxValue = new BigInteger("100");
		e = new BigInteger("500");
		while(!m.gcd(e).equals(one)) {
			e = BigInteger.probablePrime(maxValue.bitLength(), rand);
		}
	}

	public void setN(BigInteger n) {
		this.n = n;
	}

	public void setE(BigInteger e) {
		this.e = e;
	}
}
