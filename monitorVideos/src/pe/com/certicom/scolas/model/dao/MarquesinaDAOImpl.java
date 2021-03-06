package pe.com.certicom.scolas.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import pe.com.certicom.scolas.model.beans.Marquesina;
import pe.com.certicom.scolas.model.beans.MarquesinaExample;

public class MarquesinaDAOImpl extends SqlMapClientDaoSupport implements MarquesinaDAO {

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table MARQUESINA
     *
     * @ibatorgenerated Sun Dec 11 16:55:16 COT 2011
     */
    public MarquesinaDAOImpl() {
        super();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table MARQUESINA
     *
     * @ibatorgenerated Sun Dec 11 16:55:16 COT 2011
     */
    public int countByExample(MarquesinaExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("MARQUESINA.ibatorgenerated_countByExample", example);
        return count;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table MARQUESINA
     *
     * @ibatorgenerated Sun Dec 11 16:55:16 COT 2011
     */
    public int deleteByExample(MarquesinaExample example) {
        int rows = getSqlMapClientTemplate().delete("MARQUESINA.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table MARQUESINA
     *
     * @ibatorgenerated Sun Dec 11 16:55:16 COT 2011
     */
    public int deleteByPrimaryKey(Integer idMarquesina) {
        Marquesina key = new Marquesina();
        key.setIdMarquesina(idMarquesina);
        int rows = getSqlMapClientTemplate().delete("MARQUESINA.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table MARQUESINA
     *
     * @ibatorgenerated Sun Dec 11 16:55:16 COT 2011
     */
    public void insert(Marquesina record) {
        getSqlMapClientTemplate().insert("MARQUESINA.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table MARQUESINA
     *
     * @ibatorgenerated Sun Dec 11 16:55:16 COT 2011
     */
    public void insertSelective(Marquesina record) {
        getSqlMapClientTemplate().insert("MARQUESINA.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table MARQUESINA
     *
     * @ibatorgenerated Sun Dec 11 16:55:16 COT 2011
     */
    @SuppressWarnings("unchecked")
    public List<Marquesina> selectByExample(MarquesinaExample example) {
        List<Marquesina> list = getSqlMapClientTemplate().queryForList("MARQUESINA.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table MARQUESINA
     *
     * @ibatorgenerated Sun Dec 11 16:55:16 COT 2011
     */
    public Marquesina selectByPrimaryKey(Integer idMarquesina) {
        Marquesina key = new Marquesina();
        key.setIdMarquesina(idMarquesina);
        Marquesina record = (Marquesina) getSqlMapClientTemplate().queryForObject("MARQUESINA.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table MARQUESINA
     *
     * @ibatorgenerated Sun Dec 11 16:55:16 COT 2011
     */
    public int updateByExampleSelective(Marquesina record, MarquesinaExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("MARQUESINA.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table MARQUESINA
     *
     * @ibatorgenerated Sun Dec 11 16:55:16 COT 2011
     */
    public int updateByExample(Marquesina record, MarquesinaExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("MARQUESINA.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table MARQUESINA
     *
     * @ibatorgenerated Sun Dec 11 16:55:16 COT 2011
     */
    public int updateByPrimaryKeySelective(Marquesina record) {
        int rows = getSqlMapClientTemplate().update("MARQUESINA.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table MARQUESINA
     *
     * @ibatorgenerated Sun Dec 11 16:55:16 COT 2011
     */
    public int updateByPrimaryKey(Marquesina record) {
        int rows = getSqlMapClientTemplate().update("MARQUESINA.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    public List<Marquesina> listarMarquesinas(Integer idConfiguracion) {
        Map<String,Integer> parametros = new HashMap<String,Integer>();
        parametros.put("idConfiguracion",idConfiguracion);
        List<Marquesina> list = getSqlMapClientTemplate().queryForList("MARQUESINA.listarMarquesinas",parametros);
        return list;
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table MARQUESINA
     *
     * @ibatorgenerated Sun Dec 11 16:55:16 COT 2011
     */
    private static class UpdateByExampleParms extends MarquesinaExample {
        private Object record;

        public UpdateByExampleParms(Object record, MarquesinaExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}