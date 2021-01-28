import java.math.BigInteger;

public class Key {
	
	private BigInteger n;
	private BigInteger y; // correspond au e pour PublicKey, au u pour PrivateKey
	
	public Key (BigInteger v1, BigInteger v2) {
		this.n = v1;
		this.y = v2;
	}
	

	public BigInteger getN() {
		return n;
	}

	public void setN(BigInteger x) {
		this.n = x;
	}

	public BigInteger getY() {
		return y;
	}

	public void setY(BigInteger y) {
		this.y = y;
	}

}
