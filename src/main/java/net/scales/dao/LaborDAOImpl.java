package net.scales.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import net.scales.model.Labor;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

@Repository
public class LaborDAOImpl extends AbstractDAOImpl implements LaborDAO {

	@SuppressWarnings("unchecked")
	public List<Object> getLaborsByPeriod(Date date) {
		String sql = "select sezon_yyyy, nr_analiz, data_analiz, anlz_vlajn, anlz_sorn, "
				+ "anlz_kislot, anlz_zernprim, anlz_zaraj, anlz_maslprim, "
				+ "sanlz_vlajn, sanlz_sorn, sanlz_kislot, sanlz_zernprim, "
				+ "sanlz_zaraj, sanlz_maslprim, txt_comment, userid, sertificat_nr, "
				+ "priznak_arm, partida, ttn_seria, ttn_nr, anlz_natura, anlz_kleik, "
				+ "div,sc_mp,clcsc_mpt,anlz_pr_vih_zerna,nr_sert,anlz_bitoe_zerno,elevator, "
				+ "CLCDEP_POSTAVT ,CLCDEP_GRUZOOTPRAVITT "
				+ "from VTF_LABOR_MP "
				+ "where to_date(:cdate) between to_date(to_char('14.06')||'.'||to_char(sezon_yyyy)) "
				+ "and to_date(to_char('14.06')||'.'||to_char(sezon_yyyy+1)) and div=un$div.get_def() "
				+ "and (NR_ANALIZ>0 and (div is null or instr(','||un$div.get_def||',',','||div||',')>0)) ";
		return currentSession().createSQLQuery(sql).setDate("cdate", date)
				.list();
	}

	public int updateLabor(Labor data) throws HibernateException, SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insertScale(Labor data) throws HibernateException, SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Object> getTipulList(int i, String query) {
		String sql = "select cod, denumirea from vms_univers where tip='M' and gr1='P'";
		return getLimitList(i, query, sql);
	}

}
