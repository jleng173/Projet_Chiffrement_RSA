import java.math.BigInteger;

public class Key {
	
	private BigInteger x;
	private BigInteger y;
	
	public Key (BigInteger v1, BigInteger v2) {
		this.x = v1;
		this.y = v2;
	}
	

	public BigInteger getX() {
		return x;
	}

	public void setX(BigInteger x) {
		this.x = x;
	}

	public BigInteger getY() {
		return y;
	}

	public void setY(BigInteger y) {
		this.y = y;
	}

}
