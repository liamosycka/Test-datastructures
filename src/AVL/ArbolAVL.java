package src.AVL;
public class ArbolAVL {

	public NodoAVL raiz;

	public ArbolAVL() {
		this.raiz = null;
	}

	public boolean insertar(Comparable elem) {
		boolean exito = true;
		System.out.println("insertado nodo " + elem);
		if (this.raiz == null) {
			this.raiz = new NodoAVL(elem);
		} else {
			exito = insertarAux(this.raiz, elem);
		}
		this.raiz.recalcularAltura();
		System.out.println("Altura nodo raiz" + this.raiz.getAltura());
		return exito;
	}

	public void balancearArbol(NodoAVL n) {
		NodoAVL padre = obtenerPadre(this.raiz, n.getElem());
		int balanceN=calcularBalance(n),caidoIzquierda=2,caidoDerecha=-2;
		System.out.println("Nodo: " + n.getElem() + " balance= " + balanceN + " altura= " + n.getAltura());
		if (balanceN== caidoIzquierda) {
			/* arbol caido a la izquierda */
			if ((calcularBalance(n.getIzq())) < 0) {
				/* rotacion doble izq ---> derecha */
				System.out.println("Rotacion doble izq derecha");
				rotacionDobleIzquierdaDerecha(n,padre);
			} else {
				/* rotacion simple a derecha */
				System.out.println("Rotacion simple a derecha");
				if (n == this.raiz) {
					this.raiz = rotacionSimpleDerecha(n);
					this.raiz.recalcularAltura();
				} else {
					padre.setIzq(rotacionSimpleDerecha(n));
				}
			}
		} else {
			if (balanceN == caidoDerecha) {
				/* arbol caido a la derecha */
				if (calcularBalance(n.getDer()) > 0) {
					/* rotacion doble derecha izquierda */
					System.out.println("rotacion doble derecha izquierda");
					rotacionDobleDerechaIzquierda(n,padre);
				} else {
					/* rotacion simple a izquierda */
					System.out.println("Rotacion simple a izq");
					if (n == this.raiz) {
						this.raiz = rotacionSimpleIzquierda(n);
						this.raiz.recalcularAltura();
					} else {
						padre.setDer(rotacionSimpleIzquierda(n));
					}
				}
			}
		}
		n.recalcularAltura();
	}

	public NodoAVL rotacionSimpleIzquierda(NodoAVL r) {
		NodoAVL h, temp;
		System.out.println("en rotacion simple izquierda nodo r = "+r.getElem());
		h = r.getDer();
		System.out.println("en rotacion simple izquierda nodo h= r.getDer() = "+h.getElem());
		temp = h.getIzq();
		h.setIzq(r);
		r.setDer(temp);
		return h;
	}

	public NodoAVL rotacionSimpleDerecha(NodoAVL r) {
		NodoAVL h, temp;
		h = r.getIzq();
		temp = h.getDer();
		h.setDer(r);
		r.setIzq(temp);
		return h;
	}
	public  void rotacionDobleDerechaIzquierda(NodoAVL r,NodoAVL padre) {
		r.setDer(rotacionSimpleDerecha(r.getDer()));
		if(r==this.raiz) {
			this.raiz=rotacionSimpleIzquierda(r);
		}else {
			padre.setDer(rotacionSimpleIzquierda(r));
		}
	}
	public void rotacionDobleIzquierdaDerecha(NodoAVL r, NodoAVL padre) {
		r.setIzq(rotacionSimpleIzquierda(r.getIzq()));
		if(r==this.raiz) {
			this.raiz=rotacionSimpleDerecha(r);
		}else {
			padre.setIzq(rotacionSimpleDerecha(r));
		}
	}

