package example.mapper;

import example.model.SystemAttr;
import java.util.List;

public interface SystemAttrMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table system_attr
     *
     * @mbg.generated Thu Jul 16 13:35:45 MSK 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table system_attr
     *
     * @mbg.generated Thu Jul 16 13:35:45 MSK 2020
     */
    int insert(SystemAttr record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table system_attr
     *
     * @mbg.generated Thu Jul 16 13:35:45 MSK 2020
     */
    SystemAttr selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table system_attr
     *
     * @mbg.generated Thu Jul 16 13:35:45 MSK 2020
     */
    List<SystemAttr> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table system_attr
     *
     * @mbg.generated Thu Jul 16 13:35:45 MSK 2020
     */
    int updateByPrimaryKey(SystemAttr record);
}