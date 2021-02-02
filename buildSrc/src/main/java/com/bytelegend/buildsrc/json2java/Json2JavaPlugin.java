package com.bytelegend.buildsrc.json2java;

import com.sun.codemodel.JPackage;
import com.sun.codemodel.JType;
import org.gradle.api.DefaultTask;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.TaskProvider;
import org.gradle.api.tasks.compile.AbstractCompile;
import org.jsonschema2pojo.DefaultGenerationConfig;
import org.jsonschema2pojo.GenerationConfig;
import org.jsonschema2pojo.Jsonschema2Pojo;
import org.jsonschema2pojo.SourceType;
import org.jsonschema2pojo.rules.Rule;
import org.jsonschema2pojo.rules.RuleFactory;
import org.jsonschema2pojo.util.ParcelableHelper;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

// This is a workaround for https://github.com/joelittlejohn/jsonschema2pojo/pull/1008
public class Json2JavaPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        project.afterEvaluate(__ -> {
            List<Json2JavaConversion> conversions = (List<Json2JavaConversion>) project.getExtensions().getExtraProperties().get("json2Java");
            List<TaskProvider<Json2JavaTask>> tasks = new ArrayList<>();
            for (int i = 0; i < conversions.size(); ++i) {
                tasks.add(createTask(project, i, conversions.get(i)));
            }
            project.getTasks().withType(AbstractCompile.class)
                    .all(it -> it.dependsOn(tasks));
        });
    }

    private TaskProvider<Json2JavaTask> createTask(Project project, int i, Json2JavaConversion conversion) {
        return project.getTasks().register("json2Java" + i, Json2JavaTask.class,
                task -> task.conversion = conversion);
    }

    public static class MyRuleFactory extends RuleFactory {
        // There's a bug in current implementation, if a JSON node name occurs multiple times in JSON heriarchy, the previous one will be overwritten
        // This map keeps name to ocurrence so we can have Node, Node2, Node3 to avoid overwritten
        private Map<String, Integer> typeNameToOccurence = new HashMap<>();

        public Rule<JPackage, JType> getObjectRule() {
            return new MyObjectRule(this, new ParcelableHelper(), getReflectionHelper(), typeNameToOccurence);
        }
    }
}
