Challenge 2	: We need to write code that will query the meta data of an instance within Azure and provide a json formatted output. 
Ans:code.ps1

$metadataEndpoint = "http://169.254.169.254/metadata/instance?api-version=2021-02-01"
function Get-AzureMetadata {
    param (
        [string]$key
    }
    try {
        $metadata = Invoke-RestMethod -Uri $metadataEndpoint -Headers @{Metadata="true"} -TimeoutSec  2
        if ($metadata.PSObject.Properties[$key]) {
            $output = @{
                $key = $metadata.$key
            }
            Write-Output ($output | ConvertTo-Json -Depth 4)
        } else {
            Write-Output ("{""error"": ""Key '$key' not found in Azure metadata.""}")
        }
    } catch {
        # Handle any errors that occur during the request
        Write-Output ("{""error"": ""Failed to retrieve Azure metadata: $_""}")
    }
}
$keyToRetrieve = "network/interface/0/ipv4/ipAddress/0/privateIpAddress"
Get-AzureMetadata -key $keyToRetrieve
