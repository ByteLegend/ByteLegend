/*
 * Copyright 2021 ByteLegend Technologies and the original author or authors.
 * 
 * Licensed under the GNU Affero General Public License v3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      https://github.com/ByteLegend/ByteLegend/blob/master/LICENSE
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
