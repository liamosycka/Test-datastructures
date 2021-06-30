package src.AVL;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.DisplayName;

class ArbolAVLTest {
	
	private ArbolAVL tree;

    @BeforeEach                                         
    public void setUp() throws Exception {
        ArbolAVL tree = new ArbolAVL();
    }
	@Test
	void test_permitir_insercion() {
		ArbolAVL tree= new ArbolAVL();
		boolean resp=tree.insertar(2);
		assertEquals(resp,true);
	}
	@Test
	void test_no_permitir_duplicados() {
		ArbolAVL tree = new ArbolAVL();
		tree.insertar(2);
		boolean resp=tree.insertar(2);
		assertEquals(resp,false);
	}
	
	@Test
	/*No se deberían insertar nulls, ya que despues no podriamos realizar comparaciones.
	 * El programa permite insertar nulls, así que este test devuelve un Failure.*/
	void test_insertar_nulo() {
		ArbolAVL tree = new ArbolAVL();
		boolean resp=tree.insertar(null);
		assertEquals(resp,false);
	}
	
	@Test
	/*El programa no tira ninguna excepcion*/
	void test_tira_excepcion() {
		ArbolAVL tree = new ArbolAVL();
	    assertThrows(IllegalArgumentException.class, () -> tree.insertar(null));
	}
	// Fin métodos inserción
	///////////////////////////////////////////////////
	
	@Test
	void test_eliminar_si_existe() {
		ArbolAVL tree = new ArbolAVL();
		tree.insertar(3);
		boolean resp=tree.eliminar(3);
		assertEquals(resp,false);
	}
	@Test
	void test_no_eliminar_si_no_existe() {
		ArbolAVL tree = new ArbolAVL();
		tree.insertar(3);
		boolean resp=tree.eliminar(4);
		assertEquals(resp,false);
	}	

	@Test
	/*Si el arbol es nulo no se deberia intentar eliminar.
	 * El programa no chequea esto, asi que este test causa que se corte la ejecución, es un ERROR.*/
	void test_no_eliminar_si_no_arbol_nulo() {
		ArbolAVL tree = new ArbolAVL();
		boolean resp=tree.eliminar(2);
		assertEquals(resp,false);	
	}
	
	// Fin métodos para eliminar
	//////////////////////////////////////////////
	// Tests de balanceo
	
	@Test
	void test_balance_con_2_hijos() {
		ArbolAVL tree=new ArbolAVL();
		tree.insertar(5);
		NodoAVL nodo=tree.obtenerNodo(tree.raiz,5);
		int balance_1=tree.calcularBalance(nodo);
		//hay un solo nodo, el balance debería ser 0
		int ex_balance=0;
		assertEquals(balance_1,ex_balance);
		//insertamos un hijo a la izquierda
		tree.insertar(1);
		//esto deberia insertarlo como hijo izquierdo del nodo raiz
		//entonces, el balance debe ser 1
		ex_balance=1;
		int balance_2=tree.calcularBalance(nodo);
		assertEquals(balance_2,ex_balance);
		//insertamos un hijo a la derecha
		tree.insertar(6);
		//esto deberia insertarlo como hijo derecho del nodo raiz
		//el balance debería ser 0
		ex_balance=0;
		int balance_3=tree.calcularBalance(nodo);
		assertEquals(balance_3,ex_balance);
		
	}
	
	@Test
	void test_rotacion_simple_der() {
		ArbolAVL tree=new ArbolAVL();
		tree.insertar(5);
		NodoAVL nodo=tree.obtenerNodo(tree.raiz,5);
		tree.insertar(3);
		tree.insertar(1);
		//estos dos inserts deberían ir a la izquierda del nodo
		//como va a quedar desbalanceado, se hará una rotación automática
		//y el balance quedará nuevamente en 0.
		int balance=tree.calcularBalance(nodo);
		int ex_balance=0;
		assertEquals(balance,ex_balance);
	}
	
