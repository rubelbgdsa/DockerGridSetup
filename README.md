$planSpec = New-Object -TypeName PSObject -Property @{
    JournalsACLEnvId = "416908732"
    JournalsACLFlag = $true
    JournalsACLArtifactName = "journalsac1"
    JournalsACLVeracodeProfileName = "Asset Transfer-Journals-JournalsAc1 - AD00007195"
    JournalsACLVeracodeSandboxName = "Release"
    JournalsRestACLEnvId = "497913313"
    JournalsRestACLFlag = $true
    JournalsRestACLArtifactName = "journalsrestacl"
    JournalsRestACLVeracodeProfileName = "Asset Transfer-Journals-JournalsRestAc1-AD00007195"
    JournalsRestACLVeracodeSandboxName = "Release"
    ModernListApiEnvId = "416908678"
    ModernListApiFlag = $true
    ModernListApiArtifactName = "journalinstructions"
    ModernListApiveracodeProfileName = "Asset Transfer-Journals-JournalInstructions - AD00007195"
    ModernListApiVeracodeSandboxName = "Release"
}

$properties = $planSpec.psobject.properties
foreach ($property in $properties) {
    $values = @($property.Value, $planSpec."$($property.Name.Substring(0, $property.Name.Length - 4))Flag")
    $values += $planSpec."$($property.Name.Substring(0, $property.Name.Length - 4))EnvId"
    $values += $planSpec."$($property.Name.Substring(0, $property.Name.Length - 4))ArtifactName"
    $values += $planSpec."$($property.Name.Substring(0, $property.Name.Length - 4))VeracodeProfileName"
    $values += $planSpec."$($property.Name.Substring(0, $property.Name.Length - 4))VeracodeSandboxName"
    Write-Host $($values -join ', ')
}

