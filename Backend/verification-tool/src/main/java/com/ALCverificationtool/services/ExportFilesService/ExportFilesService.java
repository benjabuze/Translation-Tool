package com.ALCverificationtool.services.ExportFilesService;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public interface ExportFilesService {

    void createFolder(String language, String versionNumber);

    void createXMLFile(String language, String versionNumber) throws ParserConfigurationException, TransformerException, IOException;
}