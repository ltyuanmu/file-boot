package cn.newtouch.step.file.dao;

import cn.newtouch.step.file.bean.FileBean;
import cn.newtouch.step.file.bean.FileBeanExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface FileBeanMapper {
    @SelectProvider(type=FileBeanSqlProvider.class, method="countByExample")
    long countByExample(FileBeanExample example);

    @DeleteProvider(type=FileBeanSqlProvider.class, method="deleteByExample")
    int deleteByExample(FileBeanExample example);

    @Delete({
        "delete from t_file",
        "where id = #{id,jdbcType=CHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into t_file (id, file_name, ",
        "key_name, suffix, ",
        "file_size, folder, ",
        "private_flag, create_user, ",
        "create_time, update_user, ",
        "update_time, delete_state)",
        "values (#{id,jdbcType=CHAR}, #{fileName,jdbcType=VARCHAR}, ",
        "#{keyName,jdbcType=VARCHAR}, #{suffix,jdbcType=VARCHAR}, ",
        "#{fileSize,jdbcType=VARCHAR}, #{folder,jdbcType=VARCHAR}, ",
        "#{privateFlag,jdbcType=BIT}, #{createUser,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{updateUser,jdbcType=VARCHAR}, ",
        "#{updateTime,jdbcType=TIMESTAMP}, #{deleteState,jdbcType=BIT})"
    })
    int insert(FileBean record);

    @InsertProvider(type=FileBeanSqlProvider.class, method="insertSelective")
    int insertSelective(FileBean record);

    @SelectProvider(type=FileBeanSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="file_name", property="fileName", jdbcType=JdbcType.VARCHAR),
        @Result(column="key_name", property="keyName", jdbcType=JdbcType.VARCHAR),
        @Result(column="suffix", property="suffix", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_size", property="fileSize", jdbcType=JdbcType.VARCHAR),
        @Result(column="folder", property="folder", jdbcType=JdbcType.VARCHAR),
        @Result(column="private_flag", property="privateFlag", jdbcType=JdbcType.BIT),
        @Result(column="create_user", property="createUser", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_user", property="updateUser", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="delete_state", property="deleteState", jdbcType=JdbcType.BIT)
    })
    List<FileBean> selectByExample(FileBeanExample example);

    @Select({
        "select",
        "id, file_name, key_name, suffix, file_size, folder, private_flag, create_user, ",
        "create_time, update_user, update_time, delete_state",
        "from t_file",
        "where id = #{id,jdbcType=CHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="file_name", property="fileName", jdbcType=JdbcType.VARCHAR),
        @Result(column="key_name", property="keyName", jdbcType=JdbcType.VARCHAR),
        @Result(column="suffix", property="suffix", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_size", property="fileSize", jdbcType=JdbcType.VARCHAR),
        @Result(column="folder", property="folder", jdbcType=JdbcType.VARCHAR),
        @Result(column="private_flag", property="privateFlag", jdbcType=JdbcType.BIT),
        @Result(column="create_user", property="createUser", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_user", property="updateUser", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="delete_state", property="deleteState", jdbcType=JdbcType.BIT)
    })
    FileBean selectByPrimaryKey(String id);

    @UpdateProvider(type=FileBeanSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") FileBean record, @Param("example") FileBeanExample example);

    @UpdateProvider(type=FileBeanSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") FileBean record, @Param("example") FileBeanExample example);

    @UpdateProvider(type=FileBeanSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(FileBean record);

    @Update({
        "update t_file",
        "set file_name = #{fileName,jdbcType=VARCHAR},",
          "key_name = #{keyName,jdbcType=VARCHAR},",
          "suffix = #{suffix,jdbcType=VARCHAR},",
          "file_size = #{fileSize,jdbcType=VARCHAR},",
          "folder = #{folder,jdbcType=VARCHAR},",
          "private_flag = #{privateFlag,jdbcType=BIT},",
          "create_user = #{createUser,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_user = #{updateUser,jdbcType=VARCHAR},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "delete_state = #{deleteState,jdbcType=BIT}",
        "where id = #{id,jdbcType=CHAR}"
    })
    int updateByPrimaryKey(FileBean record);
}