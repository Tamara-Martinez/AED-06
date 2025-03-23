package Ejercicio;

public class OperacionesMatDouble implements Operable<Double> {
	private Double valor;
	public OperacionesMatDouble(Double valor) {

		this.valor = valor;
	}
	@Override
	public Double sumar(Double otro) {

		return this.valor + otro;
	}
	@Override
	public Double restar(Double otro) {

		return this.valor - otro;
	}
	@Override
	public Double multiplicar(Double otro) {

		return this.valor * otro;
	}
	@Override
	public Double dividir(Double otro) {
		if (otro == 0) {
			throw new ArithmeticException("No se puede dividir entre 0");
		}
		return this.valor / otro;
	}
	@Override
	public Double potencia(Double otro) {

		return Math.pow(this.valor, otro);
	}
	@Override

	public Double raizCuadrada() {

		return Math.sqrt(this.valor);

	}
	@Override
	public Double raizCubica() {

		return Math.cbrt(this.valor);
	}
	public Double getValor() {

		return valor;
	}
}
