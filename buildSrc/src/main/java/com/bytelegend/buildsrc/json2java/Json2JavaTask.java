package com.bytelegend.buildsrc.json2java;


import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;
import org.jsonschema2pojo.DefaultGenerationConfig;
import org.jsonschema2pojo.GenerationConfig;
import org.jsonschema2pojo.Jsonschema2Pojo;
import org.jsonschema2pojo.SourceType;
import org.jsonschema2pojo.rules.RuleFactory;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;

public class Json2JavaTask extends DefaultTask {
    Json2JavaConversion conversion;

    @TaskAction
    public void run() {
        File sourceDir = conversion.getSrcDir();
        File sourceFile = conversion.getSrcFile();
        File targetDir = conversion.getDestDir();
        String targetPackage = conversion.getTargetPackage();

        if (sourceFile != null) {
            generate(sourceFile, targetDir, targetPackage);
        } else {
            Stream.of(sourceDir.listFiles())
                    .filter(it -> it.getName().endsWith(".json"))
                    .forEach(it -> generate(it, targetDir, targetPackage));
        }
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
