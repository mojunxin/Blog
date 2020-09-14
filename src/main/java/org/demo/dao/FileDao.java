package org.demo.dao;

import org.apache.ibatis.annotations.Param;
import org.demo.model.File;

import java.util.List;

public interface FileDao {
    void save(File file);

    Integer count();

    File findByFileName(@Param("fileName") String fileName);

    List<File> findFile();
}
