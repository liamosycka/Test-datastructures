package src.AVL;
public class NodoAVL {

	public Comparable elem;
	public int altura;
	public NodoAVL hijoIzq;
	public NodoAVL hijoDer;

	public NodoAVL(Comparable elemento) {
		elem = elemento;
		hijoIzq = null;
		hijoDer = null;
		altura = 0;
	}

	public Comparable getElem() {
		return elem;
	}

	public void setElem(Comparable nuevo) {
		elem = nuevo;
	}

	public int getAltura() {
		return altura;
	}



	public void recalcularAltura() {
		//altura = recalcularAltura(this);
		if(this.getIzq()!=null) {
			if(this.getDer()!=null) {
				altura=1+Math.max(this.getDer().getAltura(), this.getIzq().getAltura());
			}else {
				altura=1+this.getIzq().getAltura();
			}
		}else {
			if(this.getDer()!=null) {
				altura=1+this.getDer().getAltura();
			}else {
				if(this.getDer()==null) {
					altura=0;
				}
			}
		}
	}

	public NodoAVL getIzq() {
		return hijoIzq;
	}

	public NodoAVL getDer() {
		return hijoDer;
	}

	public void setIzq(NodoAVL izquierdo) {
		hijoIzq = izquierdo;
		if (izquierdo != null) {
			izquierdo.recalcularAltura();
		}
		this.recalcularAltura();
	}

	public void setDer(NodoAVL derecho) {
		hijoDer = derecho;
		if (derecho != null) {
			derecho.recalcularAltura();
		}
		this.recalcularAltura();
	}
}
