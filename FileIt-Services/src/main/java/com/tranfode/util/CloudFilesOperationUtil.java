package com.tranfode.util;

import java.io.InputStream;

public class CloudFilesOperationUtil {

	/**
	 * @param jsonFileName
	 * @return
	 * @throws FileItException
	 */
	public InputStream getFIleInputStream(String jsonFileName) throws FileItException {
		InputStream oInputStream = null;
		oInputStream = CloudStorageConfig.getInstance()
				.getFile(CloudPropertiesReader.getInstance().getString("bucket.name"), jsonFileName);

		return oInputStream;
	}

	/**
	 * @param jsonFileName
	 * @param is
	 * @param fileType
	 * @throws FileItException
	 */
	public void fIleUploaded(String jsonFileName, InputStream is, String fileType) throws FileItException {
		CloudStorageConfig.getInstance().uploadFile(CloudPropertiesReader.getInstance().getString("bucket.name"),
				jsonFileName, is, fileType);
	}

}
