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
		
		System.out.println(i+"   r-1: "+rmoins1.longValueExact()+ "   u-1: "+umoins1.longValueExact()+ "   v-1: "+vmoins1.longValueExact());
		System.out.println(i+"     r: "+r.longValueExact()+ "   u: "+u.longValueExact()+ "   v: "+v.longValueExact());
		
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
			
			System.out.println(i+"   r-1: "+rmoins1.longValueExact()+ "   u-1: "+umoins1.longValueExact()+ "   v-1: "+vmoins1.longValueExact());
			System.out.println(i+"     r: "+r.longValueExact()+ "   u: "+u.longValueExact()+ "   v: "+v.longValueExact());
			
		}
		
		//System.out.println("Condition  < 2 "+(u.compareTo(BigInteger.valueOf(2))==-1) +"   >m "+ (u.compareTo(m)==1));
		
		//Vérifie que 2 < u < m

		//u = u.subtract(BigInteger.valueOf(713));
		System.out.println("   r: "+r.longValueExact()+ "   u: "+u.longValueExact()+ "   v: "+v.longValueExact());
	}

	public void CreationKey() {
		AlgoEuclide();
		
		BigInteger compare = u;
		if (u.compareTo(BigInteger.valueOf(2))==-1 || u.compareTo(m)==1) {
			long k = -0;
			
			BigInteger mul;
			do {
				mul =  m.multiply(BigInteger.valueOf(k));
				compare = u.subtract(mul);
				k--;
				System.out.println("        "+compare.longValueExact());
				System.out.println((compare.compareTo(BigInteger.valueOf(2))==-1)+"    "+ (compare.compareTo(m)==1));
			}while( compare.compareTo(BigInteger.valueOf(2))==-1 || compare.compareTo(m)==1);
			
		}
		
		BigInteger e_u = e.multiply(u);
		BigInteger m_v = m.multiply(v);
		BigInteger eu_mv = e_u.add(m_v).add(BigInteger.ONE);
		
		System.out.println("1 "+ eu_mv.longValueExact() +"  "+e.gcd(m));
		
		if ( (eu_mv).compareTo(e.gcd(m))==0) {
			
			System.out.println("2 "+BigInteger.ONE.longValueExact());
			
			if ( (eu_mv).compareTo(BigInteger.ONE)==0) {
				System.out.println("3");
				
				u = compare;
				if (!(u.compareTo(BigInteger.valueOf(2))==-1) && !(u.compareTo(m)==1)) {
					System.out.println("key privée générée");
					
					if (u.compareTo(BigInteger.valueOf(2))==-1 || u.compareTo(m)==1) {
						
						u = u.subtract(umoins1);
					}else {
						u = u.add(umoins1);
					}
					System.out.println("   u a la fin : "+u.longValueExact());
					Pkey = new Key(n,u);
					
				}
			}
		}
		
	}
	
	public Key getPKey() {
		return Pkey;
	}
	
	
	
	
}
