package main;

public class Word {

	private String artikel;
	private String word;
	private String answer;
	private String plural;
	public Word(String artikel, String word, String prular,String answer) {
		super();
		this.artikel = artikel;
		this.word = word;
		this.answer = answer;
		this.plural = prular;
	}
	
	public String getArtikel() {
		return artikel;
	}
	public void setArtikel(String artikel) {
		this.artikel = artikel;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getPrular() {
		return plural;
	}
	public void setPrular(String prular) {
		this.plural = prular;
	}
	
	
	
	
	
}
