package t6;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

//Dados que vão em cada linha da tabela
public class TableData {
	private SimpleStringProperty  curso = new SimpleStringProperty("-");
    private SimpleStringProperty  ano = new SimpleStringProperty("-");
    private SimpleStringProperty  prova = new SimpleStringProperty("-");
    private SimpleStringProperty  tipoquestao = new SimpleStringProperty("-");
    private SimpleStringProperty  idquestao = new SimpleStringProperty("-");
    private SimpleStringProperty  objeto = new SimpleStringProperty("-");
    private SimpleStringProperty  objetodetalhado = new SimpleStringProperty("-");
    private SimpleStringProperty  gabarito = new SimpleStringProperty("-");
	private SimpleStringProperty  acertoscurso = new SimpleStringProperty("-");
    private SimpleStringProperty  acertosregiao = new SimpleStringProperty("-");
    private SimpleStringProperty  acertosbrasil = new SimpleStringProperty("-");
    private SimpleStringProperty  acertosdif = new SimpleStringProperty("-");
    private SimpleStringProperty  texto = new SimpleStringProperty("-");
    private SimpleStringProperty  imagem = new SimpleStringProperty("-");
    private SimpleStringProperty  urlprova = new SimpleStringProperty("-");
    private SimpleStringProperty  urlintese = new SimpleStringProperty("-");
    private SimpleStringProperty  urlcurso = new SimpleStringProperty("-");
    private SimpleStringProperty  urlcrop = new SimpleStringProperty("-");
    
	/*public TableData(String curso,String ano,String prova,String tipoquestao,String idquestao,String objeto,String objetodetalhado,String gabarito,String acertoscurso,String acertosregiao,String acertosbrasil,String acertosdif,String texto,String imagem,String urlprova,String urlintese,String urlcurso,String urlcrop){
		this.curso = new SimpleStringProperty(curso);
		this.ano = new SimpleStringProperty(ano);
		this.prova = new SimpleStringProperty(prova);
		this.tipoquestao = new SimpleStringProperty(tipoquestao);
		this.idquestao = new SimpleStringProperty(idquestao);
		this.objeto = new SimpleStringProperty(objeto);
		this.objetodetalhado = new SimpleStringProperty(objetodetalhado);
		this.gabarito = new SimpleStringProperty(gabarito);
		this.acertoscurso = new SimpleStringProperty(acertoscurso);
		this.acertosregiao = new SimpleStringProperty(acertosregiao);
		this.acertosbrasil = new SimpleStringProperty(acertosbrasil);
		this.acertosdif = new SimpleStringProperty(acertosdif);
		this.texto = new SimpleStringProperty(texto);
		this.imagem = new SimpleStringProperty(imagem);
		this.urlprova = new SimpleStringProperty(urlprova);
		this.urlintese = new SimpleStringProperty(urlintese);
		this.urlcurso = new SimpleStringProperty(urlcurso);
		this.urlcrop = new SimpleStringProperty(urlcrop);
	
	}*/
	public TableData(ObservableList<String> data) {
		try {
			this.setCurso(data.get(0));
			this.setAno(data.get(1));
			this.setProva(data.get(2));
			this.setTipoquestao(data.get(3));
			this.setIdquestao(data.get(4));
			this.setObjeto(data.get(5));
			this.setObjetodetalhado(data.get(6));
			this.setGabarito(data.get(7));
			this.setAcertoscurso(data.get(8));
			this.setAcertosregiao(data.get(9));
			this.setAcertosbrasil(data.get(10));
			this.setAcertosdif(data.get(11));
			this.setUrlprova(data.get(12));
			this.setUrlintese(data.get(13));
			this.setUrlcurso(data.get(14));
			this.setUrlcrop(data.get(15));
		}catch(IndexOutOfBoundsException e) {}
	}
	public SimpleStringProperty cursoProperty(){return curso;}
	public SimpleStringProperty anoProperty(){return ano;}
	public SimpleStringProperty provaProperty(){return prova;}
	public SimpleStringProperty tipoquestaoProperty(){return tipoquestao;}
	public SimpleStringProperty idquestaoProperty(){return idquestao;}
	public SimpleStringProperty objetoProperty(){return objeto;}
	public SimpleStringProperty objetodetalhadoProperty(){return objetodetalhado;}
	public SimpleStringProperty gabaritoProperty(){return gabarito;}
	public SimpleStringProperty acertoscursoProperty(){return acertoscurso;}
	public SimpleStringProperty acertosregiaoProperty(){return acertosregiao;}
	public SimpleStringProperty acertosbrasilProperty(){return acertosbrasil;}
	public SimpleStringProperty acertosdifProperty(){return acertosdif;}
	public SimpleStringProperty textoProperty(){return texto;}
	public SimpleStringProperty imagemProperty(){return imagem;}
	public SimpleStringProperty urlprovaProperty(){return urlprova;}
	public SimpleStringProperty urlinteseProperty(){return urlintese;}
	public SimpleStringProperty urlcursoProperty(){return urlcurso;}
	public SimpleStringProperty urlcropProperty(){return urlcrop;}
	
    public String getCurso() {return curso.get();}
    public String getAno() {return ano.get();}
    public String getProva() {return prova.get();}
    public String getTipoquestao() {return tipoquestao.get();}
    public String getIdquestao() {return idquestao.get();}
    public String getObjeto() {return objeto.get();}
    public String getObjetodetalhado() {return objetodetalhado.get();}
    public String getGabarito() {return gabarito.get();}
    public String getAcertoscurso() {return acertoscurso.get();}
    public String getAcertosregiao() {return acertosregiao.get();}
    public String getAcertosbrasil() {return acertosbrasil.get();}
    public String getAcertosdif() {return acertosdif.get();}
    public String getTexto() {return texto.get();}
    public String getImagem() {return imagem.get();}
    public String getUrlprova() {return urlprova.get();}
    public String getUrlintese() {return urlintese.get();}
    public String getUrlcurso() {return urlcurso.get();}
    public String getUrlcrop() {return urlcrop.get();}
    
    public void setCurso(String c) {curso.set(c);}
    public void setAno(String a) {ano.set(a);}
    public void setProva(String p) {prova.set(p);}
    public void setTipoquestao(String t) {tipoquestao.set(t);}
    public void setIdquestao(String i) {idquestao.set(i);}
    public void setObjeto(String o) {objeto.set(o);}
    public void setObjetodetalhado(String o) {objetodetalhado.set(o);}
    public void setGabarito(String g) {gabarito.set(g);}
    public void setAcertoscurso(String a) {acertoscurso.set(a);}
    public void setAcertosregiao(String a) {acertosregiao.set(a);}
    public void setAcertosbrasil(String a) {acertosbrasil.set(a);}
    public void setAcertosdif(String a) {acertosdif.set(a);}
    public void setTexto(String t) {texto.set(t);}
    public void setImagem(String i) {imagem.set(i);}
    public void setUrlprova(String u) {urlprova.set(u);}
    public void setUrlintese(String u) {urlintese.set(u);}
    public void setUrlcurso(String u) {urlcurso.set(u);}
    public void setUrlcrop(String u) {urlcrop.set(u);}
}

