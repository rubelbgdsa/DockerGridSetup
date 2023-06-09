public Environment createEnvironment(String envName, String target, String space, boolean isAutoscaled, String foundations, String autoScaleUrl, String manifestYaml) {
    Environment environment = new Environment(envName);
    String deployType = getDeployType();
    
    switch (deployType) {
        case "Blue Green":
            if (envName.contains("Production-Swap")) {
                environment.finalTasks(pcfSwapTask(), createRepoTag());
            } else if (envName.contains("Decommission")) {
                environment.finalTasks(decommissionTask());
            } else {
                environment.finalTasks(genericDeploymentTask(envName, target, space, isAutoscaled, foundations, autoScaleUrl, manifestYaml));
            }
            break;
        
        default:
            if (envName.contains("Production")) {
                environment.finalTasks(genericDeploymentTask(envName, target, space, isAutoscaled, foundations, autoScaleUrl, manifestYaml), createRepoTag());
            } else {
                environment.finalTasks(genericDeploymentTask(envName, target, space, isAutoscaled, foundations, autoScaleUrl, manifestYaml));
            }
    }
    
    return environment;
}
