package crypt;
import java.math.BigInteger;

public class PrivateKey {
	
	//Coefficient de Bézout
	private BigInteger u;
	private BigInteger v;
	BigInteger umoins1;
	
	//indicatrice d'Euler
	private BigInteger e;
	private BigInteger m;
	
	private BigInteger n;
	private Key Pkey;

	public PrivateKey(BigInteger e, BigInteger m, BigInteger n) {
		this.m = m;
		this.e = e;
		this.n = n;
		Pkey = new Key(BigInteger.ZERO,BigInteger.ZERO);
		CreationKey();
	}
	
	private void AlgoEuclide() {
		
		//On commence à partir de S1
		int i = 1;
		
		BigInteger r = m;
		BigInteger rmoins1 = e;
		
		u = BigInteger.ZERO;
		umoins1 = BigInteger.ONE;
		
		v = BigInteger.ONE;
		BigInteger vmoins1 = BigInteger.ZERO;

		BigInteger tmp;
		
		while( !r.equals(BigInteger.ZERO)) {
			i++;		
			tmp = u;
			u = umoins1.subtract((rmoins1.divide(r)).multiply(u));
			umoins1 = tmp;
			
			tmp = v;
			v = vmoins1.subtract((rmoins1.divide(r)).multiply(v));
			vmoins1 = tmp;
			
			tmp = r;
			r = rmoins1.subtract((rmoins1.divide(r)).multiply(r));
			rmoins1 = tmp;
			
		}
		

	}

	public void CreationKey() {
		AlgoEuclide();
		
		BigInteger e_u = e.multiply(u);
		BigInteger m_v = m.multiply(v);
		BigInteger eu_mv = e_u.add(m_v).add(BigInteger.ONE);
		
		if ( (eu_mv).compareTo(e.gcd(m))==0) {
			
			
			if ( (eu_mv).compareTo(BigInteger.ONE)==0) {
				
				
				if (u.compareTo(BigInteger.valueOf(2))==-1 || u.compareTo(m)==1) {
					BigInteger compare;
					long k = -1;
					
					BigInteger mul;
					do {
						mul =  m.multiply(BigInteger.valueOf(k));
						compare = u.subtract(mul);
						k--;
					}while( compare.compareTo(BigInteger.valueOf(2))==-1 || compare.compareTo(m)==1);
					u = compare;
				}
					
				if (!(u.compareTo(BigInteger.valueOf(2))==-1) && !(u.compareTo(m)==1)) {
					u = u.add(umoins1);
				}
//				else {
//					System.out.println("   test 2 : "+u.longValueExact());
//					u = u.subtract(umoins1);
//				}
					Pkey = new Key(n,u);
			}
		}
		
	}
	
	public Key getPKey() {
		return Pkey;
	}
	
	
	
	
}
