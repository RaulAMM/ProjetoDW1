package Bus.model.entities;

public class Onibus {
	String Placa;
	int QTDPassageiros;
	int LinhaOnibusIdLinhaOnibus;
	
	public String getPlaca() {
		return Placa;
	}
	public void setPlaca(String placa) {
		Placa = placa;
	}
	public int getQTDPassageiros() {
		return QTDPassageiros;
	}
	public void setQTDPassageiros(int qTDPassageiros) {
		QTDPassageiros = qTDPassageiros;
	}
	public int getLinhaOnibusIdLinhaOnibus() {
		return LinhaOnibusIdLinhaOnibus;
	}
	public void setLinhaOnibusIdLinhaOnibus(int linhaOnibusIdLinhaOnibus) {
		LinhaOnibusIdLinhaOnibus = linhaOnibusIdLinhaOnibus;
	}
}
