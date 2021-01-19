import java.math.BigInteger;

public class PrivateKey {
	
	//Coefficient de Bézout
	private BigInteger u;
	
	//indicatrice d'Euler
	private BigInteger m;
	private BigInteger e;

	public PrivateKey(BigInteger m, BigInteger e) {
		this.m = m;
		this.e = e;
	}
	
	
}
