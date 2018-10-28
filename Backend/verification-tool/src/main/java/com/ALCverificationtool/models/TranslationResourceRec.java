package com.ALCverificationtool.models;


public class TranslationResourceRec {

    private String languageCode;
    private String languageVersion;
    private Long keyId;
    private String keyName;
    private boolean keyModified;
    private boolean keyNew;
    private String keyVariant;
    private String keyNote;
    private String sectionId;
    private String sectionNote;
    private String fileName;
    private String fileNotes;
    private boolean approved;
    private String folderPath;

    public TranslationResourceRec() {
        keyId = null;
    }

    public TranslationResourceRec(TranslationResourceRec original) {
        this.keyName = original.keyName;
        this.keyModified = original.keyModified;
        this.keyNew = original.keyNew;
        this.keyVariant = original.keyVariant;
        this.keyNote = original.keyNote;
        this.sectionId = original.sectionId;
        this.sectionNote = original.sectionNote;
        this.fileName = original.fileName;
        this.fileNotes = original.fileNotes;
        this.approved = original.approved;
        this.folderPath = original.folderPath;
        this.languageCode = original.languageCode;
        this.languageVersion = original.languageVersion;
        keyId = null;
    }

    public TranslationResourceRec(String keyName, boolean keyModified, boolean keyNew,
                                  String keyVariant, String keyNote, String sectionId,
                                  String sectionNote, String fileName, String fileNotes,
                                  boolean approved, String folderPath, String languageCode, String languageVersion){
        this.keyName = keyName;
        this.keyModified = keyModified;
        this.keyNew = keyNew;
        this.keyVariant = keyVariant;
        this.keyNote = keyNote;
        this.sectionId = sectionId;
        this.sectionNote = sectionNote;
        this.fileName = fileName;
        this.fileNotes = fileNotes;
        this.approved = approved;
        this.folderPath = folderPath;
        this.languageCode = languageCode;
        this.languageVersion = languageVersion ;
        keyId = null;
    }

    public Long getKeyId() {return keyId;}
    public void setKeyId(Long keyId) {this.keyId = keyId;}

    public String getKeyName() {return keyName;}
    public void setKeyName(String keyName) {this.keyName = keyName;}

    public boolean getKeyModified() {return keyModified;}
    public void setKeyModified(boolean keymodified) {this.keyModified = keymodified;}

    public boolean getKeyNew() {return keyNew;}
    public void setKeyNew(boolean keyNew) {this.keyNew = keyNew;}

    public String getKeyVariant() {return keyVariant;}
    public void setKeyVariant(String keyVariant) {this.keyVariant = keyVariant;}

    public String getKeyNote() {return keyNote;}
    public void setKeyNote(String keyNote) {this.keyNote = keyNote;}

    public String getSectionId() {return sectionId;}
    public void setSectionId(String sectionId) {this.sectionId = sectionId;}

    public String getSectionNote() {return sectionNote;}
    public void setSectionNote(String sectionNote) {this.sectionNote = sectionNote;}

    public String getFileName() {return fileName;}
    public void setFileName(String fileName) {this.fileName = fileName;}

    public String getFileNotes() {return fileNotes;}
    public void setFileNotes(String fileNotes) {this.fileNotes = fileNotes;}

    public boolean getApproved() { return approved;}
    public void setApproved(boolean approved) {this.approved = approved;}

    public String getFolderPath() { return folderPath;}
    public void setFolderPath(String folderPath) { this.folderPath = folderPath;}

    public String getLanguageCode() { return languageCode;}
    public void setLanguageCode(String languageCode) {this.languageCode = languageCode ;}

    public String getLanguageVersion() { return languageVersion;}
    public void setLanguageVersion(String languageVersion) {this.languageVersion = languageVersion ;}
}