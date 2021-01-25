import java.math.BigInteger;

public class PrivateKey {
	
	//Coefficient de Bézout
	private BigInteger u;
	private BigInteger v;
	
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
	}
	
	private void AlgoEuclide() {
		
		//On commence à partir de S1
		int i = 1;
		
		BigInteger r = m;
		BigInteger rmoins1 = e;
		
		u = BigInteger.ZERO;
		BigInteger umoins1 = BigInteger.ONE;
		
		v = BigInteger.ONE;
		BigInteger vmoins1 = BigInteger.ZERO;
		
//		System.out.println(i+"   r-1: "+rmoins1.intValue()+ "   u-1: "+umoins1.intValue()+ "   v-1: "+vmoins1.intValue());
//		System.out.println(i+"     r: "+r.intValue()+ "   u: "+u.intValue()+ "   v: "+v.intValue());
		
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
			
//			System.out.println(i+"   r-1: "+rmoins1.longValueExact()+ "   u-1: "+umoins1.longValueExact()+ "   v-1: "+vmoins1.longValueExact());
//			System.out.println(i+"     r: "+r.longValueExact()+ "   u: "+u.longValueExact()+ "   v: "+v.longValueExact());

		}
		
		//System.out.println("Condition  < 2 "+(u.compareTo(BigInteger.valueOf(2))==-1) +"   >m "+ (u.compareTo(m)==1));
		
		//Vérifie que 2 < u < m
		if (u.compareTo(BigInteger.valueOf(2))==-1 || u.compareTo(m)==1) {
			long k = -1;
			BigInteger compare;
			BigInteger mul;
			do {
				mul =  m.multiply(BigInteger.valueOf(k));
				compare = u.subtract(mul);
				k--;
				//System.out.println(compare.longValueExact());
			}while( compare.compareTo(BigInteger.valueOf(2))==-1 );
			u = compare;
		}
		
		System.out.println("   r: "+r.intValue()+ "   u: "+u.intValue()+ "   v: "+v.intValue());
	}

	public void CreationKey() {
		AlgoEuclide();
		if ( ((e.multiply(u)).add(m.multiply(v))).compareTo(e.gcd(m))==0) {
			if ( ((e.multiply(u)).add(m.multiply(v))).compareTo(BigInteger.ONE)==0) {
				if (u.compareTo(BigInteger.valueOf(2))==-1 || u.compareTo(m)==1) {
					Pkey = new Key(n,u);
				}	
			}
		}
		
	}
	
	public Key getPKey() {
		return Pkey;
	}
	
	
	
	
}
