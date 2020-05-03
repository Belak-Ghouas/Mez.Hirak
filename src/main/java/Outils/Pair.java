package Outils;



public class Pair <E,V> {

		private E page;
		private V frequence ;
		
		public Pair(E page , V frequence) {
			// TODO Auto-generated constructor stub
			this.page=page;
			this.frequence=frequence;
		}
		
		public E getKey() {
			return page;
		}
		
		public V getValue() {
			return frequence;
		}

	
	
	


	/*	@Override
		public int compareTo(Pair o) {
			// TODO Auto-generated method stub
			return this.page.compareToIgnoreCase(o.page);
		}
		*/
		
}
