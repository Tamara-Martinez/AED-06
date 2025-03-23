package Ejercicio;

public interface Operable<E> {
	E sumar(E otro);
	E restar(E otro);
	E multiplicar(E otro);
	E dividir(E otro);
	E potencia(E otro);
	E raizCuadrada();
	E raizCubica();
}
