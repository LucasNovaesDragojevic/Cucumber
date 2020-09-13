package br.com.alura.leilao.acceptance.steps;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;

import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

public class PropondoLanceSteps {

	private Lance lance;
	private Leilao leilao;
	private ArrayList<Lance> lances;
	
	@Before
	public void setup() {
		this.lances = new ArrayList<Lance>();
		this.leilao = new Leilao("Galaxy Tab S6");
	}

	@Dado("um lance valido")
	public void dado_um_lance_valido() {
		Usuario usuario = new Usuario("fulano");
		lance = new Lance(usuario , BigDecimal.TEN);
	}
	
	@Quando("propoe ao leilao")
	public void quando_propoe_o_lance() {
		leilao.propoe(lance);
	}
	
	@Entao("o lance eh aceito")
	public void entao_o_lance_eh_aceito() {
		assertEquals(1, leilao.getLances().size());
		assertEquals(BigDecimal.TEN, leilao.getLances().get(0).getValor());
	}
	
	@Dado("um lance de {double} reais do usuario {string}")
	public void um_lance_de_reais_do_usuario(Double valor, String nome) {
		Lance lance = new Lance(new Usuario(nome), BigDecimal.valueOf(valor));
		lances.add(lance);
	}
	
	@Quando("propoe varios lances ao leilao")
	public void propoe_varios_lances_ao_leilao() {
		lances.forEach(lance -> leilao.propoe(lance));
	}
	
	@Entao("os lances sao aceitos")
	public void os_lances_sao_aceitos() {
		assertEquals(this.lances.size(), leilao.getLances().size());
		assertEquals(this.lances.get(0).getValor(), leilao.getLances().get(0).getValor());
		assertEquals(this.lances.get(1).getValor(), leilao.getLances().get(1).getValor());
	}
}
