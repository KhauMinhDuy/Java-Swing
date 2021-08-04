package com.khauminhduy.util;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class FileExtenstions extends FileFilter {

	private String extenstion;

	public FileExtenstions() {

	}

	public FileExtenstions(String extenstion) {
		this.extenstion = extenstion;
	}

	@Override
	public boolean accept(File file) {
		String name = file.getName();
		String fileExtensions = Utils.getFileExtensions(name);
		if (fileExtensions == null) {
			return false;
		}
		if (fileExtensions.equals(extenstion)) {
			return true;
		}
		return false;
	}

	@Override
	public String getDescription() {
		return String.format("filter file (*.%s)", extenstion);
	}

}
