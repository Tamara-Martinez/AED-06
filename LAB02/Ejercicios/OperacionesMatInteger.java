package Ejercicio;

public class OperacionesMatInteger implements Operable<Integer> {
	private Integer valor;
	public OperacionesMatInteger(Integer valor) {

		this.valor = valor;
	}
	@Override
	public Integer sumar(Integer otro) {

		return this.valor + otro;
	}
	@Override
	public Integer restar(Integer otro) {

		return this.valor - otro;
	}
	@Override
	public Integer multiplicar(Integer otro) {
		return this.valor * otro;
	}
	@Override
	public Integer dividir(Integer otro) {
		if (otro == 0) {
			throw new ArithmeticException("No se puede dividir entre 0");
		}
		return this.valor / otro;
	}
	@Override
	public Integer potencia(Integer otro) {
		return (int) Math.pow(this.valor, otro);
	}
	@Override
	public Integer raizCuadrada() {

		return (int) Math.sqrt(this.valor);
	}
	@Override
	public Integer raizCubica() {
		return (int) Math.cbrt(this.valor);
	}
	public Integer getValor() {

		return valor;
	}
}
