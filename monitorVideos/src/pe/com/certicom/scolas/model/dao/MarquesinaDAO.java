package pe.com.certicom.scolas.model.dao;

import java.util.List;
import pe.com.certicom.scolas.model.beans.Marquesina;
import pe.com.certicom.scolas.model.beans.MarquesinaExample;

public interface MarquesinaDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table MARQUESINA
     *
     * @ibatorgenerated Sun Dec 11 16:55:16 COT 2011
     */
    int countByExample(MarquesinaExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table MARQUESINA
     *
     * @ibatorgenerated Sun Dec 11 16:55:16 COT 2011
     */
    int deleteByExample(MarquesinaExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table MARQUESINA
     *
     * @ibatorgenerated Sun Dec 11 16:55:16 COT 2011
     */
    int deleteByPrimaryKey(Integer idMarquesina);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table MARQUESINA
     *
     * @ibatorgenerated Sun Dec 11 16:55:16 COT 2011
     */
    void insert(Marquesina record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table MARQUESINA
     *
     * @ibatorgenerated Sun Dec 11 16:55:16 COT 2011
     */
    void insertSelective(Marquesina record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table MARQUESINA
     *
     * @ibatorgenerated Sun Dec 11 16:55:16 COT 2011
     */
    List<Marquesina> selectByExample(MarquesinaExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table MARQUESINA
     *
     * @ibatorgenerated Sun Dec 11 16:55:16 COT 2011
     */
    Marquesina selectByPrimaryKey(Integer idMarquesina);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table MARQUESINA
     *
     * @ibatorgenerated Sun Dec 11 16:55:16 COT 2011
     */
    int updateByExampleSelective(Marquesina record, MarquesinaExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table MARQUESINA
     *
     * @ibatorgenerated Sun Dec 11 16:55:16 COT 2011
     */
    int updateByExample(Marquesina record, MarquesinaExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table MARQUESINA
     *
     * @ibatorgenerated Sun Dec 11 16:55:16 COT 2011
     */
    int updateByPrimaryKeySelective(Marquesina record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table MARQUESINA
     *
     * @ibatorgenerated Sun Dec 11 16:55:16 COT 2011
     */
    int updateByPrimaryKey(Marquesina record);


    List<Marquesina> listarMarquesinas(Integer idConfiguracion);
}