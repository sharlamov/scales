package net.scales.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import net.scales.model.Labor;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class LaborDAOImpl extends AbstractDAOImpl implements LaborDAO {

	@SuppressWarnings("unchecked")
	public List<Object> getLaborsByPeriod(Date date) {
		String sql = "select sezon_yyyy, nr_analiz, trunc(data_analiz), anlz_vlajn, anlz_sorn, "
				+ "anlz_kislot, anlz_zernprim, anlz_zaraj, anlz_maslprim, "
				+ "sanlz_vlajn, sanlz_sorn, sanlz_kislot, sanlz_zernprim, "
				+ "sanlz_zaraj, sanlz_maslprim, txt_comment, userid, sertificat_nr, "
				+ "priznak_arm, partida, ttn_seria, ttn_nr, anlz_natura, anlz_kleik, "
				+ "div,sc_mp,clcsc_mpt,anlz_pr_vih_zerna,nr_sert,anlz_bitoe_zerno,elevator, "
				+ "CLCDEP_POSTAVT ,CLCDEP_GRUZOOTPRAVITT, sklad, clcskladt "
				+ "from VTF_LABOR_MP "
				+ "where to_date(:cdate) between to_date(to_char('14.06')||'.'||to_char(sezon_yyyy)) "
				+ "and to_date(to_char('14.06')||'.'||to_char(sezon_yyyy+1)) and div=un$div.get_def() "
				+ "and NR_ANALIZ>0";
		return currentSession().createSQLQuery(sql).setDate("cdate", date)
				.list();
	}

	@SuppressWarnings("unchecked")
	public List<Object> getLaborsOutByDate(Date date) {
		String sql = "select sezon_yyyy, nr_analiz, trunc(data_analiz), anlz_vlajn, anlz_sorn, "
				+ "anlz_kislot, anlz_zernprim, anlz_zaraj, anlz_maslprim, "
				+ "sanlz_vlajn, sanlz_sorn, sanlz_kislot, sanlz_zernprim, "
				+ "sanlz_zaraj, sanlz_maslprim, txt_comment, userid, sertificat_nr, "
				+ "priznak_arm, partida, ttn_seria, ttn_nr, anlz_natura, anlz_kleik, "
				+ "div,sc_mp,clcsc_mpt,anlz_pr_vih_zerna,nr_sert,anlz_bitoe_zerno,elevator, "
				+ "CLCDEP_POSTAVT ,CLCDEP_GRUZOOTPRAVITT, sklad, clcskladt "
				+ "from VTF_LABOR_MP "
				+ "where to_date(:cdate) between to_date(to_char('19.06')||'.'||to_char(sezon_yyyy)) "
				+ "and to_date(to_char('18.06')||'.'||to_char(sezon_yyyy+1)) and div=un$div.get_def() "
				+ "and nvl(PRIZNAK_ARM,0)<>20 and NR_ANALIZ between -200000 and 0 ";
		return currentSession().createSQLQuery(sql).setDate("cdate", date)
				.list();
	}

	public void updateLabor(Labor data, Labor oldData) throws HibernateException, SQLException {
		String sql = "update vtf_labor_mp set " +
				"sezon_yyyy = :sezon_yyyy, nr_analiz = :nr_analiz, data_analiz = :data_analiz, " +
				"anlz_vlajn = :anlz_vlajn, anlz_sorn = :anlz_sorn, anlz_kislot = :anlz_kislot, " +
				"anlz_zernprim = :anlz_zernprim, anlz_zaraj = :anlz_zaraj, anlz_maslprim = :anlz_maslprim, " +
				"sanlz_vlajn = :sanlz_vlajn, sanlz_sorn = :sanlz_sorn, sanlz_kislot = :sanlz_kislot, " +
				"sanlz_zernprim = :sanlz_zernprim, sanlz_zaraj = :sanlz_zaraj, sanlz_maslprim = :sanlz_maslprim, " +
				"txt_comment = :txt_comment, sertificat_nr = :sertificat_nr, sklad=:sklad, " +
				"partida = :partida, ttn_seria = :ttn_seria, ttn_nr = :ttn_nr, anlz_natura = :anlz_natura, anlz_kleik = :anlz_kleik, " +
				"sc_mp = :sc_mp, anlz_pr_vih_zerna = :anlz_pr_vih_zerna, nr_sert = :nr_sert, anlz_bitoe_zerno = :anlz_bitoe_zerno " +
				"where sezon_yyyy = :old_sezon_yyyy and nr_analiz = :old_nr_analiz and div = :old_div and elevator = :old_elevator ";
		Query updateQuery = currentSession().createSQLQuery(sql);
		updateQuery
				.setString("sezon_yyyy", parseNumber(data.getSezon()))
				.setString("nr_analiz", parseNumber(data.getNrAnaliz()))
				.setDate("data_analiz", data.getDateAnaliz())
				.setString("anlz_vlajn", parseNumber(data.getAnlzVlajn()))
				.setString("anlz_sorn", parseNumber(data.getAnlzSorn()))
				.setString("anlz_kislot", parseNumber(data.getAnlzKislot()))
				.setString("anlz_zernprim", parseNumber(data.getAnlzZernprim()))
				.setString("anlz_zaraj", parseNumber(data.getAnlzZaraj()))
				.setString("anlz_maslprim", parseNumber(data.getAnlzMaslprim()))
				.setString("sanlz_vlajn", parseNumber(data.getSanlzVlajn()))
				.setString("sanlz_sorn", parseNumber(data.getSanlzSorn()))
				.setString("sanlz_kislot", parseNumber(data.getSanlzKislot()))
				.setString("sanlz_zernprim", parseNumber(data.getSanlzZernprim()))
				.setString("sanlz_zaraj", parseNumber(data.getSanlzZaraj()))
				.setString("sanlz_maslprim", parseNumber(data.getSanlzMaslprim()))
				.setString("txt_comment", data.getComment())
				.setString("sertificat_nr", data.getSertificat())
				.setString("partida", parseNumber(data.getPartida()))
				.setString("ttn_seria", data.getTtnSeria())
				.setString("ttn_nr", parseNumber(data.getTtnNr()))
				.setString("anlz_natura", parseNumber(data.getAnlzNatura()))
				.setString("anlz_kleik", parseNumber(data.getAnlzKleik()))
				.setString("sc_mp", parseItem(data.getSc()))
				.setString("anlz_pr_vih_zerna", parseNumber(data.getAnlzVihod()))
				.setString("nr_sert", data.getNrSert())
				.setString("anlz_bitoe_zerno", parseNumber(data.getAnlzBitoeZerno()))
				.setString("old_sezon_yyyy", parseNumber(oldData.getSezon()))
				.setString("old_nr_analiz", parseNumber(oldData.getNrAnaliz()))
				.setString("old_div", parseNumber(oldData.getDiv()))
				.setString("old_elevator", parseNumber(oldData.getElevator()))
				.setString("sklad", parseItem(data.getDep()))
				.executeUpdate();
	}

	public void insertLabor(Labor data) throws HibernateException, SQLException {
		String sql = "insert into vtf_labor_mp " +
		  "(sezon_yyyy, nr_analiz, data_analiz, anlz_vlajn, anlz_sorn, " +
		  "anlz_kislot, anlz_zernprim, anlz_zaraj, anlz_maslprim, " +
		  "sanlz_vlajn, sanlz_sorn, sanlz_kislot, sanlz_zernprim, " +
		  "sanlz_zaraj, sanlz_maslprim, txt_comment, userid, sertificat_nr, " +
		  "priznak_arm, partida, ttn_seria, ttn_nr, anlz_natura, anlz_kleik, " +
		  "div,sc_mp,anlz_pr_vih_zerna,nr_sert,anlz_bitoe_zerno,elevator) " +
		  "values (:sezon_yyyy," + 
		  "(select nvl(max(NR_ANALIZ),0)+1 from tf_labor_mp where nr_analiz > 0 and div=:div and sezon_yyyy = :sezon_yyyy and elevator = :elevator), " + 
		  ":data_analiz, :anlz_vlajn, " +
		  ":anlz_sorn, :anlz_kislot, :anlz_zernprim, :anlz_zaraj, " +
		  ":anlz_maslprim, :sanlz_vlajn, :sanlz_sorn, :sanlz_kislot, " +
		  ":sanlz_zernprim, :sanlz_zaraj, :sanlz_maslprim, :txt_comment, " +
		  ":userid, :sertificat_nr, :priznak_arm, :partida, :ttn_seria, " +
		  ":ttn_nr, :anlz_natura, :anlz_kleik, :div,:sc_mp,:anlz_pr_vih_zerna,:nr_sert,:anlz_bitoe_zerno,:elevator) ";
		Query updateQuery = currentSession().createSQLQuery(sql);
		updateQuery
				.setString("sezon_yyyy", parseNumber(data.getSezon()))
				.setDate("data_analiz", data.getDateAnaliz())
				.setString("anlz_vlajn", parseNumber(data.getAnlzVlajn()))
				.setString("anlz_sorn", parseNumber(data.getAnlzSorn()))
				.setString("anlz_kislot", parseNumber(data.getAnlzKislot()))
				.setString("anlz_zernprim", parseNumber(data.getAnlzZernprim()))
				.setString("anlz_zaraj", parseNumber(data.getAnlzZaraj()))
				.setString("anlz_maslprim", parseNumber(data.getAnlzMaslprim()))
				.setString("sanlz_vlajn", parseNumber(data.getSanlzVlajn()))
				.setString("sanlz_sorn", parseNumber(data.getSanlzSorn()))
				.setString("sanlz_kislot", parseNumber(data.getSanlzKislot()))
				.setString("sanlz_zernprim", parseNumber(data.getSanlzZernprim()))
				.setString("sanlz_zaraj", parseNumber(data.getSanlzZaraj()))
				.setString("sanlz_maslprim", parseNumber(data.getSanlzMaslprim()))
				.setString("txt_comment", data.getComment())
				.setString("sertificat_nr", data.getSertificat())
				.setString("priznak_arm", parseNumber(data.getPriznakArm()))
				.setString("partida", parseNumber(data.getPartida()))
				.setString("ttn_seria", data.getTtnSeria())
				.setString("ttn_nr", parseNumber(data.getTtnNr()))
				.setString("anlz_natura", parseNumber(data.getAnlzNatura()))
				.setString("anlz_kleik", parseNumber(data.getAnlzKleik()))
				.setString("sc_mp", parseItem(data.getSc()))
				.setString("anlz_pr_vih_zerna", parseNumber(data.getAnlzVihod()))
				.setString("nr_sert", data.getNrSert())
				.setString("anlz_bitoe_zerno", parseNumber(data.getAnlzBitoeZerno()))
				.setString("userid", parseNumber(data.getUserId()))
				.setString("div", parseNumber(data.getDiv()))
				.setString("elevator", parseNumber(data.getElevator()))
				.executeUpdate();
	}

	public void insertLaborOut(Labor data) throws HibernateException, SQLException {
		String sql = "insert into vtf_labor_mp " +
		  "(sezon_yyyy, nr_analiz, data_analiz, anlz_vlajn, anlz_sorn, " +
		  "anlz_kislot, anlz_zernprim, anlz_zaraj, anlz_maslprim, " +
		  "sanlz_vlajn, sanlz_sorn, sanlz_kislot, sanlz_zernprim, " +
		  "sanlz_zaraj, sanlz_maslprim, txt_comment, userid, sertificat_nr, " +
		  "priznak_arm, partida, ttn_seria, ttn_nr, anlz_natura, anlz_kleik, " +
		  "div,sc_mp,anlz_pr_vih_zerna,nr_sert,anlz_bitoe_zerno,elevator) " +
		  "values (:sezon_yyyy," + 
		  "(select nvl(min(nr_analiz),0)-1 from tf_labor_mp where sezon_yyyy=:sezon_yyyy and div=:div and (nr_analiz<0 and nr_analiz>-200000 ) and nvl(priznak_arm,0)<>20 and elevator = :elevator), " + 
		  ":data_analiz, :anlz_vlajn, " +
		  ":anlz_sorn, :anlz_kislot, :anlz_zernprim, :anlz_zaraj, " +
		  ":anlz_maslprim, :sanlz_vlajn, :sanlz_sorn, :sanlz_kislot, " +
		  ":sanlz_zernprim, :sanlz_zaraj, :sanlz_maslprim, :txt_comment, " +
		  ":userid, :sertificat_nr, :priznak_arm, :partida, :ttn_seria, " +
		  ":ttn_nr, :anlz_natura, :anlz_kleik, :div,:sc_mp,:anlz_pr_vih_zerna,:nr_sert,:anlz_bitoe_zerno,:elevator) ";
		Query updateQuery = currentSession().createSQLQuery(sql);
		updateQuery
				.setString("sezon_yyyy", parseNumber(data.getSezon()))
				.setDate("data_analiz", data.getDateAnaliz())
				.setString("anlz_vlajn", parseNumber(data.getAnlzVlajn()))
				.setString("anlz_sorn", parseNumber(data.getAnlzSorn()))
				.setString("anlz_kislot", parseNumber(data.getAnlzKislot()))
				.setString("anlz_zernprim", parseNumber(data.getAnlzZernprim()))
				.setString("anlz_zaraj", parseNumber(data.getAnlzZaraj()))
				.setString("anlz_maslprim", parseNumber(data.getAnlzMaslprim()))
				.setString("sanlz_vlajn", parseNumber(data.getSanlzVlajn()))
				.setString("sanlz_sorn", parseNumber(data.getSanlzSorn()))
				.setString("sanlz_kislot", parseNumber(data.getSanlzKislot()))
				.setString("sanlz_zernprim", parseNumber(data.getSanlzZernprim()))
				.setString("sanlz_zaraj", parseNumber(data.getSanlzZaraj()))
				.setString("sanlz_maslprim", parseNumber(data.getSanlzMaslprim()))
				.setString("txt_comment", data.getComment())
				.setString("sertificat_nr", data.getSertificat())
				.setString("priznak_arm", parseNumber(data.getPriznakArm()))
				.setString("partida", parseNumber(data.getPartida()))
				.setString("ttn_seria", data.getTtnSeria())
				.setString("ttn_nr", parseNumber(data.getTtnNr()))
				.setString("anlz_natura", parseNumber(data.getAnlzNatura()))
				.setString("anlz_kleik", parseNumber(data.getAnlzKleik()))
				.setString("sc_mp", parseItem(data.getSc()))
				.setString("anlz_pr_vih_zerna", parseNumber(data.getAnlzVihod()))
				.setString("nr_sert", data.getNrSert())
				.setString("anlz_bitoe_zerno", parseNumber(data.getAnlzBitoeZerno()))
				.setString("userid", parseNumber(data.getUserId()))
				.setString("div", parseNumber(data.getDiv()))
				.setString("elevator", parseNumber(data.getElevator()))
				.executeUpdate();
	}

	public List<Object> getTipulList(int i, String query) {
		String sql = "select cod, denumirea from vms_univers where tip='M' and gr1='P'";
		return getLimitList(i, query, sql);
	}
	
	public List<Object> getDepList(int i, String query) {
		String sql = "select cod, denumirea from vms_univers where tip='O' and gr1='I'";
		return getLimitList(i, query, sql);
	}

}
