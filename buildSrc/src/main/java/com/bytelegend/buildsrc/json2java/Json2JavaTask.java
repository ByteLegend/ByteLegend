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


import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.InputFiles;
import org.gradle.api.tasks.OutputFiles;
import org.gradle.api.tasks.TaskAction;
import org.jsonschema2pojo.DefaultGenerationConfig;
import org.jsonschema2pojo.GenerationConfig;
import org.jsonschema2pojo.Jsonschema2Pojo;
import org.jsonschema2pojo.SourceType;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Json2JavaTask extends DefaultTask {
    Json2JavaConversion conversion;

    @InputFiles
    public List<File> getInputJsons() {
        if (conversion.getSrcFile() != null) {
            return Collections.singletonList(conversion.getSrcFile());
        } else {
            return Arrays.stream(conversion.getSrcDir().listFiles())
                    .filter(it -> it.getName().endsWith(".json"))
                    .collect(Collectors.toList());
        }
    }

    @OutputFiles
    public List<File> getOutputJavas() {
        return getInputJsons().stream()
                .map(it -> {
                    File javaDestDir = new File(conversion.getDestDir(), conversion.getTargetPackage().replace('.', '/'));
                    return new File(javaDestDir, it.getName().replace(".json", ".java"));
                })
                .collect(Collectors.toList());
    }

    @TaskAction
    public void run() {
        getInputJsons().forEach(it -> generate(it, conversion.getDestDir(), conversion.getTargetPackage()));
    }

    // This only supports generating 1 class for 1 JSON in each generate call
    private void generate(File srcJson, File targetDir, String targetPackage) {
        GenerationConfig config = new DefaultGenerationConfig() {
            @Override
            public File getTargetDirectory() {
                return targetDir;
            }

            @Override
            public String getTargetPackage() {
                return targetPackage;
            }

            @Override
            public SourceType getSourceType() {
                return SourceType.JSON;
            }

            @Override
            public boolean isIncludeConstructors() {
                return true;
            }

            @Override
            public boolean isUseLongIntegers() {
                return true;
            }

            @Override
            public Iterator<URL> getSource() {
                try {
                    return Arrays.asList(srcJson.toURI().toURL()).iterator();
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        try {
            Jsonschema2Pojo.generate(config, null);
            makeAllEventsImplementGitHubEventInterface(srcJson, targetDir, targetPackage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void makeAllEventsImplementGitHubEventInterface(File srcJson, File targetDir, String targetPackage) throws IOException {
        // Abc.json -> Abc
        String baseName = srcJson.getName().substring(0, srcJson.getName().length() - 5);
        if (baseName.endsWith("GitHubEvent")) {
            File generatedJava = new File(targetDir, targetPackage.replace('.', '/') + "/" + baseName + ".java");
            String generatedJavaContent = new String(Files.readAllBytes(generatedJava.toPath()));
            generatedJavaContent = generatedJavaContent.replace("class " + baseName, "class " + baseName + " implements com.bytelegend.app.jsonmodel.event.GitHubEvent");
            Files.write(generatedJava.toPath(), generatedJavaContent.getBytes());
        }
    }

}
