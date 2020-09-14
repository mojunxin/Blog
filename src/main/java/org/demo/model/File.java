package org.demo.model;

import java.util.Date;
import java.util.UUID;

public class File {

  private String id;
  private String fileName;
  private String filePath;
  private String fileType;
  private Date createDate;
  private String createBy;


  public String getId() {
    return id == null?String.valueOf(UUID.randomUUID()):id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }


  public String getFilePath() {
    return filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }


  public String getFileType() {
    return fileType;
  }

  public void setFileType(String fileType) {
    this.fileType = fileType;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public String getCreateBy() {
    return createBy;
  }

  public void setCreateBy(String createBy) {
    this.createBy = createBy;
  }

}