	public int calcularBalance(NodoAVL n) {
		int balance = 0;
		if ((n.getIzq() == null) && (n.getDer() == null)) {
			balance = 0;
		} else {
			if (n.getIzq() != null) {

				if (n.getDer() == null) {
					balance = n.getIzq().getAltura() + 1;
				} else {
					balance = n.getIzq().getAltura() - n.getDer().getAltura();
				}
			} else {
				if (n.getDer() != null) {
					balance = -1 - n.getDer().getAltura();
				}
			}
		}
		return balance;
	}

//	private boolean insertarAux(NodoAVL n, Comparable elem) {
//	boolean exito = true;
//	if (n.getElem().compareTo(elem) == 0) {
//		exito = false;
//	} else if (elem.compareTo(n.getElem()) < 0) {
//		// baja por izquierda
//		if (n.getIzq() == null) {
//			n.setIzq(new NodoAVL(elem));
//			n.recalcularAltura();
//		} else {
//			exito = insertarAux(n.getIzq(), elem);
//		}
//	} else if (n.getDer() == null) {
//		n.setDer(new NodoAVL(elem));
//		n.recalcularAltura();
//	} else {
//		exito = insertarAux(n.getDer(), elem);
//	}
//	n.recalcularAltura();
//	this.balancearArbol(n);
//	return exito;
//}

	public boolean insertarAux(NodoAVL n,Comparable elem) {
		boolean exito=true;
		if(elem.compareTo(n.getElem())<0) {
			if(n.getIzq()==null) {
				n.setIzq(new NodoAVL(elem));
			}else {
				exito=insertarAux(n.getIzq(),elem);
			}
		}else {
			if(elem.compareTo(n.getElem())>0) {
				if(n.getDer()==null) {
					n.setDer(new NodoAVL(elem));
				}else {
					exito=insertarAux(n.getDer(),elem);
				}
			}else {
				if(elem.compareTo(n.getElem())==0) {
					exito=false;
				}
			}
		}
		n.recalcularAltura();
		this.balancearArbol(n);
		return exito;
	}

	public NodoAVL obtenerPadre(NodoAVL n, Comparable elem) {
		NodoAVL nodo = null;
		if (n != null) {
			if ((n.getIzq() != null && n.getIzq().getElem().compareTo(elem) == 0)
					|| (n.getDer() != null && n.getDer().getElem().compareTo(elem) == 0)) {
				nodo = n;
			} else {
				if (n.getElem().compareTo(elem) > 0) {
					nodo = obtenerPadre(n.getIzq(), elem);
				} else {
					nodo = obtenerPadre(n.getDer(), elem);
				}
			}
		}
		return nodo;
	}

	public boolean pertenece(Comparable elem) {
		boolean pertenece = perteneceAux(this.raiz, elem);

		return pertenece;
	}

	public boolean perteneceAux(NodoAVL n, Comparable elem) {
		boolean pertenece = false;
		if (!pertenece) {
			if (n != null) {
				if (n.getElem().compareTo(elem) == 0) {
					// el elem es la raiz
					pertenece = true;
				} else if (elem.compareTo(n.getElem()) < 0) {
					pertenece = perteneceAux(n.getIzq(), elem);
				} else {
					pertenece = perteneceAux(n.getDer(), elem);
				}
			}
		}
		return pertenece;
	}

	public Comparable obtenerCandidato(NodoAVL n) {
		Comparable candidato = null;

		while (n != null) {
			candidato = n.getDer().getElem();
		}

		return candidato;
	}

	public boolean eliminar(Comparable elem) {
		boolean exito = false;
		if (this.raiz.getElem().compareTo(elem) == 0) {
			if (this.raiz.getDer() == null && this.raiz.getIzq() == null) {
				this.raiz = null;
			} else {
				if (this.raiz.getDer() != null && this.raiz.getIzq() == null) {
					this.raiz = this.raiz.getIzq();
				} else {
					if (this.raiz.getIzq() != null && this.raiz.getDer() == null) {
						this.raiz = this.raiz.getDer();
					} else {
						System.out.println("Eliminar caso 3");
						eliminarCasoTres(this.raiz);
					}
				}
				this.raiz.recalcularAltura();
				balancearArbol(this.raiz);
			}
		} else {

			if (elem.compareTo(this.raiz.getElem()) < 0) {
				exito = eliminarAux(this.raiz.getIzq(), elem, this.raiz);
			} else {
				exito = eliminarAux(this.raiz.getDer(), elem, this.raiz);
			}
		}
		return exito;
	}

