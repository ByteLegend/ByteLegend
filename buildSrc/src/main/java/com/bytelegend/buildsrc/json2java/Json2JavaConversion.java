package com.bytelegend.buildsrc.json2java;

import java.io.File;

public class Json2JavaConversion {
    private File srcFile;
    private File srcDir;
    private File destDir;
    private String targetPackage;

    public File getSrcFile() {
        return srcFile;
    }

    public Json2JavaConversion setSrcFile(File srcFile) {
        this.srcFile = srcFile;
        return this;
    }

    public File getSrcDir() {
        return srcDir;
    }

    public Json2JavaConversion setSrcDir(File srcDir) {
        this.srcDir = srcDir;
        return this;
    }

    public File getDestDir() {
        return destDir;
    }

    public Json2JavaConversion setDestDir(File destDir) {
        this.destDir = destDir;
        return this;
    }

    public String getTargetPackage() {
        return targetPackage;
    }

    public Json2JavaConversion setTargetPackage(String targetPackage) {
        this.targetPackage = targetPackage;
        return this;
    }
}
