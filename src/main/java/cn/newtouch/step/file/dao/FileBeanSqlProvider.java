package cn.newtouch.step.file.dao;

import cn.newtouch.step.file.bean.FileBean;
import cn.newtouch.step.file.bean.FileBeanExample.Criteria;
import cn.newtouch.step.file.bean.FileBeanExample.Criterion;
import cn.newtouch.step.file.bean.FileBeanExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class FileBeanSqlProvider {

    public String countByExample(FileBeanExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("t_file");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(FileBeanExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("t_file");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(FileBean record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("t_file");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=CHAR}");
        }
        
        if (record.getFileName() != null) {
            sql.VALUES("file_name", "#{fileName,jdbcType=VARCHAR}");
        }
        
        if (record.getKeyName() != null) {
            sql.VALUES("key_name", "#{keyName,jdbcType=VARCHAR}");
        }
        
        if (record.getSuffix() != null) {
            sql.VALUES("suffix", "#{suffix,jdbcType=VARCHAR}");
        }
        
        if (record.getFileSize() != null) {
            sql.VALUES("file_size", "#{fileSize,jdbcType=VARCHAR}");
        }
        
        if (record.getFolder() != null) {
            sql.VALUES("folder", "#{folder,jdbcType=VARCHAR}");
        }
        
        if (record.getPrivateFlag() != null) {
            sql.VALUES("private_flag", "#{privateFlag,jdbcType=BIT}");
        }
        
        if (record.getCreateUser() != null) {
            sql.VALUES("create_user", "#{createUser,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateUser() != null) {
            sql.VALUES("update_user", "#{updateUser,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("update_time", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDeleteState() != null) {
            sql.VALUES("delete_state", "#{deleteState,jdbcType=BIT}");
        }
        
        return sql.toString();
    }

    public String selectByExample(FileBeanExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("file_name");
        sql.SELECT("key_name");
        sql.SELECT("suffix");
        sql.SELECT("file_size");
        sql.SELECT("folder");
        sql.SELECT("private_flag");
        sql.SELECT("create_user");
        sql.SELECT("create_time");
        sql.SELECT("update_user");
        sql.SELECT("update_time");
        sql.SELECT("delete_state");
        sql.FROM("t_file");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        FileBean record = (FileBean) parameter.get("record");
        FileBeanExample example = (FileBeanExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("t_file");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=CHAR}");
        }
        
        if (record.getFileName() != null) {
            sql.SET("file_name = #{record.fileName,jdbcType=VARCHAR}");
        }
        
        if (record.getKeyName() != null) {
            sql.SET("key_name = #{record.keyName,jdbcType=VARCHAR}");
        }
        
        if (record.getSuffix() != null) {
            sql.SET("suffix = #{record.suffix,jdbcType=VARCHAR}");
        }
        
        if (record.getFileSize() != null) {
            sql.SET("file_size = #{record.fileSize,jdbcType=VARCHAR}");
        }
        
        if (record.getFolder() != null) {
            sql.SET("folder = #{record.folder,jdbcType=VARCHAR}");
        }
        
        if (record.getPrivateFlag() != null) {
            sql.SET("private_flag = #{record.privateFlag,jdbcType=BIT}");
        }
        
        if (record.getCreateUser() != null) {
            sql.SET("create_user = #{record.createUser,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateUser() != null) {
            sql.SET("update_user = #{record.updateUser,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDeleteState() != null) {
            sql.SET("delete_state = #{record.deleteState,jdbcType=BIT}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("t_file");
        
        sql.SET("id = #{record.id,jdbcType=CHAR}");
        sql.SET("file_name = #{record.fileName,jdbcType=VARCHAR}");
        sql.SET("key_name = #{record.keyName,jdbcType=VARCHAR}");
        sql.SET("suffix = #{record.suffix,jdbcType=VARCHAR}");
        sql.SET("file_size = #{record.fileSize,jdbcType=VARCHAR}");
        sql.SET("folder = #{record.folder,jdbcType=VARCHAR}");
        sql.SET("private_flag = #{record.privateFlag,jdbcType=BIT}");
        sql.SET("create_user = #{record.createUser,jdbcType=VARCHAR}");
        sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        sql.SET("update_user = #{record.updateUser,jdbcType=VARCHAR}");
        sql.SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        sql.SET("delete_state = #{record.deleteState,jdbcType=BIT}");
        
        FileBeanExample example = (FileBeanExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(FileBean record) {
        SQL sql = new SQL();
        sql.UPDATE("t_file");
        
        if (record.getFileName() != null) {
            sql.SET("file_name = #{fileName,jdbcType=VARCHAR}");
        }
        
        if (record.getKeyName() != null) {
            sql.SET("key_name = #{keyName,jdbcType=VARCHAR}");
        }
        
        if (record.getSuffix() != null) {
            sql.SET("suffix = #{suffix,jdbcType=VARCHAR}");
        }
        
        if (record.getFileSize() != null) {
            sql.SET("file_size = #{fileSize,jdbcType=VARCHAR}");
        }
        
        if (record.getFolder() != null) {
            sql.SET("folder = #{folder,jdbcType=VARCHAR}");
        }
        
        if (record.getPrivateFlag() != null) {
            sql.SET("private_flag = #{privateFlag,jdbcType=BIT}");
        }
        
        if (record.getCreateUser() != null) {
            sql.SET("create_user = #{createUser,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateUser() != null) {
            sql.SET("update_user = #{updateUser,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDeleteState() != null) {
            sql.SET("delete_state = #{deleteState,jdbcType=BIT}");
        }
        
        sql.WHERE("id = #{id,jdbcType=CHAR}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, FileBeanExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            sql.WHERE(sb.toString());
        }
    }
}