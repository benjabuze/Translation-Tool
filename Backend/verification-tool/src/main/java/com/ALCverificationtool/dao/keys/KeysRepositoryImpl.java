package com.ALCverificationtool.dao.keys;

import com.ALCverificationtool.models.TranslationResourceRec;
import com.ALCverificationtool.services.ServiceException;
import com.ALCverificationtool.services.userService.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Component
public class KeysRepositoryImpl implements KeysRepository {

    private static final String DROP_SQL =
            "DROP TABLE IF EXISTS `TABLE_NAME`";

    private static final String CREATE_SQL =
            "CREATE TABLE `TABLE_NAME` ( " +
            "  `approved` tinyint(4) NOT NULL," +
            "  `file_name` varchar(255) DEFAULT NULL," +
            "  `file_notes` varchar(2000) DEFAULT NULL," +
            "  `folder_path` varchar(255) DEFAULT NULL," +
            "  `key_name` varchar(255) NOT NULL," +
            "  `key_new` tinyint(4) NOT NULL," +
            "  `key_note` varchar(2000) DEFAULT NULL," +
            "  `key_variant` varchar(2000) DEFAULT NULL," +
            "  `section_id` varchar(255) DEFAULT NULL," +
            "  `section_note` varchar(2000) DEFAULT NULL," +
            "  `key_id` bigint(20) NOT NULL AUTO_INCREMENT," +
            "  PRIMARY KEY (`key_id`)" +
            ") ENGINE=MyISAM DEFAULT CHARSET=utf8";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public boolean createKeyTable(String keyLanguageVersion, String keyLanguageCode, boolean dropExisting) {

        String newTableName= keyLanguageCode + "_" + keyLanguageVersion;
        System.out.println(DROP_SQL.replace("TABLE_NAME", newTableName));
        System.out.println(CREATE_SQL.replace("TABLE_NAME", newTableName));

        // first make sure that keyLanguageCode and keyLanguageVersion are not null and valid
        if(keyLanguageCode == null && keyLanguageVersion == null) {
            throw new ServiceException("keyLanguageCode & keyLanguageVersion");
        }

        // if dropExisting
        if(true) {
            // then drop any existing table
            jdbcTemplate.update(DROP_SQL.replace("TABLE_NAME", newTableName));
            jdbcTemplate.update(CREATE_SQL.replace("TABLE_NAME", newTableName));

            // make sure the table exists

            return true;
        }

        else {
            // check to see if table exists
            if(true) {
                return true;
            }
        }
        // create new table with the the given tablename


        // check if successfull
        if(true) {
            return true;
        }

        return false;
    }

    @Override
    public TranslationResourceRec create(TranslationResourceRec keyData) {
        // first check that keyData is valid and not not
        if(keyData == null) {
            throw new UserException("key Data is not valid");
        }
        String tableName = toTableName(keyData.getLanguageCode(), keyData.getLanguageVersion());


        String INSERT = "INSERT INTO " + tableName + " (approved,file_name,file_notes,folder_path,\n" +
                "key_name,key_new,key_note,key_variant,\n" +
                "section_id,section_note)\n" +
                "VALUES (?,?,?,?,?,?,?,?,?,?);\n";


        Object[] parameters = new Object[] {
                keyData.getKeyApproved(),
                keyData.getFileName(),
                keyData.getFileNotes(),
                keyData.getFolderPath(),
                keyData.getKeyName(),
                keyData.getKeyNew(),
                keyData.getKeyNote(),
                keyData.getKeyVariant(),
                keyData.getSectionId(),
                keyData.getSectionNote()
        };
        jdbcTemplate.update(INSERT, parameters);
        return null;
    }
    private String toTableName(String keyLanguageCode, String keyLanguageVersion) {

        return keyLanguageCode + "_" + keyLanguageVersion;
    }

    @Override
    public List<TranslationResourceRec> getKeys(String tableName) {
        List<TranslationResourceRec> results = new ArrayList<>();
        String query = "SELECT * FROM " + tableName;
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
        for (Map row : rows) {
////        List<Map<String, Object>> temp = jdbcTemplate.queryForList(query);
////        for (Map<String, Object> map : temp){
            TranslationResourceRec tmp = new TranslationResourceRec();
////            for (Map.Entry<String, Object> entry : map.entrySet()) {
////                String key = entry.getKey();
////                Object value = entry.getValue();
////                if (key.equals("file_name")) {
////                    result.setFileName(value.toString());
////                }
////                if (key.equals("file_notes")) {
////                    result.setFileNotes(value.toString());
////                }
////                if (key.equals("folder_path")) {
////                    result.setFolderPath(value.toString());
////                }
////                if (key.equals("key_name")) {
////                    result.setKeyName(value.toString());
////                }
////                if (key.equals("key_note")) {
////                    result.setKeyNote(value.toString());
////                }
////                if (key.equals("key_variant")) {
////                    result.setKeyVariant(value.toString());
////                }
////                if (key.equals("section_id")) {
////                    result.setSectionId(value.toString());
////                }
////                if (key.equals("section_note")) {
////                    result.setSectionNote(value.toString());
////                }
////                if (key.equals("key_id")) {
////                    result.setKeyId(Long.parseLong(value.toString()));
////                }
            //tmp.setApproved((Boolean)row.get("approved"));
            if (row.get("approved").equals(1)) {
                tmp.setApproved(true);
            } else {
                tmp.setApproved(false);
            }
            tmp.setFileName((String)row.get("file_name"));
            tmp.setFileNotes((String)row.get("file_notes"));
            tmp.setKeyName((String)row.get("key_name"));
            //tmp.setKeyNew((Boolean)row.get("key_new"));
            if (row.get("key_new").equals(1)) {
                tmp.setKeyNew(true);
            } else {
                tmp.setKeyNew(false);
            }
            tmp.setKeyNote((String)row.get("key_note"));
            tmp.setKeyVariant((String)row.get("key_variant"));
            tmp.setSectionId((String)row.get("section_id"));
            tmp.setSectionNote((String)row.get("section_note"));
            tmp.setKeyId((Long)row.get("key_id"));
            if (tmp != null) {
                results.add(tmp);
            }
        }
        return results;
    }
}