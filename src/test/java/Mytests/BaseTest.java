public List<Environment> getStageList() {
    List<EnvParams> paramsList = new ArrayList<>();

    addEnvParams(paramsList, "development", getDevManifestYam1(), getDevAutoscaleUrl(), nonprodFoundations);
    addEnvParams(paramsList, "qa", getQaManifestYam1(), getQaAutoscaleUrl1(), nonprodFoundations);
    addEnvParams(paramsList, "pvs", getPvsManifestYam1(), getPvsAutoscaleUrl1(), nonprodFoundations);
    addEnvParams(paramsList, "uat", getUatManifestYam1(), getUatAutoscaleUrl1(), nonprodFoundations);
    addEnvParams(paramsList, "hen", getHenManifestYam1(), getHenAutoscaleUrl1(), nonprodFoundations);
    addEnvParams(paramsList, "tb", getTbManifestYam1(), getTbAutoscaleUrl1(), nonprodFoundations);
    addEnvParams(paramsList, "ty", getTyManifestYam1(), getTyAutoscaleUrl1(), nonprodFoundations);
    addEnvParams(paramsList, "prod", getProdManifestYam1(), getProdAutoscaleUrl1(), prodFoundations);

    return getEnvList(paramsList);
}

private void addEnvParams(List<EnvParams> paramsList, String name, String manifestYam1, String autoscaleUrl, String foundations) {
    if (manifestYam1 != null && !manifestYam1.isEmpty()) {
        paramsList.add(new EnvParams(name, "NonProd", name + "-ad00007195", false, foundations, autoscaleUrl, manifestYam1));
    }
}
