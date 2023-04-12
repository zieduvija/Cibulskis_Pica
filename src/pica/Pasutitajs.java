package pica;

public class Pasutitajs {
	String vards;
	String uzvards;
	String telefonaNumurs;
	String adrese;
	String apmakasasVeids;

	Pasutitajs(String vards, String uzvards, String telefonaNumurs, String adrese, String apmakasasVeids) {
		this.vards = vards;
		this.uzvards = uzvards;
		this.telefonaNumurs = telefonaNumurs;
		this.adrese = adrese;
		this.apmakasasVeids = apmakasasVeids;
	}

	public String getVards() {
		return this.vards;
	}

	public String getUzvards() {
		return this.uzvards;
	}

	public String getApmaksasVeids() {
		return this.apmakasasVeids;
	}

	public String getEsana() {
		if (!this.adrese.equalsIgnoreCase("Nav"))
			return "Piegāde uz " + this.adrese;
		else
			return "Uz vietas";
	}

}
