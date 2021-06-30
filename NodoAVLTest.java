package src.AVL;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NodoAVLTest {

	@Test
	void test_crear_nodo() {
		NodoAVL n=new NodoAVL(5);
		//chequeamos que se creó con ese elemento
		assertEquals(n.getElem(),5);
		//chequeamos que no tenga ningun hijo
		assertEquals(n.getIzq(),null);
		assertEquals(n.getDer(),null);
		//chequeamos que la altura sea 0
		assertEquals(n.getAltura(), 0);
			
	}
	
	@Test
	void test_set_hijoIzq() {
		NodoAVL n=new NodoAVL(5);
		NodoAVL hijoIzq=new NodoAVL(6);
		n.setIzq(hijoIzq);
		assertEquals(n.getIzq(),hijoIzq);
	}
	@Test
	void test_set_hijoDer() {
		NodoAVL n=new NodoAVL(5);
		NodoAVL hijoDer=new NodoAVL(7);
		n.setIzq(hijoDer);
		assertEquals(n.getIzq(),hijoDer);
	}
	
	@Test
	void test_recalcular_altura() {
		NodoAVL n=new NodoAVL(5);
		NodoAVL hijoIzq=new NodoAVL(6);
		NodoAVL hijoDer=new NodoAVL(7);
		n.setIzq(hijoIzq);
		n.setDer(hijoDer);
		int altura_n=n.getAltura();
		int ex_altura=1;
		assertEquals(altura_n,ex_altura);
		n.setIzq(null);
		altura_n=n.getAltura();
		assertEquals(altura_n,ex_altura);
		n.setDer(null);
		altura_n=n.getAltura();
		ex_altura=0;
		assertEquals(altura_n,0);
	}
	
	// Fin 
	
	
	
	
}

