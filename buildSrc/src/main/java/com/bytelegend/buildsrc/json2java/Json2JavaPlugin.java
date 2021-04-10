package com.bytelegend.buildsrc.json2java;

import org.gradle.api.DefaultTask;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.tasks.TaskProvider;
import org.gradle.api.tasks.compile.AbstractCompile;

import java.util.ArrayList;
import java.util.List;

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
            project.getTasks().register("json2Java", DefaultTask.class, task -> task.dependsOn(tasks));
        });
    }

    private TaskProvider<Json2JavaTask> createTask(Project project, int i, Json2JavaConversion conversion) {
        return project.getTasks().register("json2Java" + i, Json2JavaTask.class,
                task -> task.conversion = conversion);
    }
}
