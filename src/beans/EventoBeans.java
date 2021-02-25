package beans;

/**
 * Modelo Eventos
 */
public class EventoBeans {
	
	// Atributos
	private int idEvento, lotacaoEvento;
	private String nomeEvento;
	
	// Get e Set
	public int getIdEvento() {
		return idEvento;
	}
	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}
	public int getLotacaoEvento() {
		return lotacaoEvento;
	}
	public void setLotacaoEvento(int lotacaoEvento) {
		this.lotacaoEvento = lotacaoEvento;
	}
	public String getNomeEvento() {
		return nomeEvento;
	}
	public void setNomeEvento(String nomeEvento) {
		this.nomeEvento = nomeEvento;
	}
	
	// toString
	public String toString() {
		return nomeEvento;
	}
	
}
