package net.scales.model;

import java.util.Date;

public class ScalesData {

	private Long id;
	private String conducator;
	private String auto;
	private String remorca;
	private CustomItem transportator;
	private CustomItem destinatar;
	private CustomItem punctulSosire;
	private CustomItem statiaDestinatie;
	private CustomItem tipulCereale;
	private Integer sezon;
	private String ttnNumber;
	private Date ttnData;
	private Double ttnMasa;
	private Integer nrAnaliz;
	private Double masaBruto;
	private Double masaTara;
	private Date dateIn;
	private Date dateOut;
	private CustomItem elevator;
	private Long userid;
	private Long div;

	public Double getMasaNeto() {
		double b = masaBruto == null ? 0 : masaBruto;
		double t = masaTara == null ? 0 : masaTara;
		return Math.round((b - t) * 100.0) / 100.0;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getConducator() {
		return conducator;
	}
	public void setConducator(String conducator) {
		this.conducator = conducator;
	}
	public CustomItem getTransportator() {
		return transportator;
	}
	public void setTransportator(CustomItem transportator) {
		this.transportator = transportator;
	}
	public CustomItem getDestinatar() {
		return destinatar;
	}
	public void setDestinatar(CustomItem destinatar) {
		this.destinatar = destinatar;
	}
	public CustomItem getPunctulSosire() {
		return punctulSosire;
	}
	public void setPunctulSosire(CustomItem punctulSosire) {
		this.punctulSosire = punctulSosire;
	}
	public CustomItem getStatiaDestinatie() {
		return statiaDestinatie;
	}
	public void setStatiaDestinatie(CustomItem statiaDestinatie) {
		this.statiaDestinatie = statiaDestinatie;
	}
	public CustomItem getTipulCereale() {
		return tipulCereale;
	}
	public void setTipulCereale(CustomItem tipulCereale) {
		this.tipulCereale = tipulCereale;
	}
	public Integer getSezon() {
		return sezon;
	}
	public void setSezon(Integer sezon) {
		this.sezon = sezon;
	}
	public String getTtnNumber() {
		return ttnNumber;
	}
	public void setTtnNumber(String ttnNumber) {
		this.ttnNumber = ttnNumber;
	}
	public Date getTtnData() {
		return ttnData;
	}
	public void setTtnData(Date ttnData) {
		this.ttnData = ttnData;
	}
	public Integer getNrAnaliz() {
		return nrAnaliz;
	}
	public void setNrAnaliz(Integer nrAnaliz) {
		this.nrAnaliz = nrAnaliz;
	}
	public Double getMasaBruto() {
		return masaBruto;
	}
	public void setMasaBruto(Double masaBruto) {
		this.masaBruto = masaBruto;
	}
	public Double getMasaTara() {
		return masaTara;
	}
	public void setMasaTara(Double masaTara) {
		this.masaTara = masaTara;
	}
	public Date getDateIn() {
		return dateIn;
	}
	public void setDateIn(Date dateIn) {
		this.dateIn = dateIn;
	}
	public Date getDateOut() {
		return dateOut;
	}
	public void setDateOut(Date dateOut) {
		this.dateOut = dateOut;
	}
	public CustomItem getElevator() {
		return elevator;
	}
	public void setElevator(CustomItem elevator) {
		this.elevator = elevator;
	}
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public String getAuto() {
		return auto;
	}
	public void setAuto(String auto) {
		this.auto = auto;
	}
	public String getRemorca() {
		return remorca;
	}
	public void setRemorca(String remorca) {
		this.remorca = remorca;
	}
	public Double getTtnMasa() {
		return ttnMasa;
	}
	public void setTtnMasa(Double ttnMasa) {
		this.ttnMasa = ttnMasa;
	}
	public Long getDiv() {
		return div;
	}
	public void setDiv(Long div) {
		this.div = div;
	}
}
