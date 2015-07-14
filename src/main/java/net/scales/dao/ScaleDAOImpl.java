package net.scales.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import net.scales.model.Scales;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ScaleDAOImpl extends AbstractDAOImpl implements ScaleDAO {

	@SuppressWarnings("unchecked")
	public List<Object> getScalesList() {
		String sql = "SELECT ID, SOFER, NR_VAGON, NR_REMORCA, DEP_PEREVOZ, nvl(CLCDEP_PEREVOZ, DEP_PEREVOZ_TEXT), "
				+ "DEP_DESTINAT , nvl(CLCDEP_DESTINATT, DEP_DESTINAT_TEXT),	PRAZGRUZ_S_12 ,CLCPRAZGRUZ_S_12T, PUNCTTO_S_12 ,CLCPUNCTTO_S_12T, SC, CLCSCT, "
				+ "SEZON_YYYY,	TTN_N, TTN_DATA, NR_ANALIZ,	MASA_BRUTTO_RO,	MASA_TARA_RO, TIME_IN, TIME_OUT, "
				+ "ELEVATOR, CLCELEVATORT,	USERID,	DIV	FROM ytrans_VTF_PROHODN_OUT	ORDER BY id ";
		return currentSession().createSQLQuery(sql).list();
	}

	@SuppressWarnings("unchecked")
	public List<Object> getScalesListByPeriod(Date date1, Date date2) {
		String sql = "SELECT ID, SOFER, NR_VAGON,NR_REMORCA, DEP_PEREVOZ, nvl(CLCDEP_PEREVOZ, DEP_PEREVOZ_TEXT), "
				+ "DEP_DESTINAT ,nvl(CLCDEP_DESTINATT, DEP_DESTINAT_TEXT),	PRAZGRUZ_S_12 ,CLCPRAZGRUZ_S_12T, PUNCTTO_S_12 ,CLCPUNCTTO_S_12T, SC, CLCSCT, "
				+ "SEZON_YYYY,	TTN_N, TTN_DATA, NR_ANALIZ,	MASA_BRUTTO_RO,	MASA_TARA_RO, TIME_IN, TIME_OUT, "
				+ "ELEVATOR, CLCELEVATORT,	USERID,	DIV	FROM ytrans_VTF_PROHODN_OUT where TRUNC(TIME_IN,'DD') between TRUNC(to_date(:FDATA1),'DD') and TRUNC(to_date(:FDATA2),'DD')	ORDER BY id ";
		return currentSession().createSQLQuery(sql).setDate("FDATA1", date1)
				.setDate("FDATA2", date2).list();
	}

	@SuppressWarnings("unchecked")
	public List<Object> getScalesInByPeriod(Date date1, Date date2) {
		String sql = "SELECT ID, SOFER, AUTO,NR_REMORCA, DEP_POSTAV, "
				+ "decode(DEP_POSTAV,(select value from a$env where name = 'DEF_FURNIZOR'),DEP_POSTAV_TEXT,CLCDEP_POSTAVT), "
				+ "PPOGRUZ_S_12  ,CLCPPOGRUZ_S_12T, SC_MP, CLCSC_MPT, "
				+ "SEZON_YYYY,	TTN_N, TTN_DATA, MASA_TTN, NR_ANALIZ,DEP_TRANSP ,nvl(CLCDEP_TRANSPT, DEP_TRANSP_TEXT),	MASA_BRUTTO,	MASA_TARA, TIME_IN, TIME_OUT,DIV, "
				+ "ELEVATOR, CLCELEVATORT,	USERID	FROM VTF_PROHODN_MPFS where TRUNC(TIME_IN,'DD') between TRUNC(to_date(:FDATA1),'DD') and TRUNC(to_date(:FDATA2),'DD')	ORDER BY id ";
		return currentSession().createSQLQuery(sql).setDate("FDATA1", date1)
				.setDate("FDATA2", date2).list();
	}

	@SuppressWarnings("unchecked")
	public List<Object> getConducatorsList() {
		String sql = "SELECT cod1, denumirea FROM(select s.*,(select v.um  from vms_syss v where v.tip='S' and v.cod=15 and v.cod1=s.number1)um1t from vms_syss s where tip='S' and  cod=14 and cod1<>0)";
		List<Object> list = currentSession().createSQLQuery(sql).list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Object> getUniversList(String tip, String gr1) {
		String sql = "select cod, denumirea from vms_univers where tip=:tip and gr1=:gr1";
		List<Object> list = currentSession().createSQLQuery(sql)
				.setString("tip", tip).setString("gr1", gr1).list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Object> getSyssList(String tip, Integer gr1) {
		String sql = "SELECT cod1, denumirea FROM VMS_SYSS S WHERE (TIP=:tip AND COD=:gr1 AND COD1>0)";
		List<Object> list = currentSession().createSQLQuery(sql)
				.setString("tip", tip).setInteger("gr1", gr1).list();
		return list;
	}

	public int updateScale(Scales data) throws HibernateException,
			SQLException {
		String sql = "UPDATE ytrans_vtf_prohodn_out SET  sofer = :p2,"
				+ "nr_vagon = :p3, nr_remorca = :p4,"
				+ "dep_perevoz = :p5, dep_destinat = :p6,"
				+ "prazgruz_s_12 = :p7, punctto_s_12 = :p8, sc = :p9,"
				+ "sezon_yyyy = :p10, ttn_n = :p11, ttn_data = :p12,"
				+ "nr_analiz = :p13, masa_brutto = :p14,"
				+ "masa_tara = :p15,masa_netto = :p16,"
				+ "time_in = :p17,time_out = NVL (:p18, SYSDATE),"
				+ "elevator = :p19,userid = :p20,"
				+ "dep_perevoz_text = :p21, dep_destinat_text = :p22 WHERE ID = :p1";
		Query updateQuery = currentSession().createSQLQuery(sql);
		return updateQuery.setString("p1", parseNumber(data.getId()))
				.setString("p2", data.getConducator())
				.setString("p3", data.getAuto())
				.setString("p4", data.getRemorca())
				.setString("p5", parseItem(data.getTransportator()))
				.setString("p6", parseItem(data.getDestinatar()))
				.setString("p7", parseItem(data.getPunctulSosire()))
				.setString("p8", parseItem(data.getStatiaDestinatie()))
				.setString("p9", parseItem(data.getTipulCereale()))
				.setString("p10", parseNumber(data.getSezon()))
				.setString("p11", data.getTtnNumber())
				.setDate("p12", data.getTtnData())
				.setString("p13", parseNumber(data.getNrAnaliz()))
				.setString("p14", parseNumber(data.getMasaBruto()))
				.setString("p15", parseNumber(data.getMasaTara()))
				.setString("p16", parseNumber(data.getMasaNeto()))
				.setDate("p17", data.getDateIn())
				.setDate("p18", data.getDateOut())
				.setString("p19", parseItem(data.getElevator()))
				.setString("p20", parseNumber(data.getUserid()))
				.setString("p21", parseItemText(data.getTransportator()))
				.setString("p22", parseItemText(data.getDestinatar()))
				.executeUpdate();
	}

	public int insertScale(Scales data) throws HibernateException,
			SQLException {

		String sql = "insert into ytrans_vtf_prohodn_out "
				+ "(id,sofer,nr_vagon,nr_remorca,dep_perevoz,dep_destinat,prazgruz_s_12,punctto_s_12,sc,sezon_yyyy,ttn_n,ttn_data,nr_analiz,masa_brutto,masa_tara,masa_netto,time_in,time_out,elevator,userid, priznak_arm,div,dep_perevoz_text,dep_destinat_text) "
				+ " values (ID_MP_VESY.NEXTVAL, :p2, :p3, :p4, :p5, :p6, :p7, :p8, :p9, :p10, :p11, :p12, :p13, :p14, :p15, :p16, :p17, NVL(:p18, SYSDATE), :p22, :p23, 2, :p21, :p19, :p20) ";
		Query updateQuery = currentSession().createSQLQuery(sql);
		return updateQuery.setString("p2", data.getConducator())
				.setString("p3", data.getAuto())
				.setString("p4", data.getRemorca())
				.setString("p5", parseItem(data.getTransportator()))
				.setString("p6", parseItem(data.getDestinatar()))
				.setString("p7", parseItem(data.getPunctulSosire()))
				.setString("p8", parseItem(data.getStatiaDestinatie()))
				.setString("p9", parseItem(data.getTipulCereale()))
				.setString("p10", parseNumber(data.getSezon()))
				.setString("p11", data.getTtnNumber())
				.setDate("p12", data.getTtnData())
				.setString("p13", parseNumber(data.getNrAnaliz()))
				.setString("p14", parseNumber(data.getMasaBruto()))
				.setString("p15", parseNumber(data.getMasaTara()))
				.setString("p16", parseNumber(data.getMasaNeto()))
				.setDate("p17", data.getDateIn())
				.setDate("p18", data.getDateOut())
				.setString("p19", parseItemText(data.getTransportator()))
				.setString("p20", parseItemText(data.getDestinatar()))
				.setString("p21", parseNumber(data.getDiv()))
				.setString("p22", parseItem(data.getElevator()))
				.setString("p23", parseNumber(data.getUserid()))
				.executeUpdate();
	}

	public int updateScaleIn(Scales data) throws HibernateException,
			SQLException {
		String sql = "UPDATE VTF_PROHODN_MPFS set  sofer = :p2,"
				+ "auto = :p3, nr_remorca = :p4,"
				+ "dep_postav = :p5, dep_postav_text = :p6,"
				+ "ppogruz_s_12 = :p7, sc_mp = :p8, sezon_yyyy = :p9,"
				+ "ttn_n = :p10, ttn_data = :p11, masa_ttn = :p12,"
				+ "nr_analiz = :p13, dep_transp = :p14, dep_transp_text = :p15,"
				+ "masa_brutto = :p16,masa_tara = :p17,masa_netto = :p18,"
				+ "time_in = :p19,time_out = NVL(:p20, SYSDATE),"
				+ "elevator = :p21,userid = :p22 WHERE ID = :p1";
		Query updateQuery = currentSession().createSQLQuery(sql);
		return updateQuery.setString("p1", parseNumber(data.getId()))
				.setString("p2", data.getConducator())
				.setString("p3", data.getAuto())
				.setString("p4", data.getRemorca())
				.setString("p5", parseItem(data.getDestinatar()))
				.setString("p6", parseItemText(data.getDestinatar()))
				.setString("p7", parseItem(data.getPunctulSosire()))
				.setString("p8", parseItem(data.getTipulCereale()))
				.setString("p9", parseNumber(data.getSezon()))
				.setString("p10", data.getTtnNumber())
				.setDate("p11", data.getTtnData())
				.setString("p12", parseNumber(data.getTtnMasa()))
				.setString("p13", parseNumber(data.getNrAnaliz()))
				.setString("p14", parseItem(data.getTransportator()))
				.setString("p15", parseItemText(data.getTransportator()))
				.setString("p16", parseNumber(data.getMasaBruto()))
				.setString("p17", parseNumber(data.getMasaTara()))
				.setString("p18", parseNumber(data.getMasaNeto()))
				.setDate("p19", data.getDateIn())
				.setDate("p20", data.getDateOut())
				.setString("p21", parseItem(data.getElevator()))
				.setString("p22", parseNumber(data.getUserid()))
				.executeUpdate();
	}

	public int insertScaleIn(Scales data) throws HibernateException,
			SQLException {
		String sql = "insert into VTF_PROHODN_MPFS "
				+ "(id,sofer,auto,nr_remorca,dep_postav, dep_postav_text,ppogruz_s_12  , sc_mp, sezon_yyyy,	ttn_n, ttn_data, masa_ttn, nr_analiz,dep_transp ,dep_transp_text,masa_brutto,masa_tara, masa_netto, time_in, time_out,div,elevator,userid,priznak_arm) "
				+ " values (id_mp_vesy.nextval, :p2, :p3, :p4, :p5, :p6, :p7, :p8, :p9, :p10, :p11, :p12, :p13, :p14, :p15, :p16, :p17, :p18, :p19, NVL(:p20, SYSDATE), :p21, :p22, :p23, 1) ";
		Query updateQuery = currentSession().createSQLQuery(sql);
		return updateQuery.setString("p2", data.getConducator())
				.setString("p3", data.getAuto())
				.setString("p4", data.getRemorca())
				.setString("p5", parseItem(data.getDestinatar()))				
				.setString("p6", parseItemText(data.getDestinatar()))
				.setString("p7", parseItem(data.getPunctulSosire()))
				.setString("p8", parseItem(data.getTipulCereale()))
				.setString("p9", parseNumber(data.getSezon()))
				.setString("p10", data.getTtnNumber())
				.setDate("p11", data.getTtnData())
				.setString("p12", parseNumber(data.getTtnMasa()))
				.setString("p13", parseNumber(data.getNrAnaliz()))
				.setString("p14", parseItem(data.getTransportator()))
				.setString("p15", parseItemText(data.getTransportator()))
				.setString("p16", parseNumber(data.getMasaBruto()))
				.setString("p17", parseNumber(data.getMasaTara()))
				.setString("p18", parseNumber(data.getMasaNeto()))
				.setDate("p19", data.getDateIn())
				.setDate("p20", data.getDateOut())
				.setString("p21", parseNumber(data.getDiv()))
				.setString("p22", parseItem(data.getElevator()))
				.setString("p23", parseNumber(data.getUserid()))
				.executeUpdate();
	}

	public List<Object> getTransportList(int i, String query) {
		String sql = "select cod, denumirea ||', '|| codvechi as denumirea from vms_univers where tip='O' and gr1='E'";
		return getLimitList(i, query, sql);
	}

	public List<Object> getDestinatarList(int i, String query) {
		String sql = "select cod, denumirea ||', '|| codvechi as denumirea from vms_univers where tip='O' and gr1 in ('E', 'COTA')";
		return getLimitList(i, query, sql);
	}

	public List<Object> getPunctulList(int i, String query) {
		String sql = "select cod1 as cod, denumirea ||', '|| nmb1t ||', '|| nmb2t as denumirea from vms_syss s where tip='S' and cod='12' and cod1>0";
		return getLimitList(i, query, sql);
	}

	public List<Object> getTipulList(int i, String query) {
		String sql = "select cod, denumirea from vms_univers where tip='M' and gr1='P'";
		return getLimitList(i, query, sql);
	}

	public int getDefSezon(Date fdate, long div) {
		String sql = "select nvl(max(sezon_yyyy),to_char(to_date(:fdate), 'yyyy')) from yvtrans_sezon a where :fdate between datastart and dataend and div = :div";
		Query query = currentSession().createSQLQuery(sql)
				.setDate("fdate", fdate).setLong("div", div);
		return Integer.parseInt(query.uniqueResult().toString());
	}

	public Object getEnvParam(String param) {
		String sql = "select max(value) from a$env where name = :param";
		return currentSession().createSQLQuery(sql)
				.setString("param", param).uniqueResult();
	}
}
