# Set the Bamboo server URL and credentials
$bambooUrl = "https://your-bamboo-server"
$username = "your-username"
$password = "your-password"

# Create a base64-encoded authentication token
$base64Auth = [Convert]::ToBase64String([Text.Encoding]::ASCII.GetBytes(("{0}:{1}" -f $username,$password)))

# Set the headers for authentication
$headers = @{
    "Authorization" = "Basic $base64Auth"
    "Content-Type"  = "application/json"
}

# Function to send a GET request to the Bamboo REST API
function Get-BambooRestApi {
    param(
        [string]$Url
    )

    $response = Invoke-RestMethod -Uri $Url -Headers $headers -Method Get
    return $response
}

# Function to send a DELETE request to the Bamboo REST API
function Remove-BambooRestApi {
    param(
        [string]$Url
    )

    Invoke-RestMethod -Uri $Url -Headers $headers -Method Delete
}

# Define the project key and plan key for the Bamboo plan you want to manage
$projectKey = "YOUR_PROJECT_KEY"
$planKey = "YOUR_PLAN_KEY"

# Get the list of plan branches
$branchesUrl = "$bambooUrl/rest/api/latest/plan/$projectKey-$planKey/branch"
$branches = Get-BambooRestApi -Url $branchesUrl

# Iterate through the branches, filter out the release branch, and check for inactivity
$releaseBranchName = "release"  # Update with your release branch name
$today = Get-Date
$sevenDaysAgo = $today.AddDays(-7)

foreach ($branch in $branches | Where-Object { $_.name -ne $releaseBranchName }) {
    $resultsUrl = "$bambooUrl/rest/api/latest/result/$projectKey-$planKey-$($branch.name)"
    $results = Get-BambooRestApi -Url $resultsUrl

    $lastActivityDate = $results.results[0].buildCompletedDate

    if ($lastActivityDate -lt $sevenDaysAgo) {
        # Mark branch for deletion
        Write-Output "Marking branch $($branch.name) for deletion."
        $deleteUrl = "$bambooUrl/rest/api/latest/plan/$projectKey-$planKey/branch/$($branch.name)"
        Remove-BambooRestApi -Url $deleteUrl
    }
}