	@Test
	void test_rotacion_simple_izq() {
		ArbolAVL tree=new ArbolAVL();
		tree.insertar(5);
		NodoAVL nodo=tree.obtenerNodo(tree.raiz,5);
		tree.insertar(6);
		tree.insertar(7);
		//estos dos inserts deberían ir a la derecha del nodo
		//como va a quedar desbalanceado, se hará una rotación automática
		//y el balance quedará nuevamente en 0.
		int balance=tree.calcularBalance(nodo);
		int ex_balance=0;
		assertEquals(balance,ex_balance);
	}
	
	@Test
	void test_rotacion_doble_izq_der() {
		ArbolAVL tree=new ArbolAVL();
		tree.insertar(5);
		NodoAVL nodo=tree.obtenerNodo(tree.raiz,5);
		tree.insertar(3);
		tree.insertar(4);
		/*
		 * El 3 es hijo izquierdo del 5, el 4 hijo derecho del 3
		 * */
		int balance=tree.calcularBalance(nodo);
		int ex_balance=0;
		assertEquals(balance,ex_balance);
	}
	
	@Test
	void test_rotacion_doble_der_izq() {
		ArbolAVL tree=new ArbolAVL();
		tree.insertar(5);
		NodoAVL nodo=tree.obtenerNodo(tree.raiz,5);
		tree.insertar(7);
		tree.insertar(6);
		/*
		 * El 7 es hijo derecho del 5, el 6 hijo izquierdo del 7
		 * */
		int balance=tree.calcularBalance(nodo);
		int ex_balance=0;
		assertEquals(balance,ex_balance);
	}
	
	@Test
	void test_rotacion_doble_izq_der_toString() {
		ArbolAVL tree=new ArbolAVL();
		tree.insertar(5);
		NodoAVL nodo=tree.obtenerNodo(tree.raiz,5);
		tree.insertar(3);
		tree.insertar(4);
		/*
		 * El 3 es hijo izquierdo del 5, el 4 hijo derecho del 3
		 * */
		
		ArbolAVL tree_h=new ArbolAVL();
		tree_h.insertar(4);
		NodoAVL nodo_t_h=tree.obtenerNodo(tree_h.raiz,4);
		NodoAVL nodo_izq=new NodoAVL(3);
		NodoAVL nodo_der=new NodoAVL(5);
		nodo_t_h.setIzq(nodo_izq);
		nodo_t_h.setDer(nodo_der);
		
		String s_tree=tree.toString();
		String s_tree_h=tree_h.toString();
		
		assertEquals(s_tree,s_tree_h);
		
	}
	
	@Test
	void test_rotacion_doble_der_izq_toString() {
		ArbolAVL tree=new ArbolAVL();
		tree.insertar(5);
		NodoAVL nodo=tree.obtenerNodo(tree.raiz,5);
		tree.insertar(7);
		tree.insertar(6);
		/*
		 * El 3 es hijo izquierdo del 5, el 4 hijo derecho del 3
		 * */
		
		ArbolAVL tree_h=new ArbolAVL();
		tree_h.insertar(6);
		NodoAVL nodo_t_h=tree.obtenerNodo(tree_h.raiz,6);
		NodoAVL nodo_izq=new NodoAVL(5);
		NodoAVL nodo_der=new NodoAVL(7);
		nodo_t_h.setIzq(nodo_izq);
		nodo_t_h.setDer(nodo_der);
		
		String s_tree=tree.toString();
		String s_tree_h=tree_h.toString();
		
		assertEquals(s_tree,s_tree_h);
		
		
		
		
	/*Hay que testear lo siguiente:
	 * 1) Con mockito (o alguna otra cosa) verificar que cuando vayamos a eliminar
	 * algun elemento, llame al metodo que nosotros queremos:
	 * por ej: eliminarCasoTres.
	 * 2) assert called con recalcular altura en nodo
	 * 3) testear obtener padre de un nodo
	 * 4) testear pertenece
	 * 5) testear llamados a rotaciones que corresponden
	 *
	 * 7) testear el buscar candidato para buscar la raiz del subarbol*/
	}
	
	

	

}