	public boolean eliminarAux(NodoAVL n, Comparable elem, NodoAVL padre) {
		boolean exito = false;
		if (n != null) {
			if (!exito) {
				if (elem.compareTo(n.getElem()) == 0) {
					// Verifico si el nodo es hoja
					if (n.getIzq() == null && n.getDer() == null) {
						// el nodo es hoja
						eliminarCasoUno(n, padre);
						exito = true;
						// El nodo tiene almenos un hijo
					} else {
						if (n.getDer() != null && n.getIzq() != null) {
							eliminarCasoTres(n);
						} else {
							if (n.getElem().compareTo(padre.getElem()) > 0) {
								/*
								 * el nodo N es mayor al padre, por lo tanto se encuentra a su derecha Verifico
								 * si el nodo tiene 1 hijo
								 */
								eliminarCasoDos(n, padre, true);
							} else {
								// el nodo N es menor al padre, por lo tanto se encuentra a su izquierda
								eliminarCasoDos(n, padre, false);
							}
						}
						exito = true;

					}
				} else {

					if (elem.compareTo(n.getElem()) < 0) {
						exito = eliminarAux(n.getIzq(), elem, n);
					} else {
						exito = eliminarAux(n.getDer(), elem, n);
					}
				}
			}

		}
		padre.recalcularAltura();
		balancearArbol(padre);
		return exito;
	}

	public void eliminarCasoUno(NodoAVL n, NodoAVL padre) {
		if (padre.getIzq() == n) {
			padre.setIzq(null);
		} else {
			padre.setDer(null);
		}
	}

	public void eliminarCasoDos(NodoAVL n, NodoAVL padre, boolean derecha) {
		if (derecha) {
			if (n.getDer() != null) {
				// el nodo N tiene un solo hijo derecho
				padre.setDer(n.getDer());
			} else {
				padre.setDer(n.getIzq());
			}

		} else {
			if (n.getDer() != null) {
				// el nodo N tiene un solo hijo derecho
				padre.setIzq(n.getDer());
			} else {
				padre.setIzq(n.getIzq());
			}
		}

	}

	public void eliminarCasoTres(NodoAVL n) {
		NodoAVL[] arrNodos = buscarCandidato(n.getIzq(), n);
		NodoAVL candidato = arrNodos[0];
		NodoAVL padreCandidato = arrNodos[1];
		n.setElem((candidato.getElem()));
		if (candidato.getIzq() != null) {
			eliminarCasoDos(candidato, padreCandidato, false);
		} else {
			eliminarCasoUno(candidato, padreCandidato);
		}

	}

	public NodoAVL[] buscarCandidato(NodoAVL n, NodoAVL padre) {
		NodoAVL candidato = n;
		NodoAVL father = padre;
		NodoAVL[] arrNodos = new NodoAVL[2];

		while (candidato.getDer() != null) {
			father = candidato;
			candidato = candidato.getDer();
		}
		arrNodos[0] = candidato;
		arrNodos[1] = father;
		return arrNodos;
	}

	public String toString() {
		String arbol = "";

		if (this.raiz != null) {
			arbol += toStringAux(this.raiz);
		} else {
			arbol = "Arbol vacio";
		}

		return arbol;
	}

	public String toStringAux(NodoAVL nodo) {
		String listado = "";

		if (nodo != null) {
			listado += "Padre: " + nodo.getElem() + " ALTURA " + nodo.getAltura();
			listado += "\n";
			if (nodo.getIzq() != null) {
				listado += "Hijo izquierdo: " + nodo.getIzq().getElem() + " ";
			} else {
				listado += "Hijo izquierdo: No tiene ";
			}
			if (nodo.getDer() != null) {
				listado += "Hijo derecho: " + nodo.getDer().getElem() + " ";
			} else {
				listado += " Hijo derecho: No tiene ";
			}

			listado += "\n";
			listado += toStringAux(nodo.getIzq());
			listado += toStringAux(nodo.getDer());
		}
		return listado;
	}

	public NodoAVL obtenerNodo(NodoAVL n, Comparable elem) {
		NodoAVL nuevo = null;
		if (n != null) {
			if (n.getElem().compareTo(elem) == 0) {
				nuevo = n;
			} else {
				if (n.getElem().compareTo(elem) > 0) {
					obtenerNodo(n.getIzq(), elem);
				} else {
					obtenerNodo(n.getDer(), elem);
				}
			}
		}
		return nuevo;
	}

}
