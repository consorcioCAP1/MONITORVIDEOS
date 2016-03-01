package pe.com.certicom.scolas.model.dao;

import java.util.List;
import pe.com.certicom.scolas.model.beans.Video;
import pe.com.certicom.scolas.model.beans.VideoExample;

public interface VideoDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table VIDEO
     *
     * @ibatorgenerated Sun Dec 11 16:55:16 COT 2011
     */
    int countByExample(VideoExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table VIDEO
     *
     * @ibatorgenerated Sun Dec 11 16:55:16 COT 2011
     */
    int deleteByExample(VideoExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table VIDEO
     *
     * @ibatorgenerated Sun Dec 11 16:55:16 COT 2011
     */
    int deleteByPrimaryKey(Integer idVideo);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table VIDEO
     *
     * @ibatorgenerated Sun Dec 11 16:55:16 COT 2011
     */
    void insert(Video record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table VIDEO
     *
     * @ibatorgenerated Sun Dec 11 16:55:16 COT 2011
     */
    void insertSelective(Video record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table VIDEO
     *
     * @ibatorgenerated Sun Dec 11 16:55:16 COT 2011
     */
    List<Video> selectByExample(VideoExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table VIDEO
     *
     * @ibatorgenerated Sun Dec 11 16:55:16 COT 2011
     */
    Video selectByPrimaryKey(Integer idVideo);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table VIDEO
     *
     * @ibatorgenerated Sun Dec 11 16:55:16 COT 2011
     */
    int updateByExampleSelective(Video record, VideoExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table VIDEO
     *
     * @ibatorgenerated Sun Dec 11 16:55:16 COT 2011
     */
    int updateByExample(Video record, VideoExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table VIDEO
     *
     * @ibatorgenerated Sun Dec 11 16:55:16 COT 2011
     */
    int updateByPrimaryKeySelective(Video record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table VIDEO
     *
     * @ibatorgenerated Sun Dec 11 16:55:16 COT 2011
     */
    int updateByPrimaryKey(Video record);

    List<Video> listarVideos(Integer idConfiguracion);
}